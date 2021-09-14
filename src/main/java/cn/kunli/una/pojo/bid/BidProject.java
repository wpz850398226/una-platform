package cn.kunli.una.pojo.bid;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
    private String regionIds;
    //开标类型
    private String openTypeDcode;
    //简介
    private String introduction;
    //内容
    private String content;
    //发布时间
    private Date releaseTime;
    //报名开始时间
    private Date registerStartTime;
    //报名结束时间
    private Date registerEndTime;
    //投标开始时间
    private Date bidStartTime;
    //投标结束时间
    private Date bidEndTime;
    //开标时间
    private Date openTime;
    //投标完成时间
    private Date completeTime;
    //招标文件id
    private Integer bidFileId;
    //竞价文件id
    private Integer biddingFileId;
    //行业类型
    private String industryDcodes;
    //资质要求
    private String qualification;
    //联系方式
    private String phone;
    //浏览次数
    private String browseCount;
    //公司名称
    private String corporateName;

}
