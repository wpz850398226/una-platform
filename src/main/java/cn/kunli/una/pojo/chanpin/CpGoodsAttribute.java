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

    private Integer id;
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
    private String pictureUrl;
    //积分
    private Integer points;
    //生效标记：0无效；1有效
    private Integer isEffect;
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
