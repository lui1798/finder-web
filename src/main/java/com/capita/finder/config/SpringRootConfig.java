package com.capita.finder.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.capita.finder.data.ReaderMarc;
import com.capita.finder.domain.Book;

@ComponentScan({ "com.capita" })
@Configuration
public class SpringRootConfig {

	@Autowired
	DataSource dataSource;

	@Bean
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	@PostConstruct
	public void startDBManager() throws Exception {

		// hsqldb
		DatabaseManagerSwing.main(new String[] { "--url",
				"jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", "" });

		enterMarcXMLData();

	}

	/**
	 * Data Load from MarcXML Data
	 * @throws Exception
	 */
	public void enterMarcXMLData() throws Exception {

		List<Book> bookList = ReaderMarc.parseMarcXMLToData();

		for (Book book : bookList) {
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
			getNamedParameterJdbcTemplate().update(SQL, namedParameters);
		}

	}

}