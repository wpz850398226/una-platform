package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysDepartmentMapper;
import cn.kunli.una.pojo.system.SysDepartment;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (SysDepartment)表服务实现类
 *
 * @author Ponzio
 * @since 2020-06-05 11:36:05
 */
@Service
public class SysDepartmentService extends BasicService<SysDepartmentMapper, SysDepartment> {

    @Autowired
    private SysDepartmentService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

}
