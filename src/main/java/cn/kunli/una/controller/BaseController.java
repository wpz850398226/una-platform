package cn.kunli.una.controller;


import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.pojo.system.SysField;
import cn.kunli.una.annotation.LogAnnotation;
import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.pojo.vo.*;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.service.system.SysEntityService;
import cn.kunli.una.service.system.SysFieldService;
import cn.kunli.una.service.system.SysRolePermissionService;
import cn.kunli.una.service.system.SysRoleService;
import cn.kunli.una.utils.BaseUtil;
import cn.kunli.una.utils.common.*;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* @author 作者 : Ponzio
* @version 创建时间：2019年6月10日 上午10:41:12
* 类说明 :controller父类
*/
public abstract class BaseController<S extends BasicService,T extends BasePojo>{

	private static final Logger log = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected S service;
	@Autowired
	protected SysFieldService sysFieldService;
	@Autowired
	protected SysEntityService sysEntityService;
	@Autowired
	protected SysRoleService sysRoleService;
	@Autowired
	protected SysRolePermissionService sysRolePermissionService;

	// 当前Service上泛型的字节码对象
	protected Class<T> entityClass;
	protected String entityClassName;

	{
		// 读取当前类上的泛型
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		entityClass = (Class<T>) type.getActualTypeArguments()[1];
		entityClassName = entityClass.getSimpleName();
	}

	@RequestMapping(
			value = {""},
			method = {RequestMethod.POST}
	)
	@ResponseBody
	public SysResult add(T entity) {
		boolean saveResult = service.save(entity);
		if(saveResult){
			return SysResult.success();
		}
		return SysResult.fail();
	}

	@RequestMapping(
			value = {"/{id}"},
			method = {RequestMethod.GET}
	)
	@ResponseBody
	public SysResult get(@PathVariable Serializable id) {
		BasePojo basePojo = service.selectById(id);
		return new SysResult().success(basePojo);
	}

	@RequestMapping(
			value = {""},
			method = {RequestMethod.PUT}
	)
	@ResponseBody
	public SysResult update(T entity) {
		boolean updateResult = service.updateById(entity);
		if(updateResult){
			return SysResult.success();
		}
		return SysResult.fail();
	}

	@RequestMapping(
			value = {"/{ids}"},
			method = {RequestMethod.DELETE}
	)
	@ResponseBody
	public SysResult remove(@PathVariable Integer... ids) {
		for (Integer id : ids) {
			boolean removeResult = service.removeById(id);
			if(!removeResult)return SysResult.fail();
		}
		return SysResult.success();
	}

	@RequestMapping(
			value = {"/all"},
			method = {RequestMethod.GET}
	)
	@ResponseBody
	public SysResult all() {
		List list = service.list();
		return new SysResult().success(list,Long.valueOf(list.size()));
	}

	@RequestMapping(
			value = {"/page"},
			method = {RequestMethod.GET}
	)
	@ResponseBody
	public SysResult page(@RequestParam Map<String, Object> paramMap) {
//		SysParamMap sysParamMap = new SysParamMap(params);
//		Page page = service.selectPage(sysParamMap);
		SysParam sysParam = new SysParam(paramMap);
		Page page = service.selectPage(sysParam);
		return new SysResult().success(page.getRecords(),page.getTotal());
	}

	/**
	 * ajax分页查询列表页
	 * @return
	 */
	//@PreAuthorize("hasAuthority('qweqweqwe')")
	/*@RequestMapping("/table")
	@ResponseBody
	public SysResult table(@RequestParam Map<String, Object> params) {
		//判断权限
		//if(!UserUtil.isPermitted(className+":retrieve"))return SysResult.fail("无权操作");
		SysParamMap sysParamMap = new SysParamMap(params);
		if (sysParamMap.getPageNum() != null && sysParamMap.getPageSize() != null)
		PageHelper.startPage(sysParamMap.getPageNum(), sysParamMap.getPageSize());
		List<T> list = service.selectBySelective(sysParamMap);
		if(CollectionUtils.isEmpty(list))return new SysResult().setCode(0);
		PageInfo<T> pageInfo = new PageInfo<>(list);
		return new SysResult(0,"查询成功",list,pageInfo.getTotal());
	}*/

	/**
	 * 保存/添加或修改
	 * @param obj
	 * @return
	 */
	@LogAnnotation
	@RequestMapping("/save")
	@ResponseBody
	public SysResult save(@Valid T entity) {
		//验证权限
		SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
		if(loginUser.getRoleIds().indexOf("100000")==-1){
			if(!UserUtil.isPermitted(entityClassName+":create",entityClassName+":update"))return SysResult.fail("无权操作");
		}

		if(entity.getId()!=null) {
			//如果id不为空，说明是修改数据
			//修改数据
			boolean updateResult = service.updateById(entity);
			if(updateResult){
				return SysResult.success();
			}
			return SysResult.fail();
		}else if(StringUtils.isNotBlank(entity.getIds())){
			//如果ids不为空，说明是批量修改数据
			String[] idsArray = entity.getIds().split(",");
			for (String id : idsArray) {
				entity.setId(Integer.valueOf(id));
				service.updateById(entity);
			}
			return SysResult.success();

		}else {//如果id为空，说明是新增数据
			//插入数据
			boolean saveResult = service.save(entity);
			if(saveResult){
				return SysResult.success();
			}
			return SysResult.fail();
		}
	}

	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	/*@RequestMapping("/delete")
	@ResponseBody
	public SysResult delete(List<Integer> ids) {
		//验证权限
		//if(!UserUtil.isPermitted(className+":delete"))return SysResult.fail("无权操作");
		SysResult result=new SysResult();
		Integer num = 0;

		if(ids!=null&&ids.length!=0) {
			for(Object id:ids) {
				num += service.deleteByPrimaryKey(id);
			}
		}

		if(num == ids.length){

			try {
				result = SysResult.success();
			} catch (Exception e) {
				e.printStackTrace();
				result = new SysResult();
			}

		}
		return result;
	}*/

	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	/*@RequestMapping("/deleteLogically")
	@ResponseBody
	public SysResult deleteLogically(Integer[] ids) {
		//验证权限
		if(!UserUtil.isPermitted(entityClassName+":delete"))return SysResult.fail("无权操作");
		SysResult result=new SysResult();
		Integer num = 0;

		if(ids!=null&&ids.length!=0) {
			for(Integer id:ids) {
				num += service.deleteLogicallyByPrimaryKey(id);
			}
		}

		if(num == ids.length){

			try {
				result = SysResult.success();
			} catch (Exception e) {
				e.printStackTrace();
				result = new SysResult();
			}

		}
		return result;
	}*/

	/**
	 * ajax查询所有
	 * @return
	 */
	@GetMapping("/list")
	@ResponseBody
	public SysResult list(@RequestParam Map<String, Object> params) {
		List list = service.selectBySelective(new SysParamMap(params));
		return new SysResult().setData(list).setCount(Long.valueOf(list.size()));
	}

	/**
	 * ajax查询所有
	 * @return
	 */
	@RequestMapping("/queryPlural")
	@ResponseBody
	public List<T> queryPlural(@RequestParam Map<String, Object> params) {
		return service.selectBySelective(new SysParamMap(params));
	}

	/**
	 * ajax查询菜单
	 * @return
	 */
	@RequestMapping("/querySingle")
	@ResponseBody
	public T querySingle(@RequestParam Map<String, Object> params) {
		List<T> list = service.selectBySelective(new SysParamMap(params));
		return list.get(0);
	}

	@RequestMapping("/import")
	@ResponseBody
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
			SysEntity sysEntity = sysEntityService.selectOne(MapUtil.getMap("code",entityClassName));
			//查询需要导入的实体字段
			List<SysField> fieldList = sysFieldService.selectBySelective(SysParamMap.MapBuilder.aMap().put("entityId",sysEntity.getId()).put("isImport",1).build());
			if(ListUtil.isNotNull(fieldList)) {
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
					if(StringUtils.isNotBlank(sysField.getFormatDetectionTypeDvalues())
							&&sysField.getFormatDetectionTypeDvalues().indexOf("required")!=-1
							&&StringUtils.isBlank(cellValue)) {
						//如果必填项为空
						return SysResult.fail("导入失败，第"+(i+1)+"行第"+(j+1)+"列内容为空");
					}

					//将表格数据保存到行map中
					if (StringUtils.isNotBlank(cellValue) && cellValue.indexOf("&@") != -1){
						String data = cellValue.split("&@")[1];
						dataMap.put(sysField.getAssignmentCode(),data);
					}else{
						dataMap.put(sysField.getAssignmentCode(),cellValue);
					}

					//判断重复项字段值是否重复
					if(!isRepeat){//如果本行已经检测出有重复项，则不进一步检测
						//反射获取查询样本类
						T sample = entityClass.newInstance();
						if(StringUtils.isNotBlank(sysField.getDataDetectionTypeDvalues())
								&&sysField.getDataDetectionTypeDvalues().indexOf("global_unique")!=-1
								&&StringUtils.isNotBlank(cellValue)) {
							//用样本类在数据库查询是否有重复数据
							List<T> objList = service.selectList(MapUtil.getMap(sysField.getAssignmentCode(),cellValue));
							if(ListUtil.isNotNull(objList)) {
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
				for (Map<String, Object> map : insertMapList) {
					T entity = JSON.parseObject(JSON.toJSONString(map), entityClass);
					boolean saveResult = service.save(entity);
					if(saveResult)num++;
				}
				if(insertMapList.size() == num) {
					//批量插入成功
					return SysResult.success("导入成功！本次导入共"+num+"条数据");
				}else {
					return new SysResult().fail("导入失败",insertMapList);
				}
			}

		} catch (IOException | IllegalAccessException | InstantiationException | InvalidFormatException e) {
			e.printStackTrace();
		}
		return SysResult.fail();
	}

	@RequestMapping("/exportPermission")
	@ResponseBody
	public SysResult exportPermission() {
		//验证权限
		if(UserUtil.isPermitted(entityClassName+":export")){
			return SysResult.success("有权限");
		}
		return SysResult.fail("无权操作");

	}


	@RequestMapping("/export")
	public void exportObj(HttpServletResponse response, @RequestParam Map<String, Object> params) {
		//获取数据
		List<T> objList = service.selectBySelective(new SysParamMap(params));
		//查询对应实体类
		SysEntity sysEntity = sysEntityService.selectOne(MapUtil.getMap("code",entityClassName));
		//查询可以导出的实体字段
		List<SysField> fieldList = sysFieldService.selectList(SysParamMap.MapBuilder.aMap().put("entityId",sysEntity.getId()).put("isExport",1).build());
		//excel标题行
		String[] title = new String[fieldList.size()+1];
		title[0] = "序号";
		for(int j = 0; j < fieldList.size(); j++) {
			title[j+1]=fieldList.get(j).getName();
		}
		//excel文件名
		String fileName = sysEntity.getName()+"信息表"+ TimeUtil.getStrOfTime(new Date()) +".xls";
		//sheet名
		String sheetName = sysEntity.getName()+"信息表";

		String [][] content = new String[objList.size()][fieldList.size()+1];
		for (int i = 0; i < objList.size(); i++) {
			T theData = objList.get(i);
			content[i][0] = i+1+"";
			for(int j = 0; j < fieldList.size(); j++) {
				char[] chars=fieldList.get(j).getDisplayCode().toCharArray();
				chars[0]-=32;
				String methodName = "get"+String.valueOf(chars);
				try {
					Method m = entityClass.getMethod(methodName);
					Object o = m.invoke(theData);
					if(o!=null) {//如果数据不为空

						if(fieldList.get(j).getDisplayModeDcode()!=null&&fieldList.get(j).getDisplayModeDcode().equals("cd7f6c3db0864b9788e7b063ef68df98")) {
							//展示类型为时间类型
							String oStr = o.toString();
							SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
							Date date = sdf.parse(oStr);
							//Date date = new Date(oStr);
							String formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
							content[i][j+1] = formatDate;
						}else {
							content[i][j+1] = o.toString();
						}
					}else {
						content[i][j+1] = "";
					}

				} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				} catch (ParseException e) {
					e.printStackTrace();
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

	/**
	 * 提高顺序
	 * @param id
	 * @return
	 */
	@RequestMapping("increaseOrder")
	@ResponseBody
	public SysResult increaseOrder(String id) {
		Integer num = service.increaseOrder(id);
		if(num==2){
			return SysResult.success();
		}else{
			return new SysResult();
		}
	}

	/**
	 * 刷新redis配置类缓存
	 * @return
	 */
	@RequestMapping("refreshRedis")
	@ResponseBody
	public SysResult refreshRedis(String code) {
		return service.refreshRedis(code);
	}
}
