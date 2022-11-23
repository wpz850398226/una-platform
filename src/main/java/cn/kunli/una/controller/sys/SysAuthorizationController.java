package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysAuthorization;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.sys.SysAuthorizationService;
import io.swagger.annotations.Api;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * (SysAuthorization)表控制层
 *
 * @author Ponzio
 * @since 2020-05-16 15:32:52
 */
@Controller
@Api(tags = "系统-授权")
@RequestMapping("/sys/rolePermission")
public class SysAuthorizationController extends BaseController<SysAuthorizationService, SysAuthorization> {

    @PutMapping("/batch")
    @ResponseBody
    public SysResult update(@RequestBody List<SysAuthorization> list) {
        if(CollectionUtils.isNotEmpty(list)){
            for (SysAuthorization sysAuthorization : list) {
                if(sysAuthorization.getId()!=null){
                    SysResult sysResult = service.updateRecordById(sysAuthorization);
                    if(!sysResult.getIsSuccess())return sysResult;
                }
            }
        }
        return SysResult.success();
    }

    @PutMapping("/batchUpdate")
    @ResponseBody
    public SysResult batchUpdate(SysAuthorization obj) {
        if(MapUtils.isNotEmpty(obj.getMap())){
            for (Map.Entry<String, Object> entry : obj.getMap().entrySet()) {
                String id = entry.getKey();
                String value = entry.getValue().toString();
                String code = "";
                switch(value){
                    case "2":
                        code = "permission_scope_10";
                        break;
                    case "3":
                        code = "permission_scope_50";
                        break;
                    case "4":
                        code = "permission_scope_100";
                        break;
                    default:
                        code = "permission_scope_"+value;
                        break;
                }
                service.updateRecordById((SysAuthorization) new SysAuthorization().setScopeDcode(code).setId(Integer.valueOf(id)));
            }
        }
        return SysResult.success();
    }
}
