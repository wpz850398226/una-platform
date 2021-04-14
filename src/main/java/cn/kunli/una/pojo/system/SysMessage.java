package cn.kunli.una.pojo.system;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;
import lombok.experimental.Accessors;

import javax.persistence.Transient;

/**
 * (SysMessage)实体类
 *
 * @author Ponzio
 * @since 2020-06-05 11:32:46
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysMessage extends BasePojo implements Serializable {
    private static final long serialVersionUID = -23693590497690198L;
    //消息类型
    private String typeDcode;
    //操作相关实体类id
    private Integer entityId;
    //操作相关数据id
    private String dataId;
    //内容
    private String content;

    private String mobile;

    private String captcha;

    private String signName;        //短信签名

    private String templateCode;        //模板编号
    //文件保存地址
    private String fileId;


    //是否已推送
    private Integer isSend;
    //是否已读：0未读，1已读
    private Integer isRead;
    //是否人为（0：否，1：是）
    private Integer isArtificial;
    //接收人id
    private String receiverId;

    private Date sendTime;


    @Transient
    private String creatorPhotoUrl;
    @Transient
    private String receiverName;
    @Transient
    private String entityName;
    @Transient
    private Integer amount;			//统计用：消息数量
    @Transient
    private List<String> receiverIdList;			//接收前台传回守信者id，群发功能


}
