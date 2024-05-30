package com.sks.secondkillstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class SecondKillStoreApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testLock01() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Boolean isLock = valueOperations.setIfAbsent("k1", "v1");
        //如果占位成功进行正常操作
        if (isLock) {
            valueOperations.set("name", "xxxx");
            String name = (String) valueOperations.get("name");
            System.out.println("name = " + name);
            //操作结束，删除锁，如果有异常情况，则锁无法释放变成死锁，需要加超时时间
            //redisTemplate.delete("k1");
        } else {
            System.out.println("有线程在使用，请稍后");
        }
    }

    @Test
    public void testLock02() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Boolean isLock = valueOperations.setIfAbsent("k1", "v1",5, TimeUnit.SECONDS);
        //如果占位成功进行正常操作
        if (isLock) {
            valueOperations.set("name", "xxxx");
            String name = (String) valueOperations.get("name");
            System.out.println("name = " + name);
            //操作结束，删除锁，如果有异常情况，则锁无法释放变成死锁，需要加超时时间
            redisTemplate.delete("k1");
        } else {
            System.out.println("有线程在使用，请稍后");
        }
    }

}
