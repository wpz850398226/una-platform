package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysEntityMapper;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.service.BasicService;
import org.springframework.stereotype.Service;

@Service
public class SysEntityService extends BasicService<SysEntityMapper, SysEntity> {

    //@Cacheable(value = "entityRecordDetail", keyGenerator = "myCacheKeyGenerator", unless = "#result == null")
    /*public SysEntity queryFromRedis(String key) {
        List<SysEntity> ts = mapper.selectBySelective(SysParamMap.MapBuilder.aMap().put("code", key).build());
        if (CollectionUtils.isEmpty(ts)) return null;
        return ts.get(0);
    }*/

}
