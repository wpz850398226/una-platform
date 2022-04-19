package cn.kunli.una.service;

import cn.hutool.core.util.StrUtil;
import cn.kunli.una.annotation.MyCacheEvict;
import cn.kunli.una.mapper.CommonMapper;
import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.pojo.sys.SysEntity;
import cn.kunli.una.pojo.sys.SysField;
import cn.kunli.una.pojo.sys.SysRelation;
import cn.kunli.una.pojo.sys.SysSort;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.flow.FlowInstanceService;
import cn.kunli.una.service.flow.FlowTaskService;
import cn.kunli.una.service.sys.*;
import cn.kunli.una.utils.common.*;
import cn.kunli.una.utils.redis.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Primary
@Transactional
public abstract class BasicService<M extends BaseMapper<T>,T extends BasePojo> extends ServiceImpl<M,T> {

    //全局参数
    protected M mapper;
    //本类的代理
    public abstract BasicService<M,T> getThisProxy();

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
    protected SysRelationService sysRelationService;
    @Autowired
    protected SysButtonService sysButtonService;
    @Autowired
    protected SysQueryService sysQueryService;
    @Autowired
    protected SysFilterService sysFilterService;
    @Autowired
    protected SysAccountService sysAccountService;
    @Autowired
    protected SysDictionaryService sysDictionaryService;
    @Autowired
    protected SysRolePermissionService sysRolePermissionService;
    @Autowired
    protected SysPermissionService sysPermissionService;
    @Autowired
    protected SysCompanyService sysCompanyService;
    @Autowired
    protected FlowInstanceService flowInstanceService;
    @Autowired
    protected FlowTaskService flowTaskService;
    @Autowired
    protected CommonMapper commonMapper;


    private QueryWrapper<T> getWrapper(Map<String,Object> map){
        return wrapperUtil.mapToQueryWrapper(map);
    }
//    @Autowired
    public SysEntity getEntity(){
        SysEntity sysEntity = sysEntityService.selectOne(UnaMapUtil.getMap("code",entityClass.getSimpleName()));
        if(sysEntity==null)return null;
        return sysEntity;
    }

    /**
     * 新增记录
     * @param entity
     * @return
     */
    @MyCacheEvict(value = "list")
    public SysResult saveRecord(T entity) {
        //数据校验
        SysResult validationResult = validate(entity);
        if(!validationResult.getIsSuccess())return validationResult;
        //保存数据，保存前进行初始化
        boolean saveResult = super.save(initialize(entity));
        if(saveResult){
            //保存成功的结果
            SysResult success = new SysResult().success(entity.getId());
            //保存成功后的操作
            SysResult sysResult = afterSaveSuccess(entity);
            if(!sysResult.getIsSuccess())success.setMessage(success.getMessage()+","+sysResult.getMessage());
            return success;
        }
        return SysResult.fail();
    }

    /**
     * 根据主键进行删除
     *
     * @param id
     * @return
     */
    @SneakyThrows
    @MyCacheEvict(value = {"list","record:one"})
    @CacheEvict(value = "record:id", keyGenerator = "myCacheKeyGenerator")
    public boolean deleteById(Serializable id) {
        boolean removeResult = super.removeById(id);
        if(removeResult){
            //获取当前类对应实体类对象
            SysEntity sysEntity = getEntity();
            //后续记录升序
            if(sysEntity!=null) {
                //获取父字段字段类对象
                SysField sysField = sysFieldService.getById(sysEntity.getParentFieldId());
                String tableName = StrUtil.toUnderlineCase(entityClass.getSimpleName());
                String fieldCode = sysField == null ? "" : StrUtil.toUnderlineCase(sysField.getAssignmentCode());
                commonMapper.increaseOrderBehindById(tableName, fieldCode, id);
            }

            //级联删除
            List<SysRelation> sysRelationList = sysRelationService.selectList(UnaMapUtil.getMap("parentEntityId", sysEntity.getId()));
            if(CollectionUtils.isNotEmpty(sysRelationList)){
                for (SysRelation sysRelation : sysRelationList) {
                    //需要级联删除的记录所属实体类
                    Integer relatedEntityId = sysRelation.getEntityId();
                    Integer relatedFieldId = sysRelation.getRelatedFieldId();

                    SysEntity relatedEntity = sysEntityService.getById(relatedEntityId);
                    SysField relatedField = sysFieldService.getById(relatedFieldId);

                    //获取service名称，并动态获取service
                    String serviceName = relatedEntity.getCode()+"Service";

                    BasicService<M, T> thisProxy = null;
                    //关联自身实体类
                    if(entityClass.getSimpleName().equals(relatedEntity.getCode())){
                        thisProxy = getThisProxy();
                    }else{
                        switch(relatedEntity.getCode()){
                            case "SysField":
                                thisProxy = (BasicService<M, T>)sysFieldService;
                                break;
                            case "SysQuery":
                                thisProxy = (BasicService<M, T>)sysQueryService;
                                break;
                            case "SysFilter":
                                thisProxy = (BasicService<M, T>)sysFilterService;
                                break;
                            case "SysRelation":
                                thisProxy = (BasicService<M, T>)sysRelationService;
                                break;
                            case "SysButton":
                                thisProxy = (BasicService<M, T>)sysButtonService;
                                break;
                            case "SysSort":
                                thisProxy = (BasicService<M, T>)sysSortService;
                                break;
                        }
                    }

                    if(thisProxy!=null){
                        List<T> ts = thisProxy.selectList(UnaMapUtil.getMap(relatedField.getAssignmentCode(), id));
                        if(CollectionUtils.isNotEmpty(ts)){
                            for (T t : ts) {
                                thisProxy.deleteById(t.getId());
                            }
                        }
                    }


                    /*Object bean1 = SpringUtil.getBean(serviceName);
                    BasicService bean = SpringUtil.getBean(serviceName, BasicService.class);
                    List<BasePojo> list = bean.selectList(MapUtil.getMap(relatedField.getAssignmentCode(), id));
                    if(CollectionUtils.isNotEmpty(list)){
                        list.forEach(e -> bean.deleteById(e.getId()));
                    }*/

                }
            }

        }

        return removeResult;
    }

    /**
     * 条件删除
     *
     * @param id
     * @return
     */
    @MyCacheEvict(value = {"list","record:one"})
    @CacheEvict(value = "record:id", keyGenerator = "myCacheKeyGenerator")
    public boolean deleteBySelective(Map<String,Object> map) {
        /*String className = entityClass.getSimpleName();
        SysEntity sysEntity = sysEntityService.selectOne(MapUtil.getMap("code",className));
        //获取当前类对应实体类对象
        if(sysEntity!=null) {
            //获取父字段字段类对象
            SysField sysField = sysFieldService.getById(sysEntity.getParentFieldId());
            String tableName = StrUtil.toUnderlineCase(className);
            String fieldCode = sysField == null ? "" : StrUtil.toUnderlineCase(sysField.getAssignmentCode());
            commonMapper.increaseOrderBehindById(tableName, fieldCode, id);
        }*/

        return super.remove(wrapperUtil.mapToQueryWrapper(map));
    }



    /**
     * 更新数据,只操作record中的非空属性
     *
     * @param record
     * @return
     */
    @SneakyThrows
    @MyCacheEvict(value = {"list","record:one"})
    @CacheEvict(value = "record:id", keyGenerator = "myCacheKeyGenerator")
    public SysResult updateRecordById(T entity) {
        //数据校验
        SysResult validationResult = validate(entity);
        if(!validationResult.getIsSuccess())return validationResult;
        //保存数据，保存前进行初始化
        boolean result = super.updateById(initialize(entity));
        if(result){
            //保存成功的结果
            SysResult success = SysResult.success();
            //保存成功后的操作
            SysResult sysResult = afterSaveSuccess(entity);
            if(!sysResult.getIsSuccess())success.setMessage(success.getMessage()+","+sysResult.getMessage());
            return success;
        }
        return SysResult.fail();
    }

    @Override
    @MyCacheEvict(value = {"list","record:one"})
    @CacheEvict(value = "record:id", keyGenerator = "myCacheKeyGenerator")
    public boolean update(Wrapper<T> updateWrapper) {
        return super.update(updateWrapper);
    }

    //自定义更新规则
    /*@Override
    public boolean update(Wrapper<T> updateWrapper) {
        return super.update(updateWrapper);
    }*/

    /**
     * 根据主键进行查询
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = "record:id", keyGenerator = "myCacheKeyGenerator", unless = "#result == null")
    public T getById(Serializable id) {
        return super.getById(id);
    }

    public Page<T> page(Object current, Object size, Map<String,Object> map) {
        if(current==null)current = 1;
        if(size==null)size = 10;
        map.remove("pageNum");
        map.remove("pageSize");
        Page<T> pageObj = new Page<T>().setCurrent(Long.parseLong(current.toString())).setSize(Long.parseLong(size.toString()));
        return super.page(pageObj,wrapperUtil.mapToQueryWrapper(format(map)));
    }

    /**
     * 根据条件构造查询单条
     * @param queryWrapper
     * @return
     */
    @Cacheable(value = "record:one", keyGenerator = "myCacheKeyGenerator", unless = "#result == null")
    public T selectOne(Map<String,Object> map) {
        return super.getOne(wrapperUtil.mapToQueryWrapper(map));
    }

    /**
     * 根据条件构造查询多条，带处理查询实例
     * @param map
     * @return
     */
    @Cacheable(value = "list", keyGenerator = "myCacheKeyGenerator", unless = "#result == null")
    public List<T> selectList(Map<String,Object> map) {
        return super.list(wrapperUtil.mapToQueryWrapper(format(map)));
    }

    /**
     * 根据条件构造查询多条
     * @param queryWrapper
     * @return
     */
    @Cacheable(value = "list", keyGenerator = "myCacheKeyGenerator", unless = "#result == null")
    public List<T> getList(Map<String,Object> map) {
        return super.list(wrapperUtil.mapToQueryWrapper(map));
    }

    /**
     * 校验数据格式
     * @param obj
     * @return
     */
    @SneakyThrows
    public SysResult validate(T obj){
        //反射获取需要验证的字段值
        Map<String, Object> map = new HashMap<String, Object>();
        //获取当前类对应实体类对象
        SysEntity sysEntity = getEntity();
        if(sysEntity!=null){
            //如果名称不为空，验证名称唯一性
            if(StrUtil.isNotBlank(obj.getName())){
                Map<String, Object> nameParamMap = UnaMapUtil.getMap("name", obj.getName());
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
                }
                List<T> nameResultList = getThisProxy().list(getWrapper(nameParamMap));
                if(CollectionUtils.isNotEmpty(nameResultList)&&!nameResultList.get(0).getId().equals(obj.getId())) {
                    //通过新文件的名称查询到数据
                    return SysResult.fail("名称重复，保存失败");
                }
            }

            Field[] declaredFields = entityClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if(declaredField.getName().equals("code")){
                    //如果有code字段
                    declaredField.setAccessible(true);
                    Object codeObject = declaredField.get(obj);
                    //如果传入了code值，验证code全局唯一性
                    if(codeObject!=null&&StrUtil.isNotBlank(codeObject.toString())){
                        List<T> codeResultList = getThisProxy().getList(UnaMapUtil.getMap("code",codeObject));
                        if(CollectionUtils.isNotEmpty(codeResultList)&&!codeResultList.get(0).getId().equals(obj.getId())) {
                            //通过新文件的编码查询到数据
                            return SysResult.fail("编码重复，保存失败");
                        }
                    }
                    break;
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
    public T initialize(T obj) {
        SysLoginAccountDetails loginUser = UserUtil.getLoginAccount();
        if(obj.getId()==null){
            if(loginUser!=null){
                obj.setCreatorId(loginUser.getId());
                obj.setCompanyId(loginUser.getCompanyId());
                obj.setDepartmentId(loginUser.getDepartmentId());
                obj.setCreatorName(loginUser.getName());
                obj.setCreateTime(new Date());
            }

            //通过反射赋值"顺序"字段
            if(obj.getSortOrder()==null){
                //获取当前类对应实体类对象
                SysEntity sysEntity = getEntity();

                if(sysEntity==null)return obj;
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
                    num = this.count(getWrapper(UnaMapUtil.getMap(sysField.getAssignmentCode(),parentValueObj)));
                }else{
                    //没有设置父字段，查询所有数量
                    num = this.count();
                }

                //给保存对象顺序字段赋值
                obj.setSortOrder(num);
            }
        }else{
            obj.setModifyTime(new Date());
            if(loginUser!=null){
                obj.setModifierId(loginUser.getId());
                obj.setModifierName(loginUser.getName());
            }

        }

        return obj;
    }

    /**
     * 保存成功后的逻辑
     * @param obj
     * @return
     */
    @SneakyThrows
    public SysResult afterSaveSuccess(T obj) {
        return SysResult.success();
    }

    /**
     * 查询结果格式化
     * @param list
     * @return
     */
    @SneakyThrows
    public List<T> parse(List<T> list) {
        if(CollectionUtils.isEmpty(list))return list;
        SysEntity sysEntity = getEntity();
        if(sysEntity!=null){
            List<SysField> fieldList = sysFieldService.selectList(UnaMapUtil.getMap("entityId",sysEntity.getId()));
            if(CollectionUtils.isNotEmpty(fieldList)){
                //遍历该实体类的所有字段
                for (SysField sysField : fieldList) {
                    if(StrUtil.isNotBlank(sysField.getAssignmentCode())
                            &&StrUtil.isNotBlank(sysField.getDisplayCode())
                            &&!sysField.getAssignmentCode().equals(sysField.getDisplayCode())){
                        //如果赋值与取值的字段值不同，则反射获取赋值字段值，查询取值字段值

                        Field assignmentCodeField = null;
                        try {
                            assignmentCodeField = entityClass.getDeclaredField(sysField.getAssignmentCode());
                        } catch (NoSuchFieldException e) {
                            assignmentCodeField = entityClass.getSuperclass().getDeclaredField(sysField.getAssignmentCode());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if(assignmentCodeField!=null){
                            assignmentCodeField.setAccessible(true);
                            for (T entity : list) {
                                //获取记录中赋字段的值
                                Object value = assignmentCodeField.get(entity);
                                String displayValue = "";

                                if(null != value){
                                    //实体中该字段值为空的
                                    SysResult displayResult = sysFieldService.getDisplayValue(sysField.getAssignmentCode(), value.toString(),getThisProxy(),sysField.getTransformDisplayCode());
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
        }

        return list;
    }

    /**
     * 查询实例格式化
     * @param map
     * @return
     */
    public Map<String,Object> format(Map<String,Object> map) {

//        if(MapUtils.isEmpty(map))return map;
        if(map==null)map=new HashMap<>();
        SysEntity sysEntity = getEntity();

        //如果没有指定排序条件，则启用自定义设置的排序方式
        if(map.get("orderByAsc")==null&&map.get("orderByDesc")==null) {
            if(sysEntity!=null) {
                //查询本实体综合排序方法
                List<SysSort> sortList = sysSortService.selectList(UnaMapUtil.buildHashMap().put("entityId",sysEntity.getId()).put("orderByAsc","sortOrder").build());
                //格式化排序条件，转为查询语句，并将语句赋值给查询对象
                if(CollectionUtils.isNotEmpty(sortList)){
                    int size = sortList.size();
                    String [][] orderArray = new String[size+1][2];
                    orderArray[0][0] = "orderByDesc";
                    orderArray[0][1] = "weight";
                    for (int i = 0; i < size; i++) {
                        SysSort sysSort = sortList.get(i);
                        String assignmentCode = sysFieldService.getById(sysSort.getFieldId()).getAssignmentCode();
                        orderArray[i+1][1] = assignmentCode;
                        if(sysSort.getSortord()){
                            orderArray[i+1][0] = "orderByAsc";
                        }else{
                            orderArray[i+1][0] = "orderByDesc";
                        }
                    }
                    map.put("#orderArray",orderArray);
                }
            }

            if(map.get("#orderArray")==null) {
                //默认排序 1、权重倒叙 2、顺序正序
                String [][] defaultOrderArray = {{"orderByDesc","weight"},{"orderByAsc","sortOrder"}};
                map.put("#orderArray",defaultOrderArray);
//                map.put("orderByAsc","sortOrder");
            }
        }

        if(map.containsKey("rootTreeIds")){
            map.put("in:id",map.get("rootTreeIds"));
            map.remove("rootTreeIds");
        }

        return map;
    }

    public int selectCount(Map<String,Object> map) {
        return super.count(wrapperUtil.mapToQueryWrapper(map));
    }
}
