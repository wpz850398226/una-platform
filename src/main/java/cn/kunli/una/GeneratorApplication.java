package cn.kunli.una;

import cn.kunli.una.utils.mybatisplus.GeneratorUtil;
import cn.kunli.una.vo.mybatisplus.GeneratorProperties;

public class GeneratorApplication {

    public static void main(String[] args) {

        // 类注释里的Author
        String author = "wangpz";
        //表名
        String tableName = "td_etymology";

        GeneratorUtil.codeGenerate(new GeneratorProperties().setAuthor(author).setModuleName("td")
                .setIp("123.56.79.121").setUsername("root").setPassword("Kunli@888").setPort(3306)
                .setDatabase("una-layui").setTableName(tableName));
    }
}
