package com.xc.financial.tools;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

public class MyLabel extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String,Object> data = new HashMap<String,Object>();
	
	public MyLabel(Map<String,Object> data){
		this.data = data;
		this.setText("    " + (String)data.get("label"));
	}
	
	public Object getValue(){
		return data.get("value");
	}

}
