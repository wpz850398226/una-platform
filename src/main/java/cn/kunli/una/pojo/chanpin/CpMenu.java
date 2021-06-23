package cn.kunli.una.pojo.chanpin;

import java.util.Date;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;

/**
 * 菜单(CpMenu)实体类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:01
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CpMenu extends BasePojo implements Serializable {
    private static final long serialVersionUID = -96478808873895124L;
    //主键
    private Integer id;
    //名称
    private String name;
    //前端路由标识
    private String route;
    //平台id（pc后台，app）
    private String platformDcode;
    //菜单类型（0，菜单类；1，链接类）
    private String type;
    //链接地址
    private String path;
    //顺序
    private Integer sortOrder;
    //导航等级
    private Integer level;
    //父id
    private Integer parentId;
    //相关实体类名
    private String code;
    //所属权限id
    private Integer permissionId;
    //图标字典id
    private String iconDcode;
    //图标图片id
    private Integer iconFid;
    //动画效果
    private String animatedStyle;
    //备注
    private String remark;
    //创建人id
    private Integer creatorId;
    //创建人名称
    private String creatorName;

    private Date createTime;
    //创建人IP
    private String creatorHost;

    private Integer modifierId;
    //修订人名称
    private String modifierName;

    private Date modifyTime;
    //修订人IP
    private String modifierHost;
    //是否删除
    private Integer isDelete;
    //是否生效
    private Integer isEffect;
    //租户id
    private Integer tentId;
    //所属组织id
    private Integer companyId;
    //所属部门id
    private Integer departmentId;

}
