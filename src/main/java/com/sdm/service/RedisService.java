package com.sdm.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * com.sdm.service说明:
 * Created by qinyun
 * 2018/6/23 01:09
 */
public interface RedisService {
    /**
     * 放入缓存，默认有效期2小时
     * @param key 缓存key
     * @param obj 缓存对象
     */
    void set(String key, Object obj);

    /**
     * 放入缓存
     * @param key 缓存key
     * @param obj 缓存对象
     * @param timeout 单位：秒
     */
    void set(String key, Object obj, long timeout);

    /**
     * 获取缓存对象
     * @param key
     * @param clazz
     * @return
     */
    <T> T get(String key, Class<T> clazz);

    /**
     *
     * @param key
     * @param clazz List对象泛型类型
     * @return
     */
    List getForList(String key , Class<?> clazz);

    /**
     *
     * @param key
     * @param keyClazz Map的key类型
     * @param valueClazz Map 的value类型
     * @return
     */
    Map getForMap(String key, Class<?> keyClazz, Class<?> valueClazz);

    /**
     * 设置String类型数据，默认2小时
     * @param key
     * @param value
     */
    void setString(String key, String value);

    /**
     * 设置String类型数据
     * @param key
     * @param value
     * @param timeout 过期时间，单位：秒
     */
    void setString(String key, String value, long timeout);

    /**
     * 获取String类型缓存数据
     * @param key
     * @return
     */
    String getString(String key);

    /**
     * 删除缓存
     * @param key
     */
    void delete(String key);


    /**
     * 计数器减1
     * @param key
     * @param delta
     * @return
     */
    long decrement(String key, int delta);

    /**
     * 计数器加1
     * @param key
     * @param delta
     * @return
     */
    long increment(String key, int delta);

    /**
     *
     * @param key
     * @return
     */
    long getIncrValue(String key);

    /**
     * 添加
     * @param key
     * @param o
     */
    Long addToSet(String key, Object o);

    Long addToSet(String key, Object o, long timeout);

    /**
     * 删除
     * @param key
     * @param o
     * @return
     */
    Long removeSet(String key, Object o);

    /**
     * 查找
     * @param key
     * @param o
     * @return
     */
    boolean isMemberInSet(String key, Object o);

    /**
     * 返回set所有元素
     * @param key
     * @return
     */
    Set<Object> SMembers(String key);

    /**
     * 模糊获取key集合
     * @param pattern
     * @return
     */
    Set<String> getKeys(String pattern);


    /**
     * 把值放入hash缓存中
     * @param key key
     * @param hk  field字段
     * @param obj
     * @param timeout
     */
    void setToHash(String key, String hk, Object obj, long timeout);

    void setToHash(String key, String hk, Object obj);

    /**
     * 将map写入缓存
     * @param key
     * @param map
     * @param timeout 失效时间(秒)
     */
    void setMapToHash(String key, Map<String, Object> map, long timeout);

    /**
     *获取map缓存
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> Map<String, T> getMapFromHash(String key, Class<T> clazz);

    /**
     * 获取map缓存中的某个对象
     * @param key
     * @param hk
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getFromHash(String key, String hk, Class<T> clazz);

    /**
     * Hash计数器减1
     * @param key
     * @param hk
     * @param delta
     * @return
     */
    Long decrementHash(String key, String hk, int delta);

    /**
     * Hash计数器加1
     * @param key
     * @param hk
     * @param delta
     * @return
     */
    Long incrementHash(String key, String hk, int delta);

    long getExpire(String key);

    /**
     * 从列左边插入
     * @param key
     * @param value
     * @return
     */
    Long leftPush(String key, String value);

    /**
     * 从列右边插入
     * @param key
     * @param value
     * @return
     */
    Long rightPush(String key, String value);

    /**
     * 从列左边取出
     * @param key
     * @return
     */
    String leftPop(String key);

    /**
     * 从列右边取出
     * @param key
     * @return
     */
    String rightPop(String key);
}
