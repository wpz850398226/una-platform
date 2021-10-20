package cn.kunli.una.service.system;

import cn.kunli.una.annotation.MyCacheEvict;
import cn.kunli.una.mapper.SysSortMapper;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.pojo.system.SysField;
import cn.kunli.una.pojo.system.SysSort;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SysSortService extends BasicService<SysSortMapper, SysSort> {

    @Override
    public BasicService getThisProxy() {
        return sysSortService;
    }

    @Override
    @MyCacheEvict(value = "list")
    public SysResult saveRecord(SysSort entity) {
        SysResult sysResult = super.saveRecord(entity);
        if(sysResult.getIsSuccess()){
            //保存成功，清楚所属实体的缓存
            SysEntity sysEntity = sysEntityService.getById(entity.getEntityId());
            Set<String> strings = redisUtil.hasKeys("list::" + sysEntity.getCode() + "Service:");
            if(CollectionUtils.isNotEmpty(strings))redisUtil.delKeys(strings);
        }

        return sysResult;
    }

    @Override
    public List<SysSort> parse(List<SysSort> list) {
        list = super.parse(list);

        if(CollectionUtils.isNotEmpty(list)){
            for (SysSort sysSort : list) {
                if(sysSort.getFieldId()!=null){
                    SysField sysField = sysFieldService.getById(sysSort.getFieldId());
                    String assignmentCode = sysField.getAssignmentCode();
                    if(assignmentCode.equals("sortOrder")){
                        sysSort.setIsSortField(true);
                    }
                }
            }
        }

        return list;

    }
}
