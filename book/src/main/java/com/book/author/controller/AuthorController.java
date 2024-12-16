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
import com.book.author.beans.CommonResponseBean;
import com.book.author.service.AuthorService;
import com.book.author.service.BookService;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

	
	@Autowired
	private AuthorService authorService;
	

	
	@PostMapping("/save")
	public ResponseEntity<CommonResponseBean> saveAuthor(@RequestBody AuthorBean bean){
		
		CommonResponseBean response = authorService.saveAuthor(bean);
		if(response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	@GetMapping("/getAuthorById/{id}")
	public ResponseEntity<CommonResponseBean> getAuthorById(@PathVariable Long id){
		
		CommonResponseBean response = authorService.getAuthorById(id);
		if(response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	
	
	@GetMapping("/getAutherByMaxPages")
	public ResponseEntity<CommonResponseBean> getAutherByMaxPagesWritten(){
		
		CommonResponseBean response = authorService.maxPageWrittenAuthor();
		if(response.getStatus()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
	 
	
}
