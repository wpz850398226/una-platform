package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysCompanyMapper;
import cn.kunli.una.pojo.system.SysCompany;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (SysCompany)表服务实现类
 *
 * @author Ponzio
 * @since 2020-06-03 15:58:32
 */
@Service
public class SysCompanyService extends BasicService<SysCompanyMapper, SysCompany> {
    @Autowired
    private SysCompanyService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

}
