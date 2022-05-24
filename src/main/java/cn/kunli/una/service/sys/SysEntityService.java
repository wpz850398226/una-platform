package cn.kunli.una.service.sys;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.kunli.una.handler.UnaResponseException;
import cn.kunli.una.mapper.SysEntityMapper;
import cn.kunli.una.pojo.sys.SysEntity;
import cn.kunli.una.pojo.sys.SysField;
import cn.kunli.una.pojo.sys.SysFilter;
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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        for (SysEntity sysEntity : list) {
            Map<String, Object> map = UnaMapUtil.getMap("entityId", sysEntity.getId());
            sysEntity.setRelationList(sysRelationService.parse(sysRelationService.selectList(map)));
            sysEntity.setButtonList(sysButtonService.parse(sysButtonService.selectList(map)));
            sysEntity.setQueryList(sysQueryService.parse(sysQueryService.selectList(map)));
            sysEntity.setPermissionList(sysPermissionService.selectList(UnaMapUtil.getMap("entityId",sysEntity.getId())));
            sysEntity.setSortList(sysSortService.parse(sysSortService.selectList(map)));
            List<SysFilter> filterList = sysFilterService.parse(sysFilterService.selectList(map));
            if(CollectionUtils.isNotEmpty(filterList)){
                List<SysFilter> newFilterList = UnaListUtil.getList((SysFilter)new SysFilter().setName("全部"));
                newFilterList.addAll(filterList);
                sysEntity.setFilterList(newFilterList);
            }
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

    //数据库表DDL操作
    public SysResult tableGenerate(Integer id){
        //获取数据库链接信息
        DbContext ctx = new DbContext(dataSource);
        ctx.setAllowShowSQL(true);  //开启SQL日志输出
        DbContext.setGlobalDbContext(ctx);  //设定全局上下文

        //获取实体信息
        SysEntity sysEntity = this.getById(id);
        TableModel tableModel = new TableModel();
        tableModel.setTableName(StrUtil.toUnderlineCase(sysEntity.getCode()));
        tableModel.setComment(sysEntity.getName());

        //获取字段信息
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

        //建表
        int i = ctx.quiteExecute(ctx.toCreateDDL(tableModel));//生成DDL,建数据库表

        //0表示成功，-1表示失败
        if(i==0){
            return SysResult.success();
        }

        return SysResult.fail();
    }

    public SysResult codeGenerate(Integer id){
        //获取实体信息
        SysEntity sysEntity = this.getById(id);
        if(sysEntity==null)return SysResult.fail("创建失败，实体不存在");

        String entityCode = sysEntity.getCode();
        String tableName = StrUtil.toUnderlineCase(entityCode);
        if(tableName.indexOf("_")==-1)return SysResult.fail("创建失败，表名不合法");
        String prefix = tableName.substring(0, tableName.indexOf("_"));
        String suffix = tableName.substring(tableName.indexOf("_")+1);
        String entityName = StrUtil.toCamelCase(suffix);

        SysResult<String> stringSysResult = GeneratorUtil.codeGenerate(new GeneratorProperties()
                .setAuthor("wangpz").setModuleName(prefix).setUrl(url)
                .setUsername(username).setPassword(password).setTableName(tableName).setEntityName(entityName));

        return stringSysResult;
    }

    @Override
    public SysResult afterSaveSuccess(SysEntity obj) {
        //新建实体类，自动增加系统公共字段
        Integer entityId = obj.getId();
        List<SysField> sysFieldList = sysFieldService.selectList(UnaMapUtil.getMap("entityId", 100000));
        if(CollUtil.isNotEmpty(sysFieldList)){
            for (SysField sysField : sysFieldList) {
                sysField.setEntityId(entityId).setId(null);
                SysResult sysResult = sysFieldService.saveRecord(sysField);
                if(!sysResult.getIsSuccess())return sysResult;
            }
        }
        return SysResult.success();
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
                        throw new UnaResponseException("保存失败，字段["+sysField.getName()+"]不能模糊检索");
                    }
                }
            }
        }
    }
}
