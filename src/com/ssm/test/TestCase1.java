package com.ssm.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ssm.util.DbUtil;

public class TestCase1 {
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		try {
			String sql="select * from user";
			con=DbUtil.getCon();
			st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if(rs.next()){
				System.out.println(rs.getString("userName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
