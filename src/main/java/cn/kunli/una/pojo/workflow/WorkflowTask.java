package cn.kunli.una.pojo.workflow;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 流程任务(WorkflowTask)实体类
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:52
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WorkflowTask extends BasePojo implements Serializable {
    private static final long serialVersionUID = -67535831425503802L;
    //实例id
    private Integer instanceId;
    //办理人id
    private Integer assigneeId;
    //是否完成，0否，1是
    private Integer isFinish;
    //是否审批通过，0否，1是
    private Integer isApproved;
    //意见
    private String opinion;
    //节点id
    private Integer nodeId;
    //候选人ids
    private String candidateIds;
}
