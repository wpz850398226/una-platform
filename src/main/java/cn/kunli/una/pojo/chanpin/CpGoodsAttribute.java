package cn.kunli.una.pojo.chanpin;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;

/**
 * 商城-商品规格表(CpGoodsAttribute)实体类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:00
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CpGoodsAttribute extends BasePojo implements Serializable {
    private static final long serialVersionUID = 340194362530078834L;
    //编号
    private String code;
    //规格（存放规格属性名称，逗号分隔）
    private String attributeNames;
    //所属商品
    private Integer goodsId;
    //库存
    private Integer inventory;
    //销量
    private Integer sales;
    //销售价，标价
    private Double sellingPrice;
    //成本价
    private Double costPrice;
    //最高限价
    private Double ceilingPrice;
    //最低限价
    private Double floorPrice;
    //批发售价
    private Double wholesalePrice;
    //含税市场价
    private Double taxInclusiveMarketPrice;
    //不含税市场价
    private Double taxExclusiveMarketPrice;
    //示意图url
    private Integer fileId;
    //积分
    private Integer points;
    //生效标记：0无效；1有效
    private Integer isEffect;


    //商品名称
    @TableField(exist = false)
    private String goodsName;
    //店铺名称
    @TableField(exist = false)
    private String shopName;
}
