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
    private Integer isOnDuty;
    //是否外勤，0否，1是
    private Integer isOutside;
    //是否请假，0否，1是
    private Integer isVacate;
    //坐标
    private String coord;
    //事由
    private String reason;
    //外勤照片
    private String fileIds;
    //打卡时间
    private Date signTime;

}
