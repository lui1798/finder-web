package com.capita.finder.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capita.finder.domain.Book;

public class BookDaoImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	public void WhenBookDataEnterThenItShouldPersist() {
		 	
	
	 ClassPathXmlApplicationContext context = new 
                 ClassPathXmlApplicationContext("classpath:spring-bean-config.xml");
       BookDaoImpl dao = (BookDaoImpl) context.getBean("BookDao");
        
       Book book = new Book();
       book.setId(5000L);
       book.setAuthor("satishsatish");
       book.setIsbn("sdafdaf");
       book.setTitle("TitleTitle"); 
       
       
       dao.create(book);
        
       List<Book> persons = dao.findAll();
       for (Book person : persons) {
           System.out.println(person);
       }
       context.close();
   }
		
		
	

}
