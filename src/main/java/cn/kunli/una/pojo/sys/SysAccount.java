package cn.kunli.una.pojo.sys;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class SysAccount extends BasePojo implements Serializable {
    private static final long serialVersionUID = -32742696237078749L;
    //昵称
    @NotBlank(message = "名称不能为空！")
    @Size(min = 2, max = 12, message = "名称长度必须为2-12")
    private String name;
    //来源 字典编码
    private String originDcode;
    //账号类型 字典编码
    private String typeDcode;
    //状态 字典编码
    private String statusDcode;
    //账号
    @NotBlank(message = "账号不能为空！")
    private String username;
    //密码
    private String password;
    //头像照片id（前端异步加载）
    private Integer iconFileId;
    //证件文件ids
    private String certificateFileIds;
    //是否允许多点登录
    private Boolean isMultipointLogin;
    //角色ids
    @NotBlank(message = "角色不能为空！")
    private String roleId;
    //在线验证sessionid（登录刷新）
    private String onlineSession;
    //直接上级 账号id
    private Integer superiorAccountId;
    //行业类型
    private String industryTypeDcodes;
    //坐标
    private String coord;
    //所属地区
    private String regionIds;
    //会员截止日期
    private Date memberDeadline;
    //隐藏字段id
//    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String hideFieldIds;




    @TableField(exist = false)
    private String[] roleIdArray;
    @TableField(exist = false)
    private List<SysRole> roleList;
    @TableField(exist = false)
    private SysUser sysUser;
    @TableField(exist = false)
    private Boolean isAudit;    //是否审核通过
    @TableField(exist = false)
    private Boolean isSubmit;    //是否提交审核

}
