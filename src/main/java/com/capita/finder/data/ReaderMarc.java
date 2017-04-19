package com.capita.finder.data;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.marc4j.MarcReader;
import org.marc4j.MarcXmlReader;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;


import com.capita.finder.domain.Book;
import com.capita.finder.utils.Constants;

public class ReaderMarc implements Reader {
	
	
	public static List<Book> parseMarcXMLToData() throws Exception {

		InputStream in = new FileInputStream("banks.xml");

		MarcReader reader = new MarcXmlReader(in);

		List<Book> bookList = new ArrayList<Book>();

		int count = 0;

		while (reader.hasNext()) {
			System.out.println(++count);
			Record record = reader.next();

			DataField authFld = (DataField) record
					.getVariableField(Constants.AUTHOR_TAG);
			DataField isbnFld = (DataField) record
					.getVariableField(Constants.ISBN_TAG);
			DataField titleFld = (DataField) record
					.getVariableField(Constants.TTILE_TAG);

			Subfield subfieldAuth = authFld.getSubfield('a');
			String author = subfieldAuth.getData();
			
			Subfield subfieldISBN = isbnFld.getSubfield('a');
			String isbn = subfieldISBN.getData();

			Subfield subfieldTitle = titleFld.getSubfield('a');
			String title = subfieldTitle.getData();

			Book book = new Book();
			book.setId(new Long(count));
			book.setAuthor(author);
			book.setIsbn(isbn);
			book.setTitle(title);
			
			bookList.add(book);
		}

		return bookList;
	}
	

}
