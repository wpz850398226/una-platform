package cn.kunli.una;

import cn.kunli.una.pojo.app.AppLotteryWelfare;
import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.service.app.AppLotteryWelfareService;
import cn.kunli.una.utils.mybatisplus.GeneratorUtil;
import cn.kunli.una.vo.mybatisplus.GeneratorInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnaApplicationTests {


    @Autowired
    AppLotteryWelfareService appLotteryWelfareService;

    @Test
    public void testGenerate(){
        SysResult<String> stringSysResult = GeneratorUtil.codeGenerate(new GeneratorInfo().setUsername("root").setPassword("GLDpcop789!")
                .setAuthor("wangpz-b").setTableName("sys_entity").setServiceName("framework")
                .setIp("10.0.104.147").setPort(31937).setDatabase("bcp_service_common"));

        System.out.println(stringSysResult.getMessage());
    }

    @Test
    public void operateLottery(){
        //查询所有福彩记录
        List<AppLotteryWelfare> appLotteryWelfares = appLotteryWelfareService.selectList(null);
        //遍历处理
        appLotteryWelfares.stream().forEach( record ->{

            Integer id = record.getId();


        });

    }

    @Test
    public void testJedis() {
        System.out.println("启动测试了");
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1",6379,60000,"kunli@888");
        Jedis jedis = pool.getResource();
        jedis.select(0);
        jedis.set("notify", "你还在吗");
        jedis.expire("notify", 10);

        jedis.select(1);
        jedis.set("test","test");
        jedis.expire("test", 20);
    }

}
