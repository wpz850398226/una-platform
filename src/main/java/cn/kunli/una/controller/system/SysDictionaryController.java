package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.service.system.SysDictionaryService;
import cn.kunli.una.utils.common.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author Ponzio
 * @version 2019年6月5日 下午5:25:14
 * 字典管理
 */
@Slf4j
@Controller
@RequestMapping("/sys/dictionary")
public class SysDictionaryController extends BaseController<SysDictionaryService, SysDictionary> {

    /**
     * ajax查询多条
     * @return
     */
    @Override
    @GetMapping("/list")
    @ResponseBody
    public List<SysDictionary> list(@RequestParam Map<String, Object> map) {
        if(MapUtils.isNotEmpty(map)){
            if(map.get("parentCode")!=null){
                String parentCode = map.get("parentCode").toString();
                map.remove("parentCode");
                SysDictionary parentDictionary = service.selectOne(MapUtil.getMap("code", parentCode));
                if(parentDictionary!=null)map.put("parentId",parentDictionary.getId());
            }
        }
        return super.list(map);
    }

}
