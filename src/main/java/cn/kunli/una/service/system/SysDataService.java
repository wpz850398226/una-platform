package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysDataMapper;
import cn.kunli.una.pojo.system.SysData;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.pojo.system.SysSort;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.MapUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 数据/表单内容/问卷答案(SysData)表服务类
 *
 * @author Ponzio
 * @since 2021-04-02 16:14:23
 */
@Service
public class SysDataService extends BasicService<SysDataMapper, SysData> {

    @Autowired
    private SysDataService thisProxy;

    @Override
    public BasicService getThisProxy() {
        return thisProxy;
    }

    @Override
    public Map<String, Object> queryFormat(Map<String, Object> map) {
        if(MapUtils.isEmpty(map))return map;
        SysEntity sysEntity = null;
        if(map.containsKey("entityId")){
            //虚拟实体
            sysEntity = sysEntityService.getById(map.get("entityId").toString());
        }else{
            sysEntity = sysEntityService.getOne(sysEntityService.getWrapper(MapUtil.getMap("code","SysData")));
        }
        if((map.get("orderByAsc")==null|| StringUtils.isBlank(map.get("orderByAsc").toString()))
                &&(map.get("orderByDesc")==null||StringUtils.isBlank(map.get("orderByDesc").toString()))) {
            if(sysEntity!=null) {
                //查询本实体综合排序方法
                List<SysSort> sortList = sysSortService.list(sysSortService.getWrapper(MapUtil.getMap("entityId",sysEntity.getId())));
                //格式化排序条件，转为查询语句，并将语句赋值给查询对象
                if(CollectionUtils.isNotEmpty(sortList)){
                    StringBuffer ascFieldBuffer = new StringBuffer();
                    StringBuffer descFieldBuffer = new StringBuffer();
                    for (SysSort sysSort : sortList) {
                        String assignmentCode = sysFieldService.getById(sysSort.getFieldId()).getAssignmentCode();
                        if(sysSort.getSortord()){
                            ascFieldBuffer.append(",").append(assignmentCode);
                        }else{
                            descFieldBuffer.append(",").append(assignmentCode);
                        }
                    }

                    if(ascFieldBuffer.length()>0){
                        map.put("orderByAsc",ascFieldBuffer.delete(0, 1).toString());
                    }

                    if(descFieldBuffer.length()>0){
                        map.put("orderByDesc",descFieldBuffer.delete(0, 1).toString());
                    }
                }
            }
        }


        return map;
    }

    /*@Override
    public List<SysData> resultFormat(List<SysData> list) {
        if (CollectionUtils.isEmpty(list)) return list;
        for (SysData sysData : list) {
            Map<String, Object> map = new HashMap<>();
            if(sysData.getValue()!=null){
                for (Map.Entry<String, Object> entry : sysData.getValue().entrySet()) {
                    map.put(entry.getKey(),entry.getValue());
                }
                sysData.setMap(map);
            }
        }
        return list;
    }*/
}
