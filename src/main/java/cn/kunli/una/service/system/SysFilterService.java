package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysFilterMapper;
import cn.kunli.una.pojo.system.SysField;
import cn.kunli.una.pojo.system.SysFilter;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * (SysFilter)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-08 16:15:04
 */
@Service
public class SysFilterService extends BaseService<SysFilterMapper, SysFilter> {

    @Override
    public SysResult insertSelective(SysFilter record) {
        SysResult sysResult = super.insertSelective(record);
        if(sysResult.getCode()!=200)return sysResult;
        if(record.getEntityId()!=null){
            sysEntityService.deleteFromCacheByCode(record.getEntityId());
        }
        return sysResult;
    }
}
