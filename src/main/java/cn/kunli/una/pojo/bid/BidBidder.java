package cn.kunli.una.pojo.bid;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 招投标-投标信息(BidBidder)实体类
 *
 * @author Ponzio
 * @since 2021-07-17 13:19:38
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@TableName(autoResultMap = true)
public class BidBidder extends BasePojo implements Serializable {
    private static final long serialVersionUID = 733158152365029293L;
    //投标项目id
    private Integer projectId;
    //投标文件id
    private Integer bidFileId;
    //竞价文件id
    private Integer biddingFileId;
    //投标状态
    private String statusDcode;
    //投标结果
    private String resultTypeDcode;
    //是否递交保证金
    private Boolean isDeposit;
    //是否递交投标文件
    private Boolean isDeliver;

    @TableField(exist = false)
    private String projectStatusDname;    //项目状态
}
