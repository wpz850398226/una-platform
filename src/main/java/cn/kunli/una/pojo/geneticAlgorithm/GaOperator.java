package cn.kunli.una.pojo.geneticAlgorithm;

import cn.kunli.una.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 遗传算法-算子/选交变
 * </p>
 *
 * @author Ponzio
 * @since 2022-01-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GaOperator extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 所属实体
     */
    private Integer entityId;

    /**
     * 类型字典编码
     */
    private String typeDcode;

    /**
     * 计算规则
     */
    private String rule;

    /**
     * 描述
     */
    private String description;

}
