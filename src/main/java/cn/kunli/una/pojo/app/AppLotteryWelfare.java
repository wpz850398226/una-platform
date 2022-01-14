package cn.kunli.una.pojo.app;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <p>
 * 福利彩票记录
 * </p>
 *
 * @author Ponzio
 * @since 2022-01-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AppLotteryWelfare extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * 号码
     */
    private Integer number;

    /**
     * 百位数字
     */
    private Integer hundred;

    /**
     * 十位数字
     */
    private Integer decade;

    /**
     * 个位数字
     */
    private Integer single;

    /**
     * 和
     */
    private Integer sum;

    /**
     * 平均值
     */
    private Float average;

    /**
     * 方差
     */
    private Double variance;

    /**
     * 标准差
     */
    private Double standardDeviation;

    /**
     * 乘积
     */
    private Integer product;

    /**
     * 间隔天数
     */
    private Integer intervalDays;

    /**
     * 历史次数
     */
    private Integer historyCount;

    /**
     * 减上期百位差值
     */
    private Integer lastHundredDef;

    /**
     * 减上期十位差值
     */
    private Integer lastDecadeDef;

    /**
     * 减上期个位差值
     */
    private Integer lastSingleDef;

    /**
     * 百位差值是否正数
     */
    private Boolean isHundredDefPositive;

    /**
     * 十位差值是否正数
     */
    private Boolean isDecadeDefPositive;

    /**
     * 个位差值是否正数
     */
    private Boolean isSingleDefPositive;

    /**
     * 开奖日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lotteryDate;

}
