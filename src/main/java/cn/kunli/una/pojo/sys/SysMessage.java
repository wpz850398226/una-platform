package cn.kunli.una.pojo.sys;

import cn.kunli.una.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (SysMessage)实体类
 *
 * @author Ponzio
 * @since 2020-06-05 11:32:46
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@ApiModel(value="系统-消息", description="")
public class SysMessage extends BasePojo implements Serializable {
    private static final long serialVersionUID = -23693590497690198L;
    //消息类型
    private String typeDcode;
    //操作相关实体类id
    private Integer entityId;
    //操作相关数据id
    private Integer dataId;
    //内容
    private String content;

    private String mobile;

    private String captcha;

    private String signName;        //短信签名

    private String templateCode;        //模板编号
    //文件保存地址
    private String fileIds;
    //是否已推送
    private Boolean isSend;
    //是否已读：0未读，1已读
    private Boolean isRead;
    //是否人为（0：否，1：是）
    private Boolean isArtificial;
    //接收人id
    private String receiverAccountId;

}
