package cn.kunli.una.pojo.system;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysAnnouncement)实体类
 *
 * @author Ponzio
 * @since 2020-05-06 12:38:47
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysAnnouncement extends BasePojo implements Serializable {
    private static final long serialVersionUID = -90237312437025059L;
    //公司
    private String companyId;
    //所属部门
    private String departmentId;
    //是否发送至微信公众号
    private String isWechat;
    //类型
    private String type;
    //接收公司类型id
    private String companyTypeDcode;
    //公告内容
    private String content;
    //生效时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    //失效时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private String issuer;  //发布人


    @TableField(exist = false)
    private Integer isOverdue;  //过期的
    @TableField(exist = false)
    private Integer isReaded;  //已读的
}
