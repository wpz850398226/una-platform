package cn.kunli.una.pojo.workflow;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 流程节点(WorkflowNode)实体类
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:51
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WorkflowNode extends BasePojo implements Serializable {
    private static final long serialVersionUID = 931384685256685913L;
    //类型字典编码
    private String typeDcode;
    //定义id
    private Integer definitionId;
    //候选人ids
    private String candidateIds;
    //候选人类型
    private String candidateType;
    //候选人范围
    private String candidateScope;
}
