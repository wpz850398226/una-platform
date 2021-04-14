package cn.kunli.una.redis;

import cn.kunli.una.utils.common.TimeUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Date;

public class TestJedis {
    public static void main(String[] args) {
        /*JedisPool pool = new JedisPool(new JedisPoolConfig(), "121.36.74.28",6379,60000,"chuanghui@2020");
        Jedis jedis = pool.getResource();
        jedis.select(0);
        jedis.set("notify", "你还在吗");
        jedis.expire("notify", 10);

        jedis.select(37);
        jedis.set("test","test");
        jedis.expire("test", 20);*/

        long time = TimeUtil.getNextSecond(new Date(), 1800).getTime();
        System.out.println(time);
    }
}
