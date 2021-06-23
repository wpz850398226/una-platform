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

    private Integer id;
    //订单id
    private Integer orderId;
    //商品规格编号
    private Integer goodsAttributeId;
    //成交单价
    private Double transactionPrice;
    //成交量
    private Integer transactionAmount;
    //名称
    private String name;
    //备注
    private String remark;
    //创建人id
    private Integer creatorId;
    //创建人名称
    private String creatorName;
    //创建人IP
    private String creatorHost;
    //创建时间
    private Date createTime;
    //修改人
    private Integer modifierId;
    //修订人名称
    private String modifierName;
    //修订人IP
    private String modifierHost;
    //修订时间
    private Date modifyTime;
    //租户id
    private Integer tentId;
    //是否逻辑删除
    private Integer isDelete;
    //顺序
    private Integer sortOrder;
    //所属组织id
    private Integer companyId;
    //所属部门id
    private Integer departmentId;

}
