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

    private String titlePictureUrl;
    //微信二维码图片
    private String wechatErweimaPictureUrl;
    //图片数量
    private String pictureUrls;
    //是否置顶
    private Integer isStick;
    //是否热门
    private Integer isHot;
    //是否已审核
    private Integer isAudit;
    //关注数量
    private Integer concernAmount;
    //刷新时间
    private Date refreshTime;
    //置顶截止时间
    private Date stickDeadline;
}
