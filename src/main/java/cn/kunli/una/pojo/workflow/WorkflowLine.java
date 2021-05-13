package cn.kunli.una.pojo.workflow;

import java.util.Date;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.Accessors;

/**
 * 流程连线(WorkflowLine)实体类
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:50
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WorkflowLine extends BasePojo implements Serializable {
    private static final long serialVersionUID = -56496290891072647L;
    //主键id
    private Integer id;
    //名称（无用）
    private String name;
    //条件
    private String condition;
    //来源节点id
    private Integer sourceNodeId;
    //目标节点id
    private Integer targetNodeId;
    //是否默认，0否，1是
    private Integer isDefault;
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
