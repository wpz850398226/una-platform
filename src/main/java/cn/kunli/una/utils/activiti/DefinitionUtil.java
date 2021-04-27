/*
package cn.kunli.una.utils.activiti;

import cn.kunli.una.pojo.vo.ActDefinition;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

*/
/**
 * @author Ponzio
 * @version 2020年2月26日09:06:45
 * 流程引擎工具类
 *//*

@Slf4j
@Component
public class DefinitionUtil {

    */
/**
     * 会默认按照Resources目录下的activiti.cfg.xml创建流程引擎
     *//*

    //private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    @Autowired
    private RepositoryService repositoryService;


//  List<task> list = processEngine.getTaskService()//与正在执行的任务管理相关的Service
    //    .createTaskQuery()//创建任务查询对象
    */
/**查询条件（where部分）*//*

    //    .taskAssignee(assignee)//指定个人任务查询，指定办理人
    //  .taskCandidateUser(candidateUser)//User组任务的办理人查询
    //  .taskCandidateGroup(candidateGroup)//Group组任务的办理人查询
    //  .processDefinitionId(processDefinitionId)//使用流程定义ID查询
    //  .processInstanceId(processInstanceId)//使用流程实例ID查询
    //  .executionId(executionId)//使用执行对象ID查询
    */
/**排序*//*

    //    .orderByTaskCreateTime().asc()//使用创建时间的升序排列
    */
/**返回结果集*//*

    //  .singleResult()//返回惟一结果集
    //  .count()//返回结果集的数量
    //  .listPage(firstResult, maxResults);//分页查询
    //    .list();//返回列表


    */
/**
     * 查询流程定义
     *//*

    public void findProcessDefinition() {
        repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().asc().list();
    }

    */
/**
     * 查询最新版本的流程定义
     *//*

    public List<ProcessDefinition> findLastProcessDefinition() {
        //map集合特点，相同key值，后面的会覆盖前面存入的值
        Map<String, ProcessDefinition> map = new LinkedHashMap<>();

        //根据版本号升序排列
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().asc().list();

        if(list!=null&&list.size()>0){
            for(ProcessDefinition pd:list){
                map.put(pd.getKey(),pd);
            }
        }

        List<ProcessDefinition> listLast = new ArrayList(map.values());

        return listLast;
    }

    */
/**
     * 删除流程定义
     *//*

    public void deleteProcessDefinition(String deploymentId) {
        //使用部署id删除流程定义
        //不带级联删除，只能删除没有启动的流程，如果流程启动，就会抛出异常
        //repositoryService.deleteDeployment(deploymentId);

        //带级联删除
        repositoryService.deleteDeployment(deploymentId,true);
    }



    */
/**
     *  部署流程定义（读取本地磁盘文件部署）
     *  @param 工作流文件地址
     *  
     *//*

    public Deployment deployFromDisk(String name, String fileUrl) {
        log.info("【部署流程】文件地址={}", fileUrl);
        String basicPath = "";
        */
/*String dictionaryName = "windows文件根目录";
        if(SystemUtil.isOSLinux())dictionaryName = "linux文件根目录";
        SysConfiguration configuration = sysConfigurationMapper.selectOne((SysConfiguration)new SysConfiguration().setName(dictionaryName));
        if(configuration!=null){
            basicPath = configuration.getValue();
        }else{*//*

        basicPath = "D:\\una\\fileUpload";
        //}

        System.out.println("********************************basicPath="+basicPath+"*************************************");
        try {
            File file = new File(basicPath+fileUrl.replace("/file","").replace("/","\\"));
            Deployment deployment =  repositoryService
                    .createDeployment().name(fileUrl)
                    .addInputStream(fileUrl,new FileInputStream(file)).name(name).deploy();
            return deployment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    */
/**
     *  * 部署流程定义（读取项目路径文件部署）
     *  * @param 工作流地址
     *  * @Author :
     *  
     *//*

    public Deployment deployFromClassPath(String fileName) {
        log.info("【部署流程】fileName={}", fileName);
        Deployment deployment = repositoryService.createDeployment().name(fileName).addClasspathResource("process/" + fileName).deploy();
        return deployment;
    }

    */
/**
     * 分页条件查询流程定义
     * @param obj 流程定义
     * @Author :
     *  
     *//*

    public List<ProcessDefinition> getDefinitionBySelective(ActDefinition obj) {
        try {
            ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
            if(obj!=null){
                if(obj.getId()!=null)query.processDefinitionId(obj.getId());
                if(StringUtils.isNotBlank(obj.getName()))query.processDefinitionNameLike(obj.getName());
                if(StringUtils.isNotBlank(obj.getKey()))query.processDefinitionKeyLike(obj.getKey());
            }
            List<ProcessDefinition> list = query.listPage(obj.getPageNum() - 1, obj.getPageSize());
            return list;
        } catch (Exception e) {
            return null;
        }

    }


    */
/**
     * 通过流程定义id查询流程定义文件
     * @param processDefinition 流程定义
     * @Author :
     *  
     *//*

    public InputStream getResourceAsStream(ProcessDefinition processDefinition) {
        try {
            InputStream bpmnIs = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getDiagramResourceName());

            return bpmnIs;
        } catch (Exception e) {
            return null;
        }

    }


    */
/**
     * 查询流程定义的所有节点
     *//*

    public List<FlowElement> getFlowElementByDefinitionId(String processDefinitionId) {
        BpmnModel model = repositoryService.getBpmnModel(processDefinitionId);
        if(model != null) {
            List<FlowElement> flowElementList = new ArrayList<>();
            Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
            for(FlowElement e : flowElements) {
                if(e instanceof UserTask)flowElementList.add(e);
            }
            return flowElementList;
        }
        return null;
    }

}
*/
