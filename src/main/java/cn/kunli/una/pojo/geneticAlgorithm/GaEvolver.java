package cn.kunli.una.pojo.geneticAlgorithm;

import cn.kunli.una.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 遗传算法-进化者/最优解
 * </p>
 *
 * @author Ponzio
 * @since 2022-01-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GaEvolver extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 所属实体
     */
    private Integer entityId;

    /**
     * 筛选代数
     */
    private Integer generationNumber;

    /**
     * 得分阈值
     */
    private Float scoreThreshold;

    /**
     * 得分
     */
    private Float score;

    /**
     * 基因序列，有序
     */
    private String representationIds;

    /**
     * 初始群体，无序
     */
    private String initializerIds;

    /**
     * 耗时秒数
     */
    private Integer elapsedSeconds;

    /**
     * 描述
     */
    private String description;

}
