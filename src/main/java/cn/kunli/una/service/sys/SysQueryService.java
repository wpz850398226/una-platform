package cn.kunli.una.service.sys;

import cn.kunli.una.mapper.SysQueryMapper;
import cn.kunli.una.pojo.sys.SysEntity;
import cn.kunli.una.pojo.sys.SysField;
import cn.kunli.una.pojo.sys.SysQuery;
import cn.kunli.una.pojo.vo.SysResult;
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
            if(sysQuery.getOptionEntityId()!=null){
                SysEntity sysEntity = sysEntityService.getById(sysQuery.getOptionEntityId());
                if(sysEntity!=null)sysQuery.setOptionEntityPath(sysEntity.getPath());
            }
        }

        return list;
    }

    @Override
    public SysResult afterSaveSuccess(SysQuery obj) {
        //移除 实体类 缓存
        redisUtil.removeByEntityCode("SysEntity");
        return super.afterSaveSuccess(obj);
    }
}
