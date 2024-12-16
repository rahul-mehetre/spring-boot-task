package com.book.author.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.book.author.entities.Author;
import com.book.author.entities.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

	 @Query("SELECT b FROM Book b WHERE b.pages = (SELECT MAX(b2.pages) FROM Book b2)")
	 public List<Book> getAutherByMaxLikes();
	 
	 @Query("SELECT COUNT(b) FROM Book b WHERE b.author.autherId = :authorId AND b.publish.publishYear = :year")
	 public Long getPublishBooksCountInYear(Long authorId, Long year);

}
