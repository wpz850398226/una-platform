package cn.kunli.una.pojo.chanpin;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 商城-商品规格表(CpModel)实体类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:00
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CpModel extends BasePojo implements Serializable {
    private static final long serialVersionUID = 340194362530078834L;
    //编号
    private String code;
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


    //商品名称
    @TableField(exist = false)
    private String goodsName;
    //店铺名称
    @TableField(exist = false)
    private String shopName;
    //型号名称集合
    @TableField(exist = false)
    private List<String> nameList;
}
