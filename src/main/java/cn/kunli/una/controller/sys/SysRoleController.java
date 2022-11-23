package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysDictionary;
import cn.kunli.una.pojo.sys.SysEntity;
import cn.kunli.una.pojo.sys.SysRole;
import cn.kunli.una.service.sys.SysPermissionService;
import cn.kunli.una.service.sys.SysRoleService;
import cn.kunli.una.utils.common.UnaMapUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * (SysRole)表控制层
 *
 * @author Ponzio
 * @since 2020-05-08 18:04:54
 */
@Controller
@Api(tags = "系统-角色")
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController<SysRoleService, SysRole> {

    @Autowired
    private SysPermissionService sysPermissionService;


    //打开授权表单
    @RequestMapping("/authorization")
    public String authorization(Model model, SysRole obj) {
        SysDictionary permissionScopeDic = sysDictionaryService.selectOne(UnaMapUtil.getMap("code", "permission_scope"));
        if(permissionScopeDic!=null){
            model.addAttribute("scopeList", sysDictionaryService.selectList(UnaMapUtil.getMap("parentId", permissionScopeDic.getId())));
        }

        List<SysEntity> entityList = sysEntityService.selectList(null);
        for (SysEntity sysEntity : entityList) {
            sysEntity.setPermissionList(sysPermissionService.selectList(sysPermissionService.format(UnaMapUtil.getMap("entityId",sysEntity.getId()))));
        }
        model.addAttribute("entityList", entityList);
        model.addAttribute("sample", obj);
        return "system/role/authorization";
    }

    //授权
    /*@RequestMapping("/saveAuthorization")
    @ResponseBody
    public SysResult saveAuthorization(Model model, SysRole obj) {
        if (obj.getPermMap() != null) {
            for (Map.Entry<Integer, String> entry : obj.getPermMap().entrySet()) {
                boolean updateResult = sysRolePermissionService.updateById(
                        (SysAuthorization) new SysAuthorization().setScopeDcode(entry.getValue()).setId(entry.getKey()));
                if (!updateResult) return SysResult.fail();
            }
            return SysResult.success();
        }
        return SysResult.fail();
    }*/

}
