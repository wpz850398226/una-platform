package cn.kunli.una.pojo.chanpin;

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
}
