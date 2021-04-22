package cn.kunli.una.pojo.system;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (SysImgConfig)实体类
 *
 * @author Ponzio
 * @since 2020-06-10 11:26:04
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysImgConfig extends BasePojo implements Serializable {
    private static final long serialVersionUID = -79373983203602785L;
    //模块id
    private String moduleDcode;
    //编码
    private String code;
    //描述
    private String description;
    //值，图片地址
    private String value;
    //链接地址
    private String path;
    //公司id
    private String companyId;
    //顺序


    //模块编码
    @TableField(exist = false)
    private String moduleDname;

}
