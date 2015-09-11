package com.xc.financial.mapper;

import java.math.BigDecimal;
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

import com.xc.financial.beans.InstockBean;
import com.xc.financial.beans.InstockSearchBean;
import com.xc.financial.enums.column.InstockColumnEnum;
import com.xc.financial.utils.CollectionUtils;
import com.xc.financial.utils.DateUtils;
import com.xc.financial.utils.StringUtils;

public class InstockMapper {
	private Connection connect;
	private Statement statement;
	private ResultSet result;
	private static final String url="jdbc:mysql://localhost:3306/financial?useUnicode=true&characterEncoding=UTF-8";
	private static final String username="root";
	private static final String password="";
	private List<InstockBean> instocks = new ArrayList<InstockBean>();
	
	/**
	 * <p>
	 * 插入数据
	 * </p>
	 * 
	 * @param data
	 * @return
	 */
	public int insertInstock(Map<String,Object> data){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("insert into instock(code,type,member,amount,store_type,create_date,modify_date,operate) values(");
			if(StringUtils.isEmpty((String)data.get(InstockColumnEnum.getInstockColumnValueByKey("code").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(InstockColumnEnum.getInstockColumnValueByKey("code").getValue())+"',");
			}
			
			if(StringUtils.isEmpty(data.get(InstockColumnEnum.getInstockColumnValueByKey("type").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(InstockColumnEnum.getInstockColumnValueByKey("type").getValue())+"',");
			}
			
			if(StringUtils.isEmpty(data.get(InstockColumnEnum.getInstockColumnValueByKey("member").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(InstockColumnEnum.getInstockColumnValueByKey("member").getValue())+"',");
			}
			
			if(StringUtils.isEmpty(data.get(InstockColumnEnum.getInstockColumnValueByKey("amount").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(InstockColumnEnum.getInstockColumnValueByKey("amount").getValue())+"',");
			}
			
			if(StringUtils.isEmpty(data.get(InstockColumnEnum.getInstockColumnValueByKey("store_type").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(InstockColumnEnum.getInstockColumnValueByKey("store_type").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(InstockColumnEnum.getInstockColumnValueByKey("create_date").getValue()))){
				sb.append("'"+ DateUtils.parseLongDate(new Date()) +"',");
			}else{
				sb.append("'"+ data.get(InstockColumnEnum.getInstockColumnValueByKey("creat_date").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(InstockColumnEnum.getInstockColumnValueByKey("modify_date").getValue()))){
				sb.append("'"+ DateUtils.parseLongDate(new Date()) +"',");
			}else{
				sb.append("'"+ data.get(InstockColumnEnum.getInstockColumnValueByKey("modify_date").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(InstockColumnEnum.getInstockColumnValueByKey("operate").getValue()))){
				sb.append("null)");
			}else{
				sb.append("'"+ data.get(InstockColumnEnum.getInstockColumnValueByKey("operate").getValue())+"')");
			}
			StringBuffer sb1 = new StringBuffer();
			sb1.append("update financial set amount = amount + " + data.get(InstockColumnEnum.getInstockColumnValueByKey("amount").getValue()));
			sb1.append(" where type = " + data.get(InstockColumnEnum.getInstockColumnValueByKey("store_type").getValue()));
			sb1.append(" and member = " + data.get(InstockColumnEnum.getInstockColumnValueByKey("member").getValue()));
			
			connect = DriverManager.getConnection(url, username, password);
			connect.setAutoCommit(false);
			statement = connect.createStatement();
			statement.addBatch(sb.toString());
			statement.addBatch(sb1.toString());
			statement.executeBatch();
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
	public int updateInstock(Map<String,Object> data){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("update instock set ");
			if(StringUtils.isEmpty(data.get(InstockColumnEnum.getInstockColumnValueByKey("type").getValue()))){
				sb.append("type = null,");
			}else{
				sb.append("type = '"+ data.get(InstockColumnEnum.getInstockColumnValueByKey("type").getValue())+"',");
			}
			
			if(StringUtils.isEmpty(data.get(InstockColumnEnum.getInstockColumnValueByKey("member").getValue()))){
				sb.append("member = null,");
			}else{
				sb.append("member = '"+ data.get(InstockColumnEnum.getInstockColumnValueByKey("member").getValue())+"',");
			}
			
			if(StringUtils.isEmpty(data.get(InstockColumnEnum.getInstockColumnValueByKey("amount").getValue()))){
				sb.append("amount = null,");
			}else{
				sb.append("amount = '"+ data.get(InstockColumnEnum.getInstockColumnValueByKey("amount").getValue()) +"',");
			}
			
			if(StringUtils.isEmpty(data.get(InstockColumnEnum.getInstockColumnValueByKey("store_type").getValue()))){
				sb.append("store_type = null,");
			}else{
				sb.append("store_type = '"+ data.get(InstockColumnEnum.getInstockColumnValueByKey("store_type").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(InstockColumnEnum.getInstockColumnValueByKey("modify_date").getValue()))){
				sb.append("modify_date = '"+ DateUtils.parseLongDate(new Date()) +"',");
			}else{
				sb.append("modify_date = '"+ DateUtils.parseLongDate(new Date()) +"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(InstockColumnEnum.getInstockColumnValueByKey("operate").getValue()))){
				sb.append("operate = null");
			}else{
				sb.append("operate = " + "'"+ data.get(InstockColumnEnum.getInstockColumnValueByKey("operate").getValue()) +"'");
			}
			sb.append(" where code = '" + data.get(InstockColumnEnum.getInstockColumnValueByKey("code").getValue()) + "'");
			
			BigDecimal o_amount = BigDecimal.ZERO;
			if(CollectionUtils.isNotEmpty(instocks)){
				for(InstockBean instockBean:instocks){
					if(data.get(InstockColumnEnum.getInstockColumnValueByKey("code").getValue()).equals(instockBean.getCode())){
						o_amount = instockBean.getAmount();
						break;
					}
				}
			}
			StringBuffer sb1 = new StringBuffer();
			sb1.append("update financial set amount = amount + " + ((BigDecimal)data.get(InstockColumnEnum.getInstockColumnValueByKey("amount").getValue())).subtract(o_amount));
			sb1.append(" where type = " + data.get(InstockColumnEnum.getInstockColumnValueByKey("store_type").getValue()));
			sb1.append(" and member = " + data.get(InstockColumnEnum.getInstockColumnValueByKey("member").getValue()));
			
			connect = DriverManager.getConnection(url, username, password);
			connect.setAutoCommit(false);
			statement = connect.createStatement();
			statement.addBatch(sb.toString());
			statement.addBatch(sb1.toString());
			statement.executeBatch();
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
	public int deleteInstockByCode(Map<String,Object> data){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("delete from instock where code =  ");
			sb.append("'"+ data.get(InstockColumnEnum.getInstockColumnValueByKey("code").getValue())+"'");
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
	public List<InstockBean> selectInstocksByParams(InstockSearchBean searchBean){
		List<InstockBean> instockList = new ArrayList<InstockBean>();
		instocks = new ArrayList<InstockBean>();
		try{
			StringBuffer sb = new StringBuffer();	
			sb.append("select i.*,c.value as typeValue,s.value as storeTypeValue,u.username as memberValue from instock i left join code_dict c on i.type = c.id left join code_dict s on i.store_type = s.id left join user u on i.member = u.id where 1=1");
			if(StringUtils.isNotEmpty(searchBean.getCode())){
				sb.append(" and i.code like '%" + searchBean.getCode() +"%'");
			}
			if(StringUtils.isNotEmpty(searchBean.getStartDate())){
				sb.append(" and i.create_Date >= '" + DateUtils.dayBegin(DateUtils.parseSingleDate(searchBean.getStartDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getEndDate())){
				sb.append(" and i.create_Date <= '" + DateUtils.dayEnd(DateUtils.parseSingleDate(searchBean.getEndDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getType())){
				sb.append(" and i.type = " + Integer.parseInt(searchBean.getType()));
			}
			if(null != searchBean.getStoreType()){
				sb.append(" and i.store_type = " + searchBean.getStoreType());
			}
			sb.append(" order by create_date desc");
			sb.append(" limit " + searchBean.getOffset() + "," + searchBean.getRows());
			
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			result = statement.executeQuery(sb.toString());
			Integer index = 1;
			while(result.next()){
				InstockBean instockBean = new InstockBean();
				instockBean.setIndex(index++);
				instockBean.setCode(result.getString("code"));
				instockBean.setMember(result.getString("memberValue"));
				instockBean.setCreateDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("create_date"))));
				instockBean.setModifyDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("modify_date"))));
				instockBean.setAmount(BigDecimal.valueOf(Double.valueOf(result.getString("amount"))));
				instockBean.setTypeValue(result.getString("typeValue"));
				instockBean.setStoreTypeValue(result.getString("storeTypeValue"));
				instockBean.setOperate(result.getString("operate"));
				instockList.add(instockBean);
				instocks.add(instockBean);
			}
			return instockList;
		}catch(SQLException|ParseException e){
			e.printStackTrace();
			return instockList;
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
	public Integer getCount(InstockSearchBean searchBean){
		try{
			StringBuffer sb = new StringBuffer();	
			sb.append("select count(*) as count from instock i where 1=1 ");
			
			if(StringUtils.isNotEmpty(searchBean.getCode())){
				sb.append(" and i.code like '%" + searchBean.getCode() +"%'");
			}
			if(StringUtils.isNotEmpty(searchBean.getStartDate())){
				sb.append(" and i.create_Date >= '" + DateUtils.dayBegin(DateUtils.parseSingleDate(searchBean.getStartDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getEndDate())){
				sb.append(" and i.create_Date <= '" + DateUtils.dayEnd(DateUtils.parseSingleDate(searchBean.getEndDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getType())){
				sb.append(" and i.type = " + Integer.parseInt(searchBean.getType()));
			}
			if(null != searchBean.getStoreType()){
				sb.append(" and i.store_type = " + searchBean.getStoreType());
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
	 */
	public int selectMaxId(){
		try{
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			result = statement.executeQuery("select max(id) as id from instock");
			int index = -1;
			while(result.next()){
				index = Integer.parseInt(result.getString("id"));
			}
			return index;
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
