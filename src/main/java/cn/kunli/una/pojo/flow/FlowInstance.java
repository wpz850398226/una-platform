package cn.kunli.una.pojo.flow;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 流程实例(FlowInstance)实体类
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:48
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class FlowInstance extends BasePojo implements Serializable {
    private static final long serialVersionUID = 879519392584170197L;
    //定义id
    private String definitionId;
    //是否进行中，0否，1是
    private Boolean isRunning;
}
