package com.xc.financial.mainapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;

public class ComboBoxEditor extends DefaultCellEditor {
	private static final long serialVersionUID = 1L;
	
	private List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
	
	public ComboBoxEditor(List<Map<String,Object>> data) {
		super(new MyComboBox(data));
		this.data = data;
	}
	
	public Object getSelectedItemValue(){
		return ((MyComboBox)this.getComponent()).getSelectedItemValue();
	}
	
	public void setSelectedItem(Object value){
		int index = 0;
		for (int i=0;i< data.size();i++){
			String str = data.get(i).get("label").toString();
			if(str.contains("-")){
				if(str.substring(str.lastIndexOf("- ") + 1,str.length()).trim().equals(value)){
					index = i;
					break;
				}
			}else{
				if(str.equals(value)){
					index = i;
					break;
				}
			}
		}
		((MyComboBox)this.getComponent()).setSelectedIndex(index);
	}
 }
