package cn.kunli.una.service.sys;

import cn.hutool.core.util.StrUtil;
import cn.kunli.una.mapper.SysFieldMapper;
import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.pojo.sys.SysDictionary;
import cn.kunli.una.pojo.sys.SysEntity;
import cn.kunli.una.pojo.sys.SysField;
import cn.kunli.una.pojo.sys.SysPermission;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.service.duohui.chanpin.CpGoodsService;
import cn.kunli.una.service.duohui.chanpin.CpModelService;
import cn.kunli.una.service.duohui.toubiao.BidProjectService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private CpGoodsService cpGoodsService;
    @Autowired
    private CpModelService cpModelService;

    @Autowired
    private BidProjectService bidProjectService;
    @Autowired
    private GaGeneService gaGeneService;

    @Autowired
    private TdEtymologyService tdEtymologyService;

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
        if (StrUtil.isBlank(assignmentCode) || StrUtil.isBlank(value)) return SysResult.fail("查询失败：赋值编码或值为空");
        int length = assignmentCode.length();
        List<? extends BasePojo> resultList = null;

        if(assignmentCode.length()>=5){
            switch (assignmentCode.substring(length - 5)) {
                case "Dcode"://字典编码
                    resultList = sysDictionaryService.selectList(UnaMapUtil.getMap("code", value));
                    break;
            }
        }

        if(resultList==null&&assignmentCode.length()>=6){
            switch (assignmentCode.substring(length - 6)) {
                case "nodeId"://流程节点 entityId
                case "NodeId"://流程节点 entityId
//                    FlowNode flowNode = flowNodeService.getById(Integer.valueOf(value));
                    resultList = flowNodeService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "roleId"://角色id，复数，以逗号分隔
                case "RoleId"://角色id，复数，以逗号分隔
                    resultList = sysRoleService.selectList(UnaMapUtil.getMap("in:id", value));
                    break;
                case "fileId"://流程节点 entityId
                case "FileId"://流程节点 entityId
                    resultList = sysFileService.parse(sysFileService.selectList(UnaMapUtil.getMap("id", value)));
                    break;
                case "shopId"://店铺 companyId
                case "ShopId"://店铺 companyId
                    resultList = sysCompanyService.selectList(UnaMapUtil.getMap("id", value));
                    break;
                case "Dcodes"://字典编码
                    resultList = sysDictionaryService.selectList(UnaMapUtil.getMap("in:code", value));
                    break;
                case "geneId"://遗传基因
                case "GeneId"://遗传基因
                    resultList = gaGeneService.selectList(UnaMapUtil.getMap("id", value));
                    break;
            }
        }

        if(resultList==null&&assignmentCode.length()>=7){
            switch (assignmentCode.substring(length - 7)) {
                case "FieldId"://字段 fieldId
                case "fieldId"://字段 fieldId
                    resultList = sysFieldService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "GoodsId"://商品 goodsId
                case "goodsId"://商品 goodsId
                    resultList = cpGoodsService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "fileIds"://流程节点 entityId
                case "FileIds"://流程节点 entityId
                    resultList = sysFileService.parse(sysFileService.selectList(UnaMapUtil.getMap("in:id", value)));
                    break;
                case "modelId"://商品型号
                case "ModelId"://商品型号
                    resultList = cpModelService.selectList(UnaMapUtil.getMap("id", value));
                    break;
            }
        }

        if(resultList==null&&assignmentCode.length()>=8){
            switch (assignmentCode.substring(length - 8)) {
                case "EntityId"://实体 entityId
                case "entityId"://实体 entityId
                    resultList = sysEntityService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "regionId"://地区 regionId
                case "RegionId"://地区 regionId
                    resultList = sysRegionService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "originId"://词源 originId
                case "OriginId"://词源 originId
                    resultList = tdEtymologyService.selectList(UnaMapUtil.getMap("id",value));
                    break;
            }
        }

        if(resultList==null&&assignmentCode.length()>=9){
            switch (assignmentCode.substring(length - 9)) {
                case "companyId"://公司 companyId
                case "CompanyId"://公司 companyId
                    resultList = sysCompanyService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "artmentId"://部门 departmentId
                    resultList = sysDepartmentService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "ProjectId"://项目
                case "projectId":
                    resultList = bidProjectService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "accountId"://账号 accountId
                case "AccountId"://账号 accountId
                    resultList = sysAccountService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "regionIds"://地区 regionId
                case "RegionIds"://地区 regionId
                    resultList = sysRegionService.selectList(UnaMapUtil.getMap("in:id", value));
                    break;
            }
        }

        if(resultList==null&&assignmentCode.length()>=10){
            switch (assignmentCode.substring(length - 10)) {
                case "companyIds"://公司 companyId
                case "CompanyIds"://公司 companyId
                    resultList = sysCompanyService.selectList(UnaMapUtil.getMap("in:id",value));
                    break;
                case "accountIds"://账号 accountIds
                case "AccountIds"://账号 accountIds
                    resultList = sysAccountService.selectList(UnaMapUtil.getMap("in:id",value));
                    break;
            }
        }

        if(resultList==null){
            switch (assignmentCode) {
                case "permissionId"://权限 permissionId
                    resultList = sysPermissionService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "definitionId"://权限 permissionId
                    resultList = flowDefinitionService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "instanceId"://权限 permissionId
                    resultList = flowInstanceService.selectList(UnaMapUtil.getMap("id",value));
                    break;
                case "parentId"://父id parentId
                    resultList = bs.getList(UnaMapUtil.getMap("id",value));
                    break;
                default:
                    return SysResult.fail("查询失败：赋值编码 未识别");
            }
        }

        if(CollectionUtils.isNotEmpty(resultList)){
            List<String> resultStrList = new ArrayList<>();
            if(StrUtil.isNotBlank(transformDisplayCode)){
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
                return new SysResult().success("查询成功", UnaListUtil.listToStr(resultStrList));
            }
        }
        return SysResult.fail("查询无数据");
    }

    /**
     * 转换存储值为显示值
     * @param displayCode    取值编码编码
     * @param value 存储至
     * @param bs    源服务
     * @param transformDisplayCode       取值编码
     * @return
     */
    @SneakyThrows
    public SysResult getAssignmentValue(String displayCode, String value, BasicService bs,String transformDisplayCode) {
        if (StrUtil.isBlank(displayCode) || StrUtil.isBlank(value)) return SysResult.fail("查询失败：赋值编码或值为空");
        int length = displayCode.length();
        List<? extends BasePojo> resultList = null;

        if(displayCode.length()>=5){
            switch (displayCode.substring(length - 5)) {
                case "Dname"://字典编码
                    resultList = sysDictionaryService.selectList(UnaMapUtil.getMap(":name", value));
                    transformDisplayCode = "code";
                    break;
            }
        }

        if(resultList==null&&displayCode.length()>=6){
            switch (displayCode.substring(length - 6)) {
                case "Dnames"://字典编码
                    resultList = sysDictionaryService.selectList(UnaMapUtil.getMap(":name", value));
                    transformDisplayCode = "code";
                    break;
            }
        }

        /*if(resultList==null&&displayCode.length()>=7){
            switch (displayCode.substring(length - 7)) {
                case "fileUrl"://流程节点 entityId
                case "FileUrl"://流程节点 entityId
                    resultList = sysFileService.parse(sysFileService.selectList(MapUtil.getMap("path", value)));
                    break;
            }
        }*/

        if(resultList==null&&displayCode.length()>=8){
            switch (displayCode.substring(length - 8)) {
                case "nodeName"://流程节点 entityId
                case "NodeName"://流程节点 entityId
//                    FlowNode flowNode = flowNodeService.getById(Integer.valueOf(value));
                    resultList = flowNodeService.selectList(UnaMapUtil.getMap(":name",value));
                    break;
                case "roleName"://角色id，复数，以逗号分隔
                case "RoleName"://角色id，复数，以逗号分隔
                    resultList = sysRoleService.selectList(UnaMapUtil.getMap(":name", value));
                    break;
                case "shopName"://店铺 companyId
                case "ShopName"://店铺 companyId
                    resultList = sysCompanyService.selectList(UnaMapUtil.getMap(":name", value));
                    break;
                /*case "fileUrls"://流程节点 entityId
                case "FileUrls"://流程节点 entityId
                    resultList = sysFileService.parse(sysFileService.selectList(MapUtil.getMap("in:path", value)));
                    break;*/
            }
        }

        if(resultList==null&&displayCode.length()>=9){
            switch (displayCode.substring(length - 9)) {
                case "FieldName"://字段 fieldId
                case "fieldName"://字段 fieldId
                    resultList = sysFieldService.selectList(UnaMapUtil.getMap(":name",value));
                    break;
                case "GoodsName"://商品 goodsId
                case "goodsName"://商品 goodsId
                    resultList = cpGoodsService.selectList(UnaMapUtil.getMap(":name",value));
                    break;
                case "modelName"://商品型号
                case "ModelName"://商品型号
                    resultList = cpModelService.selectList(UnaMapUtil.getMap(":name", value));
                    break;
            }
        }

        if(resultList==null&&displayCode.length()>=10){
            switch (displayCode.substring(length - 10)) {
                case "EntityName"://实体 entityId
                case "entityName"://实体 entityId
                    resultList = sysEntityService.selectList(UnaMapUtil.getMap(":name",value));
                    break;
                case "regionName"://地区 regionId
                case "RegionName"://地区 regionId
                    resultList = sysRegionService.selectList(UnaMapUtil.getMap(":name",value));
                    break;
            }
        }

        if(resultList==null&&displayCode.length()>=11){
            switch (displayCode.substring(length - 11)) {
                case "companyName"://公司 companyId
                case "CompanyName"://公司 companyId
                    resultList = sysCompanyService.selectList(UnaMapUtil.getMap(":name",value));
                    break;
                case "artmentName"://部门 departmentId
                    resultList = sysDepartmentService.selectList(UnaMapUtil.getMap(":name",value));
                    break;
                case "accountName"://账号 accountId
                case "AccountName"://账号 accountId
                    resultList = sysAccountService.selectList(UnaMapUtil.getMap(":name",value));
                    break;
                case "regionNames"://地区 regionId
                case "RegionNames"://地区 regionId
                    resultList = sysRegionService.selectList(UnaMapUtil.getMap(":name", value));
                    break;
            }
        }

        if(resultList==null){
            switch (displayCode) {
                case "permissionName"://权限 permissionId
                    resultList = sysPermissionService.selectList(UnaMapUtil.getMap(":name",value));
                    break;
                case "definitionName"://权限 permissionId
                    resultList = flowDefinitionService.selectList(UnaMapUtil.getMap(":name",value));
                case "instanceName"://权限 permissionId
                    resultList = flowInstanceService.selectList(UnaMapUtil.getMap(":name",value));
                    break;
                case "parentName"://父id parentId
                    resultList = bs.getList(UnaMapUtil.getMap("name",value));
                    break;
                default:
                    return SysResult.fail("查询失败：取值编码 未识别");
            }
        }

        if(CollectionUtils.isNotEmpty(resultList)){
            List<String> resultStrList = new ArrayList<>();
            if(StrUtil.isNotBlank(transformDisplayCode)){
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
                    resultStrList.add(String.valueOf(basePojo.getId()));
                }
            }

            if(CollectionUtils.isNotEmpty(resultStrList)){
                return new SysResult().success("查询成功", UnaListUtil.listToStr(resultStrList));
            }
        }
        return SysResult.fail("查询无数据");
    }

    /**
     * 保存实例格式化
     *
     * @param obj
     * @return
     */
    public SysField initialize(SysField obj) {
        super.initialize(obj);
        if (StrUtil.isNotBlank(obj.getRadioOptions())) obj.setRadioOptions(obj.getRadioOptions().replace("，", ","));
        if (StrUtil.isNotBlank(obj.getAssignmentCode()) && StrUtil.isBlank(obj.getDisplayCode())) obj.setDisplayCode(obj.getAssignmentCode());
        if(StrUtil.isNotBlank(obj.getColumnTypeDcode())){
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
     * 查询结果格式化
     *
     * @param list
     * @return
     */
    public List<SysField> parse(List<SysField> list) {
        super.parse(list);
        for (SysField record : list) {
            //下拉联动子组件
            List<SysField> selectChildren = sysFieldService.selectList(UnaMapUtil.getMap("selectParentId", record.getId()));
            if(CollectionUtils.isNotEmpty(selectChildren)){
                List<String> idList = new ArrayList<>();
                for (SysField sysField : selectChildren) {
                    idList.add(sysField.getId().toString());
                }

                String idStr = UnaListUtil.listToStr(idList);
                record.setSelectSubIds(idStr);
            }

            //隐藏子组件
            List<SysField> hiddenChildren = sysFieldService.selectList(UnaMapUtil.getMap("hideFieldId", record.getId()));
            if(CollectionUtils.isNotEmpty(hiddenChildren)){
                Map<String,String> map = new HashMap<>();
                for (SysField sysField : hiddenChildren) {
                    if(map.containsKey(sysField.getHideFieldValue())){
                        //触发多个隐藏
                        map.put(sysField.getHideFieldValue(),map.get(sysField.getHideFieldValue())+","+sysField.getId());
                    }else{
                        map.put(sysField.getHideFieldValue(),String.valueOf(sysField.getId()));
                    }
                }
                record.setHideSubMap(map);
            }

            if (StrUtil.isNotBlank(record.getRadioOptions())){
                record.setRadioOptionArray(StrUtil.splitToArray(record.getRadioOptions(), ","));
            }

            if(StrUtil.isNotBlank(record.getAssignmentModeDcode())){
                SysDictionary assignmentModeDic = sysDictionaryService.selectOne(UnaMapUtil.getMap("code", record.getAssignmentModeDcode()));
                if(assignmentModeDic!=null){
                    record.setAssignmentType(assignmentModeDic.getRemark());
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
