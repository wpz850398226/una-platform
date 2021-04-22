package cn.kunli.una.pojo.system;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (SysRolePermission)实体类
 *
 * @author Ponzio
 * @since 2020-05-07 08:56:34
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysRolePermission extends BasePojo implements Serializable {
    private static final long serialVersionUID = 867842599456264820L;

    private Integer roleId;

    private Integer permissionId;
    //范围字典id
    private Integer scope;


    @TableField(exist = false)
    private String roleName;
    @TableField(exist = false)
    private String permissionName;
    @TableField(exist = false)
    private String keyword;
    @TableField(exist = false)
    private Integer entityId;            //相关权限所属实体
    @TableField(exist = false)
    private String permissionTypeDcode; //权限类型编码


}
