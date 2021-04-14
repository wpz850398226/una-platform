package cn.kunli.una.service;

import cn.kunli.una.handler.BaseMapper;
import cn.kunli.una.mapper.CommonMapper;
import cn.kunli.una.mapper.SysEntityMapper;
import cn.kunli.una.mapper.SysFieldMapper;
import cn.kunli.una.mapper.SysSortMapper;
import cn.kunli.una.pojo.system.*;
import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysParamMap;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.system.SysEntityService;
import cn.kunli.una.service.system.SysFieldService;
import cn.kunli.una.task.RedisTask;
import cn.kunli.una.utils.common.*;
import cn.kunli.una.utils.redis.RedisUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.NaturalId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;

@Transactional
public abstract class BaseService<M extends BaseMapper<T>,T extends BasePojo> {

    private Logger log = LoggerFactory.getLogger(BaseService.class);

    //全局参数
    protected M mapper;

    @Autowired
    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected RedisUtil<T> redisUtil;
    @Autowired
    protected SysEntityService sysEntityService;
    @Autowired
    protected SysFieldService sysFieldService;
    @Autowired
    protected SysSortMapper sysSortMapper;
    @Autowired
    protected CommonMapper commonMapper;
    @Autowired
    protected SysEntityMapper sysEntityMapper;

    // 当前Service上泛型的字节码对象
    protected Class<T> clazz;
    protected Class<? extends Mapper> mapperClazz;

    {
        // 读取当前类上的泛型
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) type.getActualTypeArguments()[1];
    }

    /**
     * 根据主键进行查询
     *
     * @param id
     * @return
     */
    @Cacheable(value = "entityRecord", keyGenerator = "myCacheKeyGenerator", unless = "#result == null")
    public T selectByPrimaryKey(Object id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    /**
     * 根据条件查询1条数据。
     *
     * @param record
     * @return
     */
    public T selectOne(T record) {
        return this.mapper.selectOne(record);
    }

    /**
     * 根据条件查询多条数据
     *
     * @param record
     * @return
     */
    //@Cacheable(value = "entityRecordList", unless = "#result == null || #result.size() == 0")
    public List<T> select(T record) {
        return this.mapper.select(record);
    }

    /**
     * 综合查询
     *
     * @param record
     * @return
     */
    public List<T> selectByExample(T record) {
        return this.mapper.selectByExample(record);
    }

    /**
     * 综合查询数量
     *
     * @param record
     * @return
     */
    public Integer selectCount(T record) {
        return this.mapper.selectCount(record);
    }

    /**
     * 插入数据,只操作record中的非空属性
     *
     * @param record
     * @return
     */
    public SysResult insertSelective(T record) {

        SysResult validationResult = this.validation(record);
        if (validationResult.getCode() != 200) return validationResult;
        int insertNum = this.mapper.insertSelective(this.saveFormat(record));
        if (insertNum > 0) {
            return SysResult.success();
        } else {
            return SysResult.fail();
        }
    }


    /**
     * 更新数据
     *
     * @param record
     * @return
     */
    public SysResult updateByPrimaryKey(T record) {
        SysResult validationResult = this.validation(record);
        if (validationResult.getCode() != 200) return validationResult;
        int updateNum = this.mapper.updateByPrimaryKey(this.saveFormat(record));
        if (updateNum > 0) {
            return SysResult.success();
        } else {
            return SysResult.fail();
        }
    }

    /**
     * 更新数据,只操作record中的非空属性
     *
     * @param record
     * @return
     */
    @SneakyThrows
    @CacheEvict(value = "entityRecord", keyGenerator = "myCacheKeyGenerator")
    public SysResult updateByPrimaryKeySelective(T record) {
        this.deleteFromCacheByCode(record.getId());
        SysResult validationResult = this.validation(record);
        if (validationResult.getCode() != 200) return validationResult;
        int updateNum = this.mapper.updateByPrimaryKeySelective(this.saveFormat(record));
        return (updateNum > 0 ? SysResult.success():SysResult.fail());
    }

    /**
     * 根据主键进行删除
     *
     * @param id
     * @return
     */
    @SneakyThrows
    @CacheEvict(value = "entityRecord", keyGenerator = "myCacheKeyGenerator")
    public Integer deleteByPrimaryKey(Object id) {
        String className = clazz.getSimpleName();
        SysEntity sysEntity = sysEntityService.queryFromRedis(className);
        //获取当前类对应实体类对象
        if(sysEntity!=null) {
            //获取父字段字段类对象
            SysField sysField = sysFieldService.selectByPrimaryKey(sysEntity.getParentFieldId());
            String tableName = StringUtil.upperCharToUnderLine(className);
            String fieldCode = sysField == null ? "" : StringUtil.upperCharToUnderLine(sysField.getAssignmentCode());
            commonMapper.increaseOrderBehindById(tableName, fieldCode, id);
        }

        this.deleteFromCacheByCode(id);
        return mapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    public Integer deleteByIds(String property, List<Object> ids) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, ids);
        return this.mapper.deleteByExample(example);
    }

    /**
     * 根据条件删除
     *
     * @param record
     * @return
     */
    public Integer delete(T record) {
        return this.mapper.delete(record);
    }

    /**
     * 根据条件分页查询
     *
     * @param record
     * @return
     */
    public PageInfo<T> queryPageListByWhere(T record, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<T> list = this.mapper.select(record);
        return new PageInfo<>(list);
    }

    /**
     * 条件查询，返回resultMap,统计查询
     *
     * @param record
     * @return
     */

    public List<T> selectBySelective(SysParamMap sysParamMap) {
        sysParamMap = this.queryFormat(sysParamMap);
        List<T> ts = this.mapper.selectBySelective(sysParamMap);
        return this.resultFormat(ts);
    }

    //通用查询
    public SysResult selectByQuery(SysParamMap sysParamMap) {
        Example example = new Example(clazz);
        if (sysParamMap.entrySet().size() > 0) {
            Example.Criteria criteria = example.createCriteria();
            Iterator var5 = sysParamMap.entrySet().iterator();

            while (var5.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry) var5.next();
                criteria.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");
            }
        }

        if (sysParamMap.getOrderBy() != null && !sysParamMap.getOrderBy().trim().isEmpty()) {
            example.setOrderByClause(sysParamMap.getOrderBy());
        }

        Page<Object> page = PageHelper.startPage(sysParamMap.getPageNum(), sysParamMap.getPageSize());
        List<T> list = this.mapper.selectByExample(example);
        return new SysResult().success(this.resultFormat(list),page.getTotal());
    }


    /**
     * 条件分页查询
     *
     * @param record
     * @return
     */
    /*public PageInfo<T> selectPageBySelective(T record) {
        record = this.queryFormat(record);
        try {
            mapperClazz = this.mapper.getClass();
            Method selectBySelective = mapperClazz.getDeclaredMethod("selectBySelective", clazz);
            if (record.getPageNum() != null && record.getPageSize() != null)
                PageHelper.startPage(record.getPageNum(), record.getPageSize());
            Object listObj = selectBySelective.invoke(this.mapper, record);
            if (listObj != null) {
                return new PageInfo<>(this.resultFormat((List<T>) listObj));
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }*/

    /**
     * 升序
     *
     * @param id
     * @return
     */

    public Integer increaseOrder(Object id) {
        //获取当前类对应实体类对象
        SysEntity sysEntity = sysEntityService.queryFromRedis(clazz.getSimpleName());
        //获取父字段字段类对象
        SysField sysField = sysFieldService.selectByPrimaryKey(sysEntity.getParentFieldId());

        String tableName = StringUtil.upperCharToUnderLine(clazz.getSimpleName());
        String fieldCode = sysField == null ? "" : StringUtil.upperCharToUnderLine(sysField.getAssignmentCode());
        return commonMapper.increaseOrder(tableName, fieldCode, id);
    }

    /**
     * 批量修改数据
     *
     * @param list
     * @return
     */

    public SysResult batchUpdateBySelective(List<Map<String, Object>> list) {
        return null;
    }

    /**
     * 逻辑删除数据
     *
     * @param id
     * @return
     */
    @SneakyThrows
    @CacheEvict(value = "entityRecord", keyGenerator = "myCacheKeyGenerator")
    public int deleteLogicallyByPrimaryKey(Integer id) {
        this.deleteFromCacheByCode(id);
        return this.mapper.updateByPrimaryKeySelective((T) new BasePojo().setIsDelete(1).setId(id));
    }

    //从redis或数据库获取数据

    @SneakyThrows
    //@Cacheable(value = "entityRecordDetail", keyGenerator = "myCacheKeyGenerator", unless = "#result == null")
    public T queryFromRedis(String key) {
        if(StringUtils.isBlank(key))return null;
        List<T> ts = this.selectBySelective(SysParamMap.MapBuilder.aMap().put("code",key).build());
        if (CollectionUtils.isEmpty(ts)) return null;
        return ts.get(0);
    }

    //手动删除 通过code缓存的记录
    @SneakyThrows
    public void deleteFromCacheByCode(Object id){
        T t = this.selectByPrimaryKey(id);
        if(t!=null){
            if(t!=null){
                Map map = JSONUtil.toMap(t);
                if(map!=null&&map.get("code")!=null){
                    //移除详情缓存中的记录
                    Object key = map.get("code");
                    redisUtil.del("entityRecordDetail::"+this.getClass().getSimpleName()+"-"+key);
                }
            }
        }
    }

    public SysResult refreshRedis(String code) {
        if (redisUtil.getIsConnect()) {
            if(StringUtils.isNotBlank(code)){
                redisUtil.del("entityRecordDetail::"+this.getClass().getSimpleName()+"-"+code);
            }else{
                Set<String> keys = redisUtil.hasKeys("entityRecordDetail::"+this.getClass().getSimpleName()+"-*");
                redisUtil.delKeys(keys);
            }
            return SysResult.success("刷新缓存成功");
        } else {
            return SysResult.fail("redis连接失败");
        }
    }

    /**
     * 校验数据格式
     * @param obj
     * @return
     */
    public SysResult validation(T obj){
        //反射获取需要验证的字段值
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //反射实例化对象
            T sample = clazz.newInstance();
            sample.setIsDelete(0);
            //获取当前类对应实体类对象
            SysEntity sysEntity = sysEntityService.queryFromRedis(clazz.getSimpleName());
            if(sysEntity!=null){
                //如果名称不为空，验证名称唯一性
                if(StringUtils.isNotBlank(obj.getName())){
                    //获取当前类对应实体类对象
                    if(sysEntity.getParentFieldId()!=null){
                        //如果当前类对应实体类有设置父字段，则验证名称局部唯一性
                        //获取父字段字段类对象
                        SysField sysField = sysFieldService.selectByPrimaryKey(sysEntity.getParentFieldId());
                        //获取父字段
                        Field parentField = clazz.getDeclaredField(sysField.getAssignmentCode());
                        parentField.setAccessible(true);
                        //获取父字段值
                        Object parentValueObj = parentField.get(obj);
                        //获取父字段set方法
                        Method setParent = clazz.getMethod("set"+ StringUtil.capitalizeInitial(sysField.getAssignmentCode()), String.class);
                        //实例化的对象赋值父字段
                        setParent.invoke(sample, parentValueObj);
                    }
                    //实例化对象赋值名称，查询，校验
                    sample.setName(obj.getName());
                    List<T> objList = this.mapper.select(sample);
                    if(objList.size()>0&&!objList.get(0).getId().equals(obj.getId())) {
                        //通过新文件的名称查询到数据
                        return SysResult.fail("名称重复，保存失败");
                    }
                }

                Field codeField = clazz.getDeclaredField("code");
                codeField.setAccessible(true);
                map.put("code", codeField.get(obj));// 设置键值

                //验证编码全局唯一性
                if(map.get("code")!=null&&map.get("code")!=""){
                    Method setCode = clazz.getMethod("setCode", String.class);
                    //实例化对象重新初始化
                    sample = clazz.newInstance();
                    sample.setIsDelete(0);
                    setCode.invoke(sample,map.get("code"));

                    List<T> objList = this.mapper.select(sample);
                    if(objList.size()>0&&!objList.get(0).getId().equals(obj.getId())) {
                        //通过新文件的编码查询到数据
                        return SysResult.fail("编码重复，保存失败");
                    }
                }
            }else{
                //return SysResult.fail("未添加实体记录",clazz.getSimpleName());
            }

        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return SysResult.fail();
        } catch (NoSuchMethodException | NoSuchFieldException e) {
            //e.printStackTrace();
        }

        //如果通过全部格式验证，则设置code=200，表示通过验证；
        return SysResult.success();
    }

    /**
     * 保存实例格式化
     * @param obj
     * @return
     */
    public T saveFormat(T obj) {
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        if(obj.getId()==null){
            obj.setCreatorId(loginUser.getId());
            obj.setCreatorName(loginUser.getName());
            obj.setCreatorHost(RequestUtil.getIpAddress(request));
        }else{
            obj.setModifierId(loginUser.getId());
            obj.setModifierName(loginUser.getName());
            obj.setModifierHost(RequestUtil.getIpAddress(request));
        }

        //通过反射赋值"顺序"字段
        try {
            //反射实例化对象
            T sample = clazz.newInstance();
            sample.setIsDelete(0);
            Method setSequence = clazz.getMethod("setSequence", Integer.class);
            Method getSequence = clazz.getMethod("getSequence", null);
            if(setSequence!=null&&getSequence.invoke(obj,null)==null){
                //获取当前类对应实体类对象
                SysEntity sysEntity = sysEntityService.queryFromRedis(clazz.getSimpleName());
                if(sysEntity!=null){
                    //获取父字段字段类对象
                    SysField sysField = sysFieldService.selectByPrimaryKey(sysEntity.getParentFieldId());
                    if(sysField!=null){
                        //获取父字段
                        Field parentField = clazz.getDeclaredField(sysField.getAssignmentCode());
                        parentField.setAccessible(true);
                        //获取父字段值
                        Object parentValueObj = parentField.get(obj);
                        //获取父字段set方法
                        Method setParent = clazz.getMethod("set"+ StringUtil.capitalizeInitial(sysField.getAssignmentCode()), String.class);
                        //实例化的对象赋值父字段
                        setParent.invoke(sample, parentValueObj);
                    }

                    //查询该父字段下的数据数量
                    int num = this.mapper.selectCount(sample);
                    //给保存对象顺序字段赋值
                    setSequence.invoke(obj,num+1);
                }
            }

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException | InstantiationException e) {
            //e.printStackTrace();
        }

        return obj;
    }

    /**
     * 查询实例格式化
     * @param sysParamMap
     * @return
     */
    public SysParamMap queryFormat(SysParamMap sysParamMap) {

        if(sysParamMap==null)return sysParamMap;
        SysEntity sysEntity = null;
        //查询obj对应实体类
        if(redisUtil.getIsConnect()&&redisUtil.hasKey("SysEntity:"+clazz.getSimpleName())){
            sysEntity = (SysEntity) redisUtil.get("SysEntity:"+clazz.getSimpleName());
        }else{
            List<SysEntity> select = sysEntityMapper.select((SysEntity) new SysEntity().setCode(clazz.getSimpleName()).setIsDelete(0));
            if(!CollectionUtils.isEmpty(select))sysEntity = select.get(0);
        }


        //排序处理
        if(StringUtils.isNotBlank(sysParamMap.getSortkey())&&StringUtils.isNotBlank(sysParamMap.getSortord())) {
            //如果采用了单列排序功能
            sysParamMap.put("sortSql", QueryUtil.sortFieldToSortSql(sysParamMap.getSortkey(),sysParamMap.getSortord()));
        }else {
            //如果单列排序功能没有调用，判断排序查询语句是否为空，如果为空，按照默认综合排序条件排序
            if(StringUtils.isBlank(sysParamMap.getSortSql())&&sysEntity!=null) {
                //查询本实体综合排序方法
                List<SysSort> sortList = redisUtil.getIsConnect()&&redisUtil.hasKey("SysEntity:"+sysEntity.getCode())?((SysEntity) redisUtil.get("SysEntity:"+sysEntity.getCode())).getSortList():
                        sysSortMapper.selectBySelective(SysParamMap.MapBuilder.aMap().put("entityId",sysEntity.getId()).put("sortSql","record.sequence").build());
                //格式化排序条件，转为查询语句，并将语句赋值给查询对象
                sysParamMap.setSortSql(QueryUtil.sortListToSortSql(sortList));
            }
        }

        //查询条件处理
        //如果高级查询语句不为空，则判断高级查询条件是否为空
        if(sysParamMap.get("advancedQuery")==null&&ListUtil.isNotNull(sysParamMap.getUtilQueryList())) {
            //格式化高级查询条件，转为查询语句，并将语句赋值给查询对象
            sysParamMap.put("advancedQuery",QueryUtil.queryListToQueryStr(sysParamMap.getUtilQueryList()));
        }

		/*try {
			Subject subject = SecurityUtils.getSubject();
			if(subject!=null){
				Object principal = subject.getPrincipal();
				SysAccount activeUser = (SysAccount)principal;
				List<SysRolePermission> rolePermissionList = new ArrayList<>();
				List<SysRole> roleList = sysRoleService.selectBySelective((SysRole) new SysRole().setIds(activeUser.getRoleIds()));
				//遍历用户的角色，通过角色与实体名称查询所有角色权限信息
				for(SysRole sysRole:roleList) {
					rolePermissionList.addAll(sysRolePermissionService.selectBySelective(new SysRolePermission().setRoleId(sysRole.getId()).setPermissionTypeDcode("function_type_retrieve").setEntityId(sysEntity.getId())));
				}
				//保存最大范围的权限的id
				Integer scope = 0;
				//如果有该权限
				if(ListUtil.isNotNull(rolePermissionList)) {
					//取最大范围权限
					for(SysRolePermission sysRolePermission:rolePermissionList) {
						if(sysRolePermission.getScope()>scope) scope = sysRolePermission.getScope();
					}
				}

				switch (scope) {
					case 4:
						//查询全部
						break;
					case 3:
						//查询公司范围
						obj.setCompanyId(sysCompanyUtil.getSubCompanyIdsByRootCompanyId(activeUser.getCompanyId()));
						break;
					case 2:
						//查询部门范围
						obj.setDepartmentId(activeUser.getDepartmentId());
						break;
					case 1:
						//查询个人范围
						obj.setCreatorId(activeUser.getId());
						break;

					default:
						//无权限
						obj.setCreatorId("0");
						break;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}*/

        return sysParamMap;
    }

    /**
     * 查询结果格式化
     * @param list
     * @return
     */
    @SneakyThrows
    public List<T> resultFormat(List<T> list) {
        if(CollectionUtils.isEmpty(list))return list;
        SysEntity sysEntity = sysEntityService.selectOne(new SysEntity().setCode(clazz.getSimpleName()));
        if(sysEntity!=null){
            List<SysField> fieldList = sysFieldService.select(new SysField().setEntityId(sysEntity.getId()));
            if(!CollectionUtils.isEmpty(fieldList)){
                //遍历该实体类的所有字段
                for (SysField sysField : fieldList) {
                    if(StringUtils.isNotBlank(sysField.getAssignmentCode())
                            &&StringUtils.isNotBlank(sysField.getDisplayCode())
                            &&!sysField.getAssignmentCode().equals(sysField.getDisplayCode())){
                        //如果赋值与取值的字段值不同，则反射获取赋值字段值，查询取值字段值
                        Field assignmentCodeField = clazz.getDeclaredField(sysField.getAssignmentCode());
                        assignmentCodeField.setAccessible(true);
                        for (T entity : list) {
                            //获取记录中赋字段的值
                            Object value = assignmentCodeField.get(entity);
                            String displayValue = "";

                            if(null != value){
                                //实体中该字段值为空的
                                SysResult displayResult = sysFieldService.getDisplayValue(sysField.getAssignmentCode(), value.toString(),this);
                                if(displayResult.getCode()==200)displayValue = displayResult.getData().toString();
                            }
                            Map<String, Object> map = entity.getMap();
                            if(map==null)map = new HashMap<>();
                            map.put(sysField.getDisplayCode(), displayValue);
                            entity.setMap(map);
                        }
                    }

                }
            }
        }

        return list;
    }
}
