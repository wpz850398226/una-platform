package cn.kunli.una.vo.mybatisplus;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GeneratorProperties {

    //数据库相关
    private String url;
    private String urlSuffix = "useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=Asia/Shanghai&useSSL=false&useAffectedRows=true&allowPublicKeyRetrieval=true";
    private String ip;
    private Integer port = 3306;
    private String database;
    private String driverName = "com.mysql.jdbc.Driver";
    private String username;
    private String password;

    //要生成的编码的表名，多个以英文逗号分割
    private String tableName;

    //模块名称
    private String moduleName = "sys";

    //实体名称
    private String entityName = "entity";

    //建表前缀
    private String tablePrefix = "tb_";

    //服务名称
    private String serviceName;

    //作者
    private String author = "wangpz";




}
