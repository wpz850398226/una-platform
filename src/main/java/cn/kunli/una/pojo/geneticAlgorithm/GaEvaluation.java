package cn.kunli.una.pojo.geneticAlgorithm;

import cn.kunli.una.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 遗传算法-适应度/评价函数
 * </p>
 *
 * @author Ponzio
 * @since 2022-01-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GaEvaluation extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 所属实体
     */
    private Integer entityId;

    /**
     * 评价规则
     */
    private String rule;

    /**
     * 评价得分
     */
    private Integer score;

    /**
     * 描述
     */
    private String description;

}
