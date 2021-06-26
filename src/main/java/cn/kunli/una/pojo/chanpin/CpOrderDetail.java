package cn.kunli.una.pojo.chanpin;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;

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
public class CpOrderDetail extends BasePojo implements Serializable {
    private static final long serialVersionUID = 711995724247659262L;
    //订单id
    private Integer orderId;
    //商品规格编号
    private Integer goodsAttributeId;
    //成交单价
    private Double transactionPrice;
    //成交量
    private Integer transactionAmount;
}
