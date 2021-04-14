package cn.kunli.una.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * 导出工具类
 *
 * @author ty
 * @version 2019-2-13
 */
public class ExcelUtils {

    public static void main(String[] args) throws IOException {

        List<String> headers = new ArrayList<>();
        headers.add("id");
        headers.add("name");
        headers.add("age");
        List<String[]> downData = new ArrayList<>();
        downData.add("张三,李四".split(","));
        List<String> downRows = new ArrayList<>();
        downRows.add("1");

        createExcelTemplate("D:\\\\excel.xls","测试",headers,downData,downRows);
    }


        /**
         * @param
         * @param filePath Excel文件路径
         * @param
         * @param headers  Excel列标题(数组)
         * @param
         * @param downData 下拉框数据(数组)
         * @param
         * @param downRows 下拉列的序号(数组,序号从0开始)
         * @return void
         * @throws String filePath, String sheetTitle, List<String> headers, Map<String,String[]> map, String[] fatherOption
         * @Title: testFunction
         * @Description: 生成Excel导入模板
         */
        public static String createExcelTemplate(String filePath, String sheetTitle,
                                                 List<String> headers, List<String[]> downData,
                                                 List<String> downRows) throws IOException {
            // 创建一个excel
            @SuppressWarnings("resource")
            XSSFWorkbook book = new XSSFWorkbook();
            CellStyle style = book.createCellStyle();
            // 创建一个居中格式
            style.setAlignment(HorizontalAlignment.CENTER);
            // 创建需要用户填写的sheet
            XSSFSheet sheetPro = book.createSheet(sheetTitle);
            Row row0 = sheetPro.createRow(0);
            Cell cell1 = row0.createCell((short) 0);
            for (int i = 0; i < headers.size(); i++) {
                cell1.setCellValue(headers.get(i));
                cell1.setCellStyle(style);
                //设置单元格宽度
                sheetPro.setColumnWidth(i, 20 * 256);
                //设置居中样式
                sheetPro.setDefaultColumnStyle(i, style);
                cell1 = row0.createCell((short) i + 1);
            }
            //创建一个专门用来存放地区信息的隐藏sheet页
            //因此也不能在现实页之前创建，否则无法隐藏。
            XSSFSheet hideSheet = book.createSheet("hideSheet");
            //这一行作用是将此sheet隐藏，功能未完成时注释此行,可以查看隐藏sheet中信息是否正确
            book.setSheetHidden(book.getSheetIndex(hideSheet), true);
            GetNewExcelTemplate(filePath, downData, downRows, sheetPro, hideSheet, book);

            return filePath;
        }

        /**
         * @param filePath 生成的模板文件路径
         * @param downData 下拉框数据集合
         * @param downRows 设置列的序号集合(从0开始)
         */
        public static void GetNewExcelTemplate(String filePath, List<String[]> downData, List<String> downRows, XSSFSheet sheetPro, XSSFSheet hideSheet, XSSFWorkbook workbook) throws IOException {

//            FileInputStream fileInput = new FileInputStream(filePath);
//            // 创建一个webbook，对应一个Excel文件
//            XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
//            //对应Excel文件中的sheet，0代表第一个
//            XSSFSheet sheet1 = workbook.getSheetAt(0);
//            XSSFSheet sheet2 = workbook.getSheetAt(1);
            //修改excle表的数据
            // 设置下拉框数据
            if (downData.size() > 0 && downRows.size() > 0) {
                String[] arr = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
                int index = 0;
                //数据行
                Row row = null;
                for (int r = 0; r < downRows.size(); r++) {
                    // 获取下拉对象
                    String[] dlData = downData.get(r);
                    int rownum = Integer.parseInt(downRows.get(r));
                    // 255以内的下拉
                    if (dlData.length < 5) {
                        // 255以内的下拉,参数分别是：作用的sheet、下拉内容数组、起始行、终止行、起始列、终止列
                        // 超过255个报错
                        sheetPro.addValidationData(setDataValidation(sheetPro, dlData, 1, 5000, rownum, rownum));
                    } else {
                        // 255以上的下拉，即下拉列表元素很多的情况
                        // 1、设置有效性
                        // String strFormula = "Sheet2!$A$1:$A$5000" ;
                        // //Sheet2第A1到A5000作为下拉列表来源数据
                        // Sheet2第A1到A5000作为下拉列表来源数据
                        String strFormula = "hideSheet!$" + arr[index] + "$1:$" + arr[index] + "$" + (dlData.length + 1);
                        // 设置每列的列宽
                        hideSheet.setColumnWidth(r, 4000);
                        // 设置数据有效性加载在哪个单元格上,参数分别是：从sheet2获取A1到A5000作为一个下拉的数据、起始行、终止行、起始列、终止列
                        // 下拉列表元素很多的情况
                        sheetPro.addValidationData(SetDataValidation(workbook, hideSheet, ("data" + r), strFormula, 1, 5000, rownum, rownum));
                        // 2、生成sheet2内容
                        for (int j = 0; j < dlData.length; j++) {
                            // 第1个下拉选项，直接创建行、列
                            if (index == 0) {
                                // 创建数据行
                                row = hideSheet.createRow(j);
                                // 设置每列的列宽
                                hideSheet.setColumnWidth(j, 4000);
                                // 设置对应单元格的值
                                row.createCell(0).setCellValue(dlData[j]);

                            } else {
                                // 非第1个下拉选项
                                int rowCount = hideSheet.getLastRowNum();
                                // 前面创建过的行，直接获取行，创建列
                                if (j <= rowCount) {
                                    // 获取行，创建列
                                    // 设置对应单元格的值
                                    hideSheet.getRow(j).createCell(index).setCellValue(dlData[j]);
                                } else {
                                    // 未创建过的行，直接创建行、创建列
                                    // 设置每列的列宽
                                    hideSheet.setColumnWidth(j, 4000);
                                    // 创建行、创建列
                                    // 设置对应单元格的值
                                    hideSheet.createRow(j).createCell(index).setCellValue(dlData[j]);
                                }
                            }
                        }
                        index++;
                    }
                }
            }

            //将修改后的文件写出到D:\\excel目录下
            // 写文件
            File f = new File(filePath);
            // 不存在则新增
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(f);
            out.flush();
            workbook.write(out);
            //关闭流
            out.close();
        }

        /**
         * @Title: SetDataValidation
         * @Description: 下拉列表元素很多的情况(255以上的下拉)
         * @param @param strFormula
         * @param @param firstRow 起始行
         * @param @param endRow 终止行
         * @param @param firstCol 起始列
         * @param @param endCol 终止列
         * @param xls格式
         * @return HSSFDataValidation @throws
         */
	/*private static HSSFDataValidation SetDataValidation(String strFormula, int firstRow, int endRow, int firstCol,
			int endCol) {

		// 设置数据有效性加载在哪个单元格上。四个参数分别是：起始行、终止行、起始列、终止列
		CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
		DVConstraint constraint = DVConstraint.createFormulaListConstraint(strFormula);
		HSSFDataValidation dataValidation = new HSSFDataValidation(regions, constraint);
		return dataValidation;
	}*/

        /**
         * @param workbook   当前工作簿
         * @param sheet      存放下拉菜单数据的sheet
         * @param dataName   数据有效性名称
         * @param strFormula 下拉菜单取数据范围
         * @param firstRow
         * @param endRow
         * @param firstCol
         * @param endCol
         * @return
         * @Description: xlsx格式 255以上下拉菜单
         */
        private static XSSFDataValidation SetDataValidation(XSSFWorkbook workbook, XSSFSheet sheet, String dataName, String strFormula, int firstRow, int endRow, int firstCol, int endCol) {
            XSSFName name = workbook.createName();
            name.setNameName(dataName);
            name.setRefersToFormula(strFormula);
            XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
            XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
                    .createFormulaListConstraint(dataName);
            CellRangeAddressList addressList = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
            XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);
            validation.setSuppressDropDownArrow(true);
            // validation.setShowErrorBox(true);
            //sheet.addValidationData(validation);
            return validation;
        }

        /**
         * @Title: setDataValidation @Description:
         * 下拉列表元素不多的情况(255以内的下拉) @param @param sheet @param @param
         * textList @param @param firstRow @param @param endRow @param @param
         * firstCol @param @param endCol @param @return @return
         * DataValidation @throws
         */
        private static XSSFDataValidation setDataValidation(XSSFSheet sheet, String[] textList, int firstRow, int endRow,
                                                            int firstCol, int endCol) {
            XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
            XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(textList);
            CellRangeAddressList addressList = new CellRangeAddressList((short) firstRow, (short) endRow, (short) firstCol, (short) endCol);
            XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);
            // 07默认setSuppressDropDownArrow(true);
            // validation.setSuppressDropDownArrow(true);
            // validation.setShowErrorBox(true);
            return validation;
            //xls 格式下拉菜单begin  DataValidation
		/*DataValidationHelper helper = sheet.getDataValidationHelper();
		// 加载下拉列表内容
		DataValidationConstraint constraint = helper.createExplicitListConstraint(textList);
		// DVConstraint constraint = new DVConstraint();
		constraint.setExplicitListValues(textList);
		// 设置数据有效性加载在哪个单元格上。四个参数分别是：起始行、终止行、起始列、终止列
		CellRangeAddressList regions = new CellRangeAddressList((short) firstRow, (short) endRow, (short) firstCol,
				(short) endCol);
		// 数据有效性对象
		DataValidation data_validation = helper.createValidation(constraint, regions);
		//xls 格式下拉菜单end
		return data_validation;*/
        }

}
