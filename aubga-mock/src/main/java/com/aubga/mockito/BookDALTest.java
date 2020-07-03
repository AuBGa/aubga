package com.aubga.mockito;

import static org.mockito.Mockito.mock;

import java.util.Arrays;
import static org.mockito.Mockito.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookDALTest {
	private static BookDAL mockedBookDAL;
	private static Book book1;
	private static Book book2;

	@BeforeClass
	public static void setUp() {
		mockedBookDAL = mock(BookDAL.class);
		// Create few instances of Book class.
		book1 = new Book("8131721019", "Compilers Principles",
				Arrays.asList("D. Jeffrey Ulman", "Ravi Sethi", "Alfred V. Aho", "Monica S. Lam"),
				"Pearson Education Singapore Pte Ltd", 2008, 1009, "BOOK_IMAGE");

		book2 = new Book("9788183331630", "Let Us C 13th Edition", Arrays.asList("Yashavant Kanetkar"),
				"BPB PUBLICATIONS", 2012, 675, "BOOK_IMAGE");

		when(mockedBookDAL.getAllBooks()).thenReturn(Arrays.asList(book1, book2));
		when(mockedBookDAL.getBook("8131721019")).thenReturn(book1);
		when(mockedBookDAL.addBook(book1)).thenReturn(book1.getIsbn());
		when(mockedBookDAL.updateBook(book1)).thenReturn(book1.getIsbn());

	}

	@Test
	public void testGetAllBooks() throws Exception {
		mockedBookDAL.getAllBooks();
	}
	@Test
	public void testGetBook() throws Exception {
		mockedBookDAL.getBook("8131721019");
	}
	@Test
	public void testAddBook() throws Exception {
		mockedBookDAL.addBook(book1);
	}
	@Test
	public void testUpdateBook() throws Exception {
		mockedBookDAL.updateBook(book1);
	}
}