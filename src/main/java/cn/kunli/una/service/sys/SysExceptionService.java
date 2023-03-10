package cn.kunli.una.service.sys;

import cn.kunli.una.mapper.SysExceptionMapper;
import cn.kunli.una.pojo.sys.SysException;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (SysException)表服务实现类
 *
 * @author Ponzio
 * @since 2020-07-21 11:04:55
 */
@Service
public class SysExceptionService extends BasicService<SysExceptionMapper, SysException> {

    @Autowired
    private SysExceptionService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    @Override
    public void saveValidate(SysException obj) {}
}
