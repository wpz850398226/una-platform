package cn.kunli.una.pojo.flow;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 流程连线(FlowLine)实体类
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:50
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class FlowLine extends BasePojo implements Serializable {
    private static final long serialVersionUID = -56496290891072647L;
    //条件
    private String flowCondition;
    //来源节点id
    private Integer originNodeId;
    //目标节点id
    private Integer targetNodeId;
    //是否默认，0否，1是
    private Integer isDefault;
    //定义id
    private Integer definitionId;

}
