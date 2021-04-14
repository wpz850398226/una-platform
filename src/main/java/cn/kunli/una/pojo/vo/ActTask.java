package cn.kunli.una.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class ActTask{

    //待办id
    private String id;

    private String name;    //任务名称，流程节点名称

    private String nameLike;    //任务名称，流程节点模糊名称

    private String instanceId;   //流程实例id

    private String userId;   //办理人id

    private String candidateUserId;   //组任务的办理人id

    private String definitionKey;   //流程定义值

    private String taskDefinitionKey;   //节点定义值

    private String comments;   //任务批注

    private String activityId;   //节点id

    private int pageNum;			// 当前页数,从客户端传递到服务端

    private int pageSize;  		//每页显示的条数,固定的

    private int type;       //待办0，已办1

    private List<String> candidateGroupList;

    private List<String> definitionKeyList;

    private Map<String,Object> param;


    public ActTask() {
        this.pageNum = 1;
        this.pageSize = 10;
    }

}
