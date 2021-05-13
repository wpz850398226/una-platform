package cn.kunli.una.pojo.system;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单(SysMenu)实体类
 *
 * @author Ponzio
 * @since 2021-02-24 09:34:28
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysMenu extends BasePojo implements Serializable {
    private static final long serialVersionUID = 162092510907532939L;
    //前端路由标识
    private String route;
    //菜单类型（0，菜单类；1，链接类）
    private String type;
    //链接地址
    private String path;
    //导航等级
    private Integer level;
    //父id
    private Integer parentId;
    //所属实体类名
    private String code;
    //所属权限id
    private Integer permissionId;
    //图标字典id
    private String iconDcode;
    //图标图片id
    private Integer iconFid;
    //动画效果
    private String animatedStyle;
    //是否生效
    private Integer isEffect;


    @TableField(exist = false)
    List<SysMenu> children = new ArrayList<>();
    @TableField(exist = false)
    private SysMenu parentMenu;
    @TableField(exist = false)
    private String parentName;
    @TableField(exist = false)
    private String permissionName;
    @TableField(exist = false)
    private String permissionCode;
    @TableField(exist = false)
    private String iconDname;
    @TableField(exist = false)
    private String iconFurl;
    @TableField(exist = false)
    private String packageName;
    @TableField(exist = false)
    private String activitName;
    @TableField(exist = false)
    private String versionName;
    @TableField(exist = false)
    private String appCode;

}
