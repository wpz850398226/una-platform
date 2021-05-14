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
public class SysRelation extends BasePojo implements Serializable {

    private Integer parentEntityId;   //树结构实体id

    private Integer entityId;   //数据所属实体id

    private Integer relatedFieldId;

    private Integer parentDataFieldId;

    private String parentDataValue;


    @TableField(exist = false)
    private String relatedFieldCode;
    @TableField(exist = false)
    private String relatedFieldName;
    @TableField(exist = false)
    private String parentDataFieldCode;
    @TableField(exist = false)
    private String parentDataFieldName;
    @TableField(exist = false)
    private String parentEntityName;                //父实体名称
    @TableField(exist = false)
    private String subEntityName;                    //子实体名称
    @TableField(exist = false)
    private String parentEntityPath;            //父实体url前缀

    private static final long serialVersionUID = 1L;


}
