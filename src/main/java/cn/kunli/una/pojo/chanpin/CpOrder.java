package cn.kunli.una.pojo.chanpin;

import java.util.Date;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;

/**
 * 商城-订单类(CpOrder)实体类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:02
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CpOrder extends BasePojo implements Serializable {
    private static final long serialVersionUID = -51268776736736818L;
    //订单编号
    private String code;
    //订单支付方式
    private String paymentType;
    //配送地址id
    private Integer deliveryAddressId;
    //配送时间
    private Date deliveryTime;
    //配送方式
    private String deliveryType;
    //配送费，快递费
    private Double deliveryCost;
    //配送费支付方式
    private String deliveryPaymentTypeDcode;
    //快递单号
    private String expressNumber;
    //发票抬头
    private String invoiceName;
    //订单状态
    private String statusDcode;
    //订单总金额
    private Double orderAmount;
    //订单积分
    private Integer orderIntegral;
    //支付总金额
    private Double paymentAmount;
    //商品总数量
    private Integer goodsAmount;
}