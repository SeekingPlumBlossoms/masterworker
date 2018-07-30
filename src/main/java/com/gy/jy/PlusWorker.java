package com.gy.jy;

public class PlusWorker extends Worker {
    @Override
    public Object handle(Object input) {
        Integer i=(Integer)input;
       // System.out.println("此次为"+i);
        return i*i;
    }
}
