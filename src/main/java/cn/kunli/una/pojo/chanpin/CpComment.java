package cn.kunli.una.pojo.chanpin;

import cn.kunli.una.pojo.BasePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商城-评论(CpComment)实体类
 *
 * @author Ponzio
 * @since 2021-07-21 21:17:23
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CpComment extends BasePojo implements Serializable {
    private static final long serialVersionUID = 389744770616016716L;
    //内容
    private String content;
    //所属订单项
    private Integer orderItemId;
    //banner图片
    private String fileIds;
    //评分
    private String score;
}
