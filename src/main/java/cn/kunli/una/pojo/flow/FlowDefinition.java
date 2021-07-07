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
    //流程定义编码
    private String code;
    //版本号
    private Integer version;



    //启动次数
    @TableField(exist = false)
    private Integer total;
    //进行中的流程数量
    @TableField(exist = false)
    private Integer running;
}
