package com.xc.financial.mapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SnMapper {
	
	private Connection connect;
	private Statement statement;
	private ResultSet result;
	private static final String url="jdbc:mysql://localhost:3306/financial?useUnicode=true&characterEncoding=UTF-8";
	private static final String username="root";
	private static final String password="";
	
	public int selectSn(Integer type) throws Exception{
		try{
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			statement.executeUpdate("update sn set num = num + 1 where type = " + type);
			result = statement.executeQuery("select num from sn where type = " + type);
			int num = -1;
			while(result.next()){
				num = Integer.parseInt(result.getString("num"));
			}
			return num;
		}catch(SQLException e){
			throw new Exception();
		}finally{
			try {
				result.close();
				statement.close();
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
