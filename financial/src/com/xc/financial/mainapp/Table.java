package com.xc.financial.mainapp;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Table extends JTable{
	
	private static final long serialVersionUID = 1L;
	private DefaultTableModel defaultmodel;
	private Object[] columnNames;
	
	public Table(Object[][] data,Object[] columnNames){
		this.columnNames = columnNames;
		
		defaultmodel = new DefaultTableModel(data, columnNames){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) {
				if(column == 3 || column == 4 || column == 5){
					return true;
				}else{
					return false;
				}
            }
		};
		this.setModel(defaultmodel);
		this.setRowHeight(20);// 设置每行的高度为20
		this.setRowHeight(0, 20);// 设置第1行的高度为15
        this.setRowSelectionAllowed(true);// 设置可否被选择.默认为false
        this.setSelectionBackground(Color.white);// 设置所选择行的背景色
        this.setSelectionForeground(Color.red);// 设置所选择行的前景色
        this.setGridColor(Color.black);// 设置网格线的颜色
        this.setDragEnabled(false);// 不懂这个
        this.setShowGrid(false);// 是否显示网格线	
        this.setShowHorizontalLines(true);// 是否显示水平的网格线
        this.setShowVerticalLines(true);// 是否显示垂直的网格线
        this.setBackground(Color.WHITE);
        this.doLayout();
	}
	
	public List<Map<String,Object>> getSelectRowValue(int[] row){
		List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
		for(int i = 0; i < row.length; i++){
			Map<String,Object> rowdata = new HashMap<String,Object>();
			for(int j = 0; j< columnNames.length; j++){
				rowdata.put(this.getColumnName(j), this.getValueAt(i, j));
			}
			datas.add(rowdata);
		}
		return datas;
	}
	
	public void addTableRow(){
		defaultmodel.addRow(new Vector<Object>());
	}
	
	public void changeColumnWidth(int columnIndex,int width){
		TableColumn column = this.getColumnModel().getColumn(columnIndex);
		column.setPreferredWidth(width);
		column.setMinWidth(width);
		column.setMaxWidth(width);
	}

}
