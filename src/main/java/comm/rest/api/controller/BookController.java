package comm.rest.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import comm.rest.api.entities.Book;
import comm.rest.api.services.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/books")
	public Book getBooks() {

		Book book = new Book();
		book.setId(123);
		book.setName("Java Devloper ref");
		book.setAuthor("James Ghoslin");

		return book;
	}

	@GetMapping("/getBooks")
	public ResponseEntity<List<Book>> getAllBooks() {

		List<Book> list = this.bookService.getAllBook();
		if (list.size() <= 0) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}

	@GetMapping("/getBookId/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {

		Book book = this.bookService.getBookById(id);
		if (book == null) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));

	}

	@PostMapping("/addBook")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {

		Book b = null;
		try {
			b = this.bookService.addBook(book);
			return ResponseEntity.of(Optional.of(b));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@DeleteMapping("/deletBook/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {

		try {
			this.bookService.deletBook(bookId);
			return ResponseEntity.status(HttpStatus.OK).build();

		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@PutMapping("/updateBook/{bid}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bid") int bid) {

		try {	
		this.bookService.updateBook(book, bid);
		return ResponseEntity.ok().body(book);
		}
		catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
