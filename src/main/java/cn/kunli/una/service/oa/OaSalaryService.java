package cn.kunli.una.service.oa;

import cn.kunli.una.pojo.oa.OaSalary;
import cn.kunli.una.mapper.OaSalaryMapper;
import cn.kunli.una.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 办公-薪资(OaSalary)表服务类
 *
 * @author Ponzio
 * @since 2021-06-26 09:42:24
 */
@Service
public class OaSalaryService extends BasicService<OaSalaryMapper, OaSalary> {
    @Autowired
    private OaSalaryService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }
}
