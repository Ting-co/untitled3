package com.javawebtest.web;

import com.javawebtest.bean.Book;
import com.javawebtest.bean.Page;
import com.javawebtest.service.BookService;
import com.javawebtest.service.impl.BookServiceImpl;
import com.javawebtest.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {

    private BookService bookService=new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        int pageNo = WebUtils.int_pare(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.int_pare(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //2.调用bookssercie。page（pageNo，pagesize）对象
        Page<Book> page =bookService.page(pageNo,pageSize);

        page.setUrl("manager/bookServlet?action=page");

        //3.保存怕个对象到requst域中
        req.setAttribute("page",page);
        //4.请求转发到book_manager页面
        System.out.println("page");
//        req.getRequestDispatcher("/pages/manager/book_manager.jsp?pageNo="+req.getAttribute("pageNo")).forward(req,resp);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);



    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.int_pare(req.getParameter("pageNo"), 0);
        pageNo+=1;
        //获取到前端的数据
      Book book=  WebUtils.copyBeanUtils(req.getParameterMap(),new Book());
      //发送给service处理
        bookService.addBook(book);
        //跳回图书管理页面
        //用请求转发，按F5时会多次提交数据
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);
        System.out.println("add");
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);

    }


    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.int_pare(req.getParameter("id"), 0);

        bookService.deleteBookById(id);

        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.copyBeanUtils(req.getParameterMap(), new Book());

        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询所有数据
        List<Book> books = bookService.queryBooks();
        //保存到域中
        req.setAttribute("books",books);
        //请求转发到图书管理后台
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    protected void getBookIdUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.int_pare(req.getParameter("id"),0);

        Book book = bookService.queryBookById(id);

        req.setAttribute("book",book);


        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);

    }
}
