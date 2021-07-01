package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysFilterMapper;
import cn.kunli.una.pojo.system.SysField;
import cn.kunli.una.pojo.system.SysFilter;
import cn.kunli.una.service.BasicService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (SysFilter)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-08 16:15:04
 */
@Service
public class SysFilterService extends BasicService<SysFilterMapper, SysFilter> {

    @Override
    public BasicService getThisProxy() {
        return sysFilterService;
    }

    @Override
    public List<SysFilter> parse(List<SysFilter> list) {
        list = super.parse(list);

        for (SysFilter sysFilter : list) {
            SysField sysField = sysFieldService.getById(sysFilter.getFieldId());
            sysFilter.setFieldCode(sysField.getAssignmentCode());
        }

        return list;
    }
}
