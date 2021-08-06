package cn.kunli.una.pojo.chanpin;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商城-订单明细表(CpOrderDetail)实体类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:03
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CpOrderItem extends BasePojo implements Serializable {
    private static final long serialVersionUID = 711995724247659262L;
    //订单id
    private Integer orderId;
    //商品id
    private Integer goodsId;
    //商品型号
    private Integer modelId;
    //成交单价
    private Double bargainPrice;
    //成交量
    private Integer volume;

    //商品型号示意图
    @TableField(exist = false)
    private String modelFileUrl;
}