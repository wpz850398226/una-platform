package cn.kunli.una.service.activiti;

import cn.kunli.una.pojo.vo.SysPageModel;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.pojo.vo.ActInstance;
import cn.kunli.una.pojo.vo.ActTask;
import cn.kunli.una.utils.activiti.InstanceUtil;
import cn.kunli.una.utils.activiti.TaskUtil;
import cn.kunli.una.utils.common.ListUtil;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * activiti流程任务管理
 */
@Service
public class ActTaskService {

	@Autowired
	private TaskUtil taskUtil;
	@Autowired
	private InstanceUtil instanceUtil;

	/**
	 * 办理任务
	 * @param map 流程变量,内中保存任务id
	 * @return
	 */
	public SysResult handle(Map<String,Object> map) {
		String id = (String) map.get("id");
		map.remove("id");
		return taskUtil.handleTask(id,map);
	}

	/**
	 * 分页条件查询待办
	 * @return
	 */
	public SysResult getTaskBySelective(ActTask obj) {
		Long total = taskUtil.getTaskCountBySelective(obj);
		List<Task> list = taskUtil.selectTaskBySelective(obj);
		if(ListUtil.isNotNull(list)){
			List<Map<String, Object>> maps = ListUtil.entityListToMapList(list);
			for (int i = 0; i < list.size(); i++) {
				List<Comment> commentByTaskId = taskUtil.getCommentByTaskId(list.get(i).getId());
				maps.get(i).put("taskId",list.get(i).getId());
				maps.get(i).put("comments",commentByTaskId);
				maps.get(i).put("processType",taskUtil.getVariableByTaskIdAndName(list.get(i).getId(),"processType"));
			}

			SysPageModel sysPageModel = new SysPageModel(maps, obj.getPageSize(), obj.getPageNum(), total.intValue());
			return new SysResult().success(sysPageModel);
		}else {
			return SysResult.success("查询无记录");
		}
	}

	/**
	 * 条件查询待办数量
	 * @return
	 */
	public SysResult getTaskCountBySelective(ActTask obj) {
		Long count = taskUtil.getTaskCountBySelective(obj);
		return new SysResult().success(count);
	}

	/**
	 * id查询待办
	 * @return
	 */
	public SysResult getTaskById(String id) {
		Task task = taskUtil.getTaskById(id);
		if(task==null)return SysResult.fail("待办不存在，可能已被其他用户处理");
		Map<String, Object> map = ListUtil.entityToMap(task);
		map.put("taskId",task.getId());
		return new SysResult().success(map);
	}


	/**
	 * 分页条件查询已办
	 * @return
	 */
	public SysResult getHiTaskBySelective(ActTask obj) {
		Long total = taskUtil.getHiTaskCountBySelective(obj);
		List<HistoricTaskInstance> list = taskUtil.selectHiTaskBySelective(obj);
		if(ListUtil.isNotNull(list)){
			List<Map<String, Object>> maps = ListUtil.entityListToMapList(list);

			for (int i = 0; i < list.size(); i++) {
				List<Comment> commentByTaskId = taskUtil.getCommentByTaskId(list.get(i).getId());
				String instanceId = list.get(i).getProcessInstanceId();
				maps.get(i).put("taskId",list.get(i).getId());
				maps.get(i).put("userId",list.get(i).getAssignee());
				maps.get(i).put("processInstanceId",instanceId);
				maps.get(i).put("processDefinitionId",list.get(i).getProcessDefinitionId());
				maps.get(i).put("comments",commentByTaskId);
				ProcessInstance instanceByInstanceId = instanceUtil.getInstanceByInstanceId(instanceId);
				if(instanceByInstanceId!=null){
					//流程未结束
					Map<String, Object> variablesByInstanceId = instanceUtil.getVariablesByInstanceId(instanceId);
					maps.get(i).put("processType",variablesByInstanceId.get("processType"));
				}else{
					//流程已结束
					List<HistoricVariableInstance> HistoricVariables = instanceUtil.getHiVariablesByInstanceId(new ActInstance().setId(instanceId).setVariableName("processType"));
					if(ListUtil.isNotNull(HistoricVariables)){
						maps.get(i).put("processType",HistoricVariables.get(0).getValue());
					}
				}

			}

			SysPageModel sysPageModel = new SysPageModel(maps, obj.getPageSize(), obj.getPageNum(), total.intValue());
			return new SysResult().success(sysPageModel);
		}else {
			return SysResult.success("查询无记录");
		}
	}


	/**
	 * 条件查询已办数量
	 * @return
	 */
	public SysResult getHiTaskCountBySelective(ActTask obj) {
		Long count = taskUtil.getHiTaskCountBySelective(obj);
		return new SysResult().success(count);
	}


	/**
	 * id查询已办
	 * @return
	 */
	public SysResult getHiTaskById(String id) {
		HistoricTaskInstance task = taskUtil.getHiTaskById(id);
		if(task==null)return SysResult.fail("已办任务不存在");
		Map<String, Object> map = ListUtil.entityToMap(task);
		String instanceId = task.getProcessInstanceId().toString();
		List<Comment> commentByTaskId = taskUtil.getCommentByTaskId(id);

		map.put("taskId",task.getId());
		map.put("processInstanceId",instanceId);
		map.put("comments",commentByTaskId);
		map.put("processDefinitionId",task.getProcessDefinitionId());

		ProcessInstance instanceByInstanceId = instanceUtil.getInstanceByInstanceId(instanceId);
		if(instanceByInstanceId!=null){
			//流程未结束
			Map<String, Object> variablesByInstanceId = instanceUtil.getVariablesByInstanceId(instanceId);
			map.put("processType",variablesByInstanceId.get("processType"));
		}else{
			//流程已结束
			List<HistoricVariableInstance> HistoricVariables = instanceUtil.getHiVariablesByInstanceId(new ActInstance().setId(instanceId).setVariableName("processType"));
			if(ListUtil.isNotNull(HistoricVariables)){
				map.put("processType",HistoricVariables.get(0).getValue());
			}
		}

		return new SysResult().success(map);
	}

	/**
	 * 拾取组任务
	 * @param obj 流程任务工具类
	 * @return
	 */
	public SysResult claim(ActTask obj) {
		return taskUtil.claim(obj);
	}

	/**
	 * 查询流程变量
	 * @return
	 */
	public SysResult getVariablesByInstanceId(String id, String name) {
		Object variableByTaskIdAndName = taskUtil.getVariableByTaskIdAndName(id, name);
		return new SysResult().success(variableByTaskIdAndName);
	}


	/**
	 * 节点跳转
	 * id  任务id
	 * activityId  目标节点id
	 * comments  批注（逗号分隔）
	 * @return
	 */
	public SysResult turnTransition(ActTask obj) {
		if(obj.getId()==null)return SysResult.fail("任务id为空");
		if(StringUtils.isBlank(obj.getUserId()))return SysResult.fail("办理人id为空");
		if(StringUtils.isBlank(obj.getActivityId()))return SysResult.fail("目标节点id为空");

		//查询待办任务
		Task task = taskUtil.getTaskById(obj.getId());

		//跳转节点
		SysResult sysResult = taskUtil.turnTransition(obj);

		if(obj.getActivityId().equals("end")){
			//如果是跳转的结束节点，判断跳转之后流程实例是否结束
			ProcessInstance instanceByInstanceId = instanceUtil.getInstanceByInstanceId(task.getProcessInstanceId());
			if(instanceByInstanceId!=null){
				//如果跳转结束节点后，流程实例没有结束，则强制结束
				//List<Task> taskList = taskUtil.selectTaskBySelective(new ActTask().setInstanceId(task.getProcessInstanceId()));
				instanceUtil.deleteInstanceById(task.getProcessInstanceId());
			}
		}

		return sysResult;
	}


}
