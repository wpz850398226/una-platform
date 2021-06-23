package cn.kunli.una.service.system;

import cn.kunli.una.pojo.chanpin.CpSpecification;
import cn.kunli.una.mapper.CpSpecificationMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商城-规格表(CpSpecification)表服务类
 *
 * @author Ponzio
 * @since 2021-06-23 23:40:04
 */
@Service
public class CpSpecificationService extends BasicService<CpSpecificationMapper, CpSpecification> {
    @Autowired
    private CpSpecificationService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
