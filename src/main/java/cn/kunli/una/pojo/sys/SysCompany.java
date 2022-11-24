package cn.kunli.una.pojo.sys;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 公司，组织，单位(SysCompany)实体类
 *
 * @author Ponzio
 * @since 2021-02-23 09:24:07
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@ApiModel(value="系统-公司/组织", description="")
public class SysCompany extends BasePojo implements Serializable {
    private static final long serialVersionUID = -52568215496787820L;

    //企业编号
    private String code;
    //企业类型字典id
    private String typeDcode;
    //企业概述
    private String overview;
    //占地面积
    private String acreage;
    //主要职责
    private String mainResponsibility;
    //行业类型
    private String industryTypeDcodes;
    //注册资本
    private String registeredCapital;
    //注册日期
    private Date registeredDate;
    //负责人角色id
    private String principalRoleId;
    //法人id
    private String legalPersonId;
    //地址
    private String address;
    //母公司id/上级单位id
    private String parentId;
    //公司网址
    private String indexUrl;
    //联系电话
    private String contactNumber;
    //统一社会信用代码
    private String unifiedSocialCreditCode;
    //开户行种类（中国银行，农业银行……）
    private String openingBankTypeDcode;
    //开户行名称
    private String openingBankName;
    //对公账户
    private String corporateAccount;
    //所属地区
    private String regionIds;
    //坐标
    private String coord;
    //附件
    private String fileIds;
    //标题图片
    private Integer titleFileId;

    //简介
    private String introduction;
    //描述
    private String description;
    //等级
    private Integer level;
    //电话
    private String phone;
    //微信
    private String wechat;
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







    @TableField(exist = false)
    private String legalPersonName;        //法人姓名
    @TableField(exist = false)
    private List<SysDepartment> departmentList;        //公司部门
    @TableField(exist = false)
    private List<SysAccount> userList;        //公司员工
}
