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
 * 办公-补卡(OaReissue)实体类
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:23
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OaReissue extends BasePojo implements Serializable {
    private static final long serialVersionUID = -98686762419524153L;
    //考勤记录id
    private Integer attendanceId;
    //事由
    private String reason;
    //图片
    private String fileIds;
    //是否审批通过
    private Integer isApprove;
    //流程实例id
    private Integer instanceId;
    //流程任务id，未通过时记录
    private Integer taskId;

}
