package com.book.author.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.author.beans.AuthorBean;
import com.book.author.beans.BookBean;
import com.book.author.beans.CommonResponseBean;
import com.book.author.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	
	@PostMapping("/save")
	public ResponseEntity<CommonResponseBean> saveAuthor(@RequestBody BookBean bean){
		
		CommonResponseBean response = bookService.saveBook(bean);
		if(response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@GetMapping("/getPublishBookInYear/{authorId}/{year}")
	public ResponseEntity<CommonResponseBean> getPublishBookByYear(@PathVariable Long authorId,@PathVariable Long year){
		CommonResponseBean response = bookService.getPublishBookByYear(authorId, year);
		if(response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
}
