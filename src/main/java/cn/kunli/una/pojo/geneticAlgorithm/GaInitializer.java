package cn.kunli.una.pojo.geneticAlgorithm;

import cn.kunli.una.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 遗传算法-初始群体/祖先
 * </p>
 *
 * @author Ponzio
 * @since 2022-01-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GaInitializer extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 所属实体
     */
    private Integer entityId;

    /**
     * 得分
     */
    private Float score;

    /**
     * 基因序列
     */
    private String representationIds;

    /**
     * 描述
     */
    private String description;

}
