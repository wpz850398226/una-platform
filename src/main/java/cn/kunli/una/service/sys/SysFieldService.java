package cn.kunli.una.service.sys;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.math.MathUtil;
import cn.hutool.core.util.StrUtil;
import cn.kunli.una.handler.UnaResponseException;
import cn.kunli.una.mapper.SysFieldMapper;
import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.pojo.sys.SysDictionary;
import cn.kunli.una.pojo.sys.SysEntity;
import cn.kunli.una.pojo.sys.SysField;
import cn.kunli.una.pojo.sys.SysPermission;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.service.flow.FlowDefinitionService;
import cn.kunli.una.service.flow.FlowInstanceService;
import cn.kunli.una.service.flow.FlowNodeService;
import cn.kunli.una.service.geneticAlgorithm.GaGeneService;
import cn.kunli.una.service.td.TdEtymologyService;
import cn.kunli.una.utils.common.UnaListUtil;
import cn.kunli.una.utils.common.UnaMapUtil;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private FlowInstanceService flowInstanceService;
    @Autowired
    private FlowNodeService flowNodeService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRegionService sysRegionService;
    @Autowired
    private SysFileService sysFileService;

    @Autowired
    private SysDepartmentService sysDepartmentService;
    @Autowired
    private GaGeneService gaGeneService;

    @Autowired
    private TdEtymologyService tdEtymologyService;

    /**
     * ???????????????????????????
     * @param assignmentCode    ????????????
     * @param value ?????????
     * @param bs    ?????????
     * @param transformDisplayCode       ????????????
     * @return
     */
    @SneakyThrows
    public SysResult getDisplayValue(String assignmentCode, String value, BasicService bs,String transformDisplayCode) {
        if (StrUtil.isBlank(assignmentCode) || StrUtil.isBlank(value)) return SysResult.fail("???????????????????????????????????????");
        int length = assignmentCode.length();
        List<? extends BasePojo> resultList = null;

        if(assignmentCode.length()>=5){
            switch (assignmentCode.substring(length - 5)) {
                case "Dcode"://????????????
                    resultList = sysDictionaryService.selectList(UnaMapUtil.getMap("code", value));
                    break;
            }
        }

        if(resultList==null&&assignmentCode.length()>=6){
            switch (assignmentCode.substring(length - 6)) {
                case "nodeId"://???????????? entityId
                case "NodeId"://???????????? entityId
//                    FlowNode flowNode = flowNodeService.getById(Integer.valueOf(value));
                    resultList = flowNodeService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "roleId"://??????id???????????????????????????
                case "RoleId"://??????id???????????????????????????
                    resultList = sysRoleService.selectList(UnaMapUtil.getMap("in:id", value));
                    break;
                case "fileId"://???????????? entityId
                case "FileId"://???????????? entityId
                    resultList = sysFileService.parse(sysFileService.selectList(UnaMapUtil.getMap("id", value)));
                    break;
                case "shopId"://?????? companyId
                case "ShopId"://?????? companyId
                    resultList = sysCompanyService.selectList(UnaMapUtil.getMap("id", value));
                    break;
                case "Dcodes"://????????????
                    resultList = sysDictionaryService.selectList(UnaMapUtil.getMap("in:code", value));
                    break;
                case "geneId"://????????????
                case "GeneId"://????????????
                    resultList = gaGeneService.selectList(UnaMapUtil.getMap("id", value));
                    break;
            }
        }

        if(resultList==null&&assignmentCode.length()>=7){
            switch (assignmentCode.substring(length - 7)) {
                case "FieldId"://?????? fieldId
                case "fieldId"://?????? fieldId
                    resultList = sysFieldService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "fileIds"://???????????? entityId
                case "FileIds"://???????????? entityId
                    resultList = sysFileService.parse(sysFileService.selectList(UnaMapUtil.getMap("in:id", value)));
                    break;
            }
        }

        if(resultList==null&&assignmentCode.length()>=8){
            switch (assignmentCode.substring(length - 8)) {
                case "EntityId"://?????? entityId
                case "entityId"://?????? entityId
                    resultList = sysEntityService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "regionId"://?????? regionId
                case "RegionId"://?????? regionId
                    resultList = sysRegionService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "originId"://?????? originId
                case "OriginId"://?????? originId
                    resultList = tdEtymologyService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "fieldIds"://??????
                case "FieldIds"://??????
                    resultList = sysFieldService.selectList(UnaMapUtil.getMap("in:id", value));
                    break;
            }
        }

        if(resultList==null&&assignmentCode.length()>=9){
            switch (assignmentCode.substring(length - 9)) {
                case "companyId"://?????? companyId
                case "CompanyId"://?????? companyId
                    resultList = sysCompanyService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "artmentId"://?????? departmentId
                    resultList = sysDepartmentService.selectList(UnaMapUtil.getMap("id",value));
                    break;
//                case "ProjectId"://??????
//                case "projectId":
//                    resultList = bidProjectService.selectList(UnaMapUtil.getMap("id",value));
//                    break;
                case "accountId"://?????? accountId
                case "AccountId"://?????? accountId
                    resultList = sysAccountService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "regionIds"://?????? regionId
                case "RegionIds"://?????? regionId
                    resultList = sysRegionService.selectList(UnaMapUtil.getMap("in:id", value));
                    break;
            }
        }

        if(resultList==null&&assignmentCode.length()>=10){
            switch (assignmentCode.substring(length - 10)) {
                case "companyIds"://?????? companyId
                case "CompanyIds"://?????? companyId
                    resultList = sysCompanyService.selectList(UnaMapUtil.getMap("in:id",value));
                    break;
                case "accountIds"://?????? accountIds
                case "AccountIds"://?????? accountIds
                    resultList = sysAccountService.selectList(UnaMapUtil.getMap("in:id",value));
                    break;
            }
        }

        if(resultList==null){
            switch (assignmentCode) {
                case "permissionId"://?????? permissionId
                    resultList = sysPermissionService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "definitionId"://?????? permissionId
                    resultList = flowDefinitionService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "instanceId"://?????? permissionId
                    resultList = flowInstanceService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "parentId"://???id parentId
                    resultList = bs.getList(UnaMapUtil.getMap("id",value));
                    break;
                default:
                    return SysResult.fail("??????????????????????????? ?????????");
            }
        }

        if(CollectionUtils.isNotEmpty(resultList)){
            List<String> resultStrList = new ArrayList<>();
            if(StrUtil.isNotBlank(transformDisplayCode)){
                BasePojo result0 = resultList.get(0);
                //???????????????????????????????????????????????????????????????????????????name
                Class<? extends BasePojo> resultClass = result0.getClass();
                Field transformDisplayField = resultClass.getDeclaredField(transformDisplayCode);
                transformDisplayField.setAccessible(true);

                for (BasePojo basePojo : resultList) {
                    //???????????????????????????
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
                return new SysResult().success("????????????", UnaListUtil.listToStr(resultStrList));
            }
        }
        return SysResult.fail("???????????????");
    }

    /**
     * ???????????????????????????
     * @param displayCode    ??????????????????
     * @param value ?????????
     * @param bs    ?????????
     * @param transformDisplayCode       ????????????
     * @return
     */
    @SneakyThrows
    public SysResult getAssignmentValue(String displayCode, String value, BasicService bs,String transformDisplayCode) {
        if (StrUtil.isBlank(displayCode) || StrUtil.isBlank(value)) return SysResult.fail("???????????????????????????????????????");
        int length = displayCode.length();
        List<? extends BasePojo> resultList = null;

        if(displayCode.length()>=5){
            switch (displayCode.substring(length - 5)) {
                case "Dname"://????????????
                    resultList = sysDictionaryService.selectList(UnaMapUtil.getMap(":name", value));
                    transformDisplayCode = "code";
                    break;
            }
        }

        if(resultList==null&&displayCode.length()>=6){
            switch (displayCode.substring(length - 6)) {
                case "Dnames"://????????????
                    resultList = sysDictionaryService.selectList(UnaMapUtil.getMap(":name", value));
                    transformDisplayCode = "code";
                    break;
            }
        }

        /*if(resultList==null&&displayCode.length()>=7){
            switch (displayCode.substring(length - 7)) {
                case "fileUrl"://???????????? entityId
                case "FileUrl"://???????????? entityId
                    resultList = sysFileService.parse(sysFileService.selectList(MapUtil.getMap("path", value)));
                    break;
            }
        }*/

        if(resultList==null&&displayCode.length()>=8){
            switch (displayCode.substring(length - 8)) {
                case "nodeName"://???????????? entityId
                case "NodeName"://???????????? entityId
//                    FlowNode flowNode = flowNodeService.getById(Integer.valueOf(value));
                    resultList = flowNodeService.selectList(UnaMapUtil.getMap(":name",value));
                    break;
                case "roleName"://??????id???????????????????????????
                case "RoleName"://??????id???????????????????????????
                    resultList = sysRoleService.selectList(UnaMapUtil.getMap(":name", value));
                    break;
                case "shopName"://?????? companyId
                case "ShopName"://?????? companyId
                    resultList = sysCompanyService.selectList(UnaMapUtil.getMap(":name", value));
                    break;
                /*case "fileUrls"://???????????? entityId
                case "FileUrls"://???????????? entityId
                    resultList = sysFileService.parse(sysFileService.selectList(MapUtil.getMap("in:path", value)));
                    break;*/
            }
        }

        if(resultList==null&&displayCode.length()>=9){
            switch (displayCode.substring(length - 9)) {
                case "FieldName"://?????? fieldId
                case "fieldName"://?????? fieldId
                    resultList = sysFieldService.selectList(UnaMapUtil.getMap(":name",value));
                    break;
            }
        }

        if(resultList==null&&displayCode.length()>=10){
            switch (displayCode.substring(length - 10)) {
                case "EntityName"://?????? entityId
                case "entityName"://?????? entityId
                    resultList = sysEntityService.selectList(UnaMapUtil.getMap(":name",value));
                    break;
                case "regionName"://?????? regionId
                case "RegionName"://?????? regionId
                    resultList = sysRegionService.selectList(UnaMapUtil.getMap(":name",value));
                    break;
            }
        }

        if(resultList==null&&displayCode.length()>=11){
            switch (displayCode.substring(length - 11)) {
                case "companyName"://?????? companyId
                case "CompanyName"://?????? companyId
                    resultList = sysCompanyService.selectList(UnaMapUtil.getMap(":name",value));
                    break;
                case "artmentName"://?????? departmentId
                    resultList = sysDepartmentService.selectList(UnaMapUtil.getMap(":name",value));
                    break;
                case "accountName"://?????? accountId
                case "AccountName"://?????? accountId
                    resultList = sysAccountService.selectList(UnaMapUtil.getMap(":name",value));
                    break;
                case "regionNames"://?????? regionId
                case "RegionNames"://?????? regionId
                    resultList = sysRegionService.selectList(UnaMapUtil.getMap(":name", value));
                    break;
            }
        }

        if(resultList==null){
            switch (displayCode) {
                case "permissionName"://?????? permissionId
                    resultList = sysPermissionService.selectList(UnaMapUtil.getMap(":name",value));
                    break;
                case "definitionName"://?????? permissionId
                    resultList = flowDefinitionService.selectList(UnaMapUtil.getMap(":name",value));
                case "instanceName"://?????? permissionId
                    resultList = flowInstanceService.selectList(UnaMapUtil.getMap(":name",value));
                    break;
                case "parentName"://???id parentId
                    resultList = bs.getList(UnaMapUtil.getMap("name",value));
                    break;
                default:
                    return SysResult.fail("??????????????????????????? ?????????");
            }
        }

        if(CollectionUtils.isNotEmpty(resultList)){
            List<String> resultStrList = new ArrayList<>();
            if(StrUtil.isNotBlank(transformDisplayCode)){
                BasePojo result0 = resultList.get(0);
                //???????????????????????????????????????????????????????????????????????????name
                Class<? extends BasePojo> resultClass = result0.getClass();
                Field transformDisplayField = resultClass.getDeclaredField(transformDisplayCode);
                transformDisplayField.setAccessible(true);

                for (BasePojo basePojo : resultList) {
                    //???????????????????????????
                    Object transformDisplayValueObj = transformDisplayField.get(basePojo);
                    if(transformDisplayValueObj!=null){
                        resultStrList.add(transformDisplayValueObj.toString());
                    }
                }
            }else{
                for (BasePojo basePojo : resultList) {
                    resultStrList.add(String.valueOf(basePojo.getId()));
                }
            }

            if(CollectionUtils.isNotEmpty(resultStrList)){
                return new SysResult().success("????????????", UnaListUtil.listToStr(resultStrList));
            }
        }
        return SysResult.fail("???????????????");
    }

    @Override
    @SneakyThrows
    public void saveValidate(SysField obj) {
        super.saveValidate(obj);
        if(StrUtil.isNotBlank(obj.getDataCheckTypeDcode()) && !"field_dataDetection_unique".equals(obj.getDataCheckTypeDcode()) && StrUtil.isBlank(obj.getThreshold())){
            throw new UnaResponseException("???????????????????????????????????????????????????????????????");
        }

        if(obj.getParentId()!=null){
            SysField parent = (SysField) getThisProxy().getById(obj.getParentId());
            if(parent.getParentId()!=null){
                throw new UnaResponseException("?????????????????????????????????????????????");
            }
        }
    }

    /**
     * ?????????????????????
     *
     * @param obj
     * @return
     */
    public SysField initialize(SysField obj) {
        super.initialize(obj);
        //?????????????????????????????????
        if (StrUtil.isNotBlank(obj.getThreshold())) obj.setThreshold(obj.getThreshold().replace("???", ","));
        // ??????????????????????????????????????????
        if (StrUtil.isNotBlank(obj.getAssignmentCode()) && StrUtil.isBlank(obj.getDisplayCode())) obj.setDisplayCode(obj.getAssignmentCode());
        //???????????? ????????? ????????????
        if(StrUtil.isNotBlank(obj.getColumnTypeDcode()) && obj.getStorageLength()==null){
            switch(obj.getColumnTypeDcode()){
                case "field_storage_VARCHAR":
                    obj.setStorageLength(255);
                    break;
                case "field_storage_INT":
                    obj.setStorageLength(11);
                    break;
                case "field_storage_TINYINT":
                    obj.setStorageLength(1);
                    break;
            }
        }
        if (obj.getId() == null) {
            if (StrUtil.isBlank(obj.getOptionNameFieldCode())) obj.setOptionNameFieldCode("name");
            if (StrUtil.isBlank(obj.getOptionValueFieldCode())) obj.setOptionValueFieldCode("id");
            if (StrUtil.isBlank(obj.getDqlName()))
                obj.setDqlName("record." + StrUtil.toUnderlineCase(obj.getAssignmentCode()));
        }else{
            if(obj.getOptionNameFieldCode()!=null&&obj.getOptionNameFieldCode().equals(""))obj.setOptionNameFieldCode("name");
            if(obj.getOptionValueFieldCode()!=null&&obj.getOptionValueFieldCode().equals(""))obj.setOptionValueFieldCode("id");
        }

        if(StrUtil.isBlank(obj.getTip()) && StrUtil.isNotBlank(obj.getDataCheckTypeDcode())){
            //??????????????????????????????????????????????????????????????????????????????????????????????????????
            SysDictionary dataCheckTypeDic = sysDictionaryService.selectOne(MapUtil.of("code", obj.getDataCheckTypeDcode()));
            obj.setTip("???????????????"+dataCheckTypeDic.getName()+";???????????????"+obj.getThreshold());
        }

        if(obj.getPermissionId()!=null){
            SysPermission sysPermission = sysPermissionService.getById(obj.getPermissionId());
            if(sysPermission!=null&&sysPermission.getEntityId()!=null){
                String typeDcode = sysPermission.getTypeDcode();
                SysEntity sysEntity = sysEntityService.getById(sysPermission.getEntityId());
                String permissionCode = sysEntity.getCode()+":"+typeDcode.substring(typeDcode.lastIndexOf("_")+1);
                obj.setPermissionCode(permissionCode);
            }
        }

        return obj;
    }

    /**
     * ?????????????????????
     *
     * @param list
     * @return
     */
    public List<SysField> parse(List<SysField> list) {
        super.parse(list);
        for (SysField record : list) {
            //?????????????????????
            List<SysField> selectChildren = sysFieldService.selectList(UnaMapUtil.getMap("selectParentId", record.getId()));
            if(CollectionUtils.isNotEmpty(selectChildren)){
                List<String> idList = new ArrayList<>();
                for (SysField sysField : selectChildren) {
                    idList.add(sysField.getId().toString());
                }

                String idStr = UnaListUtil.listToStr(idList);
                record.setSelectSubIds(idStr);
            }

            //???????????????????????????????????????????????????
            List<SysField> hiddenChildren = sysFieldService.selectList(UnaMapUtil.getMap("hideSwitchFieldId", record.getId()));
            if(CollectionUtils.isNotEmpty(hiddenChildren)){
                Map<String,String> map = new HashMap<>();
                for (SysField sysField : hiddenChildren) {
                    if(StrUtil.isNotBlank(sysField.getHideSwitchFieldValue()) ){
                        if(map.containsKey(sysField.getHideSwitchFieldValue())){
                            //??????????????????
                            map.put(sysField.getHideSwitchFieldValue(),map.get(sysField.getHideSwitchFieldValue())+","+sysField.getId());
                        }else{
                            map.put(sysField.getHideSwitchFieldValue(),String.valueOf(sysField.getId()));
                        }
                    }
                }
                record.setHideSubMap(map);
            }

            if(StrUtil.isNotBlank(record.getAssignmentModeDcode())){
                SysDictionary assignmentModeDic = sysDictionaryService.selectOne(UnaMapUtil.getMap("code", record.getAssignmentModeDcode()));
                if(assignmentModeDic!=null){
                    record.setAssignmentType(assignmentModeDic.getDescription());
                }

                if ("field_assignment_radio".equals(record.getAssignmentModeDcode()) && StrUtil.isNotBlank(record.getThreshold())){
                    record.setRadioOptionArray(StrUtil.splitToArray(record.getThreshold(), ","));
                }else if ("field_assignment_subForm".equals(record.getAssignmentModeDcode())){
                    //?????????????????????????????????????????????????????????
                    List<SysField> subFieldList = sysFieldService.selectList(MapUtil.of("parentId", record.getId()));
                    //??????groupNum????????????
                    if(CollUtil.isNotEmpty(subFieldList)){
                        Map<Integer,List<SysField>> subFieldListInGroup = new HashMap<>();
                        for (SysField sysField : subFieldList) {
                            if(!subFieldListInGroup.containsKey(sysField.getGroupNum())){
                                subFieldListInGroup.put(sysField.getGroupNum(),new ArrayList<>());
                            }
                            subFieldListInGroup.get(sysField.getGroupNum()).add(sysField);
                        }
                        //?????? ????????????
                        Set<Integer> integers = subFieldListInGroup.keySet();
                        List<Integer> sortedKeyList = integers.stream().sorted().collect(Collectors.toList());
                        List<List<SysField>> fieldListInGroup = new ArrayList<>();
                        // ??????????????????????????? ????????????
                        for (Integer key : sortedKeyList) {
                            fieldListInGroup.add(subFieldListInGroup.get(key));
                        }

                        record.setChildren(fieldListInGroup);
                    }

                }
            }

            if(StrUtil.isNotBlank(record.getFormatCheckTypeDcode())){
                SysDictionary codeDic = sysDictionaryService.selectOne(UnaMapUtil.getMap("code", record.getFormatCheckTypeDcode()));
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
