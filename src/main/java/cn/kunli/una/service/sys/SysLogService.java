package cn.kunli.una.service.sys;

import cn.kunli.una.mapper.SysLogMapper;
import cn.kunli.una.pojo.sys.SysLog;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 日志(SysLog)表服务类
 *
 * @author Ponzio
 * @since 2021-01-15 16:44:55
 */
@Service
public class SysLogService extends BasicService<SysLogMapper, SysLog> {

    @Autowired
    private SysLogService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

}
