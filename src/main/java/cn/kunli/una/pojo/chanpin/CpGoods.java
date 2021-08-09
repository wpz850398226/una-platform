package cn.kunli.una.pojo.chanpin;

import cn.kunli.una.pojo.BasePojo;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(autoResultMap = true)
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
    //行业类型
    private String industryTypeDcodes;
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
    //所属地区
    private String regionIds;
    //是否置顶
    private Boolean isStick;
    //是否热门
    private Boolean isHot;
    //是否上架
    private Boolean isAdded;
    //是否已审核
    private Boolean isAudit;
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
    private Boolean isShowInventory;
    //是否启用规格
    private Boolean isSpecification;
    //是否减库存
    private Boolean isInventoryReduce;
    //是否支持货到付款
    private Boolean isPayOnDelivery;
    //是否正品保证
    private Boolean isAuthenticGuaranteed;
    //是否支持7天无理由退换
    private Boolean isExchangeInSeven;
    //是否可开发票
    private Boolean isInvoice;
    //是否可保修
    private Boolean isRepair;
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
    private List<CpModel> modelList;
    //标题图片
    @TableField(exist = false)
    private String titleImgUrl;
    //选中的规格属性
    @TableField(exist = false)
    private CpModel checkedModel;
    //是否开通店铺
    @TableField(exist = false)
    private Boolean isOpenShop;

}
