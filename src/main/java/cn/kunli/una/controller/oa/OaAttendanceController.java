package cn.kunli.una.controller.oa;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.oa.OaAttendance;
import cn.kunli.una.pojo.system.SysAccount;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.pojo.system.SysPermission;
import cn.kunli.una.pojo.system.SysRolePermission;
import cn.kunli.una.service.oa.OaAttendanceService;
import cn.kunli.una.service.system.SysPermissionService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.DateUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    @PostMapping("/autoAttendance")
    private void autoAttendance(){
        //查询所有有修改考勤记录权限的人，都需要打卡
        SysEntity sysEntity = sysEntityService.selectOne(MapUtil.getMap("code", entityClassName));
        if(sysEntity!=null){
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
                    OaAttendance oaAttendance = (OaAttendance) new OaAttendance().setAttendanceDate(DateUtil.getDayBegin()).setCreatorId(100000);
                    for (SysAccount sysAccount : accountList) {
                        service.saveRecord((OaAttendance) oaAttendance.setAccountId(sysAccount.getId()).setId(null).setCompanyId(sysAccount.getCompanyId()).setDepartmentId(sysAccount.getDepartmentId()));
                    }
                }
            }

        }


    }
}
