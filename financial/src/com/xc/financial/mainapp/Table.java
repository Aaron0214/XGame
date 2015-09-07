package com.xc.financial.mainapp;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.xc.financial.utils.CollectionUtils;
import com.xc.financial.utils.ObjectUtils;

public class Table extends JTable{
	
	private static final long serialVersionUID = 1L;
	private DefaultTableModel defaultmodel;
	private Vector<Object>  columnNames;
	private Vector<Object>  data;
	private Table table = null;
	public List<Object> rows = new ArrayList<Object>();
	
	public Table(Vector<Object> data,Vector<Object> columnNames){
		this.columnNames = columnNames;
		this.data = data;
		table = this;
		
		defaultmodel = new DefaultTableModel(data, columnNames){

			private static final long serialVersionUID = 1L;
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}
		};
		this.setModel(defaultmodel);
		this.setRowHeight(20);// 设置每行的高度为20
		this.setRowHeight(0, 20);// 设置第1行的高度为15
        this.setRowSelectionAllowed(true);// 设置可否被选择.默认为false
        this.setSelectionBackground(new Color(166, 204, 247));// 设置所选择行的背景色
//        this.setSelectionForeground(Color.red);// 设置所选择行的前景色
        this.setGridColor(Color.black);// 设置网格线的颜色
        this.setDragEnabled(false);// 不懂这个
        this.setShowGrid(false);// 是否显示网格线	
        this.setShowHorizontalLines(true);// 是否显示水平的网格线
        this.setShowVerticalLines(true);// 是否显示垂直的网格线
        this.setBackground(Color.WHITE);
        this.doLayout();
        this.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e) {
	        	if(e.getSource() == table){
					Object value = table.getValueAt(table.getSelectedRow(), 0);
					if(!rows.contains((Object)table.getSelectedRow())){
						if(value == Boolean.TRUE){
							rows.add((Object)table.getSelectedRow());
						}else{
							rows.remove((Object)table.getSelectedRow());
						}
					}else{
						if(value == Boolean.FALSE){
							rows.remove((Object)table.getSelectedRow());
						}
					}
//					updateSelectionRows();
				}
        	}
        });
	}
	
	public List<Map<String,Object>> getSelectRowValue(int[] row){
		List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
		for(int i = 0; i < row.length; i++){
			Map<String,Object> rowdata = new HashMap<String,Object>();
			for(int j = 0; j< columnNames.size(); j++){
				rowdata.put(this.getColumnName(j), this.getValueAt(i, j));
			}
			datas.add(rowdata);
		}
		return datas;
	}
	
	public List<Map<String,Object>> getSelectRowValue(){
		 List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
		for(int i = 0; i < rows.size(); i++){
			Map<String,Object> rowdata = new HashMap<String,Object>();
			for(int j = 0; j< columnNames.size(); j++){
				if(this.getColumnModel().getColumn(j).getCellEditor() != null) {
					if(this.getColumnModel().getColumn(j).getCellEditor().getClass().getName().equals(ComboBoxEditor.class.getName())){
						ComboBoxEditor comboBoxEditor = (ComboBoxEditor) this.getCellEditor(i, j);
						rowdata.put(this.getColumnName(j),comboBoxEditor.getSelectedValue((String)this.getValueAt((int)rows.get(i), j)));
					}else{
						rowdata.put(this.getColumnName(j), this.getValueAt((int)rows.get(i), j));
					}
				}else{
					rowdata.put(this.getColumnName(j), this.getValueAt((int)rows.get(i), j));
				}
			}
			datas.add(rowdata);
		}
		return datas;
	}
	
	@SuppressWarnings("unchecked")
	public void addTableRow(boolean hasIndex){
		Vector<Object> obj = new Vector<Object>();
		obj.add(Boolean.FALSE);
		if(hasIndex){
			if(CollectionUtils.isNotEmpty(data)){
				Vector<Object> last = (Vector<Object>) data.get(data.size() -1);
				obj.add((int)last.get(1) + 1);
			}else{
				obj.add(1);
			}
			for(int i = 0;i<columnNames.size()-2;i++){
				obj.add("");
			}
		}else{
			for(int i = 0;i<columnNames.size() -1;i++){
				obj.add("");
			}
		}
		data.add(obj);
		updateTable();
	}
	
	public void initCombox(List<Integer> columns){
		int size = data.size();
		for(int i = 0 ; i < size; i++){
			if(CollectionUtils.isNotEmpty(columns)){
				for(int j = 0; j < columns.size(); j++){
					ComboBoxEditor myComboBoxEditor = (ComboBoxEditor) this.getColumnModel().getColumn(columns.get(j)).getCellEditor();
					myComboBoxEditor.setSelectedItem(this.getValueAt(i, columns.get(j)));
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void addTableRow(){
		Vector<Object> obj = new Vector<Object>();
		obj.add(Boolean.FALSE);
		if(CollectionUtils.isNotEmpty(data)){
			Vector<Object> last = (Vector<Object>) data.get(data.size() -1);
			obj.add((int)last.get(1) + 1);
		}else{
			obj.add(1);
		}
		for(int i = 0;i<columnNames.size()-2;i++){
			obj.add("");
		}
		data.add(obj);
		updateTable();
	}
	
	public void deleteTableRows(int[] row){
		ObjectUtils.sort(row);
		for(int i : row){
			defaultmodel.removeRow(i);
		}
	}
	
	public void updateTable(){
		defaultmodel.fireTableDataChanged();
	}
	
	public void updateSelectionRows(){
		
	}
	public void changeColumnWidth(int columnIndex,int width){
		TableColumn column = this.getColumnModel().getColumn(columnIndex);
		column.setPreferredWidth(width);
		column.setMinWidth(width);
		column.setMaxWidth(width);
	}
	
	public void setCellEditor(int columnIndex,DefaultCellEditor editor){
		TableColumn column = this.getColumnModel().getColumn(columnIndex);
		column.setCellEditor(editor);
	}
	
}
