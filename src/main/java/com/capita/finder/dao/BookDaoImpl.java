package com.capita.finder.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.capita.finder.domain.Book;
import com.capita.finder.dao.BookDao;

@Repository
public class BookDaoImpl implements BookDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	 
	public void create(Book book) {		
		  Integer id = book.getId().intValue();
		  String author = book.getAuthor();
		  String isbn = book.getIsbn();
		  String title = book.getTitle();
		  
	      String SQL = "INSERT INTO Book (id,author,isbn,title) VALUES (:id, :author, :isbn, :title)";  
	      Map namedParameters = new HashMap();
	      namedParameters.put("id", id);
	      namedParameters.put("author", author);     
	      namedParameters.put("isbn", isbn);  
	      namedParameters.put("title", title);  
	      namedParameterJdbcTemplate.update(SQL, namedParameters);  
	  
	}  
	
	
	public List<Book> findByAuthor(String author) {
		//Map<String, Object> params = new HashMap<String, Object>();
		
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+author);
		//params.put("author", author);
		
		String sql = "SELECT * FROM book WHERE author=:author";
		SqlParameterSource namedParameters = new MapSqlParameterSource("author", author);
		
		List<Book> result = namedParameterJdbcTemplate.query(sql, namedParameters,
				new BookMapper());

		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+result.size());
		
		return result;
	}


	
	public List<Book> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();

		String sql = "SELECT * FROM Book";

		List<Book> result = namedParameterJdbcTemplate.query(sql, params,
				new BookMapper());

		return result;
	}

	
	private static final class BookMapper implements RowMapper<Book> {

		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			Book book = new Book();
			book.setId(new Long(rs.getInt("id")));
			book.setAuthor(rs.getString("author"));
			book.setIsbn(rs.getString("isbn"));
			book.setTitle(rs.getString("title"));
			return book;
		}
	}

}