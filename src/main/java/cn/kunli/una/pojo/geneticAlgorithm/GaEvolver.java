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
     * 迭代阈值
     */
    private Integer iterationThreshold;

    /**
     * 得分阈值
     */
    private Float scoreThreshold;

    /**
     * 最高分
     */
    private Float topScore;

    /**
     * 迭代次数
     */
    private Integer iterationCount;

    /**
     * 得分
     */
    private Float score;

    /**
     * 入参基因序列，有序
     */
    private Integer paramGeneId;

    /**
     * 计算基因序列，有序
     */
    private String calculateGeneIds;

    /**
     * 初始群体数量
     */
    private Integer initializerCount;

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
