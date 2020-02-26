package com.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class test1 {
	public static void main(String[] args) {
		
		DataSource dataSource=(DataSource)SpringUtil.getBean("dataSource");
		System.out.println("dataSource="+dataSource);
		Connection con=null;
		try {
			con=dataSource.getConnection();
			String executeSql="select * from task";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(executeSql);
			List<Map> list=new ArrayList<Map>();
			while(rs.next()){
				Map<String,String> map=new HashMap<String,String>();
				map.put("errorType",rs.getString("ID"));
				map.put("countUrl",rs.getString("NAME"));
				list.add(map);
			}
			System.out.println(list.toString());
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(con!=null){
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
				
			}


	}
}
