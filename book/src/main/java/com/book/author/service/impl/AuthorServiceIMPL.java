package com.book.author.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.author.beans.AuthorBean;
import com.book.author.beans.CommonResponseBean;
import com.book.author.entities.Author;
import com.book.author.entities.Book;
import com.book.author.repo.AuthorRepo;
import com.book.author.repo.BookRepo;
import com.book.author.service.AuthorService;

@Service
public class AuthorServiceIMPL implements AuthorService {

	@Autowired
	private AuthorRepo authorRepo;
	
	@Autowired
	private BookRepo bookRepo;
	
	@Override
	public CommonResponseBean saveAuthor(AuthorBean bean) {
		CommonResponseBean responseBean = new CommonResponseBean();
		
		Author author = mapBeanToEntity(bean);
		Author result = authorRepo.save(author);
		if(result != null) {
			responseBean.setStatus(true);
			responseBean.setMessage("Author Added Successfully. ");
			responseBean.setResponseBean(result);
		}else {
			responseBean.setStatus(false);
			responseBean.setMessage("Error : Author Not Added ");
			responseBean.setResponseBean(null);
		}
		return responseBean;
	}

	@Override
	public CommonResponseBean maxPageWrittenAuthor() {
		CommonResponseBean responseBean = new CommonResponseBean();
		List<Book> result = bookRepo.getAutherByMaxLikes();
		List<AuthorBean> list = new ArrayList<>();
		if(result != null) {
		for(Book b:result) {
			AuthorBean bean = new AuthorBean();
			bean.setAge(b.getAuthor().getAge());
			bean.setAutherName(b.getAuthor().getAutherName());
			bean.setBooks(result);
			list.add(bean);
			responseBean.setMessage("Max Pages Written by Author in Single Book : "+b.getPages());
		}
			responseBean.setStatus(true);
			responseBean.setResponseBean(list);
		}else {
			responseBean.setStatus(false);
			responseBean.setMessage("System Error : Unable Fetch Record");
		}
		return responseBean;
	}
	
	
	public Author mapBeanToEntity(AuthorBean bean) {
		Author author = new Author();
		if (bean.getAge() != null)
			author.setAge(bean.getAge());
		if (bean.getAutherName() != null)
			author.setAutherName(bean.getAutherName());
		if (bean.getGender() != null)
			author.setGender(bean.getGender());
		if (bean.getRating() != null)
			author.setRating(bean.getRating());
		
		return author;
	}

	@Override
	public CommonResponseBean getAuthorById(Long id) {
		CommonResponseBean responseBean = new CommonResponseBean();
		Optional<Author> result = authorRepo.findById(id);
		
		if(result.isPresent()) {
			responseBean.setMessage("Auther Found");
			responseBean.setStatus(true);
			responseBean.setResponseBean(result.get());
		}else {
			responseBean.setStatus(false);
			responseBean.setMessage("Auther Not Found");
		}
		return responseBean;
	}
}
