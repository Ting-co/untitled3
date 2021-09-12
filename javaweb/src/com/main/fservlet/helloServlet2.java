package com.main.fservlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class helloServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get");
        ServletContext context = getServletConfig().getServletContext();
        //获取getInitParameter到web.xml里的预设域数据
        System.out.println(context.getInitParameter("name"));
        System.out.println(context.getInitParameter("id"));
        //getRealPath获取工程路径，后面还可以接东西
        System.out.println(context.getRealPath("/"));
        //setAttribute设置域数据
        context.setAttribute("key1","lxt");
        //getAttribute获取域数据
        System.out.println(context.getAttribute("key1"));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //请求重定向，两件事设置响应码为302，响应头为新地址
      /*  resp.setStatus(302);
        resp.setHeader("Location","http://localhost:8080/javaweb_war_exploded/hello4");
        //resp.setHeader("Location","https://www.baidu.com/");*/

        resp.sendRedirect("http://localhost:8080/javaweb_war_exploded/hello4");


    }
}
