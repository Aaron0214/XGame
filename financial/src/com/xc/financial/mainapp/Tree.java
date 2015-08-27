package com.xc.financial.mainapp;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

public class Tree extends JTree{
	
	private static final long serialVersionUID = 1L;

	public Tree(){
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
		this.setModel(model);
		DefaultTreeCellRenderer render=(DefaultTreeCellRenderer)(this.getCellRenderer());
//		render.setOpenIcon(new ImageIcon(""));//把前面的图标去掉
		render.setLeafIcon(new ImageIcon(""));
//		render.setClosedIcon(new ImageIcon(""));//把前面的图标去掉
		render.setBackgroundNonSelectionColor(new Color(0, 0, 0, 0));//设置节点透明
		BasicTreeUI ui=(BasicTreeUI)(this.getUI());
//		ui.setCollapsedIcon(new ImageIcon("resources/images/tree.png"));//自定义打开的图标
//		ui.setExpandedIcon(new ImageIcon("resources/images/tree1.png"));//自定义打开的图标
		this.putClientProperty("JTree.lineStyle","None");//隐藏连接线
		this.setOpaque(false);
		this.setCellRenderer(render);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

}
