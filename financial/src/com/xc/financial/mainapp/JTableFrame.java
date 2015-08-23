package com.xc.financial.mainapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class JTableFrame {
	
	private JFrame frame;
	private static final long serialVersionUID = 1L;
	private JTable table;
	private Object[] columnNames = {"","序号","订单编码","收入类型","金额","创建时间","操作员"};
	private Object[][] rowData = {};
	private DefaultTableModel defaultmodel = null;
	
	public JTableFrame(){
		frame = new JFrame();
		
		defaultmodel = new DefaultTableModel(rowData, columnNames);
		
		table = new JTable(defaultmodel);
		table.setPreferredScrollableViewportSize(new Dimension(670, 580));
		
		defaultmodel.addRow(new Vector<Object>());
		
		//设置第一列的宽度
		TableColumn firsetColumn = table.getColumnModel().getColumn(0);
		firsetColumn.setMaxWidth(2);
		
		//设置第二列的宽度
		TableColumn twoColumn = table.getColumnModel().getColumn(1);
		twoColumn.setMaxWidth(50);
		
		table.setRowHeight(25);// 设置每行的高度为20
		table.setRowMargin(5);// 设置相邻两行单元格的距离
	    table.setRowSelectionAllowed(true);// 设置可否被选择.默认为false
	    table.setSelectionBackground(Color.white);// 设置所选择行的背景色
	    table.setSelectionForeground(Color.red);// 设置所选择行的前景色
	    table.setGridColor(Color.black);// 设置网格线的颜色
	    
	    table.setDragEnabled(false);// 不懂这个
	    table.setShowGrid(false);// 是否显示网格线
	    table.setShowHorizontalLines(true);// 是否显示水平的网格线
	    table.setShowVerticalLines(true);// 是否显示垂直的网格线
	    table.doLayout();
//	    table.setBackground(Color.white);
	    
	    JScrollPane pane3 = new JScrollPane(table);
	    
	    JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setPreferredSize(new Dimension(600, 400));
        panel.setBackground(Color.black);
        panel.add(pane3);
		
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel,BorderLayout.CENTER);
//		this.setBackground(Color.WHITE);
	    frame.setSize(670, 580);
	    frame.setVisible(true);
	}
	
	public static void main(String[] args){
		new JTableFrame();
	}
	
}
