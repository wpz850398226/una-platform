package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysButtonMapper;
import cn.kunli.una.pojo.system.SysButton;
import cn.kunli.una.service.BasicService;
import org.springframework.stereotype.Service;

/**
 * (SysButton)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-07 08:09:05
 */
@Service
public class SysButtonService extends BasicService<SysButtonMapper, SysButton> {
    @Override
    public BasicService getThisProxy() {
        return sysButtonService;
    }
}
