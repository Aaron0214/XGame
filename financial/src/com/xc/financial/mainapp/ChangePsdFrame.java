package com.xc.financial.mainapp;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChangePsdFrame extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JLabel label,label1,label2;
	private JTextField field,field1,field2;

	public ChangePsdFrame(){
		this.setLayout(null);
		
		label = new JLabel("原密码：");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(new Rectangle(50,30,70,20));
		
		field = new JTextField();
		field.setBounds(new Rectangle(105,30,120,20));
		
		label1 = new JLabel("新密码：");
		label1.setFont(new Font("宋体", Font.PLAIN, 14));
		label1.setBounds(new Rectangle(50,60,70,20));
		
		field1 = new JTextField();
		field1.setBounds(new Rectangle(105,60,120,20));
		
		label2 = new JLabel("确认密码：");
		label2.setFont(new Font("宋体", Font.PLAIN, 14));
		label2.setBounds(new Rectangle(40,90,70,20));
		
		field2 = new JTextField();
		field2.setBounds(new Rectangle(105,90,120,20));
		
		this.add(label);
		this.add(field);
		this.add(label1);
		this.add(field1);
		this.add(label2);
		this.add(field2);
	}
	
	public static void main(String[] arg0){
		JFrame frame = new JFrame();
		ChangePsdFrame panel = new ChangePsdFrame();
		frame.add(panel);
		
		frame.setSize(680, 570);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
