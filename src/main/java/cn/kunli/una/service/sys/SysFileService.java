package cn.kunli.una.service.sys;

import cn.kunli.una.annotation.MyCacheEvict;
import cn.kunli.una.handler.UnaResponseException;
import cn.kunli.una.mapper.SysFileMapper;
import cn.kunli.una.pojo.sys.SysDictionary;
import cn.kunli.una.pojo.sys.SysFile;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.UnaMapUtil;
import cn.kunli.una.utils.common.MinIoUtil;
import cn.kunli.una.utils.common.UserUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
    @Autowired
    private SysFileService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }


    @Override
    @MyCacheEvict(value = "list")
    public SysResult saveRecord(SysFile entity) {
        String path = minIoUtil.putObject(entity.getFile());
        if(StrUtil.isNotBlank(path)){
            entity.setName(path.substring(path.lastIndexOf("/")+1));
            entity.setPath(path);
        }
        return super.saveRecord(entity);
    }

    @Override
    @MyCacheEvict(value = {"list","record:one"})
    @CacheEvict(value = "record:id", keyGenerator = "myCacheKeyGenerator")
//    public boolean deleteById(Serializable id) {
    public boolean deleteById(SysFile entity) {
        SysFile record = thisProxy.getById(entity.getId());
        if(record!=null&&StrUtil.isNotBlank(record.getPath())){
            minIoUtil.delete(record.getPath());
        }
        return super.deleteById(entity);
    }

    /**
     * 校验数据格式
     *
     * @param obj
     * @return
     */
    @Override
    @SneakyThrows
    public void saveValidate(SysFile obj) {
        if (obj.getFile() != null) {
            String fileName = obj.getFile().getOriginalFilename();

            if (fileName.indexOf("jpg") != -1 || fileName.indexOf("png") != -1 || fileName.indexOf("bmp") != -1 || fileName.indexOf("gif") != -1 || fileName.indexOf("jpeg") != -1) {
                if (obj.getFile().getSize() > 2 * 1024 * 1024) {
                    //图片文件大小超过规定上限
                    throw new UnaResponseException("单个图片文件不可超过2M，保存失败:" + obj.getFile().getOriginalFilename());
                }
            }

            SysLoginAccountDetails loginAccount = UserUtil.getLoginAccount();
            List<SysFile> sysFiles = thisProxy.selectList(UnaMapUtil.buildHashMap().put("creatorId", loginAccount.getId())
                    .put("originName", fileName).put("size", obj.getFile().getSize()).build());
            if(CollectionUtils.isNotEmpty(sysFiles)){
                //如果有相同账号，相同源文件名，相同大小的文件，说明是相同文件，不允许重复上传
                throw new UnaResponseException("您已上传过相同文件，本次保存失败:" + obj.getFile().getOriginalFilename());
            }

        }

    }


    //格式化保存实例
    @Override
    public SysFile initialize(SysFile obj) {

        if (obj.getId() == null) {
            String originalFilename = obj.getFile().getOriginalFilename();
            //如果id为空，新增数据
            obj.setOriginName(originalFilename);
            obj.setSize(obj.getFile().getSize());
            obj.setExtension(originalFilename.substring(originalFilename.lastIndexOf(".") + 1));
            obj.setTypeDcode("file_extension_" + obj.getExtension());
        }

        super.initialize(obj);
        return obj;
    }

    @Override
    public List<SysFile> parse(List<SysFile> list) {
        list = super.parse(list);
        if(CollectionUtils.isNotEmpty(list)){
            for (SysFile sysFile : list) {
                if(StrUtil.isNotBlank(sysFile.getTypeDcode())){
                    SysDictionary codeDic = sysDictionaryService.selectOne(UnaMapUtil.getMap("code", sysFile.getTypeDcode()));
                    if(codeDic!=null){
                        sysFile.getMap().put("typeDvalue",codeDic.getValue());
                    }
                }

                if(StrUtil.isNotBlank(sysFile.getPath())){
                    sysFile.setPath(minIoUtil.getDownLoadPath(sysFile.getPath()));
                }
            }
        }

        return list;
    }
}
