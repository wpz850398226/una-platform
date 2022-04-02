package cn.kunli.una.utils.ali.easyexcel;

import cn.kunli.una.vo.ali.easyexcel.ExportInfo;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EasyExcelUtil {

    /**
     * 最简单的写
     * <p>
     * 1. 创建excel对应的实体对象
     * <p>
     * 2. 直接写即可
     */
    public static String export(ExportInfo info) {
        String fileName = EasyExcelUtil.class.getResource("/").getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";

        if (CollectionUtils.isNotEmpty(info.getColumnNameMap())) {
            Map<String, String> columnNameMap = info.getColumnNameMap();
            String[] columnArray = info.getColumnArray();
            if (ArrayUtils.isNotEmpty(columnArray)) {
                for (String column : columnArray) {
                    if (!columnNameMap.containsKey(column)) {
                        return "导出失败，columnArray参数错误：columnArray中不能包含columnNameMap中没有的字段";
                    }
                }
            }
            //传入了 列名信息
            List<List<String>> head = getHead(columnNameMap, columnArray);
            List<List<Object>> dataList = getDataList(columnNameMap, columnArray, info.getColumnValueFormatMap(), info.getColumnValueParseMap(), info.getData());
            EasyExcel.write(fileName).sheet(info.getSheetName()).head(head).doWrite(dataList);
        } else {
            // 没有传入 列名信息，使用数据类型原生字段名称为列名
            Class<?> aClass = info.getData().get(0).getClass();
            ExcelWriterSheetBuilder builder = EasyExcel.write(fileName, aClass).sheet(info.getSheetName());

            if (CollectionUtils.isNotEmpty(info.getIncludeColumn())) {
                builder.includeColumnFiledNames(info.getIncludeColumn());
            }

            if (CollectionUtils.isNotEmpty(info.getExcludeColumn())) {
                builder.excludeColumnFiledNames(info.getExcludeColumn());
            }
            builder.doWrite(info.getData());
        }

        return fileName;
    }

    private static List<List<String>> getHead(Map<String, String> map, String[] array) {
        List<List<String>> list = new ArrayList<List<String>>();
        if (ArrayUtils.isNotEmpty(array)) {
            //指定了列顺序
            for (String column : array) {
                if (map.containsKey(column)) {
                    List<String> columnNameList = new ArrayList<>();
                    columnNameList.add(map.get(column));
                    list.add(columnNameList);
                }
            }
        } else {
            map.forEach((k, v) -> {
                List<String> columnNameList = new ArrayList<>();
                columnNameList.add(v);
                list.add(columnNameList);
            });
        }
        return list;
    }

    private static List<List<Object>> getDataList(Map<String, String> columnNameMap, String[] array,
                                                  Map<String, String> formatMap, Map<String, Map<String, String>> parseMap, List source) {
        List<List<Object>> list = new ArrayList<List<Object>>();

        if (ArrayUtils.isEmpty(array)) {
            //未指定列顺序，随机map顺序
            Set<String> columnCodeSet = columnNameMap.keySet();
            array = columnCodeSet.toArray(new String[columnCodeSet.size()]);
        }

        for (Object o : source) {
            List<Object> data = new ArrayList<Object>();
            Map<String, Object> stringObjectMap = BeanUtils.beanToMap(o);

            for (String column : array) {
                //数据库原始值
                Object value = stringObjectMap.get(column);
                //数据格式化
                /*if(CollectionUtils.isNotEmpty(formatMap) && formatMap.containsKey(column)){
                    String formatType = formatMap.get(column);
                    switch(formatType){
                        case "yyyyMMdd":
                        case "yyyy":
                        case "yyyy-MM":
                        case "yyyy-MM-dd":
                        case "yyyy-MM-dd HH:mm:ss":
                            //时间格式化
                            String strDateFormat = formatType;
                            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
                            try {
                                Date date = new Date(String.valueOf(value));
                                value = sdf.format(date);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            break;
                        case ".0":
                        case ".1":
                        case ".2":
                        case ".3":
                        case ".4":
                            //小数点格式化
                            String substring = formatType.substring(1);
                            NumberFormat nf   =   NumberFormat.getPercentInstance();
                            nf.setMinimumFractionDigits(Integer.parseInt(substring));
                            value = nf.format(Float.valueOf(value.toString()));
                            break;
                    }

                }*/


                //数据转换
                if (CollectionUtils.isNotEmpty(parseMap) && parseMap.containsKey(column)) {
                    Map<String, String> objectStringMap = parseMap.get(column);
                    if (objectStringMap.containsKey(String.valueOf(value))) {
                        value = objectStringMap.get(String.valueOf(value));
                    }
                }

                data.add(value);
            }
            list.add(data);
        }

        return list;
    }

}
