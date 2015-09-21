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

import com.xc.financial.beans.RoleBean;
import com.xc.financial.beans.RoleOperateBean;
import com.xc.financial.beans.SearchBean;
import com.xc.financial.enums.StatusEnum;
import com.xc.financial.enums.column.RoleColumnEnum;
import com.xc.financial.utils.DateUtils;
import com.xc.financial.utils.StringUtils;

public class RoleMapper {
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
	public int insertRole(RoleBean roleBean){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("insert into role(status,name,create_date,modify_date,comments,operate) values(");
			
			if(StringUtils.isEmpty(roleBean.getStatus())){
				sb.append("null,");
			}else{
				sb.append("'"+ roleBean.getStatus()+"',");
			}
			
			if(StringUtils.isEmpty(roleBean.getName())){
				sb.append("null,");
			}else{
				sb.append("'"+ roleBean.getName() +"',");
			}
			
			if(StringUtils.isEmpty(roleBean.getCreateDate())){
				sb.append("'"+ DateUtils.parseLongDate(new Date()) +"',");
			}else{
				sb.append("'"+ roleBean.getCreateDate() +"',");
			}
			
			if(StringUtils.isEmpty(roleBean.getModifyDate())){
				sb.append("'"+ DateUtils.parseLongDate(new Date()) +"',");
			}else{
				sb.append("'"+ roleBean.getModifyDate() +"',");
			}
			
			if(StringUtils.isEmpty(roleBean.getComments())){
				sb.append("null,");
			}else{
				sb.append("'"+ roleBean.getComments() +"',");
			}
			
			if(StringUtils.isEmpty(roleBean.getOperate())){
				sb.append("null)");
			}else{
				sb.append("'"+ roleBean.getOperate() +"')");
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
	public int updateRole(RoleBean roleBean){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("update role set ");
			if(StringUtils.isEmpty(roleBean.getStatus())){
				sb.append("status = null,");
			}else{
				sb.append("status = '"+ roleBean.getStatus() +"',");
			}
			
			if(StringUtils.isEmpty(roleBean.getName())){
				sb.append("name = null,");
			}else{
				sb.append("name = '"+ roleBean.getName() +"',");
			}
			
			if(StringUtils.isEmpty(roleBean.getModifyDate())){
				sb.append("modify_date = '"+ DateUtils.parseLongDate(new Date()) +"',");
			}else{
				sb.append("modify_date = '"+ roleBean.getModifyDate() +"',");
			}
			
			if(StringUtils.isEmpty(roleBean.getComments())){
				sb.append("comments = null,");
			}else{
				sb.append("comments = " + "'"+ roleBean.getComments() +"',");
			}
			
			if(StringUtils.isEmpty(roleBean.getOperate())){
				sb.append("operate = null");
			}else{
				sb.append("operate = " + "'"+ roleBean.getOperate() +"'");
			}
			sb.append(" where id = '"+ roleBean.getId()+"'");
			
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
	public int deleteRoleById(Map<String,Object> data){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("delete from financial where id = ");
			sb.append(data.get(RoleColumnEnum.getRoleColumnValueByKey("id").getValue()));
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
	public List<RoleOperateBean> selectRolesByParams(SearchBean searchBean){
		List<RoleOperateBean> roleList = new ArrayList<RoleOperateBean>();
		try{
			StringBuffer sb = new StringBuffer();	
			sb.append("select * from role where 1=1");

			if(null != searchBean.getId()){
				sb.append(" and id = " + searchBean.getId());
			}
			
			if(StringUtils.isNotEmpty(searchBean.getStartDate())){
				sb.append(" and create_Date >= '" + DateUtils.dayBegin(DateUtils.parseSingleDate(searchBean.getStartDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getEndDate())){
				sb.append(" and create_Date <= '" + DateUtils.dayEnd(DateUtils.parseSingleDate(searchBean.getEndDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getStatus())){
				sb.append(" and status = '" + searchBean.getStatus() + "'") ;
			}
			sb.append(" limit " + searchBean.getOffset() + "," + searchBean.getRows());
			
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			result = statement.executeQuery(sb.toString());
			while(result.next()){
				RoleOperateBean roleBean = new RoleOperateBean();
				roleBean.setId(Integer.parseInt(result.getString("id")));
				roleBean.setName(result.getString("name"));
				roleBean.setStatusValue(StatusEnum.getStatusValueByKey(result.getString("status")).getValue());
				roleBean.setCreateDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("create_date"))));
				roleBean.setModifyDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("modify_date"))));
				roleBean.setComments(result.getString("comments"));
				roleBean.setOperate(result.getString("operate"));
				roleList.add(roleBean);
			}
			return roleList;
		}catch(SQLException|ParseException e){
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
	
	/**
	 * <p>
	 * 查询数据
	 * </p>
	 * 
	 * @param data
	 * @return
	 */
	public List<RoleBean> selectRolesBySearchBean(SearchBean searchBean){
		List<RoleBean> roleList = new ArrayList<RoleBean>();
		try{
			StringBuffer sb = new StringBuffer();	
			sb.append("select * from role where 1=1");

			if(null != searchBean.getId()){
				sb.append(" and id = " + searchBean.getId());
			}
			
			if(StringUtils.isNotEmpty(searchBean.getStartDate())){
				sb.append(" and create_Date >= '" + DateUtils.dayBegin(DateUtils.parseSingleDate(searchBean.getStartDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getEndDate())){
				sb.append(" and create_Date <= '" + DateUtils.dayEnd(DateUtils.parseSingleDate(searchBean.getEndDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getStatus())){
				sb.append(" and status = '" + searchBean.getStatus() + "'") ;
			}
			sb.append(" limit " + searchBean.getOffset() + "," + searchBean.getRows());
			
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			result = statement.executeQuery(sb.toString());
			while(result.next()){
				RoleBean roleBean = new RoleBean();
				roleBean.setId(Integer.parseInt(result.getString("id")));
				roleBean.setName(result.getString("name"));
				roleBean.setStatus(result.getString("status"));
				roleBean.setCreateDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("create_date"))));
				roleBean.setModifyDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("modify_date"))));
				roleBean.setComments(result.getString("comments"));
				roleBean.setOperate(result.getString("operate"));
				roleList.add(roleBean);
			}
			return roleList;
		}catch(SQLException|ParseException e){
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
	
	/**
	 * <p>
	 * 根据code查询数据
	 * </p>
	 * 
	 * @param data
	 * @return
	 */
	public RoleBean selectRolesById(Integer id){
		RoleBean roleBean = new RoleBean();
		try{
			StringBuffer sb = new StringBuffer();	
			sb.append("select * from role r left join role_authority ra on r.id = ra.role_id where 1=1");
			sb.append(" and r.id = " + id);
			
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			result = statement.executeQuery(sb.toString());
			List<String> authorities = new ArrayList<String>();
			while(result.next()){
				if(!result.getString("id").equals(roleBean.getId())){
					roleBean.setId(Integer.parseInt(result.getString("id")));
					roleBean.setStatus(result.getString("status"));
					roleBean.setName(result.getString("name"));
					roleBean.setCreateDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("create_date"))));
					roleBean.setModifyDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("modify_date"))));
					roleBean.setComments(result.getString("comments"));
					roleBean.setOperate(result.getString("operate"));
				}
				if(StringUtils.isNotEmpty(result.getString("authority"))){
					authorities.add(result.getString("authority"));
				}
			}
			roleBean.setAuthorities(authorities);
			return roleBean;
		}catch(SQLException|ParseException e){
			e.printStackTrace();
			return roleBean;
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
	public Integer getCount(SearchBean searchBean){
		try{
			StringBuffer sb = new StringBuffer();	
			sb.append("select count(*) as count from role where 1=1 ");
			
			if(StringUtils.isNotEmpty(searchBean.getStartDate())){
				sb.append(" and create_Date >= '" + DateUtils.dayBegin(DateUtils.parseSingleDate(searchBean.getStartDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getEndDate())){
				sb.append(" and create_Date <= '" + DateUtils.dayEnd(DateUtils.parseSingleDate(searchBean.getEndDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getType())){
				sb.append(" and status = '" + searchBean.getStatus()+ "'");
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
	
}
