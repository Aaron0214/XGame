package com.xc.financial.mainapp;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.xc.financial.beans.InstockBean;
import com.xc.financial.beans.SearchBean;
import com.xc.financial.enums.InstockColumnEnum;
import com.xc.financial.utils.DateUtils;
import com.xc.financial.utils.ObjectUtils;
import com.xc.financial.utils.StringUtils;

public class InstockFrame extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Table table;
	private Vector<Object> columns = new Vector<Object>();
	private String[] columnNames= {"","序号","订单编码","家庭成员","收入类型","金额(元)","创建时间","修改时间","操作员"}; 
	private Vector<Object> rowData = new Vector<Object>();
	private JScrollPane pane3;
	private JButton button,save,delete,search;
	private JLabel label,label1,label2;
	private JComboBox<String> type;
	private JTextField field,startDate,endDate;
	private DatePicker datepicker,datepicker1;
	private String[] str = {"A","B"};
	private Connection connect;
	private Statement statement;
	private ResultSet result;
	private static final String url="jdbc:mysql://localhost:3306/financial?useUnicode=true&characterEncoding=UTF-8";
	private static final String username="root";
	private static final String password="";
	
	public InstockFrame(){
		init();
		
		label = new JLabel("编码：");
		label.setFont(new Font("宋体", Font.PLAIN, 13));
		label.setVerticalAlignment(SwingConstants.TOP);
		
		field = new JTextField();
		field.setPreferredSize(new Dimension(100, 20));
		
		label1 = new JLabel("开始日期：");
		label1.setFont(new Font("宋体", Font.PLAIN, 13));
		
		startDate = new JTextField();
		startDate.setPreferredSize(new Dimension(175, 20));
		datepicker = DatePicker.getInstance("yyyy-MM-dd");
		datepicker.register(startDate);
		
		label2 = new JLabel("结束日期：");
		label2.setFont(new Font("宋体", Font.PLAIN, 13));
		
		endDate = new JTextField();
		endDate.setPreferredSize(new Dimension(175, 20));
		datepicker1 = DatePicker.getInstance("yyyy-MM-dd");
		datepicker1.register(endDate);
		
		type = new JComboBox<String>(str);
		type.setFont(new Font("宋体", Font.PLAIN, 13));
		type.setPreferredSize(new Dimension(50, 20));
		
		search = new JButton("搜索");
		search.setPreferredSize(new Dimension(60, 20));
		search.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		table = new Table(rowData, columns);
		table.setPreferredScrollableViewportSize(new Dimension(670, 420));
		
		table.isCellEditable(0,2);
		//设置第一列的宽度
		table.changeColumnWidth(0, 20);
		//设置第二列的宽度
		table.changeColumnWidth(1, 50);
		//设置第3列的宽度
		table.changeColumnWidth(2, 120);
		//设置第4列的宽度
		table.changeColumnWidth(3, 100);
		//设置第5列的宽度
		table.changeColumnWidth(4, 100);
		//设置第6列的宽度
		table.changeColumnWidth(5, 100);
		//设置第7列的宽度
		table.changeColumnWidth(6, 150);
		//设置第8列的宽度
		table.changeColumnWidth(7, 150);
		//设置第9列的宽度
		table.changeColumnWidth(8, 100);
		
		table.setCellEditor(4, new MyComboBoxEditor(str));
		
		table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		
        pane3 = new JScrollPane(table);
        
        pane3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() != datepicker){
					datepicker.hidePanel();
				}
			}
		});
        
        button = new JButton("添加");
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        save = new JButton("保存");
        save.setCursor(new Cursor(Cursor.HAND_CURSOR));
        delete = new JButton("删除");
        delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
        
        search.addActionListener(this);
        button.addActionListener(this);
        save.addActionListener(this);
        delete.addActionListener(this);
        
        this.add(label);
        this.add(field);
        this.add(label1);
        this.add(startDate);
        this.add(label2);
        this.add(endDate);
        this.add(type);
        this.add(search);
        
        this.add(pane3);
        this.add(button);
        this.add(save);
        this.add(delete);
        
        this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() != datepicker){
					datepicker.hidePanel();
				}
			}
		});
//		this.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == search){
			SearchBean searchBean = new SearchBean();
			searchBean.setCode(field.getText());
			searchBean.setStartDate(startDate.getText());
			searchBean.setEndDate(endDate.getText());
			searchBean.setType(type.getSelectedItem().toString());
			getDatas(searchBean);
		}
		if(e.getSource() == button){
			table.addTableRow();
		}
		if(e.getSource() == save){
			insertData();
		}
		if(e.getSource() == delete){
			table.deleteTableRows(table.getSelectedRows());
		}
		
	}
	
	private void init(){
		//初始化表头
		for(String column : columnNames){
			columns.add(column);
		}
		getDatas(new SearchBean());
	}
	
	private void insertData(){
		StringBuffer sb = new StringBuffer();
		List<Map<String, Object>> datas = table.getSelectRowValue(table.getSelectedRows());
		for(Map<String, Object> data : datas){
			try {
				sb.append("insert into instock(code,type,member,amount,creat_date,modify_date,operate) values(");
				sb.append("'F20150826000001',");
				sb.append("'"+ data.get(InstockColumnEnum.getInstockColumnValueByKey("type").getValue())+"',");
				sb.append("'"+ data.get(InstockColumnEnum.getInstockColumnValueByKey("member").getValue())+"',");
				sb.append("'"+ data.get(InstockColumnEnum.getInstockColumnValueByKey("amount").getValue()) +"',");
				sb.append("'"+ DateUtils.parseLongDate(new Date()) +"',");
				sb.append("'"+ DateUtils.parseLongDate(new Date()) +"',");
				sb.append(data.get("abc") == null ? null + ")": "'"+ data.get("abc") +"')");
				
				connect = DriverManager.getConnection(url, username, password);
				statement = connect.createStatement();
				statement.executeUpdate(sb.toString());
				statement.close();
				connect.close();
			} catch (SQLException|ParseException e1) {
				e1.printStackTrace();
			}finally{
				try {
					statement.close();
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private void getDatas(SearchBean searchBean){
		StringBuffer sb = new StringBuffer();	
		sb.append("select * from instock where 1=1");
		if(StringUtils.isNotEmpty(searchBean.getCode())){
			sb.append(" and code like '%" + searchBean.getCode() +"%'");
		}
		if(StringUtils.isNotEmpty(searchBean.getStartDate())){
			sb.append(" and createDate >= '" + DateUtils.dateBegin(DateUtils.parseDate(searchBean.getStartDate())) + "'");
		}
		if(StringUtils.isNotEmpty(searchBean.getEndDate())){
			sb.append(" and createDate <= '" + DateUtils.dateEnd(DateUtils.parseDate(searchBean.getEndDate())) + "'");
		}
		if(StringUtils.isNotEmpty(searchBean.getType())){
			sb.append(" and type = '" + searchBean.getType() + "'");
		}
		try {
			connect = DriverManager.getConnection(url, username, password);
			statement = connect.createStatement();
			result = statement.executeQuery(sb.toString());
			result.last();
			int count = result.getRow();
			if(count != 0){
				result.beforeFirst();
				Integer index = 1;
				while(result.next()){
					rowData.clear();
					buildData(result,index,count);
					if(null != table){
						table.updateTable();
					}
				}
			}else{
				rowData.clear();
				table.updateTable();
			}
			statement.close();
			connect.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally{
			try {
				statement.close();
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void buildData(ResultSet rs,Integer index,int count){
		InstockBean instockBean = new InstockBean();
		try {
			instockBean.setIndex(index);
			instockBean.setCode(rs.getString("code"));
			instockBean.setMember(rs.getString("member"));
			instockBean.setCreateDate(DateUtils.parseLongDate(DateUtils.parseDate(rs.getString("create_date"))));
			instockBean.setModifyDate(DateUtils.parseLongDate(DateUtils.parseDate(rs.getString("modify_date"))));
			instockBean.setAmount(BigDecimal.valueOf(Double.valueOf(rs.getString("amount"))));
			instockBean.setType(rs.getString("type"));
			instockBean.setOperate(rs.getString("operate"));
			Vector<Object> obj = ObjectUtils.changeToVecto(instockBean);
			rowData.add(obj);
		} catch (SQLException|ParseException e) {
			e.printStackTrace();
		}
	}
	
	class MyComboBoxEditor extends DefaultCellEditor {
		private static final long serialVersionUID = 1L;

		public MyComboBoxEditor(String[] items) {
		    super(new JComboBox<Object>(items));
		}
	 }
	
	class MyCheckBoxEditor extends DefaultCellEditor {
		private static final long serialVersionUID = 1L;

		public MyCheckBoxEditor() {
		    super(new JCheckBox());
		}
	 }
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		JPanel panel = new InstockFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(panel,BorderLayout.CENTER);
	    frame.setSize(680, 580);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    frame.setVisible(true);
	}
}
