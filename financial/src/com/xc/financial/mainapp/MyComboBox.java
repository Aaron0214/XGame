package com.xc.financial.mainapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.xc.financial.utils.CollectionUtils;
import com.xc.financial.utils.StringUtils;

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
	
	public Object getSelectedValue(String label){
		if(StringUtils.isNotEmpty(label)){
			Object value = null;
			for(Map<String,Object> item : data){
				if(label.equals(item.get("label"))){
					value = item.get("value");
					break;
				}
			}
			return value;
		}else{
			return null;
		}
	}
	
	public void init(){
		str = new String[data.size()];
		int i = 0; 
		for(Map<String,Object> item : data){
			str[i] = item.get("label").toString();
			i++;
		}
	}
	
	public void refresh(){
		this.removeAllItems();
		if(CollectionUtils.isNotEmpty(data)){
			for(Map<String,Object> item : data){
				this.addItem(item.get("label").toString());
			}
		}
	}
	
	public void addItem(Map<String,Object> item){
		data.add(item);
		this.addItem(item.get("label"));
	}
	
	public void setSelectItem(Object item){
		if(null == item){
			this.setSelectedIndex(0);
		}else{
			for(Map<String,Object> items : data){
				if(item.equals(items.get("value"))){
					this.setSelectedItem(items.get("label"));
					break;
				}else if(item.equals(items.get("label"))){
					this.setSelectedItem(items.get("label"));
					break;
				}
			}
		}
	}

}
