package cn.kunli.una.pojo.app;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 股票
 * </p>
 *
 * @author wangpz
 * @since 2022-04-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("app_stock")
@ApiModel(value="AppStock对象", description="股票")
public class AppStock extends BasePojo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "开盘日期")
    private LocalDateTime openDate;

    @ApiModelProperty(value = "开盘价")
    private Float openingPrice;

    @ApiModelProperty(value = "收盘价")
    private Float closingPrice;

    @ApiModelProperty(value = "最高价")
    private Float topPrice;

    @ApiModelProperty(value = "最低价")
    private Float floorPrice;

    @ApiModelProperty(value = "总手")
    private Integer totalHand;

    @ApiModelProperty(value = "市场排名")
    private Integer marketRanking;

    @ApiModelProperty(value = "高点数量")
    private Integer topAmount;

    @ApiModelProperty(value = "低点数量")
    private Integer floorAmount;

    @ApiModelProperty(value = "最高点时间")
    private LocalDateTime topTime;

    @ApiModelProperty(value = "最低点时间")
    private LocalDateTime floorTime;

    @ApiModelProperty(value = "贴吧情绪")
    private String abroadMoodDcode;


}
