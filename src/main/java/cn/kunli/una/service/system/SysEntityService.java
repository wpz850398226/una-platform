package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysEntityMapper;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.MapUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysEntityService extends BasicService<SysEntityMapper, SysEntity> {

    @Override
    public BasicService getThisProxy() {
        return sysEntityService;
    }

    @Override
    public SysEntity initialize(SysEntity obj) {
        obj = super.initialize(obj);

        if(obj.getIsVirtual()==1){
            obj.setPath("/sys/data");
        }

        return obj;
    }

    @Override
    public List<SysEntity> parse(List<SysEntity> list) {
        if(CollectionUtils.isEmpty(list))return list;
        list = super.parse(list);

        for (SysEntity sysEntity : list) {
            Map<String, Object> map = MapUtil.getMap("entityId", sysEntity.getId());
            sysEntity.setRelationList(sysRelationService.parse(sysRelationService.list(sysRelationService.getWrapper(map))));
            sysEntity.setButtonList(sysButtonService.list(sysButtonService.getWrapper(map)));
            sysEntity.setQueryList(sysQueryService.list(sysQueryService.getWrapper(map)));
            sysEntity.setFilterList(sysFilterService.list(sysFilterService.getWrapper(map)));
        }

        return list;
    }
}
