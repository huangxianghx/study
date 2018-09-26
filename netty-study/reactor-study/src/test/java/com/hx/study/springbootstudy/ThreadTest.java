package com.hx.study.springbootstudy;

import org.slf4j.LoggerFactory;


public class ThreadTest {
    private org.slf4j.Logger log= LoggerFactory.getLogger(ThreadTest.class);

    public static void main(String[] args) {
        DoDelete doDelete=new DoDelete();
        doDelete.run();
    }


    private static class DoDelete implements Runnable{
        @Override
        public void run() {
            try{
                System.out.println("do delete");
                int i=1/0;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
