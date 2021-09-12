package com.javawebtest.service.impl;

import com.javawebtest.bean.Book;
import com.javawebtest.bean.Page;
import com.javawebtest.dao.BookDao;
import com.javawebtest.dao.impl.BookDaoImpl;
import com.javawebtest.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page =new Page<>();

        //设置每页显示数量
        page.setPageSize(pageSize);

        //求总记录数
        Integer pageTotacount =bookDao.queryForPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotacount);
        //求总页码
        Integer pageTotal =pageTotacount / pageSize;
        if (pageTotacount % pageSize >0){
            pageTotal +=1;
        }
        //设置总页码
        page.setPageTotal(pageTotal);

        //设置当前页码
        page.setPageNo(pageNo);



        //求当前页的数据的索引开始
        int begin=(page.getPageNo()-1)*pageSize;
        //求当前页数据
        List<Book> items =bookDao.queryForPageItems(begin,pageSize);
        //设置当前页
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize,int min,int max) {

            Page<Book> page =new Page<>();

            //设置每页显示数量
            page.setPageSize(pageSize);

            //求总记录数

        Integer pageTotacount =bookDao.queryForPageTotalCountByPrice(min,max);
            //设置总记录数
            page.setPageTotalCount(pageTotacount);
            //求总页码
            Integer pageTotal =pageTotacount / pageSize;
            if (pageTotacount % pageSize >0){
                pageTotal +=1;
            }
            //设置总页码
            page.setPageTotal(pageTotal);

            //设置当前页码
            page.setPageNo(pageNo);



            //求当前页的数据的索引开始
            int begin=(page.getPageNo()-1)*pageSize;
            //求当前页数据
            List<Book> items =bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
            //设置当前页
            page.setItems(items);

            return page;

    }
}

























