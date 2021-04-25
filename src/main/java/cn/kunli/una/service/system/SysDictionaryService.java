package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysDictionaryMapper;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.redis.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SysDictionaryService extends BasicService<SysDictionaryMapper, SysDictionary> {

    @Autowired
    RedisUtil redisUtil;

    /**
     * 通过code模糊查询字典记录
     *
     * @param code
     * @return
     */
    public List<SysDictionary> selectByLikeCode(String code) {
        QueryWrapper<SysDictionary> wrapper = wrapperUtil.mapToWrapper(MapUtil.getMap(":code", code)).orderByAsc("sequence");
        List<SysDictionary> list = this.list(wrapper);
        return list;
    }


    /**
     * 根据主键查询名字
     *
     * @param id
     * @return
     */
    public String selectTitleByPrimaryKey(String id) {
        SysDictionary sysDictionary = this.getById(id);
        if (sysDictionary != null) return sysDictionary.getName();
        return null;
    }

    /*@Override
    public SysResult refreshRedis() {
        if (redisUtil.getIsConnect()) {
            Set<String> keys = redisUtil.hasKeys("SysDictionary:*");
            redisUtil.delKeys(keys);

            List<SysDictionary> dictionaryList = selectBySelective(SysParamMap.MapBuilder.aMap().put("type", "集合").build());
            List<SysDictionary> rootDictionary = selectBySelective(SysParamMap.MapBuilder.aMap().put("parentId", 0).build());
            try {
                redisUtil.set("SysDictionary:root", rootDictionary);
                for (SysDictionary collectionDictionary : dictionaryList) {
                    if (StringUtils.isNotBlank(collectionDictionary.getCode()) && ListUtil.isNotNull(collectionDictionary.getChildren())) {
                        String key = "SysDictionary:" + collectionDictionary.getCode();
                        //添加新的缓存数据
                        redisUtil.set(key, collectionDictionary);
                        redisUtil.set("SysDictionary:" + collectionDictionary.getId(), collectionDictionary.getName());
                        for (SysDictionary optionDictionary : collectionDictionary.getChildren()) {
                            if (optionDictionary.getType() != null && optionDictionary.getType().equals("选项") && StringUtils.isNotBlank(optionDictionary.getCode())) {
                                //添加新的缓存数据
                                redisUtil.set("SysDictionary:" + optionDictionary.getCode(), optionDictionary);
                                redisUtil.set("SysDictionary:" + optionDictionary.getId(), optionDictionary.getName());
                            }
                        }
                    }
                }

                return SysResult.success("刷新redis成功");
            } catch (Exception e) {
                e.printStackTrace();
                return SysResult.fail("刷新redis失败");
            }
        } else {
            return SysResult.fail("redis连接失败");
        }
    }*/

    //格式化保存实例
    @Override
    public SysDictionary saveFormat(SysDictionary obj) {
        //如果父字典不是根目录，则新增字典根id与父字典保持一致
        SysDictionary parentDictionary = this.getById(obj.getParentId());
        if (obj.getParentId().equals(0)) {
            obj.setRootId(0);
        } else {
            obj.setRootId(parentDictionary.getRootId());
        }

        super.saveFormat(obj);
        return obj;
    }
}
