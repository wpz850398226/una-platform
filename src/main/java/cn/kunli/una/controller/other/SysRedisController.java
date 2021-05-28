package cn.kunli.una.controller.other;

import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.redis.UserVehicleDispatchExpiredListener;
import cn.kunli.una.service.system.SysCompanyService;
import cn.kunli.una.service.system.SysConfigurationService;
import cn.kunli.una.service.system.SysDictionaryService;
import cn.kunli.una.service.system.SysEntityService;
import cn.kunli.una.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
public class SysRedisController implements ApplicationRunner {
    @Autowired
    private SysDictionaryService sysDictionaryService;
    @Autowired
    private SysConfigurationService sysConfigurationService;
    @Autowired
    private SysEntityService sysEntityService;
    @Autowired
    private SysCompanyService sysCompanyService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    UserVehicleDispatchExpiredListener userVehicleDispatchExpiredListener;

    //redis地址
    @Value("${spring.redis.host}")
    private String redisHost;
    //redis端口
    @Value("${spring.redis.port}")
    private Integer redisPort;
    //redis库号
    @Value("${spring.redis.database}")
    private Integer redisDatabase;
    //redis密码
    @Value("${spring.redis.password}")
    private String redisPassword;

    /**
     * 系统初始化
     * 保存所有redis，只运行一次
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
//        sysConfigurationService.refreshRedis();		//添加、更新系统配置类缓存
//        sysDictionaryService.refreshRedis();			//添加、更新字典类缓存
//		sysEntityService.refreshRedis();				//添加、更新实体类缓存
        //sysCompanyService.refreshRedis();                 //添加、更新单位信息缓存


        /*JedisPool pool = new JedisPool(new JedisPoolConfig(), redisHost, redisPort, 60000, redisPassword);
        Jedis jedis = pool.getResource();
        //只订阅patten匹配在超时事件
        jedis.psubscribe(userVehicleDispatchExpiredListener, "__key*@" + redisDatabase + "__:expired");*/
    }

    /**
     * 添加缓存
     *
     * @param obj
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public SysResult save(String key, Object obj) {
        Boolean resultFlag = redisUtil.set(key, obj);
        if (resultFlag) {
            return SysResult.success();
        } else {
            return SysResult.fail();
        }
    }

    /**
     * 通过键名删除redis缓存
     *
     * @param keys
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public SysResult delete(HttpSession session, String... keys) {

        try {
            redisUtil.del(keys);
            return SysResult.success();
        } catch (Exception e) {
            log.error("清理redis失败");
            e.printStackTrace();
            return SysResult.fail();
        }
    }

    @RequestMapping("/querySingle")
    @ResponseBody
    public SysResult querySingle(Model model, HttpSession session, String key) {
        return new SysResult().success(redisUtil.get(key));
    }

    @GetMapping("/list")
    @ResponseBody
    public SysResult list(Model model, HttpSession session, String... keys) {
        List<Object> objList = new ArrayList<>();
        for (String key : keys) {
            objList.add(redisUtil.get(key));
        }
        return new SysResult().success(objList);
    }


    @RequestMapping("/hasKey")
    @ResponseBody
    public SysResult hasKey(String key) {
        return new SysResult().success(redisUtil.hasKey(key));
    }

}
