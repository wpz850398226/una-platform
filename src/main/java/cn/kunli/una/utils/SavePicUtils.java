package cn.kunli.una.utils;

import cn.kunli.una.service.sys.SysConfigurationService;
import cn.kunli.una.utils.common.DateUtil;
import cn.kunli.una.utils.common.UnaMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Component
public class SavePicUtils {

	@Autowired
	private SysConfigurationService sysConfigurationService;


	public String saveUpload(MultipartFile file, Integer entityId) throws IOException{
		//获取不同操作系统的根目录
		String path = GetPicUrlUtils.getUploadUrlBySystemType(
		        sysConfigurationService.selectOne(UnaMapUtil.getMap("code","windowsUploadUrl")).getValue()
				,sysConfigurationService.selectOne(UnaMapUtil.getMap("code","linuxUploadUrl")).getValue());
		//原始文件名
		String originalFileName = file.getOriginalFilename();
		//新文件名，添加原始文件名后缀
		String newFileName = "/"+entityId+"/"+ DateUtil.getStrOfDate(new Date())+"/"+ UUID.randomUUID().toString().replaceAll("-","") + originalFileName.substring(originalFileName.lastIndexOf("."));
		//创建新文件，路径为：图片上传路径+实体id+当天日期字符串+新文件名
		File newFile = new File(path + newFileName);
		//将内存中的数据写入磁盘
        File fileParent = newFile.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
		file.transferTo(newFile);
		//返回路径
		return newFileName;
	}
}
