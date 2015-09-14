package com.xc.financial.mainapp;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IndexFrame extends JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label,label1,label2,label3,label4,label5,label6,label7,label8,label9,
						label10,label11,label12,label13,label14,label15,label16;

	public IndexFrame(){
		this.setLayout(null);
		
		label12 = new JLabel("系统介绍");
		label12.setFont(new Font("宋体", Font.PLAIN, 15));
		label12.setBounds(new Rectangle(50,35,80,20)); 
		
		label = new JLabel("系统名称：");
		label.setFont(new Font("宋体", Font.PLAIN, 13));
		label.setBounds(new Rectangle(120,75,80,20));
		
		label1 = new JLabel("abc");
		label1.setFont(new Font("宋体", Font.PLAIN, 13));
		label1.setBounds(new Rectangle(190,75,80,20));
		
		label2 = new JLabel("系统版本：");
		label2.setFont(new Font("宋体", Font.PLAIN, 13));
		label2.setBounds(new Rectangle(390,75,80,20));
		
		label3 = new JLabel("v1.0");
		label3.setFont(new Font("宋体", Font.PLAIN, 13));
		label3.setBounds(new Rectangle(460,75,80,20));
		
		label4 = new JLabel("开发者：");
		label4.setFont(new Font("宋体", Font.PLAIN, 13));
		label4.setBounds(new Rectangle(133,105,80,20));
		
		label5 = new JLabel("xc");
		label5.setFont(new Font("宋体", Font.PLAIN, 13));
		label5.setBounds(new Rectangle(190,105,80,20));
		
		label6 = new JLabel("开发时间：");
		label6.setFont(new Font("宋体", Font.PLAIN, 13));
		label6.setBounds(new Rectangle(390,105,80,20));
		
		label7 = new JLabel("2015-09-14");
		label7.setFont(new Font("宋体", Font.PLAIN, 13));
		label7.setBounds(new Rectangle(460,105,80,20));
		
		label8 = new JLabel("开发语言：");
		label8.setFont(new Font("宋体", Font.PLAIN, 13));
		label8.setBounds(new Rectangle(120,165,80,20));
		
		label9 = new JLabel("java");
		label9.setFont(new Font("宋体", Font.PLAIN, 13));
		label9.setBounds(new Rectangle(190,165,80,20));
		
		label10 = new JLabel("开发环境：");
		label10.setFont(new Font("宋体", Font.PLAIN, 13));
		label10.setBounds(new Rectangle(390,165,80,20));
		
		label11 = new JLabel("Windows 7");
		label11.setFont(new Font("宋体", Font.PLAIN, 13));
		label11.setBounds(new Rectangle(460,165,80,20));
		
		label13 = new JLabel("java版本：");
		label13.setFont(new Font("宋体", Font.PLAIN, 13));
		label13.setBounds(new Rectangle(120,195,80,20));
		
		label14 = new JLabel("jdk1.7");
		label14.setFont(new Font("宋体", Font.PLAIN, 13));
		label14.setBounds(new Rectangle(190,195,80,20));
		
		label15 = new JLabel("java目录：");
		label15.setFont(new Font("宋体", Font.PLAIN, 13));
		label15.setBounds(new Rectangle(390,195,80,20));
		
		label16 = new JLabel("C:\\software\\jdk1.7.0_05");
		label16.setFont(new Font("宋体", Font.PLAIN, 13));
		label16.setBounds(new Rectangle(460,195,180,20));
		
		this.add(label12);
		this.add(label);
		this.add(label1);
		this.add(label2);
		this.add(label3);
		this.add(label4);
		this.add(label5);
		this.add(label6);
		this.add(label7);
		this.add(label8);
		this.add(label9);
		this.add(label10);
		this.add(label11);
		this.add(label13);
		this.add(label14);
		this.add(label15);
		this.add(label16);
	}
	
	public static void main(String[] arg0){
		JFrame frame = new JFrame();
		JPanel panel = new IndexFrame();
		frame.add(panel);
		
		frame.setSize(680,570);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
