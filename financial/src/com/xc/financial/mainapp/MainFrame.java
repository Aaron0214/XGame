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

import com.xc.financial.login.LoginFrame;

public class MainFrame implements MouseListener{
	
	private JFrame frame;
	private Tree jtree;
	private JPanel jpanel,instock,outstock,financial;
	private JLabel label,username,logout;
	
	public MainFrame(){
		frame = new JFrame();
		frame.setLayout(null);
		
		label = new JLabel("欢迎,");
		label.setFont(new Font("宋体", Font.PLAIN, 13));
		label.setBounds(new Rectangle(675,5,40,20));
		
		username = new JLabel("abc");
		username.setFont(new Font("宋体", Font.PLAIN, 13));
		username.setBounds(new Rectangle(710,5,30,20));
		username.setForeground(Color.BLUE);
		username.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		logout = new JLabel("退出登录");
		logout.setFont(new Font("宋体", Font.PLAIN, 13));
		logout.setForeground(Color.BLUE);
		logout.setBounds(new Rectangle(745,5,80,20));
		logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		jtree = new Tree();
		jtree.setBounds(new Rectangle(20,35,100,580));
		
		jpanel = new JPanel();
		jpanel.setBounds(new Rectangle(130,30,672,546));
		
		instock = new InstockFrame();
		instock.setBounds(new Rectangle(125,30,672,546));
		
		outstock = new OutStockFrame();
		outstock.setBounds(new Rectangle(125,30,672,546));
		
		financial = new FinancialFrame();
		financial.setBounds(new Rectangle(125,30,672,546));
		
		jtree.addMouseListener(this);
		logout.addMouseListener(this);
		
		frame.add(label);
		frame.add(username);
		frame.add(logout);
		frame.add(jtree);
		frame.add(jpanel);
		frame.add(instock);
		frame.add(outstock);
		frame.add(financial);
		
		doLay();
		frame.setVisible(true);
		frame.setSize(810, 610);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void doLay() {
        Container container = frame.getContentPane();
        container.setBackground(new Color(167, 201, 219));
        frame.pack();
    } 
	
	public static void main(String[] args){
		new MainFrame();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == jtree){
			if(SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1){
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtree.getLastSelectedPathComponent();
				if(node != null && node.getChildCount() == 0){
					if(node.getUserObject().equals("首页")){
						jpanel.setVisible(true);
						instock.setVisible(false);
						outstock.setVisible(false);
						financial.setVisible(false);
					}
					if(node.getUserObject().equals("收入管理")){
						jpanel.setVisible(false);
						outstock.setVisible(false);
						financial.setVisible(false);
						instock.setVisible(true);
					}
					
					if(node.getUserObject().equals("消费管理")){
						jpanel.setVisible(false);
						instock.setVisible(false);
						financial.setVisible(false);
						outstock.setVisible(true);
					}
					
					if(node.getUserObject().equals("余额管理")){
						jpanel.setVisible(false);
						instock.setVisible(false);
						outstock.setVisible(false);
						financial.setVisible(true);
					}
				}
			}
		}
		
		if(e.getSource() == logout){
			frame.dispose();
			new LoginFrame();
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

}
