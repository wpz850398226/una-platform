package cn.kunli.una.service.sys;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.kunli.una.handler.UnaResponseException;
import cn.kunli.una.mapper.SysEntityMapper;
import cn.kunli.una.pojo.sys.*;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.UnaListUtil;
import cn.kunli.una.utils.common.UnaMapUtil;
import cn.kunli.una.utils.mybatisplus.GeneratorUtil;
import cn.kunli.una.vo.mybatisplus.GeneratorProperties;
import com.alibaba.druid.pool.DruidDataSource;
import com.github.drinkjava2.jdialects.Type;
import com.github.drinkjava2.jdialects.annotation.jpa.GenerationType;
import com.github.drinkjava2.jdialects.model.ColumnModel;
import com.github.drinkjava2.jdialects.model.TableModel;
import com.github.drinkjava2.jsqlbox.DbContext;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SysEntityService extends BasicService<SysEntityMapper, SysEntity> {

    @Override
    public BasicService getThisProxy() {
        return sysEntityService;
    }
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private DruidDataSource dataSource;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverName;

    @Override
    public SysEntity initialize(SysEntity obj) {
        obj = super.initialize(obj);

        if(obj.getIsVirtual()!=null && obj.getIsVirtual()){
            obj.setPath("/sys/data");
        }

        return obj;
    }

    @Override
    public List<SysEntity> parse(List<SysEntity> list) {
        if(CollectionUtils.isEmpty(list))return list;
        list = super.parse(list);

        List<SysRelation> allRelationList = sysRelationService.parse(sysRelationService.list());
        List<SysButton> allButtonList = sysButtonService.parse(sysButtonService.list());
        List<SysQuery> allQueryList = sysQueryService.parse(sysQueryService.list());
        List<SysPermission> allPermissionList = sysPermissionService.parse(sysPermissionService.list());
        List<SysSort> allSortList = sysSortService.parse(sysSortService.list());
        List<SysFilter> allFilterList = sysFilterService.parse(sysFilterService.list());



        for (SysEntity sysEntity : list) {
            Integer entityId = sysEntity.getId();
            Map<String, Object> map = UnaMapUtil.getMap("entityId", entityId);

            List<SysRelation> relationList = allRelationList.stream().filter(s -> s.getEntityId().equals(entityId)).collect(Collectors.toList());
            sysEntity.setRelationList(relationList);
            allRelationList.removeAll(relationList);

            List<SysButton> buttonList = allButtonList.stream().filter(s -> s.getEntityId().equals(entityId)).collect(Collectors.toList());
            sysEntity.setButtonList(buttonList);
            allButtonList.removeAll(buttonList);

            List<SysQuery> queryList = allQueryList.stream().filter(s -> s.getEntityId().equals(entityId)).collect(Collectors.toList());
            sysEntity.setQueryList(queryList);
            allQueryList.removeAll(queryList);

            List<SysPermission> permissionList = allPermissionList.stream().filter(s -> s.getEntityId().equals(entityId)).collect(Collectors.toList());
            sysEntity.setPermissionList(permissionList);
            allPermissionList.removeAll(permissionList);

            List<SysSort> sortList = allSortList.stream().filter(s -> s.getEntityId().equals(entityId)).collect(Collectors.toList());
            sysEntity.setSortList(sortList);
            allSortList.removeAll(sortList);

            List<SysFilter> filterList = allFilterList.stream().filter(s -> s.getEntityId().equals(entityId)).collect(Collectors.toList());
            if(CollectionUtils.isNotEmpty(filterList)){
                filterList.add(0, (SysFilter) new SysFilter().setName("??????"));
            }
            sysEntity.setFilterList(filterList);
            allFilterList.removeAll(filterList);


            /*sysEntity.setRelationList(sysRelationService.parse(sysRelationService.selectList(map)));
            sysEntity.setButtonList(sysButtonService.parse(sysButtonService.selectList(map)));
            sysEntity.setQueryList(sysQueryService.parse(sysQueryService.selectList(map)));
            sysEntity.setPermissionList(sysPermissionService.selectList(map));
            sysEntity.setSortList(sysSortService.parse(sysSortService.selectList(map)));
            List<SysFilter> filterList = sysFilterService.parse(sysFilterService.selectList(map));*/

            if(StrUtil.isNotBlank(sysEntity.getKeywordFieldIds())){
                List<SysField> sysFieldList = sysFieldService.selectList(UnaMapUtil.getMap("in:id", sysEntity.getKeywordFieldIds()));
                if(CollUtil.isNotEmpty(sysFieldList)){
                    List<String> fieldCodeList = sysFieldList.stream().map(SysField::getAssignmentCode).collect(Collectors.toList());
                    String fieldDisplayCodes = UnaListUtil.listToStr(fieldCodeList);
                    sysEntity.getMap().put("keywordColumn",fieldDisplayCodes);
                }
            }

        }

        return list;
    }

    //????????????DDL??????
    public SysResult tableGenerate(Integer id){
        //???????????????????????????
        DbContext ctx = new DbContext(dataSource);
        ctx.setAllowShowSQL(true);  //??????SQL????????????
        DbContext.setGlobalDbContext(ctx);  //?????????????????????

        //??????????????????
        SysEntity sysEntity = this.getById(id);
        TableModel tableModel = new TableModel();
        tableModel.setTableName(StrUtil.toUnderlineCase(sysEntity.getCode()));
        tableModel.setComment(sysEntity.getName());

        //??????????????????
        List<SysField> sysFieldList = sysFieldService.selectList(UnaMapUtil.buildHashMap().put("entityId", id).put("ne:columnTypeDcode","field_storage_NULL").build());
        List<ColumnModel> columnModelList = sysFieldList.stream().map(sf -> {
            String substring = sf.getColumnTypeDcode().substring(sf.getColumnTypeDcode().lastIndexOf("_") + 1);
            ColumnModel columnModel = new ColumnModel(StrUtil.toUnderlineCase(sf.getAssignmentCode()));
            columnModel.setColumnType(Type.valueOf(substring));
            columnModel.setLength(sf.getStorageLength());
            columnModel.setComment(sf.getName());
            columnModel.setDefaultValue(sf.getDefaultValue());

            if(sf.getName().equals("ID")){
                columnModel.setPkey(true);
                columnModel.setNullable(false);
                columnModel.setIdGenerationType(GenerationType.AUTO);
            }

            return columnModel;
        }).collect(Collectors.toList());

        tableModel.setColumns(columnModelList);

        //??????
        int i = ctx.quiteExecute(ctx.toCreateDDL(tableModel));//??????DDL,???????????????

        //0???????????????-1????????????
        if(i==0){
            return SysResult.success();
        }

        return SysResult.fail();
    }

    public SysResult codeGenerate(Integer id){
        //??????????????????
        SysEntity sysEntity = this.getById(id);
        if(sysEntity==null)return SysResult.fail("??????????????????????????????");

        String entityCode = sysEntity.getCode();
        String tableName = StrUtil.toUnderlineCase(entityCode);
        if(tableName.indexOf("_")==-1)return SysResult.fail("??????????????????????????????");
        String prefix = tableName.substring(0, tableName.indexOf("_"));
        String suffix = tableName.substring(tableName.indexOf("_")+1);
        String entityName = StrUtil.toCamelCase(suffix);

        SysResult<String> stringSysResult = GeneratorUtil.codeGenerate(new GeneratorProperties()
                .setAuthor("wangpz").setModuleName(prefix).setUrl(url)
                .setUsername(username).setPassword(password).setTableName(tableName).setEntityName(entityName));

        return stringSysResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void afterSaveSuccess(SysEntity obj) {
        if(obj.getModifyTime()==null){
            //????????????????????????????????????????????????
            Integer entityId = obj.getId();
            List<SysField> sysFieldList = sysFieldService.selectList(UnaMapUtil.getMap("entityId", 100000));
            if(CollUtil.isNotEmpty(sysFieldList)){
                for (SysField sysField : sysFieldList) {
                    sysField.setEntityId(entityId).setId(null);
                    SysResult sysResult = sysFieldService.saveRecord(sysField);
                }
            }
        }
    }

    @Override
    @SneakyThrows
    public void saveValidate(SysEntity obj) {
        super.saveValidate(obj);
        if(StrUtil.isNotBlank(obj.getKeywordFieldIds())){
            List<SysField> sysFieldList = sysFieldService.selectList(UnaMapUtil.getMap("in:id", obj.getKeywordFieldIds()));
            if(CollUtil.isNotEmpty(sysFieldList)){
                for (SysField sysField : sysFieldList) {
                    if(!sysField.getAssignmentCode().equals(sysField.getDisplayCode())){
                        throw new UnaResponseException("?????????????????????["+sysField.getName()+"]??????????????????");
                    }
                }
            }
        }
    }
}
