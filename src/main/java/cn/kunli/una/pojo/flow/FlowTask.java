package cn.kunli.una.pojo.flow;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 流程任务(FlowTask)实体类
 *
 * @author Ponzio
 * @since 2021-05-12 22:29:52
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class FlowTask extends BasePojo implements Serializable {
    private static final long serialVersionUID = -67535831425503802L;
    //实例id
    private Integer instanceId;
    //实体记录id
    private String recordId;
    //办理人id
    private Integer accountId;
    //是否审批通过，0否，1是
    private Boolean isAgree;
    //意见
    private String opinion;
    //节点id
    private Integer nodeId;
    //候选人id
    private String candidateAccountId;
    //节点类型编码
    private String nodeTypeDcode;
    //节点关联的实体id
//    private Integer nodeEntityId;
    //激活时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activateTime;
    //关闭时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date offTime;

    //同一个实例中已经提交的任务
    @TableField(exist = false)
    private List<FlowTask> submitTaskList;
}
