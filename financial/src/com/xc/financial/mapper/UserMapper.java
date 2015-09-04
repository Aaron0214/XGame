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

import com.xc.financial.beans.AddressBean;
import com.xc.financial.beans.UserBean;
import com.xc.financial.beans.UserOperateBean;
import com.xc.financial.beans.UserSearchBean;
import com.xc.financial.enums.StatusEnum;
import com.xc.financial.enums.column.UserColumnEnum;
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
	public int insertUser(UserBean userBean){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("insert into user(name,status,sex,code,username,password,email,phone,create_date,modify_date,comments,operate,address_id) values(");
			if(StringUtils.isEmpty(userBean.getName())){
				sb.append("null,");
			}else{
				sb.append("'"+ userBean.getName() +"',");
			}
			
			if(StringUtils.isEmpty(userBean.getStatus())){
				sb.append("null,");
			}else{
				sb.append("'"+ userBean.getStatus() +"',");
			}
			
			if(null == userBean.getSex()){
				sb.append("null,");
			}else{
				sb.append("'"+ userBean.getSex() +"',");
			}
			
			if(StringUtils.isEmpty(userBean.getCode())){
				sb.append("null,");
			}else{
				sb.append("'"+ userBean.getCode() +"',");
			}
			
			if(StringUtils.isEmpty(userBean.getUsername())){
				sb.append("null,");
			}else{
				sb.append("'"+ userBean.getUsername() +"',");
			}
			
			if(StringUtils.isEmpty(userBean.getPassword())){
				sb.append("null,");
			}else{
				sb.append("'"+ userBean.getPassword() +"',");
			}
			
			if(StringUtils.isEmpty(userBean.getEmail())){
				sb.append("null,");
			}else{
				sb.append("'"+ userBean.getEmail() +"',");
			}
			
			if(StringUtils.isEmpty(userBean.getPhone())){
				sb.append("null,");
			}else{
				sb.append("'"+ userBean.getPhone() +"',");
			}
			
			if(StringUtils.isEmpty(userBean.getCreateDate())){
				sb.append("'"+ DateUtils.parseLongDate(new Date()) +"',");
			}else{
				sb.append("'"+ userBean.getCreateDate() +"',");
			}
			
			if(StringUtils.isEmpty(userBean.getModifyDate())){
				sb.append("'"+ DateUtils.parseLongDate(new Date()) +"',");
			}else{
				sb.append("'"+ userBean.getModifyDate() +"',");
			}
			
			if(StringUtils.isEmpty(userBean.getComments())){
				sb.append("null,");
			}else{
				sb.append("'"+ userBean.getComments() +"',");
			}
			
			if(StringUtils.isEmpty(userBean.getOperate())){
				sb.append("null,");
			}else{
				sb.append("'"+ userBean.getOperate() +"',");
			}
			
			StringBuffer sb1 = new StringBuffer();
			sb1.append("insert into address(country_code,province_code,city_code,town_code,addr,house_code,zip_code) values (");
			AddressBean addressBean = userBean.getAddressBean();
			if(StringUtils.isNotEmpty(addressBean.getCountryCode())){
				sb1.append("'"+ addressBean.getCountryCode() +"',");
			}else{
				sb1.append("null,");
			}
			
			if(StringUtils.isNotEmpty(addressBean.getProvinceCode())){
				sb1.append("'"+ addressBean.getProvinceCode() +"',");
			}else{
				sb1.append("null,");
			}
			
			if(StringUtils.isNotEmpty(addressBean.getCityCode())){
				sb1.append("'"+ addressBean.getCityCode() +"',");
			}else{
				sb1.append("null,");
			}
			
			if(StringUtils.isNotEmpty(addressBean.getTownCode())){
				sb1.append("'"+ addressBean.getTownCode() +"',");
			}else{
				sb1.append("null,");
			}
			
			if(StringUtils.isNotEmpty(addressBean.getAddr())){
				sb1.append("'"+ addressBean.getAddr() +"',");
			}else{
				sb1.append("null,");
			}
			
			if(StringUtils.isNotEmpty(addressBean.getHouseCode())){
				sb1.append("'"+ addressBean.getHouseCode() +"',");
			}else{
				sb1.append("null,");
			}
			
			if(StringUtils.isNotEmpty(addressBean.getZipCode())){
				sb1.append("'"+ addressBean.getZipCode() +"')");
			}else{
				sb1.append("null)");
			}
			
			connect = DriverManager.getConnection(url, username, password);
			connect.setAutoCommit(false);
			statement = connect.createStatement();
			statement.executeUpdate(sb1.toString());
			ResultSet rt = statement.executeQuery("select max(id) as address_id from address");
			while(rt.next()){
				userBean.setAddressId(StringUtils.isEmpty(rt.getString("address_id")) ? null : Integer.parseInt(rt.getString("address_id")));
			}
			
			if(null == userBean.getAddressId()){
				sb.append("null)");
			}else{
				sb.append("'"+ userBean.getAddressId() +"')");
			}
			
			statement.executeUpdate(sb.toString());
			connect.commit();
			return 1;
		}catch(SQLException|ParseException e){
			e.printStackTrace();
			try {
				connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
			if(StringUtils.isEmpty((String)data.get(UserColumnEnum.getUserColumnValueByKey("name").getValue()))){
				sb.append("type = null,");
			}else{
				sb.append("type = '"+ data.get(UserColumnEnum.getUserColumnValueByKey("name").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(UserColumnEnum.getUserColumnValueByKey("status").getValue()))){
				sb.append("member = null,");
			}else{
				sb.append("member = '"+ data.get(UserColumnEnum.getUserColumnValueByKey("status").getValue())+"',");
			}
			
			if(StringUtils.isEmpty(data.get(UserColumnEnum.getUserColumnValueByKey("sex").getValue()))){
				sb.append("amount = null,");
			}else{
				sb.append("amount = '"+ data.get(UserColumnEnum.getUserColumnValueByKey("sex").getValue()) +"',");
			}
			
			if(StringUtils.isEmpty(data.get(UserColumnEnum.getUserColumnValueByKey("address").getValue()))){
				sb.append("amount = null,");
			}else{
				sb.append("amount = '"+ data.get(UserColumnEnum.getUserColumnValueByKey("address").getValue()) +"',");
			}
			
			if(StringUtils.isEmpty(data.get(UserColumnEnum.getUserColumnValueByKey("username").getValue()))){
				sb.append("amount = null,");
			}else{
				sb.append("amount = '"+ data.get(UserColumnEnum.getUserColumnValueByKey("username").getValue()) +"',");
			}
			
			if(StringUtils.isEmpty(data.get(UserColumnEnum.getUserColumnValueByKey("password").getValue()))){
				sb.append("amount = null,");
			}else{
				sb.append("amount = '"+ data.get(UserColumnEnum.getUserColumnValueByKey("password").getValue()) +"',");
			}
			
			if(StringUtils.isEmpty(data.get(UserColumnEnum.getUserColumnValueByKey("email").getValue()))){
				sb.append("amount = null,");
			}else{
				sb.append("amount = '"+ data.get(UserColumnEnum.getUserColumnValueByKey("email").getValue()) +"',");
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
			sb.append(" where code = '" + data.get(UserColumnEnum.getUserColumnValueByKey("code").getValue()) +"'");
			
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
	public List<UserOperateBean> selectUsersByParams(UserSearchBean searchBean){
		List<UserOperateBean> userList = new ArrayList<UserOperateBean>();
		try{
			StringBuffer sb = new StringBuffer();	
			sb.append("select * from user where 1=1");
			
			if(StringUtils.isNotEmpty(searchBean.getName())){
				sb.append(" and name like '%" + searchBean.getName() +"%'");
			}
			
			if(StringUtils.isNotEmpty(searchBean.getUsername())){
				sb.append(" and username like '%" + searchBean.getUsername() +"%'");
			}
			
			if(StringUtils.isNotEmpty(searchBean.getCode())){
				sb.append(" and code like '%" + searchBean.getCode() +"%'");
			}
			if(StringUtils.isNotEmpty(searchBean.getStartDate())){
				sb.append(" and create_Date >= '" + DateUtils.dayBegin(DateUtils.parseSingleDate(searchBean.getStartDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getEndDate())){
				sb.append(" and create_Date <= '" + DateUtils.dayEnd(DateUtils.parseSingleDate(searchBean.getEndDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getStatus())){
				sb.append(" and status = '" + searchBean.getStatus() + "'");
			}
			
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			result = statement.executeQuery(sb.toString());
			Integer index = 1;
			while(result.next()){
				UserOperateBean userBean = new UserOperateBean();
				userBean.setIndex(index++);
				userBean.setCode(result.getString("code"));
				userBean.setName(result.getString("name"));
				userBean.setStatusValue(StatusEnum.getStatusValueByKey(result.getString("value")).getValue());
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
	
	/**
	 * <p>
	 * 根据code查询数据
	 * </p>
	 * 
	 * @param data
	 * @return
	 */
	public UserBean selectUsersByCode(String userCode){
		UserBean userBean = new UserBean();
		try{
			StringBuffer sb = new StringBuffer();	
			sb.append("select * from user u left join address adr on u.address_id = adr.id where 1=1");
			
			if(StringUtils.isNotEmpty(userCode)){
				sb.append(" and code = '" + userCode +"'");
			}
			
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			result = statement.executeQuery(sb.toString());
			while(result.next()){
				userBean.setId(Integer.parseInt(result.getString("id")));
				userBean.setName(result.getString("name"));
				userBean.setCode(result.getString("code"));
				userBean.setStatus(result.getString("status"));
				userBean.setSex(StringUtils.isEmpty(result.getString("sex"))? null : Integer.parseInt(result.getString("sex")));
				userBean.setAddressId(StringUtils.isEmpty(result.getString("address_id"))? null : Integer.parseInt(result.getString("address_id")));
				userBean.setUsername(result.getString("username"));
				userBean.setEmail(result.getString("email"));
				userBean.setPhone(result.getString("phone"));
				userBean.setCreateDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("create_date"))));
				userBean.setModifyDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("modify_date"))));
				userBean.setComments(result.getString("comments"));
				userBean.setOperate(result.getString("operate"));
				
				AddressBean addressBean = new AddressBean();
			}
			return userBean;
		}catch(SQLException|ParseException e){
			e.printStackTrace();
			return userBean;
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
