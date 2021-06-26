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
 * 办公-薪资(OaSalary)实体类
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:24
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OaSalary extends BasePojo implements Serializable {
    private static final long serialVersionUID = 458469544799627749L;
    //所属月份
    private Date month;
    //缺卡次数
    private Integer lackAmount;
    //请假天数
    private Integer vacationDays;
    //出差天数
    private Integer travelDays;
    //迟到次数
    private Integer lateAmount;
    //早退次数
    private Integer leaveEarlyAmount;
    //加班天数
    private Integer extraWorkDays;
    //旷工天数
    private Integer absenteeismDays;
    //最终薪资
    private Double salary;

}
