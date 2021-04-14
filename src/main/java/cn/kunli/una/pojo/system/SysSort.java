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
public class SysSort extends BasePojo implements Serializable {


    private Integer entityId;

    private String fieldId;

    private String sortord;




    @Transient
    private String entityName;			//实体名称
    @Transient
    private String fieldName;			//排序字段名称
    @Transient
    private String fieldDqlName;		//排序查询字段语句
    @Transient
    private String name;

    private static final long serialVersionUID = 1L;


}
