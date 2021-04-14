package cn.kunli.una.controller.system;

import cn.kunli.una.annotation.LogAnnotation;
import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysData;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.system.SysDataService;
import cn.kunli.una.utils.common.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
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
    @LogAnnotation
    @RequestMapping("/savePrivate")
    @ResponseBody
    public SysResult save(@Valid SysData record, @RequestParam Map<String, Object> params) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(params);
        return super.save(record.setValue(jsonObject));
    }

    /**
     * 跳转通用管理页
     *
     * @param
     * @return
     */
    @RequestMapping("/querySinglePrivate")
    @ResponseBody
    public Map querySingle(Integer id) {
        if (id==null) return null;
        SysData sysData = baseService.selectByPrimaryKey(id);
        if(sysData==null)return null;
        JSONObject value = sysData.getValue();
        Map map = JSONUtil.toMap(sysData);
        map.putAll(value);
        return map;
    }
}
