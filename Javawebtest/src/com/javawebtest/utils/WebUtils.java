package com.javawebtest.utils;

import com.javawebtest.bean.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {


    /*        第一版
     public  static  void copyBeanUtils(HttpServletRequest request,Object bean){

        try {
            System.out.println("之前"+bean);
            BeanUtils.populate(bean,request.getParameterMap());

            System.out.println("之后"+bean);
            } catch (Exception e) {
             e.printStackTrace();
            }

        }*/

   /*第二版
    public  static  void  copyBeanUtils(Map value, Object bean){

        try {
            System.out.println("之前"+bean);
            BeanUtils.populate(bean,value);
            System.out.println("之后"+bean);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    /*第三版
    public  static Object copyBeanUtils(Map value, Object bean){

        try {
            System.out.println("之前"+bean);
            *//*第一版 public  static  void copyBeanUtils(HttpServletRequest request,Object bean)

            BeanUtils.populate(bean,request.getParameterMap());*//*
            BeanUtils.populate(bean,value);

            System.out.println("之后"+bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
            return bean;
    }*/

    /*第四版*/
    public  static <T> T copyBeanUtils(Map value, T bean){

        try {
//            System.out.println("之前"+bean);
            /*第一版 public  static  void copyBeanUtils(HttpServletRequest request,Object bean)

            BeanUtils.populate(bean,request.getParameterMap());*/
            BeanUtils.populate(bean,value);

//            System.out.println("之后"+bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int  int_pare(String s,int defulet){
        try {
            return  Integer.parseInt(s);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return  defulet;
    }
}
