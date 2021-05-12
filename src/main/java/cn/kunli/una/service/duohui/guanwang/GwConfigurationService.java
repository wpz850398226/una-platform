package cn.kunli.una.service.duohui.guanwang;

import cn.kunli.una.mapper.GwConfigurationMapper;
import cn.kunli.una.pojo.duohui.guanwang.GwConfiguration;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 配置(GwConfiguration)表服务类
 *
 * @author Ponzio
 * @since 2021-03-26 11:26:06
 */
@Service
public class GwConfigurationService extends BasicService<GwConfigurationMapper, GwConfiguration> {
    @Autowired
    private GwConfigurationService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
