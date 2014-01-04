package com.example.ballgame;

import android.graphics.Rect;

public class Control {

	/**
	 * 判断球是否与边界相撞。
	 *  最好有返回值判断是哪个边界
	 * @param ball
	 */
	public static void judgeBallBound(Ball ball){
		if(ball.judgeOverX()==true)
			ball.changeX();
		if(ball.judgeOverY()==true)
			ball.changeY();
	}
	
	public static int judgeCircleRect(Ball ball, Rect rect){
		int k= collisionCircleRect(ball, rect);
		if(k!=0)
			ball.tureTo(k);
		return k;
	}
	/**
	 * 判断 Ball 和 Rect是否相撞
	 * 
	 * 
	 * 
	 * @param ball
	 * @param rect
	 * @return 
	 * =0 没碰撞
	 * = 1 2 3 4 5 6 7 8 八个碰撞区域
	 */
	public static int collisionCircleRect(Ball ball, Rect rect){
		float dis= 0;
		int k=0;
		if(ball.getX()<rect.left){
			if(ball.getY()<rect.top){			// 1-th
				dis= disPointPoint(ball.getX(), ball.getY(), rect.left, rect.top);
				if(dis<=ball.getRadius2()){ // 碰撞
					k= 1;
				}
			}
			else if(ball.getY()>rect.bottom){	// 3-th
				dis= disPointPoint(ball.getX(), ball.getY(), rect.left, rect.bottom);
				if(dis<=ball.getRadius2()){ // 碰撞
					k= 3;
				}
			}
			else {								// 2-th
				dis= rect.left-ball.getX();
				if(dis<=ball.getRadius()){
					k= 2;
				}
			}
		}
		else if(ball.getX()>rect.right){
			if(ball.getY()<rect.top){			// 7-th
				dis= disPointPoint(ball.getX(), ball.getY(), rect.right, rect.top);
				if(dis<=ball.getRadius2()){ 
					k= 7;
				}
			}
			else if(ball.getY()>rect.bottom){	// 9-th
				dis= disPointPoint(ball.getX(), ball.getY(), rect.right, rect.bottom);
				if(dis<=ball.getRadius2()){ 
					k= 9;
				}
			}
			else {								// 8-th
				dis= ball.getX()-rect.right;
				if(dis<=ball.getRadius()){
					k= 8;
				}
			}
		}
		else {
			if(ball.getY()<rect.top){			// 4-th
				dis= rect.top-ball.getY();
				if(dis<=ball.getRadius()){ 
					k= 4;
				}
			}
			else if(ball.getY()>rect.bottom){	// 6-th
				dis= ball.getY() - rect.bottom;
				if(dis<=ball.getRadius()){ 
					k= 6;
				}
			}
		}		
	
		
		return k;
	}

	public static float disPointPoint(float x0, float y0, float x1, float y1){
		float dis= (x0-x1)*(x0-x1)+(y0-y1)*(y0-y1);
		return dis;
	}
	
}
