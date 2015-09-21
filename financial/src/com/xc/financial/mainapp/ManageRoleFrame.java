package com.xc.financial.mainapp;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.xc.financial.beans.RoleBean;
import com.xc.financial.mapper.RoleMapper;
import com.xc.financial.utils.CollectionUtils;
import com.xc.financial.utils.DateUtils;
import com.xc.financial.utils.ObjectUtils;
import com.xc.financial.utils.StringUtils;
import com.xc.financial.tools.*;

public class ManageRoleFrame extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel idLabel,idFiled,nameLabel,status,createDate,createDateField,modifyDate,modifyDateField,comment,
						roleLabel,instock,outstock,financial,user;
	private JTextField nameField,commentField;
	private JCheckBox addInstock,editInstock,deleteInstock,addOutstock,editOutstock,deleteOutstock,
						addFinancial,editFinancial,deleteFinancial,addUser,editUser,deleteUser,changePsd;
	
	private JButton save,back;
	private static List<Map<String,Object>> str = new ArrayList<Map<String,Object>>();
	private RoleMapper roleMapper = new RoleMapper();
	
	private MyComboBox statusField;
	private MainFrame frame;
	
	public ManageRoleFrame(Integer roleId){
		this.setLayout(null);
		init();
		
		idLabel = new JLabel("编号：");
		idLabel.setFont(new Font("宋体", Font.BOLD, 13));
		idLabel.setBounds(new Rectangle(45,15,45,20));
		
		idFiled = new JLabel();
		idFiled.setFont(new Font("宋体", Font.PLAIN, 13));
		idFiled.setBounds(new Rectangle(90,15,80,20));
		
		nameLabel = new JLabel("名称：");
		nameLabel.setFont(new Font("宋体", Font.BOLD, 13));
		nameLabel.setBounds(new Rectangle(45,45,70,20));
		
		nameField = new JTextField();
		nameField.setFont(new Font("宋体", Font.PLAIN, 13));
		nameField.setBounds(new Rectangle(90,45,150,20));
		
		status = new JLabel("状态：");
		status.setFont(new Font("宋体", Font.BOLD, 13));
		status.setBounds(new Rectangle(45,75,70,20));
		
		statusField = new MyComboBox(str);
		statusField.setFont(new Font("宋体", Font.PLAIN, 13));
		statusField.setBounds(new Rectangle(90,75,100,20));
		statusField.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		comment = new JLabel("备注：");
		comment.setFont(new Font("宋体", Font.BOLD, 13));
		comment.setBounds(new Rectangle(45,105,45,20));
		
		commentField = new JTextField();
		commentField.setFont(new Font("宋体", Font.PLAIN, 13));
		commentField.setBounds(new Rectangle(90,105,150,20));
		
		createDate = new JLabel("创建时间：");
		createDate.setFont(new Font("宋体", Font.BOLD, 13));
		createDate.setBounds(new Rectangle(18,135,70,20));
		
		createDateField = new JLabel();
		createDateField.setFont(new Font("宋体", Font.PLAIN, 13));
		createDateField.setBounds(new Rectangle(90,135,150,20));
		
		modifyDate = new JLabel("修改时间：");
		modifyDate.setFont(new Font("宋体", Font.BOLD, 13));
		modifyDate.setBounds(new Rectangle(18,165,70,20));
		
		modifyDateField = new JLabel();
		modifyDateField.setFont(new Font("宋体", Font.PLAIN, 13));
		modifyDateField.setBounds(new Rectangle(90,165,150,20));
		
		roleLabel = new JLabel("角色管理：");
		roleLabel.setFont(new Font("宋体", Font.BOLD, 13));
		roleLabel.setBounds(new Rectangle(18,205,70,20));
		
		instock = new JLabel("收入管理：");
		instock.setFont(new Font("宋体", Font.BOLD, 13));
		instock.setBounds(new Rectangle(90,225,70,20));
		
		addInstock = new JCheckBox("新增收入单");
		addInstock.setFont(new Font("宋体", Font.PLAIN, 13));
		addInstock.setBounds(new Rectangle(160,225,100,20));
		addInstock.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		editInstock = new JCheckBox("编辑收入单");
		editInstock.setFont(new Font("宋体", Font.PLAIN, 13));
		editInstock.setBounds(new Rectangle(260,225,100,20));
		editInstock.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		deleteInstock = new JCheckBox("删除收入单");
		deleteInstock.setFont(new Font("宋体", Font.PLAIN, 13));
		deleteInstock.setBounds(new Rectangle(360,225,100,20));
		deleteInstock.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		outstock = new JLabel("消费管理：");
		outstock.setFont(new Font("宋体", Font.BOLD, 13));
		outstock.setBounds(new Rectangle(90,250,70,20));
		
		addOutstock = new JCheckBox("新增消费单");
		addOutstock.setFont(new Font("宋体", Font.PLAIN, 13));
		addOutstock.setBounds(new Rectangle(160,250,100,20));
		addOutstock.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		editOutstock = new JCheckBox("编辑消费单");
		editOutstock.setFont(new Font("宋体", Font.PLAIN, 13));
		editOutstock.setBounds(new Rectangle(260,250,100,20));
		editOutstock.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		deleteOutstock = new JCheckBox("删除消费单");
		deleteOutstock.setFont(new Font("宋体", Font.PLAIN, 13));
		deleteOutstock.setBounds(new Rectangle(360,250,100,20));
		deleteOutstock.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		financial = new JLabel("余额管理：");
		financial.setFont(new Font("宋体", Font.BOLD, 13));
		financial.setBounds(new Rectangle(90,275,70,20));
		
		addFinancial = new JCheckBox("新增余额");
		addFinancial.setFont(new Font("宋体", Font.PLAIN, 13));
		addFinancial.setBounds(new Rectangle(160,275,100,20));
		addFinancial.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		editFinancial = new JCheckBox("编辑余额");
		editFinancial.setFont(new Font("宋体", Font.PLAIN, 13));
		editFinancial.setBounds(new Rectangle(260,275,100,20));
		editFinancial.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		deleteFinancial = new JCheckBox("删除余额");
		deleteFinancial.setFont(new Font("宋体", Font.PLAIN, 13));
		deleteFinancial.setBounds(new Rectangle(360,275,100,20));
		deleteFinancial.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		user = new JLabel("用户管理：");
		user.setFont(new Font("宋体", Font.BOLD, 13));
		user.setBounds(new Rectangle(90,300,70,20));
		
		addUser = new JCheckBox("新增用户");
		addUser.setFont(new Font("宋体", Font.PLAIN, 13));
		addUser.setBounds(new Rectangle(160,300,100,20));
		addUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		editUser = new JCheckBox("编辑用户");
		editUser.setFont(new Font("宋体", Font.PLAIN, 13));
		editUser.setBounds(new Rectangle(260,300,100,20));
		editUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		deleteUser = new JCheckBox("删除用户");
		deleteUser.setFont(new Font("宋体", Font.PLAIN, 13));
		deleteUser.setBounds(new Rectangle(360,300,100,20));
		deleteUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		changePsd = new JCheckBox("修改密码");
		changePsd.setFont(new Font("宋体", Font.PLAIN, 13));
		changePsd.setBounds(new Rectangle(460,300,100,20));
		changePsd.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		save = new JButton("保存");
        save.setCursor(new Cursor(Cursor.HAND_CURSOR));
        save.setBounds(new Rectangle(90,350,60,30));
        back = new JButton("返回");
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.setBounds(new Rectangle(170,350,60,30));
        
        save.addActionListener(this);
        back.addActionListener(this);
		
		this.add(idLabel);
		this.add(idFiled);
		this.add(nameLabel);
		this.add(nameField);
		this.add(status);
		this.add(statusField);
		this.add(createDate);
		this.add(createDateField);
		this.add(modifyDate);
		this.add(modifyDateField);
		this.add(comment);
		this.add(commentField);
		this.add(roleLabel);
		
		this.add(instock);
		this.add(addInstock);
		this.add(editInstock);
		this.add(deleteInstock);
		
		this.add(outstock);
		this.add(addOutstock);
		this.add(editOutstock);
		this.add(deleteOutstock);
		
		this.add(financial);
		this.add(addFinancial);
		this.add(editFinancial);
		this.add(deleteFinancial);
		
		this.add(user);
		this.add(addUser);
		this.add(editUser);
		this.add(deleteUser);
		this.add(changePsd);
		
		this.add(save);
		this.add(back);
		getDatas(roleId);
	}
	
	private void init(){
		//初始化静态数据
		str.clear();
		
		Map<String,Object> item = new HashMap<String,Object>();
		item.put("label", "正常");
		item.put("value", "Y");
		str.add(item);
		
		Map<String,Object> item1 = new HashMap<String,Object>();
		item1.put("label", "不正常");
		item1.put("value", "N");
		str.add(item1);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == save){
			saveUpdateData();
			RoleFrame role = new RoleFrame();
			role.setFrame(frame);
			frame.changeFrame(role);
		}
		if(e.getSource() == back){
			RoleFrame role = new RoleFrame();
			role.setFrame(frame);
			frame.changeFrame(role);
		}
	}
	
	public void setFrame(MainFrame frame){
		this.frame = frame;
	}
	
	public MainFrame getFrame(){
		return frame;
	}
	
	private RoleBean prepareRoleBean(){
		RoleBean roleBean = new RoleBean();
		try{
			roleBean.setId(StringUtils.isEmpty(idFiled.getText()) ? null : Integer.parseInt(idFiled.getText()));
			roleBean.setName(nameField.getText());
			roleBean.setStatus(statusField.getSelectedItemValue().toString());
			roleBean.setCreateDate(DateUtils.parseLongDate(new Date()));
			roleBean.setModifyDate(DateUtils.parseLongDate(new Date()));
			roleBean.setOperate(null);
			roleBean.setComments(commentField.getText());
			List<String> authorities = new ArrayList<String>();
			if(addInstock.isSelected()){
				authorities.add("addInstock");
			}
			if(editInstock.isSelected()){
				authorities.add("editInstock");
			}
			if(deleteInstock.isSelected()){
				authorities.add("deleteInstock");
			}
			if(addOutstock.isSelected()){
				authorities.add("addOutstock");
			}
			if(editOutstock.isSelected()){
				authorities.add("editOutstock");
			}
			if(deleteOutstock.isSelected()){
				authorities.add("deleteOutstock");
			}
			if(addFinancial.isSelected()){
				authorities.add("addFinancial");
			}
			if(editFinancial.isSelected()){
				authorities.add("editFinancial");
			}
			if(deleteFinancial.isSelected()){
				authorities.add("deleteFinancial");
			}
			if(addUser.isSelected()){
				authorities.add("addUser");
			}
			if(editUser.isSelected()){
				authorities.add("editUser");
			}
			if(deleteUser.isSelected()){
				authorities.add("deleteUser");
			}
			if(changePsd.isSelected()){
				authorities.add("changePsd");
			}
			roleBean.setAuthorities(authorities);
		}catch(Exception e){
			e.printStackTrace();
		}
		return roleBean;
	}
	
	private void saveUpdateData(){
		RoleBean roleBean = prepareRoleBean();
		if(ObjectUtils.isNotEmpty(roleBean)){
			if(null == roleBean.getId()){
				roleMapper.insertRole(roleBean);
				JOptionPane.showMessageDialog(null, "添加成功！");
			}else{
				roleMapper.updateRole(roleBean);
				JOptionPane.showMessageDialog(null, "更新成功！");
			}
		}else{
			JOptionPane.showMessageDialog(null, "请填必填项！");
		}
	}
	
	private void getDatas(Integer roleId){
		RoleBean roleBean = roleMapper.selectRolesById(roleId);
		if(ObjectUtils.isNotEmpty(roleBean)){
			initRoleFrameData(roleBean);
		}else{
			clearData();
		}
	}
	
	private void initRoleFrameData(RoleBean roleBean){
		idFiled.setText(roleBean.getId().toString());
		nameField.setText(roleBean.getName());
		statusField.setSelectItem(roleBean.getStatus());
		commentField.setText(roleBean.getComments());
		createDateField.setText(roleBean.getCreateDate());
		modifyDateField.setText(roleBean.getModifyDate());
		if(CollectionUtils.isNotEmpty(roleBean.getAuthorities())){
			for(String authority:roleBean.getAuthorities()){
				if(authority.equals("addInstock")){
					addInstock.setSelected(true);
				}
				if(authority.equals("editInstock")){
					editInstock.setSelected(true);
				}
				if(authority.equals("deleteInstock")){
					deleteInstock.setSelected(true);
				}
				if(authority.equals("addOutstock")){
					addOutstock.setSelected(true);
				}
				if(authority.equals("editOutstock")){
					editOutstock.setSelected(true);
				}
				if(authority.equals("deleteOutstock")){
					deleteOutstock.setSelected(true);
				}
				if(authority.equals("addFinancial")){
					addFinancial.setSelected(true);
				}
				if(authority.equals("editFinancial")){
					editFinancial.setSelected(true);
				}
				if(authority.equals("deleteFinancial")){
					deleteFinancial.setSelected(true);
				}
				if(authority.equals("addUser")){
					addUser.setSelected(true);
				}
				if(authority.equals("editUser")){
					editUser.setSelected(true);
				}
				if(authority.equals("deleteUser")){
					deleteUser.setSelected(true);
				}
				if(authority.equals("changePsd")){
					changePsd.setSelected(true);
				}
			}
		}
	}
	
	private void clearData(){
		try {
			idFiled.setText(null);
			nameField.setText(null);
			statusField.setSelectedIndex(0);
			commentField.setText(null);
			createDateField.setText(DateUtils.parseLongDate(new Date()));
			modifyDateField.setText(DateUtils.parseLongDate(new Date()));
			addInstock.setSelected(false);
			editInstock.setSelected(false);
			deleteInstock.setSelected(false);
			addOutstock.setSelected(false);
			editOutstock.setSelected(false);
			deleteOutstock.setSelected(false);
			addFinancial.setSelected(false);
			editFinancial.setSelected(false);
			deleteFinancial.setSelected(false);
			addUser.setSelected(false);
			editUser.setSelected(false);
			deleteUser.setSelected(false);
			changePsd.setSelected(false);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] arg0){
		JFrame frame = new JFrame();
		ManageRoleFrame panel = new ManageRoleFrame(null);
		frame.add(panel);
		
		frame.setSize(680, 570);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
