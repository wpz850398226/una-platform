package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.service.system.SysEntityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author Ponzio
 * @version 2019年6月5日 下午5:25:30
 * 实体管理
 */
@Controller
@RequestMapping("/sys/entity")
public class SysEntityController extends BaseController<SysEntityService, SysEntity> {

    @Override
    public List<SysEntity> queryPlural(Map<String, Object> params) {
        List<SysEntity> entityList = super.queryPlural(params);
        if(entityList!=null){
            for (SysEntity sysEntity : entityList) {
                sysEntity.setName(sysEntity.getName()+"-"+sysEntity.getMap().get("platformDname"));
            }
        }
        return entityList;
    }
}
