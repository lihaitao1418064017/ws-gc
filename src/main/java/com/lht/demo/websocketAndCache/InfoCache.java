package com.lht.demo.websocketAndCache;

import com.google.common.collect.Maps;
import com.lht.demo.guavacache.GuavaAbstractLoadingCache;

import java.util.Map;

public class InfoCache extends GuavaAbstractLoadingCache<String, String> {


    private static Map<String, String> cacheMap = Maps.newHashMap();

    public InfoCache() {

    }


    @Override
    protected String fetchData(String key) {

        return cacheMap.get(key);
    }


}

