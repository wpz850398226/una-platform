package cn.kunli.una.service.system;

import cn.hutool.core.util.StrUtil;
import cn.kunli.una.mapper.SysEntityMapper;
import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.pojo.system.SysField;
import cn.kunli.una.pojo.system.SysFilter;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.common.MapUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.github.drinkjava2.jdialects.Type;
import com.github.drinkjava2.jdialects.annotation.jpa.GenerationType;
import com.github.drinkjava2.jdialects.model.ColumnModel;
import com.github.drinkjava2.jdialects.model.TableModel;
import com.github.drinkjava2.jsqlbox.DbContext;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            Map<String, Object> map = MapUtil.getMap("entityId", sysEntity.getId());
            sysEntity.setRelationList(sysRelationService.parse(sysRelationService.selectList(map)));
            sysEntity.setButtonList(sysButtonService.parse(sysButtonService.selectList(map)));
            sysEntity.setQueryList(sysQueryService.parse(sysQueryService.selectList(map)));
            sysEntity.setPermissionList(sysPermissionService.selectList(MapUtil.getMap("entityId",sysEntity.getId())));
            sysEntity.setSortList(sysSortService.parse(sysSortService.selectList(map)));
            List<SysFilter> filterList = sysFilterService.parse(sysFilterService.selectList(map));
            if(CollectionUtils.isNotEmpty(filterList)){
                List<SysFilter> newFilterList = ListUtil.getList((SysFilter)new SysFilter().setName("全部"));
                newFilterList.addAll(filterList);
                sysEntity.setFilterList(newFilterList);
            }

        }

        return list;
    }

    //数据库表DDL操作
    public SysResult tableOperate(Integer id){
        DbContext ctx = new DbContext(dataSource);
        ctx.setAllowShowSQL(true);  //开启SQL日志输出
        DbContext.setGlobalDbContext(ctx);  //设定全局上下文

        SysEntity sysEntity = this.getById(id);
        TableModel tableModel = new TableModel();
        tableModel.setTableName(StrUtil.toUnderlineCase(sysEntity.getCode()));
        tableModel.setComment(sysEntity.getName());


        List<SysField> sysFieldList = sysFieldService.selectList(MapUtil.getMap("entityId", id));
        List<ColumnModel> columnModelList = sysFieldList.stream().map(sf -> {
            ColumnModel columnModel = new ColumnModel(StrUtil.toUnderlineCase(sf.getAssignmentCode()));
            columnModel.setColumnType(Type.valueOf(sf.getColumnTypeDcode()));
            columnModel.setComment(sf.getName());
            columnModel.setDefaultValue(sf.getDefaultValue());
            return columnModel;
        }).collect(Collectors.toList());

        ColumnModel idColumnModel = new ColumnModel("id");
        idColumnModel.setPkey(true);
        idColumnModel.setColumnType(Type.INTEGER);
        idColumnModel.setNullable(false);
        idColumnModel.setIdGenerationType(GenerationType.AUTO);
        idColumnModel.setLength(11);
        columnModelList.add(idColumnModel);
        tableModel.setColumns(columnModelList);

        int i = ctx.quiteExecute(ctx.toDropAndCreateDDL(tableModel));//生成DDL,建数据库表

        if(i==0){
            return SysResult.success();
        }

        return SysResult.fail();
    }

    public SysResult autoCodeGenerate(String... tableName){
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("Ponzio");
        gc.setOpen(false);
        //设置实体统一加后缀DO
//        gc.setEntityName(FILE_NAME_MODEL);
//        gc.setDateType(DateType.ONLY_DATE);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://123.56.79.121:3306/una-layui?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false&nullCatalogMeansCurrent=true");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("Kunli@888");
        mpg.setDataSource(dsc);

        dsc.setUrl(url);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setParent("cn.kunli.una");
        pc.setEntity("pojo.geneticAlgorithm");
        pc.setController("controller.geneticAlgorithm");
        pc.setServiceImpl("service.geneticAlgorithm");
//        pc.setMapper("mapper");

        mpg.setPackageInfo(pc);
//        System.out.println("包名：" + JSON.toJSONString(pc));
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"  + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setController("templates/controller.java");
        templateConfig.setServiceImpl("templates/serviceImpl.java");

        //不生成的文件
        templateConfig.setService(null);
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setChainModel(true);
        strategy.setSuperEntityClass(BasePojo.class);
//        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass(AbstractController.class);
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(tableName);
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix("ga_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

        return SysResult.fail();
    }
}
