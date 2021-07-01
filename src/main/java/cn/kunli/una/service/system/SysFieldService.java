package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysFieldMapper;
import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.pojo.flow.FlowNode;
import cn.kunli.una.pojo.system.*;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.service.flow.FlowDefinitionService;
import cn.kunli.una.service.flow.FlowNodeService;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.StringUtil;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysFieldService extends BasicService<SysFieldMapper, SysField> {

    @Override
    public BasicService getThisProxy() {
        return sysFieldService;
    }
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private FlowDefinitionService flowDefinitionService;
    @Autowired
    private FlowNodeService flowNodeService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRegionService sysRegionService;
    @Autowired
    private SysFileService sysFileService;

    /**
     * 转换存储值为显示值
     * @param assignmentCode    赋值编码
     * @param value 存储至
     * @param bs    源服务
     * @param transformDisplayCode       取值编码
     * @return
     */
    @SneakyThrows
    public SysResult getDisplayValue(String assignmentCode, String value, BasicService bs,String transformDisplayCode) {
        if (StringUtils.isBlank(assignmentCode) || StringUtils.isBlank(value)) return SysResult.fail("查询失败：赋值编码或值为空");
        int length = assignmentCode.length();
        String resultStr = "";
        List<? extends BasePojo> resultList = new ArrayList<>();

        if(StringUtils.isBlank(resultStr)&&CollectionUtils.isEmpty(resultList)&&assignmentCode.length()>=5){
            switch (assignmentCode.substring(length - 5)) {
                case "Dcode"://字典编码
                    resultList = sysDictionaryService.list(sysDictionaryService.getWrapper(MapUtil.getMap("code", value)));
                    break;
            }
        }

        if(StringUtils.isBlank(resultStr)&&CollectionUtils.isEmpty(resultList)&&assignmentCode.length()>=6){
            switch (assignmentCode.substring(length - 6)) {
                case "NodeId"://流程节点 entityId
//                    FlowNode flowNode = flowNodeService.getById(Integer.valueOf(value));
                    resultList = flowNodeService.list(flowNodeService.getWrapper(MapUtil.getMap("id",value)));
                    break;
                case "roleId"://角色id，复数，以逗号分隔
                    resultList = sysRoleService.list(sysRoleService.getWrapper(MapUtil.getMap("in:id", value)));
                    break;
                case "fileId"://流程节点 entityId
                case "FileId"://流程节点 entityId
                    resultList = sysFileService.list(sysFileService.getWrapper(MapUtil.getMap("in:id", value)));
                    break;
            }
        }

        if(StringUtils.isBlank(resultStr)&&CollectionUtils.isEmpty(resultList)&&assignmentCode.length()>=7){
            switch (assignmentCode.substring(length - 7)) {
                case "FieldId"://字段 fieldId
                case "fieldId"://字段 fieldId
                    resultList = sysFieldService.list(sysFieldService.getWrapper(MapUtil.getMap("id",value)));
                    break;
            }
        }

        if(StringUtils.isBlank(resultStr)&&CollectionUtils.isEmpty(resultList)&&assignmentCode.length()>=8){
            switch (assignmentCode.substring(length - 8)) {
                case "EntityId"://实体 entityId
                case "entityId"://实体 entityId
                    resultList = sysEntityService.list(sysEntityService.getWrapper(MapUtil.getMap("id",value)));
                    break;
                case "regionId"://地区 regionId
                    resultList = sysRegionService.list(sysRegionService.getWrapper(MapUtil.getMap("id",value)));
                    break;
            }
        }

        if(StringUtils.isBlank(resultStr)&&CollectionUtils.isEmpty(resultList)){
            switch (assignmentCode) {
                case "accountId"://账号 accountId
                    resultList = sysAccountService.list(sysAccountService.getWrapper(MapUtil.getMap("id",value)));
                    break;
                case "permissionId"://权限 permissionId
                    resultList = sysPermissionService.list(sysPermissionService.getWrapper(MapUtil.getMap("id",value)));
                    break;
                case "definitionId"://权限 permissionId
                    resultList = flowDefinitionService.list(flowDefinitionService.getWrapper(MapUtil.getMap("id",value)));
                    break;
                case "parentId"://父id parentId
                    resultList = bs.list(bs.getWrapper(MapUtil.getMap("id",value)));
                    break;
                default:
                    return SysResult.fail("查询失败：赋值编码 未识别");
            }
        }

        if(StringUtils.isNotBlank(resultStr))return new SysResult().success("查询成功",resultStr);
        if(CollectionUtils.isNotEmpty(resultList)){
            List<String> resultStrList = new ArrayList<>();
            if(StringUtils.isNotBlank(transformDisplayCode)){
                BasePojo result0 = resultList.get(0);
                //如果指定了转换取值编码，则通过反射获取，否则默认取name
                Class<? extends BasePojo> resultClass = result0.getClass();
                Field transformDisplayField = resultClass.getDeclaredField(transformDisplayCode);
                transformDisplayField.setAccessible(true);

                for (BasePojo basePojo : resultList) {
                    //获取转换取值字段值
                    Object transformDisplayValueObj = transformDisplayField.get(basePojo);
                    if(transformDisplayValueObj!=null){
                        resultStrList.add(transformDisplayValueObj.toString());
                    }
                }
            }else{
                for (BasePojo basePojo : resultList) {
                    resultStrList.add(basePojo.getName());
                }
            }

            if(CollectionUtils.isNotEmpty(resultStrList)){
                return new SysResult().success("查询成功",ListUtil.listToStr(resultStrList));
            }
        }
        return SysResult.fail("查询失败：赋值编码 未识别");
    }

    /**
     * 保存实例格式化
     *
     * @param obj
     * @return
     */
    public SysField initialize(SysField obj) {
        super.initialize(obj);
        if (StringUtils.isNotBlank(obj.getRadioOptions())) obj.setRadioOptions(obj.getRadioOptions().replace("，", ","));
        if (obj.getId() == null) {
            if (StringUtils.isBlank(obj.getOptionNameFieldCode())) obj.setOptionNameFieldCode("name");
            if (StringUtils.isBlank(obj.getOptionValueFieldCode())) obj.setOptionValueFieldCode("id");
            if (StringUtils.isBlank(obj.getDqlName()))
                obj.setDqlName("record." + StringUtil.upperCharToUnderLine(obj.getAssignmentCode()));
        }else{
            if(obj.getOptionNameFieldCode()!=null&&obj.getOptionNameFieldCode().equals(""))obj.setOptionNameFieldCode("name");
            if(obj.getOptionValueFieldCode()!=null&&obj.getOptionValueFieldCode().equals(""))obj.setOptionValueFieldCode("id");
        }


        return obj;
    }

    /**
     * 查询结果格式化
     *
     * @param list
     * @return
     */
    public List<SysField> parse(List<SysField> list) {
        super.parse(list);
        for (SysField record : list) {
            List<SysField> children = sysFieldService.list(this.getWrapper(MapUtil.getMap("selectParentId", record.getId())));
            if(CollectionUtils.isNotEmpty(children)){
                List<String> idList = new ArrayList<>();
                for (SysField sysField : children) {
                    idList.add(sysField.getId().toString());
                }

                String idStr = ListUtil.listToStr(idList);
                record.setSelectSubIds(idStr);
            }

            if (StringUtils.isNotBlank(record.getRadioOptions())){
                record.setRadioOptionArray(StringUtils.split(record.getRadioOptions(), ","));
            }

            if(StringUtils.isNotBlank(record.getAssignmentModeDcode())){
                SysDictionary assignmentModeDic = sysDictionaryService.getOne(sysDictionaryService.getWrapper(MapUtil.getMap("code", record.getAssignmentModeDcode())));
                if(assignmentModeDic!=null){
                    record.setAssignmentType(assignmentModeDic.getRemark());
                }
            }

            if(StringUtils.isNotBlank(record.getFormatCheckTypeDcode())){
                SysDictionary codeDic = sysDictionaryService.getOne(sysDictionaryService.getWrapper(MapUtil.getMap("code", record.getFormatCheckTypeDcode())));
                if(codeDic!=null)record.getMap().put("formatCheckTypeDvalue",codeDic.getValue());
            }

            if(record.getIsRequired()){
                if(record.getMap().containsKey("formatCheckTypeDvalue")){
                    record.getMap().put("formatCheckTypeDvalue",record.getMap().get("formatCheckTypeDvalue")+" required");
                }else{
                    record.getMap().put("formatCheckTypeDvalue","required");
                }
            }

            if(record.getOptionEntityId()!=null){
                SysEntity optionEntity = sysEntityService.getById(record.getOptionEntityId());
                if(optionEntity!=null)record.setOptionEntityPath(optionEntity.getPath());
            }
        }
        return list;
    }
}
