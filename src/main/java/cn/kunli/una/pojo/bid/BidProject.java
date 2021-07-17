package cn.kunli.una.pojo.bid;

import java.util.Date;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;

/**
 * 招投标-项目表表(BidProject)实体类
 *
 * @author Ponzio
 * @since 2021-07-17 13:19:47
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BidProject extends BasePojo implements Serializable {
    private static final long serialVersionUID = -96784303890499086L;
    //项目区域
    private String projectArea;
    //项目类型0：现场，1：网络
    private Integer projectType;
    //项目详情
    private String projectDetails;
    //发布时间
    private Date releaseTime;
    //报名开始时间
    private Date registerStart;
    //报名结束时间
    private Date registerEnd;
    //投标开始时间
    private Date bidStart;
    //投标结束时间
    private Date bidEnd;
    //招标文件id
    private Integer bidFileId;
    //竞价文件id
    private Integer bidding;

}
