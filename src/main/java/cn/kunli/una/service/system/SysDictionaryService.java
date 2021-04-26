package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysDictionaryMapper;
import cn.kunli.una.pojo.system.SysDictionary;
import cn.kunli.una.pojo.system.SysMenu;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.common.MapUtil;
import cn.kunli.una.utils.redis.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
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

    @Override
    public List<SysDictionary> resultFormat(List<SysDictionary> list) {
        if(CollectionUtils.isEmpty(list))return list;
        list = super.resultFormat(list);
        for (SysDictionary record : list) {
            List<SysDictionary> subList = this.list(wrapperUtil.mapToWrapper(MapUtil.getMap("parentId", record.getId())));
            if(CollectionUtils.isNotEmpty(subList)){
                this.resultFormat(subList);
            }
            record.setChildren(subList);
        }
        return list;
    }
}
