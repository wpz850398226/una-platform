package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysSortMapper;
import cn.kunli.una.pojo.system.SysSort;
import cn.kunli.una.service.BasicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysSortService extends BasicService<SysSortMapper, SysSort> {

    @Override
    public BasicService getThisProxy() {
        return sysSortService;
    }
}
