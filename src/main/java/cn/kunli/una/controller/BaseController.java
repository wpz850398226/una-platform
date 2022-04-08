package cn.kunli.una.controller;


import cn.hutool.core.util.StrUtil;
import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.pojo.sys.*;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.service.sys.*;
import cn.kunli.una.utils.BaseUtil;
import cn.kunli.una.utils.common.DateUtil;
import cn.kunli.una.utils.common.*;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* @author 作者 : Ponzio
* @version 创建时间：2019年6月10日 上午10:41:12
* 类说明 :controller父类
*/
@Slf4j
public abstract class BaseController<S extends BasicService,T extends BasePojo>{

	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected S service;
	@Autowired
	protected SysFieldService sysFieldService;
	@Autowired
	protected SysAccountService sysAccountService;
	@Autowired
	protected SysEntityService sysEntityService;
	@Autowired
	protected SysRoleService sysRoleService;
	@Autowired
	protected SysDictionaryService sysDictionaryService;
	@Autowired
	protected SysRolePermissionService sysRolePermissionService;
	@Autowired
	protected SysDataService sysDataService;
	@Autowired
	protected SysPermissionService sysPermissionService;
	@Autowired
	protected SysConfigurationService sysConfigurationService;
	@Autowired
	protected SysArticleService sysArticleService;
	@Autowired
	protected SysSortService sysSortService;

	// 当前Service上泛型的字节码对象
	protected Class<T> entityClass;
	protected String entityClassName;

	{
		// 读取当前类上的泛型
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		entityClass = (Class<T>) type.getActualTypeArguments()[1];
		entityClassName = entityClass.getSimpleName();
	}

	@PostMapping("")
	@ResponseBody
	@ApiOperation(value = "新增",notes = "")
	public SysResult add(@Valid @RequestBody T entity) {
		return service.saveRecord(entity);
	}

	/**
	 * 保存/添加或修改
	 * @param obj
	 * @return
	 */
	@PostMapping("/save")
	@ResponseBody
	@ApiOperation(value = "保存",notes = "")
	public SysResult save(@Valid T entity) {
		//验证权限
		SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
		if(loginUser.getRoleId().indexOf("100000")==-1){
			if(!UserUtil.isPermitted(entityClassName+":create",entityClassName+":update"))return SysResult.fail("无权操作");
		}

		if(entity.getId()!=null) {
			//如果id不为空，说明是修改数据
			return service.updateRecordById(entity);
		}else if(StrUtil.isNotBlank(entity.getIds())){
			//如果ids不为空，说明是批量修改数据
			String[] idsArray = entity.getIds().split(",");
			for (String id : idsArray) {
				entity.setId(Integer.valueOf(id));
				service.updateRecordById(entity);
			}
			return SysResult.success();

		}else {//如果id为空，说明是新增数据
			//插入数据
			return service.saveRecord(entity);
		}
	}

	@DeleteMapping("/{ids}")
	@ResponseBody
	@ApiOperation(value = "id删除",notes = "支持批量删除")
	public SysResult delete(@PathVariable Integer... ids) {
		for (Integer id : ids) {
			boolean deleteResult = service.deleteById(id);
			if(!deleteResult)return SysResult.fail();
		}
		return SysResult.success();
	}

	@PutMapping("")
	@ResponseBody
	@ApiOperation(value = "修改",notes = "")
	public SysResult update(@RequestBody T entity) {
		if(entity.getId()!=null) {
			//如果id不为空，说明是id修改数据
			return service.updateRecordById(entity);
		}else if(StrUtil.isNotBlank(entity.getIds())){
			//如果ids不为空，说明是批量修改数据
			String[] idsArray = entity.getIds().split(",");
			for (String id : idsArray) {
				entity.setId(Integer.valueOf(id));
				SysResult sysResult = service.updateRecordById(entity);
				if(!sysResult.getIsSuccess())return sysResult;
			}
			return SysResult.success();
		}
		return SysResult.fail("修改失败，id为空");
	}

	/**
	 * 提高顺序
	 * @param id
	 * @return
	 */
	@SneakyThrows
	@PutMapping("/ascend/{id}")
	@ResponseBody
	@ApiOperation(value = "id升序",notes = "")
	public SysResult ascend(@PathVariable Serializable id) {
		//查询需要升序的记录
		T ascendRecord = (T) service.getById(id);
		if(ascendRecord!=null){
			Integer sortOrder = ascendRecord.getSortOrder();
			if(sortOrder!=null){
				//获取当前类对应实体类对象
				SysEntity sysEntity = service.getEntity();
				if(sysEntity!=null){
					//默认：升序 则顺序-1
					Integer sortValue = -1;
					List<SysSort> sortList = sysSortService.selectList(UnaMapUtil.getMap("entityId", sysEntity.getId()));
					//如果单以排序字段排序 且 倒序排序，则 升序时 顺序+1，即修改sortValue为1
					if(CollectionUtils.isNotEmpty(sortList)&&sortList.size()==1){
						SysSort theSort = sysSortService.parse(sortList).get(0);
						if(theSort.getIsSortField()&&!theSort.getSortord()){
							sortValue = 1;
						}
					}

					Map<String, Object> paramMap = UnaMapUtil.getMap("sortOrder", sortOrder + sortValue);
					//获取父字段字段类对象
					SysField sysField = sysFieldService.getById(sysEntity.getParentFieldId());
					if(sysField!=null){
						//获取父字段
						Field parentField = entityClass.getDeclaredField(sysField.getAssignmentCode());
						parentField.setAccessible(true);
						//获取父字段值
						Object parentValueObj = parentField.get(ascendRecord);
						paramMap.put(sysField.getAssignmentCode(),parentValueObj);
					}

					//查询要降序的记录
					BasePojo descendRecord = service.selectOne(paramMap);
					if(descendRecord == null) return SysResult.fail("顺序已至顶，无法升序，请尝试修改权重");

					//目标记录降序
					T descT = entityClass.newInstance();
					descT.setId(descendRecord.getId()).setSortOrder(sortOrder);
					service.updateRecordById(descT);
					//本记录 升序
					T ascT = entityClass.newInstance();
					ascT.setId(ascendRecord.getId()).setSortOrder(sortOrder + sortValue);
					return service.updateRecordById(ascT);
				}

			}
			return SysResult.fail("顺序为空或无法提升");
		}
		return SysResult.fail("记录不存在");
	}

	@GetMapping("/{id}")
	@ResponseBody
	@ApiOperation(value = "id查询",notes = "")
	public SysResult get(@PathVariable Serializable id) {
		T record = (T) service.getById(id);
		return new SysResult().success(service.parse(UnaListUtil.getList(record)).get(0));
	}

	@SneakyThrows
	@GetMapping("/page")
	@ResponseBody
	@ApiOperation(value = "分页查询",notes = "")
	public SysResult page(@RequestParam Map<String, Object> map) {
		SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
		Object current = map.get("pageNum");
		Object size = map.get("pageSize");
		map.remove("pageNum");
		map.remove("pageSize");
		//经过处理的请求参数
		Map<String,Object> handledParamMap = new HashMap<>();

		if(loginUser.getRoleId().indexOf("100000")==-1){
			//如果不是超级管理员，查询权限范围
			SysEntity sysEntity = service.getEntity();
			SysPermission sysPermission = sysPermissionService.selectOne(UnaMapUtil.buildHashMap().put("entityId", sysEntity.getId()).put("type_dcode", "permission_type_retrieve").build());
			if(sysPermission!=null&&loginUser!=null){
				Integer scopeCode = 0;
				List<SysRolePermission> rolePermissionList = sysRolePermissionService.selectList(UnaMapUtil.buildHashMap().put("permissionId", sysPermission.getId()).put("in:roleId", loginUser.getRoleId()).build());
				if(CollectionUtils.isNotEmpty(rolePermissionList)){
					for (SysRolePermission sysRolePermission : rolePermissionList) {
						if(StrUtil.isNotBlank(sysRolePermission.getScopeDcode())){
							String scopeDcode = sysRolePermission.getScopeDcode();
							Integer scope = Integer.valueOf(scopeDcode.substring(scopeDcode.lastIndexOf("_")+1));
							if(scope>scopeCode)scopeCode = scope;
						}
					}
				}

				if(scopeCode>=0){
					//有查询权限
					switch (scopeCode) {
						case 100:
							//查询全部
							break;
						case 50:
							//查询公司范围
							map.put("companyId",loginUser.getCompanyId());
							break;
						case 10:
							//查询部门范围
							map.put("departmentId",loginUser.getDepartmentId());
							break;
						case 1:
							//查询个人范围
							map.put("creatorId",loginUser.getId());
							break;
						default:
							//无权限
							map.put("creatorId","-1");
							break;
					}
				}
			}
		}

		//处理查询条件
        if(MapUtils.isNotEmpty(map)){
            SysEntity sysEntity = sysEntityService.selectOne(UnaMapUtil.getMap("code", entityClassName));
            //如果是虚拟实体
			if(entityClassName.equals("SysData")&&map.containsKey("entityId")){
				sysEntity = sysEntityService.getById(map.get("entityId").toString());
				map.remove("entityId");
			}

			//处理模糊查询条件
            if(sysEntity!=null&&MapUtils.isNotEmpty(map)){
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String displayCode = entry.getKey();
                    int i = displayCode.indexOf(":");
                    if(i==0){
                    	//只处理模糊查询的情况
                        displayCode = displayCode.substring(i+1);
						SysField sysField = sysFieldService.selectOne(UnaMapUtil.buildHashMap().put("entityId", sysEntity.getId()).put("displayCode", displayCode).build());
						String assignmentCode = sysField.getAssignmentCode();
						if(sysField!=null&&!assignmentCode.equals(displayCode)){
							//如果赋值编码不等于取值编码，换查询条件
							SysResult assignmentValue = sysFieldService.getAssignmentValue(displayCode, String.valueOf(entry.getValue()), service, null);
							if(assignmentValue.getIsSuccess()){
								handledParamMap.put("in:"+assignmentCode,assignmentValue.getData());
//								map.put("in:"+assignmentCode,assignmentValue.getData());
//								map.put(entry.getKey().replace(displayCode,assignmentCode),assignmentValue.getData());
//								map.remove(entry.getKey());
							}
						}
                    }else{
						handledParamMap.put(entry.getKey(),entry.getValue());
					}
                }
            }
        }
		IPage page = service.page(current,size, handledParamMap);
		List<T> list = service.parse(page.getRecords());
		//判断是否是统计查询
		if(map.containsKey("groupBy")&&CollectionUtils.isNotEmpty(list)){
			Integer total = 0;
			String groupByField = map.get("groupBy").toString();
			Field declaredField = entityClass.getDeclaredField(groupByField);
			declaredField.setAccessible(true);
			for (T record : list) {
				Object groupByValue = declaredField.get(record);
				int count = service.selectCount(UnaMapUtil.getMap(groupByField, groupByValue));
				record.setCount(count);
				total += count;
			}
			page.setTotal(total);
		}

		page.setRecords(list);
		return new SysResult().success(page.getRecords(),page.getTotal());
	}

	/**
	 * ajax查询多条
	 * @return
	 */
	@GetMapping("/list")
	@ResponseBody
	@ApiOperation(value = "批量查询",notes = "")
	public List<T> list(@RequestParam Map<String, Object> map) {
		List list = service.selectList(map);
		return service.parse(list);
	}

	@PostMapping("/import")
	@ResponseBody
	@ApiOperation(value = "导入",notes = "")
	public SysResult importObj(MultipartFile file) {
		SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
		//验证权限
		if(!UserUtil.isPermitted(entityClassName+":import"))return SysResult.fail("无权操作");
		List<Map<String, Object>> insertMapList = new ArrayList<>();
		List<Map<String, Object>> repeatMapList = new ArrayList<>();

		Workbook workbook;
		try {
			workbook = WorkbookFactory.create(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);

			// 获得行数
			int rowNum = sheet.getLastRowNum(); // 总行数
			//新增 数据条数
			int insertAmount = 0;
			//更新数据条数
			int updateAmount = 0;

			//获得首行
			Row headRow = sheet.getRow(0);
			//获得列数
			int colNum = headRow.getLastCellNum();

			//查询对应实体类
			SysEntity sysEntity = sysEntityService.selectOne(UnaMapUtil.getMap("code",entityClassName));
			//查询需要导入的实体字段
			Map<String, Object> map = new UnaMapUtil<>().put("entityId", sysEntity.getId()).put("isImport",1).build();
			List<SysField> fieldList = sysFieldService.selectList(map);
			if(UnaListUtil.isNotNull(fieldList)) {
				//如果需要导入的字段不为空且与excel列数相等
				if(fieldList.size()==colNum) {
					for(int j=0;j<colNum;j++) {
						if(headRow.getCell(j).getStringCellValue().trim()==null||!headRow.getCell(j).getStringCellValue().trim().equals(fieldList.get(j).getName())) {
							return SysResult.fail("第"+(j+1)+"列表头错误");
						}
					}
				}else {
					//如果需要导入的字段不为空且与excel列数不等
					return SysResult.fail("模板错误，请重新下载模板");
				}
			}

			//表格取值，从第3行开始读取
			for(int i=1;i<=rowNum;i++) {//遍历行
				//行内所有表格数据保存如map中
				Map<String, Object> dataMap = new HashMap<>();
				dataMap.put("creatorId",loginUser.getId());
				dataMap.put("id", BaseUtil.getPrimaryId());
				dataMap.put("isDelete", 0);
				//本行数据是否有重复项
				Boolean isRepeat = false;
				//Map<String, Object> uniqueSampleMap = new HashMap<>();			//存放非重复项字段及数据，供查询数据库是否有重复数据

				Row row = sheet.getRow(i);
				for(Cell cell:row) {
					cell.setCellType(CellType.STRING);
				}
				for(int j=0;j<colNum;j++) {//遍历行内列
					SysField sysField = fieldList.get(j);
					Cell cell = row.getCell(j);
					String cellValue = cell.getStringCellValue().trim();
					/**
					 * 判断是否是必填项且是否为空
					 */
					if(StrUtil.isNotBlank(sysField.getFormatCheckTypeDcode())
							&&sysField.getFormatCheckTypeDcode().indexOf("required")!=-1
							&&StrUtil.isBlank(cellValue)) {
						//如果必填项为空
						return SysResult.fail("导入失败，第"+(i+1)+"行第"+(j+1)+"列内容为空");
					}

					//将表格数据保存到行map中
					if (StrUtil.isNotBlank(cellValue) && cellValue.indexOf("&@") != -1){
						String data = cellValue.split("&@")[1];
						dataMap.put(sysField.getAssignmentCode(),data);
					}else{
						dataMap.put(sysField.getAssignmentCode(),cellValue);
					}

					//判断重复项字段值是否重复
					if(!isRepeat){//如果本行已经检测出有重复项，则不进一步检测
						//反射获取查询样本类
						T sample = entityClass.newInstance();
						if(StrUtil.isNotBlank(sysField.getDataCheckTypeDcode())
								&&sysField.getDataCheckTypeDcode().indexOf("global_unique")!=-1
								&&StrUtil.isNotBlank(cellValue)) {
							//用样本类在数据库查询是否有重复数据
							List<T> objList = service.selectList(UnaMapUtil.getMap(sysField.getAssignmentCode(), cellValue));
							if(UnaListUtil.isNotNull(objList)) {
								//有重复数据，设置本行有重复项为true，并加入到重复项数据集合
								isRepeat = true;
							}
						}
					}
				}

				//判断本行是否有重复数据
				if(isRepeat){
					repeatMapList.add(dataMap);
				}else {
					//如果没有重复
					insertMapList.add(dataMap);
				}

			}

			//批量插入不重复的数据
			if(insertMapList.size()>0) {
				Integer num = 0;
				for (Map<String, Object> paramMap : insertMapList) {
					T entity = JSON.parseObject(JSON.toJSONString(paramMap), entityClass);
					SysResult sysResult = service.saveRecord(entity);
					if(sysResult.getIsSuccess())num++;
				}
				if(insertMapList.size() == num) {
					//批量插入成功
					return SysResult.success("导入成功！本次导入共"+num+"条数据");
				}else {
					return new SysResult().fail("导入失败",insertMapList);
				}
			}

		} catch (IOException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
		return SysResult.fail();
	}

	@RequestMapping("/exportPermission")
	@ResponseBody
	@ApiOperation(value = "导出鉴权",notes = "")
	public SysResult exportPermission() {
		//验证权限
		if(UserUtil.isPermitted(entityClassName+":export")){
			return SysResult.success("有权限");
		}
		return SysResult.fail("无权操作");

	}

	@SneakyThrows
	@RequestMapping("/export")
	@ApiOperation(value = "导出",notes = "")
	public void exportObj(HttpServletResponse response, @RequestParam Map<String, Object> map) {
		//获取数据
		List<T> objList = service.parse(service.selectList(map));
		//查询对应实体类
		SysEntity sysEntity = sysEntityService.selectOne(UnaMapUtil.getMap("code",entityClassName));
		//查询可以导出的实体字段
		List<SysField> fieldList = sysFieldService.selectList(UnaMapUtil.buildHashMap().put("entityId",sysEntity.getId()).put("isExport",1).build());
		//excel标题行
		String[] title = new String[fieldList.size()+1];
		title[0] = "序号";
		for(int j = 0; j < fieldList.size(); j++) {
			title[j+1]=fieldList.get(j).getName();
		}
		//excel文件名
		String fileName = sysEntity.getName()+"信息表"+ DateUtil.getStrOfTime(new Date()) +".xls";
		//sheet名
		String sheetName = sysEntity.getName()+"信息表";

		String [][] content = new String[objList.size()][fieldList.size()+1];
		for (int i = 0; i < objList.size(); i++) {
			T theData = objList.get(i);
			content[i][0] = i+1+"";
			//遍历需要导出的字段
			for(int j = 0; j < fieldList.size(); j++) {
				SysField sysField = fieldList.get(j);
				String displayModeDcode = sysField.getDisplayModeDcode();
				Object value;
				if(sysField.getAssignmentCode().equals(sysField.getDisplayCode())){
					//赋值编码与取值编码相同
					value = EntityUtil.getFieldValue(theData.getClass(), theData, sysField.getAssignmentCode());

				}else{
					//赋值编码与取值编码不同，从map中取值
					value = theData.getMap().get(sysField.getDisplayCode());
				}

				if(value!=null){
					//如果取值不为空，判断展示方式
					switch(displayModeDcode){
						case "field_display_whether":	//判断 类型
							if(value.equals(true)){
								value = "是";
							}else{
								value = "否";
							}
							break;
						case "field_display_time":	//判断 类型
							SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
							Date date = sdf.parse(String.valueOf(value));
							value = DateUtil.getStrOfDate(date);
							break;
						default:
							break;
					}
					content[i][j+1] = String.valueOf(value);
				}else {
					content[i][j+1] = "/";
				}

			}

		}

		//创建HSSFWorkbook
		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
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
