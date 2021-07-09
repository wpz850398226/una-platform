package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.pojo.system.SysRole;
import cn.kunli.una.service.system.SysPermissionService;
import cn.kunli.una.service.system.SysRoleService;
import cn.kunli.una.utils.common.MapUtil;
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
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController<SysRoleService, SysRole> {

    @Autowired
    private SysPermissionService sysPermissionService;


    //打开授权表单
    @RequestMapping("/authorization")
    public String authorization(Model model, SysRole obj) {
        SysDictionary permissionScopeDic = sysDictionaryService.getOne(sysDictionaryService.getWrapper(MapUtil.getMap("code", "permission_scope")));
        if(permissionScopeDic!=null){
            model.addAttribute("scopeList", sysDictionaryService.selectList(MapUtil.getMap("parentId", permissionScopeDic.getId())));
        }

        List<SysEntity> entityList = sysEntityService.list();
        for (SysEntity sysEntity : entityList) {
            sysEntity.setPermissionList(sysPermissionService.selectList(sysPermissionService.format(MapUtil.getMap("entityId",sysEntity.getId()))));
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
                        (SysRolePermission) new SysRolePermission().setScopeDcode(entry.getValue()).setId(entry.getKey()));
                if (!updateResult) return SysResult.fail();
            }
            return SysResult.success();
        }
        return SysResult.fail();
    }*/

}
