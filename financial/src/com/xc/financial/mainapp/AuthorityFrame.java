package com.xc.financial.mainapp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

public class AuthorityFrame extends JPanel implements ActionListener,MouseListener,KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button,button1,button2,button3,save;
	private JPanel panel,panel1;
	private JLabel label,label1,label2;
	private Map<String,List<MyLabel>> roleList = new HashMap<String,List<MyLabel>>();
	private List<Map<String,Object>> userList = new ArrayList<Map<String,Object>>();
	private RoleMapper roleMapper = new RoleMapper();
	private UserRoleMapper userRoleMapper = new UserRoleMapper();
	private MyComboBox user;
	private UserMapper userMapper = new UserMapper();
	private boolean isShiftPressed = false;
	private List<MyLabel> selectedLabelList = new ArrayList<MyLabel>();
	
	public AuthorityFrame(){
		this.setLayout(null);
		init();
		
		label2 = new JLabel("选择用户：");
		label2.setFont(new Font("宋体", Font.PLAIN, 13));
		label2.setBounds(new Rectangle(20,15,65,20));
		
		user = new MyComboBox(userList);
		user.setBounds(new Rectangle(85,15,100,20));
		user.setCursor(new Cursor(Cursor.HAND_CURSOR));
		user.setOpaque(false);
		
		label = new JLabel(" 所有角色 ");
		label.setFont(new Font("宋体", Font.PLAIN, 13));
		label.setForeground(new Color(126, 126, 126));
		label.setBounds(new Rectangle(45,48,70,20));
		label.setOpaque(true);
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(new Color(126, 126, 126), 1));
		panel.setBounds(new Rectangle(40,60,200,420));
		panel.setOpaque(false);
		
		label1 = new JLabel(" 已选角色 ");
		label1.setFont(new Font("宋体", Font.PLAIN, 13));
		label1.setForeground(new Color(126, 126, 126));
		label1.setBounds(new Rectangle(345,48,70,20));
		label1.setOpaque(true);
		
		panel1 = new JPanel();
		panel1.setBorder(BorderFactory.createLineBorder(new Color(126, 126, 126), 1));
		panel1.setBounds(new Rectangle(340,60,200,420));
		panel1.setOpaque(false);
		
		button = new JButton(">>>>");
		button.setBorder(BorderFactory.createLineBorder(new Color(126, 126, 126), 1));
		button.setContentAreaFilled(false);
		button.setOpaque(false);
		button.setFocusPainted(false);
		button.setBounds(new Rectangle(265,200,50,20));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		button1 = new JButton(">>");
		button1.setBorder(BorderFactory.createLineBorder(new Color(126, 126, 126), 1));
		button1.setContentAreaFilled(false);
		button1.setOpaque(false);
		button1.setFocusPainted(false);
		button1.setBounds(new Rectangle(265,230,50,20));
		button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		button2 = new JButton("<<");
		button2.setBorder(BorderFactory.createLineBorder(new Color(126, 126, 126), 1));
		button2.setContentAreaFilled(false);
		button2.setOpaque(false);
		button2.setFocusPainted(false);
		button2.setBounds(new Rectangle(265,280,50,20));
		button2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		button3 = new JButton("<<<<");
		button3.setBorder(BorderFactory.createLineBorder(new Color(126, 126, 126), 1));
		button3.setContentAreaFilled(false);
		button3.setOpaque(false);
		button3.setFocusPainted(false);
		button3.setBounds(new Rectangle(265,310,50,20));
		button3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		save = new JButton("保存");
		save.setBounds(new Rectangle(260,500,60,30));
		save.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		if(CollectionUtils.isNotEmpty(roleList.get("allRoleList"))){
			Integer index = 1;
			for(MyLabel myLabel : roleList.get("allRoleList")){
				myLabel.setBounds(41,68 + (index++ -1) * 20,198,20);
				myLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(168, 168, 168)));
				myLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				myLabel.addMouseListener(this);
				this.add(myLabel);
			}
		}
		user.addActionListener(this);
		button.addActionListener(this);
		button1.addActionListener(this);
		button3.addActionListener(this);
		button2.addActionListener(this);
		save.addActionListener(this);
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addMouseListener(this);
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
		roleList = new HashMap<String,List<MyLabel>>();
		List<MyLabel> allroleList = new ArrayList<MyLabel>();
		List<MyLabel> selectedRoleList = new ArrayList<MyLabel>();
		if(null != userId){
			List<UserRoleBean> selectedList = getSelectedRoleList(userId);
			if(CollectionUtils.isNotEmpty(getAllRoleList())){
				for(RoleBean roleBean:getAllRoleList()){
					boolean isSelected = false;
					if(CollectionUtils.isNotEmpty(selectedList)){
						for(UserRoleBean userRoleBean:selectedList){
							if(roleBean.getId().equals(userRoleBean.getRoleId())){
								isSelected = true;
								break;
							}else{
								isSelected = false;
							}
						}
					}
					if(isSelected){
						Map<String,Object> item = new HashMap<String,Object>();
						item.put("label", roleBean.getName());
						item.put("value", roleBean.getId());
						selectedRoleList.add(new MyLabel(item));
					}else{
						Map<String,Object> item = new HashMap<String,Object>();
						item.put("label", roleBean.getName());
						item.put("value", roleBean.getId());
						allroleList.add(new MyLabel(item));
					}
				}
			}
		}else{
			if(CollectionUtils.isNotEmpty(getAllRoleList())){
				for(RoleBean roleBean:getAllRoleList()){
					Map<String,Object> item = new HashMap<String,Object>();
					item.put("label", roleBean.getName());
					item.put("value", roleBean.getId());
					allroleList.add(new MyLabel(item));
				}
			}
		}
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == user){
			getDatas((Integer)user.getSelectedItemValue());
			
			List<MyLabel> allRoles = new ArrayList<MyLabel>();
			allRoles.addAll(roleList.get("allRoleList"));
			
			List<MyLabel> selectedRoles = new ArrayList<MyLabel>();
			selectedRoles.addAll(roleList.get("selectedRoleList"));
			
			updateSelectedBox(allRoles,selectedRoles);
		}
		if(e.getSource() == button){
			List<MyLabel> selectedRoles = new ArrayList<MyLabel>();
			selectedRoles.addAll(roleList.get("allRoleList"));
			selectedRoles.addAll(roleList.get("selectedRoleList"));
			updateSelectedBox(new ArrayList<MyLabel>(),selectedRoles);
		}
		if(e.getSource() == button1){
			if(CollectionUtils.isNotEmpty(selectedLabelList)){
				List<MyLabel> allRoles = new ArrayList<MyLabel>();
				List<MyLabel> selectedRoles = new ArrayList<MyLabel>();
				HashSet<MyLabel> labels = new HashSet<MyLabel>();
				
				for(MyLabel label:roleList.get("allRoleList")){
					boolean isSelected = false;
					for(MyLabel label1:selectedLabelList){
						if(label.equals(label1)){
							isSelected = true;
							break;
						}else{
							isSelected = false;
						}
					}
					if(isSelected){
						labels.add(label);
					}else{
						allRoles.add(label);
					}
				}
				labels.addAll(roleList.get("selectedRoleList"));
				selectedRoles.addAll(labels);
				updateSelectedBox(allRoles,selectedRoles);
			}
		}
		
		if(e.getSource() == button2){
			if(CollectionUtils.isNotEmpty(selectedLabelList)){
				List<MyLabel> allRoles = new ArrayList<MyLabel>();
				HashSet<MyLabel> labels = new HashSet<MyLabel>();
				List<MyLabel> selectedRoles = new ArrayList<MyLabel>();
				
				for(MyLabel label:roleList.get("selectedRoleList")){
					boolean isSelected = false;
					for(MyLabel label1:selectedLabelList){
						if(label.equals(label1)){
							isSelected = true;
							break;
						}else{
							isSelected = false;
						}
					}
					if(isSelected){
						labels.add(label);
					}else{
						selectedRoles.add(label);
					}
				}
				labels.addAll(roleList.get("allRoleList"));
				allRoles.addAll(labels);
				updateSelectedBox(allRoles,selectedRoles);
			}
		}
		if(e.getSource() == button3){
			List<MyLabel> allRoles = new ArrayList<MyLabel>();
			allRoles.addAll(roleList.get("allRoleList"));
			allRoles.addAll(roleList.get("selectedRoleList"));
			updateSelectedBox(allRoles,new ArrayList<MyLabel>());
		}
		
		if(e.getSource() == save){
			if(null != user.getSelectedItemValue()){
				List<UserRoleBean> userRoleList = new ArrayList<UserRoleBean>();
				if(CollectionUtils.isNotEmpty(roleList.get("selectedRoleList"))){
					for(MyLabel label:roleList.get("selectedRoleList")){
						UserRoleBean userRoleBean = new UserRoleBean();
						userRoleBean.setRoleId((Integer)label.getValue());
						userRoleBean.setUserId((Integer)user.getSelectedItemValue());
						userRoleList.add(userRoleBean);
					}
				}
				saveUpdateData(userRoleList);
			}else{
				JOptionPane.showMessageDialog(this, "请先选择用户！");
			}
		}
	}
	
	private void updateSelectedBox(List<MyLabel> allRoleList,List<MyLabel> selectedRoleList){
		for(Component a : this.getComponents()){
			if(a.getClass().equals(MyLabel.class)){
				this.remove(a);
			}
		}
		roleList.get("allRoleList").clear();
		roleList.get("selectedRoleList").clear();
		if(CollectionUtils.isNotEmpty(allRoleList)){
			Integer index = 1;
			for(MyLabel myLabel : allRoleList){
				roleList.get("allRoleList").add(myLabel);
				myLabel.setBounds(41,68 + (index++ -1) * 20,198,20);
				myLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(168, 168, 168)));
				myLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				myLabel.addMouseListener(this);
				this.add(myLabel,index-1);
			}
		}
		if(CollectionUtils.isNotEmpty(selectedRoleList)){
			Integer index = 1;
			for(MyLabel myLabel : selectedRoleList){
				roleList.get("selectedRoleList").add(myLabel);
				myLabel.setBounds(341,68 + (index++ -1) * 20,198,20);
				myLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(168, 168, 168)));
				myLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				myLabel.addMouseListener(this);
				this.add(myLabel,allRoleList.size()+index-1);
			}
		}
		this.updateUI();
		this.requestFocus();
	}
	
	private void saveUpdateData(List<UserRoleBean> userRoleList){
		if(CollectionUtils.isNotEmpty(userRoleList)){
			try{
				userRoleMapper.updateUserRole(userRoleList);
				JOptionPane.showMessageDialog(this, "保存成功！");
			}catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "保存失败！");
			}
		}else{
			userRoleMapper.deleteRoleByUserId((Integer)user.getSelectedItemValue());
			JOptionPane.showMessageDialog(this, "保存成功！");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().getClass().equals(MyLabel.class)){
			boolean isAtSamePanel = true;
			if(CollectionUtils.isNotEmpty(selectedLabelList)){
				MyLabel label = selectedLabelList.get(0);
				if(label.getX() == ((MyLabel)e.getSource()).getX()){
					isAtSamePanel = true;
				}else{
					isAtSamePanel = false;
				}
			}
			
			if(!isShiftPressed || !isAtSamePanel){
				for(MyLabel mylabel:selectedLabelList){
					mylabel.setOpaque(false);
					mylabel.setForeground(Color.BLACK);
				}
				selectedLabelList.clear();
			}
			if(!selectedLabelList.contains((MyLabel)e.getSource())){
				selectedLabelList.add((MyLabel)e.getSource());
			}
			for(MyLabel mylabel:selectedLabelList){
				mylabel.setOpaque(true);
				mylabel.setBackground(new Color(204, 204, 204));
				mylabel.setForeground(Color.BLUE);
			}
		}else{
			for(MyLabel mylabel:selectedLabelList){
				mylabel.setOpaque(false);
				mylabel.setForeground(Color.BLACK);
			}
			selectedLabelList.clear();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e1) {
		if(e1.getKeyCode() == KeyEvent.VK_SHIFT){
			isShiftPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e1) {
		isShiftPressed = false;
	}

	@Override
	public void keyTyped(KeyEvent e1) {
		
	}

}
