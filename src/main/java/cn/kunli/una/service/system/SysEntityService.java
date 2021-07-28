package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysEntityMapper;
import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.pojo.system.SysFilter;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.ListUtil;
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
    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    public SysEntity initialize(SysEntity obj) {
        obj = super.initialize(obj);

        if(obj.getIsVirtual()){
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
            sysEntity.setRelationList(sysRelationService.parse(sysRelationService.selectList(map)));
            sysEntity.setButtonList(sysButtonService.parse(sysButtonService.selectList(map)));
            sysEntity.setQueryList(sysQueryService.parse(sysQueryService.selectList(map)));
            sysEntity.setPermissionList(sysPermissionService.selectList(MapUtil.getMap("entityId",sysEntity.getId())));
            List<SysFilter> filterList = sysFilterService.parse(sysFilterService.selectList(map));
            if(CollectionUtils.isNotEmpty(filterList)){
                List<SysFilter> newFilterList = ListUtil.getList((SysFilter)new SysFilter().setName("全部"));
                newFilterList.addAll(filterList);
                sysEntity.setFilterList(newFilterList);
            }

        }

        return list;
    }
}
