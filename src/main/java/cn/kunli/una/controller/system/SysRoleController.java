package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysRole;
import cn.kunli.una.pojo.system.SysRolePermission;
import cn.kunli.una.pojo.vo.SysParamMap;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.system.SysDictionaryService;
import cn.kunli.una.service.system.SysEntityService;
import cn.kunli.una.service.system.SysRolePermissionService;
import cn.kunli.una.service.system.SysRoleService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

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
    private SysDictionaryService sysDictionaryService;
    @Autowired
    private RedisUtil redisUtil;


    //打开授权表单
    @RequestMapping("/authorization")
    public String authorization(Model model, SysRole obj) {
        model.addAttribute("scopeList", sysDictionaryService.list(
                sysDictionaryService.getWrapper(MapUtil.getMap("parentCode", "permission_scope"))));
        model.addAttribute("entityList", sysEntityService.list());
        model.addAttribute("sample", obj);
        return "system/role/authorization";
    }

    //授权
    @RequestMapping("/saveAuthorization")
    @ResponseBody
    public SysResult saveAuthorization(Model model, SysRole obj) {
        if (obj.getMap() != null) {
            for (Map.Entry<Integer, Integer> entry : obj.getPermMap().entrySet()) {
                boolean updateResult = sysRolePermissionService.updateById(
                        (SysRolePermission) new SysRolePermission().setScope(entry.getValue()).setId(entry.getKey()));
                if (!updateResult) return SysResult.fail();
            }
            return SysResult.success();
        }
        return SysResult.fail();
    }

}
