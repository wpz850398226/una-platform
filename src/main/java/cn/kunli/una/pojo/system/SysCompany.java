package cn.kunli.una.pojo.system;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Transient;
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


    @Transient
    private String[] industryIdArray;            //行业id数组
    @Transient
    private String[] fileIdArray;            //附件id数组
    @Transient
    private String legalPersonName;        //法人姓名
    @Transient
    private List<SysDepartment> departmentList;        //公司部门
    @Transient
    private List<SysAccount> userList;        //公司员工
    @Transient
    private List<SysFile> fileList;
    @Transient
    private String typeName;
    @Transient
    private String industryNames;
    @Transient
    private String regionName;
    @Transient
    private List<SysCompany> children;
    @Transient
    private String employeeId;
    @Transient
    private String rootCompanyId;
    //根公司id，组织上级，查询用
    @Transient
    private String grandCompanyId;
    @Transient
    private String companyId;
    @Transient
    private Integer userNumber;
    @Transient
    private String provinceName;    //省
    @Transient
    private String provinceId;    //省
    @Transient
    private String districtName;    //区
    @Transient
    private String districtId;    //区
    @Transient
    private String countyName;      //县
    @Transient
    private String levelName;   //公司等级
    @Transient
    private String parentCompanyName;
    @Transient
    private String principalRoleTitle;  //负责人角色名称
    @Transient
    private Integer personNum; //公司人数
    @Transient
    private Integer equipmentNum;   //器材库存
    @Transient
    private Integer equipageVehicleNum;   //器材库存
    @Transient
    private Integer vehicleNum; //备勤车辆总数
    @Transient
    private String subCompanyNum;
    @Transient
    private List<SysCompany> subCompany;
    @Transient
    private List<SysAccount> legalPersons;
    @Transient
    private String manageCompanyTitle;
    @Transient
    private String characterDname;
    @Transient
    private String isShow;
    /**
     * 公司ID in查询条件
     */
    @Transient
    private List<String> companyIds;

    @Transient
    private String pId;
}
