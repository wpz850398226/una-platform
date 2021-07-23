package cn.kunli.una.pojo.oa;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 办公-考勤(OaAttendance)实体类
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:17
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OaAttendance extends BasePojo implements Serializable {
    private static final long serialVersionUID = 120156716659503245L;
    //是否上班，0否，1是
    private Boolean isOnDuty;
    //是否外勤，0否，1是
    private Boolean isOutside;
    //是否请假，0否，1是
    private Boolean isVacate;
    //坐标
    private String coord;
    //事由
    private String reason;
    //外勤照片
    private String fileIds;
    //打卡时间
    private Date signTime;
    //所属日期
    private Date attendanceDate;
    //账号id
    private Integer accountId;

}
