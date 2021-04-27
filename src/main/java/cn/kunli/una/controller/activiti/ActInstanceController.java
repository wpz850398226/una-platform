/*
package cn.kunli.una.controller.activiti;

import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.pojo.vo.ActInstance;
import cn.kunli.una.service.activiti.ActInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

*/
/**
 * activiti流程实例管理
 * @author Ponzio
 * 时间：2020年3月4日07:24:07
 *//*

@Controller
@RequestMapping("/act/instance")
public class ActInstanceController{

	@Autowired
	private ActInstanceService objService;



	*/
/**
	 * 启动工作流程
	 * @param definitionKey
	 * @param map
	 * @return
	 *//*

	@RequestMapping("save")
	@ResponseBody
	public SysResult save(String definitionKey, @RequestParam Map<String,Object> map) {
		return objService.save(definitionKey,map);
	}

	*/
/**
	 * 终止流程
	 * @param key
	 * @return
	 *//*

	@RequestMapping("stop")
	@ResponseBody
	public SysResult save(String key) {
		return objService.stop(key);
	}


	*/
/**
	 * 分页条件查询流程实例
	 * @return
	 *//*

	@RequestMapping("/instanceTable")
	@ResponseBody
	public SysResult table(ActInstance obj) {
		return objService.table(obj);
	}


	*/
/**
	 * 查询流程变量
	 * @return
	 *//*

	@RequestMapping("/getVariablesByInstanceId")
	@ResponseBody
	public SysResult getVariablesByInstanceId(String id) {
		return objService.getVariablesByInstanceId(id);
	}


	*/
/**
	 * 查询历史活动节点
	 * @return
	 *//*

	@RequestMapping("/getHistoricActivityByInstanceId")
	@ResponseBody
	public SysResult getHistoricActivityByInstanceId(String id) {
		return objService.getHistoricActivityByInstanceId(id);
	}

	*/
/**
	 * 查询单个流程实例
	 * @return
	 *//*

	@RequestMapping("/getInstanceByInstanceId")
	@ResponseBody
	public SysResult getInstanceByInstanceId(String id) {
		return objService.getInstanceByInstanceId(id);
	}



}
*/
