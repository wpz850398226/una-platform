package cn.kunli.una.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.kunli.una.annotation.LogAnnotation;
import cn.kunli.una.annotation.MyCacheEvict;
import cn.kunli.una.handler.BasicMapper;
import cn.kunli.una.handler.UnaResponseException;
import cn.kunli.una.mapper.CommonMapper;
import cn.kunli.una.pojo.BasePojo;
import cn.kunli.una.pojo.sys.*;
import cn.kunli.una.pojo.vo.SysLoginAccountDetails;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.flow.FlowInstanceService;
import cn.kunli.una.service.flow.FlowTaskService;
import cn.kunli.una.service.sys.*;
import cn.kunli.una.utils.common.UnaMapUtil;
import cn.kunli.una.utils.common.UnaMathUtil;
import cn.kunli.una.utils.common.UserUtil;
import cn.kunli.una.utils.common.WrapperUtil;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Primary
@Transactional
public abstract class BasicService<M extends BaseMapper<T>,T extends BasePojo> extends ServiceImpl<M,T> {

    //????????????
    protected M mapper;

    //???????????????
    public abstract BasicService<M,T> getThisProxy();

    @Autowired
    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    public String entityName = entityClass.getSimpleName();

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected RedisUtil<T> redisUtil;
    @Autowired
    protected WrapperUtil<T> wrapperUtil;       //??????????????? ?????????
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
    @Resource
    protected SysDataPermissionService sysDataPermissionService;
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
     * ????????????
     * @param entity
     * @return
     */
    @SneakyThrows
    @LogAnnotation
    @MyCacheEvict(value = "list")
    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasAnyAuthorityByEntity(#entity,'create')")
    public SysResult saveRecord(T entity) {
        //????????????
        saveValidate(entity);
        //???????????????????????????????????????
        boolean saveResult = super.save(initialize(entity));
        if(saveResult){
            //????????????????????????
            afterSaveSuccess(entity);
            return new SysResult().success(entity.getId());
        }
        return SysResult.fail();
    }

    /**
     * ????????????????????????
     *
     * @param id
     * @return
     */
    @SneakyThrows
    @LogAnnotation
    @Transactional(rollbackFor = Exception.class)
    @MyCacheEvict(value = {"list","record:one"})
    @PreAuthorize("hasAnyAuthorityByEntity(#entity,'delete')")
    @CacheEvict(value = "record:id", keyGenerator = "myCacheKeyGenerator")
    public boolean deleteById(T entity) {
        beforeDelete(entity.getId());
        boolean removeResult = super.removeById(entity.getId());
        if(removeResult){
            afterDeleteSuccess(entity.getId());
        }

        return removeResult;
    }

    /**
     * ????????????
     *
     * @param id
     * @return
     */
    @MyCacheEvict(value = {"list","record:one"})
    @CacheEvict(value = "record:id", keyGenerator = "myCacheKeyGenerator")
    public boolean deleteBySelective(Map<String,Object> map) {
        /*String className = entityClass.getSimpleName();
        SysEntity sysEntity = sysEntityService.selectOne(MapUtil.getMap("code",className));
        //????????????????????????????????????
        if(sysEntity!=null) {
            //??????????????????????????????
            SysField sysField = sysFieldService.getById(sysEntity.getParentFieldId());
            String tableName = StrUtil.toUnderlineCase(className);
            String fieldCode = sysField == null ? "" : StrUtil.toUnderlineCase(sysField.getAssignmentCode());
            commonMapper.increaseOrderBehindById(tableName, fieldCode, id);
        }*/

        return super.remove(wrapperUtil.mapToQueryWrapper(map));
    }



    /**
     * ????????????,?????????record??????????????????
     *
     * @param record
     * @return
     */
    @SneakyThrows
    @LogAnnotation
    @MyCacheEvict(value = {"list","record:one"})
    @PreAuthorize("hasAnyAuthorityByEntity(#entity,'update')")
    @CacheEvict(value = "record:id", keyGenerator = "myCacheKeyGenerator")
    public SysResult updateRecordById(T entity) {
        //????????????
        saveValidate(entity);
        //???????????????????????????????????????
        boolean result = super.updateById(initialize(entity));
        if(result){
            //????????????????????????
            afterSaveSuccess(entity);
            return SysResult.success();
        }
        return SysResult.fail();
    }

    @Override
    @LogAnnotation
    @MyCacheEvict(value = {"list","record:one"})
    @CacheEvict(value = "record:id", keyGenerator = "myCacheKeyGenerator")
    public boolean update(Wrapper<T> updateWrapper) {
        return super.update(updateWrapper);
    }

    //?????????????????????
    /*@Override
    public boolean update(Wrapper<T> updateWrapper) {
        return super.update(updateWrapper);
    }*/

    /**
     * ????????????????????????
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = "record:id", keyGenerator = "myCacheKeyGenerator", unless = "#result == null")
    public T getById(Serializable id) {
        return super.getById(id);
    }

    @PreAuthorize("hasAnyAuthorityByEntity(#entity,'retrieve')")
//    @PreAuthorize("@customPreAuthorizer.hasAuthorityByEntity(#map,'SysField:retrieve')")
//    public Page<T> page(Object current, Object size, Map<String,Object> map) {
    public Page<T> page(T entity, Map<String,Object> map) {
//        if(t.getPageNum()==null)t.setPageNum(1);
//        if(t.getPageSize()==null)t.setPageSize(10);
        map.remove("pageNum");
        map.remove("pageSize");
//        Page<T> pageObj = new Page<T>().setCurrent(Long.parseLong(current.toString())).setSize(Long.parseLong(size.toString()));
        Page<T> pageObj = new Page<T>().setCurrent(entity.getPageNum()).setSize(entity.getPageSize());
        return super.page(pageObj,wrapperUtil.mapToQueryWrapper(format(map)));
    }

    /**
     * ??????????????????????????????
     * @param queryWrapper
     * @return
     */
    @Cacheable(value = "record:one", keyGenerator = "myCacheKeyGenerator", unless = "#result == null")
    public T selectOne(Map<String,Object> map) {
        return super.getOne(wrapperUtil.mapToQueryWrapper(map));
    }

    /**
     * ??????????????????????????????????????????????????????
     * @param map
     * @return
     */
    @Cacheable(value = "list", keyGenerator = "myCacheKeyGenerator", unless = "#result == null")
    public List<T> selectList(Map<String,Object> map) {
        return super.list(wrapperUtil.mapToQueryWrapper(format(map)));
    }

    /**
     * ??????????????????????????????
     * @param queryWrapper
     * @return
     */
    @Cacheable(value = "list", keyGenerator = "myCacheKeyGenerator", unless = "#result == null")
    public List<T> getList(Map<String,Object> map) {
        return super.list(wrapperUtil.mapToQueryWrapper(map));
    }

    /**
     * ??????????????????
     * @param obj
     * @return
     */
    @SneakyThrows
    public void saveValidate(T obj){
        //????????????????????????????????????
        SysEntity sysEntity = getEntity();
        if(sysEntity!=null){
            //?????????????????????????????????????????????
            if(StrUtil.isNotBlank(obj.getName())){
                Map<String, Object> nameParamMap = UnaMapUtil.getMap("name", obj.getName());
                //????????????????????????????????????
                if(sysEntity.getParentFieldId()!=null){
                    //?????????????????????????????????????????????????????????????????????????????????
                    //??????????????????????????????
                    SysField sysField = sysFieldService.getById(sysEntity.getParentFieldId());
                    //???????????????
                    Field parentField = entityClass.getDeclaredField(sysField.getAssignmentCode());
                    parentField.setAccessible(true);
                    //??????????????????
                    Object parentValueObj = parentField.get(obj);
                    nameParamMap.put(sysField.getAssignmentCode(),parentValueObj);
                }
                List<T> nameResultList = getThisProxy().list(getWrapper(nameParamMap));
                if(CollectionUtils.isNotEmpty(nameResultList)&&!nameResultList.get(0).getId().equals(obj.getId())) {
                    //???????????????????????????????????????
                    throw new UnaResponseException("???????????????????????????");
                }
            }

            //????????????????????????????????????
            List<SysField> sysFieldList = sysFieldService.selectList(UnaMapUtil.buildHashMap()
                    .put("entityId", sysEntity.getId()).put("isNotNull","dataCheckTypeDcode").put("isEffect",true).build());

            if(CollUtil.isNotEmpty(sysFieldList)){
                for (SysField sysField : sysFieldList) {
                    String dataCheckTypeDcode = sysField.getDataCheckTypeDcode();
                    if(StrUtil.isNotBlank(dataCheckTypeDcode)){
                        String dataCheckValue = sysField.getThreshold();
                        //????????????????????????????????????????????????????????????????????????
                        if(StrUtil.isNotBlank(dataCheckTypeDcode) && !dataCheckTypeDcode.equals("field_dataDetection_unique") && StrUtil.isBlank(dataCheckValue)){
                            throw new UnaResponseException("???????????????["+sysField.getName()+"]?????????????????????");
                        }
                        String fieldCode = sysField.getAssignmentCode();
                        log.info("??????????????????????????????[{}]",fieldCode);
                        Field declaredField = null;
                        try {
                            declaredField = entityClass.getDeclaredField(fieldCode);
                        } catch (NoSuchFieldException e) {
                            declaredField = entityClass.getSuperclass().getDeclaredField(fieldCode);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        declaredField.setAccessible(true);
                        //??????????????????
                        Object fieldValueObject = declaredField.get(obj);
                        if(fieldValueObject!=null&&StrUtil.isNotBlank(fieldValueObject.toString())){
                            switch(dataCheckTypeDcode){
                                case "field_dataDetection_unique":{
                                    //????????????
                                    List<T> resultList = getThisProxy().getList(MapUtil.of(fieldCode,fieldValueObject));
                                    if(CollectionUtils.isNotEmpty(resultList)&&!resultList.get(0).getId().equals(obj.getId())) {
                                        //???????????????????????????????????????
                                        throw new UnaResponseException("???????????????"+sysField.getName()+"??????");
                                    }
                                }
                                break;
                                case "field_dataDetection_length":{
                                    //????????????
                                    if(String.valueOf(fieldValueObject).length()>Integer.valueOf(dataCheckValue)){
                                        throw new UnaResponseException("???????????????"+sysField.getName()+"?????????????????????");
                                    }
                                }
                                break;
                                case "field_dataDetection_threshold":{
                                    //????????????
                                    String[] thresholdValueArray = dataCheckValue.split(",");
                                    if(thresholdValueArray.length!=2){
                                        throw new UnaResponseException("???????????????"+sysField.getName()+"????????????????????????");
                                    }

                                    Double minThreshold = Double.valueOf(thresholdValueArray[0]);
                                    Double maxThreshold = Double.valueOf(thresholdValueArray[1]);
                                    Double fieldValueDouble = Double.valueOf(String.valueOf(fieldValueObject));
                                    if(fieldValueDouble>maxThreshold || fieldValueDouble<minThreshold){
                                        throw new UnaResponseException("???????????????"+sysField.getName()+"?????????????????????");
                                    }
                                }
                                break;
                                case "field_dataDetection_effective":{
                                    //????????????
                                    String[] effectiveValueArray = dataCheckValue.split(",");
                                    if(!Arrays.asList(effectiveValueArray).contains(fieldValueObject)){
                                        //?????????????????????????????????????????????????????????????????????
                                        throw new UnaResponseException("???????????????"+sysField.getName()+"?????????");
                                    }
                                }
                                break;
                                case "field_dataDetection_invalid":{
                                    //????????????
                                    String[] invalidValueArray = dataCheckValue.split(",");
                                    for (String s : invalidValueArray) {
                                        if(String.valueOf(fieldValueObject).contains(s)){
                                            //???????????????????????????????????????????????????????????????
                                            throw new UnaResponseException("???????????????"+sysField.getName()+"?????????????????????");
                                        }
                                    }
                                }
                                break;
                                case "field_dataDetection_averageAmplitude":{
                                    //??????????????????
                                    QueryWrapper<T> queryWrapper = new QueryWrapper();
                                    queryWrapper.select("AVG(column_name) as "+StrUtil.toUnderlineCase(fieldCode));
//                                    queryWrapper.apply("SUBSTRING(CODE,1," + prefixLength + ") = '" + codePrefix + "'");
                                    T one = super.getOne(queryWrapper);
                                    if(one!=null){
                                        //???????????????
                                        Double thresholdPercent = Double.valueOf(dataCheckValue);
                                        //???????????????
                                        Object fieldAverageValueObject = declaredField.get(one);
                                        Double fieldValueDouble = Double.valueOf(String.valueOf(fieldValueObject));
                                        Double fieldAverageValueDouble = Double.valueOf(String.valueOf(fieldAverageValueObject));
                                        double factualPercent = Math.abs(fieldValueDouble - fieldAverageValueDouble) / fieldAverageValueDouble;
                                        if(factualPercent>thresholdPercent){
                                            throw new UnaResponseException("???????????????"+sysField.getName()+"????????????????????????????????????");
                                        }
                                    }
                                }
                                break;
                                case "field_dataDetection_medianAmplitude":{
                                    //??????????????????
                                    List<T> orderByAsc = getThisProxy().selectList(MapUtil.of("orderByAsc", fieldCode));
                                    if(CollUtil.isNotEmpty(orderByAsc)){
                                        Double thresholdPercent = Double.valueOf(dataCheckValue);
                                        Double fieldValueDouble = Double.valueOf(String.valueOf(fieldValueObject));

                                        int size = orderByAsc.size();
                                        int midSize = 0;

                                        if(size % 2 == 0){
                                            //??????
                                            midSize = size / 2 + 1;
                                        } else {
                                            midSize = ++size/2;
                                        }

                                        //????????????
                                        T one = orderByAsc.get(midSize);
                                        Object fieldMidValueObject = declaredField.get(one);

                                        Double fieldMidValueDouble = Double.valueOf(String.valueOf(fieldMidValueObject));
                                        double factualMidPercent = Math.abs(fieldValueDouble - fieldMidValueDouble) / fieldMidValueDouble;
                                        if(factualMidPercent>thresholdPercent){
                                            throw new UnaResponseException("???????????????"+sysField.getName()+"????????????????????????????????????");
                                        }
                                    }
                                }
                                break;
                                case "field_dataDetection_varianceThreshold":{
                                    //??????????????????
                                    QueryWrapper<T> queryWrapper = new QueryWrapper();
                                    queryWrapper.select(StrUtil.toUnderlineCase(fieldCode)+" as description");
                                    List<T> list = getThisProxy().list(queryWrapper);

                                    if(CollUtil.isNotEmpty(list)){
                                        //????????????
                                        Double threshold = Double.valueOf(dataCheckValue);
                                        List<String> strValueList = list.stream().map(T::getDescription).collect(Collectors.toList());
                                        Double[] doubles = strValueList.stream().toArray(Double[]::new);
                                        //?????????
                                        double variance = UnaMathUtil.Variance(doubles);
                                        if(variance>threshold){
                                            throw new UnaResponseException("???????????????"+sysField.getName()+"?????????????????????????????????"+dataCheckValue);
                                        }
                                    }
                                }
                                break;
                                case "field_dataDetection_stdThreshold":{
                                    //?????????????????????
                                    QueryWrapper<T> queryWrapper = new QueryWrapper();
                                    queryWrapper.select(StrUtil.toUnderlineCase(fieldCode)+" as description");
                                    List<T> list = getThisProxy().list(queryWrapper);

                                    if(CollUtil.isNotEmpty(list)){
                                        //???????????????
                                        Double threshold = Double.valueOf(dataCheckValue);
                                        List<String> strValueList = list.stream().map(T::getDescription).collect(Collectors.toList());
                                        Double[] doubles = strValueList.stream().toArray(Double[]::new);
                                        //????????????
                                        double standardDiviation = UnaMathUtil.StandardDiviation(doubles);
                                        if(standardDiviation>threshold){
                                            throw new UnaResponseException("???????????????"+sysField.getName()+"???????????????????????????????????????"+dataCheckValue);
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * ??????????????????
     * @param obj
     * @return
     */
    @SneakyThrows
    public void deleteValidate(T obj){

    }

    /**
     * ?????????????????????
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

            //??????????????????"??????"??????
            if(obj.getSortOrder()==null){
                //????????????????????????????????????
                SysEntity sysEntity = getEntity();

                if(sysEntity==null)return obj;
                //??????????????????????????????
                SysField sysField = sysFieldService.getById(sysEntity.getParentFieldId());
                int num = 0;
                if(sysField!=null){
                    //???????????????
                    Field parentField = entityClass.getDeclaredField(sysField.getAssignmentCode());
                    parentField.setAccessible(true);
                    //??????????????????
                    Object parentValueObj = parentField.get(obj);
                    //????????????????????????????????????
                    num = this.count(getWrapper(UnaMapUtil.getMap(sysField.getAssignmentCode(),parentValueObj)));
                }else{
                    //??????????????????????????????????????????
                    num = this.count();
                }

                //?????????????????????????????????
                obj.setSortOrder(num);
            }

            /*if(StrUtil.isBlank(obj.getCode())){
                //?????????????????????????????????????????????????????????
                QueryWrapper<T> queryWrapper = new QueryWrapper();
                int prefixLength = codePrefix.length();
                int prefixNextLocation = prefixLength + 1;
                queryWrapper.select("CONCAT('" + codePrefix + "',IF(MAX(SUBSTRING(CODE," + prefixNextLocation + ")) IS NULL,10000, MAX(SUBSTRING(CODE," + prefixNextLocation + ")))+1) AS CODE");
                queryWrapper.apply("SUBSTRING(CODE,1," + prefixLength + ") = '" + codePrefix + "'");
                T one = super.getOne(queryWrapper);
                if (one != null) {
                    obj.setCode(one.getCode());
                }
            }*/
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
     * ????????????????????????
     * @param obj
     * @return
     */
    @SneakyThrows
    public void afterSaveSuccess(T obj) {
    }

    /**
     * ??????????????????
     * @param obj
     * @return
     */
    @SneakyThrows
    public void beforeDelete(Serializable id) {
    }

    /**
     * ????????????????????????
     * @param obj
     * @return
     */
    @SneakyThrows
    public void afterDeleteSuccess(Serializable id) {
        //????????????????????????????????????
        SysEntity sysEntity = getEntity();
        //??????????????????
        if(sysEntity!=null) {
            //??????????????????????????????
            SysField sysField = sysFieldService.getById(sysEntity.getParentFieldId());
            String tableName = StrUtil.toUnderlineCase(entityClass.getSimpleName());
            String fieldCode = sysField == null ? "" : StrUtil.toUnderlineCase(sysField.getAssignmentCode());
            commonMapper.increaseOrderBehindById(tableName, fieldCode, id);
        }

        //????????????
        List<SysRelation> sysRelationList = sysRelationService.selectList(MapUtil.of("parentEntityId", sysEntity.getId()));
        if(CollectionUtils.isNotEmpty(sysRelationList)){
            for (SysRelation sysRelation : sysRelationList) {
                //??????????????????????????????????????????
                Integer relatedEntityId = sysRelation.getEntityId();
                Integer relatedFieldId = sysRelation.getRelatedFieldId();

                SysEntity relatedEntity = sysEntityService.getById(relatedEntityId);
                SysField relatedField = sysFieldService.getById(relatedFieldId);

                //??????service????????????????????????service
                String serviceName = StrUtil.lowerFirst(relatedEntity.getCode())+"Service";
                Object bean = SpringUtil.getBean(serviceName);
//                SysRelationService bean1 = SpringUtil.getBean(SysRelationService.class);
                if(bean!=null){
                    BasicService<BasicMapper<BasePojo>, BasePojo> thisProxy = (BasicService<BasicMapper<BasePojo>, BasePojo>)bean;

                    if(thisProxy!=null){
                        List<BasePojo> pojoList = thisProxy.selectList(MapUtil.of(relatedField.getAssignmentCode(), id));
                        if(CollectionUtils.isNotEmpty(pojoList)){
                            for (BasePojo pojo : pojoList) {
                                thisProxy.deleteById(pojo);
                            }
                        }
                    }
                }

            }
        }

    }

    /**
     * ?????????????????????
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
                //?????????????????????????????????
                for (SysField sysField : fieldList) {
                    if(StrUtil.isNotBlank(sysField.getAssignmentCode())
                            &&StrUtil.isNotBlank(sysField.getDisplayCode())
                            &&!sysField.getAssignmentCode().equals(sysField.getDisplayCode())){
                        //????????????????????????????????????????????????????????????????????????????????????????????????

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
                                //??????????????????????????????
                                Object value = assignmentCodeField.get(entity);
                                String displayValue = "-";

                                if(null != value){
                                    //??????????????????????????????
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
     * ?????????????????????
     * @param map
     * @return
     */
    public Map<String,Object> format(Map<String,Object> map) {
        if(map==null)map=new HashMap<>();
        SysEntity sysEntity = getEntity();
        if(sysEntity==null)return map;

        SysLoginAccountDetails loginAccount = UserUtil.getLoginAccount();
        String roleIds = loginAccount.getRoleId();

        if(StrUtil.isNotBlank(roleIds)){
            //??????????????????
            QueryWrapper<SysDataPermission> queryWrapper = new QueryWrapper<SysDataPermission>().eq("entity_id", sysEntity.getId()).in("role_id",roleIds);
            List<SysDataPermission> sysDataPermissionList = sysDataPermissionService.list(queryWrapper);
            map = dataPermissionFormat(map, sysDataPermissionList);
        }

        //????????????????????????????????????????????????????????????????????????
        if(map.get("orderByAsc")==null&&map.get("orderByDesc")==null) {
            //??????????????????????????????????????????
            QueryWrapper<SysSort> queryWrapper = new QueryWrapper<SysSort>().eq("entity_id", sysEntity.getId()).orderByDesc("weight").orderByAsc("sort_order");
            List<SysSort> sortList = sysSortService.list(queryWrapper);
            //??????????????????????????????????????????????????????????????????????????????
            if(CollectionUtils.isNotEmpty(sortList)){
                for (SysSort sysSort : sortList) {
                    String assignmentCode = sysFieldService.getById(sysSort.getFieldId()).getAssignmentCode();
                    String sortTypeStr = sysSort.getSortord()?"orderByAsc":"orderByDesc";
                    map.put(sortTypeStr+":"+ System.currentTimeMillis(),assignmentCode);
                }
            }else{
                //?????????????????????????????????
                map.put("orderByDesc:"+ System.currentTimeMillis(),"weight");
                map.put("orderByAsc:"+ System.currentTimeMillis(),"sortOrder");
            }
        }

        if(map.containsKey("rootTreeIds")){
            map.put("in:id",map.get("rootTreeIds"));
            map.remove("rootTreeIds");
        }

        map.remove("isFormat");
        return map;
    }

    //???????????????????????????
    private Map<String,Object> dataPermissionFormat(Map<String,Object> map, List<SysDataPermission> sysDataPermissionList){
        if(CollUtil.isEmpty(sysDataPermissionList))return map;
        for (SysDataPermission sysDataPermission : sysDataPermissionList) {
            if(CollUtil.isNotEmpty(sysDataPermission.getChildren())){
                //??????????????????????????????
                Map<String, Object> orResultMap = dataPermissionFormat(new HashMap<>(), sysDataPermission.getChildren());
                map.put("or:"+System.currentTimeMillis(),orResultMap);
            }else{
                //??????????????????????????????
                SysField sysField = sysFieldService.getById(sysDataPermission.getFieldId());
                String ruleTypeDcode = sysDataPermission.getRuleTypeDcode();
                switch(ruleTypeDcode){
                    case "permission_dataRuleType_equal":
                        map.put(sysField.getAssignmentCode(),sysDataPermission.getThreshold());
                        break;
                    case "permission_dataRuleType_unequal":
                        map.put("ne:"+sysField.getAssignmentCode(),sysDataPermission.getThreshold());
                        break;
                    case "permission_dataRuleType_greater":
                        map.put("gt:"+sysField.getAssignmentCode(),sysDataPermission.getThreshold());
                        break;
                    case "permission_dataRuleType_less":
                        map.put("lt:"+sysField.getAssignmentCode(),sysDataPermission.getThreshold());
                        break;
                    case "permission_dataRuleType_greaterEqual":
                        map.put("ge:"+sysField.getAssignmentCode(),sysDataPermission.getThreshold());
                        break;
                    case "permission_dataRuleType_lessEqual":
                        map.put("le:"+sysField.getAssignmentCode(),sysDataPermission.getThreshold());
                        break;
                    case "permission_dataRuleType_include":
                        map.put("like:"+sysField.getAssignmentCode(),sysDataPermission.getThreshold());
                        break;
                    case "permission_dataRuleType_beIncluded":
                        map.put("in:"+sysField.getAssignmentCode(),sysDataPermission.getThreshold());
                        break;
                }
            }
        }
        return map;
    }

    public int selectCount(Map<String,Object> map) {
        return super.count(wrapperUtil.mapToQueryWrapper(map));
    }
}
