package com.gy.jy;

import java.util.Map;
import java.util.Queue;

/**
 * @author yebin
 */
public class Worker implements Runnable {
    protected Queue<Object> worekQuery;
    protected Map<String,Object> resultMap;

    public void setWorekQuery(Queue<Object> worekQuery) {
        this.worekQuery = worekQuery;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public Object handle(Object input){
        return input;
    }
    @Override
    public void run() {
        while (true){
            Object input=worekQuery.poll();
            if(input==null){
                break;
            }
            Object re=handle(input);
            System.out.println(Thread.currentThread().getName()+"::::"+input);
            resultMap.put(Integer.toString(input.hashCode()),re);
        }
    }
}
