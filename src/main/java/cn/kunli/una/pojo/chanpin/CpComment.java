package cn.kunli.una.pojo.chanpin;

import java.util.Date;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;

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
    //所属商品
    private Integer goodsId;
    //banner图片
    private String fileIds;
    //评分
    private Integer score;
}
