package cn.kunli.una.pojo.workflow;

import java.util.Date;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.Accessors;

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
    //主键id
    private Integer id;
    //名称
    private String name;
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
    //备注
    private String remark;
    //创建人id
    private Integer creatorId;
    //创建时间
    private Date createTime;
    //修改人id
    private Integer modifierId;
    //修改时间
    private Date modifyTime;
    //是否删除
    private Integer isDelete;
    //创建人名称
    private String creatorName;
    //创建人IP
    private String creatorHost;
    //修订人名称
    private String modifierName;
    //修订人IP
    private String modifierHost;
    //租户id
    private Integer tentId;
    //顺序
    private Integer sequence;

}
