package cn.kunli.una.pojo.system;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Transient;
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
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysRole extends BasePojo implements Serializable {
    private static final long serialVersionUID = 535825710305670374L;
    //公司类型id
    private String companyTypeDcode;
    //所属公司id
    private Integer companyId;
    //描述
    private String description;
    //首页地址
    private String indexUrl;
    //权重（权重越大，影响力越大）
    private Integer weight;


    @Transient
    private String companyName;
    @Transient
    private String companyTypeDname;
    @Transient
    private List<String> permissionIdList;
    @Transient
    private List<SysRolePermission> rolePermissionList;
    @Transient
    private List<SysIndex> indexList;
    @Transient
    private Map<Integer,Integer> permMap;
}
