package cn.kunli.una.pojo.sys;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 角色(SysRole)实体类
 *
 * @author Ponzio
 * @since 2021-02-24 09:37:01
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysRole extends BasePojo implements Serializable {
    private static final long serialVersionUID = 535825710305670374L;
    //公司类型id
    private String companyTypeDcode;
    //描述
    private String description;
    //首页地址
    private String indexUrl;
    //权重（权重越大，影响力越大）
    private Integer weight;


    @TableField(exist = false)
    private String companyTypeDname;
    @TableField(exist = false)
    private List<String> permissionIdList;
    @TableField(exist = false)
    private List<SysRolePermission> rolePermissionList;
    @TableField(exist = false)
    private List<SysIndex> indexList;
    @TableField(exist = false)
    private Map<Integer, String> permMap;
}
