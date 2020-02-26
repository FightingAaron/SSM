package com.ssm.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

//数据库操作工具类
public class DbUtil {
	
	private static Logger logger=Logger.getLogger(DbUtil.class);
	//连接
	public static Connection con;
	
	//获取连接
	public static Connection getCon(){
		DataSource db=(DataSource)SpringUtil.getBean("dataSource");
		try {
			con=db.getConnection();
		} catch (SQLException e) {
			logger.info("获取数据库连接失败："+e.getMessage());
			e.printStackTrace();
		}
		return con;
	}
	
	
	//关闭连接
	public static void closeCon(Connection con){
		try {
			if(con!=null){
				con.commit();
				con.close();
			}
		} catch (SQLException e) {
			logger.info("关闭连接失败："+e.getMessage());
			e.printStackTrace();
		}
	}
}
