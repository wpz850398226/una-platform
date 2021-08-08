package cn.kunli.una.pojo.gongqiu;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 供求信息(GqInformation)实体类
 *
 * @author Ponzio
 * @since 2021-07-21 21:17:30
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class GqInformation extends BasePojo implements Serializable {
    private static final long serialVersionUID = 976137308522223657L;
    //编号
    private String code;
    //全称
    private String fullName;
    //已销售量
    private Integer sales;
    //行业类型
    private String industryTypeDcodes;
    //类型编码
    private String typeDcode;
    //来源类型编码
    private String originTypeDcode;
    //认证类型编码
    private String certifyDcode;
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
    //上架人id
    private Integer adderId;
    //上架时间
    private Date addedTime;
    //是否可开发票
    private Boolean isInvoice;
    //销售价，标价
    private Double sellingPrice;
    //刷新时间
    private Date refreshTime;
    //置顶截止时间
    private Date stickDeadline;
    //联系方式
    private String mobile;

}
