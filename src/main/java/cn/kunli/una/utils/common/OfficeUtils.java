package cn.kunli.una.utils.common;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfficeUtils {

    protected static final Logger logger = LoggerFactory.getLogger(OfficeUtils.class);

    public static  JSONObject readExcelContentz(MultipartFile file,List<String[]> fieldNameList) throws Exception {
        JSONObject result = new JSONObject();
        Workbook wb = getWb(file);
        if (wb == null) {
            throw new Exception();
        }
        //-----------------------------------------------
        for(int k=0;k<fieldNameList.size();k++){
            JSONArray jsonArray = new JSONArray();
            String[] fieldName = fieldNameList.get(k);

            Sheet sheet = wb.getSheetAt(k);
            // 得到总行数
            int rowNum = sheet.getLastRowNum();
            Row row = sheet.getRow(0);
            int colNum = row.getPhysicalNumberOfCells();
            // 正文内容应该从第二行开始,第一行为表头的标题
            for (int i = 2; i <= rowNum; i++) {
                row = sheet.getRow(i);
                int j = 0;
                JSONObject json = new JSONObject();
                while (j < colNum) {
                    Object obj = getCellFormatValue(row.getCell(j));
                    json.put(fieldName[j],obj);
                    j++;
                }
//                content.put(i, cellValue);
                jsonArray.add(json);
            }
            result.put(""+k,jsonArray);
        }
        return result;
    }

    //根据Cell类型设置数据
    private static Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";
        if (cell != null) {
            switch (cell.getCellTypeEnum()) {
                case NUMERIC:
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                    break;
                case FORMULA: {
                    cellvalue = cell.getDateCellValue();
                    break;
                }
                case STRING:
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }

    private static Workbook getWb(MultipartFile mf) {
        String filepath = mf.getOriginalFilename();
        String ext = filepath.substring(filepath.lastIndexOf("."));
        Workbook wb = null;
        try {
            InputStream is = mf.getInputStream();
            if (".xls".equals(ext)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(ext)) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = null;
            }
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
        return wb;
    }
}
