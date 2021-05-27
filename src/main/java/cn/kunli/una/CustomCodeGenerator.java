package cn.kunli.una;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.mvc.AbstractController;

import java.util.*;


/**
 * <p>
 *     业务代码生成
 *          生成基本业务逻辑代码，包括增删改查和审核业务
 *          生成状态字典类 ststus
 *          生成查询用dto  searchbean
 *          数据类型映射 date -> LocalDate ，datetime -> LocalDateTime
 *     注意： 程序中默认数据库表中含如下字段，并以此为前提生成serviceImpl 中的代码。
 *
 *    数据库表模板：      `
     CREATE TABLE `表名` (
     `id` INT(11) NOT NULL AUTO_INCREMENT,
     -- 业务字段START

     -- 业务字段END
     `status` VARCHAR(6) DEFAULT NULL COMMENT '审核状态',
     `flow_id` VARCHAR(33) DEFAULT NULL COMMENT '流程id',
     `update_user` VARCHAR(60) DEFAULT NULL COMMENT '更新人',
     `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
     `update_user_name` VARCHAR(60) DEFAULT NULL COMMENT '更新人名',
     `create_user` VARCHAR(60) DEFAULT NULL COMMENT '创建人',
     `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
     `create_user_name` VARCHAR(60) DEFAULT NULL COMMENT '创建人名',
     `apply_user` VARCHAR(60) DEFAULT NULL COMMENT '填报人id',
     `apply_user_name` VARCHAR(60) DEFAULT NULL COMMENT '填报人',
     `apply_time` DATE DEFAULT NULL COMMENT '填报日期',
     `submit_user` VARCHAR(60) DEFAULT NULL COMMENT '提交人id',
     `submit_user_name` VARCHAR(60) DEFAULT NULL COMMENT '提交人名',
     `submit_time` DATE DEFAULT NULL COMMENT '提交时间',
     `audit_user` VARCHAR(60) DEFAULT NULL COMMENT '审核人id',
     `audit_user_name` VARCHAR(60) DEFAULT NULL COMMENT '审核人名',
     `audit_time` DATE DEFAULT NULL COMMENT '审核时间',
     `audit_opinion` TEXT COMMENT '审核意见',
     PRIMARY KEY (`id`)
     ) ENGINE=INNODB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='表说明';

 * </p>
 *
 * @author mmz
 * @since 2021-05-22
 */
public class CustomCodeGenerator extends AutoGenerator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/superviseservice/src/main/java");
        gc.setAuthor("mmz");
        gc.setOpen(false);
        gc.setDateType(DateType.ONLY_DATE);
         gc.setSwagger2(true); //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/una-platform?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false&nullCatalogMeansCurrent=true");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("glodon2021");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("cu.kunli.una.bussiness");
        mpg.setPackageInfo(pc);
        System.out.println("包名：" + JSON.toJSONString(pc));
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map m = new HashMap();
                m.put("searchbeanPage", pc.getParent()+".bean.dto" );
                m.put("dictPage", pc.getParent()+".bean.dict" );
                this.setMap(m);
                // to do nothing
            }
        };


        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                String path = projectPath + "/superviseservice/src/main/resources/" + pc.getParent().replaceAll("\\.","/") +
                        "/mapper/"  + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                System.out.println(path);
                return path;
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
        templateConfig.setEntity("templates/mybatisplus/entity.java"); // 配置 entity 模板
         templateConfig.setService("templates/mybatisplus/service.java");
         templateConfig.setServiceImpl("templates/mybatisplus/serviceImpl.java");
        templateConfig.setController("templates/mybatisplus/controller.java");

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);//@DATA
        strategy.setRestControllerStyle(true);
        // 公共父类
        strategy.setSuperControllerClass(AbstractController.class);//import AbstractController

        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(false);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


}
