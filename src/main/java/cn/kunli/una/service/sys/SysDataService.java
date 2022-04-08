package cn.kunli.una.service.sys;

import cn.kunli.una.mapper.SysDataMapper;
import cn.kunli.una.pojo.sys.SysData;
import cn.kunli.una.pojo.sys.SysEntity;
import cn.kunli.una.pojo.sys.SysField;
import cn.kunli.una.pojo.sys.SysSort;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.UnaMapUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public Map<String, Object> format(Map<String, Object> map) {
        if(MapUtils.isEmpty(map))return map;
        SysEntity sysEntity = null;
        if(map.containsKey("entityId")){
            //虚拟实体
            sysEntity = sysEntityService.getById(map.get("entityId").toString());
        }else{
            sysEntity = sysEntityService.selectOne(UnaMapUtil.getMap("code","SysData"));
        }
        if((map.get("orderByAsc")==null|| StrUtil.isBlank(map.get("orderByAsc").toString()))
                &&(map.get("orderByDesc")==null||StrUtil.isBlank(map.get("orderByDesc").toString()))) {
            if(sysEntity!=null) {
                //查询本实体综合排序方法
                List<SysSort> sortList = sysSortService.selectList(UnaMapUtil.getMap("entityId",sysEntity.getId()));
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

    @Override
    public List<SysData> parse(List<SysData> list) {
        if(CollectionUtils.isEmpty(list))return list;
        for (SysData sysData : list) {
            if(sysData.getEntityId()!=null){
                SysEntity sysEntity = sysEntityService.getById(sysData.getEntityId());
                if(sysEntity!=null){
                    Map<String, Object> map = sysData.getMap();
                    if(map==null)map = new HashMap<>();
                    map.put("entityName", sysEntity.getName());
                    sysData.setMap(map);
                }
            }
        }


        SysData sysData0 = list.get(0);

        if(sysData0.getEntityId()!=null){
            //虚拟实体，查询虚拟实体类，而不是sysData类
            Integer entityId = sysData0.getEntityId();
            List<SysField> fieldList = sysFieldService.selectList(UnaMapUtil.getMap("entityId",entityId));

            if(CollectionUtils.isNotEmpty(fieldList)){
                //遍历该实体类的所有字段
                for (SysField sysField : fieldList) {
                    if(StrUtil.isNotBlank(sysField.getAssignmentCode())&&StrUtil.isNotBlank(sysField.getDisplayCode())
                            &&!sysField.getAssignmentCode().equals(sysField.getDisplayCode())){
                        for (SysData sysData : list) {
                            //获取记录中赋字段的值
                            String displayValue = "";
                            if(sysData.getValue()!=null){
                                Object value = sysData.getValue().get(sysField.getAssignmentCode());
                                if(null != value){
                                    //实体中该字段值为空的
                                    SysResult displayResult = sysFieldService.getDisplayValue(sysField.getAssignmentCode(), value.toString(),thisProxy,sysField.getTransformDisplayCode());
                                    if(sysData.getMap()==null)sysData.setMap(new HashMap<>());
                                    if(displayResult.getIsSuccess()){
                                        displayValue = displayResult.getData().toString();
//                                    sysData.getValue().put(sysField.getDisplayCode(),displayValue);
                                        sysData.getMap().put(sysField.getDisplayCode(), displayValue);
                                    }else{
                                        sysData.getMap().put(sysField.getDisplayCode(), displayResult.getMessage());
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }

        return list;
    }
}
