package cn.kunli.una.pojo.chanpin;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 商城-店铺类(CpShop)实体类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:03
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CpShop extends BasePojo implements Serializable {
    private static final long serialVersionUID = 394422274241492697L;
    //编号
    private String code;
    //简介
    private String introduction;
    //描述
    private String description;
    //所属地区
    private Integer regionId;
    //详细地址
    private String address;
    //等级
    private Integer level;
    //电话
    private String phone;
    //微信
    private String wechat;
    //标题图片
    private Integer titleFileId;
    //微信二维码图片
    private Integer wechatFileId;
    //图片
    private String bannerFileIds;
    //是否置顶
    private Boolean isStick;
    //是否热门
    private Boolean isHot;
    //是否已审核
    private Boolean isAudit;
    //关注数量
    private Integer concernAmount;
    //刷新时间
    private Date refreshTime;
    //置顶截止时间
    private Date stickDeadline;
    //关于我们图片
    private Integer aboutUsFileId;
    //关于我们描述
    private String aboutUsDescription;
    //关于我们内容
    private String aboutUsContent;
    //是否有门面
    private Boolean isFacade;
}
