package cn.kunli.una.pojo.system;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Transient;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysRelation extends BasePojo implements Serializable {

    private Integer parentEntityId;

    private Integer subEntityId;

    private Integer relatedFieldId;

    private Integer parentDataFieldId;

    private String parentDataValue;


    @Transient
    private String relatedFieldCode;
    @Transient
    private String relatedFieldName;
    @Transient
    private String parentDataFieldCode;
    @Transient
    private String parentDataFieldName;
    @Transient
    private String parentEntityName;				//父实体名称
    @Transient
    private String subEntityName;					//子实体名称
    @Transient
    private String parentEntityPath;			//父实体url前缀

    private static final long serialVersionUID = 1L;


}
