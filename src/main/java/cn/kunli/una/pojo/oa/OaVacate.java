package cn.kunli.una.pojo.oa;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 办公-请假(OaVacate)实体类
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OaVacate extends BasePojo implements Serializable {
    private static final long serialVersionUID = -73103737365826349L;
    //请假类型
    private String typeDcode;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //事由
    private String reason;
    //流程实例id
    private Integer instanceId;
    //流程任务id，未通过时记录
    private Integer taskId;
    //时长
    private Integer duration;


    @TableField(exist = false)
    private Boolean isAgree;    //是否审核通过

}
