package cn.kunli.una.service.sys;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.kunli.una.annotation.LogAnnotation;
import cn.kunli.una.annotation.MyCacheEvict;
import cn.kunli.una.handler.UnaResponseException;
import cn.kunli.una.mapper.SysDictionaryMapper;
import cn.kunli.una.pojo.sys.SysDictionary;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.UnaMapUtil;
import cn.kunli.una.utils.redis.RedisUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class SysDictionaryService extends BasicService<SysDictionaryMapper, SysDictionary> {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public BasicService getThisProxy() {
        return sysDictionaryService;
    }

    /**
     * 更新数据,只操作record中的非空属性
     *
     * @return
     */
    @Override
    @LogAnnotation
    @SneakyThrows
    //@MyCacheEvict(value = {"list","record:one"})
    //@CacheEvict(value = "record:id", keyGenerator = "myCacheKeyGenerator")
    public SysResult updateRecordById(SysDictionary entity) {
        SysResult sysResult = super.updateRecordById(initialize(entity));
        if(sysResult.getIsSuccess()){
            if(StrUtil.isNotBlank(entity.getCode())){
                List<SysDictionary> children = this.selectList(MapUtil.of("parentId", entity.getId()));
                if(CollUtil.isNotEmpty(children)){
                    children.forEach(c -> {
                        SysDictionary sample = (SysDictionary) new SysDictionary().setParentCode(entity.getCode()).setCode(entity.getCode() + "_" + c.getValue()).setId(c.getId());
                        SysResult childResult = this.updateRecordById(sample);
                    });
                }
            }
        }
        return sysResult;
    }

    @Override
    @SneakyThrows
    public void saveValidate(SysDictionary obj) {
        if(StrUtil.isNotBlank(obj.getName())){
            List<SysDictionary> sameNamelist = super.selectList(UnaMapUtil.buildHashMap()
                    .put("parentId", obj.getParentId()).put("name", obj.getName()).build());
            if(CollectionUtils.isNotEmpty(sameNamelist)&&!sameNamelist.get(0).getId().equals(obj.getId())){
                throw new UnaResponseException("保存失败：字典名称重复");
            }
        }

        if(StrUtil.isNotBlank(obj.getValue())){
            List<SysDictionary> sameValuelist = sysDictionaryService.selectList(UnaMapUtil.buildHashMap()
                    .put("parentId", obj.getParentId()).put("value", obj.getValue()).build());
            if(CollectionUtils.isNotEmpty(sameValuelist)&&!sameValuelist.get(0).getId().equals(obj.getId())){
                throw new UnaResponseException("保存失败：字典值重复");
            }

            if(obj.getValue().indexOf("_")!=-1){
                throw new UnaResponseException("保存失败：字典值不能包含下划线_");
            }
        }

    }

    //格式化保存实例
    @Override
    public SysDictionary initialize(SysDictionary obj) {
        super.initialize(obj);
        //如果父字典不是根目录，则新增字典根id与父字典保持一致
        SysDictionary parentDictionary = sysDictionaryService.getById(obj.getParentId());

        if(parentDictionary!=null){
            if (obj.getParentId().equals(0)||obj.getParentId().equals(100000)) {
                obj.setRootId(0);
                obj.setCode(obj.getValue());
            } else {
                obj.setRootId(parentDictionary.getRootId());
                obj.setParentCode(parentDictionary.getCode());
                if(StrUtil.isNotBlank(obj.getValue())){
                    obj.setCode(parentDictionary.getCode()+"_"+obj.getValue());
                }
            }
        }

        return obj;
    }

    @Override
    public List<SysDictionary> parse(List<SysDictionary> list) {
        if(CollectionUtils.isEmpty(list))return list;
        list = super.parse(list);
        //查询所有字典
        List<SysDictionary> allDictionaryList = super.list();

        for (SysDictionary record : list) {
//            List<SysDictionary> subList = sysDictionaryService.selectList(UnaMapUtil.getMap("parentId", record.getId()));

            List<SysDictionary> children = parseChildren(allDictionaryList, record.getId());
            record.setChildren(children);
        }
        return list;
    }

    private List<SysDictionary> parseChildren(List<SysDictionary> list, Integer parentId){
        List<SysDictionary> result = new ArrayList<>();
        Iterator<SysDictionary> iterator = list.iterator();
        while (iterator.hasNext()){
            SysDictionary next = iterator.next();
            if(parentId.equals(next.getParentId())){
                List<SysDictionary> children = parseChildren(list, next.getId());
                if(CollectionUtil.isNotEmpty(children)){
                    next.setChildren(children);
                }
                //将匹配到父id的记录放入返回结果中
                result.add(next);
                //递归遍历list，删除元素报错：fail-fast
//                iterator.remove();
            }
        }
        return result;
    }
}
