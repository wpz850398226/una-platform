/*
package cn.kunli.una.controller.activiti;

import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.pojo.vo.ActDefinition;

import cn.kunli.una.service.activiti.ActDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

*/
/**
 * activiti流程定义管理
 * @author Ponzio
 * 时间：2020年3月4日07:24:07
 *//*

@Controller
@RequestMapping("/act/definition")
public class ActDefinitionController {
	@Autowired
	private ActDefinitionService objService;


	*/
/**
	 * 查询最新版本流程定义
	 * @return
	 *//*

	@RequestMapping("/latest")
	@ResponseBody
	public SysResult latest() {
		return objService.latest();
	}


	*/
/**
	 * 分页条件查询流程定义
	 * @return
	 *//*

	*/
/*@RequestMapping("/table")
	@ResponseBody
	public LayTable table(ActDefinition obj) {
		LayTable table = objService.table(obj);
		return table;
	}*//*



	*/
/**
	 * 保存/添加或修改
	 * @param obj
	 * @return
	 *//*

	@RequestMapping("/save")
	@ResponseBody
	public SysResult save(ActDefinition obj) {
		return objService.save(obj);
	}

	*/
/**
	 * 删除
	 * @param ids
	 * @return
	 *//*

	@RequestMapping("delete")
	@ResponseBody
	public SysResult delete(String[] ids) {
		return objService.delete(ids);
	}


}
*/
