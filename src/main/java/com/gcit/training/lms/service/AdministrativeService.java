package com.gcit.training.lms.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.gcit.training.lms.dao.AuthorDAO;
import com.gcit.training.lms.dao.BookDAO;
import com.gcit.training.lms.dao.BookLoansDAO;
import com.gcit.training.lms.dao.BorrowerDAO;
import com.gcit.training.lms.dao.BranchDAO;
import com.gcit.training.lms.dao.CopiesDAO;
import com.gcit.training.lms.dao.GenreDAO;
import com.gcit.training.lms.dao.PublisherDAO;
import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.BookLoans;
import com.gcit.training.lms.entity.Borrower;
import com.gcit.training.lms.entity.Branch;
import com.gcit.training.lms.entity.Copies;
import com.gcit.training.lms.entity.Genre;
import com.gcit.training.lms.entity.Publisher;

public class AdministrativeService
{

	@Autowired
	AuthorDAO authorDao;

	@Autowired
	BookDAO bookDao;

	@Autowired
	BookLoansDAO bookLoansDao;

	@Autowired
	BorrowerDAO borrowerDao;

	@Autowired
	BranchDAO branchDao;

	@Autowired
	CopiesDAO copiesDao;

	@Autowired
	GenreDAO genreDao;

	@Autowired
	PublisherDAO publisherDao;
	

	
	@Transactional
	public void addAuthor(String authorName) throws SQLException
	{
		Author author = new Author();
		author.setAuthorName(authorName);
		authorDao.create(author);
	}

	public List<Author> getAllAuthors(int pageNo, int pageSize, String searchString) throws SQLException
	{
		if (StringUtils.hasLength(searchString))
			return authorDao.readByName(searchString, pageNo, pageSize);
		else
			return authorDao.readAll(pageNo, pageSize);
	}

	public int getAllAuthorsCount() throws SQLException
	{

		return authorDao.readAllCount();

	}

	@Transactional
	public String deleteAuthor(Integer authorId) throws SQLException
	{
		Author author = new Author();
		author.setAuthorId(authorId);

		if (bookDao.readByAuthor(author).size() != 0)
			return "Unable to delete author, Author is a writer of some of the books owned by library";

		authorDao.delete(author);

		return "success";
	}

	@Transactional
	public void updateAuthor(Integer authorId, String authorName) throws SQLException
	{
		Author author = new Author();
		author.setAuthorId(authorId);
		author.setAuthorName(authorName);
		authorDao.update(author);
	}

	public List<Author> searchAuthors(String searchString, Integer pageNo, Integer pageSize) throws SQLException
	{
		return authorDao.readByName(searchString, pageNo, pageSize);

	}

	public int searchAuthorsCount(String searchString) throws SQLException
	{
		return authorDao.readByNameCount(searchString);
	}

	public String pagination(String url, String searchString, int count, int pageSize)
	{
		StringBuffer sb = new StringBuffer("<script src=\"./resources/template/pagination.js\"></script>");

		int totalPage = ((count) / pageSize);

		if (count % pageSize != 0)
			totalPage++;
		sb.append("<nav><ul class='pagination'>");

		url = url + "?";

		if (StringUtils.hasLength(searchString))
			url = url + "searchString=" + searchString + "&";

		for (int i = 1; i <= totalPage; i++)
			// sb.append("<li><a  href = 'javascript:populate(" + url +
			// "pageNo=" + i + "&pageSize=" + pageSize + ")'>" + i +
			// "</a></li>");
			sb.append("<li><a   class='paginationClass' data-href='" + url + "pageNo=" + i + "&pageSize=" + pageSize + "' >" + i + "</a></li>");

		sb.append("</ul></nav>");

		return sb.toString();

	}

	public Author getAuthorById(int authorId) throws SQLException
	{
		// TODO Auto-generated method stub
		return authorDao.readOne(authorId);
	}

	public Book getBookById(int bookId) throws SQLException
	{
		// TODO Auto-generated method stub
		return bookDao.readOne(bookId);
	}
	public int getAllBooksCount()
	{
		
		return bookDao.getCountAll();
	}

	public List<Book> getAllBooks(int pageNo, int pageSize, String searchString) throws SQLException
	{
		if (StringUtils.hasLength(searchString))
			return bookDao.readByTitle(pageNo, pageSize, searchString);
		else
			return bookDao.readAll(pageNo, pageSize);
	}

	public int getBooksCountByTitle(String searchString)
	{
		return bookDao.getCountByName(searchString);
	}

	public void addBorrower(String borrowerName, String borrowerAddr,String borrowerPhone) throws SQLException{
		Borrower borrower = new Borrower();
		borrower.setAddress(borrowerAddr);
		borrower.setName(borrowerName);
		borrower.setPhoneNo(borrowerPhone);
		try{
			borrowerDao.create(borrower);
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		
	}
	public void addBranch(String branchName, String branchAddr) throws SQLException{
			Branch branch = new Branch();
			branch.setBranchAddress(branchAddr);
			branch.setBranchName(branchName);
	try{
			branchDao.create(branch);
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		
	}
	
	public void addGenre(String genreName) throws SQLException{
			Genre gen = new Genre();
			gen.setGenreName(genreName);
		try{	
			genreDao.create(gen);
		
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Transactional
	public void updateAuthor(int id, String name)throws SQLException{
		
		Author author = new Author();
		author.setAuthorId(id);
		author.setAuthorName(name);
		try{
			
			authorDao.update(author);
		}catch (Exception e){
			e.printStackTrace();
			
		}
		
	}
	@Transactional
	public void updateGenre(int id, String name)throws SQLException{
		
		Genre gen = new Genre();
		gen.setGenreId(id);
		gen.setGenreName(name);
		try{ 
			genreDao.update(gen);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	@Transactional
	public void updatePublisher(int id, String name, String address, String phone)throws SQLException{
		Publisher pub = new Publisher();
		pub.setPublisherId(id);
		pub.setPublisherName(name);
		pub.setPublisherAddress(address);
		pub.setPhone(phone);
		try{
			
			publisherDao.update(pub);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	@Transactional
	public void updateBorrower(int cardNo, String name, String address, String phone)throws SQLException{
		Borrower bow = new Borrower();
		bow.setCardNo(cardNo);
		bow.setName(name);
		bow.setAddress(address);
		bow.setPhoneNo(phone);
		try{
			borrowerDao.update(bow);
		}catch (Exception e){
			e.printStackTrace();
		
		}
		
	}
	@Transactional
	public void updateBranch( int branchId, String name, String address)throws SQLException{
		Branch bow = new Branch();
		bow.setBranchName(name);
		bow.setBranchAddress(address);
		bow.setBranchId(branchId);
		try{
			branchDao.update(bow);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Transactional
	public void updateBook(int bookId, String title, int pubId)throws SQLException{
		Book bk = new Book();
		bk.setBookId(bookId);
		bk.setTitle(title);
		Publisher pub = new Publisher();
		pub.setPublisherId(pubId);
		bk.setPublisher(pub);
		
		try{
		
			bookDao.update(bk);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	@Transactional
	public void updateBookCopies(int bookId, int branchId, int numCopies)throws SQLException{
		Book bk = new Book();
		bk.setBookId(bookId);
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		Copies bc = new Copies();
		bc.setBook(bk);
		bc.setBranch(branch);
		bc.setNoOfCopies(numCopies);
		try{
			copiesDao.update(bc);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	@Transactional
	public void updateBookLoan(int bookId, int branchId, int cardNo,String dateOut, String duedate,String dateIn)throws SQLException{
		Book bk = new Book();
		bk.setBookId(bookId);
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		Borrower b = new Borrower();
		b.setCardNo(cardNo);
		BookLoans bl = new BookLoans();
		bl.setBook(bk);
		bl.setCard(b);
		bl.setBranch(branch);
		bl.setDateOut(Timestamp.valueOf(dateOut));
		bl.setDateIn(Timestamp.valueOf(dateIn));
		bl.setDueDate(Timestamp.valueOf(duedate));
		try{
			bookLoansDao.update(bl);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	public void addBook(int bkId, String title, int pubId) throws SQLException{
			Book bk = new Book();
			Publisher pub = new Publisher();
			bk.setBookId(bkId);
			bk.setTitle(title);
			pub.setPublisherId(pubId);
			bk.setPublisher(pub);
		try{
			
			bookDao.create(bk);
		}catch (Exception e){
			e.printStackTrace();
			
		}
		
	}
	public void addPublisher(String pubName, String pubAddr, String pubPhone) throws SQLException{
		Publisher pub = new Publisher();
		pub.setPhone(pubPhone);
		pub.setPublisherAddress(pubAddr);
		pub.setPublisherName(pubName);
		try{
			publisherDao.create(pub);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void addBookCopie(int bookId, int branchId, int copies) throws SQLException{
			Copies copy = new Copies();
			Branch branch = new Branch();
			Book bk = new Book();
			bk.setBookId(bookId);
			branch.setBranchId(branchId);
			copy.setNoOfCopies(copies);
		try{
			copiesDao.create(copy);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void addBookLoan(int bookId,int branchId ,int cardNo ) throws SQLException{
		
		BookLoans bL = new BookLoans();
		Book book = new Book();
		Branch branch = new Branch();
		Borrower borrower = new Borrower();
		book.setBookId(bookId);
		borrower.setCardNo(cardNo);
		branch.setBranchId(branchId);
		bL.setBook(book);
		bL.setBranch(branch);
		bL.setCard(borrower);
		//bL.setDateOut(dateOut);
		//bL.setDueDate(dueDate);
		//bL.setDateIn(Timestamp.valueOf(dateIn));
		try{
			bookLoansDao.create(bL);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	public List<Author> getAllAuthors(int pageNo, int pageSize) throws SQLException{
		
		try{
			return authorDao.readAll(pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Book> getAllBooks(int pageNo, int pageSize) throws SQLException{
		
		try{
			return bookDao.readAll(pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public List<Publisher> getAllPublisher(int pageNo, int pageSize) throws SQLException{
		try{
			return publisherDao.readAll(pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public List<Branch>getAllBranches(int pageNo, int pageSize) throws SQLException{
		try{
			return branchDao.readAll(pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public List<Genre> getAllGenres(int pageNo, int pageSize) throws SQLException{
		try{
			
			return genreDao.readAll(pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Borrower> getAllBorrowers(int pageNo, int pageSize) throws SQLException{

		try{
			
			return borrowerDao.readAll(pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<BookLoans> getAllBookLoans(int pageNo, int pageSize) throws SQLException{
		try{
			return bookLoansDao.readAll(pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public List<BookLoans> getAllBookLoansByCardNo(int cardNo) throws SQLException{
		try{
			return bookLoansDao.readByCardNo(cardNo);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public List<Copies> getBookCopyTitle(String title )throws SQLException{
		try{
			return (List<Copies>)copiesDao.readByBookTitle(title);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Copies> getBookCopyByBook(int bookId )throws SQLException{
		try{
			return (List<Copies>)copiesDao.readByBook(bookId);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Copies> getAllBookCopie(int pageNo, int pageSize) throws SQLException{
		
		try{
			
			return copiesDao.readAll(pageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public void deletePublisher(Integer authorId) throws SQLException {
		Publisher pub = new Publisher();
		pub.setPublisherId(authorId);
		try{
			publisherDao.delete(pub);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void deleteBook(Integer bookId) throws SQLException {
		Book bk = new Book();
		bk.setBookId(bookId);;
		try{
			bookDao.delete(bk);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public void deleteBorrower(Integer cardNo) throws SQLException {
		Borrower borrower= new Borrower();
		borrower.setCardNo(cardNo);
		try{
			borrowerDao.delete(borrower);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public void deleteBranch(Integer authorId) throws SQLException {
		Branch branch = new Branch();
		branch.setBranchId(authorId);
		try{
			branchDao.delete(branch);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void deleteBookCopy(Integer numCopy) throws SQLException {
		Copies copy = new Copies();
		copy.setNoOfCopies( numCopy);
		try{
			copiesDao.delete(copy);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public void deleteBookLoans(Integer bookId) throws SQLException {
		BookLoans loan = new BookLoans();
		Book bk = new Book();
		bk.setBookId(bookId);
		loan.setBook(bk);
		try{
			bookLoansDao.deleteByBookId(bookId);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public List<Author> searchAuthors(String searchString, Integer pageNo) throws SQLException {
		try{
			return authorDao.readByName(searchString, pageNo,10);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public List<Book> searchBooks(String searchString, Integer pageNo) throws SQLException {
		
		try{

			return bookDao.readByTitle( pageNo, 10,searchString);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Branch> searchBranches(String searchString, Integer pageNo,int pageSize) throws SQLException {

		try{
			return branchDao.readByName(searchString, pageNo,pageSize);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public List<BookLoans> searchBookLoans(int Id, Integer pageNo) throws SQLException {
		
		try{
			
			return bookLoansDao.readByBranchId(Id, pageNo);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public List<Publisher> searchPublisher(String searchString, Integer pageNo) throws SQLException {
		try{
			return publisherDao.readByName(searchString, pageNo);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Borrower> searchBorrower(String searchString, Integer pageNo) throws SQLException {
		try{
			return borrowerDao.readByName(searchString, pageNo);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Genre> searchGenres(String searchString, Integer pageNo, int pageSize) throws SQLException {
		try{
			
			return genreDao.readByName(searchString, pageNo,pageSize);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public List<Copies> searchBookCopy(String copy, Integer pageNo) throws SQLException {
		
		try{
			return copiesDao.readByBookTitle(copy);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
