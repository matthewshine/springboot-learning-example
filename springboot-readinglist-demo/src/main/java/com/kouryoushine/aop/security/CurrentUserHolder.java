package com.kouryoushine.aop.security;

/**
 * 用来保存临时用户
 */
public class CurrentUserHolder {

    private  static  final ThreadLocal<String> holder = new ThreadLocal<>();

    public  static String get(){
        return  holder.get()==null?"unknown":holder.get();
    }


    public  static void set( String user){
        holder.set(user);
    }
}
