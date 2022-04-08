package cn.kunli.una.controller.sys;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.sys.SysData;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.sys.SysDataService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 数据/表单内容/问卷答案(SysData)表控制层
 *
 * @author Ponzio
 * @since 2021-04-02 16:14:24
 */
@Controller
@RequestMapping("/sys/data")
public class SysDataController extends BaseController<SysDataService, SysData> {

    /**
     * 保存/添加或修改
     *
     * @param params
     * @return
     */
    @RequestMapping("/virtual")
    @ResponseBody
    public SysResult saveData(@RequestBody Map<String, Object> params) {
        SysData record = new SysData();
        if(params.containsKey("entityId")){
            record.setEntityId(Integer.valueOf(params.get("entityId").toString()));
        }
        if(params.containsKey("name")){
            record.setName(params.get("name").toString());
        }
        JSONObject jsonObject = new JSONObject();
        params.remove("id");
        params.remove("entityId");
        jsonObject.putAll(params);
        return super.save(record.setValue(jsonObject));
    }

    @PutMapping("/virtual")
    @ResponseBody
    public SysResult update(@RequestBody Map<String, Object> params) {
        SysData record = new SysData();
        if(params.containsKey("id")) {
            record.setId(Integer.valueOf(params.get("id").toString()));
            params.remove("id");
            JSONObject jsonObject = new JSONObject();
            jsonObject.putAll(params);
            record.setValue(jsonObject);
            //如果id不为空，说明是修改数据
            return service.updateRecordById(record);
        }
        return SysResult.fail("修改失败，id为空");
    }

}
