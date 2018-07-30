package com.gy.jy;

import java.util.Map;
import java.util.Set;

public class ClientMain {

    public static void main(String[] args){
        Master m=new Master(new PlusWorker(),5);
        for (int i = 0; i < 10; i++) {
            m.submit(i);
        }
        m.execute();
        int re=0;
        Map<String,Object> resultMap=m.getResultMap();
        while (resultMap.size()>0|| !m.isComplete()){
            String key=null;
            Set<String> keys=resultMap.keySet();
            for(String k:keys){
                key=k;
                break;
            }
            Integer i=null;
            if(key!=null){
                i=(Integer) resultMap.get(key);
            }
            if(i!=null){
                re+=i;
            }
            if(key!=null){
                resultMap.remove(key);
            }
            System.out.println("此次计算结果为:"+re);
        }
    }
}
