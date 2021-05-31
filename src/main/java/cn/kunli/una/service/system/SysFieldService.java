package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysFieldMapper;
import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.pojo.system.SysField;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.service.flow.FlowDefinitionService;
import cn.kunli.una.service.flow.FlowNodeService;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.common.StringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public SysResult getDisplayValue(String assignmentCode, String value, BasicService bs) {
        if (StringUtils.isBlank(assignmentCode) || StringUtils.isBlank(value)) return SysResult.fail("查询失败：赋值编码或值为空");
        int length = assignmentCode.length();
        BasePojo target = null;
        switch (assignmentCode.substring(length - 5)) {
            case "Dcode"://字典编码
                target = sysDictionaryService.getOne(sysDictionaryService.getWrapper(MapUtil.getMap("code",value)));
                break;
        }
        if (target != null) return new SysResult().success("查询成功", target.getName());

        switch (assignmentCode.substring(length - 6)) {
            case "NodeId"://流程节点 entityId
                target = flowNodeService.getById(Integer.valueOf(value));
                break;
        }
        if (target != null) return new SysResult().success("查询成功", target.getName());

        switch (assignmentCode.substring(length - 7)) {
            case "FieldId"://字段 fieldId
                target = sysFieldService.getById(Integer.valueOf(value));
                break;
            case "fieldId"://字段 fieldId
                target = sysFieldService.getById(Integer.valueOf(value));
                break;
        }
        if (target != null) return new SysResult().success("查询成功", target.getName());

        switch (assignmentCode.substring(length - 8)) {
            case "EntityId"://实体 entityId
                target = sysEntityService.getById(Integer.valueOf(value));
                break;
            case "entityId"://实体 entityId
                target = sysEntityService.getById(Integer.valueOf(value));
                break;
        }
        if (target != null) return new SysResult().success("查询成功", target.getName());

        switch (assignmentCode) {
            case "accountId"://账号 accountId
                target = sysAccountService.getById(Integer.valueOf(value));
                break;
            case "permissionId"://权限 permissionId
                target = sysPermissionService.getById(Integer.valueOf(value));
                break;
            case "definitionId"://权限 permissionId
                target = flowDefinitionService.getById(Integer.valueOf(value));
                break;
            case "parentId"://父id parentId
                target = bs.getById(Integer.valueOf(value));
                break;
            default:
                return SysResult.fail("查询失败：赋值编码 未识别");
        }
        if (target != null) return new SysResult().success("查询成功", target.getName());

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
