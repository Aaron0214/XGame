package com.xc.financial.mainapp;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.xc.financial.beans.CodeDictBean;
import com.xc.financial.beans.SearchBean;
import com.xc.financial.enums.column.CodeDictColumnEnum;
import com.xc.financial.mapper.CodeDictMapper;
import com.xc.financial.utils.CollectionUtils;
import com.xc.financial.utils.ObjectUtils;
import com.xc.financial.utils.StringUtils;
import com.xc.financial.tools.*;

public class CodeDictFrame extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Table table;
	private Vector<Object> columns = new Vector<Object>();
	private String[] columnNames= {"","编号","分类","内容","上级分类"}; 
	private Vector<Object> rowData = new Vector<Object>();
	private JScrollPane pane3;
	private JButton button,save,delete,search;
	private JLabel label,label3;
	private MyComboBox type;
	private JTextField field;
	private static List<Map<String,Object>> str = new ArrayList<Map<String,Object>>();
	private static List<Map<String,Object>> parent = new ArrayList<Map<String,Object>>();
	private CodeDictMapper codeDictMapper = new CodeDictMapper();
	private PageToolBar<CodeDictFrame> pageTool;
	private Integer totalNumber;
	
	public CodeDictFrame(){
		this.setLayout(null);
		init();
		
		label = new JLabel("编号：");
		label.setFont(new Font("宋体", Font.PLAIN, 13));
		label.setBounds(new Rectangle(5,15,50,20));
		
		field = new JTextField();
		field.setBounds(new Rectangle(45,15,120,20));
		
		label3 = new JLabel("分类：");
		label3.setFont(new Font("宋体", Font.PLAIN, 13));
		label3.setBounds(new Rectangle(185,15,80,20));
		
		type = new MyComboBox(str);
		type.setFont(new Font("宋体", Font.PLAIN, 13));
		type.setBounds(new Rectangle(250,15,90,20));
		type.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		search = new JButton("搜索");
		search.setCursor(new Cursor(Cursor.HAND_CURSOR));
		search.setBounds(new Rectangle(360,15,80,20));
		
		
		table = new Table(rowData, columns){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if(column == 1){
					return false;
				}else{
					return true;
				}
            }
		};
		table.setPreferredScrollableViewportSize(new Dimension(670, 450));
		
		table.isCellEditable(0,1);
		//设置第一列的宽度
		table.changeColumnWidth(0, 30);
		//设置第二列的宽度
		table.changeColumnWidth(1, 150);
		//设置第3列的宽度
		table.changeColumnWidth(2, 150);
		//设置第4列的宽度
		table.changeColumnWidth(3, 190);
		//设置第5列的宽度
		table.changeColumnWidth(4, 150);
		
		table.setCellEditor(2, new ComboBoxEditor(str));
		
		table.setCellEditor(4, new ComboBoxEditor(parent));
		
		table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		
		List<Integer> columns = new ArrayList<Integer>();
		columns.add(2);
		columns.add(4);
		table.initCombox(columns);
		table.setOpaque(false);
		
        pane3 = new JScrollPane(table);
        pane3.setOpaque(false);
        pane3.getViewport().setOpaque(false);
        pane3.setBounds(new Rectangle(2,50,670,450));
        
        pageTool = new PageToolBar<CodeDictFrame>(this,totalNumber);
        pageTool.setBounds(new Rectangle(668-pageTool.getPanelLength(),455,pageTool.getPanelLength()-3,22));
        
        button = new JButton("添加");
        button.setBounds(new Rectangle(240,508,60,25));
        save = new JButton("保存");
        save.setCursor(new Cursor(Cursor.HAND_CURSOR));
        save.setBounds(new Rectangle(310,508,60,25));
        delete = new JButton("删除");
        delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        delete.setBounds(new Rectangle(380,508,60,25));
        
        search.addActionListener(this);
        button.addActionListener(this);
        save.addActionListener(this);
        delete.addActionListener(this);
        
        this.add(label);
        this.add(field);
        this.add(label3);
        this.add(type);
        this.add(search);
        
        this.add(pageTool);
        this.add(pane3);
        this.add(button);
        this.add(save);
        this.add(delete);
        
		this.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == search){
			search(new Long(1),new Long(15));
			pageTool.setSelectedValue(15);
		}
		if(e.getSource() == button){
			table.addTableRow(false);
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
		
		//初始化静态数据
		Map<String,Object> black = new HashMap<String,Object>();
		black.put("label", "");
		black.put("value", null);
		str.add(black);
		
		Map<String,Object> item3 = new HashMap<String,Object>();
		item3.put("label", "区域");
		item3.put("value", 0);
		str.add(item3);
		
		Map<String,Object> item = new HashMap<String,Object>();
		item.put("label", "收入");
		item.put("value", 1);
		str.add(item);
		
		Map<String,Object> item1 = new HashMap<String,Object>();
		item1.put("label", "支出");
		item1.put("value", 2);
		str.add(item1);
		
		Map<String,Object> item2 = new HashMap<String,Object>();
		item2.put("label", "余额");
		item2.put("value", 3);
		str.add(item2);
		
		parent.add(black);
		
		SearchBean searchBean = new SearchBean();
		searchBean.setPageNumber(new Long(1));
		searchBean.setPageSize(new Long(15));
		getDatas(searchBean);
	}
	
	private void saveUpdateData(){
		List<Map<String, Object>> datas = table.getSelectRowValue();
		if(CollectionUtils.isNotEmpty(datas)){
			for(Map<String, Object> data : datas){
				try {
					if(StringUtils.isEmpty((Object)data.get(CodeDictColumnEnum.getCodeDictColumnValueByKey("id").getValue()))){
						codeDictMapper.insertCodeDict(data);
					}else{
						codeDictMapper.updateCodeDict(data);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			SearchBean searchBean = new SearchBean();
			searchBean.setPageNumber(new Long(1));
			searchBean.setPageSize(new Long(15));
			getDatas(searchBean);
		}else{
			JOptionPane.showMessageDialog(null, "请先选择需要保存的数据！");
		}
	}
	
	private void getDatas(SearchBean searchBean){
		totalNumber = codeDictMapper.getCount(searchBean);
		if(null != pageTool){
			pageTool.setTotalNumber(totalNumber);
			pageTool.setBounds(new Rectangle(668-pageTool.getPanelLength(),455,pageTool.getPanelLength()-3,22));
		}
		List<CodeDictBean> codeDictBeanList = codeDictMapper.selectCodeDictsByParams(searchBean);
		if(CollectionUtils.isNotEmpty(codeDictBeanList)){
			rowData.clear();
			parent.clear();
			Map<String,Object> black = new HashMap<String,Object>();
			black.put("label", "");
			black.put("value", null);
			parent.add(black);
			for(CodeDictBean codeDictBean : codeDictBeanList){
				rowData.add(buildVectorData(codeDictBean));
				Map<String,Object> item = new HashMap<String,Object>();
				item.put("label", codeDictBean.getId() + " - " + codeDictBean.getValue());
				item.put("value", codeDictBean.getId());
				parent.add(item);
			}
		}else{
			rowData.clear();
		}
		if(table != null){
			table.updateTable();
			table.getColumnModel().getColumn(4).setCellEditor(new ComboBoxEditor(parent));
			table.rows.clear();
		}
	}
	
	private void search(long pageNumber,long pageSize){
		SearchBean searchBean = new SearchBean();
		searchBean.setId(StringUtils.isEmpty(field.getText()) ? null : Integer.parseInt(field.getText()));
		searchBean.setType(type.getSelectedItemValue() == null ? null : type.getSelectedItemValue() .toString());
		searchBean.setPageNumber(pageNumber);
		searchBean.setPageSize(pageSize);
		getDatas(searchBean);
	}
	
	private Vector<Object> buildVectorData(CodeDictBean codeDictBean){
		try {
			return ObjectUtils.createNewVecto(codeDictBean);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setTitle("配置项管理界面");
		JPanel panel = new CodeDictFrame();
		panel.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(panel,BorderLayout.CENTER);
	    frame.setSize(680, 570);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    frame.setVisible(true);
	}
}
