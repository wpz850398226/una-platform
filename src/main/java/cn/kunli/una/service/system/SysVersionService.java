package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysVersionMapper;
import cn.kunli.una.pojo.system.SysVersion;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (SysVersion)表服务实现类
 *
 * @author Ponzio
 * @since 2020-07-08 11:41:50
 */
@Service
public class SysVersionService extends BaseService<SysVersionMapper, SysVersion> {

    //校验格式
    public SysResult validation(SysVersion obj) {

        List<SysVersion> externalVersionObjList = this.select((SysVersion) new SysVersion().setExternalVersion(obj.getExternalVersion()).setIsDelete(0));
        if (externalVersionObjList.size() > 0 && !externalVersionObjList.get(0).getId().equals(obj.getId())) {
            //通过新文件的名称查询到数据
            return SysResult.fail("外部版本号重复，保存失败:" + obj.getExternalVersion());
        }

        List<SysVersion> internalVersionObjList = this.select((SysVersion) new SysVersion().setInternalVersion(obj.getInternalVersion()).setIsDelete(0));
        if (internalVersionObjList.size() > 0 && !internalVersionObjList.get(0).getId().equals(obj.getId())) {
            //通过新文件的名称查询到数据
            return SysResult.fail("内部版本号重复，保存失败:" + obj.getInternalVersion());
        }

        //如果通过全部格式验证，则设置code=0，表示通过验证；
        return SysResult.success();

    }
}
