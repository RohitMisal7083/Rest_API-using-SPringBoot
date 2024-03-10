package comm.rest.api.dao;

import org.springframework.data.repository.CrudRepository;

import comm.rest.api.entities.Book;

public interface BookRepository extends CrudRepository <Book ,Integer>{
	
	public Book findById(int id);

}
