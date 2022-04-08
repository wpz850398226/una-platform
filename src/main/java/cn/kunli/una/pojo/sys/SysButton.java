package cn.kunli.una.pojo.sys;

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
    //所属权限id
    private Integer permissionId;
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
    //警示语
    private String warning;
    //表单实体id
    private Integer formEntityId;
    //表单携带参数字段id
    private Integer formFieldId;
    //条件字段id
    private Integer conditionFieldId;
    //条件值
    private String conditionValue;



    @TableField(exist = false)
    private String formFieldCode;


}
