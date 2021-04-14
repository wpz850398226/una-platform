package cn.kunli.una.pojo.system;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import cn.kunli.una.pojo.BasePojo;


import javax.persistence.Transient;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SysFile extends BasePojo implements Serializable {

    private String originalTitle;

    private Long size;

    private Integer width;

    private Integer height;

    private String fileUrl;

    private String typeDcode;

    private String extension;

    private Integer entityId;


    @Transient
    private MultipartFile[] fileArray;
    @Transient
    private MultipartFile file;
    @Transient
    private String typeDname;

    private static final long serialVersionUID = 1L;


}
