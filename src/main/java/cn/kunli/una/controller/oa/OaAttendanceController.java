package cn.kunli.una.controller.oa;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.oa.OaAttendance;
import cn.kunli.una.pojo.system.*;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.oa.OaAttendanceService;
import cn.kunli.una.service.system.SysPermissionService;
import cn.kunli.una.utils.common.DateUtil;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.UserUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 办公-考勤(OaAttendance)表控制层
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:20
 */
@Controller
@RequestMapping("/oa/attendance")
public class OaAttendanceController extends BaseController<OaAttendanceService, OaAttendance> {

    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 自动生成考勤记录
     */
    @Scheduled(cron = "0 1 0 * * ?")//凌晨12:01
    @PostMapping("/auto")
    @ResponseBody
    private void autoAttendance(){
        //查询所有有修改考勤记录权限的人，都需要打卡
        SysEntity sysEntity = sysEntityService.selectOne(MapUtil.getMap("code", entityClassName));
        if(sysEntity!=null){
            //查询修改考勤记录的权限
            SysPermission permission = sysPermissionService.selectOne(MapUtil.buildHashMap().put("type_dcode", "permission_type_update")
                            .put("entityId", sysEntity.getId()).build());
            if(permission!=null){
                List<SysRolePermission> rolePermissionList = sysRolePermissionService.selectList(MapUtil.buildHashMap().put("permissionId", permission.getId())
                                .put("ne:scope_dcode", "permission_scope_0").build());

                if(CollectionUtils.isNotEmpty(rolePermissionList)){
                    StringBuffer stringBuffer = new StringBuffer();
                    for (SysRolePermission sysRolePermission : rolePermissionList) {
                        stringBuffer.append(",").append(sysRolePermission.getRoleId());
                    }
                    //获取到所有需要考勤的角色id
                    String roleIds = stringBuffer.delete(0, 1).toString();
                    //查询拥有这些角色的所有账号
                    List<SysAccount> accountList = sysAccountService.selectList(MapUtil.getMap("*:apply", "CONCAT(role_id, ',') REGEXP CONCAT(REPLACE('"+roleIds+"',',',',|'),',') =1"));
                    //生成考勤记录
                    OaAttendance oaAttendance = (OaAttendance) new OaAttendance().setAttendanceDate(DateUtil.getDayBegin());
                    for (SysAccount sysAccount : accountList) {
                        oaAttendance.setAccountId(sysAccount.getId()).setCompanyId(sysAccount.getCompanyId())
                                .setDepartmentId(sysAccount.getDepartmentId()).setCreatorId(sysAccount.getId());
                        service.saveRecord((OaAttendance)oaAttendance.setIsOnDuty(true).setId(null)); //上班打卡记录
                        service.saveRecord((OaAttendance)oaAttendance.setIsOnDuty(false).setId(null)); //下班打卡记录
                    }
                }
            }

        }


    }


    /**
     * 考勤打卡
     */
    @ResponseBody
    @PutMapping ("/punch/{coord}")
    private SysResult punch(@PathVariable String coord){
        Date punchTime = new Date();
        String time = DateUtil.getStrOfTime(punchTime);

        //查询配置项 匹配的时间区间
        SysConfiguration oaAttendanceConfig = sysConfigurationService.selectOne(MapUtil.buildHashMap().put("le:lower", time)
                .put("gt:upper", time).put(":code", "OaAttendance_").build());

        if(oaAttendanceConfig==null)return SysResult.fail("打卡失败，当前不在打卡时间内");

        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();

        Map<String, Object> queryMap = MapUtil.buildHashMap().put("accountId", loginUser.getId())
                .put("attendanceDate", DateUtil.getDayBegin()).put("is_on_duty",0).build();
        if(oaAttendanceConfig.getValue().equals("上班"))queryMap.put("is_on_duty",1);
        //查询对应账号的考勤记录
        OaAttendance oaAttendance = service.selectOne(queryMap);
        if(oaAttendance==null)return SysResult.fail("打卡失败，未生成当天的考勤记录");

        //查到了对应的考勤记录
        if(oaAttendance.getSignTime()!=null && oaAttendanceConfig.getValue().equals("上班")){//上班打卡，不能重复打卡，不能覆盖前面的，下班打卡可以覆盖
            return SysResult.fail("无需重复打卡");
        }else{
            SysResult sysResult = service.updateRecordById((OaAttendance) oaAttendance.setCoord(coord).setSignTime(punchTime).setName(oaAttendanceConfig.getDescription()));
            return sysResult;
        }

    }


}
