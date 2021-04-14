package cn.kunli.una.pojo.system;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import cn.kunli.una.pojo.BasePojo;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.Accessors;

/**
 * (SysVersion)实体类
 *
 * @author Ponzio
 * @since 2020-07-08 11:41:46
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysVersion extends BasePojo implements Serializable {
    private static final long serialVersionUID = 640860669042858758L;
    //外部版本号
    private String externalVersion;
    //内部版本号    暂时约定为下载文件名称
    private String internalVersion;
    //版本编码
    private Integer versionCode;
    //地址
    private String fileUrl;
    //更新内容
    private String description;
    //移动端类型字典id
    // 安卓: 9f94607e5d164759882e9609f4dec31b    IOS: ecc275cc8c5848e3bc593148ab091b4b
    private String appTypeDcode;
    @Transient
    private String appTypeName;



}
