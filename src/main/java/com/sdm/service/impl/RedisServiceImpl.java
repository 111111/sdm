package com.sdm.service.impl;

import com.sdm.service.RedisService;
import com.sdm.util.JsonRedisSeriaziler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * 说明：
 * Created by qinyun.
 * 2016/9/9 11:39
 */
@Service
public class RedisServiceImpl implements RedisService {
    /**
     opsForValue() 封装操作strings
     opsForList() 封装操作list
     opsForSet() 封装操作sets
     opsForZSet() 封装操作sorted sets
     opsForHash() 封装操作hashs
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static String COMMON_KEY = "sdm.";

    private String getKey(String key){
        if(!key.startsWith(COMMON_KEY)){
            key = COMMON_KEY + key;
        }
        return key;
    }



    /**
     * 放入缓存，默认有效期2小时
     * @param key 缓存key
     * @param obj 缓存对象
     */
    @Override
    public void set(String key, Object obj) {
        set(getKey(key), obj, 60 * 60 * 2);
    }

    /**
     * 放入缓存
     * @param key 缓存key
     * @param obj 缓存对象
     * @param timeout 单位：秒
     */
    @Override
    public void set(String key, Object obj, long timeout) {
        if (obj == null) {
            return;
        }
        String value = "";
        if(obj.getClass().equals(String.class) ||
                obj.getClass().equals(Integer.class) ||
                obj.getClass().equals(Double.class) ||
                obj.getClass().equals(Float.class) ||
                obj.getClass().equals(Short.class) ||
                 obj.getClass().equals(Long.class) ||
                obj.getClass().equals(Boolean.class)){
            value = obj.toString();

        }else{
            value = JsonRedisSeriaziler.seriazileAsString(obj);
        }
        setString(getKey(key), value, timeout);
    }

    /**
     * 获取缓存对象
     * @param key
     * @param clazz
     * @return
     */
    @Override
    public <T> T get(String key, Class<T> clazz) {
        String value = getString(getKey(key));
        return JsonRedisSeriaziler.deserializeAsObject(value, clazz);
    }

    @Override
    public List getForList(String key, Class<?> clazz) {
        String value = getString(getKey(key));
        return JsonRedisSeriaziler.deserializeAsList(value, clazz);
    }

    @Override
    public Map getForMap(String key, Class<?> keyClazz, Class<?> valueClazz) {
        String value = getString(getKey(key));
        return JsonRedisSeriaziler.deserializeAsMap(value, keyClazz, valueClazz);
    }

    /**
     * 设置String类型数据，默认2小时
     * @param key
     * @param value
     */
    @Override
    public void setString(String key, String value){
        setString(getKey(key), value, 60 * 60 * 2);
    }

    /**
     * 设置String类型数据
     * @param key
     * @param value
     * @param timeout 过期时间，单位：秒
     */
    @Override
    public void setString(String key, String value, long timeout){
        if(timeout > 0){
            redisTemplate.opsForValue().set(getKey(key), value, timeout, TimeUnit.SECONDS);
        }else{
            redisTemplate.opsForValue().set(getKey(key), value);
        }

    }



    /**
     * 获取String类型缓存数据
     * @param key
     * @return
     */
    @Override
    public String getString(String key){
        return (String)redisTemplate.opsForValue().get(getKey(key));
    }

    /**
     * 删除缓存
     * @param key
     */
    @Override
    public void delete(String key) {
        redisTemplate.delete(getKey(key));
    }

    /**
     * 计数器减1
     * @param key
     * @param delta
     * @return
     */
    @Override
    public long decrement(String key, int delta) {
        Long value = redisTemplate.opsForValue().increment(getKey(key), -delta);
        return value;
    }

    /**
     * 计数器加1
     * @param key
     * @param delta
     * @return
     */
    @Override
    public long increment(String key, int delta) {
        Long value = redisTemplate.opsForValue().increment(getKey(key), delta);
        return value;
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    public long getIncrValue(String key) {
        return getIncrValue2(getKey(key));
    }

    /**
     *
     * @param key
     * @return
     */
    private long getIncrValue2(final String key) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer=redisTemplate.getStringSerializer();
                byte[] rowkey=serializer.serialize(getKey(key));
                byte[] rowval=connection.get(rowkey);
                try {
                    String val=serializer.deserialize(rowval);
                    return Long.parseLong(val);
                } catch (Exception e) {
                    return 0L;
                }
            }
        });
    }

    /**
     * 添加
     * @param key
     * @param o
     */
    @Override
    public Long addToSet(String key, Object o){
        return addToSet(getKey(key), o, -1);
    }

    /**
     *
     * @param key
     * @param o
     * @param timeout 失效时间(秒)
     * @return
     */
    @Override
    public Long addToSet(String key, Object o, long timeout){
        Long ret = redisTemplate.opsForSet().add(getKey(key), o);
        if(timeout > 0){
            expire(key, timeout);
        }
        return ret;
    }

    /**
     * 删除
     * @param key
     * @param o
     * @return
     */
    @Override
    public Long removeSet(String key, Object o){
        return redisTemplate.opsForSet().remove(getKey(key), o);
    }

    /**
     * 查找
     * @param key
     * @param o
     * @return
     */
    @Override
    public boolean isMemberInSet(String key, Object o){
        return redisTemplate.opsForSet().isMember(getKey(key), o);
    }

    /**
     * 返回集合所有元素
     * @param key
     * @return
     */
    @Override
    public Set<Object> SMembers(String key){
        return redisTemplate.opsForSet().members(getKey(key));
    }

    /**
     * 模糊获取key集合
     * @param pattern
     * @return
     */
    @Override
    public Set<String> getKeys(String pattern){
        return redisTemplate.keys(pattern);
    }

/****************************************************************/
    /**
     * 把值放入hash缓存中
     * @param key key
     * @param hk  field字段
     * @param obj
     * @param timeout 失效时间(秒)
     */
    @Override
    public void setToHash(String key, String hk, Object obj, long timeout){
        String value = "";
        if(obj.getClass().equals(String.class) ||
                obj.getClass().equals(Integer.class) ||
                obj.getClass().equals(Double.class) ||
                obj.getClass().equals(Float.class) ||
                obj.getClass().equals(Short.class) ||
                obj.getClass().equals(Long.class) ||
                obj.getClass().equals(Boolean.class)){
            value = obj.toString();

        }else{
            value = JsonRedisSeriaziler.seriazileAsString(obj);
        }
        redisTemplate.opsForHash().put(getKey(key), hk, value);
        expire(key, timeout);

    }

    @Override
    public void setToHash(String key, String hk, Object value){
        setToHash(getKey(key), hk, value, 60 * 60 * 2);
    }
    /**
     * 将map写入缓存
     * @param key
     * @param map
     * @param timeout 失效时间(秒)
     */
    @Override
    public void setMapToHash(String key, Map<String, Object> map, long timeout){
//        redisTemplate.opsForHash().putAll(COMMON_KEY + key, map);
        if(map != null && map.size() > 0){
            for(Map.Entry<String, Object> entry : map.entrySet()) {
                String k = entry.getKey();
                Object v = entry.getValue();
                if(v != null){
                    setToHash(getKey(key), k, JsonRedisSeriaziler.seriazileAsString(v));
                }
            }
        }
        expire(getKey(key), timeout);
    }

    /**
     *获取map缓存
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> Map<String, T> getMapFromHash(String key, Class<T> clazz){
        BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(getKey(key));
        Map<String, String> stringTMap = boundHashOperations.entries();
        if(stringTMap != null && stringTMap.size() > 0){
            Map<String, T> tMap = new HashMap();
            for(Map.Entry<String, String> entry : stringTMap.entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();
                if(v != null){
                    tMap.put(k, JsonRedisSeriaziler.deserializeAsObject(v, clazz));
                }
            }
            return tMap;
        }
        return null;
    }

    /**
     * 获取map缓存中的某个对象
     * @param key
     * @param hk
     * @param clazz
     * @param <T>
     * @return
     */
    @Override
    public <T> T getFromHash(String key, String hk, Class<T> clazz){
        String v = (String)redisTemplate.opsForHash().get(getKey(key), hk);
        if(v != null && !clazz.equals(String.class)){
            return JsonRedisSeriaziler.deserializeAsObject(v, clazz);
        }else{
            return (T)v;
        }
//        return null;
    }

    /**
     * Hash计数器减1
     * @param key
     * @param hk
     * @param delta
     * @return
     */
    @Override
    public Long decrementHash(String key, String hk, int delta) {
        Long value = redisTemplate.opsForHash().increment(getKey(key), hk, -delta);
        return value;
    }

    /**
     * Hash计数器加1
     * @param key
     * @param hk
     * @param delta
     * @return
     */
    @Override
    public Long incrementHash(String key, String hk, int delta) {
        Long value = redisTemplate.opsForHash().increment(getKey(key), hk, delta);
        return value;
    }

    /**
     * 从列左边插入
     * @param key
     * @param value
     * @return
     */
    @Override
    public Long leftPush(String key, String value) {
        return redisTemplate.opsForList().leftPush(getKey(key), value);
    }

    /**
     * 从列右边插入
     * @param key
     * @param value
     * @return
     */
    @Override
    public Long rightPush(String key, String value) {
        return redisTemplate.opsForList().rightPush(getKey(key), value);
    }

    /**
     * 从列左边取出
     * @param key
     * @return
     */
    @Override
    public String leftPop(String key) {
        return (String)redisTemplate.opsForList().leftPop(getKey(key));
    }

    /**
     * 从列右边取出
     * @param key
     * @return
     */
    @Override
    public String rightPop(String key) {
        return (String)redisTemplate.opsForList().rightPop(getKey(key));
    }

    /**************************************************************************************/

    /**
     * 指定缓存的失效时间
     * @param key 缓存KEY
     * @param timeout 失效时间(秒)
     */
    public void expire(String key, long timeout) {

        if(timeout > 0){
            redisTemplate.expire(getKey(key), timeout, TimeUnit.SECONDS);
        }


    }

    /**
     * 获取缓存时间
     * @param key
     * @return
     */
    @Override
    public long getExpire(String key) {
        return redisTemplate.getExpire(getKey(key));
    }


    /**
     * @return
     */
    public String flushDB() {
        return (String)redisTemplate.execute(new RedisCallback() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    /**
     * @return
     */
    public long dbSize() {
        return (Long)redisTemplate.execute(new RedisCallback() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
    }

    /**
     * @return
     */
    public String ping() {
        return (String)redisTemplate.execute(new RedisCallback() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.ping();
            }
        });
    }

}
