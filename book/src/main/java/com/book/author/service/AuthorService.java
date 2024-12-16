package com.book.author.service;

import org.springframework.stereotype.Service;

import com.book.author.beans.AuthorBean;
import com.book.author.beans.CommonResponseBean;

@Service
public interface AuthorService {

	public CommonResponseBean saveAuthor(AuthorBean bean);
	
	public CommonResponseBean getAuthorById(Long id);

	
	public CommonResponseBean maxPageWrittenAuthor() ;
	
}
