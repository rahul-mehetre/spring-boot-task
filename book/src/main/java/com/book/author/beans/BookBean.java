package com.book.author.beans;



import lombok.Data;

@Data
public class BookBean {

	private Long bookId;

	private String bookName;

	private Long pages;
	
	private Long authersID;

	private Long yearOfPublication;
}
