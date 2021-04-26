package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysRelationMapper;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.pojo.system.SysField;
import cn.kunli.una.pojo.system.SysRelation;
import cn.kunli.una.service.BasicService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (SysRelation)表服务实现类
 *
 * @author Ponzio
 * @since 2020-05-08 14:37:22
 */
@Service
public class SysRelationService extends BasicService<SysRelationMapper, SysRelation> {

    @Override
    public List<SysRelation> resultFormat(List<SysRelation> list) {
        if(CollectionUtils.isEmpty(list))return list;
        list = super.resultFormat(list);
        for (SysRelation sysRelation : list) {
            sysRelation.setRelatedFieldCode(sysFieldService.getById(sysRelation.getRelatedFieldId()).getAssignmentCode());
            SysEntity parentEntity = sysEntityService.getById(sysRelation.getParentEntityId());
            sysRelation.setParentEntityName(parentEntity.getName()).setParentEntityPath(parentEntity.getPath());

            SysField parentDataField = sysFieldService.getById(sysRelation.getParentDataFieldId());
            if(parentDataField!=null)sysRelation.setParentDataFieldCode(parentDataField.getAssignmentCode());
        }
        return list;
    }
}
