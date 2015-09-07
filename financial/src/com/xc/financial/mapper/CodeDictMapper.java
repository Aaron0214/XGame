package com.xc.financial.mapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xc.financial.beans.CodeDictBean;
import com.xc.financial.beans.SearchBean;
import com.xc.financial.enums.CodeDictEnum;
import com.xc.financial.enums.column.CodeDictColumnEnum;
import com.xc.financial.utils.StringUtils;

public class CodeDictMapper {
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
	public int insertCodeDict(Map<String,Object> data){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("insert into code_dict(type,value,pid) values(");
			
			if(StringUtils.isEmpty((Object)data.get(CodeDictColumnEnum.getCodeDictColumnValueByKey("type").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(CodeDictColumnEnum.getCodeDictColumnValueByKey("type").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(CodeDictColumnEnum.getCodeDictColumnValueByKey("value").getValue()))){
				sb.append("null,");
			}else{
				sb.append("'"+ data.get(CodeDictColumnEnum.getCodeDictColumnValueByKey("value").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((Object)data.get(CodeDictColumnEnum.getCodeDictColumnValueByKey("pid").getValue()))){
				sb.append("null)");
			}else{
				sb.append("'"+ data.get(CodeDictColumnEnum.getCodeDictColumnValueByKey("pid").getValue())+"')");
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
	public int updateCodeDict(Map<String,Object> data){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("update code_dict set ");
			if(StringUtils.isEmpty((Object)data.get(CodeDictColumnEnum.getCodeDictColumnValueByKey("type").getValue()))){
				sb.append("type = null,");
			}else{
				sb.append("type = '"+ data.get(CodeDictColumnEnum.getCodeDictColumnValueByKey("type").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((String)data.get(CodeDictColumnEnum.getCodeDictColumnValueByKey("value").getValue()))){
				sb.append("value = null,");
			}else{
				sb.append("value = '"+ data.get(CodeDictColumnEnum.getCodeDictColumnValueByKey("value").getValue())+"',");
			}
			
			if(StringUtils.isEmpty((Object)data.get(CodeDictColumnEnum.getCodeDictColumnValueByKey("pid").getValue()))){
				sb.append("pid = null");
			}else{
				sb.append("pid = '"+ data.get(CodeDictColumnEnum.getCodeDictColumnValueByKey("pid").getValue()) +"'");
			}
			sb.append(" where id = " + data.get(CodeDictColumnEnum.getCodeDictColumnValueByKey("id").getValue()));
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
	public int deleteCodeDictByCode(Map<String,Object> data){
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("delete from code_dict where id =  ");
			sb.append("'"+ data.get(CodeDictColumnEnum.getCodeDictColumnValueByKey("id").getValue())+"'");
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
	public List<CodeDictBean> selectCodeDictsByParams(SearchBean searchBean){
		List<CodeDictBean> codeDictList = new ArrayList<CodeDictBean>();
		try{
			StringBuffer sb = new StringBuffer();	
			sb.append("select c.*,p.value as pidValue from code_dict c left join code_dict p on c.pid = p.id where 1=1 ");
			
			if(null != searchBean.getId()){
				sb.append(" and c.id = '" + searchBean.getId() + "'");
			}
			
			if(StringUtils.isNotEmpty(searchBean.getType())){
				sb.append(" and c.type = " + Integer.parseInt(searchBean.getType()));
			}
			sb.append(" order by c.id;");
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			result = statement.executeQuery(sb.toString());
			while(result.next()){
				CodeDictBean codeDictBean = new CodeDictBean();
				codeDictBean.setId(Integer.parseInt(result.getString("id")));
				codeDictBean.setType(Integer.parseInt(result.getString("type")));
				codeDictBean.setTypeValue(CodeDictEnum.getCodeDictValueByKey(Integer.parseInt(result.getString("type"))).getValue());
				codeDictBean.setValue(result.getString("value"));
				codeDictBean.setPid(result.getString("pid") == null ? null : Integer.parseInt(result.getString("pid")));
				codeDictBean.setPidValue(result.getString("pidValue") == null ? null : result.getString("pidValue"));
				codeDictList.add(codeDictBean);
			}
			return codeDictList;
		}catch(SQLException e){
			e.printStackTrace();
			return codeDictList;
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
	
	/**
	 * <p>
	 * 查询一个数据的子集
	 * </p>
	 * 
	 * @param data
	 * @return
	 */
	public List<CodeDictBean> selectChildrenByParams(Map<String,Object> params){
		List<CodeDictBean> codeDictList = new ArrayList<CodeDictBean>();
		try{
			StringBuffer sb = new StringBuffer();	
			sb.append("select c.* from code_dict c left join code_dict p on c.pid = p.id where 1=1");
			
			if(StringUtils.isNotEmpty(params.get("id"))){
				sb.append(" and p.id = " + params.get("id"));
			}
			
			if(StringUtils.isNotEmpty(params.get("type"))){
				sb.append(" and c.type = " + params.get("type"));
			}
			
			if(StringUtils.isNotEmpty(params.get("value"))){
				sb.append(" and p.value = '" + params.get("value") +"'");
			}
			
			if(StringUtils.isNotEmpty(params.get("code"))){
				sb.append(" and p.code = '" + params.get("code") +"'");
			}
			
			sb.append(" order by c.id;");
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			result = statement.executeQuery(sb.toString());
			while(result.next()){
				CodeDictBean codeDictBean = new CodeDictBean();
				codeDictBean.setId(Integer.parseInt(result.getString("id")));
				codeDictBean.setType(Integer.parseInt(result.getString("type")));
				codeDictBean.setTypeValue(CodeDictEnum.getCodeDictValueByKey(Integer.parseInt(result.getString("type"))).getValue());
				codeDictBean.setValue(result.getString("value"));
				codeDictBean.setPid(result.getString("pid") == null ? null : Integer.parseInt(result.getString("pid")));
				codeDictBean.setCode(result.getString("code"));
				codeDictList.add(codeDictBean);
			}
			return codeDictList;
		}catch(SQLException e){
			e.printStackTrace();
			return codeDictList;
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
