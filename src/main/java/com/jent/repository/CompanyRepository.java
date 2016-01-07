package com.jent.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jent.bean.Company;
import com.jent.common.DataBaseHelper;

public class CompanyRepository {
	private Logger log = LoggerFactory.getLogger(CompanyRepository.class);
	
	public Company getCompanyByCode(String code){
		log.info("get Company by Code.");
		Company c = new Company();
		Connection conn = DataBaseHelper.getDBConnection();
		if(conn == null){
			return c;
		}
		
		String sql = "select * from company where company_code = ?";
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			rs = ps.executeQuery();
			while(rs.next()){
				c.setCompany_id(rs.getLong("company_id"));
				c.setCompany_code(rs.getString("company_code"));
				c.setCompany_name(rs.getString("company_name"));
				c.setCompany_address(rs.getString("company_address"));
				c.setCompany_province(rs.getString("company_province"));
			}
		} catch (SQLException e) {
			log.error("get company by code error : " + e.getMessage());
		}
		DataBaseHelper.cleanResultSet(rs);
		DataBaseHelper.cleanConnection(conn);
		
		return c;
	}
	
	public List<Company> getCompanyList(){
		log.info("get Company list.");
		List<Company> list = new ArrayList<Company>();
		Connection conn = DataBaseHelper.getDBConnection();
		if(conn == null){
			return list;
		}
		
		String sql = "select * from company";
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Company c = new Company();
				c.setCompany_id(rs.getLong("company_id"));
				c.setCompany_code(rs.getString("company_code"));
				c.setCompany_name(rs.getString("company_name"));
				c.setCompany_address(rs.getString("company_address"));
				c.setCompany_province(rs.getString("company_province"));
				list.add(c);
			}
		} catch (SQLException e) {
			log.error("get company by code error : " + e.getMessage());
		}
		DataBaseHelper.cleanResultSet(rs);
		DataBaseHelper.cleanConnection(conn);
		
		return list;
	}
}
