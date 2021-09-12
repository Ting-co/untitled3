package com.javawebtest.web;

import com.javawebtest.bean.Book;
import com.javawebtest.bean.Page;
import com.javawebtest.service.BookService;
import com.javawebtest.service.impl.BookServiceImpl;
import com.javawebtest.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        int pageNo = WebUtils.int_pare(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.int_pare(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //2.调用bookssercie。page（pageNo，pagesize）对象
        Page<Book> page =bookService.page(pageNo,pageSize);

        page.setUrl("client/clientBookServlet?action=page");

        //3.保存怕个对象到requst域中
        req.setAttribute("page",page);
        //4.请求转发到book_manager页面
        System.out.println("page");
//        req.getRequestDispatcher("/pages/manager/book_manager.jsp?pageNo="+req.getAttribute("pageNo")).forward(req,resp);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);



    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        int pageNo = WebUtils.int_pare(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.int_pare(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.int_pare(req.getParameter("min"), 0);
        int max = WebUtils.int_pare(req.getParameter("max"),Integer.MAX_VALUE);

        //2.调用bookssercie。page（pageNo，pagesize）对象
        Page<Book> page =bookService.pageByPrice(pageNo,pageSize,min,max);

        StringBuffer sb= new StringBuffer("client/clientBookServlet?action=pageByPrice");
        if (req.getParameter("min")!=null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max")!=null){
            sb.append("&max=").append(req.getParameter("max"));
        }

        page.setUrl(sb.toString());

        //3.保存怕个对象到requst域中
        req.setAttribute("page",page);
        //4.请求转发到book_manager页面
        System.out.println("page");
//        req.getRequestDispatcher("/pages/manager/book_manager.jsp?pageNo="+req.getAttribute("pageNo")).forward(req,resp);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);



    }
}
