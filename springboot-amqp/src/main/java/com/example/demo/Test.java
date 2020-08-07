package com.example.demo;


import net.dreamlu.mica.core.utils.$;

/**
 * @author : Lee
 * @date : 2020-01-17
 */

public class Test {
    public static void main(String[] args) {
       try {
           String a=null;
           a.equals("11");
       }catch (Exception ex){
           ex.printStackTrace();
           $.toJson(ex.getStackTrace());
           System.out.println($.toJson(ex.getStackTrace()));
           for(StackTraceElement stackTraceElement:ex.getStackTrace()){

               System.out.println(stackTraceElement.getClassName());
               System.out.println(stackTraceElement.getMethodName());
               System.out.println(stackTraceElement.getLineNumber());
           }

       }

    }
}
