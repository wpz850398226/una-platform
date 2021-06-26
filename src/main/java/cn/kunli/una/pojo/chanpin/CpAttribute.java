package cn.kunli.una.pojo.chanpin;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.Accessors;

/**
 * 商城-规格属性表(CpAttribute)实体类
 *
 * @author Ponzio
 * @since 2021-06-23 23:33:38
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CpAttribute extends BasePojo implements Serializable {
    private static final long serialVersionUID = 564000734227507185L;
    //规格id
    private Integer specificationId;
}
