package cn.kunli.una.pojo.workflow;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 流程定义(WorkflowDefinition)实体类
 *
 * @author Ponzio
 * @since 2021-05-12 22:03:55
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WorkflowDefinition extends BasePojo implements Serializable {
    private static final long serialVersionUID = -89055343881786180L;
    //实体id
    private Integer entityId;
    //流程定义编码
    private String code;
    //版本号
    private Integer version;
}
