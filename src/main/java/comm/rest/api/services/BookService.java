package comm.rest.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import comm.rest.api.entities.Book;

@Component
public class BookService {
	
	private static List<Book> list=new ArrayList<>();
	
	static {
		
		list.add(new Book(1,"java Complete Ref","james Ghosalin"));
		list.add(new Book(2,"Html Complete Ref","html founder"));
		list.add(new Book(3,"Python Complete Ref","Python Founder"));

	}
	
	// method to get all
	
	public List<Book> getAllBook(){
		
		return list;
	}
	
	// method to get single Book by id
	
	public Book getBookById(int id) {
		
		Book book=null;
		try{
			
			book=list.stream().filter(e->e.getId()==id).findFirst().get();
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
		return book;
	}
	
	
	
	public Book addBook(Book book) {
		
		list.add(book);
		System.out.println(book);
		return book;
	}

	public void deletBook(int bookId) {
		
		list=list.stream().filter(book->book.getId()!=bookId).collect(Collectors.toList());
		
		
	}
	
	public void updateBook(Book book,int bid) {
		
		list = list.stream().map(b->{
			
			if(b.getId()==bid) {
				
				b.setName(book.getName());
				b.setAuthor(book.getAuthor());
			}
			return b;
			
		}).collect(Collectors.toList());
		
	}
}
