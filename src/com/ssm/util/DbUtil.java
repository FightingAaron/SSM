package com.ssm.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

//���ݿ����������
public class DbUtil {
	
	private static Logger logger=Logger.getLogger(DbUtil.class);
	//����
	public static Connection con;
	
	//��ȡ����
	public static Connection getCon(){
		DataSource db=(DataSource)SpringUtil.getBean("dataSource");
		try {
			con=db.getConnection();
		} catch (SQLException e) {
			logger.info("��ȡ���ݿ�����ʧ�ܣ�"+e.getMessage());
			e.printStackTrace();
		}
		return con;
	}
	
	
	//�ر�����
	public static void closeCon(Connection con){
		try {
			if(con!=null){
				con.commit();
				con.close();
			}
		} catch (SQLException e) {
			logger.info("�ر�����ʧ�ܣ�"+e.getMessage());
			e.printStackTrace();
		}
	}
}
