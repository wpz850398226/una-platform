package cn.kunli.una.service.activiti;

import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.pojo.vo.ActDefinition;
import cn.kunli.una.utils.activiti.DefinitionUtil;
import cn.kunli.una.utils.common.ListUtil;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * activiti流程定义管理
 */
@Service
public class ActDefinitionService {

    @Autowired
    private DefinitionUtil definitionUtil;

    /**
     * 查询最新版本流程定义
     *
     * @return
     */
    public SysResult latest() {
        List<ProcessDefinition> list = definitionUtil.findLastProcessDefinition();
        List<Map<String, Object>> mapList = ListUtil.entityListToMapList(list);

        return new SysResult().success(mapList);
    }

    /**
     * 查询流程定义流程节点
     *
     * @return
     */
    public SysResult flowElement(String id) {
        List<FlowElement> flowElementByDefinitionId = definitionUtil.getFlowElementByDefinitionId(id);
        return new SysResult().success(flowElementByDefinitionId);
    }


    /**
     * 分页条件查询流程定义
     *
     * @return
     */
    public SysResult table(ActDefinition obj) {
        List<ProcessDefinition> list = definitionUtil.getDefinitionBySelective(obj);
        return new SysResult().success(list);
    }


    /**
     * 保存/添加或修改
     *
     * @param obj
     * @return
     */
    public SysResult save(ActDefinition obj) {

        if (StringUtils.isNotBlank(obj.getFileId())) {
            //String fileUrl = sysFileMapper.selectByPrimaryKey(obj.getFileId()).getFileUrl();
            Deployment deployment = definitionUtil.deployFromDisk(obj.getName(), "/file/" + obj.getName());
            if (deployment != null) return new SysResult().success("部署成功", deployment);
        }

        return SysResult.fail("部署失败");

		/*if(obj.getDeployType()!=null&&obj.getDeployType()==1){
			//磁盘加载
			if(StringUtils.isNotBlank(obj.getFileId())) {
				//String fileUrl = sysFileMapper.selectByPrimaryKey(obj.getFileId()).getFileUrl();
				Deployment deployment = DefinitionUtil.deployFromDisk(obj.getName(),"/file/"+obj.getName());
				if(deployment!=null)return SysResult.success("部署成功",deployment);
			}
		}else{
			//类加载
			Deployment deployment = DefinitionUtil.deployFromClassPath(obj.getName());
			if(deployment!=null)return SysResult.success("部署成功",deployment);
		}*/

    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public SysResult delete(String[] ids) {
        for (String id : ids) {
            definitionUtil.deleteProcessDefinition(id);
        }
        return SysResult.success();
    }


}
