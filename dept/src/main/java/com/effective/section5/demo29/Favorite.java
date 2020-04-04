package com.effective.section5.demo29;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Favorite {
    /**
     * 注意这个地方Map的key进行了参数化，而非是Map本身
     */
    private Map<Class<?>,Object>  map = new HashMap<Class<?>, Object>();
    /**
     * 这个类的缺点就是：比如说你想要将List<String>含有泛型的对象存入Map中是无法实现的。也就是说，无法支持不可具体化的对象
     */
    private Map<List<?>,Object>  map2 = new HashMap<List<?>, Object>();
     
     
    public <E> void add(Class<E> type,E instance) {
        if(type!=null) {
            map.put(type, instance);
        }
    }
     
    public <E> void add(List<E> type,E instance) {
        if(type!=null) {
            map2.put(type, instance);
        }
    }
      
    public <E> E get(Class<E> type) {
        Object object = map.get(type);
        return type.cast(object);
    }
     
}
