/*
package cn.kunli.una.controller.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import una.pojo.system.entity.SysEntity;
import una.pojo.system.entity.SysField;
import una.service.system.entity.SysEntityService;
import una.service.system.entity.SysFieldService;
import una.utils.ExcelUtil;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.List;


*/
/**
 * @author Ponzio
 * @version 2019年9月18日 下午9:27:39
 * excel工具
 *//*

@Controller
@Api(tags = "系统-")
@RequestMapping("/sys/excel")
public class SysExcelController {

	@Autowired
	private SysEntityService sysEntityService;
	@Autowired
	private SysFieldService sysFieldService;



	@RequestMapping("/template")
	public void template(HttpServletResponse response, Integer entityId) {

		//获取数据
		SysEntity sysEntity = sysEntityService.selectBySelective(new SysEntity().setId(entityId)).get(0);
        //excel标题
		//查询可以导出的实体字段
		SysField sysField = new SysField();
		sysField.setWhetherDisplay("是");
		sysField.setWhetherImport("是");
		sysField.setEntityId(entityId);
		List<SysField> fieldList = sysFieldService.selectBySelective(sysField);
        String[] title = new String[fieldList.size()+1];
        //title[0] = "序号";
        for(int j = 0; j < fieldList.size(); j++) {
        	title[j]=fieldList.get(j).getName();
        }
     	//excel文件名
      	String fileName = sysEntity.getName()+"导入模板.xls";
      	//sheet名
	    String sheetName = sysEntity.getName()+"信息表";
	    //创建第一行数据
	    String[][] content = new String[1][fieldList.size()+1];
	    for(int j = 0; j < fieldList.size(); j++) {
	    	String contentText = fieldList.get(j).getStorageType()==null?"文本":fieldList.get(j).getStorageType();
	    	if(fieldList.get(j).getWhetherRequired()!=null&&fieldList.get(j).getWhetherRequired().equals("是"))contentText+="(必填)";
	    	content[0][j] = contentText;
		}

		//创建HSSFWorkbook
		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
		//给第二行说明字体设置为红色
		HSSFSheet sheet = wb.getSheet(sheetName);
		HSSFRow row = sheet.getRow(1);
		HSSFCellStyle style = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setColor(HSSFColor.RED.index);
		style.setFont(font);
		for(int i=0;i<row.getPhysicalNumberOfCells();i++) {
			row.getCell(i).setCellStyle(style);
		}

		//响应到客户端
		try {
			fileName = new String(fileName.getBytes(),"ISO8859-1");
			response.setContentType("application/octet-stream;charset=ISO8859-1");
	        response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
	        response.addHeader("Pargam", "no-cache");
	        response.addHeader("Cache-Control", "no-cache");
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



}
*/
