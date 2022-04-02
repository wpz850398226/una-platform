package cn.kunli.una.vo.ali.easyexcel;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Accessors(chain = true)
public class ExportInfo {

    //表格名称
    private String sheetName = "数据";

    //数据源
    private List data;

    //导出字段
    private Set<String> includeColumn;

    //排除字段
    private Set<String> excludeColumn;

    //字段名称映射关系
    private Map<String,String> columnNameMap;

    //字段顺序
    private String[] columnArray;

    //字段值格式化映射
    private Map<String,String> columnValueFormatMap;

    //字段值内容转换映射
    private Map<String,Map<String,String>> columnValueParseMap;

}
