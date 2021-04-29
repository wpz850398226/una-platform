package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysFileMapper;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.pojo.system.SysFile;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.MinIoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * (SysFile)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-16 22:04:52
 */
@Slf4j
@Service
public class SysFileService extends BasicService<SysFileMapper, SysFile> {

    @Autowired
    private MinIoUtil minIoUtil;


    @Override
    public boolean save(SysFile entity) {
        String path = minIoUtil.putObject(entity.getFile());
        if(StringUtils.isNotBlank(path)){
            entity.setName(path.substring(path.lastIndexOf("/")+1));
            entity.setPath(path);
        }
        return super.save(entity);
    }

    @Override
    public boolean deleteById(Serializable id) {
        SysFile record = this.getById(id);
        if(record!=null){
            minIoUtil.delete(record.getPath());
        }
        return super.deleteById(id);
    }

    /**
     * 校验数据格式
     *
     * @param obj
     * @return
     */
    @Override
    public SysResult validation(SysFile obj) {
        if(obj.getId()==null){
            if(obj.getFile()==null){
                return SysResult.fail("保存失败:文件不能为空");
            }
        }
        if (obj.getFile() != null) {
            String fileName = obj.getFile().getOriginalFilename();

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

        if (obj.getId() == null) {
            String originalFilename = obj.getFile().getOriginalFilename();
            //如果id为空，新增数据
            obj.setOriginName(originalFilename);
            obj.setSize(obj.getFile().getSize());
            obj.setExtension(originalFilename.substring(originalFilename.lastIndexOf(".") + 1));
            obj.setTypeDcode("platform_file_extension_" + obj.getExtension());
        }

        super.saveFormat(obj);
        return obj;
    }

    @Override
    public List<SysFile> resultFormat(List<SysFile> list) {
        list = super.resultFormat(list);
        if(CollectionUtils.isNotEmpty(list)){
            for (SysFile sysFile : list) {
                if(StringUtils.isNotBlank(sysFile.getTypeDcode())){
                    SysDictionary codeDic = sysDictionaryService.getOne(sysDictionaryService.getWrapper(MapUtil.getMap("code", sysFile.getTypeDcode())));
                    if(codeDic!=null){
                        sysFile.getMap().put("typeDvalue",codeDic.getValue());
                    }
                }

                if(StringUtils.isNotBlank(sysFile.getPath())){
                    sysFile.setPath(minIoUtil.getDownLoadPath(sysFile.getPath()));
                }
            }
        }

        return list;
    }
}
