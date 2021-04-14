package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysSortMapper;
import cn.kunli.una.pojo.system.SysSort;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysSortService extends BaseService<SysSortMapper, SysSort> {

    @Override
    public SysResult insertSelective(SysSort record) {
        SysResult sysResult = super.insertSelective(record);
        if(sysResult.getCode()!=200)return sysResult;
        if(record.getEntityId()!=null){
            sysEntityService.deleteFromCacheByCode(record.getEntityId());
        }
        return sysResult;
    }
}
