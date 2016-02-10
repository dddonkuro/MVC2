package com.gcit.training.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.lms.entity.Copies;

public class CopiesDAO extends AbstractDAO implements ResultSetExtractor<List<Copies>>
{
	@Autowired
	JdbcTemplate template;
	
	@Autowired
	BookDAO bookDao;
	
	@Autowired
	BranchDAO branchDao;

	public void create(Copies a) throws SQLException
	{
		template.update("insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?, ? , ?)", new Object[] { a.getBook().getBookId(), a.getBranch().getBranchId(), a.getNoOfCopies() });
	}

	public void update(Copies a) throws SQLException
	{
		template.update("update tbl_book_copies set noOfCopies = ? where bookId = ? AND branchId = ?", new Object[] { a.getNoOfCopies(), a.getBook().getBookId(), a.getBranch().getBranchAddress() });
	}

	public void delete(Copies a) throws SQLException
	{

		template.update("delete from tbl_book_copies where bookId = ? AND branchId = ?", new Object[] { a.getBook().getBookId(), a.getBranch().getBranchAddress() });

	}

	public Integer bookCopyCount()
	{
		return template.queryForObject("SELECT count(*) from tbl_book_copies", new Object[] {}, Integer.class);
	}
	public Copies readOne(int bookId, int branchId) throws SQLException
	{
		List<Copies> list = (List<Copies>) template.query("select * from tbl_book_copies bookId = ? AND branchId = ?", new Object[] { bookId, branchId },this);
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}

	}

	public List<Copies> readAll(int pageNo, int pageSize) throws SQLException
	{
		String sql = "SELECT * FROM library.tbl_book_copies";
		return (List<Copies>) template.query(sql, new Object[] {}, this);
	}

	public List<Copies> readByBookTitle(String title) throws SQLException
	{
		String sql ="SELECT title,bk.bookId,br.branchId, noOfCopies FROM tbl_book bk INNER JOIN tbl_book_copies cp ON bk.bookId = cp.bookId INNER JOIN tbl_library_branch br ON cp.branchId = br.branchId AND title =?";
		return (List<Copies>) template.query(sql, new Object[] { title }, this);
	}
	
	public List<Copies> readByBook(int bookId) throws SQLException
	{
		String sql ="SELECT * FROM tbl_book where bookId =?";
		return (List<Copies>) template.query(sql, new Object[] { bookId }, this);
	}
	public List<Copies> readByBranch(int branchId) throws SQLException
	{
		return (List<Copies>) template.query("select * from tbl_book_copies where branchId = ?", new Object[] { branchId }, this);
	}

	@Override
	public List<Copies> extractData(ResultSet rs) throws SQLException
	{
		List<Copies> aList = new ArrayList<Copies>();
		while (rs.next())
		{
			Copies a = new Copies();
			a.setBook(bookDao.readOne(rs.getInt("bookId")));
			a.setBranch(branchDao.readOne(rs.getInt("branchId")));
			a.setNoOfCopies(rs.getInt("noOfCopies"));
			aList.add(a);
		}

		return aList;
	}
}
