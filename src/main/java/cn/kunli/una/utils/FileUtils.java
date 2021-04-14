package cn.kunli.una.utils;

import cn.kunli.una.pojo.vo.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileUtils {

    /**
     * 处理上传文件
     *
     * @param files
     * @param
     * @return
     */
    public static List<String> uploadFiles(List<MultipartFile> files, String folderName) {
        List<String> pictures = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if (file.isEmpty()) {
                //文件为空
                continue;
            }
            String fileName = file.getOriginalFilename();
            // 上传基础路径+类型路径+时间戳
            String filetypeName = files.get(i).getOriginalFilename();

            String newFilePath = folderName + File.separator +
                    new Date().getTime() +
                    File.separator + fileName;
            File dest = new File(Constant.UPLOAD_FILE_PATH + newFilePath);
            try {
                dest.mkdirs();
                file.transferTo(dest);
            } catch (IOException e) {
            }
            pictures.add(newFilePath);
        }
        return pictures;
    }

    public static FileEntity file2FileEntity(String baseDir, String moduleName, String fileName
            , String virtualPath) throws FileNotFoundException {

        //获取当前日期
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(Date.from(Instant.now()));
        //生成文件目录
        File file = new File(baseDir + moduleName + "\\" + currentDate);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(file.getPath() + "\\" + fileName);
        FileOutputStream outputStream = new FileOutputStream(file);
        String url = null;
        FileEntity fileEntity = null;
        if (!StringUtils.isEmpty(virtualPath)) {
            url = file.getPath().replace(baseDir, "");
            fileEntity = new FileEntity(virtualPath + "/" + url.replace("\\", "/"), outputStream);
        } else {
            url = file.getPath();
            fileEntity = new FileEntity(url, outputStream);
        }
        return fileEntity;
    }

    @Data
    @AllArgsConstructor
    public static class FileEntity {
        String url;
        OutputStream stream;
    }

}
