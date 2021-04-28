package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysData;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.system.SysDataService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
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
    @RequestMapping("/saveData")
    @ResponseBody
    public SysResult saveData(@Valid SysData record, @RequestParam Map<String, Object> params) {
        JSONObject jsonObject = new JSONObject();
        params.remove("id");
        params.remove("entityId");
        jsonObject.putAll(params);
        return super.save(record.setValue(jsonObject));
    }

}
