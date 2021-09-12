package com.main.fservlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class ShellServlet3 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数之前才有效
        request.setCharacterEncoding("UTF-8");
        System.out.println("获取请求的资源路径"+request.getRequestURI());
        System.out.println("获取请求的绝对路径（统一资源定位器）"+request.getRequestURL());
        System.out.println("获取客户段的IP地址"+request.getRemoteHost());
        System.out.println("获取请求头"+request.getHeader("User-Agent"));
        System.out.println("获取请求的参数"+request.getParameter("username"));
        System.out.println("获取请求的参数"+request.getParameter("password"));
        String[] hoppies = request.getParameterValues("hoppy");
        for(String ho:hoppies){
            System.out.println(ho);
        }
        System.out.println("获取请求的多个参数"+ Arrays.asList(hoppies));
        System.out.println("获取请求的多个参数"+ Arrays.toString(hoppies));


        //请求转发
       // RequestDispatcher requestDispatcher = request.getRequestDispatcher("/hello2"); //而这个在工程项目下找到到web。xml里有，就重新定位到2
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/b.html");
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/");      //定位到web下，即工程项目位置http://localhost:8080/javaweb_war_exploded/

        requestDispatcher.forward(request,response);




    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*//设置服务器字符集为utf-8
        response.setCharacterEncoding("UTF-8");
        //通过响应头，设置浏览器也使用utf-8字符集
        response.setHeader("Content-Type","text/html;charset=UTF-8");*/

        //同时设置服务器和浏览器的字符集，此方法要在接受数据之前用
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = response.getWriter();
        writer.println("返回了信息");
    }
}
