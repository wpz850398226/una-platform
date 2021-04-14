package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysQueryMapper;
import cn.kunli.una.pojo.system.SysFilter;
import cn.kunli.una.pojo.system.SysQuery;
import cn.kunli.una.pojo.system.SysRelation;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * (SysParamMap)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-08 16:19:03
 */
@Service
public class SysQueryService extends BaseService<SysQueryMapper, SysQuery> {

    @Override
    public SysResult insertSelective(SysQuery record) {
        SysResult sysResult = super.insertSelective(record);
        if(sysResult.getCode()!=200)return sysResult;
        if(record.getEntityId()!=null){
            sysEntityService.deleteFromCacheByCode(record.getEntityId());
        }
        return sysResult;
    }
}
