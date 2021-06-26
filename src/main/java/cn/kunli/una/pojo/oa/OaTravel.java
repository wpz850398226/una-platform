package cn.kunli.una.pojo.oa;

import java.util.Date;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;

/**
 * 办公-出差(OaTravel)实体类
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:24
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OaTravel extends BasePojo implements Serializable {
    private static final long serialVersionUID = -34656996502873446L;
    //事由
    private String reason;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //同行人
    private String companion;
    //时长
    private Integer duration;
    //总开销
    private Double expense;

}
