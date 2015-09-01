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

import com.xc.financial.beans.FinancialBean;
import com.xc.financial.beans.SearchBean;
import com.xc.financial.enums.column.FinancialColumnEnum;
import com.xc.financial.enums.column.OutstockColumnEnum;
import com.xc.financial.utils.DateUtils;
import com.xc.financial.utils.StringUtils;

public class FinancialMapper {
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
	public int insertFinancial(Map<String,Object> data){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("insert into financial(code,type,member,amount,create_date,modify_date,comments,operate) values(");
			
			if(StringUtils.isEmpty((String)data.get(OutstockColumnEnum.getOutstockColumnValueByKey("code").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(OutstockColumnEnum.getOutstockColumnValueByKey("code").getValue())+"',");
			}
			
			if(StringUtils.isEmpty(data.get(FinancialColumnEnum.getFinancialColumnValueByKey("type").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(FinancialColumnEnum.getFinancialColumnValueByKey("type").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(FinancialColumnEnum.getFinancialColumnValueByKey("member").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(FinancialColumnEnum.getFinancialColumnValueByKey("member").getValue())+"',");
			}
			
			if(StringUtils.isEmpty(data.get(FinancialColumnEnum.getFinancialColumnValueByKey("amount").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(FinancialColumnEnum.getFinancialColumnValueByKey("amount").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(FinancialColumnEnum.getFinancialColumnValueByKey("create_date").getValue()))){
				sb.append("'"+ DateUtils.parseLongDate(new Date()) +"',");
			}else{
				sb.append("'"+ data.get(FinancialColumnEnum.getFinancialColumnValueByKey("creat_date").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(FinancialColumnEnum.getFinancialColumnValueByKey("modify_date").getValue()))){
				sb.append("'"+ DateUtils.parseLongDate(new Date()) +"',");
			}else{
				sb.append("'"+ data.get(FinancialColumnEnum.getFinancialColumnValueByKey("modify_date").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(OutstockColumnEnum.getOutstockColumnValueByKey("comments").getValue()))){
				sb.append("null)");
			}else{
				sb.append("'"+ data.get(OutstockColumnEnum.getOutstockColumnValueByKey("comments").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(FinancialColumnEnum.getFinancialColumnValueByKey("operate").getValue()))){
				sb.append("null)");
			}else{
				sb.append("'"+ data.get(FinancialColumnEnum.getFinancialColumnValueByKey("operate").getValue())+"')");
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
	public int updateFinancial(Map<String,Object> data){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("update financial set ");
			if(StringUtils.isEmpty(data.get(FinancialColumnEnum.getFinancialColumnValueByKey("type").getValue()))){
				sb.append("type = null,");
			}else{
				sb.append("type = '"+ data.get(FinancialColumnEnum.getFinancialColumnValueByKey("type").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(FinancialColumnEnum.getFinancialColumnValueByKey("member").getValue()))){
				sb.append("member = null,");
			}else{
				sb.append("member = '"+ data.get(FinancialColumnEnum.getFinancialColumnValueByKey("member").getValue())+"',");
			}
			
			if(StringUtils.isEmpty(data.get(FinancialColumnEnum.getFinancialColumnValueByKey("amount").getValue()))){
				sb.append("amount = null,");
			}else{
				sb.append("amount = '"+ data.get(FinancialColumnEnum.getFinancialColumnValueByKey("amount").getValue()) +"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(FinancialColumnEnum.getFinancialColumnValueByKey("modify_date").getValue()))){
				sb.append("modify_date = '"+ DateUtils.parseLongDate(new Date()) +"',");
			}else{
				sb.append("modify_date = '"+ data.get(FinancialColumnEnum.getFinancialColumnValueByKey("modify_date").getValue()) +"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(OutstockColumnEnum.getOutstockColumnValueByKey("comments").getValue()))){
				sb.append("comments = null");
			}else{
				sb.append("comments = " + "'"+ data.get(OutstockColumnEnum.getOutstockColumnValueByKey("comments").getValue()) +"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(FinancialColumnEnum.getFinancialColumnValueByKey("operate").getValue()))){
				sb.append("operate = null");
			}else{
				sb.append("operate = " + "'"+ data.get(FinancialColumnEnum.getFinancialColumnValueByKey("operate").getValue()) +"'");
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
	public int deleteFinancialByCode(Map<String,Object> data){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("delete from financial where code =  ");
			sb.append("'"+ data.get(FinancialColumnEnum.getFinancialColumnValueByKey("code").getValue())+"'");
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
	public List<FinancialBean> selectFinancialsByParams(SearchBean searchBean){
		List<FinancialBean> finanacialList = new ArrayList<FinancialBean>();
		try{
			StringBuffer sb = new StringBuffer();	
			sb.append("select f.*,c.value as typeValue from financial f left join code_dict c on f.type = c.id where 1=1");
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
				sb.append(" and type = " + Integer.parseInt(searchBean.getType()));
			}
			
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			result = statement.executeQuery(sb.toString());
			Integer index = 1;
			while(result.next()){
				FinancialBean financialBean = new FinancialBean();
				financialBean.setIndex(index++);
				financialBean.setCode(result.getString("code"));
				financialBean.setMember(result.getString("member"));
				financialBean.setCreateDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("create_date"))));
				financialBean.setModifyDate(DateUtils.parseLongDate(DateUtils.parseDate(result.getString("modify_date"))));
				financialBean.setAmount(BigDecimal.valueOf(Double.valueOf(result.getString("amount"))));
				financialBean.setTypeValue(result.getString("typeValue"));
				financialBean.setComments(result.getString("comments"));
				financialBean.setOperate(result.getString("operate"));
				finanacialList.add(financialBean);
			}
			return finanacialList;
		}catch(SQLException|ParseException e){
			e.printStackTrace();
			return finanacialList;
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
