package cn.kunli.una.task;

import cn.kunli.una.mapper.SysAccountMapper;
import cn.kunli.una.utils.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

/**
 * @Author Ponzio
 * @Description redis相关定时任务
 * @Date 2020/10/15 13:50
 **/
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@RequestMapping("/redisTask")
public class RedisTask {
    private Logger log = LoggerFactory.getLogger(RedisTask.class);
    @Autowired
    private SysAccountMapper sysAccountMapper;
    @Autowired
    private RedisUtil redisUtil;


    /**
     * @return : void
     * @Author Ponzio
     * @Description 定时查询redis公车申请监听记录
     * @Date 2020/10/15 13:51
     **/
    //@Scheduled(cron = "0 0 0/1 * * ? ")//每1小时执行一次
    @RequestMapping("/vehicleMonitor")
    @ResponseBody
    private void vehicleMonitor() {
        String pattern = "monitor_userVehicle_*";
        //模糊查询keys，查询当前所有的公车申请redis监听
        Set<String> keys = redisUtil.hasKeys(pattern);
        for (String key : keys) {
            Object o = redisUtil.get(key);
            //获取公车申请超时时间戳
            long timeMillis = Long.valueOf(o.toString());
            //比较当前时间
            if (System.currentTimeMillis() >= timeMillis) {
                //获取公车申请记录id
                String applyId = pattern.replace("monitor_userVehicle_", "");
                //如果当前时间晚于公车申请超时时间，则说明遗漏了，手动触发公车申请超时
                //userVehicleDispatchingService.vehicleApplyOutTime(applyId);
                //移除监听的redis记录
                redisUtil.del(key);
            }

        }
    }
}
