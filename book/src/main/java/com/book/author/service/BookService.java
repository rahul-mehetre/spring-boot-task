package com.book.author.service;

import org.springframework.stereotype.Service;

import com.book.author.beans.BookBean;
import com.book.author.beans.CommonResponseBean;
import com.book.author.entities.Book;

@Service
public interface BookService {

	public CommonResponseBean saveBook(BookBean bean);
	
	public CommonResponseBean getBookByID(Long id);
	
	public Book mapBeanToEntity(BookBean bean,CommonResponseBean responseBean);
	
	public BookBean mapEntityToBean(Book entity);
	
	
	public CommonResponseBean getPublishBookByYear(Long authorId,Long year);
	
}
