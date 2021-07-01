package cn.kunli.una.controller.other;

import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.redis.UserVehicleDispatchExpiredListener;
import cn.kunli.una.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

/**
 * redis缓存管理
 *
 * @author Ponzio
 * @version 2020年6月10日14:10:05
 */
@Slf4j
@Controller
@RequestMapping("/sys/redis")
@Order(value = 1)
//public class SysRedisController implements ApplicationRunner {
public class SysRedisController{
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    UserVehicleDispatchExpiredListener userVehicleDispatchExpiredListener;

    /**
     * 系统初始化
     * 保存所有redis，只运行一次
     */
    public void run(ApplicationArguments args) throws Exception {
        /*JedisPool pool = new JedisPool(new JedisPoolConfig(), redisHost, redisPort, 60000, redisPassword);
        Jedis jedis = pool.getResource();
        //只订阅patten匹配在超时事件
        jedis.psubscribe(userVehicleDispatchExpiredListener, "__key*@" + redisDatabase + "__:expired");*/
    }

    @DeleteMapping("/flush")
    @ResponseBody
    public SysResult flush() {
        Set set = redisUtil.hasKeys("*");
        redisUtil.delKeys(set);
        return SysResult.success();
    }

}
