package cn.kunli.una.pojo.flow;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 流程节点(FlowNode)实体类
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:51
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class FlowNode extends BasePojo implements Serializable {
    private static final long serialVersionUID = 931384685256685913L;
    //类型字典编码
    private String typeDcode;
    //是否入口排他
    private Boolean isEntryExclusive;
    //是否出口排他
    private Boolean isExitExclusive;
    //定义id
    private Integer definitionId;
    //候选人ids
    private String candidateValue;
    //候选人类型
    private String candidateTypeDcode;
    //实体/表单id
    private Integer entityId;


    @TableField(exist = false)
    private String candidateRoleIds;
    @TableField(exist = false)
    private String candidateAccountIds;
}
