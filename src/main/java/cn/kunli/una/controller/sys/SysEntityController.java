package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysEntity;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.sys.SysEntityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/list")
    public List<SysEntity> list(@RequestParam Map<String, Object> map) {
        List<SysEntity> entityList = super.list(map);
        if(entityList!=null){
            for (SysEntity sysEntity : entityList) {
                sysEntity.setName(sysEntity.getName()+"-"+sysEntity.getMap().get("platformDname"));
            }
        }
        return entityList;
    }

    @ResponseBody
    @PostMapping("/tableGenerate/{id}")
    public SysResult tableGenerate(@PathVariable Integer id) {
        return service.tableGenerate(id);
    }

    @ResponseBody
    @PostMapping("/codeGenerate/{id}")
    public SysResult codeGenerate(@PathVariable Integer id) {
        return service.codeGenerate(id);
    }
}
