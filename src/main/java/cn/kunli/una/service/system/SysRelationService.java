package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysRelationMapper;
import cn.kunli.una.pojo.system.SysFilter;
import cn.kunli.una.pojo.system.SysRelation;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * (SysRelation)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-08 14:37:22
 */
@Service
public class SysRelationService extends BaseService<SysRelationMapper, SysRelation> {

    @Override
    public SysResult insertSelective(SysRelation record) {
        SysResult sysResult = super.insertSelective(record);
        if(sysResult.getCode()!=200)return sysResult;
        if(record.getSubEntityId()!=null){
            sysEntityService.deleteFromCacheByCode(record.getSubEntityId());
        }
        return sysResult;
    }
}
