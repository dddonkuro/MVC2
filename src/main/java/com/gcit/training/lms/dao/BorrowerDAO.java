package com.gcit.training.lms.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.lms.entity.Borrower;

public class BorrowerDAO extends AbstractDAO implements ResultSetExtractor<List<Borrower>>{

	@Autowired
	JdbcTemplate template;

	
	public void create(Borrower a) throws SQLException {
		template.update("insert into tbl_borrower (name, address, phone) values (?, ?, ?)",new Object[]{a.getName(),a.getAddress(), a.getPhoneNo()});
		}

	public void update(Borrower a) throws SQLException {
		template.update("update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?", new Object[]{a.getName(), a.getAddress(), a.getPhoneNo(), a.getCardNo()});
	}

	public void updateName(Borrower a) throws SQLException {
		template.update("update tbl_borrower set name = ? where cardNo = ?", new Object[]{a.getName(), a.getCardNo()});
	}
	
	public void updateAddress(Borrower a) throws SQLException {
		template.update("update tbl_borrower set  address = ? where cardNo = ?", new Object[]{a.getAddress(), a.getCardNo()});
	}
	public Integer borrowerCount()
	{
		return template.queryForObject("SELECT count(*) from tbl_borrower", new Object[] {}, Integer.class);
	}
	public void updatePhoneNo(Borrower a) throws SQLException {
		template.update("update tbl_borrower set phone = ? where cardNo = ?", new Object[]{a.getPhoneNo(), a.getCardNo()});
	}
	
	public void delete(Borrower a) throws SQLException {
		template.update("delete from tbl_borrower where cardNo = ?",new Object[]{a.getCardNo()});
	}

	public Borrower readOne(int cardNo) throws SQLException {

		List<Borrower> list= (List<Borrower>) template.query("select * from tbl_borrower where cardNo = ?",new Object[]{cardNo},this);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}

	}

	public List<Borrower> readAll(int pageNo, int pageSize) throws SQLException {
		if (pageNo == 0)
			pageNo++;
		Integer pageOffset = (pageNo - 1) * pageSize;
		return (List<Borrower>)template.query("select * from tbl_borrower LIMIT ? OFFSET ?",new Object[]{pageSize,pageOffset},this);

	}
	
	public List<Borrower> readByName(String searchString,int pageSize) throws SQLException {

		String qString = "%" + searchString + "%";
		return (List<Borrower>)template.query("select * from tbl_borrower where name like ? LIMIT ?",new Object[]{qString,pageSize},this);
	}
	
	public List<Borrower> readByAddress(String searchString) throws SQLException {

		String qString = "%" + searchString + "%";
		return (List<Borrower>)template.query("select * from tbl_borrower address like ?",new Object[]{qString},this);
	}
	
	public List<Borrower> readByPhone(String searchString) throws SQLException {

		String qString = "%" + searchString + "%";
		return (List<Borrower>)template.query("select * from tbl_borrower phone like ?",new Object[]{qString},this);
	}

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> aList = new ArrayList<Borrower>();
		while(rs.next()) {
			Borrower a = new Borrower();
			a.setCardNo(rs.getInt("cardNo"));
			a.setName(rs.getString("name"));
			a.setAddress(rs.getString("address"));
			a.setPhoneNo(rs.getString("phone"));
			aList.add(a);
		}

		return aList;
	}
}
