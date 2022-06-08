package cn.kunli.una.config;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.kunli.una.pojo.sys.SysDictionary;
import cn.kunli.una.service.sys.SysDictionaryService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;

@Configuration
public class CacheConfig extends CachingConfigurerSupport {
    @Resource
    private RedisConnectionFactory factory;
    @Resource
    private SysDictionaryService sysDictionaryService;

    /**
     * 自定义生成redis-key
     *
     * @return
     */
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName()).append(".");
            sb.append(method.getName()).append(".");
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            System.out.println("keyGenerator=" + sb.toString());
            return sb.toString();
        };
    }

    @Bean
    @Override
    public CacheResolver cacheResolver() {
        return new SimpleCacheResolver(cacheManager());
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        // 用于捕获从Cache中进行CRUD时的异常的回调处理器。
        return new SimpleCacheErrorHandler();
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        RedisCacheConfiguration cacheConfiguration =
                defaultCacheConfig()
                        //不缓存null
                        .disableCachingNullValues()
                        //设置value为自动转json的object
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                        // 缓存数据保存1小时
                        .entryTtl(Duration.ofHours(1));
        return RedisCacheManager.builder(factory).cacheDefaults(cacheConfiguration).build();
    }

//    @Bean
    public Map<String,String> globalDictionaryMap() {
        Map<String,String> map = new HashMap<>();
        List<SysDictionary> list = new ArrayList<>();
        List<SysDictionary> logType = sysDictionaryService.selectList(MapUtil.of("parentCode", "log_type"));
        List<SysDictionary> logOperate = sysDictionaryService.selectList(MapUtil.of("parentCode", "log_operate"));
        list.addAll(logType);
        list.addAll(logOperate);

        if(CollUtil.isNotEmpty(list)){
            list.forEach(d -> map.put(d.getParentCode()+":"+d.getName(),d.getCode()));
        }

        return map;
    }
}
