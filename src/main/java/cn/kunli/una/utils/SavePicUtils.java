package cn.kunli.una.utils;

import cn.kunli.una.service.system.SysConfigurationService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

@Component
public class SavePicUtils {

	@Autowired
	private SysConfigurationService sysConfigurationService;


	public static String base64ToPic(String imgStr) throws IOException{
		String picSaveUrl = GetPicUrlUtils.getPicUrl();
		BASE64Decoder decoder = new BASE64Decoder();
		String imgFilePath = "";
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            imgFilePath = UUID.randomUUID()+".jpg";//新生成的图片
            String url = picSaveUrl + imgFilePath;
            OutputStream out = new FileOutputStream(url);
            out.write(b);
            out.flush();
            out.close();
        } catch (Exception e) {
        	e.printStackTrace();
        	return "";
        }
        return imgFilePath;
	}


	public String saveUpload(MultipartFile file, Integer entityId) throws IOException{
		//获取不同操作系统的根目录
		String path = GetPicUrlUtils.getUploadUrlBySystemType(
		        sysConfigurationService.getOne(sysConfigurationService.getWrapper(MapUtil.getMap("code","windowsUploadUrl"))).getValue()
				,sysConfigurationService.getOne(sysConfigurationService.getWrapper(MapUtil.getMap("code","linuxUploadUrl"))).getValue());
		//原始文件名
		String originalFileName = file.getOriginalFilename();
		//新文件名，添加原始文件名后缀
		String newFileName = "/"+entityId+"/"+ TimeUtil.getStrOfDate(new Date())+"/"+ UUID.randomUUID().toString().replaceAll("-","") + originalFileName.substring(originalFileName.lastIndexOf("."));
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
