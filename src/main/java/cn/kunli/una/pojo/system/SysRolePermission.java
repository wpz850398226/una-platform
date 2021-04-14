package cn.kunli.una.pojo.system;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;
import lombok.experimental.Accessors;

import javax.persistence.Transient;

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


    @Transient
    private String roleName;
    @Transient
    private String permissionName;
    @Transient
    private String keyword;
    @Transient
    private Integer entityId;			//相关权限所属实体
    @Transient
    private String permissionTypeDcode; //权限类型编码


}
