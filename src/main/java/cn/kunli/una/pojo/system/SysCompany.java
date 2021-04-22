package cn.kunli.una.pojo.system;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
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
@JsonInclude(value = JsonInclude.Include.NON_NULL)
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
    //附近水源
    private Integer headwaters;
    //主要职责
    private String mainResponsibility;
    //行业id们
    private String industryDcodes;
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
    //所属地址（营业执照为准）
    private String regionId;
    //纬度
    private Double latitude;
    //经度
    private Double longitude;
    //附件
    private String fileIds;
    //用户同步ID
    private String subId;


    @TableField(exist = false)
    private String[] industryIdArray;            //行业id数组
    @TableField(exist = false)
    private String[] fileIdArray;            //附件id数组
    @TableField(exist = false)
    private String legalPersonName;        //法人姓名
    @TableField(exist = false)
    private List<SysDepartment> departmentList;        //公司部门
    @TableField(exist = false)
    private List<SysAccount> userList;        //公司员工
    @TableField(exist = false)
    private List<SysFile> fileList;
    @TableField(exist = false)
    private String typeName;
    @TableField(exist = false)
    private String industryNames;
    @TableField(exist = false)
    private String regionName;
    @TableField(exist = false)
    private List<SysCompany> children;
    @TableField(exist = false)
    private String employeeId;
    @TableField(exist = false)
    private String rootCompanyId;
    //根公司id，组织上级，查询用
    @TableField(exist = false)
    private String grandCompanyId;
    @TableField(exist = false)
    private String companyId;
    @TableField(exist = false)
    private Integer userNumber;
    @TableField(exist = false)
    private String provinceName;    //省
    @TableField(exist = false)
    private String provinceId;    //省
    @TableField(exist = false)
    private String districtName;    //区
    @TableField(exist = false)
    private String districtId;    //区
    @TableField(exist = false)
    private String countyName;      //县
    @TableField(exist = false)
    private String levelName;   //公司等级
    @TableField(exist = false)
    private String parentCompanyName;
    @TableField(exist = false)
    private String principalRoleTitle;  //负责人角色名称
    @TableField(exist = false)
    private Integer personNum; //公司人数
    @TableField(exist = false)
    private Integer equipmentNum;   //器材库存
    @TableField(exist = false)
    private Integer equipageVehicleNum;   //器材库存
    @TableField(exist = false)
    private Integer vehicleNum; //备勤车辆总数
    @TableField(exist = false)
    private String subCompanyNum;
    @TableField(exist = false)
    private List<SysCompany> subCompany;
    @TableField(exist = false)
    private List<SysAccount> legalPersons;
    @TableField(exist = false)
    private String manageCompanyTitle;
    @TableField(exist = false)
    private String characterDname;
    @TableField(exist = false)
    private String isShow;
    /**
     * 公司ID in查询条件
     */
    @TableField(exist = false)
    private List<String> companyIds;

    @TableField(exist = false)
    private String pId;
}
