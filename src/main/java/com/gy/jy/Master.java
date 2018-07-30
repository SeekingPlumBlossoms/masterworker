package com.gy.jy;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiPredicate;

/**
 * @author yebin
 */
public class Master {
    /**任务队列**/
    protected Queue<Object> workQuery=new ConcurrentLinkedQueue<Object>();
    /**worker 进程队列**/
    protected Map<String,Thread> threadMap=new HashMap<String, Thread>();
    /**子任务处理结果**/
    protected Map<String,Object> resultMap=new ConcurrentHashMap<String, Object>();

    public boolean isComplete(){
        for(Map.Entry<String,Thread> entry:threadMap.entrySet()){
            if(entry.getValue().getState()!=Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }
    public  Master(Worker worker,int countWorker){
        worker.setWorekQuery(workQuery);
        worker.setResultMap(resultMap);
        for (int i = 0; i <countWorker ; i++) {
            threadMap.put(Integer.toString(i),new Thread(worker,Integer.toString(i)));
        }

    }

    public  void  submit(Object job){
        workQuery.add(job);
    }
    public  Map<String,Object> getResultMap(){
        return  resultMap;
    }
    public void  execute(){
        threadMap.forEach((key,thread)->thread.start());
    }
}
