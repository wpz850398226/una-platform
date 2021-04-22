package cn.kunli.una.pojo.system;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysTask extends BasePojo implements Serializable {


    private String typeDicId;

    private String statusDicId;

    private String name;

    private String content;

    private String fileId;

    private Integer executorNum;            //执行人数量

    private Date closeTime;

    private Integer entityId;

    private String entityDataId;

    private String priorityDicId;


    @TableField(exist = false)
    private String typeName;
    @TableField(exist = false)
    private SysDictionary status;
    @TableField(exist = false)
    private SysDictionary priority;
    @TableField(exist = false)
    private String entityName;
    @TableField(exist = false)
    private String receiverId;        //接收人id
    @TableField(exist = false)
    private List<String> receiverIdList;


    private static final long serialVersionUID = 1L;


}
