package cn.kunli.una.pojo.sys;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 账号(SysAccount)实体类
 *
 * @author Ponzio
 * @since 2021-03-14 20:59:47
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@ApiModel(value="系统-", description="")
public class SysAccount extends BasePojo implements Serializable {
    private static final long serialVersionUID = -32742696237078749L;
    @ApiModelProperty(value = "昵称", dataType = "String")
    @NotBlank(message = "名称不能为空！")
    @Size(min = 2, max = 12, message = "名称长度必须为2-12")
    private String name;

    @ApiModelProperty(value = "来源", dataType = "String")
    private String originDcode;

    @ApiModelProperty(value = "账号类型", dataType = "String")
    private String typeDcode;

    @ApiModelProperty(value = "状态", dataType = "String")
    private String statusDcode;

    @ApiModelProperty(value = "账号", dataType = "String")
    @NotBlank(message = "账号不能为空！")
    private String username;

    @ApiModelProperty(value = "密码", dataType = "String")
    private String password;

    //头像照片id（前端异步加载）
    @ApiModelProperty(value = "头像照片", dataType = "Integer")
    private Integer iconFileId;

    //证件文件ids
    @ApiModelProperty(value = "证件文件", dataType = "String")
    private String certificateFileIds;

    //是否允许多点登录
    @ApiModelProperty(value = "是否允许多点登录", dataType = "Boolean")
    private Boolean isMultipointLogin;

    //角色ids
    @ApiModelProperty(value = "角色", dataType = "String")
    @NotBlank(message = "角色不能为空！")
    private String roleId;

    //在线验证sessionid（登录刷新）
    @ApiModelProperty(value = "在线验证session", dataType = "String")
    private String onlineSession;

    //直接上级 账号id
    @ApiModelProperty(value = "直接上级", dataType = "Integer")
    private Integer superiorAccountId;

    //行业类型
    @ApiModelProperty(value = "行业类型", dataType = "String")
    private String industryTypeDcodes;

    //坐标
    @ApiModelProperty(value = "坐标", dataType = "String")
    private String coord;

    //所属地区
    @ApiModelProperty(value = "所属地区", dataType = "String")
    private String regionIds;

    //会员截止日期
    @ApiModelProperty(value = "会员截止日期", dataType = "Date")
    private Date memberDeadline;




    @ApiModelProperty(value = "角色id数组", dataType = "Array")
    @TableField(exist = false)
    private String[] roleIdArray;

    @ApiModelProperty(value = "角色集合", dataType = "List")
    @TableField(exist = false)
    private List<SysRole> roleList;

    @ApiModelProperty(value = "用户信息", dataType = "Object")
    @TableField(exist = false)
    private SysUser sysUser;

    @ApiModelProperty(value = "是否审核通过", dataType = "Boolean")
    @TableField(exist = false)
    private Boolean isAudit;    //是否审核通过

    @ApiModelProperty(value = "是否提交审核", dataType = "Boolean")
    @TableField(exist = false)
    private Boolean isSubmit;    //是否提交审核

}
