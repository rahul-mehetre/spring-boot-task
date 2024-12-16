package com.book.author.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.author.beans.BookBean;
import com.book.author.beans.CommonResponseBean;
import com.book.author.entities.Author;
import com.book.author.entities.Book;
import com.book.author.entities.Publish;
import com.book.author.repo.AuthorRepo;
import com.book.author.repo.BookRepo;
import com.book.author.repo.PublishRepo;
import com.book.author.service.BookService;

import jakarta.transaction.Transactional;

@Service
public class BookServiceIMPL implements BookService {

	@Autowired
	private AuthorRepo authorRepo;

	@Autowired
	private BookRepo bookRepo;

	@Override
	public CommonResponseBean saveBook(BookBean bean) {
		CommonResponseBean responseBean = new CommonResponseBean();
		Book bookEntity = mapBeanToEntity(bean, responseBean);
		Optional<Author> author = authorRepo.findById(bean.getAuthersID());
		if (author.isPresent()) {
			bookEntity.setAuthor(author.get());
			author.get().getBooks().add(bookEntity);
			Author updatedAuthor = authorRepo.save(author.get());
			if (updatedAuthor != null) {
				responseBean.setStatus(true);
				responseBean.setMessage("Book Added Successfully .");
				responseBean.setResponseBean(updatedAuthor);

			} else {
				responseBean.setStatus(false);
				responseBean.setMessage("System Error : Unable To Add Book .");
				responseBean.setResponseBean(null);
			}
		} else {
			responseBean.setStatus(false);
			responseBean.setMessage("Author Not Found.");
			responseBean.setResponseBean(null);
		}

		return responseBean;

	}

	@Override
	public CommonResponseBean getBookByID(Long id) {
//		CommonResponseBean responseBean = new CommonResponseBean();
//		responseBean.setStatus(true);
		return null;
	}

	@Override
	public Book mapBeanToEntity(BookBean bean, CommonResponseBean responseBean) {
		Book book = new Book();
		if (bean.getBookId() != null)
			book.setBookId(bean.getBookId());
		if (bean.getBookName() != null)
			book.setBookName(bean.getBookName());
		if (bean.getPages() != null)
			book.setPages(bean.getPages());

		Publish publish = new Publish();
		if (bean.getYearOfPublication() != null) {
			publish.setPublishYear(bean.getYearOfPublication());
			book.setPublish(publish);
		}
		return book;

	}

	@Override
	public BookBean mapEntityToBean(Book entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponseBean getPublishBookByYear(Long authorId, Long year) {
		CommonResponseBean responseBean = new CommonResponseBean();
		Optional<Author>  auther = authorRepo.findById(authorId);
		if(auther.isPresent()) {
			Long count = bookRepo.getPublishBooksCountInYear(authorId, year);
			if (count>0) {
				responseBean.setStatus(true);
				responseBean.setMessage("Published Book Found");
				responseBean.setResponseBean(count);

			} else {
				responseBean.setStatus(false);
				responseBean.setMessage("Book Is Not Published Yet.");
				responseBean.setResponseBean(null);
			}
		} else {
			responseBean.setStatus(false);
			responseBean.setMessage("Author Not Found");
			responseBean.setResponseBean(null);
		}
		

		return responseBean;
	}

}
