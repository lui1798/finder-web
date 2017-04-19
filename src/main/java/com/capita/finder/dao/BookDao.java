package com.capita.finder.dao;

import java.util.List;

import com.capita.finder.domain.Book;


public interface BookDao {

	public List<Book> findByAuthor(String author);
	
	public List<Book> findAll();
	
	public void create(Book book);

}