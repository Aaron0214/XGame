package com.xc.financial.mapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xc.financial.beans.UserRoleBean;
import com.xc.financial.utils.StringUtils;

public class UserRoleMapper {

	private Connection connect;
	private Statement statement;
	private ResultSet result;
	private static final String url="jdbc:mysql://localhost:3306/financial?useUnicode=true&characterEncoding=UTF-8";
	private static final String username="root";
	private static final String password="";
	
	/**
	 * <p>
	 * 插入数据
	 * </p>
	 * 
	 * @param data
	 * @return
	 */
	public int insertUserRole(UserRoleBean userRoleBean){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("insert into user_role(user_id,role_id) values(");
			
			if(null == userRoleBean.getUserId()){
				sb.append("null,");
			}else{
				sb.append("'"+ userRoleBean.getUserId()+"',");
			}
			
			if(StringUtils.isEmpty(userRoleBean.getRoleId())){
				sb.append("null)");
			}else{
				sb.append("'"+ userRoleBean.getRoleId() +"')");
			}
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			return statement.executeUpdate(sb.toString());
		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}finally{
			try {
				statement.close();
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * <p>
	 * 更新数据
	 * </p>
	 * 
	 * @param data
	 * @return
	 */
	public int deleteRoleByUserId(Integer userId){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("delete from user_role where user_id = ");
			sb.append(userId);
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			return statement.execute(sb.toString()) == true ? 1 : -1;
		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}finally{
			try {
				statement.close();
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * <p>
	 * 查询数据
	 * </p>
	 * 
	 * @param data
	 * @return
	 */
	public List<UserRoleBean> selectUserRolesByParams(Map<String,Integer> params){
		List<UserRoleBean> roleList = new ArrayList<UserRoleBean>();
		try{
			StringBuffer sb = new StringBuffer();	
			sb.append("select * from user_role where 1=1");

			if(null != params.get("userId")){
				sb.append(" and user_id = " + params.get("userId"));
			}
			
			if(null != params.get("roleId")){
				sb.append(" and role_id = " + params.get("roleId")) ;
			}
			
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			result = statement.executeQuery(sb.toString());
			while(result.next()){
				UserRoleBean userRoleBean = new UserRoleBean();
				userRoleBean.setUserId(Integer.parseInt(result.getString("user_id")));
				userRoleBean.setRoleId(Integer.parseInt(result.getString("role_id")));
				roleList.add(userRoleBean);
			}
			return roleList;
		}catch(SQLException e){
			e.printStackTrace();
			return roleList;
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
