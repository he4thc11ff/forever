package com.effective.section2.demo5;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TestKeySet {
    public static void main(String[] args) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("A", "A");
        map.put("B", "B");
        map.put("C", "C");

        Set<String> set = map.keySet();
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            System.out.println(it.next()+"①");
        }

        System.out.println("---------------");

        map.put("D", "D");
        set = map.keySet();
        it = set.iterator();
        while(it.hasNext()){
            System.out.println(it.next()+"②");
        }

    }
}
