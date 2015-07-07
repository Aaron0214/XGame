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
	private boolean isEnd = true;
	
	public MainFrame2(){
		frame = new JFrame();
		frame.setLayout(null);
		
		//工具栏
		button = new JButton();
		button.setText("开始");
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
			int num = JOptionPane.showConfirmDialog(frame, "确认开始?","提示", 0);
			if(num == 0){
				isEnd = false;
				frame.requestFocus();
				Thread t = new Thread(new run_thread());
				t.start();
			}else{
				isEnd = true;
				frame.requestFocus();
			}
		}
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(!isEnd){
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
		}else{
			JOptionPane.showMessageDialog(frame, "请先开始游戏！","提示",0);
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
		List<SnakeBean> a = new ArrayList<SnakeBean>();
		a.addAll(snakeBeanList);
		ifNoRoad(a);
		ifEat(snakeBeanList,panel.x,panel.y);
	}
	
	//向左移动
	private void moveLeft(List<SnakeBean> snakeBeanList){
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
		
		List<SnakeBean> a = new ArrayList<SnakeBean>();
		a.addAll(snakeBeanList);
		ifNoRoad(a);
		ifEat(snakeBeanList,panel.x,panel.y);
	}
	
	//向下移动
	private void moveDown(List<SnakeBean> snakeBeanList){
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
		
		List<SnakeBean> a = new ArrayList<SnakeBean>();
		a.addAll(snakeBeanList);
		ifNoRoad(a);
		ifEat(snakeBeanList,panel.x,panel.y);
	}
	
	private void moveRight(List<SnakeBean> snakeBeanList){
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
		
		List<SnakeBean> a = new ArrayList<SnakeBean>();
		a.addAll(snakeBeanList);
		ifNoRoad(a);
		ifEat(snakeBeanList,panel.x,panel.y);
	}
	
	//判断是否碰到自己的身体
	public void ifNoRoad(List<SnakeBean> snakeBeanList){
		int size = snakeBeanList.size();
		SnakeBean s = snakeBeanList.get(size - 1);
		Integer x = s.getStartPointX();
		Integer y = s.getStartPointY();
		int i = 0;
		if(x + 15 > 500 || x - 5 < 0 || y - 5 < 0 || y + 5 > 450){
			JOptionPane.showMessageDialog(frame, "Game over","游戏结束",0);
			isEnd = true;
			int num = JOptionPane.showConfirmDialog(frame, "是否重新开始？","",0);
			if(num == 0){
				isEnd = false;
				panel.init();
				sort(panel.list);
				panel.repaint();
				Thread t = new Thread(new run_thread());
				t.start();
			}else{
				isEnd = true;
			}
		}else{
			for(SnakeBean snakeBean : snakeBeanList){
				if((x.equals(snakeBean.getStartPointX()) && y.equals(snakeBean.getStartPointY())) 
						|| (x.equals(snakeBean.getEndPointX()) && y.equals(snakeBean.getEndPointY()))){
					JOptionPane.showMessageDialog(frame, "Game over","游戏结束",0);
					isEnd = true;
					int num = JOptionPane.showConfirmDialog(frame, "是否重新开始？","",0);
					if(num == 0){
						isEnd = false;
						panel.init();
						sort(panel.list);
						panel.repaint();
						Thread t = new Thread(new run_thread());
						t.start();
					}else{
						isEnd = true;
					}
				}
				if(i == size - 2){
					break;
				}
				i++;
			}
		}
	}
	
	public void ifEat(List<SnakeBean> snakeBeanList,Integer x,Integer y){
		int size = snakeBeanList.size();
		SnakeBean s = snakeBeanList.get(size -1);
		Integer startPointX = 0;
		Integer startPointY = 0;
		if(isup){
			startPointX = s.getStartPointX();
			startPointY = s.getStartPointY() - 5;
		}else if(isleft){
			startPointX = s.getStartPointX() - 5;
			startPointY = s.getStartPointY();
		}else if(isdown){
			startPointX = s.getStartPointX();
			startPointY = s.getStartPointY() + 5; 
		}else if(isright){
			startPointX = s.getStartPointX() + 5;
			startPointY = s.getStartPointY();
		}
		if(x.equals(startPointX) && y.equals(startPointY)){
			SnakeBean snake = new SnakeBean();
			snake.setStartPointX(x);
			snake.setStartPointY(y);
			snake.setEndPointX(x+5);
			snake.setEndPointY(y+5);
			snakeBeanList.add(snake);
			x = 5 * (int) (Math.random() * 99);
			y = 5 * (int) (Math.random() * 99);
			panel.x = x;
			panel.y = y;
			System.out.println("x:"+ panel.x + ",y:" +panel.y);
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
			
			while(!isEnd){
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
	
	int x = 50;
	int y = 50;
	List<SnakeBean> list = new ArrayList<SnakeBean>();
	
	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 500, 500);
		g.setColor(Color.white);
		g.fillRect(x,y,5,5);
		if(null != list && list.size() > 0){
			for(SnakeBean snakeBean : list){
				g.fillRect(snakeBean.getStartPointX(),snakeBean.getStartPointY(), Math.abs(snakeBean.getStartPointX() - snakeBean.getEndPointX()), Math.abs(snakeBean.getStartPointY() - snakeBean.getEndPointY()));
			}
		}
	}
	
	public void init(){
		list = new ArrayList<SnakeBean>();
		x = 5 * (int) (Math.random() * 99);
		y = 5 * (int) (Math.random() * 99);
		System.out.println("x:"+x+",y:"+y);
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
