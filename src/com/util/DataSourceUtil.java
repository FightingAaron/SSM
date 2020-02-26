package com.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;



public class DataSourceUtil {
	
	public static Connection con;
	
	public static Connection getCon(){
		DataSource dataSource=(DataSource)SpringUtil.getBean("dataSource");
		try {
			con=dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	//πÿ±’¡¨Ω”
	public static void closeCon(){
		if(con!=null){
			try {
				con.commit();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
