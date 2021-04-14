package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysButtonMapper;
import cn.kunli.una.pojo.system.SysButton;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (SysButton)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-07 08:09:05
 */
@Service
public class SysButtonService extends BaseService<SysButtonMapper, SysButton> {

    @Override
    public SysResult insertSelective(SysButton record) {
        SysResult sysResult = super.insertSelective(record);
        if(sysResult.getCode()!=200)return sysResult;
        if(record.getEntityId()!=null){
            sysEntityService.deleteFromCacheByCode(record.getEntityId());
        }
        return sysResult;
    }
}
