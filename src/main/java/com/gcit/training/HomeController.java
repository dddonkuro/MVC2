package com.gcit.training;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.gcit.training.lms.dao.*;

import com.gcit.training.lms.entity.*;
import com.gcit.training.lms.service.*;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	AdministrativeService adminService;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "index";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {

		return "admin";

	}

	@RequestMapping(value = "/borrower", method = RequestMethod.GET)
	public String borrower(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {

		return "borrower";

	}

	@RequestMapping(value = "/librarian", method = RequestMethod.GET)
	public String librarian(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {

		return "librarian";

	}

	@RequestMapping(value = "/viewAuthor", method = RequestMethod.GET)
	public String viewAuthor(Locale locale, Model model, WebRequest webRequest)
		throws SQLException {

		int count = adminService.getAllAuthorsCount();
		String s = adminService.pagination("/training/getAuthor", new String(),
				count, 10);

	model.addAttribute("pagination", s);

		return "viewauthor";

	}

	@RequestMapping(value = "/authorValues", method = RequestMethod.GET)
	public String getAuthorData(Locale locale, Model model,
			WebRequest webRequest) throws SQLException {
		int pages = adminService.getAllAuthorsCount()/10;
		int page =0;
		StringBuilder builder = new StringBuilder("<li><a href='pageNo");
		List<Author> authors;
		
//		int pageNo = Integer.parseInt(webRequest.getParameter("pageNo"));
//		int pageSize = Integer.parseInt(webRequest.getParameter("pageSize"));
//		String searchString = webRequest.getParameter("searchString");
		StringBuffer buffer = new StringBuffer("<div class=\"container\"><ul class=\"pagination\">");
		// String page ="";
		 for(int i=1;i<=pages;i++){
			 //page = ""+i;
			 buffer.append("<li><a href='pageAuthor?pageNo=\"+i+\"'>" +i+"</a></li>");
		 }
		 buffer.append("</ul></div>");
		 model.addAttribute("pagination", buffer.toString());
		//model.addAttribute("pageNo", pageNo);
		
		model.addAttribute("pages",builder.toString());
		authors = (List<Author>) adminService.getAllAuthors(1, 10);
		String divtbl = "<div class='container'><div class='row'> <div class ='col-md-6 col-md-offset-3'>";
		StringBuilder sb = new StringBuilder(divtbl+ "<table class='table table-striped' id='authorsTable'><thead><tr><th>#</th><th>Author Name</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
		for (Author author : authors)
		{
			sb.append("<tr><td>"
					+ author.getAuthorId()
					+ "</td><td>"
					+ author.getAuthorName()
					+ "</td><td align='center'><a href='updateAuthor?authorId="
					+ author.getAuthorId()
					+ "'><button class='btn btn btn-primary'>Edit Author</button></a></td>"
					+ "<td><button type='button' class='btn btn-sm btn-danger' onclick=\"javascript:location.href='deleteAuthor?authorId="
					+ author.getAuthorId()
					+ "';\">Delete Author</button></td></tr>");
		}
		sb.append("</table></div></div>");
		model.addAttribute("result", sb.toString());
		model.addAttribute("total", authors.size());
		//model.addAttribute("pageSize", numbPages);
		return "viewauthor";
	}

	@RequestMapping(value = "/searchAuthor", method = RequestMethod.GET)
	public String searchAuthor(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
		
		String searchString = webRequest.getParameter("searchString");
		int count = adminService.searchAuthorsCount(searchString);

		return "viewauthor";

	}

	@RequestMapping(value = "/updateAuthor", method = RequestMethod.GET)
	public String editAuthor(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {

		String authorId = webRequest.getParameter("authorId");
	
			model.addAttribute("authorId", authorId);
		
		return "editauthor";

	}

	@RequestMapping(value = "/editAuthor", method = RequestMethod.POST)
	public String editAuthorPost(Locale locale, Model model,
			WebRequest webRequest) throws SQLException {

		Integer authorId = Integer
				.parseInt(webRequest.getParameter("authorId"));
		String authorName = webRequest.getParameter("authorName");

		try {
			adminService.updateAuthor(authorId, authorName);
		} catch (Exception E) {
			E.printStackTrace();
		}
		return "editauthor";

	}

	@RequestMapping(value = "/addAuthor", method = RequestMethod.GET)
	public String addAuthor(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
		return "addauthor";
	}

	@RequestMapping(value = "/createAuthor", method = RequestMethod.POST)
	public String addAuthorPost(Locale locale, Model model,
		WebRequest webRequest) throws SQLException {
		String authorName = webRequest.getParameter("authorName");
		try {
			adminService.addAuthor(authorName);
			model.addAttribute("status", "success");

		} catch (Exception E) {
			model.addAttribute("status", "Error");
		}
		return "status";
	}

//	@RequestMapping(value = "/deleteAuthor", method = RequestMethod.GET) 
//	public String DeleteAuthor(Locale locale, Model model, WebRequest webRequest)
//			throws SQLException {
//		int authorId = Integer.parseInt(webRequest.getParameter("authorId"));
//
//		try {
//			model.addAttribute("authorName",
//					adminService.getAuthorById(authorId).getAuthorName());
//			String status = adminService.deleteAuthor(authorId);
//			model.addAttribute("status", status);
//
//		} catch (Exception E) {
//			model.addAttribute("status", "Error Delete Author");
//		}
//		return "deleteauthor";
//	}

	// / book
	// operation********************************************************************888

//	@RequestMapping(value = "/viewBook", method = RequestMethod.GET)
//	public String viewBook(Locale locale, Model model, WebRequest webRequest)
//			throws SQLException {
//		int count = adminService.getAllBooksCount();
//		String s = adminService.pagination("/training/getBook", new String(),
//				count, 10);
//
//		model.addAttribute("pagination", s);
//
//		return "viewbook";
//	}

	@RequestMapping(value = "/findBook", method = RequestMethod.GET)
	public String searchBook(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
		return "viewbook";
	}

	@RequestMapping(value = "/searchBook", method = RequestMethod.POST)
	public String viewBookDatat(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {

		List<Book> books;

//		int pageNo = Integer.parseInt(webRequest.getParameter("pageNo"));
//	   int pageSize = Integer.parseInt(webRequest.getParameter("pageSize"));
		String searchString = webRequest.getParameter("searchString");

		books = (List<Book>) adminService.getAllBooks(1, 10,
				"The lost Tribe");
		String divtbl = "<div class='container'><div class='row'> <div class ='col-md-6 col-md-offset-3'>";
		StringBuffer sb = new StringBuffer("<table class='table table-striped' id='booksTable'><thead><tr><th>#</th><th>Book Title</th><th>Branch</th><th>Number of Copies</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
		for (Book book : books){
			sb.append("<tr><td>"
					+ book.getBookId()
					+ "</td><td name='tile'>"
					+ book.getTitle()
					+ "</td><td align='center'><button type='button' class='btn btn btn-primary'  href='editBook?bookId="
					+ book.getBookId()
					+ "'>Edit Book</button></td><td><button type='button' class='btn btn-sm btn-danger' onclick=\"javascript:location.href='deleteBook?bookId="
					+ book.getBookId() + "';\">Delete Book</button></td></tr>");
		}
		sb.append("<table>");
		model.addAttribute("books", sb.toString());
	
		return "viewbook";//result deleteBook
	}

	@RequestMapping(value = "/addBook", method = RequestMethod.GET)
	public String addBook(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {

		return "addbook";
	}

	@RequestMapping(value = "/addBorrower", method = RequestMethod.GET)
	public String addBorrower(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
		
		return "index";
	}
	@RequestMapping(value = "/pageAuthor", method = RequestMethod.GET)
	public String pageAuthor(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
				int pageNo = Integer.parseInt(webRequest.getParameter("pageNO"));
				 int pages = adminService.getAllAuthorsCount();
				 StringBuffer buffer = new StringBuffer("<div class=\"container\"><ul class=\"pagination\">");
				 String page ="";
				 for(int i=1;i<=pages;i++){
					 //page = ""+i;
					 buffer.append("<li><a href='pageAuthor?pageNo=\"+i+\"'>" +i+"</a></li>");
				 }
				 buffer.append("</ul></div>");
				 model.addAttribute("pagination", buffer.toString());
				model.addAttribute("pageNo", pageNo);
		return "viewbookloan";
	}

	@RequestMapping(value = "/createBorrower", method = RequestMethod.POST)
	public String createBorrower(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
		String  Name = webRequest.getParameter("name");
		String Addr = webRequest.getParameter("address");
		String  phone = webRequest.getParameter("phone");
		
		try {

			adminService.addBorrower(Name , Addr, phone);
			System.err.println("Values = "+ Name+" "+Addr+" "+phone);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "addborrower";
	}
	@RequestMapping(value = "/createBranch", method = RequestMethod.POST)
	public String addBranch(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {

		String branchName = webRequest.getParameter("branchName");
		String branchAddr = webRequest.getParameter("branchAddress");
		try {

			adminService.addBranch(branchName, branchAddr);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return "admin";
	}
	
	@RequestMapping(value = "/addBranch", method = RequestMethod.GET)
	public String addNewBranch(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
		return "addbranch";
	}

	
	@RequestMapping(value = "/addGenre", method = RequestMethod.GET)
	public String addGenre(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
		
		return "addgenre";
	}
	
	@RequestMapping(value = "/createPublisher", method = RequestMethod.GET)
	public String createPublisher(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
		
		return "addpublisher";
	}
	
	@RequestMapping(value = "/addgenre", method = RequestMethod.POST)//a possible mismatch
	public String addGenreGet(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
		String genreName = webRequest.getParameter("genreName");

		try {

			adminService.addGenre(genreName);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return "addgenre";
	}

	@RequestMapping(value = "/updateAuthor", method = RequestMethod.POST)
	public String updateAuthor(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
		String authorName = webRequest.getParameter("authorName");
		int authorId = Integer.parseInt(webRequest.getParameter("authorId"));
		try {
			adminService.updateAuthor(authorId, authorName);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "viewauthor";

	}

	@RequestMapping(value = "/editGenre", method = RequestMethod.POST)
	public String updateGenre(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
		String genre = webRequest.getParameter("genreName");
		int Id = Integer.parseInt(webRequest.getParameter("genreId"));
		try {
			adminService.updateGenre(Id, genre);

		} catch (Exception e) {
			e.printStackTrace();

		}
		System.err.println("Genre name: "+ genre+"Id: "+Id);//debug
		return "index";
	}


	@RequestMapping(value = "/updateGenre", method = RequestMethod.GET)
	public String updateGenre2(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
		int genre = Integer.parseInt(webRequest.getParameter("genreId"));
		System.err.println("genre Id: "+ genre);// debug
		model.addAttribute("genreId",genre );
		return "editgenre";
	}
	@RequestMapping(value = "/updatePublisher", method = RequestMethod.POST)
	public String updatePublisher(Locale locale, Model model,
			WebRequest webRequest) throws SQLException {
		int pubId = Integer.parseInt(webRequest.getParameter("publisherId"));
		String pubName = webRequest.getParameter("name");
		String pubAddr = webRequest.getParameter("address");
		String pubPhone = webRequest.getParameter("phone");
		try {
			adminService.updatePublisher(pubId, pubName, pubAddr, pubPhone);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return "index";
	}

	@RequestMapping(value = "/updatetBorrower", method = RequestMethod.POST)
	public String updateBorrower(Locale locale, Model model,
			WebRequest webRequest) throws SQLException {
		String borrowerName = webRequest.getParameter("borrowerName");
		String borrowerAddr = webRequest.getParameter("borrowerAddress");
		String borrowerPhone = webRequest.getParameter("borrowerPhone");
		int cardNo = Integer.parseInt(webRequest.getParameter("cardNo"));
		try {
			adminService.updateBorrower(cardNo, borrowerName, borrowerAddr,
					borrowerPhone);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "index";
	}
	@RequestMapping(value = "/editBorrower", method = RequestMethod.GET)
	public String Borrowerupdate(Locale locale, Model model,
			WebRequest webRequest) throws SQLException {
		int cardNo = Integer.parseInt(webRequest.getParameter("cardNo"));
			model.addAttribute("cardNo",cardNo);
		return "editborrower";
	}
	@RequestMapping(value = "/editBranch", method = RequestMethod.GET)
	public String BranchUpdate(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
			model.addAttribute("branchId",webRequest.getParameter("branchId"));
			System.err.println(webRequest.getParameter("branchId"));
		return "editbranch";
	}
	@RequestMapping(value = "/updateBranch", method = RequestMethod.POST)
	public String editBranch(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
		int branchId = Integer.parseInt(webRequest.getParameter("branchId"));
		String branchName = webRequest.getParameter("branchName");
		String branchAddr = webRequest.getParameter("branchAddress");
		try {
			adminService.updateBranch(branchId,branchName, branchAddr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editbranch";
	}

	@RequestMapping(value = "/updateBook", method = RequestMethod.GET)
	public String updateBookGet(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
		
		String title;
		int id = Integer.parseInt(webRequest.getParameter("bookId"));
		//int pubId = Integer.parseInt(webRequest.getParameter("pubId"));
		Book bk = adminService.getBookById(id);
		title = bk.getTitle();
		model.addAttribute("bookId",id);
		model.addAttribute("titel",title);
		
		return "editbook";
	}
	@RequestMapping(value = "/editBook", method = RequestMethod.POST)
	public String updateBook(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
		String title = webRequest.getParameter("title");
		int bookId = Integer.parseInt(webRequest.getParameter("bookId"));
		int pubId = Integer.parseInt(webRequest.getParameter("publisherId"));
		try {
			adminService.updateBook(bookId, title, pubId);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "editbook";
	}

	@RequestMapping(value = "/editBookCopies", method = RequestMethod.POST)
	public String updateBookCopies(Locale locale, Model model,
			WebRequest webRequest) throws SQLException {
		int bookId = Integer.parseInt(webRequest.getParameter("bookId"));
		int branchId = Integer.parseInt(webRequest.getParameter("branchId"));
		int copies = Integer.parseInt(webRequest.getParameter("NumbCopies"));
		try {
			adminService.updateBookCopies(bookId, branchId, copies);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewbookcopy";
	}

	@RequestMapping(value = "/updateBookLoans", method = RequestMethod.POST)
	public String updateBookLoan(Locale locale, Model model,
			WebRequest webRequest) throws SQLException {
		int bookId = Integer.parseInt(webRequest.getParameter("bookId"));
		int branchId = Integer.parseInt(webRequest.getParameter("branchId"));
		int cardNo = Integer.parseInt(webRequest.getParameter("cardNo"));
		String dateOut = webRequest.getParameter("dateOut");
		String dateIn = webRequest.getParameter("dateIn");
		String dueDate = webRequest.getParameter("dueDate");
		try {
			adminService.updateBookLoan(bookId, branchId, cardNo, dateOut,
					dueDate, dateIn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewbookloan";
	}

	@RequestMapping(value = "/editBookLoans", method = RequestMethod.GET)
	public String editBookLoan(Locale locale, Model model,
			WebRequest webRequest) throws SQLException {
		int bookId = Integer.parseInt(webRequest.getParameter("bookId"));
		int branchId = Integer.parseInt(webRequest.getParameter("branchId"));
		int cardNo = Integer.parseInt(webRequest.getParameter("cardNo"));
		
		return "editbookloans";
	}
	//add operations ****************************************************************************
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBook2(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
		String title = webRequest.getParameter("title");
		int pubId = Integer.parseInt(webRequest.getParameter("pubId"));
		int bookId = Integer.parseInt(webRequest.getParameter("bookId"));
		try {
			adminService.addBook(bookId, title, pubId);
			System.err.println(title +"was added!");
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "admin";
	}

	@RequestMapping(value = "/addPublisher", method = RequestMethod.POST)
	public String addPublisher(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
		String publisherName = webRequest.getParameter("publisherName");
		String publisherAddr = webRequest.getParameter("publisherAddr");
		String publisherPhone = webRequest.getParameter("publisherPhone");
		try {
			adminService.addPublisher(publisherName, publisherAddr,
					publisherPhone);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "index";
	}

	@RequestMapping(value = "/addBookCopy", method = RequestMethod.POST)
	public String addBookCopie(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
		int bookId = Integer.parseInt(webRequest.getParameter("bookId"));
		int branchId = Integer.parseInt(webRequest.getParameter("branchId"));
		int numbOfCopies = Integer.parseInt(webRequest
				.getParameter("numbCopies"));
		try {
			adminService.addBookCopie(bookId, branchId, numbOfCopies);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "addbookcopy";
	}

	@RequestMapping(value = "/createBookLoan", method = RequestMethod.POST)
	public String addBookLoan(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
		int bookId = Integer.parseInt(webRequest.getParameter("bookId"));
		int branchId = Integer.parseInt(webRequest.getParameter("branchId"));
		int cardNo = Integer.parseInt(webRequest.getParameter("cardNo"));
		
		try {
			adminService.addBookLoan(bookId, branchId, cardNo);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "addbookloan";
	}
	
	@RequestMapping(value = "/addBookLoan", method = RequestMethod.GET)
	public String addNewLoan(Locale locale, Model model, WebRequest webRequest)
			throws SQLException {
	
		List<Copies> book;
		int branchId =0;
		int bookId = Integer.parseInt(webRequest.getParameter("bookId"));
		try {
			book =adminService.getBookCopyByBook(bookId);
			branchId = book.get(0).getBranch().getBranchId();
			model.addAttribute("bookId", bookId);
			model.addAttribute("branchId", branchId);
			System.err.println("branchId: "+branchId);
		} catch (Exception e) {
			e.printStackTrace();

		}
		System.err.println("Branch Id: "+branchId);
		return "addbookloan";
	}
	
	
	//view operations ************************************************************************
	 @RequestMapping(value="/getAuthor", method = RequestMethod.GET)
	 public String getAuthors(Locale locale, Model model, WebRequest webRequest) throws
	 SQLException{
		 List<Author> authors;
		 int numbPage = adminService.getAllBooksCount();
		 
	 try{
		 
		 authors = adminService.getAllAuthors(1, 10);
		 StringBuffer sb = new StringBuffer("<table class='table table-striped' id='booksTable'><thead><tr><th>#</th><th>Book Title</th><th>Branch</th><th>Number of Copies</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
			for (Author author : authors)
				sb.append("<tr><td>"
						+ author.getAuthorId()
						+ "</td><td>"
						+ author.getAuthorName()
						+ "</td><td align='center'><button  class='btn btn btn-primary'  href='editAutor?authorId="
						+ author.getAuthorId()
						+ "'>Edit Book</button></td><td><button class='btn btn-sm btn-danger' onclick=\"javascript:location.href='deleteBook?bookId="
						+ author.getAuthorId() + "';\">Delete Book</button></td></tr>");
		
			sb.append("<table>");
			model.addAttribute("result", sb.toString());// data-toggle='modal'
			model.addAttribute("total", authors.size());											// data-target='#myModal1'
			System.out.println(authors.size());					// befor edit href
			return "result";
	 }catch (Exception e){
	 e.printStackTrace();
	 return null;
	 }
	 }
	 @RequestMapping(value="/viewBook", method = RequestMethod.GET)
	 public String getTotalBooks(Locale locale, Model model, WebRequest webRequest) throws
	 SQLException{
//		 int pageNo = Integer.parseInt(webRequest.getParameter("pageNo"));
//		 int pageSize = Integer.parseInt(webRequest.getParameter("pageSize"));
//		 int numbPage = adminService.getAllBooksCount();
		 List<Book> bk;
	 try{
		 bk = adminService.getAllBooks(1, 100);
		 StringBuffer sb = new StringBuffer("<table class='table table-striped' id='booksTable'><thead><tr><th>#</th><th>Book Title</th><th>Publisher</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
			for (Book b : bk)
				sb.append("<tr><td>"
						+ b.getBookId()
						+ "</td><td>"
						+b.getTitle()+"</td><td>"
						+ "</td><td align='center'><a href='updateBook?bookId="
						+ b.getBookId()
						+ "'><button  class='btn btn btn-primary'>Edit</button></a></td><td><button  class='btn btn-sm btn-danger' onclick=\"javascript:location.href='deleteBook?bookId="
						+b.getBookId()+"';\">Remove</button></td></tr>");

			sb.append("</table>");
			model.addAttribute("books", sb.toString());
			System.err.println("Record =: "+bk.size());
			return "viewbook";
		
	 }catch (Exception e){
	 e.printStackTrace();
	 return null;
	 }
	 }
	 
	 @RequestMapping(value="/borrowerView", method = RequestMethod.GET)
	 public String Borrower(Locale locale, Model model, WebRequest webRequest) throws
	 SQLException{
//		 int pageNo = Integer.parseInt(webRequest.getParameter("pageNo"));
//		 int pageSize = Integer.parseInt(webRequest.getParameter("pageSize"));
//		 int numbPage = adminService.getAllBooksCount();
		 List<Book> bk;
	 try{
		 bk = adminService.getAllBooks(1, 100);
		 StringBuffer sb = new StringBuffer("<table class='table table-striped' id='booksTable'><thead><tr><th>#</th><th>Book Title</th><th>Edit</th><th>Return</th></tr></thead><tbody>");
			for (Book b : bk)
				sb.append("<tr><td>"
						+ b.getBookId()
						+ "</td><td>"
						+b.getTitle()
						+ "</td><td align='center'><a href='addBookLoan?bookId="
						+ b.getBookId()
						+ "'><button  class='btn btn btn-primary'>Check out</button></a></td></tr>");

			sb.append("<table>");
			model.addAttribute("books", sb.toString());
			return "viewbook";
		
	 }catch (Exception e){
	 e.printStackTrace();
	 return null;
	 }
	 }
	 @RequestMapping(value="/viewPublisher", method = RequestMethod.GET)
			 public String getPublisher(Locale locale, Model model, WebRequest webRequest) throws
			 SQLException{
				 List<Publisher> pubs;
				 int numbPage = adminService.getAllBooksCount();
				 
			 try{
				 
				 pubs = adminService.getAllPublisher(1, 100);
				 StringBuffer sb = new StringBuffer("<table class='table table-striped' id='publishers'><thead><tr><th>#</th><th>Publisher</th><th>Address</th><th>Phone</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
					for (Publisher publisher : pubs)
						sb.append("<tr><td>"
								+ publisher.getPublisherId()
								+ "</td><td>"
								+ publisher.getPublisherName()+ "</td><td>"+publisher.getPublisherAddress()+"</td><td>"+publisher.getPhone()
								+ "</td><td align='center'><button class='btn btn btn-primary' onclick=\"javascript:location.href='editPublisher?publisherId="
								+ publisher.getPublisherId()
								+ "';\">Edit Publisher</button></td><td><button class='btn btn-sm btn-danger' onclick=\"javascript:location.href='deletePublisher?publisherId="
								+ publisher.getPublisherId() + "';\">Delete Publisher</button></td></tr>");

					sb.append("</table>");
					model.addAttribute("result", sb.toString());// data-toggle='modal'
					model.addAttribute("total", pubs.size());											// data-target='#myModal1'
					//System.err.println("total"+ pubs.size());					// befor edit href
					return "viewpublisher";
			 }catch (Exception e){
			 e.printStackTrace();
			 return null;
			 }
			 }
	 @RequestMapping(value="/viewBranch", method = RequestMethod.GET)
	 public String getAllBranches(Locale locale, Model model, WebRequest webRequest) throws
	 SQLException{
//		 int pageNo = Integer.parseInt(webRequest.getParameter("pageNo"));
//		 int pageSize = Integer.parseInt(webRequest.getParameter("pageSize"));
		 List<Branch> branches;
	 try{
	 branches = adminService.getAllBranches(1, 10);
	 String divtbl = "<div class='container'><div class='row'> <div class ='col-md-6 col-md-offset-3'>";
		StringBuilder sb = new StringBuilder(divtbl+ "<table class='table table-striped' id='authorsTable'><thead><tr><th>#</th><th>Branch Name</th><th>Address</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
		for (Branch branch : branches)
		{
			sb.append("<tr><td>"
					+  branch.getBranchId()
					+ "</td><td>"
					+  branch.getBranchName()+"</td><td>"+branch.getBranchAddress()
					+ "</td><td align='center'><button class='btn btn btn-primary'  onclick=\"javascript:location.href='editBranch?branchId="
					+ branch.getBranchId()
					+ "';\">Edit branch</button></td><td><button class='btn btn-sm btn-danger' onclick=\"javascript:location.href='deleteBranch?branchId="
					+ branch.getBranchId() + "';\">Delete branch</button></td></tr>");
			
		}
		sb.append("</table></div></div>");
		model.addAttribute("result", sb.toString());
	 
	 return "viewbranch";
	 }catch (Exception e){
	 e.printStackTrace();
	 return null;
	 }
	 }
	 @RequestMapping(value="/viewGenre", method = RequestMethod.GET)
	 public String getAllGenres(Locale locale, Model model, WebRequest webRequest) throws
	 SQLException{
//		 int pageNo = Integer.parseInt(webRequest.getParameter("pageNo"));
//		 int pageSize = Integer.parseInt(webRequest.getParameter("pageSize"));
		 List<Genre> genres;
	 try{
	 genres = adminService.getAllGenres(1, 10);
	 String divtbl = "<div class='container'><div class='row'> <div class ='col-md-6 col-md-offset-3'>";
		StringBuilder sb = new StringBuilder(divtbl+ "<table class='table table-striped' id='authorsTable'><thead><tr><th>#</th><th>Genre Name</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
		for (Genre genre : genres)
		{
			sb.append("<tr><td>"
					+ genre.getGenreId()
					+ "</td><td>"
					+ genre.getGenreName()
					+ "</td><td align='center'><button class='btn btn btn-primary'  onclick=\"javascript:location.href='updateGenre?genreId="
					+ genre.getGenreId()
					+ "';\">Edit Genre</button></td><td><button class='btn btn-sm btn-danger' onclick=\"javascript:location.href='deleteGenre?genreId="
					+ genre.getGenreId() + "';\">Delete Genre</button></td></tr>");
			
		}
		sb.append("</table></div></div>");
		model.addAttribute("result", sb.toString());
	 
	 return "viewgenre";
	 }catch (Exception e){
	 e.printStackTrace();
	 return null;
	 }
	 }
	 @RequestMapping(value="/viewBorrower", method = RequestMethod.GET)
	 public String getAllBorrowers(Locale locale, Model model, WebRequest webRequest) throws
	 SQLException{
//		 int pageNo = Integer.parseInt(webRequest.getParameter("pageNo"));
//		 int pageSize = Integer.parseInt(webRequest.getParameter("pageSize"));
		 List<Borrower> borrowers;
	
		 borrowers = adminService.getAllBorrowers(1, 10);
		 List<Publisher> pubs;
		 int numbPage = adminService.getAllBooksCount();
		 
	 try{
		 
		 //pubs = adminService.getAllPublisher(1, 100);
		 StringBuffer sb = new StringBuffer("<table class='table table-striped' id='publishers'><thead><tr><th>#</th><th>Borrower</th><th>Address</th><th>Phone</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
			for (Borrower b : borrowers)
				sb.append("<tr><td>"
						+ b.getCardNo()
						+ "</td><td>"
						+ b.getName()+ 
						"</td><td>"+b.getAddress()
						+"</td><td>"+b.getPhoneNo()
						+ "</td><td align='center'><button class='btn btn btn-primary' onclick=\"javascript:location.href='editBorrower?cardNo="
						+ b.getCardNo()
						+ "';\">Edit Borrower</button></td><td><button class='btn btn-sm btn-danger' onclick=\"javascript:location.href='deleteBorrower?cardNo="
						+ b.getCardNo() +"';\">Delete Borrower</button></td></tr>");

			sb.append("</table>");
			model.addAttribute("result", sb.toString());// data-toggle='modal'
			//model.addAttribute("total", pubs.size());											// data-target='#myModal1'
			//System.err.println("total"+ borrowers.size());					// befor edit href
			return "viewborrower";
	 }catch (Exception e){
		 e.printStackTrace();
		 return null;
	 }
 }
	 @RequestMapping(value="/viewBookLoan", method = RequestMethod.GET)
	 public String getAllBookLoans(Locale locale, Model model, WebRequest webRequest) throws
	 SQLException{
//		 int pageNo = Integer.parseInt(webRequest.getParameter("pageNo"));
//		 int pageSize = Integer.parseInt(webRequest.getParameter("pageSize"));
		 List<BookLoans> loans;
	 try{
	 	loans = adminService.getAllBookLoans(1, 10);
	 	
	 	 String divtbl = "<div class='container'><div class='row'> <div class ='col-md-10'>";
			StringBuilder sb = new StringBuilder(divtbl+ "<table class='table table-striped' id='authorsTable'><thead><tr><th>Book title</th><th>Branch Name</th><th>Borrower</th><th>Date Out</th><th>Due date</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
			for (BookLoans loan : loans)
			{
				sb.append("<tr><td>"
						+  loan.getBook().getTitle()
						+ "</td><td>"
						+  loan.getBranch().getBranchName()+"</td><td>"+loan.getCard().getName()+"</td><td>"+loan.getDateOut()+"</td><td>"+loan.getDueDate()
						+ "</td><td align='center'><button class='btn btn btn-primary'  onclick=\"javascript:location.href='editBookLoans?bookId="
						+ loan.getBook().getBookId()+"&branchId="+loan.getBranch().getBranchId()+"&cardNo="+loan.getCard().getCardNo()
						+ "';\">Edit book loan</button></td><td><button class='btn btn-sm btn-danger' onclick=\"javascript:location.href='deleteBookLoan?bookId="
						+ loan.getBook().getBookId() +"&branchId="+loan.getBranch().getBranchId()+"&cardNo="+loan.getCard().getCardNo() +"';\">Delete book loan</button></td></tr>");
				
			}
			sb.append("</table></div></div>");
			model.addAttribute("result", sb.toString());
		 
	 	
	 return "viewbookloan";
	 }catch (Exception e){
	 e.printStackTrace();
	 return null;
	 }
	 }
	 @RequestMapping(value="/viewBookCopy", method = RequestMethod.GET)
	 public String getAllBookCopie(Locale locale, Model model, WebRequest webRequest) throws
	 SQLException{
//		 int pageNo = Integer.parseInt(webRequest.getParameter("pageNo"));
//		 int pageSize = Integer.parseInt(webRequest.getParameter("pageSize"));
		 List<Copies> copies;
	 try{
		 copies = adminService.getAllBookCopie(1, 10);
		 String divtbl = "<div class='container'><div class='row'> <div class ='col-md-10'>";
		 StringBuffer sb = new StringBuffer(divtbl+"<table class='table table-striped' id='booksTable'><thead><tr><th>#</th><th>Book Title</th><th>Branch Name</th><th>Number of Copies</th><th>Edit copy</th><th>Delete</th></tr></thead><tbody>");
			for (Copies b : copies)
				sb.append("<tr><td>"
						+ b.getBook().getBookId()
						+ "</td><td>"
						+b.getBook().getTitle()+"</td><td>"+b.getBranch().getBranchName()+"</td><td>"+b.getNoOfCopies()
						+ "</td><td align='center'><button class='btn btn btn-primary'  onclick=\"javascript:location.href='editCopy?bookId="
						+ b.getBook().getBookId()+"&branchId ="+b.getBranch().getBranchId()
						+ "';\">Edit copy</button></td><td><button class='btn btn-sm btn-danger' onclick=\"javascript:location.href='deleteAuthor?authorId="
						+ b.getBook().getBookId() + "&branchId ="+b.getBranch().getBranchId()+"';\">Delete copy</button></td></tr>");

			sb.append("</table></div></div></div>");
			model.addAttribute("result", sb.toString());
	 	 String  str =copies.get(0).getBook().getTitle();//debug
	 	System.err.println("title: "+str);//debug
		 
	 return "viewbookcopy";
	 }catch (Exception e){
	 e.printStackTrace();
	 return null;
	 }
	 }
	 
	 //delete operations **********************************************************************
	 
	 @RequestMapping(value="/deleteAuthor", method = RequestMethod.GET)
	 public String deleteAuthor(Locale locale, Model model, WebRequest webRequest) throws SQLException {
	 
	 int authorId= Integer.parseInt(webRequest.getParameter("authorId"));
	 
	 try{
		 adminService.deleteAuthor(authorId);
		 System.err.println("Author was deleted");
		 return "admin";
	 }catch (Exception e){
	 e.printStackTrace();
	 return null;
	 }
	 }
	 
	 @RequestMapping(value="/deleteBook", method = RequestMethod.GET)
	 public String deleteBook(Locale locale, Model model, WebRequest webRequest) throws SQLException {
	 
	 int bookId= Integer.parseInt(webRequest.getParameter("bookId"));
	 
	 try{
		 adminService.deleteBook(bookId);
		 model.addAttribute("result", "<h2 style='color:blue;'> was successfully deleted");
		 return "index";
	 }catch (Exception e){
		 System.err.println("Cant delete book: "+bookId);
		 model.addAttribute("result", "<h2 style='color:red;'>Foreign key constrains, unable to delete book");
	 e.printStackTrace();
	 return "index";
	 }
	 }
	
	 @RequestMapping(value="/deletePublisher", method = RequestMethod.GET)
	 public String deletePublisher(Locale locale, Model model, WebRequest webRequest) throws SQLException {
		 int pubId= Integer.parseInt(webRequest.getParameter("publisherId"));
	 try{
		 adminService.deletePublisher(pubId);
	 	return "index";
	 }catch (Exception e){
	 e.printStackTrace();
	 return null;
	 }
	 }
	 @RequestMapping(value="/deleteBorrower", method = RequestMethod.GET)
	 public String deleteBorrower(Locale locale, Model model, WebRequest webRequest) throws SQLException {
		 int cardNo= Integer.parseInt(webRequest.getParameter("cardNo"));
	 try{
		 adminService.deleteBorrower(cardNo);
		 return "index";
	 }catch (Exception e){
	 e.printStackTrace();
	 return null;
	 }
	 }
	 
	 @RequestMapping(value="/deleteBranch", method = RequestMethod.GET)
	 public String deleteBranch(Locale locale, Model model, WebRequest webRequest) throws SQLException {
		 	int branchId = Integer.parseInt(webRequest.getParameter("branchId"));
	 try{
		 adminService.deleteBranch(branchId);
		 return "index";
	 }catch (Exception e){
	 e.printStackTrace();
	 return null;
	 }
	 }
	 @RequestMapping(value="/deleteCopy", method = RequestMethod.GET)
	 public String deleteBookCopy(Locale locale, Model model, WebRequest webRequest) throws SQLException {
		 int copy = Integer.parseInt(webRequest.getParameter("bookId"));
		 
	 try{
	 	adminService.deleteBookCopy(copy);
	 	return "viewbookcopy";
	 }catch (Exception e){
	 e.printStackTrace();
	 return null;
	 }
	 
	 }
	 
	 @RequestMapping(value="/deleteBookLoan", method = RequestMethod.GET)
	 public String deleteBookLoans(Locale locale, Model model, WebRequest webRequest) throws SQLException {
		 int loan = Integer.parseInt(webRequest.getParameter("bookId"));
	 try{
		 adminService.deleteBookLoans(loan);
		 return "viewbookloan";
	 }catch (Exception e){
	 e.printStackTrace();
	 return null;
	 }
	 }
	
	 //search operations **************************************************************************************
	 
	 
	 @RequestMapping(value="/searchAuthor", method = RequestMethod.POST)
	 public String searchAuthors(Locale locale, Model model, WebRequest webRequest)
	 throws SQLException {
//		 int pageNo = Integer.parseInt(webRequest.getParameter("pageNo"));
//		 int pageSize = Integer.parseInt(webRequest.getParameter("pageSize"));
		 String searchString =webRequest.getParameter("searchString");
		 List<Author> authors;
		 String str;
	 try{
	 	authors  = (List<Author>)adminService.getAllAuthors(1, 10, searchString);
	 	 String divtbl = "<div class='container'><div class='row'> <div class ='col-md-10'>";
		 StringBuffer sb = new StringBuffer(divtbl+"<table class='table table-striped' id='booksTable'><thead><tr><th>#</th><th>Author Name</th><th>Edit Author</th><th>Delete</th></tr></thead><tbody>");
			for (Author b : authors)
				sb.append("<tr><td>"
						+ b.getAuthorId()
						+ "</td><td>"
						+b.getAuthorName()
						+ "</td><td align='center'><button class='btn btn btn-primary'  onclick=\"javascript:location.href='editAuthor?authroId="
						+ b.getAuthorId()
						+  "';\">Edit Author</button></td><td><button class='btn btn-sm btn-danger' onclick=\"javascript:location.href='deleteAuthor?authorId="
						+ b.getAuthorId() + "';\">Delete Author</button></td></tr>");

			sb.append("</table></div></div></div>");
			model.addAttribute("result", sb.toString());
	 	 str =authors.get(0).getAuthorName();
	 	System.err.println("Author name: "+authors.get(0).getAuthorName());
	 return "viewauthor";
	 }catch (Exception e){
	 e.printStackTrace();
	 return null;
	 }
	 }
	 
	 @RequestMapping(value="/searchBook", method = RequestMethod.GET)
	 public String searchBooks(Locale locale, Model model, WebRequest webRequest) throws
	 SQLException {
//		 int pageNo = Integer.parseInt(webRequest.getParameter("pageNo"));
//		 int pageSize = Integer.parseInt(webRequest.getParameter("pageSize"));
		 String searchString = webRequest.getParameter("searchString");
		 List<Book> books;
	 try{
		 books= adminService.getAllBooks(1,10, searchString);

		 StringBuffer sb = new StringBuffer("<table class='table table-striped' id='booksTable'><thead><tr><th>#</th><th>Book Title</th><th>Branch</th><th>Number of books</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
			for (Book b : books)
				sb.append("<tr><td>"
						+ b.getBookId()
						+ "</td><td>"
						+b.getTitle()
						+ "</td><td align='center'><button type='button' class='btn btn btn-primary'  href='editbook?bookId="
						+ b.getBookId()+"&pubId ="+b.getPublisher().getPublisherId()
						+ "'>Edit Book</button></td><td><button type='button' class='btn btn-sm btn-danger' onclick=\"javascript:location.href='deleteBook?bookId="
						+ b.getBookId() + "';\">Delete Book</button></td></tr>");

			sb.append("<table>");
			model.addAttribute("books", sb.toString());
	 return "viewbook";
	 }catch (Exception e){
	 e.printStackTrace();
	 return null;
	 }
	 }
	 @RequestMapping(value="/searchBranch", method = RequestMethod.POST)
	 public String searchBranches(Locale locale, Model model, WebRequest webRequest)
	 throws SQLException {
			List<Branch> branches;
			String name = webRequest.getParameter("searchstring");
			System.err.println("Searched values:"+name);
			 try{
				 branches = adminService.searchBranches(name, 1,10);
				 String divtbl = "<div class='container'><div class='row'> <div class ='col-md-6 col-md-offset-3'>";
					StringBuilder sb = new StringBuilder(divtbl+ "<table class='table table-striped' id='authorsTable'><thead><tr><th>#</th><th>Branch Name</th><th>Address</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
					for (Branch branch : branches)
					{
						sb.append("<tr><td>"
								+  branch.getBranchId()
								+ "</td><td>"
								+  branch.getBranchName()+"</td><td>"+branch.getBranchAddress()
								+ "</td><td align='center'><button class='btn btn btn-primary'  onclick=\"javascript:location.href='editBranch?branchId="
								+ branch.getBranchId()
								+ "';\">Edit branch</button></td><td><button class='btn btn-sm btn-danger' onclick=\"javascript:location.href='deleteBranch?branchId="
								+ branch.getBranchId() + "';\">Delete branch</button></td></tr>");
						
					}
					sb.append("</table></div></div>");
					model.addAttribute("result", sb.toString());
				 
				 return "viewbranch";
			 }catch (Exception e){
			 e.printStackTrace();
			 return null;
			 }
	 }
	 @RequestMapping(value="/editPublisher", method = RequestMethod.GET)
	 public String editPublisher(Locale locale, Model model, WebRequest webRequest) throws SQLException {
		 int pubId = Integer.parseInt(webRequest.getParameter("publisherId"));
		 System.err.println("Publisher ID: "+ pubId);
		 model.addAttribute("publisherId", pubId);
		 return "editpublisher";
	 }
	 
	
	 
	 @RequestMapping(value="/searchBookLoan", method = RequestMethod.POST)
	 public String searchBookLoans(Locale locale, Model model, WebRequest webRequest) throws SQLException {
		 List<BookLoans> loans;
		 int bookId = Integer.parseInt(webRequest.getParameter("bookId"));
		 
		 try{
		 	loans = adminService.searchBookLoans(bookId, 10);
		 	
		 	 String divtbl = "<div class='container'><div class='row'> <div class ='col-md-10'>";
				StringBuilder sb = new StringBuilder(divtbl+ "<table class='table table-striped' id='authorsTable'><thead><tr><th>Book title</th><th>Branch Name</th><th>Borrower</th><th>Date Out</th><th>Due date</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
				for (BookLoans loan : loans)
				{
					sb.append("<tr><td>"
							+  loan.getBook().getTitle()
							+ "</td><td>"
							+  loan.getBranch().getBranchName()+"</td><td>"+loan.getCard().getName()+"</td><td>"+loan.getDateOut()+"</td><td>"+loan.getDueDate()
							+ "</td><td align='center'><button class='btn btn btn-primary'  onclick=\"javascript:location.href='editBookLoans?bookId="
							+ loan.getBook().getBookId()+"&branchId="+loan.getBranch().getBranchId()+"&cardNo="+loan.getCard().getCardNo()
							+ "';\">Edit book loan</button></td><td><button class='btn btn-sm btn-danger' onclick=\"javascript:location.href='deleteBookLoan?bookId="
							+ loan.getBook().getBookId() +"&branchId="+loan.getBranch().getBranchId()+"&cardNo="+loan.getCard().getCardNo() +"';\">Delete book loan</button></td></tr>");
					
				}
				sb.append("</table></div></div>");
				model.addAttribute("result", sb.toString());
			
		      return "viewbookloan";
		 }catch (Exception e){
		 e.printStackTrace();
		 return null;
		 }
	 }
	
	 @RequestMapping(value="/viewMyBook", method = RequestMethod.GET)
	 public String ViewMyBooks(Locale locale, Model model, WebRequest webRequest) throws SQLException {
		 int cardNo = Integer.parseInt(webRequest.getParameter("cardNo"));
		 List<BookLoans> loans;
	 try{
		 loans = adminService.getAllBookLoansByCardNo(cardNo);
	 	  String divtbl = "<div class='container'><div class='row'> <div class ='col-md-10'>";
			StringBuilder sb = new StringBuilder(divtbl+ "<table class='table table-striped' id='authorsTable'><thead><tr><th>Book title</th><th>Branch Name</th><th>Borrower</th><th>Date Out</th><th>Due date</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
			for (BookLoans loan : loans)
			{
				sb.append("<tr><td>"
						+  loan.getBook().getTitle()
						+ "</td><td>"
						+  loan.getBranch().getBranchName()+"</td><td>"+loan.getCard().getName()+"</td><td>"+loan.getDateOut()+"</td><td>"+loan.getDueDate()
						+ "</td><td align='center'><button class='btn btn btn-primary'  onclick=\"javascript:location.href='editBook?bookId="
						+ loan.getBook().getBookId()
						+ "';\">Return book</button></td><td>");
			}
			sb.append("</table></div></div>");
			model.addAttribute("books", sb.toString());
		 
	 	
	 return "borrower";
	 }catch (Exception e){
	 e.printStackTrace();
	 return null;
	 }
	 }
	 @RequestMapping(value="/searchPublisher", method = RequestMethod.POST)
	 public String searchPublisher(Locale locale, Model model, WebRequest webRequest) throws SQLException {
	 List<Publisher> pubs;
	 	String searchString = webRequest.getParameter("searchString");
	    //pubs = adminService.getAllPublisher(1, 10);
			 	try{
				 
				 pubs = adminService.searchPublisher(searchString, 10);
				 StringBuffer sb = new StringBuffer("<table class='table table-striped' id='publishers'><thead><tr><th>#</th><th>Publisher</th><th>Address</th><th>Phone</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
					for (Publisher publisher : pubs)
						sb.append("<tr><td>"
								+ publisher.getPublisherId()
								+ "</td><td>"
								+ publisher.getPublisherName()+ "</td><td>"+publisher.getPublisherAddress()+"</td><td>"+publisher.getPhone()
								+ "</td><td align='center'><button class='btn btn btn-primary' onclick=\"javascript:location.href='editAutor?authorId"
								+ publisher.getPublisherId()
								+  "';\">Edit Publisher</button></td><td><button class='btn btn-sm btn-danger' onclick=\"javascript:location.href='deleteAuthor?authorId"
								+ publisher.getPublisherId() +  "';\">Delete Publisher</button></td></tr>");

					sb.append("</table>");
					model.addAttribute("result", sb.toString());// data-toggle='modal'
					model.addAttribute("total", pubs.size());											// data-target='#myModal1'
					//System.err.println("total"+ pubs.size());					// befor edit href
					return "viewpublisher";
			 }catch (Exception e){
			 e.printStackTrace();
			 return null;
			 }
	 }
	 
	 @RequestMapping(value="/searchBorrower", method = RequestMethod.POST)
	 public String searchBorrower(Locale locale, Model model, WebRequest webRequest)
	 throws SQLException {
		 List<Borrower> borrowers;
		String borrower = webRequest.getParameter("searchstring");	
		 borrowers = adminService.searchBorrower(borrower, 10);
		 List<Publisher> pubs;
		 int numbPage = adminService.getAllBooksCount();
		 
	 try{
		 
		 //pubs = adminService.getAllPublisher(1, 100);
		 StringBuffer sb = new StringBuffer("<table class='table table-striped' id='publishers'><thead><tr><th>#</th><th>Borrower</th><th>Address</th><th>Phone</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
			for (Borrower b : borrowers)
				sb.append("<tr><td>"
						+ b.getCardNo()
						+ "</td><td>"
						+ b.getName()+ 
						"</td><td>"+b.getAddress()
						+"</td><td>"+b.getPhoneNo()
						+ "</td><td align='center'><button class='btn btn btn-primary' onclick=\"javascript:location.href='editBorrower?cardNo="
						+ b.getCardNo()
						+ "';\">Edit Borrower</button></td><td><button class='btn btn-sm btn-danger' onclick=\"javascript:location.href='deleteBorrower?cardNo="
						+ b.getCardNo() +"';\">Delete Borrower</button></td></tr>");

			sb.append("</table>");
			model.addAttribute("result", sb.toString());// data-toggle='modal'
								// befor edit href
			return "viewborrower";
	 }catch (Exception e){
		 e.printStackTrace();
		 return null;
	 }
	 }
	
	 @RequestMapping(value="/searchGenre", method = RequestMethod.POST)
	 public String searchGenres(Locale locale, Model model, WebRequest webRequest)
	 throws SQLException {
	  String genreNames = webRequest.getParameter("genreName");
		 List<Genre> genres;
		 System.err.println("Searched value: "+genreNames);
		 try{
		 genres = adminService.searchGenres(genreNames, 1,10);
		 String divtbl = "<div class='container'><div class='row'> <div class ='col-md-6 col-md-offset-3'>";
			StringBuilder sb = new StringBuilder(divtbl+ "<table class='table table-striped' id='authorsTable'><thead><tr><th>#</th><th>Genre Name</th><th>Edit</th><th>Delete</th></tr></thead><tbody>");
			for (Genre genre : genres)
			{
				sb.append("<tr><td>"
						+ genre.getGenreId()
						+ "</td><td>"
						+ genre.getGenreName()
						+ "</td><td align='center'><button class='btn btn btn-primary'  onclick=\"javascript:location.href='updateGenre?genreId="
						+ genre.getGenreId()
						+ "';\">Edit Genre</button></td><td><button class='btn btn-sm btn-danger' onclick=\"javascript:location.href='deleteGenre?genreId="
						+ genre.getGenreId() + "';\">Delete Genre</button></td></tr>");
				
			}
			sb.append("</table></div></div>");
			model.addAttribute("result", sb.toString());
			System.err.println("Searched value: "+genreNames);
		 return "viewgenre";
		 }catch (Exception e){
		 e.printStackTrace();
		 return null;
		 }
		
	 }
	 @RequestMapping(value="/searchCopy", method = RequestMethod.POST)
	 public String searchBookCopy(Locale locale, Model model, WebRequest webRequest) throws
	 SQLException {
		 String title = webRequest.getParameter("title");
		 List<Copies>copies;
		
	 try{
	  copies = adminService.getBookCopyTitle(title);
	  String divtbl = "<div class='container'><div class='row'> <div class ='col-md-10'>";
	  StringBuffer sb = new StringBuffer(divtbl+"<table class='table table-striped' id='booksTable'><thead><tr><th>#</th><th>Book Title</th><th>Branch Name</th><th>Number of Copies</th><th>Edit copy</th><th>Delete</th></tr></thead><tbody>");
		for (Copies b : copies)
			sb.append("<tr><td>"
					+ b.getBook().getBookId()
					+ "</td><td>"
					+b.getBook().getTitle()+"</td><td>"+b.getBranch().getBranchName()+"</td><td>"+b.getNoOfCopies()
					+ "</td><td align='center'><button class='btn btn btn-primary'  onclick=\"javascript:location.href='editAuthor?authroId="
					+ b.getBook().getBookId()
					+ "';\">Edit copy</button></td><td><button class='btn btn-sm btn-danger' onclick=\"javascript:location.href='deleteAuthor?authorId="
					+ b.getBook().getBookId() + "';\">Delete copy</button></td></tr>");

		sb.append("</table></div></div></div>");
		model.addAttribute("result", sb.toString());
	 String  str =copies.get(0).getBook().getTitle();//debug
	System.err.println("title: "+str);//debug
	 
		return "viewbookcopy";
	 }catch (Exception e){
	 e.printStackTrace();
	 return null;
	 }
	 }
}
