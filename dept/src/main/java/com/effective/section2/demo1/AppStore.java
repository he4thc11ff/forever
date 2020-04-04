package com.effective.section2.demo1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务管理者（应用市场）
 */
public class AppStore {
    private AppStore() {
    }
    
    /**
     * 服务
     */
    private static final Map<String, MusicProvider> providers=new ConcurrentHashMap<String, MusicProvider>();
    
    /**
     * 注册服务
     * @param name
     * @param p
     */
    public static void registerProvider(String name, MusicProvider p) {
        providers.put(name, p);
    }

    /**
     * 下载App
     * @param name
     * @return
     */
    public static MusicApp installApp(String name) {
        MusicProvider p = providers.get(name);
        if (p == null) {
            throw new IllegalArgumentException("No provider registered with name : " + name);
        }
        return p.getMusicApp();
    }
}
