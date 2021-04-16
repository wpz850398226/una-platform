package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysDataMapper;
import cn.kunli.una.pojo.system.SysData;
import cn.kunli.una.service.BasicService;
import org.springframework.stereotype.Service;


/**
 * 数据/表单内容/问卷答案(SysData)表服务类
 *
 * @author Ponzio
 * @since 2021-04-02 16:14:23
 */
@Service
public class SysDataService extends BasicService<SysDataMapper, SysData> {

    /*@Override
    public List<SysData> selectBySelective(SysParamMap sysParamMap) {
        Example example = new Example(entityClass);
        if (sysParamMap.entrySet().size() > 0) {
            Example.Criteria criteria = example.createCriteria();
            Iterator var5 = sysParamMap.entrySet().iterator();

            for (Map.Entry<String, Object> entry : sysParamMap.entrySet()) {
                if(entry.getValue()!=null&&!entry.getValue().equals("")){
                    try {
                        Field declaredField = entityClass.getDeclaredField(entry.getKey());
                        if(declaredField!=null)criteria.andEqualTo(entry.getKey(), entry.getValue());
                    } catch (NoSuchFieldException e) {
                        //e.printStackTrace();
                        continue;
                    }

                }
            }
        }

        if (sysParamMap.getOrderBy() != null && !sysParamMap.getOrderBy().trim().isEmpty()) {
            example.setOrderByClause(sysParamMap.getOrderBy());
        }

        List<SysData> list = mapper.selectByExample(example);
        return resultFormat(list);

    }*/

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

    /*@Override
    public List<SysData> resultFormat(List<SysData> list) {
        if (CollectionUtils.isEmpty(list)) return list;
        //声明存放组id，字段名与值 的map{id,{fieldName,fieldValue}}
        Map<String, Map<String,Object>> fieldValueMap = new HashMap<>();
        //声明存放字段赋值编码的map
        Map<Integer, String> fieldMap = new HashMap<>();

        for (SysData sysData : list) {
            //获取字段名
            String fieldAssignmentCode = fieldMap.get(sysData.getFieldId());
            if (StringUtils.isBlank(fieldAssignmentCode)){
                SysField sysField = sysFieldService.selectById(sysData.getFieldId());
                fieldAssignmentCode = sysField.getAssignmentCode();
                fieldMap.put(sysField.getId(), fieldAssignmentCode);
            }

            Map<String, Object> map = fieldValueMap.get(sysData.getGroupId());
            if(map==null)map = new HashMap<>();
            map.put(fieldAssignmentCode,sysData.getValue());
            fieldValueMap.put(sysData.getGroupId(),map);
        }

        List<SysData> resultList = new ArrayList<>();
        for (Map.Entry<String, Map<String, Object>> entry : fieldValueMap.entrySet()) {
            Map<String, Object> resultEntityMap = entry.getValue();
            resultEntityMap.put("id",entry.getKey());
            resultList.add((SysData) new SysData().setMap(resultEntityMap));
        }
        return resultList;
    }*/
}
