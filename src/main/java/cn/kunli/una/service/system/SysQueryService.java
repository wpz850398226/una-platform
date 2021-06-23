package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysQueryMapper;
import cn.kunli.una.pojo.system.SysField;
import cn.kunli.una.pojo.system.SysQuery;
import cn.kunli.una.service.BasicService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<SysQuery> parse(List<SysQuery> list) {
        list = super.parse(list);
        for (SysQuery sysQuery : list) {
            if(sysQuery.getFieldId()!=null){
                SysField sysField = sysFieldService.getById(sysQuery.getFieldId());
                sysQuery.setFieldCode(sysField.getAssignmentCode());
            }
        }

        return list;
    }
}
