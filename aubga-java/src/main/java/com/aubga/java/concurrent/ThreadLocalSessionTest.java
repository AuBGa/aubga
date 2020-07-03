package com.aubga.java.concurrent;

public class ThreadLocalSessionTest {
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

    //获取Session
    public static Session getCurrentSession(){
        Session session =  threadLocal.get();
        //判断Session是否为空，如果为空，将创建一个session，并设置到本地线程变量中
        try {
            if(session ==null&&!session.isOpen()){
                /*if(sessionFactory==null){
                    rbuildSessionFactory();// 创建Hibernate的SessionFactory
                }else{
                    session = sessionFactory.openSession();
                }*/
            }
            threadLocal.set(session);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return session;
    }
}
