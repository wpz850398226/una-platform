package cn.kunli.una.pojo.sys;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户(SysUser)实体类
 *
 * @author Ponzio
 * @since 2021-03-14 21:01:33
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysUser extends BasePojo implements Serializable {
    private static final long serialVersionUID = -40208367511437689L;
    //账号id
    private Integer accountId;
    //姓名
    private String name;
    //性别（女0；男1）
    private Integer gender;
    //工作职务
    private String job;
    //生日
    private Date birthday;
    //证件照片（身份证，营业执照等）
    private String fileIds;
    //邮箱
    private String email;
    //座机号
    private String tel;
    //手机号
    @NotBlank
    @Pattern(regexp = "^[0-9]{11}$", message = "手机号格式不正确！")
    private String mobile;
    //身份证号码
    @NotBlank(message = "身份证号不能为空")
    @Pattern(regexp = "^[0-9]{17}([0-9]|X)$", message = "身份证号格式不正确！")
    private String idNumber;
    //qq号
    private String qq;
    //微信号
    private String wechat;
    //微信公众号openid
    private String openid;
    //婚否（0，未婚；1，已婚）
    private Boolean isMarried;
    //是否在职（0：否，1，是）
    private Boolean isInservice;
    //籍贯 地区字典id
    private String nativePlaceRid;
    //籍贯名称
    private String nativePlaceRname;
    //学历 字典编码
    private String degreeDcode;
    //政治面貌 字典编码
    private String politicalDcode;
    //民族 字典编码
    private String nationDcode;
    //血型 字典编码
    private String bloodTypeDcode;
    //地址，住址
    private String address;

}
