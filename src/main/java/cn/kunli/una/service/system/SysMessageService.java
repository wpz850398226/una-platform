package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysMessageMapper;
import cn.kunli.una.pojo.system.SysMessage;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (SysMessage)表服务实现类
 *
 * @author Ponzio
 * @since 2020-06-05 11:38:30
 */
@Service
public class SysMessageService extends BasicService<SysMessageMapper, SysMessage> {

    @Autowired
    private SysMessageService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

}
