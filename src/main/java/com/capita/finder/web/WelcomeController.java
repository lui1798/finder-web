package com.capita.finder.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.capita.finder.dao.BookDao;
import com.capita.finder.data.ReaderMarc;
import com.capita.finder.domain.Book;

@Controller
public class WelcomeController {

	private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	@Autowired
	BookDao bookDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Model model) throws Exception {

		logger.debug("satish");
		
		List<Book> books = bookDao.findAll();

		System.out.println(books);

		model.addAttribute("book", books);
		
		return "welcome";

	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
    public String viewSearchForm(Model model) {
        Book searchForm = new Book();    
        model.addAttribute("searchForm", searchForm);
           
        return "welcome";
    }
     
	
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView processSearch(@ModelAttribute("searchForm") Book book
            ) throws Exception {
        
        List<Book> booksByAuthor = bookDao.findByAuthor(book.getAuthor());
        ModelAndView model = new ModelAndView("welcome");
		model.addObject("booksByAuthor", booksByAuthor);

		return model;

    }
	

}