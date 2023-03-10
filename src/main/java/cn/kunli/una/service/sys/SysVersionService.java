package cn.kunli.una.service.sys;

import cn.kunli.una.handler.UnaResponseException;
import cn.kunli.una.mapper.SysVersionMapper;
import cn.kunli.una.pojo.sys.SysVersion;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.UnaMapUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.executable.ValidateOnExecution;
import java.util.List;

/**
 * (SysVersion)表服务实现类
 *
 * @author Ponzio
 * @since 2020-07-08 11:41:50
 */
@Service
public class SysVersionService extends BasicService<SysVersionMapper, SysVersion> {

    @Autowired
    private SysVersionService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    //校验格式
    @Override
    @SneakyThrows
    public void saveValidate(SysVersion obj) {

        List<SysVersion> externalVersionObjList = thisProxy.getList(UnaMapUtil.getMap("externalVersion",obj.getExternalVersion()));
        if (externalVersionObjList.size() > 0 && !externalVersionObjList.get(0).getId().equals(obj.getId())) {
            //通过新文件的名称查询到数据
            throw new UnaResponseException("外部版本号重复，保存失败:" + obj.getExternalVersion());
        }

        List<SysVersion> internalVersionObjList = thisProxy.getList(UnaMapUtil.getMap("internalVersion",obj.getInternalVersion()));
        if (internalVersionObjList.size() > 0 && !internalVersionObjList.get(0).getId().equals(obj.getId())) {
            //通过新文件的名称查询到数据
            throw new UnaResponseException("内部版本号重复，保存失败:" + obj.getInternalVersion());
        }
    }
}
