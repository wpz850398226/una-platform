package cn.kunli.una.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Subscriber {
    public static void main(String[] args) {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "121.36.74.28",6379,60000,"chuanghui@2020");
        Jedis jedis = pool.getResource();
        //只订阅patten匹配在超时事件
        jedis.psubscribe(new KeyExpiredListener(), "__key*@37__:expired");
    }
}
