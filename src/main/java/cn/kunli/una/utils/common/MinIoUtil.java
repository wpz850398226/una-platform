package cn.kunli.una.utils.common;

import cn.kunli.una.config.MinioConfig;
import io.minio.*;
import io.minio.http.Method;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import cn.hutool.core.util.StrUtil;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * ClassName:    minio公共类
 * Description: minio文件服务工具类
 * Datetime:    2021/3/10 16:15
 * Author:   caojx@glodon.com
 */
@Slf4j
@NoArgsConstructor
@Component
public class MinIoUtil {
    /**
     * Minio
     */
    public static final String URI_DELIMITER = "/";
    @Autowired
    private MinioConfig minioConfig;
    @Autowired
    private MinioClient minioClient;

    /**
     * 上传文件
     * @param multipartFile
     * @return
     */
    public String putObject (MultipartFile multipartFile) {
        return putObject(new MultipartFile[] {multipartFile}).get(0);
    }

    /**
     * 上传文件
     * @param multipartFiles
     * @return
     */
    public List<String> putObject(MultipartFile ...multipartFiles) {
        String bucketName = minioConfig.getBucketName();
        try {

            List<String> retVal = new LinkedList<>();

            String[] folders = getDateFolder();

            for (MultipartFile multipartFile : multipartFiles) {

                // UUID重命名
                String fileName = UUID.randomUUID().toString().replace("-", "") + "." + getSuffix(multipartFile.getOriginalFilename());

                // 年/月/日/file
                String finalPath = new StringBuilder(String.join(URI_DELIMITER, folders))
                        .append(URI_DELIMITER)
                        .append(fileName).toString();

                minioClient.putObject(PutObjectArgs.builder()
                        .stream(multipartFile.getInputStream(), multipartFile.getSize(),PutObjectArgs.MIN_MULTIPART_SIZE)
                        .object(finalPath)
                        .contentType(multipartFile.getContentType())
                        .bucket(bucketName)
                        .build());

//                retVal.add(gateway(finalPath));
                retVal.add(finalPath);
            }
            return retVal;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description    文件下载
     * @param bucketName
     * @param fileName
     * @param actualFileName
     * @param httpServletResponse
     * @Return void
     * @Author  caojx
     * @Datetime  2021/3/11 16:31
     */
    public void download(String bucketName, String fileName,String actualFileName, HttpServletResponse httpServletResponse) {
        try {
            //如果bucket为null 为默认bucket
            if (StrUtil.isBlank(bucketName)) {
                bucketName = minioConfig.getBucketName();
            }
            StatObjectResponse statObjectResponse = minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(fileName).build());
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(actualFileName, "UTF-8"));
            httpServletResponse.setContentType(statObjectResponse.contentType());
            httpServletResponse.setCharacterEncoding("UTF-8");
            InputStream inputStream = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
            IOUtils.copy(inputStream, httpServletResponse.getOutputStream());
            inputStream.close();
        } catch (Exception e) {
            log.error("附件下载失败, fileName: {}, bucketName: {}, attachmentNameInServer: {}", actualFileName, bucketName, fileName);
            e.printStackTrace();
        }

    }

    /**
     * 查询预览地址或下载地址，有过期时间
     * @param bucketName
     * @param objectName
     * @param expiryTime
     * @return
     */
    @SneakyThrows
    public String getDownLoadPath(String bucketName,String objectName,Integer expiryTime){
        if(StrUtil.isBlank(objectName))return null;
        if(StrUtil.isBlank(bucketName))bucketName = minioConfig.getBucketName();
        if(expiryTime==null)expiryTime = 30*60;
        minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
        String presignedObjectUrl = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(bucketName).object(objectName).build());
        return presignedObjectUrl;
    }

    /**
     * 查询预览地址或下载地址，永久有效
     * @param bucketName
     * @param objectName
     * @param expiryTime
     * @return
     */
    public String getDownLoadPath(String path){
        if(StrUtil.isBlank(path))return path;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("http://")
                .append(minioConfig.getEndpoint()).append(":")
                .append(minioConfig.getPort()).append(URI_DELIMITER)
                .append(minioConfig.getBucketName()).append(URI_DELIMITER)
                .append(path);
        return stringBuffer.toString();
    }

    /**
     * 复制文件
     * @param source
     * @param target
     */
    public void copy(String source, String target) {

        try {
            minioClient.copyObject(CopyObjectArgs.builder()
                    .bucket(minioConfig.getBucketName())
                    .object(target)
                    .source(CopySource.builder()
                            .bucket(minioConfig.getBucketName())
                            .object(source)
                            .build())
                    .build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除文件
     * @param object
     */
    public void delete (String object) {

        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(minioConfig.getBucketName())
                    .object(object)
                    .build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取访问网关
     * @param path
     * @return
     */
    public String gateway(String path) {
        String gateway = minioConfig.getEndpoint()+"/"+minioConfig.getPort();
        if (!gateway.endsWith(URI_DELIMITER)) {
            gateway += URI_DELIMITER;
        }
        return gateway + path;
    }

    /**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    protected String getSuffix(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            String suffix = fileName.substring(index + 1);
            if (!suffix.isEmpty()) {
                return suffix;
            }
        }
        throw new IllegalArgumentException("非法文件名称："  + fileName);
    }

    /**
     * 获取年月日[2020, 09, 01]
     * @return
     */
    protected String[] getDateFolder() {
        String[] retVal = new String[3];

        LocalDate localDate = LocalDate.now();
        retVal[0] = localDate.getYear() + "";

        int month = localDate.getMonthValue();
        retVal[1] = month < 10 ? "0" + month : month + "";

        int day = localDate.getDayOfMonth();
        retVal[2] = day < 10 ? "0" + day : day + "";

        return retVal;
    }

}
