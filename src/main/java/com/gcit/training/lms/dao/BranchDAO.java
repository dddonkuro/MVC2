package com.gcit.training.lms.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.lms.entity.Branch;


public class BranchDAO extends AbstractDAO implements ResultSetExtractor<List<Branch>>{

	@Autowired
	JdbcTemplate template;

	public void create(Branch a) throws SQLException {
		template.update("insert into tbl_library_branch (branchName, branchAddress) values (? , ?)",new Object[]{a.getBranchName(),a.getBranchAddress()});
		
	}

	public void update(Branch a) throws SQLException {
		template.update("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?",new Object[]{a.getBranchName(),a.getBranchAddress(),a.getBranchId()});
	}
	
	public void updateName(Branch a) throws SQLException {
		template.update("update tbl_library_branch set branchName = ? where branchId = ?",new Object[]{a.getBranchName(),a.getBranchId()});
	}
	
	public void updateAddress(Branch a) throws SQLException {
		template.update("update tbl_library_branch set  branchAddress = ? where branchId = ?",new Object[]{a.getBranchAddress(),a.getBranchId()});
	}
	public Integer branchCount()
	{
		return template.queryForObject("SELECT count(*) from tbl_library_branch", new Object[] {}, Integer.class);
	}

	public void delete(Branch a) throws SQLException {
		template.update("delete from tbl_library_branch where branchId = ?",new Object[]{a.getBranchId()});
	}

	public Branch readOne(int branchId) throws SQLException {

		List<Branch> list = (List<Branch>)template.query("select * from tbl_library_branch where branchId = ?",new Object[]{branchId},this);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public List<Branch> readAll(int pageNo, int pageSize) throws SQLException {
		if (pageNo == 0)
			pageNo++;
		Integer PageOffset = (pageNo - 1) * pageSize;
		return (List<Branch>)template.query("select * from tbl_library_branch  limit ? offset ?",new Object[]{pageSize,PageOffset},this);

	}
	
	public List<Branch> readByName(String searchString,int pageNo,int pageSize) throws SQLException {
		if (pageNo == 0)
			pageNo++;
		Integer PageOffset = (pageNo - 1) * pageSize;
		String qString = "%" + searchString + "%";
		return (List<Branch>)template.query("select * from tbl_library_branch where branchName like ? limit ? offset ?",new Object[]{qString,pageSize,PageOffset},this);

	}
	
	public List<Branch> readByAddress(String searchString) throws SQLException {
		String qString = "%" + searchString + "%";

		return (List<Branch>)template.query("select * from tbl_library_branch where branchAddress like ?",new Object[]{qString},this);

	}

	@Override
	public List<Branch> extractData(ResultSet rs) throws SQLException {
		List<Branch> aList = new ArrayList<Branch>();
		while(rs.next()) {
			Branch a = new Branch();
			a.setBranchId(rs.getInt("branchId"));
			a.setBranchName(rs.getString("branchName"));
			a.setBranchAddress(rs.getString("branchAddress"));
			aList.add(a);
		}

		return aList;
	}
	
}
