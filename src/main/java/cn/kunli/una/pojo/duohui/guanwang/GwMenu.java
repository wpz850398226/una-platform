package cn.kunli.una.pojo.duohui.guanwang;

import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.pojo.system.SysMenu;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜单(GwMenu)实体类
 *
 * @author Ponzio
 * @since 2021-03-26 11:09:34
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class GwMenu extends BasePojo implements Serializable {
    private static final long serialVersionUID = -80884215785303575L;
    //前端路由标识
    private String route;
    //平台id（pc后台，app）
    private String platformDcode;
    //菜单类型（0，菜单类；1，链接类）
    private String type;
    //链接地址
    private String path;
    //顺序，按从小到大排序，0开始
    private Integer sequence;
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


    @TableField(exist = false)
    List<GwMenu> children = new ArrayList<>();

}
