package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysImgConfigMapper;
import cn.kunli.una.pojo.system.SysImgConfig;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (SysImgConfig)表服务实现类
 *
 * @author Ponzio
 * @since 2020-06-10 11:26:08
 */
@Service
public class SysImgConfigService extends BasicService<SysImgConfigMapper, SysImgConfig> {

    @Autowired
    private SysImgConfigService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

}
