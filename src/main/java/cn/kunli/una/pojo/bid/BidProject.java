package cn.kunli.una.pojo.bid;

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
    //状态
    private String statusDcode;
    //项目区域
    private String regionIds;
    //开标类型
    private String openTypeDcode;
    //简介
    private String introduction;
    //内容
    private String content;
    //报名开始时间
    private Date registerStartTime;
    //报名结束时间
    private Date registerEndTime;
    //开标时间
    private Date openTime;
    //公示时间
    private Date publicityTime;
    //公示时长
    private Integer publicityDays;
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
    //收藏次数
    private String collectCount;
    //保证金
    private String deposit;
    //开标结果
    private String resultTypeDcode;
    //中标单位id
    private Integer bidAccountId;
    //收藏用户
    private String collectAccountIds;


    @TableField(exist = false)
    private Boolean isApplyed;    //是否已报名
}
