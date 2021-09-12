package com.javawebtest.service;


import com.javawebtest.bean.Book;
import com.javawebtest.bean.Page;

import java.util.List;

public interface BookService {

    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize,int min,int max);

}

