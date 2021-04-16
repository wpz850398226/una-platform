package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysEntityMapper;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.pojo.vo.SysParamMap;
import cn.kunli.una.service.BasicService;
import cn.kunli.una.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SysEntityService extends BasicService<SysEntityMapper, SysEntity> {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SysSortService sysSortService;

    @Override
    //@Cacheable(value = "entityRecordDetail", keyGenerator = "myCacheKeyGenerator", unless = "#result == null")
    public SysEntity queryFromRedis(String key) {

        List<SysEntity> ts = mapper.selectBySelective(SysParamMap.MapBuilder.aMap().put("code", key).build());
        if (CollectionUtils.isEmpty(ts)) return null;
        return ts.get(0);
    }

}
