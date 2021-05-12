package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysConfigurationMapper;
import cn.kunli.una.pojo.system.SysConfiguration;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (SysConfiguration)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-07 08:53:37
 */
@Service
public class SysConfigurationService extends BasicService<SysConfigurationMapper, SysConfiguration> {

    @Autowired
    private SysConfigurationService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

}
