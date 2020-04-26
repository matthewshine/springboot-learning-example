package com.kouryoushine.threadlocal;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Optional;

@RestController
public class StatController1 {

    //spring是单例模式，所有线程共享一个对象，这里定义的变量会被所有线程共享
    //创建一个变量用来保存每个线程中的threadc值
    //注：Val是引用类型的，这样可以同时保存在set和每个线程中，只要set持有变量引用就可以获取最终结算结果
    HashSet<Val<Integer>> set = new HashSet<>();

    ThreadLocal <Val<Integer>> threadc= new ThreadLocal<Val<Integer>>(){
        @Override
        protected Val<Integer> initialValue() {
            Val<Integer> val = new Val<>();
            addtoSet(val);
            return val;
        }
    };

    //该段代码依然有线程安全问题，但是只在线程初始化的时候才调用一次，所以对性能影响的范围极大缩小
    synchronized void  addtoSet(Val<Integer> val){
        val.setVal(0);
        set.add(val);
        System.out.println("--withlock--");
    }

    @RequestMapping("/stat")
    public Integer stat(){
        //遍历map获取所有值的和
        Optional<Integer> reduce = set.stream().map(a -> a.getVal()).reduce((a, x) -> a + x);
        System.out.println(reduce.get());
        return reduce.get();
    }


    @RequestMapping("/addthreadlocal")
    public Integer addthreadlocal(){
        Val<Integer> val = threadc.get();
        val.setVal(val.getVal()+1);
        return 1;
    }

}
