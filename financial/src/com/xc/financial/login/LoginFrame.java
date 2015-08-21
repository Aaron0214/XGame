package com.xc.financial.login;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.xc.financial.mainapp.MainFrame;

public class LoginFrame implements ActionListener{
	
	private JFrame frame;
	private JLabel usernameLabel,pwdLabel,forget;
	private JTextField usernameInput,pwdInput;
	private JButton login;
	private JCheckBox checkBox;
	
	public LoginFrame(){
		frame = new JFrame();
		frame.setLayout(null);
		
		usernameLabel = new JLabel("用戶名:");
		usernameLabel.setFont(new Font("宋体", Font.BOLD, 14));
		usernameLabel.setBounds(new Rectangle(70,100,60,25));
		
		usernameInput = new JTextField();
		usernameInput.setBounds(new Rectangle(130,100,150,25));
		
		pwdLabel = new JLabel("密码:");
		pwdLabel.setFont(new Font("宋体", Font.BOLD, 14));
		pwdLabel.setBounds(new Rectangle(85,140,60,25));
		
		pwdInput = new JTextField();
		pwdInput.setBounds(new Rectangle(130,140,150,25));
		
		checkBox = new JCheckBox("记住用户");
		checkBox.setFont(new Font("宋体", Font.BOLD, 12));
		checkBox.setBounds(new Rectangle(126,170,80,25));
		checkBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		forget = new JLabel("忘记密码?");
		forget.setFont(new Font("宋体", Font.BOLD, 12));
		forget.setBounds(new Rectangle(220,170,80,25));
		
		login = new JButton("登 陆");
		login.setFont(new Font("宋体", Font.BOLD, 14));
		login.setBounds(new Rectangle(130,200,150,25));
		login.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		login.addActionListener(this);
		
		frame.add(usernameLabel);
		frame.add(usernameInput);
		frame.add(pwdLabel);
		frame.add(pwdInput);
		frame.add(checkBox);
		frame.add(forget);
		frame.add(login);
		
		frame.setVisible(true);
		frame.setSize(400, 360);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args){
		new LoginFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == login){
//			JOptionPane.showMessageDialog(frame, "登陆成功！");
			new MainFrame();
		}
	}
}
