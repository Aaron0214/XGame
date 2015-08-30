package com.xc.financial.mainapp;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.xc.financial.beans.InstockBean;
import com.xc.financial.beans.SearchBean;
import com.xc.financial.enums.UserColumnEnum;
import com.xc.financial.enums.SnTypeEnum;
import com.xc.financial.enums.UserColumnEnum;
import com.xc.financial.mapper.InstockMapper;
import com.xc.financial.mapper.SnMapper;
import com.xc.financial.utils.CollectionUtils;
import com.xc.financial.utils.DateUtils;
import com.xc.financial.utils.NumberFormatUtils;
import com.xc.financial.utils.ObjectUtils;
import com.xc.financial.utils.StringUtils;

public class UserFrame extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Table table;
	private Vector<Object> columns = new Vector<Object>();
	private String[] columnNames= {"","序号","用户编码","姓名","性别","状态","地址 ","用户名","邮箱","创建时间","修改时间","备注","操作员"}; 
	private Vector<Object> rowData = new Vector<Object>();
	private JScrollPane pane3;
	private JButton button,save,delete,search;
	private JLabel label,label1,label2,label3;
	private JComboBox<String> type;
	private JTextField field,startDate,endDate;
	private DatePicker datepicker,datepicker1;
	private String[] str = {"","A","B"};
	private InstockMapper instockMapper = new InstockMapper();
	private SnMapper snMapper = new SnMapper();
	
	public UserFrame(){
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
		
		label3 = new JLabel("用户名：");
		label3.setFont(new Font("宋体", Font.PLAIN, 13));
		
		type = new JComboBox<String>(str);
		type.setFont(new Font("宋体", Font.PLAIN, 13));
		type.setPreferredSize(new Dimension(50, 20));
		
		search = new JButton("搜索");
		search.setPreferredSize(new Dimension(60, 20));
		search.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		table = new Table(rowData, columns){
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if(column == 0 || column == 3 || column == 4 || column == 5){
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
        this.add(label3);
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
		this.setVisible(false);
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
			saveUpdateData();
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
	
	private void saveUpdateData(){
		List<Map<String, Object>> datas = table.getSelectRowValue();
		if(CollectionUtils.isNotEmpty(datas)){
			for(Map<String, Object> data : datas){
				try {
					if(StringUtils.isEmpty((String)data.get(UserColumnEnum.getUserColumnValueByKey("code").getValue()))){
						int num = snMapper.selectSn(SnTypeEnum.USER_CODE.getKey());
						data.put(UserColumnEnum.getUserColumnValueByKey("code").getValue(),"F" + DateUtils.parseDates(new Date()) + NumberFormatUtils.formatNumber(num));
						instockMapper.insertInstock(data);
					}else{
						instockMapper.updateInstock(data);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			getDatas(new SearchBean());
		}else{
			JOptionPane.showMessageDialog(null, "请先选择需要保存的数据！");
		}
	}
	
	private void getDatas(SearchBean searchBean){
		List<InstockBean> instockBeanList = instockMapper.selectInstocksByParams(searchBean);
		if(CollectionUtils.isNotEmpty(instockBeanList)){
			rowData.clear();
			for(InstockBean instockBean:instockBeanList){
				rowData.add(buildVectorData(instockBean));
			}
		}else{
			rowData.clear();
		}
		if(table != null){
			table.updateTable();
		}
	}
	
	private Vector<Object> buildVectorData(InstockBean instockBean){
		try {
			return ObjectUtils.createNewVecto(instockBean);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
		JPanel panel = new UserFrame();
		panel.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(panel,BorderLayout.CENTER);
	    frame.setSize(680, 580);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    frame.setVisible(true);
	}
}
