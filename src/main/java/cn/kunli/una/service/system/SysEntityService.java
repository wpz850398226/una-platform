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

    @Autowired
    private SysRelationService sysRelationService;
    @Autowired
    private SysButtonService sysButtonService;
    @Autowired
    private SysQueryService sysQueryService;
    @Autowired
    private SysFilterService sysFilterService;

    @Override
    public List<SysEntity> resultFormat(List<SysEntity> list) {
        if(CollectionUtils.isEmpty(list))return list;
        list = super.resultFormat(list);

        for (SysEntity sysEntity : list) {
            Map<String, Object> map = MapUtil.getMap("entityId", sysEntity.getId());
            sysEntity.setRelationList(sysRelationService.resultFormat(sysRelationService.list(sysRelationService.getWrapper(map))));
            sysEntity.setButtonList(sysButtonService.list(sysButtonService.getWrapper(map)));
            sysEntity.setQueryList(sysQueryService.list(sysQueryService.getWrapper(map)));
            sysEntity.setFilterList(sysFilterService.list(sysFilterService.getWrapper(map)));
        }

        return list;
    }
}
