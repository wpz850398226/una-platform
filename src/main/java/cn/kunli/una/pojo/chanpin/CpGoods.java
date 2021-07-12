package cn.kunli.una.pojo.chanpin;

import cn.kunli.una.pojo.BasePojo;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 账号(CpGoods)实体类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:00
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CpGoods extends BasePojo implements Serializable {
    private static final long serialVersionUID = -52941713558499123L;
    //编号
    private String code;
    //全称
    private String fullName;
    //单位
    private String unitDcode;
    //已销售量
    private Integer sales;
    //所属店铺id
    private Integer shopId;
    //所属一级行业编码
    private String primaryIndustryDcode;
    //所属二级行业编码
    private String secondryIndustryDcode;
    //所属三级行业编码
    private String thirdryIndustryDcode;
    //类型id
    private String typeDcode;
    //产品状态id
    private String statusDcode;
    //关键字
    private String keyword;
    //商品简介
    private String introduction;
    //描述
    private String description;
    //内容
    private String content;
    //所属区域id
    private Integer regionId;
    //是否置顶
    private Integer isStick;
    //是否热门
    private Integer isHot;
    //是否上架
    private Integer isAdded;
    //是否已审核
    private Integer isAudit;
    //浏览次数
    private Integer viewAmount;
    //收藏数量
    private Integer collectAmount;
    //评论数量
    private Integer commentAmount;
    //平均评分
    private Double averageScore;
    //上架人id
    private Integer adderId;
    //上架时间
    private Date addedTime;
    //库存
    private Integer inventory;
    //是否显示库存
    private Integer isShowInventory;
    //是否启用规格
    private Integer isSpecification;
    //减库存方式
    private Integer isReductionType;
    //是否支持货到付款
    private Integer isPayOnDelivery;
    //是否正品保证
    private Integer isAuthenticGuaranteed;
    //是否支持7天无理由退换
    private Integer isExchangeInSeven;
    //是否可开发票
    private Integer isInvoice;
    //是否可保修
    private Integer isRepair;
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
    //运费
    private Double freightPrice;
    //免运费下限
    private Double freeFreightPrice;
    //刷新时间
    private Date refreshTime;
    //置顶截止时间
    private Date stickDeadline;
    //商品图片
    private String fileId;
    //所属组织id
    private Integer companyId;
    //所属部门id
    private Integer departmentId;
    //值，扩展字段
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private JSONObject goodsParam;



    //规格列表
    @TableField(exist = false)
    private List<CpSpecification> specificationList;
    //商品规格属性列表
    @TableField(exist = false)
    private List<CpGoodsAttribute> goodsAttributeList;
    //参数json字符串
    @TableField(exist = false)
    private String goodsParamStr;

}
