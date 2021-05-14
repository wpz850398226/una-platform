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
 * (SysConfiguration)实体类
 *
 * @author Ponzio
 * @since 2020-05-07 08:53:32
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysConfiguration extends BasePojo implements Serializable {
    private static final long serialVersionUID = 470869590176267899L;
    //平台id
    private String platformDcode;
    //类型
    private String type;
    //编码
    private String code;
    //描述
    private String description;
    //值
    private String value;
    //模块id
    private String moduleDcode;


    //模块名称
    @TableField(exist = false)
    private String moduleDname;
    //公司名称
    @TableField(exist = false)
    private String companyName;


}
