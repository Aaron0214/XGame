package com.xc.financial.mainapp;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

public class MainFrame implements MouseListener{
	
	private JFrame frame;
	private JTree tree;
	private JPanel jpanel,instoke;
	
	public MainFrame(){
		frame = new JFrame();
		frame.setLayout(null);
		
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("财务管理系统");
		DefaultMutableTreeNode index = new DefaultMutableTreeNode("首页");
		
		DefaultMutableTreeNode jxc = new DefaultMutableTreeNode("进销存");
		DefaultMutableTreeNode instore = new DefaultMutableTreeNode("收入管理");
		DefaultMutableTreeNode outstore = new DefaultMutableTreeNode("消费管理");
		DefaultMutableTreeNode maney = new DefaultMutableTreeNode("余额管理");
		
		DefaultMutableTreeNode user = new DefaultMutableTreeNode("用户管理");
		DefaultMutableTreeNode adduser = new DefaultMutableTreeNode("用户列表");
		
		jxc.add(instore);
		jxc.add(outstore);
		jxc.add(maney);
		
		user.add(adduser);
		
		top.add(index);
		top.add(jxc);
		top.add(user);
		
		tree = new JTree(top);
		tree.setBounds(new Rectangle(20,20,120,580));
		tree.setOpaque(false);
//		tree.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		jpanel = new JPanel();
		jpanel.setBackground(Color.gray);
		jpanel.setBounds(new Rectangle(140,20,670,580));
		
		instoke = new InstockFrame();
		instoke.setBounds(new Rectangle(140,20,670,580));
		
		tree.addMouseListener(this);
		
		
		frame.add(tree);
		frame.add(jpanel);
		frame.add(instoke);
		
		frame.setVisible(true);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		new MainFrame();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if(node != null && node.getChildCount() == 0){
				if(node.getUserObject().equals("首页")){
					jpanel.setVisible(true);
					instoke.setVisible(false);
				}
				if(node.getUserObject().equals("收入管理")){
					jpanel.setVisible(false);
					instoke.setVisible(true);
				}
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
