package com.neuedu.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaCache {


    //创建并初始化了一个缓存，初始容量1000，最大尺寸1000，过期时间一天。
    //LRU算法     最多使用次数算法
    //链式写法
    public static LoadingCache<String,String> cacheBuilder = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(1000).expireAfterAccess(1, TimeUnit.DAYS).build(
            new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    //默认方法
                    return "null";
                }
            }
    );

    //放
    public static void putCache(String key,String value)
    {
        cacheBuilder.put(key, value);
    }

    //拿
    public static String getCache(String key)
    {
        String s =null;
        try {
            s = cacheBuilder.get(key);
            if ("null".equals(s))
            {
                return null;
            }
            return s;
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return s;
    }

}
