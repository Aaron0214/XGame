package com.xc.snake;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class MainFrame2 implements ActionListener,KeyListener{
	
	private JFrame frame;
	private GamePanel2 panel;
	private JToolBar toolbar;
	private JButton button;
	private boolean isleft = false;
	private boolean isright = false;
	private boolean isup = true;
	private boolean isdown = false;
	
	public MainFrame2(){
		frame = new JFrame();
		frame.setLayout(null);
		
		//工具栏
		button = new JButton();
		button.setText("start");
		button.setContentAreaFilled(false);
		button.setBorder(null);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		toolbar = new JToolBar();
		toolbar.setBounds(new Rectangle(0,0,500,20));
		toolbar.add(button);
		
		//主程序panel
		panel =  new GamePanel2();
		panel.init();
		sort(panel.list);
		panel.setBounds(new Rectangle(0,20,500,480));
		
		frame.add(toolbar);
		frame.add(panel);
		
		Thread t = new Thread(new run_thread());
		t.start();
		
		button.addActionListener(this);
		frame.setFocusable(true);
		frame.addKeyListener(this);
		frame.setTitle("snake");
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		new MainFrame2();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button){
			System.out.println("2");
		}
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
			moveDown(panel.list);
			isup = false;
			isdown = true;
			isleft = false;
			isright = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
			moveUp(panel.list);
			isup = true;
			isdown = false;
			isleft = false;
			isright = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
			moveLeft(panel.list);
			isup = false;
			isdown = false;
			isleft = true;
			isright = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
			moveRight(panel.list);
			isup = false;
			isdown = false;
			isleft = false;
			isright = true;
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	//向上移动
	public void moveUp(List<SnakeBean> snakeBeanList){
		List<SnakeBean> a = new ArrayList<SnakeBean>();
		a.addAll(snakeBeanList);
//		ifNoRoad(a,"up");
//		ifNoRoad(snakeBeanList,"up");
		//先倒序
		if(isdown){
			sort(snakeBeanList);
		}
		snakeBeanList.remove(0);
		int size = snakeBeanList.size();
		SnakeBean snakeBean = new SnakeBean();
		snakeBean.setStartPointX(snakeBeanList.get(size -1).getStartPointX());
		snakeBean.setStartPointY(snakeBeanList.get(size -1).getStartPointY() - 5);
		snakeBean.setEndPointX(snakeBeanList.get(size -1).getEndPointX());
		snakeBean.setEndPointY(snakeBeanList.get(size -1).getStartPointY());
		snakeBeanList.add(snakeBean);
		panel.repaint();
	}
	
	//向左移动
	private void moveLeft(List<SnakeBean> snakeBeanList){
		List<SnakeBean> a = new ArrayList<SnakeBean>();
		a.addAll(snakeBeanList);
//		ifNoRoad(a,"left");
//		ifNoRoad(snakeBeanList,"left");
		if(isright){
			sort(snakeBeanList);
		}
		snakeBeanList.remove(0);
		int size = snakeBeanList.size();
		SnakeBean snakeBean = new SnakeBean();
		snakeBean.setStartPointX(snakeBeanList.get(size -1).getStartPointX() - 5);
		snakeBean.setStartPointY(snakeBeanList.get(size -1).getStartPointY());
		snakeBean.setEndPointX(snakeBeanList.get(size -1).getStartPointX());
		snakeBean.setEndPointY(snakeBeanList.get(size -1).getEndPointY());
		snakeBeanList.add(snakeBean);
		panel.repaint();
	}
	
	//向下移动
	private void moveDown(List<SnakeBean> snakeBeanList){
		List<SnakeBean> a = new ArrayList<SnakeBean>();
		a.addAll(snakeBeanList);
//		ifNoRoad(a,"down");
//		ifNoRoad(snakeBeanList,"down");
		if(isup){
			sort(snakeBeanList);
		}
		snakeBeanList.remove(0);
		int size = snakeBeanList.size();
		SnakeBean snakeBean = new SnakeBean();
		snakeBean.setStartPointX(snakeBeanList.get(size -1).getStartPointX());
		snakeBean.setStartPointY(snakeBeanList.get(size -1).getStartPointY() + 5);
		snakeBean.setEndPointX(snakeBeanList.get(size -1).getEndPointX());
		snakeBean.setEndPointY(snakeBeanList.get(size -1).getStartPointY());
		snakeBeanList.add(snakeBean);
		panel.repaint();
	}
	
	private void moveRight(List<SnakeBean> snakeBeanList){
		List<SnakeBean> a = new ArrayList<SnakeBean>();
		a.addAll(snakeBeanList);
//		ifNoRoad(a,"right");
//		ifNoRoad(snakeBeanList,"right");
		//先正序
		if(isleft){
			sort(snakeBeanList);
		}
		snakeBeanList.remove(0);
		int size = snakeBeanList.size();
		SnakeBean snakeBean = new SnakeBean();
		snakeBean.setStartPointX(snakeBeanList.get(size -1).getStartPointX() + 5);
		snakeBean.setStartPointY(snakeBeanList.get(size -1).getStartPointY());
		snakeBean.setEndPointX(snakeBeanList.get(size -1).getStartPointX());
		snakeBean.setEndPointY(snakeBeanList.get(size -1).getEndPointY());
		snakeBeanList.add(snakeBean);
		panel.repaint();
		
	}
	
	//判断是否碰到自己的身体
	public void ifNoRoad(List<SnakeBean> snakeBeanList,String dire){
		int size = snakeBeanList.size();
		SnakeBean s = snakeBeanList.get(size - 1);
		Integer x = s.getStartPointX();
		Integer y = s.getStartPointY();
//		Integer x = 0;
//		Integer y = 0;
//		if("left".equals(dire)){
//			 x = s.getStartPointX() - 5;
//			 y = s.getStartPointY();
//		}else if("up".equals(dire)){
//			 x = s.getStartPointX();
//			 y = s.getStartPointY() - 5;
//		}else if("down".equals(dire)){
//			 x = s.getStartPointX();
//			 y = s.getStartPointY() + 5;
//		}else if("right".equals(dire)){
//			 x = s.getStartPointX() + 5;
//			 y = s.getStartPointY();
//		}
		int i = 0;
		for(SnakeBean snakeBean : snakeBeanList){
			if((x.equals(snakeBean.getStartPointX()) && y.equals(snakeBean.getStartPointY())) 
					|| (x.equals(snakeBean.getEndPointX()) && y.equals(snakeBean.getEndPointY()))){
				JOptionPane.showMessageDialog(frame, "Game over");
			}
			if(i == size - 1){
				break;
			}
		}
	}
	
	//将列表反向
	public void sort(List<SnakeBean> snakeBeanList){
		int n = snakeBeanList.size();
		for(int m = 0; m< n/2;m++){
			SnakeBean s = new SnakeBean(); 
			buildSnakBean(s,snakeBeanList.get(m));
			buildSnakBean(snakeBeanList.get(m),snakeBeanList.get(n - m -1));
			buildSnakBean(snakeBeanList.get(n - m -1),s);
		}
	}
	
	//构建一个新的snakeBean
	public void buildSnakBean(SnakeBean s1,SnakeBean s){
		s1.setStartPointX(s.getStartPointX());
		s1.setStartPointY(s.getStartPointY());
		s1.setEndPointX(s.getEndPointX());
		s1.setEndPointY(s.getEndPointY());
	}
	
	//AI线程
	class run_thread implements Runnable {

		@Override
		public void run() {
			int indexX = 0;
			int indexY = 0;
			
			while(indexX >= 0 && indexY >= 0){
				try {
					if(isup){
						moveUp(panel.list);
						panel.repaint();
					}else if(isdown){
						moveDown(panel.list);
						panel.repaint();
					}else if(isleft){
						moveLeft(panel.list);
						panel.repaint();
					}else if(isright){
						moveRight(panel.list);
						panel.repaint();
					}
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
			}
		}
	}
}

class GamePanel2 extends JPanel {

	private static final long serialVersionUID = 1L;
	
	List<SnakeBean> list = new ArrayList<SnakeBean>();
	
	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 500, 500);
		g.setColor(Color.white);
		if(null != list && list.size() > 0){
			for(SnakeBean snakeBean : list){
				g.fillRect(snakeBean.getStartPointX(),snakeBean.getStartPointY(), Math.abs(snakeBean.getStartPointX() - snakeBean.getEndPointX()), Math.abs(snakeBean.getStartPointY() - snakeBean.getEndPointY()));
			}
		}
	}
	
	public void init(){
		for(int i = 0;i<100/5;i++){
			SnakeBean snakeBean = new SnakeBean();
			snakeBean.setStartPointX(250);
			snakeBean.setStartPointY(150 + 5 * i);
			snakeBean.setEndPointX(255);
			snakeBean.setEndPointY(150 + 5 * (i+1));
			list.add(snakeBean);
		}
	}
}
