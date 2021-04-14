package cn.kunli.una.controller.system;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.pojo.system.SysField;
import cn.kunli.una.pojo.vo.SysResponseParameter;
import cn.kunli.una.service.system.SysDictionaryService;
import cn.kunli.una.service.system.SysEntityService;
import cn.kunli.una.service.system.SysFieldService;
import cn.kunli.una.utils.common.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author Ponzio
 * @version 2019年6月5日 下午5:25:30
 * 菜单管理
 */
@Controller
@RequestMapping("/sys/field")
public class SysFieldController extends BaseController<SysFieldService, SysField> {

    @Autowired
    private SysFieldService objService;
    @Autowired
    private SysEntityService sysEntityService;
    @Autowired
    private SysDictionaryService sysDictionaryService;

    /**
     * 跳转表单页
     *
     * @param model
     * @param params
     * @return
     */
    //@PreAuthorize("hasAnyAuthority('SysField:insert','SysField:update')")
    @RequestMapping("form")
    public String form(Model model, @RequestParam Map<String, Object> params) throws IllegalAccessException {
        if (params.get("id") != null) {
            SysField sysField = objService.selectByPrimaryKey(params.get("id"));
            if (sysField != null) params = JSONUtil.toMapWithParent(sysField);
        } else {
            //新增时设置默认值
//			sysField = new SysField().setDisplayModeDcode("field_display_text").setAssignmentModeDcode("field_manual_assign_text");
            params.put("displayModeDcode", "field_display_text");
            params.put("assignmentModeDcode", "field_manual_assign_text");
        }
        model.addAttribute("record", params);
        SysEntity entityClass = sysEntityService.selectOne(new SysEntity().setCode("SysField"));
        model.addAttribute("sysResponseParameter", new SysResponseParameter().setSysEntity(entityClass));

        return "system/field/form";
    }

    /*获取展示值*/
	/*@RequestMapping("/getDisplayValue")
	@ResponseBody
	public SysResult getDisplayValue(@RequestParam Map<String,String> map) {

		Map<String,Map<String,Object>> resultMap = new HashMap();
		for (Map.Entry<String, String> integerMapEntry : map.entrySet()) {
			String value = integerMapEntry.getValue();
			Map<String,String> codeValueMap = JSONUtil.stringToBean(value, Map.class);
			Map<String, Object> codeValueResult = new HashMap<>();
			for (Map.Entry<String, String> entry : codeValueMap.entrySet()) {
				SysResult displayValue = baseService.getDisplayValue(entry.getKey(), String.valueOf(entry.getValue()),null);
				if(displayValue.getCode()==200){
					codeValueResult.put(entry.getKey(),displayValue.getData());
					resultMap.put(integerMapEntry.getKey(),codeValueResult);
				}
			}
		}
		return new SysResult().success(resultMap);
	}*/

}
