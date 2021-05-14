package cn.kunli.una.pojo.system;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (SysButton)实体类
 *
 * @author Ponzio
 * @since 2020-05-07 08:09:02
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysButton extends BasePojo implements Serializable {
    private static final long serialVersionUID = 643270690059007717L;
    //所属实体
    private Integer entityId;
    //位置（table,form
    private String positionDcode;
    //事件名称
    private String event;
    //点击事件方法
    private String onclick;
    //class值
    private String classValue;
    //图标
    private String iconDcode;
    //标签
    private String label;
    //顺序


    @TableField(exist = false)
    private String entityName;
    @TableField(exist = false)
    private String positionDname;
    @TableField(exist = false)
    private String positionDvalue;
    @TableField(exist = false)
    private String iconDvalue;


}
