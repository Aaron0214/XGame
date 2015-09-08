package com.xc.financial.mainapp;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.xc.financial.beans.SearchPage;

public class PageToolBar<T> extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JButton firstPage,prewPage,nextPage,lastPage,refresh;
	private JLabel label,label1,label2,label3,label4,label5,label6;
	private JTextField field;
	private JComboBox<Integer> comboBox;
	private Integer[] str = {5,10,15,20,25};
	private Integer num = 100;
	private Integer num_per = 15;
	private Class<T> frame;
	
	public PageToolBar(Class<T> frame){
		this.frame = frame;
		this.setLayout(null);
//		this.setBackground(Color.white);
		this.setBackground(new Color(247, 244, 213));
//		this.setBackground(new Color(249, 250, 205));
		this.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));
		
		ImageIcon firsticon = new ImageIcon("resources/images/first.jpg");
		firstPage = new JButton(firsticon);
		firstPage.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));
		firstPage.setOpaque(false);
		firstPage.setFocusPainted(false);
		firstPage.setContentAreaFilled(false);
		firstPage.setCursor(new Cursor(Cursor.HAND_CURSOR));
		firstPage.setBounds(new Rectangle(5,3,firsticon.getIconWidth(),firsticon.getIconHeight()));
		
		ImageIcon prewicon = new ImageIcon("resources/images/prew.jpg");
		prewPage = new JButton(prewicon);
		prewPage.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));
		prewPage.setOpaque(false);
		prewPage.setFocusPainted(false);
		prewPage.setContentAreaFilled(false);
		prewPage.setCursor(new Cursor(Cursor.HAND_CURSOR));
		prewPage.setBounds(new Rectangle(25,3,prewicon.getIconWidth(),prewicon.getIconHeight()));
		
		label = new JLabel("第");
		label.setFont(new Font("宋体", Font.PLAIN, 13));
		label.setBounds(new Rectangle(45,3,15,16));
		
		field = new JTextField();
		field.setText("1");
		field.setBounds(new Rectangle(62,3,25,16));
		
		label1 = new JLabel("页");
		label1.setFont(new Font("宋体", Font.PLAIN, 13));
		label1.setBounds(new Rectangle(90,3,20,16));
		
		label2 = new JLabel("/共");
		label2.setFont(new Font("宋体", Font.PLAIN, 13));
		label2.setBounds(new Rectangle(108,3,20,16));
		
		label3 = new JLabel(Math.round(1.0 * num/num_per) + "页");
		label3.setFont(new Font("宋体", Font.PLAIN, 13));
		label3.setBounds(new Rectangle(132,3,25,16));
		
		ImageIcon nexticon = new ImageIcon("resources/images/next.jpg");
		nextPage = new JButton(nexticon);
		nextPage.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));
		nextPage.setOpaque(false);
		nextPage.setFocusPainted(false);
		nextPage.setContentAreaFilled(false);
		nextPage.setCursor(new Cursor(Cursor.HAND_CURSOR));
		nextPage.setBounds(new Rectangle(155,3,nexticon.getIconWidth(),nexticon.getIconHeight()));
		
		ImageIcon lasticon = new ImageIcon("resources/images/last.jpg");
		lastPage = new JButton(lasticon);
		lastPage.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));
		lastPage.setOpaque(false);
		lastPage.setFocusPainted(false);
		lastPage.setContentAreaFilled(false);
		lastPage.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lastPage.setBounds(new Rectangle(175,3,lasticon.getIconWidth(),lasticon.getIconHeight()));
		
		label4 = new JLabel(" |每页");
		label4.setFont(new Font("宋体", Font.PLAIN, 13));
		label4.setBounds(new Rectangle(190,3,45,16));
		
		comboBox = new JComboBox<Integer>(str);
		comboBox.setSelectedItem(new Integer(num_per));
		comboBox.setBounds(new Rectangle(235,3,45,16));
		comboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
		comboBox.getEditor().getEditorComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
		comboBox.setBackground(new Color(247, 244, 213));
		
		label5 = new JLabel("条");
		label5.setFont(new Font("宋体", Font.PLAIN, 13));
		label5.setBounds(new Rectangle(285,3,15,16));
		
		ImageIcon refreshicon = new ImageIcon("resources/images/refresh.png");
		refresh = new JButton(refreshicon);
		refresh.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));
		refresh.setOpaque(false);
		refresh.setFocusPainted(false);
		refresh.setContentAreaFilled(false);
		refresh.setCursor(new Cursor(Cursor.HAND_CURSOR));
		refresh.setBounds(new Rectangle(305,3,refreshicon.getIconWidth()+1,refreshicon.getIconHeight()));
		
		label6 = new JLabel(" |共" + num + "条数据");
		label6.setFont(new Font("宋体", Font.PLAIN, 13));
		label6.setBounds(new Rectangle(320,3,100,16));
		
		firstPage.addActionListener(this);
		prewPage.addActionListener(this);
		nextPage.addActionListener(this);
		lastPage.addActionListener(this);
		comboBox.addActionListener(this);
		refresh.addActionListener(this);
		
		this.add(firstPage);
		this.add(prewPage);
		this.add(label);
		this.add(field);
		this.add(label1);
		this.add(label2);
		this.add(label3);
		this.add(nextPage);
		this.add(lastPage);
		this.add(label4);
		this.add(comboBox);
		this.add(label5);
		this.add(refresh);
		this.add(label6);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == firstPage){
			field.setText("1");
		}
		if(e.getSource() == prewPage){
			Integer page = Integer.parseInt(field.getText());
			if(page == 1){
				field.setText("1");
			}else{
				field.setText(String.valueOf((page - 1)));
			}
		}
		if(e.getSource() == nextPage){
			Integer page = Integer.parseInt(field.getText());
			Integer totalpage = (int) Math.round(1.0 * num/num_per);
			if(page == totalpage){
				field.setText(String.valueOf(totalpage));
			}else{
				field.setText(String.valueOf((page + 1)));
			}
			
		}
		if(e.getSource() == lastPage){
			Integer totalpage = (int) Math.round(1.0 * num/num_per);
			field.setText(String.valueOf(totalpage));
			SearchPage search = new SearchPage();
			search.setPageNumber(Long.valueOf(String.valueOf(totalpage)));
			search.setPageSize(Long.valueOf(String.valueOf(num_per)));
			try {
				for(Method m :frame.getDeclaredMethods()){
					if(m.getName().equals("search")){
						m.setAccessible(true);
						m.invoke(frame.newInstance(), search);
						break;
					}
				}
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource() == comboBox){
			num_per = (Integer)comboBox.getSelectedItem();
			label3.setText(Math.round(1.0 * num/num_per) + "页");
		}
		
		if(e.getSource() == refresh){
			field.setText("1");
			num_per = 15;
			label3.setText(Math.round(1.0 * num/num_per) + "页");
			comboBox.setSelectedItem(num_per);
		}
	}

	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setLayout(null);
		JPanel toolbar = new PageToolBar<InstockFrame>(InstockFrame.class);
		toolbar.setBounds(new Rectangle(20,20,411,22));
		frame.add(toolbar);
		
		frame.setSize(600, 100);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
