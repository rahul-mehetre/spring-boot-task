package com.book.author.beans;

import java.util.List;

import com.book.author.entities.Book;

import lombok.Data;

@Data
public class AuthorBean {
	private Long autherId;
	
	private String autherName;
	
	private Integer age;
	
	private String gender;
	
	private Integer rating;
	
	private List<Book> books;
}
