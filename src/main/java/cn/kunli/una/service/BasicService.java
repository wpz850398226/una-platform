package cn.kunli.una.service;

import cn.kunli.una.handler.BasicMapper;
import cn.kunli.una.mapper.CommonMapper;
import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.pojo.system.SysField;
import cn.kunli.una.pojo.system.SysSort;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.system.SysDictionaryService;
import cn.kunli.una.service.system.SysEntityService;
import cn.kunli.una.service.system.SysFieldService;
import cn.kunli.una.service.system.SysSortService;
import cn.kunli.una.utils.common.*;
import cn.kunli.una.utils.redis.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@Transactional
public abstract class BasicService<M extends BasicMapper<T>,T extends BasePojo> extends ServiceImpl<M,T> {

    private Logger log = LoggerFactory.getLogger(BasicService.class);

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
    protected WrapperUtil<T> wrapperUtil;       //条件构造器 工具类
    @Autowired
    protected SysEntityService sysEntityService;
    @Autowired
    protected SysFieldService sysFieldService;
    @Autowired
    protected SysSortService sysSortService;
    @Autowired
    protected SysDictionaryService sysDictionaryService;
    @Autowired
    protected CommonMapper commonMapper;


    public QueryWrapper<T> getWrapper(Map<String,Object> map){
        return wrapperUtil.mapToWrapper(map);
    }

    /**
     * 根据主键进行查询
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = "entityRecord", keyGenerator = "myCacheKeyGenerator", unless = "#result == null")
    public T getById(Serializable id) {
        return super.getById(id);
    }

    /**
     * 查询（根据 columnMap 条件）
     *
     * @param record
     * @return
     */
    /*public List<T> selectByMap(Map<String,Object> map) {
        return this.mapper.selectByMap(map);
    }*/

    @Override
    public boolean saveOrUpdate(T entity) {
        return super.saveOrUpdate(this.saveFormat(entity));
    }

    /**
     * 更新数据,只操作record中的非空属性
     *
     * @param record
     * @return
     */
    @SneakyThrows
    @CacheEvict(value = "entityRecord", keyGenerator = "myCacheKeyGenerator")
    @Override
    public boolean updateById(T entity) {
        this.deleteFromCacheByCode(entity.getId());
        return super.updateById(entity);
    }

    /**
     * 根据主键进行删除
     *
     * @param id
     * @return
     */
    @SneakyThrows
    @CacheEvict(value = "entityRecord", keyGenerator = "myCacheKeyGenerator")
    public boolean deleteById(Serializable id) {
        String className = entityClass.getSimpleName();
        SysEntity sysEntity = sysEntityService.queryFromRedis(className);
        //获取当前类对应实体类对象
        if(sysEntity!=null) {
            //获取父字段字段类对象
            SysField sysField = sysFieldService.getById(sysEntity.getParentFieldId());
            String tableName = StringUtil.upperCharToUnderLine(className);
            String fieldCode = sysField == null ? "" : StringUtil.upperCharToUnderLine(sysField.getAssignmentCode());
            commonMapper.increaseOrderBehindById(tableName, fieldCode, id);
        }

        this.deleteFromCacheByCode(id);
        return super.removeById(id);
    }

    /**
     * 条件查询，返回resultMap,统计查询
     *
     * @param record
     * @return
     */
    /*public List<T> selectBySelective(map map) {
        map = this.queryFormat(map);
        List<T> ts = this.mapper.selectBySelective(map);
        return this.resultFormat(ts);
    }*/




    /**
     * 升序
     * @param id
     * @return
     */

    public Integer increaseOrder(Object id) {
        //获取当前类对应实体类对象
        SysEntity sysEntity = sysEntityService.queryFromRedis(entityClass.getSimpleName());
        //获取父字段字段类对象
        SysField sysField = sysFieldService.getById(sysEntity.getParentFieldId());

        String tableName = StringUtil.upperCharToUnderLine(entityClass.getSimpleName());
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
    /*@SneakyThrows
    @CacheEvict(value = "entityRecord", keyGenerator = "myCacheKeyGenerator")
    public int deleteLogicallyByPrimaryKey(Integer id) {
        this.deleteFromCacheByCode(id);
        return this.mapper.updateByPrimaryKeySelective((T) new BasePojo().setIsDelete(1).setId(id));
    }*/

    //从redis或数据库获取数据

    @SneakyThrows
    //@Cacheable(value = "entityRecordDetail", keyGenerator = "myCacheKeyGenerator", unless = "#result == null")
    public T queryFromRedis(String key) {
        if(StringUtils.isBlank(key))return null;
        T record = super.getOne(wrapperUtil.mapToWrapper(MapUtil.getMap("code", key)));
//        List<T> ts = this.selectBySelective(map.MapBuilder.aMap().put("code",key).build());
        return record;
    }

    //手动删除 通过code缓存的记录
    @SneakyThrows
    public void deleteFromCacheByCode(Serializable id){
        T t = this.getById(id);
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
    @SneakyThrows
    public SysResult validation(T obj){
        //反射获取需要验证的字段值
        Map<String, Object> map = new HashMap<String, Object>();
        //获取当前类对应实体类对象
        SysEntity sysEntity = sysEntityService.queryFromRedis(entityClass.getSimpleName());
        if(sysEntity!=null){
            //如果名称不为空，验证名称唯一性
            if(StringUtils.isNotBlank(obj.getName())){
                Map<String, Object> nameParamMap = MapUtil.getMap("name", obj.getName());
                //获取当前类对应实体类对象
                if(sysEntity.getParentFieldId()!=null){
                    //如果当前类对应实体类有设置父字段，则验证名称局部唯一性
                    //获取父字段字段类对象
                    SysField sysField = sysFieldService.getById(sysEntity.getParentFieldId());
                    //获取父字段
                    Field parentField = entityClass.getDeclaredField(sysField.getAssignmentCode());
                    parentField.setAccessible(true);
                    //获取父字段值
                    Object parentValueObj = parentField.get(obj);
                    nameParamMap.put(sysField.getAssignmentCode(),parentValueObj);
                    //获取父字段set方法
                    //Method setParent = entityClass.getMethod("set"+ StringUtil.capitalizeInitial(sysField.getAssignmentCode()), String.class);
                    //实例化的对象赋值父字段
                    //setParent.invoke(sample, parentValueObj);
                }
                List<T> nameResultList = this.list(wrapperUtil.mapToWrapper(nameParamMap));
                if(!CollectionUtils.isEmpty(nameResultList)&&!nameResultList.get(0).getId().equals(obj.getId())) {
                    //通过新文件的名称查询到数据
                    return SysResult.fail("名称重复，保存失败");
                }
            }

            Field codeField = entityClass.getDeclaredField("code");
            if(codeField!=null){
                //如果有code字段
                codeField.setAccessible(true);
                Object codeObject = codeField.get(obj);
                //如果传入了code值，验证code全局唯一性
                if(codeObject!=null&&StringUtils.isNotBlank(codeObject.toString())){
                    List<T> codeResultList = this.list(wrapperUtil.mapToWrapper(MapUtil.getMap("code",codeObject)));
                    if(!CollectionUtils.isEmpty(codeResultList)&&!codeResultList.get(0).getId().equals(obj.getId())) {
                        //通过新文件的编码查询到数据
                        return SysResult.fail("编码重复，保存失败");
                    }
                }
            }
        }
        //如果通过全部格式验证，则设置code=200，表示通过验证；
        return SysResult.success();
    }

    /**
     * 保存实例格式化
     * @param obj
     * @return
     */
    @SneakyThrows
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

        //反射实例化对象
        T sample = entityClass.newInstance();
        sample.setIsDelete(0);
        //通过反射赋值"顺序"字段
        Method setSequence = entityClass.getMethod("setSequence", Integer.class);
        Method getSequence = entityClass.getMethod("getSequence", null);
        if(setSequence!=null&&getSequence.invoke(obj,null)==null){
            //获取当前类对应实体类对象
            SysEntity sysEntity = sysEntityService.queryFromRedis(entityClass.getSimpleName());
            if(sysEntity!=null){
                //获取父字段字段类对象
                SysField sysField = sysFieldService.getById(sysEntity.getParentFieldId());
                int num = 0;
                if(sysField!=null){
                    //获取父字段
                    Field parentField = entityClass.getDeclaredField(sysField.getAssignmentCode());
                    parentField.setAccessible(true);
                    //获取父字段值
                    Object parentValueObj = parentField.get(obj);
                    //查询该父字段下的数据数量
                    num = this.count(wrapperUtil.mapToWrapper(MapUtil.getMap(sysField.getAssignmentCode(),parentValueObj)));
                }else{
                    //没有设置父字段，查询所有数量
                    num = this.count();
                }

                //给保存对象顺序字段赋值
                setSequence.invoke(obj,num+1);
            }
        }
        return obj;
    }

    /**
     * 查询实例格式化
     * @param map
     * @return
     */
    public Map<String,Object> queryFormat(Map<String,Object> map) {

        if(MapUtils.isEmpty(map))return map;
        SysEntity sysEntity = sysEntityService.getOne(sysEntityService.getWrapper(MapUtil.getMap("code", entityClass.getSimpleName())));

        //如果没有指定排序条件，则启用系统设置的排序方式
        if((map.get("orderByAsc")==null||StringUtils.isBlank(map.get("orderByAsc").toString()))
                &&(map.get("orderByDesc")==null||StringUtils.isBlank(map.get("orderByDesc").toString()))) {
            if(sysEntity!=null) {
                //查询本实体综合排序方法
                List<SysSort> sortList = sysSortService.list(sysSortService.getWrapper(MapUtil.getMap("entityId",sysEntity.getId())));
                //格式化排序条件，转为查询语句，并将语句赋值给查询对象
                if(CollectionUtils.isNotEmpty(sortList)){
                    StringBuffer ascFieldBuffer = new StringBuffer();
                    StringBuffer descFieldBuffer = new StringBuffer();
                    for (SysSort sysSort : sortList) {
                        String assignmentCode = sysFieldService.getById(sysSort.getFieldId()).getAssignmentCode();
                        if(sysSort.getSortord()){
                            ascFieldBuffer.append(",").append(assignmentCode);
                        }else{
                            descFieldBuffer.append(",").append(assignmentCode);
                        }
                    }

                    if(ascFieldBuffer.length()>0){
                        map.put("orderByAsc",ascFieldBuffer.delete(0, 1).toString());
                    }

                    if(descFieldBuffer.length()>0){
                        map.put("orderByDesc",descFieldBuffer.delete(0, 1).toString());
                    }
                }
            }
        }

        //查询条件处理
        //如果高级查询语句不为空，则判断高级查询条件是否为空
        /*if(map.get("advancedQuery")==null&&ListUtil.isNotNull(map.getUtilQueryList())) {
            //格式化高级查询条件，转为查询语句，并将语句赋值给查询对象
            map.put("advancedQuery",QueryUtil.queryListToQueryStr(map.getUtilQueryList()));
        }*/

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

        return map;
    }

    /**
     * 查询结果格式化
     * @param list
     * @return
     */
    @SneakyThrows
    public List<T> resultFormat(List<T> list) {
        if(CollectionUtils.isEmpty(list))return list;
        SysEntity sysEntity = sysEntityService.getOne(sysEntityService.getWrapper(MapUtil.getMap("code",entityClass.getSimpleName())));
        if(sysEntity!=null){
            List<SysField> fieldList = sysFieldService.list(sysFieldService.getWrapper(MapUtil.getMap("entityId",sysEntity.getId())));
            if(!CollectionUtils.isEmpty(fieldList)){
                //遍历该实体类的所有字段
                for (SysField sysField : fieldList) {
                    if(StringUtils.isNotBlank(sysField.getAssignmentCode())
                            &&StringUtils.isNotBlank(sysField.getDisplayCode())
                            &&!sysField.getAssignmentCode().equals(sysField.getDisplayCode())){
                        //如果赋值与取值的字段值不同，则反射获取赋值字段值，查询取值字段值
                        Field assignmentCodeField = entityClass.getDeclaredField(sysField.getAssignmentCode());
                        assignmentCodeField.setAccessible(true);
                        for (T entity : list) {
                            //获取记录中赋字段的值
                            Object value = assignmentCodeField.get(entity);
                            String displayValue = "";

                            if(null != value){
                                //实体中该字段值为空的
                                SysResult displayResult = sysFieldService.getDisplayValue(sysField.getAssignmentCode(), value.toString(),this);
                                if(displayResult.getIsSuccess())displayValue = displayResult.getData().toString();
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
