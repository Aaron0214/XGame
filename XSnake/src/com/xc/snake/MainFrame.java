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
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class MainFrame implements ActionListener,KeyListener{
	
	private JFrame frame;
	private GamePanel panel;
	private JToolBar toolbar;
	private JButton button;
	private boolean isleft = false;
	private boolean isright = false;
	private boolean isup = true;
	private boolean isdown = false;
	private boolean ismove = false;
	private boolean ischange = false;
	private String lastdire = "";
	
	public MainFrame(){
		frame = new JFrame();
		frame.setLayout(null);
		
		button = new JButton();
		button.setText("start");
		button.setContentAreaFilled(false);
		button.setBorder(null);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		toolbar = new JToolBar();
		toolbar.setBounds(new Rectangle(0,0,500,20));
		toolbar.add(button);
		
		panel =  new GamePanel();
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
		new MainFrame();
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
			if(isup){
				panel.lastPointX =  panel.startPointX;
				panel.lastPointY =  panel.startPointY + 5;
				panel.height = 5; 
				panel.lastheight = panel.length - panel.height;
				panel.lastwidth = 5;
				ismove = true;
				lastdire = "up";
			}else if(isleft){
				panel.lastPointX =  panel.startPointX;
				panel.lastPointY =  panel.startPointY;
				panel.lastwidth = panel.length;
				panel.lastheight = 5;
				panel.width = 5;
				panel.height = 5;
				ismove = false;
				lastdire = "left";
			}else if(isright){
				panel.lastPointX =  panel.startPointX;
				panel.lastPointY =  panel.startPointY;
				panel.startPointX = panel.startPointX + panel.length - 5;
				panel.lastwidth = panel.length;
				panel.lastheight = 5;
				panel.width = 5;
				panel.height = 5;
				lastdire = "right";
				ismove = false;
			}
			if(ismove){
				panel.startPointY += 5;
				panel.lastPointY = panel.startPointY +5;
			}else{
				if("left".equals(lastdire)){
					panel.height += 5;
					panel.lastwidth -= 5;
					if(panel.lastwidth == 0){
						ismove = true;
					}
				}else if("right".equals(lastdire)){
					panel.height += 5;
					panel.lastPointX +=5;
					panel.lastwidth -=5;
					if(panel.lastwidth == 0){
						ismove = true;
					}
				}
			}
			panel.repaint();
			isup = false;
			isdown = true;
			isright = false;
			isleft = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
			if(isdown){
				ischange = false;
			} else if(isleft){
				panel.lastPointX =  panel.startPointX;
				panel.lastPointY =  panel.startPointY;
				panel.lastwidth = panel.length;
				panel.lastheight = 5;
				panel.width = 5;
				ischange = true;
				lastdire = "left";
			}else if(isright){
				panel.lastPointX =  panel.startPointX;
				panel.lastPointY =  panel.startPointY;
				panel.startPointX = panel.startPointX + panel.length - 5;
				panel.lastwidth = panel.length;
				panel.lastheight = 5;
				panel.width = 5;
				panel.height = 5;
				lastdire = "right";
				ischange = true;
			}else{
				Thread t = new Thread(new run_thread());
				t.start();
			}
			panel.startPointY -= 5;
			if(ischange){
				panel.lastwidth -=5;
				if("right".equals(lastdire)){
					panel.lastPointX +=5;
				}
			}else{
				panel.lastheight -=5;
			}
			panel.height += 5;
			panel.repaint();
			
			isup = true;
			isdown = false;
			isright = false;
			isleft = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
			if(isup){
				panel.lastPointX =  panel.startPointX;
				panel.lastPointY =  panel.startPointY;
				panel.lastheight = panel.length;
				panel.width = 5;
				ismove = false;
			}else if(isdown){
				panel.lastPointX =  panel.startPointX;
				panel.lastPointY =  panel.startPointY;
				panel.startPointX = panel.startPointX;
				panel.startPointY = panel.startPointY + panel.length;
				panel.lastheight = panel.length;
				panel.width = 5;
				ismove = true;
			}else if(isright){
				ismove = false;
			}else{
				Thread t = new Thread(new run_thread());
				t.start();
			}
			if(ismove){
				panel.lastPointY += 5;
			}
			panel.startPointX -= 5;
			panel.lastheight -=5;
			panel.lastwidth = 5;
			panel.width += 5;
			panel.height = 5;
			panel.repaint();
			
			isup = false;
			isdown = false;
			isright = false;
			isleft = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
			if(isup){
				panel.lastPointX =  panel.startPointX;
				panel.lastPointY =  panel.startPointY;
				panel.lastheight = panel.length;
				panel.lastwidth = 5;
				panel.width = 5;
				panel.height = 5;
				ismove = false;
				lastdire = "up";
			}else if(isdown){
				panel.lastPointX =  panel.startPointX;
				panel.lastPointY =  panel.startPointY;
				panel.startPointY = panel.startPointY + panel.length;
				panel.lastheight = panel.length;
				panel.lastwidth = 5;
				panel.width = 5;
				panel.height = 5;
				ismove = false;
				lastdire = "down";
			}else if(isleft){
				ismove = true;
			}
			if(ismove){
				panel.startPointX +=5;
				panel.lastPointX = panel.startPointX +5;
			}else{
				panel.lastheight -=5;
				panel.width += 5;
				if("down".equals(lastdire)){
					panel.lastPointY +=5;
				}
				if(panel.lastheight == 0){
					ismove = true;
				}
				
			}
			panel.repaint();
			isup = false;
			isdown = false;
			isright = true;
			isleft = false;
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	class run_thread implements Runnable {

		@Override
		public void run() {
			int indexX = 0;
			int indexY = 0;
			boolean isstart = false;
			
			while(indexX >= 0 && indexY >= 0 && isstart){
				try {
					if(isup){
						indexY = panel.startPointY - 10;
						panel.startPointY -= 5;
						panel.lastheight -=5;
						panel.height += 5;
					}else if(isdown){
						indexY = 500 - (panel.startPointY + panel.height);
						panel.startPointY += 5;
					}else if(isleft){
						System.out.println("startx:"+panel.startPointX+",starty:"+panel.startPointY+",lastx:"+panel.lastPointY+",lasty:"+ panel.lastPointY );
						indexX =  panel.startPointX;
						panel.startPointX -= 5;
						panel.lastheight -=5;
						panel.lastwidth = 5;
						panel.width += 5;
						panel.height = 5;
					}else if(isright){
						indexX =  500 - (panel.startPointX + panel.width);
						panel.startPointX += 5;
					}
					
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				panel.repaint();
			}
			
			
			
		}
		
	}
}

class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	int startPointX = 250;
	int startPointY = 150;
	int length = 100;
	int width = 5;
	int height = 5;
	int lastwidth = 5;
	int lastheight = length - height;
	int lastPointX = startPointX;
	int lastPointY = startPointY + 5;
	List<List<Integer>> list = new ArrayList<List<Integer>>();
	
	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 500, 500);
		g.setColor(Color.white);
//		g.fillRect(startPointX, startPointY,Math.abs(startPointX - lastPointX) > width ? Math.abs(startPointX - lastPointX):width, Math.abs(startPointY - lastPointY) < height?Math.abs(startPointY - lastPointY):height);
//		g.fillRect(lastPointX, lastPointY, (width - Math.abs(startPointX - lastPointX)) > 0 ? width - Math.abs(startPointX - lastPointX):0 ,  (height - Math.abs(startPointY - lastPointY) > 0) ?height - Math.abs(startPointY - lastPointY):0);
		g.fillRect(startPointX, startPointY,width >= length ? length:width,height >= length ? length :height);
		g.fillRect(lastPointX, lastPointY,lastwidth,lastheight);
	}
}
