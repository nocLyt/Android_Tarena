package com.example.ballgame;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Brick {
	private ArrayList<Rect> bricks= new ArrayList<Rect>();
	private int Num=10;
	private int bh=30, bw=50;	// 每个砖块的长 (bw) 宽(bh)
	
	Brick(){
		init_random();
	}
	
	/**
	 * 随机初始化   需用 Num
	 */
	void init_random(){
		bricks.clear();
		for (int i=1;i<=Num;i++){
			int left= (int)(Math.random()*((int)WindowInfo.getWindowWidth()));
			int top= (int)(Math.random()*((int)WindowInfo.getWindowHeight()));
			bricks.add(new Rect(left, top, left+bh, top+bw));
		}
	}
	
	
	/**
	 * 绘画
	 * @param canvas
	 * @param paint
	 */
	public void paintOnView(Canvas canvas, Paint paint){
		for(Rect i:bricks){
			canvas.drawRect(i, paint);
		}
	}
	

	public void judgeBallBricks(Ball ball){
		Rect flag= null;
		for(Rect i:bricks){
			int k= Control.judgeCircleRect(ball, i);
			if(k!=0)	
				flag= i;
		}
		bricks.remove((Rect)flag);
	}
	
	public int getBh() {
		return bh;
	}


	public void setBh(int bh) {
		this.bh = bh;
	}


	public int getBw() {
		return bw;
	}


	public void setBw(int bw) {
		this.bw = bw;
	}
	
	public int getNum() {
		return Num;
	}
	public void setNum(int num) {
		Num = num;
	}

	
	
	
}
