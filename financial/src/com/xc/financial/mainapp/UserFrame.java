package com.xc.financial.mainapp;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.xc.financial.beans.UserOperateBean;
import com.xc.financial.beans.UserSearchBean;
import com.xc.financial.enums.column.UserColumnEnum;
import com.xc.financial.mapper.UserMapper;
import com.xc.financial.utils.CollectionUtils;
import com.xc.financial.utils.ObjectUtils;

public class UserFrame extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Table table;
	private Vector<Object> columns = new Vector<Object>();
	private String[] columnNames= {"","序号","用户编码","姓名","性别","出生日期","状态","地址 ","用户名","邮箱","电话号码","创建时间","修改时间","备注","操作员"}; 
	private Vector<Object> rowData = new Vector<Object>();
	private JScrollPane pane3;
	private JButton button,edit,delete,search;
	private JLabel label,label1,label2,label3,label4,label5;
	private MyComboBox type;
	private JTextField field,field1,field2,startDate,endDate;
	private DatePicker datepicker,datepicker1;
	private static List<Map<String,Object>> str = new ArrayList<Map<String,Object>>();
	private UserMapper userkMapper = new UserMapper();
	private PageToolBar<UserFrame> pageTool;
	private Integer totalNumber;
	
	public UserFrame(){
		this.setLayout(null);
		init();
		
		label = new JLabel("编码：");
		label.setFont(new Font("宋体", Font.PLAIN, 13));
		label.setBounds(new Rectangle(5,15,50,20));
		
		field = new JTextField();
		field.setBounds(new Rectangle(45,15,120,20));
		
		label3 = new JLabel("用户名：");
		label3.setFont(new Font("宋体", Font.PLAIN, 13));
		label3.setBounds(new Rectangle(175,15,80,20));
		
		field1 = new JTextField();
		field1.setBounds(new Rectangle(230,15,120,20));
		
		label5 = new JLabel("姓名：");
		label5.setFont(new Font("宋体", Font.PLAIN, 13));
		label5.setBounds(new Rectangle(360,15,80,20));
		
		field2 = new JTextField();
		field2.setBounds(new Rectangle(400,15,120,20));
		
		label4 = new JLabel("状态：");
		label4.setFont(new Font("宋体", Font.PLAIN, 13));
		label4.setBounds(new Rectangle(530,15,80,20));
		
		type = new MyComboBox(str);
		type.setFont(new Font("宋体", Font.PLAIN, 13));
		type.setBounds(new Rectangle(570,15,90,20));
		type.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		label1 = new JLabel("开始日期：");
		label1.setFont(new Font("宋体", Font.PLAIN, 13));
		label1.setBounds(new Rectangle(5,45,80,20));
		
		startDate = new JTextField();
		startDate.setBounds(new Rectangle(75,45,175,20));
		datepicker = DatePicker.getInstance("yyyy-MM-dd");
		datepicker.register(startDate);
		
		label2 = new JLabel("结束日期：");
		label2.setFont(new Font("宋体", Font.PLAIN, 13));
		label2.setBounds(new Rectangle(265,45,80,20));
		
		endDate = new JTextField();
		endDate.setBounds(new Rectangle(335,45,175,20));
		datepicker1 = DatePicker.getInstance("yyyy-MM-dd");
		datepicker1.register(endDate);
		
		search = new JButton("搜索");
		search.setBounds(new Rectangle(530,45,60,20));
		search.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		table = new Table(rowData, columns){
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if(column == 0){
					return true;
				}else{
					return false;
				}
            }
		};
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
		table.changeColumnWidth(4, 80);
		//设置第6列的宽度
		table.changeColumnWidth(5, 100);
		//设置第6列的宽度
		table.changeColumnWidth(6, 80);
		//设置第7列的宽度
		table.changeColumnWidth(7, 200);
		//设置第8列的宽度
		table.changeColumnWidth(8, 100);
		//设置第9列的宽度
		table.changeColumnWidth(9, 150);
		//设置第9列的宽度
		table.changeColumnWidth(10, 100);
		//设置第10列的宽度
		table.changeColumnWidth(11, 150);
		//设置第11列的宽度
		table.changeColumnWidth(12, 150);
		//设置第12列的宽度
		table.changeColumnWidth(13, 150);
		//设置第13列的宽度
		table.changeColumnWidth(14, 100);
		
		table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		table.setOpaque(false);
		
        pane3 = new JScrollPane(table);
        pane3.setOpaque(false);
        pane3.getViewport().setOpaque(false);
        pane3.setBounds(new Rectangle(2,80,670,420));
        
        pane3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() != datepicker){
					datepicker.hidePanel();
				}
			}
		});
        
        pageTool = new PageToolBar<UserFrame>(this,totalNumber);
        pageTool.setBounds(new Rectangle(668-pageTool.getPanelLength(),455,pageTool.getPanelLength()-3,22));
        
        button = new JButton("添加");
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBounds(new Rectangle(240,508,60,25));
        edit = new JButton("编辑");
        edit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        edit.setBounds(new Rectangle(310,508,60,25));
        delete = new JButton("删除");
        delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        delete.setBounds(new Rectangle(380,508,60,25));
        
        search.addActionListener(this);
        button.addActionListener(this);
        edit.addActionListener(this);
        delete.addActionListener(this);
        
        this.add(label);
        this.add(field);
        this.add(label3);
        this.add(field1);
        this.add(label5);
        this.add(field2);
        this.add(label4);
        this.add(label1);
        this.add(startDate);
        this.add(label2);
        this.add(endDate);
        this.add(type);
        this.add(search);
        
        this.add(pageTool);
        this.add(pane3);
        this.add(button);
        this.add(edit);
        this.add(delete);
        
        this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() != datepicker || e.getSource() != datepicker1){
					datepicker.hidePanel();
					datepicker1.hidePanel();
				}
			}
		});
		this.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == search){
			search(new Long(1),new Long(15));
		}
		if(e.getSource() == button){
			new ManageUserFrame(this,null,"add");
		}
		if(e.getSource() == edit){
			List<Map<String, Object>> datas = table.getSelectRowValue();
			if(CollectionUtils.isNotEmpty(datas)){
				if(datas.size() > 1){
					JOptionPane.showMessageDialog(null, "请选择一条数据进行编辑！");
				}else{
					Map<String, Object> data = datas.get(0);
					new ManageUserFrame(this,(String)data.get(UserColumnEnum.getUserColumnValueByKey("code").getValue()),"edit");
				}
			}else{
				JOptionPane.showMessageDialog(null, "请先选择一行需要编辑的数据！");
			}
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
		//初始化静态数据
		str.clear();
		Map<String,Object> black = new HashMap<String,Object>();
		black.put("label", "");
		black.put("value", null);
		str.add(black);
		
		Map<String,Object> item = new HashMap<String,Object>();
		item.put("label", "正常");
		item.put("value", "Y");
		str.add(item);
		
		Map<String,Object> item1 = new HashMap<String,Object>();
		item1.put("label", "不正常");
		item1.put("value", "N");
		str.add(item1);
		
		UserSearchBean searchBean = new UserSearchBean();
		searchBean.setPageNumber(new Long(1));
		searchBean.setPageSize(new Long(15));
		getDatas(searchBean);
	}
	
	public void getDatas(UserSearchBean searchBean){
		List<UserOperateBean> userBeanList = userkMapper.selectUsersByParams(searchBean);
		totalNumber = userBeanList.size();
		if(CollectionUtils.isNotEmpty(userBeanList)){
			rowData.clear();
			for(UserOperateBean userBean:userBeanList){
				rowData.add(buildVectorData(userBean));
			}
		}else{
			rowData.clear();
		}
		if(table != null){
			table.updateTable();
		}
	}
	
	private void search(long pageNumber,long pageSize){
		UserSearchBean searchBean = new UserSearchBean();
		searchBean.setCode(field.getText());
		searchBean.setStartDate(startDate.getText());
		searchBean.setEndDate(endDate.getText());
		searchBean.setType(type.getSelectedItem().toString());
		searchBean.setPageNumber(pageNumber);
		searchBean.setPageSize(pageSize);
		getDatas(searchBean);
	}
	
	private Vector<Object> buildVectorData(UserOperateBean userBean){
		try {
			return ObjectUtils.createNewVecto(userBean);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		JPanel panel = new UserFrame();
		panel.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(panel,BorderLayout.CENTER);
	    frame.setSize(680, 570);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    frame.setVisible(true);
	}
}
