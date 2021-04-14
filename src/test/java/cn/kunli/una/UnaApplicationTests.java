package cn.kunli.una;

import cn.kunli.una.service.system.SysAccountService;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnaApplicationTests {


    @Autowired
    SysAccountService sysAccountService;

    @Test
    public void exportTest(){

        Map<String,Object> map = new HashMap<>();
        Map<String,Object> subMap = new HashMap<>();
        subMap.put("role","c6cca5a74f9d4740b00247be6ccad555");
        subMap.put("description","灭火救援指挥部部长角色");
        subMap.put("condition","isUnderCoverage==0 or lengTime>1");
        map.put("message",subMap);
        System.out.println(JSONObject.toJSONString(map));

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


    @Test
    public void testStr() {
        String str = new String("");
        String str1 = new String("aaaaaaa");
        String str2 = new String("ababababababababababa");
        String str3 = new String("bbbbbbbbbbbbb");

        System.out.println("输入="+str+",输出："+getNum(str));
        System.out.println("输入="+str1+",输出："+getNum(str1));
        System.out.println("输入="+str2+",输出："+getNum(str2));
        System.out.println("输入="+str3+",输出："+getNum(str3));
    }

    public int getNum (String str){
        int num = 0;
        boolean f = false;
        for (int i = str.length()-1; i >=0; i--) {
            char c = str.charAt(i);
            if (c=='a'){
                if(!f){
                    f = true;
                }
                num++;
            }

            if(c!='a'&&f){
                break;
            }
        }
        return num;
    }

}
