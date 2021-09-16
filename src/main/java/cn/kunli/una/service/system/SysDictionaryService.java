package cn.kunli.una.service.system;

import cn.kunli.una.annotation.MyCacheEvict;
import cn.kunli.una.mapper.SysDictionaryMapper;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.redis.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

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
     * @param record
     * @return
     */
    @Override
    @SneakyThrows
    @MyCacheEvict(value = {"list","record:one"})
    @CacheEvict(value = "record:id", keyGenerator = "myCacheKeyGenerator")
    public SysResult updateRecordById(SysDictionary entity) {
        SysResult sysResult = super.updateRecordById(entity);
        if(sysResult.getIsSuccess()){
            if(StringUtils.isNotBlank(entity.getCode())){
                //修改子字典的父类编码
                UpdateWrapper updateWrapper = new UpdateWrapper();
                updateWrapper.setEntity(new SysDictionary().setParentId(entity.getId()));
                updateWrapper.setSql("parent_code = '"+entity.getCode()+"'");
                sysDictionaryService.update(updateWrapper);
            }
        }
        return sysResult;
    }

    /**
     * 通过code模糊查询字典记录
     *
     * @param code
     * @return
     */
    public List<SysDictionary> selectByLikeCode(String code) {
        List<SysDictionary> list = sysDictionaryService.selectList(MapUtil.buildHashMap().put(":code", code).put("orderByAsc","sortOrder").build());
        return list;
    }

    @Override
    public SysResult validate(SysDictionary obj) {
        List<SysDictionary> sameNamelist = sysDictionaryService.selectList(MapUtil.buildHashMap()
                .put("parentId", obj.getParentId()).put("name", obj.getName()).build());
        if(CollectionUtils.isNotEmpty(sameNamelist)&&!sameNamelist.get(0).getId().equals(obj.getId())){
            return SysResult.fail("名字重复，保存失败");
        }
        List<SysDictionary> sameValuelist = sysDictionaryService.selectList(MapUtil.buildHashMap()
                .put("parentId", obj.getParentId()).put("value", obj.getValue()).build());
        if(CollectionUtils.isNotEmpty(sameNamelist)&&!sameNamelist.get(0).getId().equals(obj.getId())){
            return SysResult.fail("值重复，保存失败");
        }
        return SysResult.success();
    }

    //格式化保存实例
    @Override
    public SysDictionary initialize(SysDictionary obj) {
        super.initialize(obj);
        //如果父字典不是根目录，则新增字典根id与父字典保持一致
        SysDictionary parentDictionary = sysDictionaryService.getById(obj.getParentId());

        if (obj.getParentId().equals(0)) {
            obj.setRootId(0);
            obj.setCode(obj.getValue());
        } else {
            obj.setRootId(parentDictionary.getRootId());
            obj.setParentCode(parentDictionary.getCode());
            obj.setCode(parentDictionary.getCode()+"_"+obj.getValue());
        }

        return obj;
    }

    @Override
    public List<SysDictionary> parse(List<SysDictionary> list) {
        if(CollectionUtils.isEmpty(list))return list;
        list = super.parse(list);
        for (SysDictionary record : list) {
            List<SysDictionary> subList = sysDictionaryService.selectList(MapUtil.getMap("parentId", record.getId()));
            if(CollectionUtils.isNotEmpty(subList)){
                this.parse(subList);
            }
            record.setChildren(subList);
        }
        return list;
    }
}
