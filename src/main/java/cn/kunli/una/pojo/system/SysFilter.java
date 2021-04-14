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
public class SysFilter extends BasePojo implements Serializable {

    private Integer entityId;

    private String fieldId;

    private String filterValue;



    @Transient
    private String entityName;
    @Transient
    private String fieldCode;
    @Transient
    private String fieldName;



    private static final long serialVersionUID = 1L;


}
