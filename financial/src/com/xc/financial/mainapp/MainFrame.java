package com.xc.financial.mainapp;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import com.xc.financial.tools.*;

import com.xc.financial.login.LoginFrame;

public class MainFrame extends JFrame implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tree jtree;
	private JPanel jpanel,instock,outstock,financial,codeDict,back,authority;
	private UserFrame user;
	private RoleFrame role;
	private JLabel label,label1,username,logout,copyright;
	private JPanel lastFrame;
	
	public MainFrame(String name){
		this.setLayout(null);
		
		back = (JPanel) this.getContentPane();
		
		label = new JLabel("欢迎,");
		label.setFont(new Font("宋体", Font.PLAIN, 13));
		label.setBounds(new Rectangle(675,5,40,20));
		
		label1 = new JLabel("欢迎登陆财务管理系统");
		label1.setFont(new Font("宋体", Font.PLAIN, 13));
		label1.setBounds(new Rectangle(20,5,150,20));
		
		username = new JLabel(name);
		username.setFont(new Font("宋体", Font.PLAIN, 13));
		username.setBounds(new Rectangle(710,5,name.length() * 20,20));
		username.setForeground(Color.BLUE);
		username.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		logout = new JLabel("退出登录");
		logout.setFont(new Font("宋体", Font.PLAIN, 13));
		logout.setForeground(Color.BLUE);
		logout.setBounds(new Rectangle(745,5,80,20));
		logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		jtree = new Tree();
		jtree.setBounds(new Rectangle(15,30,110,580));
		
		jpanel = new IndexFrame();
		jpanel.setBounds(new Rectangle(125,30,672,541));
		
		instock = new InstockFrame();
		instock.setBounds(new Rectangle(125,30,672,541));
		
		outstock = new OutStockFrame();
		outstock.setBounds(new Rectangle(125,30,672,541));
		
		financial = new FinancialFrame();
		financial.setBounds(new Rectangle(125,30,672,541));
		
		user = new UserFrame();
		user.setBounds(new Rectangle(125,30,672,541));
		
		codeDict = new CodeDictFrame();
		codeDict.setBounds(new Rectangle(125,30,672,541));
		
		role = new RoleFrame();
		role.setBounds(new Rectangle(125,30,672,541));
		
		authority = new AuthorityFrame();
		authority.setBounds(new Rectangle(125,30,672,541));
		
		copyright = new JLabel("COPYRIGHT © 2014 XuCe ALL RIGHTS RESERVED.");
		copyright.setFont(new Font("宋体", Font.PLAIN, 14));
		copyright.setBounds(new Rectangle(265,550,300,80));
		
		jtree.addMouseListener(this);
		logout.addMouseListener(this);
		
		back.add(copyright);
		back.add(label1);
		back.add(label);
		back.add(username);
		back.add(logout);
		back.add(jtree);
		back.add(jpanel);
		
		doLay();
		this.setVisible(true);
		this.setSize(810, 635);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void doLay() {
        Container container = this.getContentPane();
//        container.setBackground(new Color(167, 201, 219));
        container.setBackground(new Color(172, 213, 242));
        this.pack();
    } 
	
	public static void main(String[] args){
		new MainFrame("abc");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == jtree){
			back.remove(jpanel);
			back.remove(instock);
			back.remove(outstock);
			back.remove(financial);
			back.remove(user);
			back.remove(codeDict);
			back.remove(role);
			back.remove(authority);
			if(null != lastFrame){
				back.remove(lastFrame);
			}
			if(SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1){
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtree.getLastSelectedPathComponent();
				if(node != null && node.getChildCount() == 0){
					if(node.getUserObject().equals("首页")){
						jpanel = new IndexFrame();
						jpanel.setBounds(new Rectangle(125,30,672,541));
						back.add(jpanel);
						back.updateUI();
						jpanel.setVisible(true);
					}
					if(node.getUserObject().equals("收入管理")){
						instock = new InstockFrame();
						instock.setBounds(new Rectangle(125,30,672,541));
						back.add(instock);
						back.updateUI();
						instock.setVisible(true);
					}
					
					if(node.getUserObject().equals("消费管理")){
						outstock = new OutStockFrame();
						outstock.setBounds(new Rectangle(125,30,672,541));
						back.add(outstock);
						back.updateUI();
						outstock.setVisible(true);
					}
					
					if(node.getUserObject().equals("余额管理")){
						financial = new FinancialFrame();
						financial.setBounds(new Rectangle(125,30,672,541));
						back.add(financial);
						back.updateUI();
						financial.setVisible(true);
					}
					
					if(node.getUserObject().equals("用户列表")){
						user = new UserFrame();
						user.setBounds(new Rectangle(125,30,672,541));
						user.setFrame(this);
						back.add(user);
						back.updateUI();
						user.setVisible(true);
					}
					if(node.getUserObject().equals("配置项管理")){
						codeDict = new CodeDictFrame();
						codeDict.setBounds(new Rectangle(125,30,672,541));
						back.add(codeDict);
						back.updateUI();
						codeDict.setVisible(true);
					}
					if(node.getUserObject().equals("角色管理")){
						role = new RoleFrame();
						role.setBounds(new Rectangle(125,30,672,541));
						role.setFrame(this);
						back.add(role);
						back.updateUI();
					}
					if(node.getUserObject().equals("权限管理")){
						authority = new AuthorityFrame();
						authority.setBounds(new Rectangle(125,30,672,541));
						back.add(authority);
						back.updateUI();
					}
					if(node.getUserObject().equals("更改密码")){
						authority = new AuthorityFrame();
						authority.setBounds(new Rectangle(125,30,672,541));
						back.add(authority);
						back.updateUI();
					}
				}
			}
		}
		
		if(e.getSource() == logout){
			this.dispose();
			new LoginFrame();
		}
		
	}
	
	public void changeFrame(JPanel panel){
		back.remove(jpanel);
		back.remove(instock);
		back.remove(outstock);
		back.remove(financial);
		back.remove(user);
		back.remove(codeDict);
		back.remove(role);
		if(null != lastFrame){
			back.remove(lastFrame);
		}
		panel.setBounds(new Rectangle(125,30,672,541));
		back.add(panel);
		back.updateUI();
		lastFrame = panel;
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

}
