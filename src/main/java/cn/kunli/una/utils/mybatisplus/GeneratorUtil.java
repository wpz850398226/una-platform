package cn.kunli.una.utils.mybatisplus;

import cn.kunli.una.controller.BaseController;
import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.vo.mybatisplus.GeneratorInfo;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class GeneratorUtil {

    public static SysResult<String> codeGenerate(GeneratorInfo info){
        if(StringUtils.isBlank(info.getTableName())){
            return SysResult.fail("创建失败，未指定表名");
        }

        if(StringUtils.isBlank(info.getPassword()) || StringUtils.isBlank(info.getUsername())){
            return SysResult.fail("创建失败，数据库账号密码信息确实");
        }

        if((StringUtils.isBlank(info.getUrl()) && (StringUtils.isBlank(info.getIp()) || StringUtils.isBlank(info.getDatabase()))) || StringUtils.isBlank(info.getDriverName())){
            return SysResult.fail("创建失败，数据库信息有误");
        }

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
//        gc.setAuthor("wangpz-b");
        gc.setAuthor(info.getAuthor());
        gc.setOpen(false);
        gc.setSwagger2(true);
        //设置实体统一加后缀DO
//        gc.setEntityName(FILE_NAME_MODEL);
//        gc.setDateType(DateType.ONLY_DATE);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        String url = "";
        if(StringUtils.isNotBlank(info.getUrl())){
            url = info.getUrl();
        }else{
            StringBuffer sb = new StringBuffer();
            url = sb.append("jdbc:mysql://")
                    .append(info.getIp())
                    .append(":")
                    .append(info.getPort())
                    .append("/")
                    .append(info.getDatabase())
                    .append("?")
                    .append(info.getUrlSuffix())
                    .toString();
        }

        dsc.setUrl(url);
        dsc.setDriverName(info.getDriverName());
        dsc.setUsername(info.getUsername());
        dsc.setPassword(info.getPassword());
        // dsc.setSchemaName("public");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("GLDpcop789!");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setParent("cn.kunli.una");
        pc.setEntity("pojo");
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
        String xmlTemplatePath = "templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/template/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(xmlTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/"+info.getServiceName()+"/src/main/resources/mapper/"  + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setController("templates/mybatisplus/controller.java");
        templateConfig.setService(null);
        templateConfig.setServiceImpl("templates/mybatisplus/service.java");
        templateConfig.setMapper("templates/mapper.java");
        templateConfig.setEntity("templates/mybatisplus/entity.java");
        templateConfig.setXml(null);

        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(BasePojo.class);
        strategy.setSuperEntityColumns("id","name","remark","creatorId","createTime");
        strategy.setSuperServiceImplClass(BasicService.class);
        //开启驼峰转换
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        //开启lombok
        strategy.setEntityLombokModel(true);
        //开启controller restful风格
        strategy.setRestControllerStyle(true);
        //关闭controller路径连字符风格
        strategy.setControllerMappingHyphenStyle(false);
        strategy.setSuperControllerClass(BaseController.class);
        //开启链式编程
        strategy.setChainModel(true);
//        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass(AbstractController.class);
        // 写于父类中的公共字段
        strategy.setInclude(info.getTableName().split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(info.getTablePrefix());
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
        return SysResult.success("创建成功");
    }

}
