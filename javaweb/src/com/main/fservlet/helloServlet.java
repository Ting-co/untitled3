package com.main.fservlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class helloServlet  implements Servlet {
   public helloServlet(){
       System.out.println("1");
   }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {



        System.out.println("2");
        System.out.println("new push");
        ServletContext servletContext = servletConfig.getServletContext();
        System.out.println(servletContext.getAttribute("key1"));



    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest httpServlet= (HttpServletRequest) servletRequest;
        String method = httpServlet.getMethod();
        if("POST".equals(method)){
            DoPost();
        }else if ("GET".equals(method)){
            DoGet();
        }
        System.out.println("jjjj3");
    }

    public void DoPost(){
        System.out.println("这是post");
    }
    public void DoGet(){
        System.out.println("这是get");
    }
    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("4");
    }
}
