package cn.kunli.una.pojo.workflow;

import java.util.Date;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.Accessors;

/**
 * 流程节点(WorkflowNode)实体类
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:51
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WorkflowNode extends BasePojo implements Serializable {
    private static final long serialVersionUID = 931384685256685913L;
    //主键id
    private Integer id;
    //名称
    private String name;
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
    private Integer sortOrder;

}
