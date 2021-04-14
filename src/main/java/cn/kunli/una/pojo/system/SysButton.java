package cn.kunli.una.pojo.system;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;
import lombok.experimental.Accessors;

import javax.persistence.Transient;

/**
 * (SysButton)实体类
 *
 * @author Ponzio
 * @since 2020-05-07 08:09:02
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
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




    @Transient
    private String entityName;
    @Transient
    private String positionDname;
    @Transient
    private String positionDvalue;
    @Transient
    private String iconDvalue;


}
