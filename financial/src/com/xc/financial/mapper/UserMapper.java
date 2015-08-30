package com.xc.financial.mapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xc.financial.beans.SearchBean;
import com.xc.financial.beans.UserBean;
import com.xc.financial.enums.UserColumnEnum;
import com.xc.financial.utils.DateUtils;
import com.xc.financial.utils.StringUtils;

public class UserMapper {
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
	public int insertOutstock(Map<String,Object> data){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("insert into user(name,status,sex,code,address,username,password,email,create_date,modify_date,comments,operate) values(");
			if(StringUtils.isEmpty((String)data.get(UserColumnEnum.getUserColumnValueByKey("code").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(UserColumnEnum.getUserColumnValueByKey("code").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(UserColumnEnum.getUserColumnValueByKey("type").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(UserColumnEnum.getUserColumnValueByKey("type").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(UserColumnEnum.getUserColumnValueByKey("member").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(UserColumnEnum.getUserColumnValueByKey("member").getValue())+"',");
			}
			
			if(StringUtils.isEmpty(data.get(UserColumnEnum.getUserColumnValueByKey("amount").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(UserColumnEnum.getUserColumnValueByKey("amount").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(UserColumnEnum.getUserColumnValueByKey("create_date").getValue()))){
				sb.append("'"+ DateUtils.parseLongDate(new Date()) +"',");
			}else{
				sb.append("'"+ data.get(UserColumnEnum.getUserColumnValueByKey("creat_date").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(UserColumnEnum.getUserColumnValueByKey("modify_date").getValue()))){
				sb.append("'"+ DateUtils.parseLongDate(new Date()) +"',");
			}else{
				sb.append("'"+ data.get(UserColumnEnum.getUserColumnValueByKey("modify_date").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(UserColumnEnum.getUserColumnValueByKey("comments").getValue()))){
				sb.append("null)");
			}else{
				sb.append("'"+ data.get(UserColumnEnum.getUserColumnValueByKey("comments").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(UserColumnEnum.getUserColumnValueByKey("operate").getValue()))){
				sb.append("null)");
			}else{
				sb.append("'"+ data.get(UserColumnEnum.getUserColumnValueByKey("operate").getValue())+"')");
			}
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			return statement.executeUpdate(sb.toString());
		}catch(SQLException|ParseException e){
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
	public int updateUser(Map<String,Object> data){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("update user set ");
			if(StringUtils.isEmpty((String)data.get(UserColumnEnum.getUserColumnValueByKey("type").getValue()))){
				sb.append("type = null,");
			}else{
				sb.append("type = '"+ data.get(UserColumnEnum.getUserColumnValueByKey("type").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(UserColumnEnum.getUserColumnValueByKey("member").getValue()))){
				sb.append("member = null,");
			}else{
				sb.append("member = '"+ data.get(UserColumnEnum.getUserColumnValueByKey("member").getValue())+"',");
			}
			
			if(StringUtils.isEmpty(data.get(UserColumnEnum.getUserColumnValueByKey("amount").getValue()))){
				sb.append("amount = null,");
			}else{
				sb.append("amount = '"+ data.get(UserColumnEnum.getUserColumnValueByKey("amount").getValue()) +"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(UserColumnEnum.getUserColumnValueByKey("modify_date").getValue()))){
				sb.append("modify_date = '"+ DateUtils.parseLongDate(new Date()) +"',");
			}else{
				sb.append("modify_date = '"+ data.get(UserColumnEnum.getUserColumnValueByKey("modify_date").getValue()) +"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(UserColumnEnum.getUserColumnValueByKey("comments").getValue()))){
				sb.append("comments = null");
			}else{
				sb.append("comments = " + "'"+ data.get(UserColumnEnum.getUserColumnValueByKey("comments").getValue()) +"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(UserColumnEnum.getUserColumnValueByKey("operate").getValue()))){
				sb.append("operate = null");
			}else{
				sb.append("operate = " + "'"+ data.get(UserColumnEnum.getUserColumnValueByKey("operate").getValue()) +"'");
			}
			
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			return statement.executeUpdate(sb.toString());
		}catch(SQLException|ParseException e){
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
	public int deleteUserByCode(Map<String,Object> data){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("delete from user where code =  ");
			sb.append("'"+ data.get(UserColumnEnum.getUserColumnValueByKey("code").getValue())+"'");
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
	public List<UserBean> selectUsersByParams(SearchBean searchBean){
		List<UserBean> userList = new ArrayList<UserBean>();
		try{
			StringBuffer sb = new StringBuffer();	
			sb.append("select * from user where 1=1");
			if(StringUtils.isNotEmpty(searchBean.getCode())){
				sb.append(" and code like '%" + searchBean.getCode() +"%'");
			}
			if(StringUtils.isNotEmpty(searchBean.getStartDate())){
				sb.append(" and create_Date >= '" + DateUtils.dayBegin(DateUtils.parseSingleDate(searchBean.getStartDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getEndDate())){
				sb.append(" and create_Date <= '" + DateUtils.dayEnd(DateUtils.parseSingleDate(searchBean.getEndDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getType())){
				sb.append(" and type = '" + searchBean.getType() + "'");
			}
			
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			result = statement.executeQuery(sb.toString());
			Integer index = 1;
			while(result.next()){
				UserBean userBean = new UserBean();
				userBean.setIndex(index++);
				userBean.setCode(result.getString("code"));
				userBean.setName(result.getString("name"));
				userBean.setStatusValue("");
				userBean.setSexValue("");
				userBean.setAddress(result.getString("address"));
				userBean.setUsername(result.getString("username"));
				userBean.setEmail(result.getString("email"));
				userBean.setCreateDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("create_date"))));
				userBean.setModifyDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("modify_date"))));
				userBean.setComments(result.getString("comments"));
				userBean.setOperate(result.getString("operate"));
				userList.add(userBean);
			}
			return userList;
		}catch(SQLException|ParseException e){
			e.printStackTrace();
			return userList;
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
	/**
	 * <p>
	 * 查询最大的id
	 * </p>
	 * 
	 * @param data
	 * @return
	 * @throws Exception 
	 */
	public int selectMaxId() throws Exception{
		try{
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			result = statement.executeQuery("select max(id) as id from outstock");
			int index = -1;
			while(result.next()){
				index = Integer.parseInt(result.getString("id"));
			}
			return index;
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
