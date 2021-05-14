package cn.kunli.una.pojo.system;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysIndex extends BasePojo implements Serializable {


    private String name;

    private String description;

    private String type;

    private String href;

    private String onclick;

    private String title;

    private String pictureUrl;

    private String iconDicId;

    private Integer roleId;


    @TableField(exist = false)
    private String icon;
    @TableField(exist = false)
    private String roleName;


}
