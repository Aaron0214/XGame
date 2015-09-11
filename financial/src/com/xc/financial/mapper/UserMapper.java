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
import com.xc.financial.enums.SexEnum;
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
			sb.append("insert into user(name,status,sex,code,username,password,email,phone,birth,create_date,modify_date,comments,operate,address_id) values(");
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
			
			if(StringUtils.isEmpty(userBean.getBirth())){
				sb.append("null,");
			}else{
				sb.append("'"+ userBean.getBirth() +"',");
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
	public int updateUser(UserBean userBean){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("update user set ");
			if(StringUtils.isEmpty(userBean.getName())){
				sb.append("name = null,");
			}else{
				sb.append("name = '"+ userBean.getName() +"',");
			}
			
			if(StringUtils.isEmpty(userBean.getStatus())){
				sb.append("status = null,");
			}else{
				sb.append("status = '"+ userBean.getStatus() +"',");
			}
			
			if(null == userBean.getSex()){
				sb.append("sex = null,");
			}else{
				sb.append("sex = '"+ userBean.getSex() +"',");
			}
			
			if(StringUtils.isEmpty(userBean.getUsername())){
				sb.append("username = null,");
			}else{
				sb.append("username = '"+ userBean.getUsername() +"',");
			}
			
			if(StringUtils.isEmpty(userBean.getEmail())){
				sb.append("email = null,");
			}else{
				sb.append("email = '"+ userBean.getEmail() +"',");
			}
			
			if(StringUtils.isEmpty(userBean.getPhone())){
				sb.append("phone = null,");
			}else{
				sb.append("phone = '"+ userBean.getPhone() +"',");
			}
			
			if(StringUtils.isEmpty(userBean.getBirth())){
				sb.append("birth = null,");
			}else{
				sb.append("birth = '"+ userBean.getBirth() +"',");
			}
			
			if(StringUtils.isEmpty(userBean.getModifyDate())){
				sb.append("modify_date = '"+ DateUtils.parseLongDate(new Date()) +"',");
			}else{
				sb.append("modify_date = '"+ userBean.getModifyDate() +"',");
			}
			
			if(StringUtils.isEmpty(userBean.getComments())){
				sb.append("comments = null,");
			}else{
				sb.append("comments = '"+ userBean.getComments() +"',");
			}
			
			if(StringUtils.isEmpty(userBean.getOperate())){
				sb.append("operate = null");
			}else{
				sb.append("operate = '"+ userBean.getOperate() +"'");
			}
			sb.append(" where code = '" + userBean.getCode() +"'");
			
			StringBuffer sb1 = new StringBuffer();
			sb1.append("update address set ");
			AddressBean addressBean = userBean.getAddressBean();
			
			if(StringUtils.isNotEmpty(addressBean.getCountryCode())){
				sb1.append("country_code = '"+ addressBean.getCountryCode() +"',");
			}else{
				sb1.append("country_code = null,");
			}
			
			if(StringUtils.isNotEmpty(addressBean.getProvinceCode())){
				sb1.append("province_code = '"+ addressBean.getProvinceCode() +"',");
			}else{
				sb1.append("province_code = null,");
			}
			
			if(StringUtils.isNotEmpty(addressBean.getCityCode())){
				sb1.append("city_code = '"+ addressBean.getCityCode() +"',");
			}else{
				sb1.append("city_code = null,");
			}
			
			if(StringUtils.isNotEmpty(addressBean.getTownCode())){
				sb1.append("town_code = '"+ addressBean.getTownCode() +"',");
			}else{
				sb1.append("town_code = null,");
			}
			
			if(StringUtils.isNotEmpty(addressBean.getAddr())){
				sb1.append("addr = '"+ addressBean.getAddr() +"',");
			}else{
				sb1.append("addr = null,");
			}
			
			if(StringUtils.isNotEmpty(addressBean.getHouseCode())){
				sb1.append("house_code = '"+ addressBean.getHouseCode() +"',");
			}else{
				sb1.append("house_code = null,");
			}
			
			if(StringUtils.isNotEmpty(addressBean.getZipCode())){
				sb1.append("zip_code = '"+ addressBean.getZipCode() +"'");
			}else{
				sb1.append("zip_code = null");
			}
			sb1.append(" where id = " + addressBean.getId());
			
			connect = DriverManager.getConnection(url, username, password);
			connect.setAutoCommit(false);
			statement = connect.createStatement();
			statement.executeUpdate(sb1.toString());
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
			sb.append("select u.*,a.addr,a.house_code,province.value as provinceValue,city.value as cityValue,town.value as townValue from user u ");
			sb.append("left join address a on u.address_id = a.id ");
			sb.append("left join code_dict province on a.province_code = province.code ");
			sb.append("left join code_dict city on a.city_code = city.code ");
			sb.append("left join code_dict town on a.town_code = town.code where 1=1");
			
			if(StringUtils.isNotEmpty(searchBean.getName())){
				sb.append(" and u.name like '%" + searchBean.getName() +"%'");
			}
			
			if(StringUtils.isNotEmpty(searchBean.getUsername())){
				sb.append(" and u.username like '%" + searchBean.getUsername() +"%'");
			}
			
			if(StringUtils.isNotEmpty(searchBean.getCode())){
				sb.append(" and u.code like '%" + searchBean.getCode() +"%'");
			}
			if(StringUtils.isNotEmpty(searchBean.getStartDate())){
				sb.append(" and u.create_Date >= '" + DateUtils.dayBegin(DateUtils.parseSingleDate(searchBean.getStartDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getEndDate())){
				sb.append(" and u.create_Date <= '" + DateUtils.dayEnd(DateUtils.parseSingleDate(searchBean.getEndDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getStatus())){
				sb.append(" and u.status = '" + searchBean.getStatus() + "'");
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
				userBean.setStatusValue(StatusEnum.getStatusValueByKey(result.getString("status")).getValue());
				userBean.setSexValue(SexEnum.getSexValueByKey(Integer.parseInt(result.getString("sex"))).getValue());
				userBean.setBirth(result.getString("birth"));
				userBean.setUsername(result.getString("username"));
				userBean.setEmail(result.getString("email"));
				userBean.setPhone(result.getString("phone"));
				userBean.setCreateDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("create_date"))));
				userBean.setModifyDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("modify_date"))));
				userBean.setComments(result.getString("comments"));
				userBean.setOperate(result.getString("operate"));
				//构建地址信息
				String address = "";
				if(StringUtils.isNotEmpty(result.getString("provinceValue"))){
					address += result.getString("provinceValue");
				}
				if(StringUtils.isNotEmpty(result.getString("cityValue"))){
					address += " - " +result.getString("cityValue");
				}
				if(StringUtils.isNotEmpty(result.getString("townValue"))){
					address += " - " +result.getString("townValue");
				}
				if(StringUtils.isNotEmpty(result.getString("addr"))){
					address += " - " +result.getString("addr");
				}
				if(StringUtils.isNotEmpty(result.getString("house_code"))){
					address += " - " +result.getString("house_code");
				}
				userBean.setAddress(address);
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
	 * 查询数据数量
	 * </p>
	 * 
	 * @param data
	 * @return
	 */
	public Integer getCount(UserSearchBean searchBean){
		try{
			StringBuffer sb = new StringBuffer();	
			sb.append("select count(*) as count from user where 1=1 ");
			
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
			int count = 0;
			while(result.next()){
				count = Integer.parseInt(result.getString("count"));
			}
			return count;
		}catch(SQLException e){
			e.printStackTrace();
			return -1;
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
			sb.append(" and code = '" + userCode +"'");
			
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
				userBean.setBirth(StringUtils.isEmpty(result.getString("birth")) ? null :DateUtils.parseDate(DateUtils.parseSingleDate(result.getString("birth"))));
				userBean.setCreateDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("create_date"))));
				userBean.setModifyDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("modify_date"))));
				userBean.setComments(result.getString("comments"));
				userBean.setOperate(result.getString("operate"));
				
				AddressBean addressBean = new AddressBean();
				addressBean.setId(StringUtils.isEmpty(result.getString("address_id"))? null : Integer.parseInt(result.getString("address_id")));
				addressBean.setCountryCode(result.getString("country_code"));
				addressBean.setProvinceCode(result.getString("province_code"));
				addressBean.setCityCode(result.getString("city_code"));
				addressBean.setTownCode(result.getString("town_code"));
				addressBean.setHouseCode(result.getString("house_code"));
				addressBean.setAddr(result.getString("addr"));
				addressBean.setZipCode(result.getString("zip_code"));
				userBean.setAddressBean(addressBean);
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
	
	public List<UserBean> selectUserList(){
		List<UserBean> userBeanList = new ArrayList<UserBean>();
		try{
			StringBuffer sb = new StringBuffer();	
			sb.append("select * from user where status = 'Y' ");
			
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			result = statement.executeQuery(sb.toString());
			while(result.next()){
				UserBean userBean = new UserBean();
				userBean.setId(Integer.parseInt(result.getString("id")));
				userBean.setUsername(result.getString("username"));
				userBean.setName(result.getString("name"));
				userBeanList.add(userBean);
			}
			return userBeanList;
		}catch(SQLException e){
			e.printStackTrace();
			return userBeanList;
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
