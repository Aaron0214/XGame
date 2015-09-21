package com.xc.financial.mainapp;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import com.xc.financial.beans.RoleOperateBean;
import com.xc.financial.beans.SearchBean;
import com.xc.financial.enums.column.RoleColumnEnum;
import com.xc.financial.mapper.RoleMapper;
import com.xc.financial.utils.CollectionUtils;
import com.xc.financial.utils.ObjectUtils;
import com.xc.financial.utils.StringUtils;
import com.xc.financial.tools.*;

public class RoleFrame extends JPanel implements ActionListener{

	/**
	 * 
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Table table;
	private Vector<Object> columns = new Vector<Object>();
	private String[] columnNames= {"","编号","角色名称","状态","创建时间","修改时间","备注","操作员"}; 
	private Vector<Object> rowData = new Vector<Object>();
	private JScrollPane pane3;
	private JButton button,edit,delete,search;
	private JLabel label,label1,label2,label3;
	private MyComboBox type;
	private JTextField field,startDate,endDate;
	private DatePicker datepicker,datepicker1;
	private static List<Map<String,Object>> str = new ArrayList<Map<String,Object>>();
	private RoleMapper roleMapper = new RoleMapper();
	private PageToolBar<RoleFrame> pageTool;
	private Integer totalNumber;
	private MainFrame frame;
	
	public RoleFrame(){
		this.setLayout(null);
		init();
		
		label = new JLabel("编号：");
		label.setFont(new Font("宋体", Font.PLAIN, 13));
		label.setBounds(new Rectangle(5,15,50,20));
		
		field = new JTextField();
		field.setBounds(new Rectangle(45,15,100,20));
		
		label3 = new JLabel("状态：");
		label3.setFont(new Font("宋体", Font.PLAIN, 13));
		label3.setBounds(new Rectangle(180,15,80,20));
		
		type = new MyComboBox(str);
		type.setFont(new Font("宋体", Font.PLAIN, 13));
		type.setBounds(new Rectangle(220,15,80,20));
		type.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		label1 = new JLabel("开始日期：");
		label1.setFont(new Font("宋体", Font.PLAIN, 13));
		label1.setBounds(new Rectangle(330,15,80,20));
		
		startDate = new JTextField();
		startDate.setBounds(new Rectangle(395,15,175,20));
		datepicker = DatePicker.getInstance("yyyy-MM-dd");
		datepicker.register(startDate);
		
		
		label2 = new JLabel("结束日期：");
		label2.setFont(new Font("宋体", Font.PLAIN, 13));
		label2.setBounds(new Rectangle(5,45,80,20));
		
		endDate = new JTextField();
		endDate.setBounds(new Rectangle(70,45,175,20));
		datepicker1 = DatePicker.getInstance("yyyy-MM-dd");
		datepicker1.register(endDate);
		
		search = new JButton("搜索");
		search.setBounds(new Rectangle(270,45,60,20));
		search.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		table = new Table(rowData, columns){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				if(column == 0){
					return true;
				}else{
					return false;
				}
            }
		};
		table.setPreferredScrollableViewportSize(new Dimension(670, 420));
		
		
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
		table.changeColumnWidth(4, 150);
		//设置第6列的宽度
		table.changeColumnWidth(5, 150);
		//设置第7列的宽度
		table.changeColumnWidth(6, 150);
		//设置第8列的宽度
		table.changeColumnWidth(7, 100);
		
		table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		
		table.setOpaque(false);
		
        pane3 = new JScrollPane(table);
        pane3.setOpaque(false);
        pane3.getViewport().setOpaque(false);
        pane3.setBounds(new Rectangle(2,80,670,420));
        
        pane3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() != datepicker || e.getSource() != datepicker1){
					datepicker.hidePanel();
					datepicker1.hidePanel();
				}
			}
		});
        
        pageTool = new PageToolBar<RoleFrame>(this,totalNumber);
        pageTool.setBounds(new Rectangle(668-pageTool.getPanelLength(),455,pageTool.getPanelLength()-3,22));
        
        button = new JButton("添加");
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBounds(new Rectangle(240,508,60,25));
        
        edit = new JButton("编辑");
        edit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        edit.setBounds(new Rectangle(310,508,60,25));
        
        delete = new JButton("删除");
        delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        delete.setBounds(new Rectangle(380,508,60,25));
        
        search.addActionListener(this);
        button.addActionListener(this);
        edit.addActionListener(this);
        delete.addActionListener(this);
        
        this.add(label);
        this.add(field);
        this.add(label3);
        this.add(type);
        this.add(label1);
        this.add(startDate);
        this.add(label2);
        this.add(endDate);
        this.add(search);
        
        this.add(pageTool);
        this.add(pane3);
        this.add(button);
        this.add(edit);
        this.add(delete);
        
        this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() != datepicker || e.getSource() != datepicker1){
					datepicker.hidePanel();
					datepicker1.hidePanel();
				}
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == search){
			search(new Long(1),new Long(15));
		}
		if(e.getSource() == button){
			ManageRoleFrame manageRole = new ManageRoleFrame(null);
			manageRole.setFrame(frame);
			frame.changeFrame(manageRole);
		}
		if(e.getSource() == edit){
			List<Map<String, Object>> datas = table.getSelectRowValue();
			if(CollectionUtils.isNotEmpty(datas)){
				if(datas.size() > 1){
					JOptionPane.showMessageDialog(null, "请选择一条数据进行编辑！");
				}else{
					Map<String, Object> data = datas.get(0);
					ManageRoleFrame manageRole = new  ManageRoleFrame((Integer)data.get(RoleColumnEnum.getRoleColumnValueByKey("id").getValue()));
					manageRole.setFrame(frame);
					frame.changeFrame(manageRole);
				}
			}else{
				JOptionPane.showMessageDialog(null, "请先选择一行需要编辑的数据！");
			}
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
		
		Map<String,Object> item = new HashMap<String,Object>();
		item.put("label", "正常");
		item.put("value", "Y");
		str.add(item);
		
		Map<String,Object> item1 = new HashMap<String,Object>();
		item1.put("label", "不正常");
		item1.put("value", "N");
		str.add(item1);
		
		SearchBean searchBean = new SearchBean();
		searchBean.setPageNumber(new Long(1));
		searchBean.setPageSize(new Long(15));
		getDatas(searchBean);
	}
	
	
	private void getDatas(SearchBean searchBean){
		totalNumber = roleMapper.getCount(searchBean);
		if(null != pageTool){
			pageTool.setTotalNumber(totalNumber);
			pageTool.setBounds(new Rectangle(668-pageTool.getPanelLength(),455,pageTool.getPanelLength()-3,22));
		}
		List<RoleOperateBean> roleBeanList = roleMapper.selectRolesByParams(searchBean);
		if(CollectionUtils.isNotEmpty(roleBeanList)){
			rowData.clear();
			for(RoleOperateBean roleBean : roleBeanList){
				rowData.add(buildVectorData(roleBean));
			}
		}else{
			rowData.clear();
		}
		if(table != null){
			table.updateTable();
		}
	}
	
	private void search(long pageNumber,long pageSize){
		SearchBean searchBean = new SearchBean();
		searchBean.setId(StringUtils.isEmpty(field.getText())?null:Integer.parseInt(field.getText()));
		searchBean.setStartDate(startDate.getText());
		searchBean.setEndDate(endDate.getText());
		searchBean.setStatus(StringUtils.isEmpty(type.getSelectedItemValue()) ? null : type.getSelectedItemValue().toString());
		searchBean.setPageNumber(pageNumber);
		searchBean.setPageSize(pageSize);
		getDatas(searchBean);
	}
	
	private Vector<Object> buildVectorData(RoleOperateBean roleBean){
		try {
			return ObjectUtils.createNewVecto(roleBean);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void setFrame(MainFrame frame){
		this.frame = frame;
	}
	
	public MainFrame getFrame(){
		return frame;
	}
	
	public static void main(String[] arg0){
		JFrame frame = new JFrame();
		RoleFrame panel = new RoleFrame();
		frame.add(panel);
		frame.setSize(680, 570);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
