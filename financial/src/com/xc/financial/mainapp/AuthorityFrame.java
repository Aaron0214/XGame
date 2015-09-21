package com.xc.financial.mainapp;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.xc.financial.beans.RoleBean;
import com.xc.financial.beans.SearchBean;
import com.xc.financial.beans.UserBean;
import com.xc.financial.beans.UserRoleBean;
import com.xc.financial.enums.StatusEnum;
import com.xc.financial.mapper.RoleMapper;
import com.xc.financial.mapper.UserMapper;
import com.xc.financial.mapper.UserRoleMapper;
import com.xc.financial.tools.MyComboBox;
import com.xc.financial.tools.MyLabel;
import com.xc.financial.utils.CollectionUtils;

public class AuthorityFrame extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button,button1,button2,button3,save;
	private JPanel panel,panel1;
	private JLabel label,label1,label2;
	private Map<String,List<Map<String,Object>>> roleList = new HashMap<String,List<Map<String,Object>>>();
	private List<Map<String,Object>> userList = new ArrayList<Map<String,Object>>();
	private RoleMapper roleMapper = new RoleMapper();
	private UserRoleMapper userRoleMapper = new UserRoleMapper();
	private MyComboBox user;
	private UserMapper userMapper = new UserMapper();

	public AuthorityFrame(){
		this.setLayout(null);
		init();
		
		label2 = new JLabel("选择用户：");
		label2.setFont(new Font("宋体", Font.PLAIN, 13));
		label2.setBounds(new Rectangle(20,15,65,20));
		
		user = new MyComboBox(userList);
		user.setBounds(new Rectangle(85,15,100,20));
		user.setBackground(Color.white);
		user.setOpaque(false);
		
		label = new JLabel(" 所有角色 ");
		label.setFont(new Font("宋体", Font.PLAIN, 13));
		label.setForeground(new Color(192, 192, 192));
		label.setBounds(new Rectangle(45,50,70,20));
		label.setBackground(Color.white);
		label.setOpaque(true);
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));
		panel.setBounds(new Rectangle(40,60,200,420));
		panel.setOpaque(false);
		
		label1 = new JLabel(" 已选角色 ");
		label1.setFont(new Font("宋体", Font.PLAIN, 13));
		label1.setForeground(new Color(192, 192, 192));
		label1.setBounds(new Rectangle(345,50,70,20));
		label1.setBackground(Color.white);
		label1.setOpaque(true);
		
		panel1 = new JPanel();
		panel1.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));
		panel1.setBounds(new Rectangle(340,60,200,420));
		panel1.setOpaque(false);
		
		button = new JButton(">>>>");
		button.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));
		button.setContentAreaFilled(false);
		button.setOpaque(false);
		button.setFocusPainted(false);
		button.setBounds(new Rectangle(265,200,50,20));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		button1 = new JButton(">>");
		button1.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));
		button1.setContentAreaFilled(false);
		button1.setOpaque(false);
		button1.setFocusPainted(false);
		button1.setBounds(new Rectangle(265,230,50,20));
		button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		button2 = new JButton("<<");
		button2.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));
		button2.setContentAreaFilled(false);
		button2.setOpaque(false);
		button2.setFocusPainted(false);
		button2.setBounds(new Rectangle(265,280,50,20));
		button2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		button3 = new JButton("<<<<");
		button3.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));
		button3.setContentAreaFilled(false);
		button3.setOpaque(false);
		button3.setFocusPainted(false);
		button3.setBounds(new Rectangle(265,310,50,20));
		button3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		save = new JButton("保存");
		save.setBounds(new Rectangle(260,500,60,30));
		
		this.setBackground(Color.white);
		
		if(CollectionUtils.isNotEmpty(roleList.get("allRoleList"))){
			Integer index = 1;
			for(Map<String,Object> item : roleList.get("allRoleList")){
				MyLabel myLabel = new MyLabel(item);
				myLabel.setBounds(340,65 + (index++ -1) * 20,200,20);
				myLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(204, 204, 204)));
				myLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				this.add(myLabel);
			}
		}
		
		this.add(label2);
		this.add(user);
		this.add(button);
		this.add(button1);
		this.add(button2);
		this.add(button3);
		this.add(label);
		this.add(panel);
		this.add(label1);
		this.add(panel1);
		this.add(save);
	}
	
	private void init(){
		//初始化静态数据
		userList.clear();
		Map<String,Object> black = new HashMap<String,Object>();
		black.put("label", "");
		black.put("value", null);
		userList.add(black);
		
		List<UserBean> userBeanList = userMapper.selectUserList();
		if(CollectionUtils.isNotEmpty(userBeanList)){
			for(UserBean userBean:userBeanList){
				Map<String,Object> item = new HashMap<String,Object>();
				item.put("label", userBean.getUsername());
				item.put("value", userBean.getId());
				userList.add(item);
			}
		}
		
		getDatas(null);
	}
	
	private void getDatas(Integer userId){
		List<Map<String,Object>> allroleList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> selectedRoleList = new ArrayList<Map<String,Object>>();
		if(null != userId){
			List<UserRoleBean> selectedList = getSelectedRoleList(userId);
			if(CollectionUtils.isNotEmpty(getAllRoleList())){
				for(RoleBean roleBean:getAllRoleList()){
					if(CollectionUtils.isNotEmpty(selectedRoleList)){
						for(UserRoleBean userRoleBean:selectedList){
							if(roleBean.getId().equals(userRoleBean.getRoleId())){
								Map<String,Object> item = new HashMap<String,Object>();
								item.put("label", roleBean.getName());
								item.put("value", roleBean.getId());
								selectedRoleList.add(item);
								break;
							}
						}
					}
					Map<String,Object> item = new HashMap<String,Object>();
					item.put("label", roleBean.getName());
					item.put("value", roleBean.getId());
					allroleList.add(item);
				}
			}
		}else{
			if(CollectionUtils.isNotEmpty(getAllRoleList())){
				for(RoleBean roleBean:getAllRoleList()){
					Map<String,Object> item = new HashMap<String,Object>();
					item.put("label", roleBean.getName());
					item.put("value", roleBean.getId());
					allroleList.add(item);
				}
			}
		}
		Map<String,Object> item = new HashMap<String,Object>();
		item.put("label", " abcd");
		item.put("value", 2);
		allroleList.add(item);
		roleList.put("allRoleList", allroleList);
		roleList.put("selectedRoleList", selectedRoleList);
	}
	
	private List<RoleBean> getAllRoleList(){
		SearchBean searchBean = new SearchBean();
		searchBean.setStatus(StatusEnum.NORMAL.getKey());
		return roleMapper.selectRolesBySearchBean(searchBean);
	}
	
	private List<UserRoleBean> getSelectedRoleList(Integer userId){
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("userId", userId);
		return userRoleMapper.selectUserRolesByParams(params);
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		AuthorityFrame panel = new AuthorityFrame();
		frame.add(panel);
		frame.setSize(670, 580);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
