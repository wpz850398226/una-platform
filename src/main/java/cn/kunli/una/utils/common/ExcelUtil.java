package cn.kunli.una.utils.common;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    /**
     * 导出Excel
     *
     * @param sheetName sheet名称
     * @param title     标题
     * @param values    内容
     * @param wb        HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, HSSFWorkbook wb) {

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if (wb == null) {
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values[i].length; j++) {
                //将内容按顺序赋给对应的列对象
                String data = values[i][j];
                //row.createCell(j).setCellValue(values[i][j]);
                HSSFCell contentCell = row.createCell(j);
                Boolean isNum = false;//data是否为数值型
                Boolean isInteger = false;//data是否为整数
                Boolean isPercent = false;//data是否为百分数
                if (data != null || "".equals(data)) {
                    //判断data是否为数值型
                    isNum = data.matches("^(-?\\d+)(\\.\\d+)?$");
                    //判断data是否为整数（小数部分是否为0）
                    isInteger = data.matches("^[-\\+]?[\\d]*$");
                    //判断data是否为百分数（是否包含“%”）
                    isPercent = data.contains("%");
                }

                //如果单元格内容是数值类型，涉及到金钱（金额、本、利），则设置cell的类型为数值型，设置data的类型为数值类型
                if (isNum && !isPercent) {
                    HSSFDataFormat df = wb.createDataFormat(); // 此处设置数据格式
                    if (isInteger) {
                        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,#0"));//数据格式只显示整数
                        // 设置单元格格式
                        contentCell.setCellStyle(style);
                        // 设置单元格内容为int类型
                        contentCell.setCellValue(Long.parseLong(data));
                    } else {
                        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.000"));//保留两位小数点
                        // 设置单元格格式
                        contentCell.setCellStyle(style);
                        // 设置单元格内容为double类型
                        contentCell.setCellValue(Double.parseDouble(data));
                    }

                } else {
                    contentCell.setCellStyle(style);
                    // 设置单元格内容为字符型
                    contentCell.setCellValue(data);
                }

            }
        }
        return wb;
    }

    /**
     * 导出Excel
     *
     * @param sheetName sheet名称
     * @param title     标题
     * @param values    内容
     * @param wb        HSSFWorkbook对象
     * @return
     */
    public static XSSFWorkbook createXSSFWorkbook(String sheetName, String[] title, String[][] values, XSSFWorkbook wb) {

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if (wb == null) {
            wb = new XSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        XSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式

        //声明列对象
        XSSFCell cell = null;

        //创建标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values[i].length; j++) {
                //将内容按顺序赋给对应的列对象
                String data = values[i][j];
                //row.createCell(j).setCellValue(values[i][j]);
                XSSFCell contentCell = row.createCell(j);
                Boolean isNum = false;//data是否为数值型
                Boolean isInteger = false;//data是否为整数
                Boolean isPercent = false;//data是否为百分数
                if (data != null || "".equals(data)) {
                    //判断data是否为数值型
                    isNum = data.matches("^(-?\\d+)(\\.\\d+)?$");
                    //判断data是否为整数（小数部分是否为0）
                    isInteger = data.matches("^[-\\+]?[\\d]*$");
                    //判断data是否为百分数（是否包含“%”）
                    isPercent = data.contains("%");
                }

                //如果单元格内容是数值类型，涉及到金钱（金额、本、利），则设置cell的类型为数值型，设置data的类型为数值类型
                if (isNum && !isPercent) {
                    XSSFDataFormat df = wb.createDataFormat(); // 此处设置数据格式
                    if (isInteger) {
                        style.setDataFormat(df.getFormat("#,#0"));//数据格式只显示整数
                        // 设置单元格格式
                        contentCell.setCellStyle(style);
                        // 设置单元格内容为int类型
                        contentCell.setCellValue(Long.parseLong(data));
                    } else {
                        style.setDataFormat(df.getFormat("#,##0.000"));//保留两位小数点
                        // 设置单元格格式
                        contentCell.setCellStyle(style);
                        // 设置单元格内容为double类型
                        contentCell.setCellValue(Double.parseDouble(data));
                    }

                } else {
                    contentCell.setCellStyle(style);
                    // 设置单元格内容为字符型
                    contentCell.setCellValue(data);
                }

            }
        }
        return wb;
    }


    /**
     * 导出Excel，入参值为List集合
     *
     * @param sheetName sheet名称
     * @param titles    标题
     * @param rows      内容
     * @param wb        HSSFWorkbook对象
     * @return
     */
    public static XSSFWorkbook createXSSFWorkbook(String sheetName, List<String> titles, List<String[]> rows, XSSFWorkbook wb
            , String[] title, Boolean isCheckingIn) {

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if (wb == null) {
            wb = new XSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        XSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT); // 创建一个居中格式

        //声明列对象
        XSSFCell cell = null;

        //存储最大列宽
        Map<Integer, Integer> maxWidth = new HashMap<>();

        int headerTitleSize = 0;

        //定义三种单元格颜色样式   1-旷工   2-上班外勤   3-上班缺卡
        XSSFCellStyle style1 = wb.createCellStyle();
        style1.setFillForegroundColor(new XSSFColor(new Color(255, 153, 204)));
        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFCellStyle style2 = wb.createCellStyle();
        style2.setFillForegroundColor(new XSSFColor(new Color(204, 255, 255)));
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFCellStyle style3 = wb.createCellStyle();
        style3.setFillForegroundColor(new XSSFColor(new Color(255, 128, 128)));
        style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //首先判断标题是否为空
        if (title != null && title.length != 0) {
            headerTitleSize = title.length;
            for (int i = 0; i < title.length; i++) {
                row = sheet.createRow(i);
                //创建单元格
                XSSFCell headerTitle = row.createCell(0);
                headerTitle.setCellValue(title[i]);
                XSSFCellStyle headerStyle = wb.createCellStyle();
                headerStyle.setFillForegroundColor(new XSSFColor(new Color(204, 255, 255)));
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                XSSFFont font = wb.createFont();
                font.setBold(true);
                font.setColor(new XSSFColor(new Color(0, 128, 128)));
                short fontSize = 12;
                if (i == 0) {
                    fontSize = 12;
                } else {
                    fontSize = 12;
                }
                font.setFontHeightInPoints(fontSize);
                headerStyle.setFont(font);
                headerTitle.setCellStyle(headerStyle);
                //创建合并单元格
                CellRangeAddress region = new CellRangeAddress(i, i, 0, titles.size());
                sheet.addMergedRegion(region);
            }
            row = sheet.createRow(title.length);
        }

        //创建标题
        for (int i = 0; i < titles.size(); i++) {
            cell = row.createCell(i);
            XSSFCellStyle titleStyle = wb.createCellStyle();
            if (isCheckingIn) {
                titleStyle.setFillForegroundColor(new XSSFColor(new Color(255, 255, 204)));
                titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            }
            XSSFFont font = wb.createFont();
            font.setBold(true);
            short fontSize = 12;
            font.setFontHeightInPoints(fontSize);
            titleStyle.setFont(font);
            cell.setCellValue(titles.get(i));
            cell.setCellStyle(titleStyle);
            maxWidth.put(i, cell.getStringCellValue().getBytes().length * 256 + 512);
        }

        XSSFCellStyle tempStyle = style;
        XSSFCellStyle numberStyle = wb.createCellStyle();
        //创建内容
        for (int i = 0; i < rows.size(); i++) {
            row = sheet.createRow(i + 1 + headerTitleSize);
            for (int j = 0; j < rows.get(i).length; j++) {
                style = tempStyle;
                //将内容按顺序赋给对应的列对象
                String data = rows.get(i)[j];
                //row.createCell(j).setCellValue(values[i][j]);
                XSSFCell contentCell = row.createCell(j);
                Boolean isNum = false;//data是否为数值型
                Boolean isInteger = false;//data是否为整数
                Boolean isPercent = false;//data是否为百分数
                if (data != null || "".equals(data)) {
                    //判断data是否为数值型
                    isNum = data.matches("^(-?\\d+)(\\.\\d+)?$");
                    //判断data是否为整数（小数部分是否为0）
                    isInteger = data.matches("^[-\\+]?[\\d]*$");
                    if (isInteger) {
                        isInteger = String.valueOf(Long.MAX_VALUE).length() >= data.length();
                    }
                    //判断data是否为百分数（是否包含“%”）
                    isPercent = data.contains("%");
                }

                //如果单元格内容是数值类型，涉及到金钱（金额、本、利），则设置cell的类型为数值型，设置data的类型为数值类型
                //创建单元格样式
                boolean wenben = false;
                if (isNum && !isPercent) {
                    XSSFDataFormat df = wb.createDataFormat(); // 此处设置数据格式
                    if (isInteger) {
                        if (data.length() > 10) {
                            wenben = true;
                            numberStyle.setDataFormat(df.getFormat("@"));//数据格式为 文本
                            // 设置单元格内容为String类型
                            contentCell.setCellValue(data);
                        } else {
                            numberStyle.setDataFormat(df.getFormat("0"));//数据格式只显示整数
                            // 设置单元格格式
                            contentCell.setCellStyle(numberStyle);
                            // 设置单元格内容为int类型
                            contentCell.setCellValue(Long.parseLong(data));
                        }
                    } else {

                        numberStyle.setDataFormat(df.getFormat("0.00"));//保留两位小数点
                        contentCell.setCellStyle(numberStyle);
                        // 设置单元格内容为double类型
                        contentCell.setCellValue(Double.parseDouble(data));
                    }
                    int length = 0;
                    if (wenben) {
                        length = contentCell.getStringCellValue().getBytes().length * 256 + 512;
                    } else {
                        length = String.valueOf(contentCell.getNumericCellValue()).getBytes().length * 256 + 512;
                    }
                    //长数字手机预览是会显示科学计数法，吧单元格设置为文本
//                    int length = String.valueOf(contentCell.getNumericCellValue()).getBytes().length * 256 + 512;
                    //这里把宽度最大限制到15000
                    if (length > 15000) {
                        length = 15000;
                    }
                    maxWidth.put(j, Math.max(length, maxWidth.get(j)));
                } else {
                    //判断是否为旷工
                    if (data != null && isCheckingIn) {
                        if (data.contains("旷工") || data.contains("请假")) {
                            style = style1;
                        }
                        if (data.contains("上班外勤") || data.contains("下班外勤")) {
                            style = style2;
                        }
                        if (data.contains("上班缺卡") || data.contains("下班缺卡") || data.contains("迟到") || data.contains("早退")) {
                            style = style3;
                        }
                    }
                    contentCell.setCellStyle(style);
                    // 设置单元格内容为字符型
                    contentCell.setCellValue(data);
                    int length = contentCell.getStringCellValue().getBytes().length * 256 + 512;
                    //这里把宽度最大限制到15000
                    if (length > 15000) {
                        length = 15000;
                    }
                    maxWidth.put(j, Math.max(length, maxWidth.get(j)));
                }

            }
        }

        for (int i = 0; i < titles.size(); i++) {
            sheet.setColumnWidth(i, maxWidth.get(i));
        }

        return wb;
    }

}
