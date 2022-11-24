package cn.kunli.una.pojo.sys;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="系统-关系", description="")
public class SysRelation extends BasePojo implements Serializable {

    //树结构实体id
    private Integer parentEntityId;

    //数据所属实体id
    private Integer entityId;

    //关联关系的字段id
    private Integer relatedFieldId;

    //树结构查询条件 字段id
    private Integer parentDataFieldId;

    //树结构查询条件 值
    private String parentDataValue;

    //继承字段id
    private Integer extendFieldId;


    @TableField(exist = false)
    private String relatedFieldCode;
    @TableField(exist = false)
    private String parentDataFieldCode;
    @TableField(exist = false)
    private String extendFieldCode;
    @TableField(exist = false)
    private String parentEntityName;                //父实体名称
    @TableField(exist = false)
    private String parentEntityPath;            //父实体url前缀

    private static final long serialVersionUID = 1L;


}
