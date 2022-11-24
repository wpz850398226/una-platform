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

/**
 * (SysAuthorization)实体类
 *
 * @author Ponzio
 * @since 2020-05-07 08:56:34
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@ApiModel(value="系统-授权", description="")
public class SysAuthorization extends BasePojo implements Serializable {
    private static final long serialVersionUID = 867842599456264820L;

    private Integer roleId;

    private Integer permissionId;
    //范围字典id
    private String scopeDcode;


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
