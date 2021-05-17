package cn.kunli.una.pojo.flow;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 流程定义(FlowDefinition)实体类
 *
 * @author Ponzio
 * @since 2021-05-12 22:03:55
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class FlowDefinition extends BasePojo implements Serializable {
    private static final long serialVersionUID = -89055343881786180L;
    //实体id
    private Integer entityId;
    //流程定义编码
    private String code;
    //版本号
    private Integer version;
}
