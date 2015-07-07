package com.xc.snake;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

	
	private static final long serialVersionUID = 1L;
	
	public GameFrame(){
		setSize(500,500);
		setVisible(true);
	}
	
	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 500, 500);
		g.setColor(Color.white);
		g.fillRect(250, 150, 5, 100);
	}
	
	public static void main(String[] args){
		new GameFrame();
	}
	
}
