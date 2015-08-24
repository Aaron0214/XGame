package com.xc.financial.mainapp;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

public class MainFrame implements MouseListener{
	
	private JFrame frame;
	private JTree jtree;
	private JPanel jpanel,instoke;
	
	public MainFrame(){
		frame = new JFrame();
		frame.setLayout(null);
		
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("财务管理系统");
		DefaultMutableTreeNode index = new DefaultMutableTreeNode("首页");
		
		DefaultMutableTreeNode jxc = new DefaultMutableTreeNode("进销存");
		jxc.add(new DefaultMutableTreeNode("收入管理"));
		jxc.add(new DefaultMutableTreeNode("消费管理"));
		jxc.add(new DefaultMutableTreeNode("余额管理"));
		
		DefaultMutableTreeNode user = new DefaultMutableTreeNode("用户管理");
		user.add(new DefaultMutableTreeNode("用户列表"));
		root.add(index);
		root.add(jxc);
		root.add(user);
		
		DefaultTreeModel model=new DefaultTreeModel(root);
		jtree = new JTree(model);
		DefaultTreeCellRenderer render=(DefaultTreeCellRenderer)(jtree.getCellRenderer());
//		render.setOpenIcon(new ImageIcon(""));//把前面的图标去掉
		render.setLeafIcon(new ImageIcon(""));
//		render.setClosedIcon(new ImageIcon(""));//把前面的图标去掉
		render.setBackgroundNonSelectionColor(new Color(0, 0, 0, 0));//设置节点透明
		BasicTreeUI ui=(BasicTreeUI)(jtree.getUI());
//		ui.setCollapsedIcon(new ImageIcon("resources/images/tree.png"));//自定义打开的图标
//		ui.setExpandedIcon(new ImageIcon("resources/images/tree1.png"));//自定义打开的图标
		jtree.putClientProperty("JTree.lineStyle","None");//隐藏连接线
		jtree.setOpaque(false);
		jtree.setCellRenderer(render);
		jtree.setBounds(new Rectangle(20,20,120,580));
		jtree.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		jpanel = new JPanel();
//		jpanel.setBackground(Color.gray);
		jpanel.setBounds(new Rectangle(130,20,660,546));
		
		instoke = new InstockFrame();
		instoke.setBounds(new Rectangle(130,20,660,546));
		
		jtree.addMouseListener(this);
		
		frame.add(jtree);
		frame.add(jpanel);
		frame.add(instoke);
		
		doLay();
		frame.setVisible(true);
		frame.setSize(800, 600);
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
		if(SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) jtree.getLastSelectedPathComponent();
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
