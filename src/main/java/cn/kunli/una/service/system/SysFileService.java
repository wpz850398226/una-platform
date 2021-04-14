package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysFileMapper;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.pojo.system.SysFile;
import cn.kunli.una.pojo.vo.SysParamMap;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BaseService;
import cn.kunli.una.utils.SavePicUtils;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.ThumbnailatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * (SysFile)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-16 22:04:52
 */
@Slf4j
@Service
public class SysFileService extends BaseService<SysFileMapper, SysFile> {

    @Autowired
    private SavePicUtils savePicUtils;
    @Autowired
    private SysDictionaryService sysDictionaryService;

    /**
     * 插入数据,只操作record中的非空属性
     *
     * @param record
     * @return
     */
    public SysResult insertSelective(SysFile record) {
        String imgExpande = ".jpg .JPG .png .PNG .bmp .BMP";
        SysResult validationResult = this.validation(record);
        if (validationResult.getCode() != 200) return validationResult;
        if (record.getFileArray() != null) {
            List<String> urlList = new ArrayList<>();
            for (MultipartFile multipartFile : record.getFileArray()) {
                record.setId(null);
                record.setFile(multipartFile);
                int insertNum = this.mapper.insertSelective(this.saveFormat(record));
                if (insertNum <= 0) return SysResult.fail(ListUtil.listToStr(urlList));
                urlList.add(record.getFileUrl());

                //------------------    图片生成缩略图 start   --------------------------
                String filePath = record.getFileUrl();
                int i = filePath.indexOf(".");
                //如果是文件
                if (i > 0) {
                    String expandedName = filePath.substring(i);
                    if (imgExpande.indexOf(expandedName) >= 0) {
                        //如果是图片生成缩略图
                        try {
                            filePath = filePath.replaceAll("/file", "D:/una/fileUpload");
                            String purposeImg = filePath.replaceAll("fileUpload", "fileUpload/thumbnailFile");

                            ThumbnailatorUtil.generateFixedSizeImage(filePath, purposeImg);
                        } catch (Exception e) {
                            log.error("生成缩略图失败 " + filePath);
                        }
                    }
                }
                //------------------    图片生成缩略图 end   --------------------------
            }
            return new SysResult().success("保存成功", ListUtil.listToStr(urlList));
        } else if (record.getFile() != null) {
            int insertNum = this.mapper.insertSelective(this.saveFormat(record));
            if (insertNum > 0) {
                //------------------    图片生成缩略图 start   --------------------------
                String filePath = record.getFileUrl();
                int i = filePath.indexOf(".");
                //如果是文件
                if (i > 0) {
                    String expandedName = filePath.substring(i);
                    if (imgExpande.indexOf(expandedName) >= 0) {
                        //如果是图片生成缩略图
                        try {
                            filePath = filePath.replaceAll("/file", "D:/una/fileUpload");
                            String purposeImg = filePath.replaceAll("fileUpload", "fileUpload/thumbnailFile");

                            ThumbnailatorUtil.generateFixedSizeImage(filePath, purposeImg);
                        } catch (Exception e) {
                            log.error("生成缩略图失败 " + filePath);
                        }
                    }
                }
                //------------------    图片生成缩略图 end   --------------------------
                return new SysResult().success("保存成功", record.getFileUrl());
            }
        }
        return SysResult.fail();
    }

    /**
     * 校验数据格式
     *
     * @param obj
     * @return
     */
    @Override
    public SysResult validation(SysFile obj) {
        if (obj.getFile() != null) {
            String fileName = obj.getFile().getOriginalFilename();
            //去掉文件重复上传限制
			/*List<SysFile> objList = objMapper.select((SysFile) new SysFile().setOriginalTitle(obj.getFile().getOriginalFilename()).setSize(obj.getFile().getSize()).setIsDelete(0));
			if(objList.size()>0&&!objList.get(0).getId().equals(obj.getId())) {
				//通过新文件的源文件名称和大小查询到数据
				return SysResult.fail("文件不可重复上传",fileName);
			}*/

            if (fileName.indexOf("jpg") != -1 || fileName.indexOf("png") != -1 || fileName.indexOf("bmp") != -1 || fileName.indexOf("gif") != -1 || fileName.indexOf("jpeg") != -1) {
                if (obj.getFile().getSize() > 2 * 1024 * 1024) {
                    //图片文件大小超过规定上限
                    return SysResult.fail("单个图片文件不可超过2M，保存失败:" + obj.getFile().getOriginalFilename());
                }
            }

        }

        //如果通过全部格式验证，则设置code=200，表示通过验证；
        return SysResult.success();
    }


    //格式化保存实例
    @Override
    public SysFile saveFormat(SysFile obj) {

        if (obj.getId() == null || obj.getId().equals("")) {
            //如果id为空，新增数据
            obj.setOriginalTitle(obj.getFile().getOriginalFilename());
            obj.setSize(obj.getFile().getSize());
        }

        obj.setExtension(obj.getFile().getOriginalFilename().substring(obj.getFile().getOriginalFilename().lastIndexOf(".") + 1));

        try {
            //保存文件
            String fileUrl = savePicUtils.saveUpload(obj.getFile(), obj.getEntityId());
            //文件展示路径
            String url = "/file" + fileUrl;
            //原始文件名
            obj.setFileUrl(url);
            obj.setName(url.substring(url.lastIndexOf("/") + 1));

            List<SysDictionary> fileTypeList = sysDictionaryService.selectBySelective(SysParamMap.MapBuilder.aMap().put("parentName", "扩展名").put("code", obj.getExtension()).build());
            if (fileTypeList != null && fileTypeList.size() > 0) {
                obj.setTypeDcode(fileTypeList.get(0).getCode());
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        super.saveFormat(obj);
        return obj;
    }

}
