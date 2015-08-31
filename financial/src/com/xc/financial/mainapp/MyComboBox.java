package com.xc.financial.mainapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class MyComboBox extends JComboBox<Object>{
	
	List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
	
	private String[] str = {};

	private static final long serialVersionUID = 1L;
	
	public MyComboBox(List<Map<String,Object>> data){
		this.data = data;
		init();
		DefaultComboBoxModel<Object> aModel = new DefaultComboBoxModel<Object>(str);
		this.setModel(aModel);
	}
	
	@Override
	public Object getSelectedItem(){
		if(this.getSelectedIndex() == -1){
			return null;
		}
		String str = data.get(this.getSelectedIndex()).get("label").toString();
		if(str.contains("-")){
			return str.substring(str.lastIndexOf("- ") + 1,str.length()).trim();
		}else{
			return str;
		}
	}
	
	public Object getSelectedItemValue(){
		if(this.getSelectedIndex() == -1){
			return null;
		}
		return data.get(this.getSelectedIndex()).get("value");
	}
	
	public void init(){
		str = new String[data.size()];
		int i = 0; 
		for(Map<String,Object> item : data){
			str[i] = item.get("label").toString();
			i++;
		}
	}

}
