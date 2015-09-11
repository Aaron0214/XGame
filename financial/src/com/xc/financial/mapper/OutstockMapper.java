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

import com.xc.financial.beans.OutstockBean;
import com.xc.financial.beans.OutstockSearchBean;
import com.xc.financial.enums.column.InstockColumnEnum;
import com.xc.financial.enums.column.OutstockColumnEnum;
import com.xc.financial.utils.CollectionUtils;
import com.xc.financial.utils.DateUtils;
import com.xc.financial.utils.StringUtils;

public class OutstockMapper {
	private Connection connect;
	private Statement statement;
	private ResultSet result;
	private static final String url="jdbc:mysql://localhost:3306/financial?useUnicode=true&characterEncoding=UTF-8";
	private static final String username="root";
	private static final String password="";
	private List<OutstockBean> outstocks = new ArrayList<OutstockBean>();
	
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
			sb.append("insert into outstock(code,type,member,amount,create_date,modify_date,comments,operate) values(");
			if(StringUtils.isEmpty((String)data.get(OutstockColumnEnum.getOutstockColumnValueByKey("code").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(OutstockColumnEnum.getOutstockColumnValueByKey("code").getValue())+"',");
			}
			
			if(StringUtils.isEmpty(data.get(OutstockColumnEnum.getOutstockColumnValueByKey("type").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(OutstockColumnEnum.getOutstockColumnValueByKey("type").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(OutstockColumnEnum.getOutstockColumnValueByKey("member").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(OutstockColumnEnum.getOutstockColumnValueByKey("member").getValue())+"',");
			}
			
			if(StringUtils.isEmpty(data.get(OutstockColumnEnum.getOutstockColumnValueByKey("amount").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(OutstockColumnEnum.getOutstockColumnValueByKey("amount").getValue())+"',");
			}
			
			if(StringUtils.isEmpty(data.get(OutstockColumnEnum.getOutstockColumnValueByKey("pur_source").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(OutstockColumnEnum.getOutstockColumnValueByKey("pur_source").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(OutstockColumnEnum.getOutstockColumnValueByKey("create_date").getValue()))){
				sb.append("'"+ DateUtils.parseLongDate(new Date()) +"',");
			}else{
				sb.append("'"+ data.get(OutstockColumnEnum.getOutstockColumnValueByKey("creat_date").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(OutstockColumnEnum.getOutstockColumnValueByKey("modify_date").getValue()))){
				sb.append("'"+ DateUtils.parseLongDate(new Date()) +"',");
			}else{
				sb.append("'"+ data.get(OutstockColumnEnum.getOutstockColumnValueByKey("modify_date").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(OutstockColumnEnum.getOutstockColumnValueByKey("comments").getValue()))){
				sb.append("null)");
			}else{
				sb.append("'"+ data.get(OutstockColumnEnum.getOutstockColumnValueByKey("comments").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(OutstockColumnEnum.getOutstockColumnValueByKey("operate").getValue()))){
				sb.append("null)");
			}else{
				sb.append("'"+ data.get(OutstockColumnEnum.getOutstockColumnValueByKey("operate").getValue())+"')");
			}
			
			StringBuffer sb1 = new StringBuffer();
			sb1.append("update financial set amount = amount - " + data.get(OutstockColumnEnum.getOutstockColumnValueByKey("amount").getValue()));
			sb1.append(" where type = " + data.get(OutstockColumnEnum.getOutstockColumnValueByKey("pur_source").getValue()));
			sb1.append(" and member = " + data.get(OutstockColumnEnum.getOutstockColumnValueByKey("member").getValue()));
			
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
	public int updateOutstock(Map<String,Object> data){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("update outstock set ");
			if(StringUtils.isEmpty(data.get(OutstockColumnEnum.getOutstockColumnValueByKey("type").getValue()))){
				sb.append("type = null,");
			}else{
				sb.append("type = '"+ data.get(OutstockColumnEnum.getOutstockColumnValueByKey("type").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(OutstockColumnEnum.getOutstockColumnValueByKey("member").getValue()))){
				sb.append("member = null,");
			}else{
				sb.append("member = '"+ data.get(OutstockColumnEnum.getOutstockColumnValueByKey("member").getValue())+"',");
			}
			
			if(StringUtils.isEmpty(data.get(OutstockColumnEnum.getOutstockColumnValueByKey("amount").getValue()))){
				sb.append("amount = null,");
			}else{
				sb.append("amount = '"+ data.get(OutstockColumnEnum.getOutstockColumnValueByKey("amount").getValue()) +"',");
			}
			
			if(StringUtils.isEmpty(data.get(OutstockColumnEnum.getOutstockColumnValueByKey("pur_source").getValue()))){
				sb.append("pur_source = null,");
			}else{
				sb.append("pur_source = '"+ data.get(OutstockColumnEnum.getOutstockColumnValueByKey("pur_source").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(OutstockColumnEnum.getOutstockColumnValueByKey("modify_date").getValue()))){
				sb.append("modify_date = '"+ DateUtils.parseLongDate(new Date()) +"',");
			}else{
				sb.append("modify_date = '"+ DateUtils.parseLongDate(new Date()) +"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(OutstockColumnEnum.getOutstockColumnValueByKey("comments").getValue()))){
				sb.append("comments = null");
			}else{
				sb.append("comments = " + "'"+ data.get(OutstockColumnEnum.getOutstockColumnValueByKey("comments").getValue()) +"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(OutstockColumnEnum.getOutstockColumnValueByKey("operate").getValue()))){
				sb.append("operate = null");
			}else{
				sb.append("operate = " + "'"+ data.get(OutstockColumnEnum.getOutstockColumnValueByKey("operate").getValue()) +"'");
			}
			sb.append(" where code = '" + data.get(OutstockColumnEnum.getOutstockColumnValueByKey("code").getValue()) + "'");
			
			BigDecimal o_amount = BigDecimal.ZERO;
			if(CollectionUtils.isNotEmpty(outstocks)){
				for(OutstockBean outstockBean : outstocks){
					if(data.get(OutstockColumnEnum.getOutstockColumnValueByKey("code").getValue()).equals(outstockBean.getCode())){
						o_amount = outstockBean.getAmount();
						break;
					}
				}
			}
			StringBuffer sb1 = new StringBuffer();
			sb1.append("update financial set amount = amount - " + ((BigDecimal)data.get(InstockColumnEnum.getInstockColumnValueByKey("amount").getValue())).subtract(o_amount));
			sb1.append(" where type = " + data.get(OutstockColumnEnum.getOutstockColumnValueByKey("pur_source").getValue()));
			sb1.append(" and member = " + data.get(OutstockColumnEnum.getOutstockColumnValueByKey("member").getValue()));
			
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
	public int deleteOutstockByCode(Map<String,Object> data){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("delete from outstock where code =  ");
			sb.append("'"+ data.get(OutstockColumnEnum.getOutstockColumnValueByKey("code").getValue())+"'");
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
	public List<OutstockBean> selectOutstocksByParams(OutstockSearchBean searchBean){
		List<OutstockBean> outstockList = new ArrayList<OutstockBean>();
		outstocks = new ArrayList<OutstockBean>();
		try{
			StringBuffer sb = new StringBuffer();	
			sb.append("select o.*,c.value as typeValue,s.value as purSourceValue from outstock o left join code_dict c on o.type = c.id left join code_dict s on o.pur_source = s.id where 1=1");
			if(StringUtils.isNotEmpty(searchBean.getCode())){
				sb.append(" and o.code like '%" + searchBean.getCode() +"%'");
			}
			if(StringUtils.isNotEmpty(searchBean.getStartDate())){
				sb.append(" and o.create_Date >= '" + DateUtils.dayBegin(DateUtils.parseSingleDate(searchBean.getStartDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getEndDate())){
				sb.append(" and o.create_Date <= '" + DateUtils.dayEnd(DateUtils.parseSingleDate(searchBean.getEndDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getType())){
				sb.append(" and o.type = " + Integer.parseInt(searchBean.getType()));
			}
			if(null != searchBean.getPurSource()){
				sb.append(" and o.pur_source = " + searchBean.getPurSource());
			}
			sb.append(" limit " + searchBean.getOffset() + "," + searchBean.getRows());
			
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			result = statement.executeQuery(sb.toString());
			Integer index = 1;
			while(result.next()){
				OutstockBean outstockBean = new OutstockBean();
				outstockBean.setIndex(index++);
				outstockBean.setCode(result.getString("code"));
				outstockBean.setMember(result.getString("member"));
				outstockBean.setCreateDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("create_date"))));
				outstockBean.setModifyDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("modify_date"))));
				outstockBean.setAmount(BigDecimal.valueOf(Double.valueOf(result.getString("amount"))));
				outstockBean.setTypeValue(result.getString("typeValue"));
				outstockBean.setPurSourceValue(result.getString("purSourceValue"));
				outstockBean.setComments(result.getString("comments"));
				outstockBean.setOperate(result.getString("operate"));
				outstockList.add(outstockBean);
				outstocks.add(outstockBean);
			}
			return outstockList;
		}catch(SQLException|ParseException e){
			e.printStackTrace();
			return outstockList;
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
	public Integer getCount(OutstockSearchBean searchBean){
		try{
			StringBuffer sb = new StringBuffer();	
			sb.append("select count(*) as count from outstock o where 1=1 ");
			
			if(StringUtils.isNotEmpty(searchBean.getCode())){
				sb.append(" and o.code like '%" + searchBean.getCode() +"%'");
			}
			if(StringUtils.isNotEmpty(searchBean.getStartDate())){
				sb.append(" and o.create_Date >= '" + DateUtils.dayBegin(DateUtils.parseSingleDate(searchBean.getStartDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getEndDate())){
				sb.append(" and o.create_Date <= '" + DateUtils.dayEnd(DateUtils.parseSingleDate(searchBean.getEndDate())) + "'");
			}
			if(StringUtils.isNotEmpty(searchBean.getType())){
				sb.append(" and o.type = " + Integer.parseInt(searchBean.getType()));
			}
			if(null != searchBean.getPurSource()){
				sb.append(" and o.pur_source = " + searchBean.getPurSource());
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
}
