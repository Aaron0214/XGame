package com.xc.financial.mainapp;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class InstockFrame extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private Object[] columnNames = {"","序号","订单编码","收入类型","金额","创建时间","操作员"};
	private Object[][] rowData = {};
	private JScrollPane pane3;
	private JButton button,save,delete;
	private JLabel lable;
	
	public InstockFrame(){
		
		lable = new JLabel("编码：");
		
		
		table = new JTable(rowData, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(670, 480));
		
		//设置第一列的宽度
		TableColumn firsetColumn = table.getColumnModel().getColumn(0);
		firsetColumn.setMaxWidth(2);
		
		//设置第二列的宽度
		TableColumn twoColumn = table.getColumnModel().getColumn(1);
		twoColumn.setMaxWidth(50);
				
		table.setRowHeight(30);// 设置每行的高度为20
		table.setRowHeight(0, 20);// 设置第1行的高度为15
		table.setRowMargin(5);// 设置相邻两行单元格的距离
        table.setRowSelectionAllowed(true);// 设置可否被选择.默认为false
        table.setSelectionBackground(Color.white);// 设置所选择行的背景色
        table.setSelectionForeground(Color.red);// 设置所选择行的前景色
        table.setGridColor(Color.black);// 设置网格线的颜色
        
        table.setDragEnabled(false);// 不懂这个
        table.setShowGrid(false);// 是否显示网格线
        table.setShowHorizontalLines(true);// 是否显示水平的网格线
        table.setShowVerticalLines(true);// 是否显示垂直的网格线
        table.setBackground(Color.BLACK);
        table.doLayout();
        
        pane3 = new JScrollPane(table);
        
        button = new JButton("添加");
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        save = new JButton("保存");
        save.setCursor(new Cursor(Cursor.HAND_CURSOR));
        delete = new JButton("删除");
        delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
        
        this.add(lable);
        this.add(pane3);
        this.add(button);
        this.add(save);
        this.add(delete);
        
		this.setSize(650, 546);
		this.setVisible(false);
		
	}
	
	public static void main(String[] args){
		new InstockFrame();
	}
}
