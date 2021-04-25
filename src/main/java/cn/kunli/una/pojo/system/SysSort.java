package cn.kunli.una.pojo.system;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysSort extends BasePojo implements Serializable {


    private Integer entityId;

    private String fieldId;

    private Boolean sortord;


    @TableField(exist = false)
    private String entityName;            //实体名称
    @TableField(exist = false)
    private String fieldName;            //排序字段名称
    @TableField(exist = false)
    private String fieldDqlName;        //排序查询字段语句
    @TableField(exist = false)
    private String name;

    private static final long serialVersionUID = 1L;


}
