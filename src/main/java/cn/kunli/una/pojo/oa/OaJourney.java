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
 * 办公-出差行程(OaJourney)实体类
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:22
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OaJourney extends BasePojo implements Serializable {
    private static final long serialVersionUID = -44766361641971710L;
    //所属出差id
    private Integer travelId;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //交通工具 字典编码
    private String transportDcode;
    //交通费
    private Double transportExpense;
    //是否住宿
    private Integer isAccommodation;
    //住宿费
    private Double accommodationExpense;

}
