package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.exception.RemoteException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Transactional
public class RedisUtil {

    @Qualifier("stringRedisTemplate")
    @Autowired
    private StringRedisTemplate template;

    @Autowired
    @Qualifier("stringRedisTemplate2")
    private StringRedisTemplate stringRedisTemplate;


    public void mSet(Map<String, String> cacheMap) {
        ValueOperations<String, String> ops = template.opsForValue();
        ops.multiSet(cacheMap);
    }

    public void mSet(Map<String, String> cacheMap, int timeout) {
        ValueOperations<String, String> ops = template.opsForValue();
        ops.multiSet(cacheMap);
        for (Map.Entry<String, String> entry : cacheMap.entrySet()) {
            template.expire(entry.getKey(), timeout, TimeUnit.SECONDS);
        }
    }

    public String get(String key) {
        ValueOperations<String, String> ops = template.opsForValue();
        return ops.get(key);
    }

    public <T> T get(String key, Class<T> clazz) {

        ValueOperations<String, String> ops = template.opsForValue();
        String value = ops.get(key);
        return JSONObject.parseObject(value, clazz);
    }

    public void set(String key, String value, int timeOut) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return;
        }
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, value, timeOut, TimeUnit.SECONDS);
    }

    public void setToken(String key, String value, int timeOut) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return;
        }
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(key, value, timeOut, TimeUnit.SECONDS);
    }

    public boolean delete(String key){
        boolean deleteSuccess = true;
        if (StringUtils.isEmpty(key)){
            return true;
        }
        try {
            deleteSuccess = template.delete(key);
        } catch (Exception e){
            throw new RemoteException("删除缓存key为" + key + "时出现错误，错误原因为：", e);
        }
        return deleteSuccess;
    }
}
