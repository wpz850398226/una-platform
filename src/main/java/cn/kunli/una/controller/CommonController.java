package cn.kunli.una.controller;

import cn.kunli.una.pojo.system.*;
import cn.kunli.una.pojo.vo.Constant;
import cn.kunli.una.pojo.vo.SysResponseParameter;
import cn.kunli.una.service.system.*;
import cn.kunli.una.utils.ExcelUtils;
import cn.kunli.una.utils.common.*;
import cn.kunli.una.utils.redis.RedisUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.MalformedObjectNameException;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @author Ponzio
 * @version 2019年6月5日 下午5:25:30
 * 通用接口
 */
@Controller
@RequestMapping("/sys")
public class CommonController {

    @Autowired
    private SysEntityService sysEntityService;
    @Autowired
    private SysFieldService sysFieldService;
    @Autowired
    private SysDictionaryService sysDictionaryService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysRelationService sysRelationService;

    /**
     * 跳转通用管理页
     *
     * @param model
     * @return
     */
    @SneakyThrows
    @RequestMapping("/manage/{className}")
    public String manage(Model model, @PathVariable("className") String className, @RequestParam Map<String, Object> params) {
        //判断权限
        //if(!SecurityUtils.getSubject().isPermitted(className+":retrieve"))return "error/401";
        SysEntity entityClass = sysEntityService.getOne(sysEntityService.getWrapper(MapUtil.getMap("code",className)));
        SysResponseParameter sysResponseParameter = new SysResponseParameter();
        if(entityClass!=null){
            entityClass = sysEntityService.resultFormat(ListUtil.getList(entityClass)).get(0);

            //如果查询的虚拟实体，则通过参数获取实体类名
            if(className.equals("SysData")&&params!=null){
                Object classNameObject = params.get("className");
                if(classNameObject!=null){
                    SysEntity virtualEntity = sysEntityService.getOne(sysEntityService.getWrapper(MapUtil.getMap("code",classNameObject.toString())));
                    if(virtualEntity!=null){
                        params.put("entityId",virtualEntity.getId());
                        params.remove("className");
                        sysResponseParameter.setEntityId(virtualEntity.getId()).setEntityClass(virtualEntity.getCode()).setParams(params);
                    }
                }
            }else{
                if(entityClass.getCode().equals("SysRelation")||entityClass.getCode().equals("SysField")
                        && CollectionUtils.isEmpty(entityClass.getRelationList())){
                    SysRelation sysRelation = new SysRelation()
                            .setEntityId(entityClass.getId())
                            .setParentEntityPath("/sys/entity")
                            .setParentEntityName("实体类")
                            .setParentDataFieldCode("entityId")
                            .setRelatedFieldCode("entityId")
                            .setParentDataValue(null);
                    entityClass.setRelationList(ListUtil.getList(sysRelation));
                }
            }

            sysResponseParameter.setSysEntity(entityClass);
        }

        model.addAttribute("sysResponseParameter", sysResponseParameter);
        model.addAttribute("activeUser", UserUtil.getLoginAccount());
        return "common/sysManage";
    }


    /**
     * 跳转通用表单页
     *
     * @param model
     * @param obj
     * @return
     */
    @RequestMapping("/form/{className}")
    public String form(Model model, @PathVariable("className") String className, @RequestParam Map<String, Object> obj) {
        //判断权限
        String permissionCode;
        if (obj.get("batch") != null) {
            permissionCode = className + ":update";
        } else {
            permissionCode = className + ":" + (obj.get("id") != null ? "update" : "create");
        }

        //if(!SecurityUtils.getSubject().isPermitted(permissionCode))return "error/401";
        SysEntity entityClass = sysEntityService.getOne(sysEntityService.getWrapper(MapUtil.getMap("code",className)));
        //创建查询实例
        Map<String, Object> map = new MapUtil<>().put("entityId", entityClass.getId()).put("isUpdate", 1).build();
        //如果是批量修改，则查询可批量修改的 字段
        if (obj.get("batch") != null) map.put("isBatchUpdate",1);
        //查询字段
        List<SysField> sysFieldList = sysFieldService.resultFormat(sysFieldService.list(sysFieldService.getWrapper(map)));
        List<List<SysField>> sysFieldListList = new ArrayList<>();


        for (SysField sysField : sysFieldList) {
            if(StringUtils.isNotBlank(sysField.getGroupName())){
                List<SysField> subFieldList  = new ArrayList<>();
                subFieldList.add(sysField);
                sysFieldListList.add(subFieldList);
            }else{
                if(sysFieldListList.size()==0){
                    sysFieldListList.add(new ArrayList<SysField>());
                }
                sysFieldListList.get(sysFieldListList.size()-1).add(sysField);
            }
        }

        switch (className) {
            case "SysDictionary":
                if (obj.get("parentId") != null) {
                    Integer parentId = Integer.valueOf(obj.get("parentId").toString());
                    SysDictionary parentDictionary = sysDictionaryService.getById(parentId);
                    obj.put("code", parentDictionary.getCode() + "_");
                }
                obj.put("type", "选项");

                break;
            case "SysMenu":
                if (obj.get("parentId") != null) {
                    Integer parentId = Integer.valueOf(obj.get("parentId").toString());
                    SysMenu sysMenu = sysMenuService.getById(parentId);
                    obj.put("level", sysMenu.getLevel() + 1);
                }
            case "SysConfiguration":
                if (obj.get("moduleDcode") != null) {
                    obj.put("code", sysDictionaryService.getOne(sysDictionaryService.getWrapper(MapUtil.getMap("code",obj.get("moduleDcode").toString()))).getValue() + "_");
                }
            case "SysImgConfig":
                if (obj.get("moduleDcode") != null) {
                    obj.put("code", sysDictionaryService.getOne(sysDictionaryService.getWrapper(MapUtil.getMap("code",obj.get("moduleDcode").toString()))).getValue() + "_");
                }
                break;
        }

        model.addAttribute("sample", obj);
        model.addAttribute("sysFieldList", sysFieldList);
        model.addAttribute("sysFieldListList", sysFieldListList);
        model.addAttribute("sysResponseParameter", new SysResponseParameter().setSysEntity(entityClass));
        model.addAttribute("activeUser", UserUtil.getLoginAccount());

        return "common/sysForm";
    }

    /**
     * 跳转通用表单页
     *
     * @param model
     * @param obj
     * @return
     */
    @RequestMapping("/detail/{className}")
    public String detail(Model model, @PathVariable("className") String className, @RequestParam Map<String, Object> obj) {
        //判断权限
        SysEntity entityClass = sysEntityService.getOne(sysEntityService.getWrapper(MapUtil.getMap("code",className)));
        //创建查询实例
        Map<String, Object> map = new MapUtil<>().put("entityId", entityClass.getId()).put("isUpdate", 1).build();
        //如果是批量修改，则查询可批量修改的 字段
        if (obj.get("batch") != null) map.put("isBatchUpdate",1);
        //查询字段
        List<SysField> sysFieldList = sysFieldService.resultFormat(sysFieldService.list(sysFieldService.getWrapper(map)));
        List<List<SysField>> sysFieldListList = new ArrayList<>();


        for (SysField sysField : sysFieldList) {
            if(StringUtils.isNotBlank(sysField.getGroupName())){
                List<SysField> subFieldList  = new ArrayList<>();
                subFieldList.add(sysField);
                sysFieldListList.add(subFieldList);
            }else{
                if(sysFieldListList.size()==0){
                    sysFieldListList.add(new ArrayList<SysField>());
                }
                sysFieldListList.get(sysFieldListList.size()-1).add(sysField);
            }
        }

        model.addAttribute("sample", obj);
        model.addAttribute("sysFieldList", sysFieldList);
        model.addAttribute("sysFieldListList", sysFieldListList);
        model.addAttribute("sysResponseParameter", new SysResponseParameter().setSysEntity(entityClass));
        model.addAttribute("activeUser", UserUtil.getLoginAccount());

        return "common/sysDetail";
    }


    /**
     * 下载导入模板
     *
     * @param className //实体类名
     * @return
     */
//	@RequestMapping("/importTemplate/{className}")
//    @ResponseBody
    public void importTemplate(HttpServletResponse response, @PathVariable("className") String className) {

        //获取数据
        SysEntity sysEntity = sysEntityService.getOne(sysEntityService.getWrapper(MapUtil.getMap("code",className)));
        //excel标题
        //查询可以导出的实体字段
        List<SysField> fieldList = sysFieldService.list(
                sysFieldService.getWrapper(new MapUtil<>()
                        .put("entityId",sysEntity.getId())
                        .put("isImport",1)
                        .put("isDelete",0).build()));
        String[] title = new String[fieldList.size()];
        for (int j = 0; j < fieldList.size(); j++) {
            title[j] = fieldList.get(j).getName();
        }
        //excel文件名
        String fileName = sysEntity.getName() + "导入模板.xls";
        //sheet名
        String sheetName = sysEntity.getName() + "信息表";
        //创建第一行数据
        String[][] content = new String[1][fieldList.size()];
        for (int j = 0; j < fieldList.size(); j++) {
            String contentText = "文本";
            //String contentText = fieldList.get(j).get==null?"文本":fieldList.get(j).getStorageType();
            if (StringUtils.isNotBlank(fieldList.get(j).getFormatCheckTypeDcode())) {

                contentText += "(" + fieldList.get(j).getFormatCheckTypeDcode() + ")";
            }
            content[0][j] = contentText;
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
        //给第二行说明字体设置为红色
        HSSFSheet sheet = wb.getSheet(sheetName);
        HSSFRow row = sheet.getRow(1);
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setColor(Font.COLOR_RED);
        style.setFont(font);
        for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
            row.getCell(i).setCellStyle(style);
        }

        //响应到客户端
        try {
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
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

    /**
     * 跳转地图页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/map")
    public String map(Model model, String textId, String textVal) {
        model.addAttribute("textInputId", textId);
        model.addAttribute("textInputVal", textVal);

        return "common/sysMap";
    }


    @RequestMapping("/importTemplate/{className}")
    @ResponseBody
    public void importTemplateNew(HttpServletResponse response, @PathVariable("className") String className) throws IOException, MalformedObjectNameException {
        //获取数据
        SysEntity sysEntity = sysEntityService.getOne(sysEntityService.getWrapper(MapUtil.getMap("code",className)));
        //excel标题
        //查询可以导出的实体字段
        List<SysField> fieldList = sysFieldService.list(
                sysFieldService.getWrapper(new MapUtil<>().put("entityId",sysEntity.getId()).put("isImport",1).put("isDelete",0).build()));
        String[] title = new String[fieldList.size()];

        if (fieldList != null && fieldList.size() > 0) {
            List<String> headers = new ArrayList<>();
            List<String[]> downData = new ArrayList<>();
            List<String> downRows = new ArrayList<>();
            for (int i = 0; i < fieldList.size(); i++) {
                headers.add(fieldList.get(i).getName());
                if ("field_assignment_singleselect".equals(fieldList.get(i).getAssignmentModeDcode()) ||
                        "field_assignment_multiselect".equals(fieldList.get(i).getAssignmentModeDcode())) {
                    downRows.add(String.valueOf(i));
                    String url = "http://localhost:" + Constant.SERVICE_PORT + "/" + fieldList.get(i).getOptionEntityPath() + "/list";
                    String param = fieldList.get(i).getOptionParamValue();
                    String rul = "";
                    if (StringUtils.isBlank(param) || "all".equals(param)) {
//                        rul = HttpUtil.doPost(url, null, "auth_token_data");
                    } else {
                        Map<String, Object> mapParam = new HashMap<>();
                        mapParam.put(fieldList.get(i).getOptionParamName(), param);
//                        rul = HttpUtil.doPost(url, mapParam, "auth_token_data");
                    }
                    try {
                        System.out.println(rul);
                        List<Map> dataList = JSONObject.parseArray(rul, Map.class);
                        String data = "";

                        for (int j = 0; j < dataList.size(); j++) {
                            if ("".equals(data)) {
                                if (StringUtils.isBlank(fieldList.get(i).getOptionNameFieldCode()))
                                    data += dataList.get(j).get("title");
                                else
                                    data += dataList.get(j).get(fieldList.get(i).getOptionNameFieldCode());
                                if (StringUtils.isBlank(fieldList.get(i).getOptionValueFieldCode()))
                                    data += "&@" + dataList.get(j).get("id");
                                else
                                    data += "&@" + dataList.get(j).get(fieldList.get(i).getOptionValueFieldCode());
                            } else {
                                if (StringUtils.isBlank(fieldList.get(i).getOptionNameFieldCode()))
                                    data += "," + dataList.get(j).get("title");
                                else
                                    data += dataList.get(j).get(fieldList.get(i).getOptionNameFieldCode());
                                if (StringUtils.isBlank(fieldList.get(i).getOptionValueFieldCode()))
                                    data += "&@" + dataList.get(j).get("id");
                                else
                                    data += "&@" + dataList.get(j).get(fieldList.get(i).getOptionValueFieldCode());

                            }
                        }
                        downData.add(data.split(","));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if ("field_assignment_radio".equals(fieldList.get(i).getAssignmentModeDcode())) {
                    downData.add(fieldList.get(i).getRadioOptions().split(","));
                }

            }

            String newFilePath = Constant.UPLOAD_FILE_PATH + "excel" + File.separator +
                    new Date().getTime() +
                    File.separator + "导入模板.xlsx";
            String path = ExcelUtils.createExcelTemplate(newFilePath, "信息表",
                    headers, downData, downRows);

            //响应到客户端
            try {
                // path是指欲下载的文件的路径。
                File file = new File(path);
                // 取得文件名。
                String filename = file.getName();
                // 取得文件的后缀名。
                String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

                // 以流的形式下载文件。
                InputStream fis = new BufferedInputStream(new FileInputStream(path));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                // 清空response
                response.reset();
                // 设置response的Header
                response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "ISO8859-1"));
                response.addHeader("Content-Length", "" + file.length());
                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                toClient.write(buffer);
                toClient.flush();
                toClient.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }


}
