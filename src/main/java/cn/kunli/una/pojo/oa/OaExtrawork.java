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
 * 办公-加班(OaExtrawork)实体类
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:21
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OaExtrawork extends BasePojo implements Serializable {
    private static final long serialVersionUID = -40962512432287245L;
    //工作者id
    private Integer workerId;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //时长
    private Integer duration;
    //事由
    private String reason;

}
