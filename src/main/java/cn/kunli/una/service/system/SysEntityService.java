package cn.kunli.una.service.system;

import cn.kunli.una.mapper.SysEntityMapper;
import cn.kunli.una.pojo.system.SysEntity;
import cn.kunli.una.pojo.system.SysSort;
import cn.kunli.una.pojo.vo.SysParamMap;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.BaseService;
import cn.kunli.una.utils.common.ListUtil;
import cn.kunli.una.utils.redis.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class SysEntityService extends BaseService<SysEntityMapper, SysEntity> {

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
