package com.xc.financial.mainapp;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
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
import com.xc.financial.beans.InstockBean;
import com.xc.financial.beans.InstockSearchBean;
import com.xc.financial.beans.UserBean;
import com.xc.financial.enums.CodeDictEnum;
import com.xc.financial.enums.SnTypeEnum;
import com.xc.financial.enums.column.InstockColumnEnum;
import com.xc.financial.mapper.CodeDictMapper;
import com.xc.financial.mapper.InstockMapper;
import com.xc.financial.mapper.SnMapper;
import com.xc.financial.mapper.UserMapper;
import com.xc.financial.utils.CollectionUtils;
import com.xc.financial.utils.DateUtils;
import com.xc.financial.utils.NumberFormatUtils;
import com.xc.financial.utils.ObjectUtils;
import com.xc.financial.utils.StringUtils;

public class InstockFrame extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Table table;
	private Vector<Object> columns = new Vector<Object>();
	private String[] columnNames= {"","序号","编码","家庭成员","收入类型","金额(元)","存储类型","创建时间","修改时间","操作员"}; 
	private Vector<Object> rowData = new Vector<Object>();
	private JScrollPane pane3;
	private JButton button,save,delete,search;
	private JLabel label,label1,label2,label3,label4;
	private MyComboBox type,store;
	private JTextField field,startDate,endDate;
	private DatePicker datepicker,datepicker1;
	private List<Map<String,Object>> str = new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> storeType = new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> userList = new ArrayList<Map<String,Object>>();
	private InstockMapper instockMapper = new InstockMapper();
	private CodeDictMapper codeDictMapper = new CodeDictMapper();
	private SnMapper snMapper = new SnMapper();
	private UserMapper userMapper = new UserMapper();
	private PageToolBar<InstockFrame> pageTool;
	private Integer totalNumber;
	
	public InstockFrame(){
		this.setLayout(null);
		init();
		
		label = new JLabel("编码：");
		label.setFont(new Font("宋体", Font.PLAIN, 13));
		label.setBounds(new Rectangle(5,15,50,20));
		
		field = new JTextField();
		field.setBounds(new Rectangle(45,15,120,20));
		
		label3 = new JLabel("收入类型：");
		label3.setFont(new Font("宋体", Font.PLAIN, 13));
		label3.setBounds(new Rectangle(185,15,80,20));
		
		type = new MyComboBox(str);
		type.setFont(new Font("宋体", Font.PLAIN, 13));
		type.setBounds(new Rectangle(250,15,90,20));
		type.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		label4 = new JLabel("存储类型：");
		label4.setFont(new Font("宋体", Font.PLAIN, 13));
		label4.setBounds(new Rectangle(360,15,80,20));
		
		store = new MyComboBox(storeType);
		store.setFont(new Font("宋体", Font.PLAIN, 13));
		store.setBounds(new Rectangle(430,15,90,20));
		store.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		label1 = new JLabel("开始日期：");
		label1.setFont(new Font("宋体", Font.PLAIN, 13));
		label1.setBounds(new Rectangle(5,45,80,20));
		
		startDate = new JTextField();
		startDate.setBounds(new Rectangle(75,45,175,20));
		datepicker = DatePicker.getInstance("yyyy-MM-dd");
		datepicker.register(startDate);
		
		label2 = new JLabel("结束日期：");
		label2.setFont(new Font("宋体", Font.PLAIN, 13));
		label2.setBounds(new Rectangle(265,45,80,20));
		
		endDate = new JTextField();
		endDate.setBounds(new Rectangle(335,45,175,20));
		datepicker1 = DatePicker.getInstance("yyyy-MM-dd");
		datepicker1.register(endDate);
		
		search = new JButton("搜索");
		search.setBounds(new Rectangle(530,45,60,20));
		search.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		table = new Table(rowData, columns){
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if(column == 0 || column == 3 || column == 4 || column == 5 || column == 6){
					return true;
				}else{
					return false;
				}
            }
		};
		table.setPreferredScrollableViewportSize(new Dimension(670, 390));
		
		table.isCellEditable(0,2);
		//设置第一列的宽度
		table.changeColumnWidth(0, 20);
		//设置第二列的宽度
		table.changeColumnWidth(1, 50);
		//设置第3列的宽度
		table.changeColumnWidth(2, 120);
		//设置第4列的宽度
		table.changeColumnWidth(3, 100);
		//设置第5列的宽度
		table.changeColumnWidth(4, 100);
		//设置第6列的宽度
		table.changeColumnWidth(5, 100);
		//设置第6列的宽度
		table.changeColumnWidth(6, 100);
		//设置第7列的宽度
		table.changeColumnWidth(7, 150);
		//设置第8列的宽度
		table.changeColumnWidth(8, 150);
		//设置第9列的宽度
		table.changeColumnWidth(9, 100);
		
		table.setCellEditor(3, new ComboBoxEditor(userList));
		
		table.setCellEditor(4, new ComboBoxEditor(str));
		
		table.setCellEditor(6, new ComboBoxEditor(storeType));
		
		table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		
		List<Integer> columns = new ArrayList<Integer>();
		columns.add(4);
		columns.add(6);
		table.initCombox(columns);
		table.setOpaque(false);
		
        pane3 = new JScrollPane(table);
        pane3.setOpaque(false);
        pane3.getViewport().setOpaque(false);
        pane3.setBounds(new Rectangle(2,80,670,420));
        
        pane3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() != datepicker){
					datepicker.hidePanel();
				}
			}
		});
        
        pageTool = new PageToolBar<InstockFrame>(this,totalNumber);
        pageTool.setBounds(new Rectangle(668-pageTool.getPanelLength(),455,pageTool.getPanelLength()-3,22));
        
        button = new JButton("添加");
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
        this.add(label1);
        this.add(startDate);
        this.add(label2);
        this.add(endDate);
        this.add(label3);
        this.add(type);
        this.add(label4);
        this.add(store);
        this.add(search);
        
        this.add(pageTool);
        this.add(pane3);
        this.add(button);
        this.add(save);
        this.add(delete);
        
        this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() != datepicker || e.getSource() != datepicker1){
					datepicker.hidePanel();
					datepicker1.hidePanel();
				}
			}
		});
		this.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == search){
			search(new Long(1),new Long(15));
		}
		if(e.getSource() == button){
			table.addTableRow();
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
		str.clear();
		Map<String,Object> black = new HashMap<String,Object>();
		black.put("label", "");
		black.put("value", null);
		str.add(black);
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("type", CodeDictEnum.INSTOCK.getKey());
		params.put("value", "收入类型");
		
		List<CodeDictBean> codeDictList = codeDictMapper.selectChildrenByParams(params);
		if(CollectionUtils.isNotEmpty(codeDictList)){
			for(CodeDictBean codeDictBean : codeDictList){
				Map<String,Object> item = new HashMap<String,Object>();
				item.put("label", codeDictBean.getValue());
				item.put("value", codeDictBean.getId());
				str.add(item);
			}
		}
		storeType.clear();
		storeType.add(black);
		
		params = new HashMap<String,Object>();
		params.put("type", CodeDictEnum.FINANCIAL.getKey());
		params.put("value", "存储类型");
		
		List<CodeDictBean> storeTypeList = codeDictMapper.selectChildrenByParams(params);
		if(CollectionUtils.isNotEmpty(storeTypeList)){
			for(CodeDictBean codeDictBean : storeTypeList){
				Map<String,Object> item = new HashMap<String,Object>();
				item.put("label", codeDictBean.getValue());
				item.put("value", codeDictBean.getId());
				storeType.add(item);
			}
		}
		
		userList.clear();
		userList.add(black);
		List<UserBean> userBeanList  = userMapper.selectUserList();
		if(CollectionUtils.isNotEmpty(userBeanList)){
			for(UserBean userBean : userBeanList){
				Map<String,Object> item = new HashMap<String,Object>();
				item.put("label", userBean.getUsername());
				item.put("value", userBean.getId());
				userList.add(item);
			}
		}
		InstockSearchBean searchBean = new InstockSearchBean();
		searchBean.setPageNumber(new Long(1));
		searchBean.setPageSize(new Long(15));
		getDatas(searchBean);
	}
	
	private void saveUpdateData(){
		List<Map<String, Object>> datas = table.getSelectRowValue();
		if(CollectionUtils.isNotEmpty(datas)){
			for(Map<String, Object> data : datas){
				try {
					if(StringUtils.isEmpty((String)data.get(InstockColumnEnum.getInstockColumnValueByKey("code").getValue()))){
						int num = snMapper.selectSn(SnTypeEnum.INSTOCK_CODE.getKey());
						data.put(InstockColumnEnum.getInstockColumnValueByKey("code").getValue(),"F" + DateUtils.parseDates(new Date()) + NumberFormatUtils.formatNumber(num));
						instockMapper.insertInstock(data);
					}else{
						instockMapper.updateInstock(data);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			InstockSearchBean searchBean = new InstockSearchBean();
			searchBean.setPageNumber(new Long(1));
			searchBean.setPageSize(new Long(15));
			getDatas(searchBean);
		}else{
			JOptionPane.showMessageDialog(null, "请先选择需要保存的数据！");
		}
	}
	
	private void getDatas(InstockSearchBean searchBean){
		totalNumber = instockMapper.getCount(searchBean);
		if(null != pageTool){
			pageTool.setTotalNumber(totalNumber);
			pageTool.setBounds(new Rectangle(668-pageTool.getPanelLength(),455,pageTool.getPanelLength()-3,22));
		}
		List<InstockBean> instockBeanList = instockMapper.selectInstocksByParams(searchBean);
		if(CollectionUtils.isNotEmpty(instockBeanList)){
			rowData.clear();
			for(InstockBean instockBean:instockBeanList){
				rowData.add(buildVectorData(instockBean));
			}
		}else{
			rowData.clear();
		}
		if(table != null){
			table.updateTable();
		}
	}
	
	private void search(long pageNumber,long pageSize){
		InstockSearchBean searchBean = new InstockSearchBean();
		searchBean.setCode(field.getText());
		searchBean.setStartDate(startDate.getText());
		searchBean.setEndDate(endDate.getText());
		searchBean.setType(StringUtils.isEmpty(type.getSelectedItemValue()) ? null : type.getSelectedItemValue().toString());
		searchBean.setStoreType(StringUtils.isEmpty(store.getSelectedItemValue()) ? null : (Integer)store.getSelectedItemValue());
		searchBean.setPageNumber(pageNumber);
		searchBean.setPageSize(pageSize);
		getDatas(searchBean);
	}
	
	private Vector<Object> buildVectorData(InstockBean instockBean){
		try {
			return ObjectUtils.createNewVecto(instockBean);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		JPanel panel = new InstockFrame();
		panel.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(panel,BorderLayout.CENTER);
	    frame.setSize(680, 570);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    frame.setVisible(true);
	}
}
