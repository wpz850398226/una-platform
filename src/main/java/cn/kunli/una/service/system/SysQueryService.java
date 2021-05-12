package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysQueryMapper;
import cn.kunli.una.pojo.system.SysQuery;
import cn.kunli.una.service.BasicService;
import org.springframework.stereotype.Service;

/**
 * (SysParamMap)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-08 16:19:03
 */
@Service
public class SysQueryService extends BasicService<SysQueryMapper, SysQuery> {

    @Override
    public BasicService getThisProxy() {
        return sysQueryService;
    }
}
