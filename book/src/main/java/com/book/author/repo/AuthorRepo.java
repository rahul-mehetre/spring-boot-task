package com.book.author.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.book.author.entities.Author;
import com.book.author.entities.Book;

@Repository
public interface AuthorRepo extends JpaRepository<Author,Long>{

	
//	 public List<Book> findAllBooks();
	
}
