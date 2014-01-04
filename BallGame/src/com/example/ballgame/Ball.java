package com.example.ballgame;

public class Ball {
	private float x=100, y=100;			// 小球的圆心
	private float radius=20;	// 小球半径
	private float vx=5, vy=5; 	// 运动速度
	

	public Ball() {
	}

	public Ball(float x, float y, float radius, float vx, float vy) {
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.vx = vx;
		this.vy = vy;
	}

	// 移动小球
	public void move(){
		x+= vx;
		y+= vy;
	}
	
	/**
	 * 判断 小球在X轴方向是否越界 
	 * @return
	 * 	=1 越界
	 *  =0 没越界
	 */
	public boolean judgeOverX(){
		float nt= x;
		if(vx<0)	nt-= radius;
		else 	nt+= radius;
		if(nt<=0 || nt>=WindowInfo.getWindowWidth())
			return true;
		return false;
	}
	/**
	 * 判断 小球在Y轴方向是否越界
	 * @return
	 */
	public boolean judgeOverY(){
		float nt= y;
		if(vy<0)	nt-= radius;
		else	nt+= radius;
		if(nt<=0 || nt>=WindowInfo.getWindowHeight())
			return true;
		
		return false;
	}
	
	// 更改Y轴方向
	public void changeY(){
		vy= -vy;
	}
	// 更改X轴方向
	public void changeX(){
		vx= -vx;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getVx() {
		return vx;
	}

	public void setVx(float vx) {
		this.vx = vx;
	}

	public float getVy() {
		return vy;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}
	/**
	 * 转向 k代表的方向 (1..9) 八方向
	 * @param k
	 */
	public void tureTo(int k){
		if(k%3==1)
			vy= -1*Math.abs(vy);
		else if(k%3==0)
			vy= Math.abs(vy);
		if(k<=3 && k>=1)
			vx= -1*Math.abs(vx);
		else if(k>=7)
			vx= Math.abs(vx);
			
	}
	public float getRadius2(){
		return radius*radius;
	}
	
}
