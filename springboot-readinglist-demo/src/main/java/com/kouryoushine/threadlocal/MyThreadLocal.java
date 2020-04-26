package com.kouryoushine.threadlocal;

import java.util.HashMap;

public class MyThreadLocal<T> {

    //1,给每个thread保存一个HashMap<MyThreadLocal<?>,Object>
    //2,通过MyThreadLocal可以获取到变量的值
    static  HashMap<Thread,HashMap<MyThreadLocal<?>,Object>> threadHashMap = new HashMap<Thread,HashMap<MyThreadLocal<?>,Object>>();


    //获取当前线程的HashMap<MyThreadLocal<?>,Object>
    synchronized HashMap<MyThreadLocal<?>, Object> getMap(){ //线程安全，争抢threadHashMap资源
        Thread thread = Thread.currentThread();
        if(threadHashMap.containsKey(thread)){ //如果含有当前thread
            return threadHashMap.get(thread);
        }else {
            HashMap<MyThreadLocal<?>, Object> myThreadLocalObjectHashMap = new HashMap<>();
            threadHashMap.put(thread,myThreadLocalObjectHashMap);
            return  myThreadLocalObjectHashMap;
        }
    }

    T value;
    protected  T initialVlaue(){
        return null;
    }

    T get(){
        HashMap<MyThreadLocal<?>, Object> map = getMap();
        //获取当前线程的threadlocalMap,key是MyThreadLocal<?>
        if(!map.containsKey(this)){ //如果不含有当前threadlocal的变量，初始化值存入map
            map.put(this,initialVlaue());
        }
       // 获取map的value并返回
        return  (T)getMap().get(this);
    }

    void  set(T val){
        getMap().put(this,val);
    }
}
