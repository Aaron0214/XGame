package com.xc.financial.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.xc.financial.utils.DateUtils;
import com.xc.financial.utils.StringUtils;
import com.xc.financial.tools.*;

public class JTableFrame implements ActionListener{
	
	private JFrame frame;
	private JTable table;
	private Vector<Object> columnNames = new Vector<Object>();
	private String[] columns = {"","序号","订单编码","家庭成员","收入类型","金额","创建时间","修改时间","操作员"};
	private Vector<Object> rowData = new Vector<Object>();
	private JScrollPane pane3;
	private JButton button,save,delete,search;
	private JLabel label,label1,label2;
	private JComboBox<String> type;
	private JTextField field,startDate,endDate;
	private DefaultTableModel defaultmodel = null;
	private DatePicker datepicker,datepicker1;
	
	private String[] str = {"A","B"};
	
	public JTableFrame(){
		frame = new JFrame();
		
	    JPanel panel = buildJpanel();
		
	    panel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(e.getSource() != datepicker){
					datepicker.hidePanel();
				}
			}
		});
	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel,BorderLayout.CENTER);
	    frame.setSize(680, 580);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    frame.setVisible(true);
	}
	
	private JPanel buildJpanel(){
		JPanel jpanel = new JPanel();
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
		search.setPreferredSize(new Dimension(70, 20));
		
		defaultmodel = new DefaultTableModel(rowData, columnNames);
		table = new JTable(defaultmodel);
		table.setPreferredScrollableViewportSize(new Dimension(670, 440));
		
		//设置第一列的宽度
		TableColumn firsetColumn = table.getColumnModel().getColumn(0);
		firsetColumn.setMaxWidth(5);
		
		//设置第二列的宽度
		TableColumn twoColumn = table.getColumnModel().getColumn(1);
		twoColumn.setMaxWidth(50);
		twoColumn.setCellEditor(new MyComboBoxEditor(str));
				
		table.setRowHeight(20);// 设置每行的高度为20
		table.setRowHeight(0, 20);// 设置第1行的高度为15
//		table.setRowMargin(5);// 设置相邻两行单元格的距离
        table.setRowSelectionAllowed(true);// 设置可否被选择.默认为false
        table.setSelectionBackground(Color.white);// 设置所选择行的背景色
        table.setSelectionForeground(Color.red);// 设置所选择行的前景色
        table.setGridColor(Color.black);// 设置网格线的颜色
        
        table.setDragEnabled(false);// 不懂这个
        table.setShowGrid(false);// 是否显示网格线
        table.setShowHorizontalLines(true);// 是否显示水平的网格线
        table.setShowVerticalLines(true);// 是否显示垂直的网格线
        table.setBackground(Color.WHITE);
        table.doLayout();
        
        pane3 = new JScrollPane(table);
        pane3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
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
		
        button.addActionListener(this);
        
        jpanel.add(label);
        jpanel.add(field);
        jpanel.add(label1);
        jpanel.add(startDate);
        jpanel.add(label2);
        jpanel.add(endDate);
        jpanel.add(type);
        jpanel.add(search);
        
        jpanel.add(pane3);
        jpanel.add(button);
        jpanel.add(save);
        jpanel.add(delete);
        return jpanel;
	}

	
	
	public static void main(String[] args){
		new JTableFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == search){
			String code = field.getText();
			String start = startDate.getText();
			String end = endDate.getText();
			String typeValue = type.getSelectedItem().toString();
			StringBuffer sb = new StringBuffer();	
			sb.append("select * from instock where 1=1 ");
			if(StringUtils.isNotEmpty(code)){
				sb.append("and code like %" + code +"%");
			}
			if(StringUtils.isNotEmpty(start)){
				sb.append("and createDate >=" + DateUtils.dateBegin(DateUtils.parseDate(start)));
			}
			if(StringUtils.isNotEmpty(end)){
				sb.append("and createDate <=" + DateUtils.dateEnd(DateUtils.parseDate(end)));
			}
			if(StringUtils.isNotEmpty(typeValue)){
				sb.append("and type =" + typeValue);
			}
		}
		
		if(e.getSource() == button){
			Vector<Object> data = new Vector<Object>();
			for(int i =0;i<9;i++){
				data.add("a");
			}
			rowData.add(data);
			rowData.clear();
			AbstractTableModel model = (AbstractTableModel) table.getModel();
			model.fireTableDataChanged();
//			defaultmodel.addRow(new Vector<Object>());
		}
		
	}
	
	private void init(){
		for(String colum:columns){
			columnNames.add(colum);
		}
		Vector<Object> data = new Vector<Object>();
		for(int i =0;i<9;i++){
			data.add("a");
		}
		rowData.add(data);
	}
	
	class MyComboBoxEditor extends DefaultCellEditor {
		private static final long serialVersionUID = 1L;

	public MyComboBoxEditor(String[] items) {
	    super(new JComboBox<Object>(items));
	  }
	}

}
