package com.xc.snake;

import java.io.Serializable;

public class SnakeBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer startPointX;
	
	private Integer startPointY;
	
	private Integer endPointX;
	
	private Integer endPointY;

	public Integer getStartPointX() {
		return startPointX;
	}

	public void setStartPointX(Integer startPointX) {
		this.startPointX = startPointX;
	}

	public Integer getStartPointY() {
		return startPointY;
	}

	public void setStartPointY(Integer startPointY) {
		this.startPointY = startPointY;
	}

	public Integer getEndPointX() {
		return endPointX;
	}

	public void setEndPointX(Integer endPointX) {
		this.endPointX = endPointX;
	}

	public Integer getEndPointY() {
		return endPointY;
	}

	public void setEndPointY(Integer endPointY) {
		this.endPointY = endPointY;
	}

}
