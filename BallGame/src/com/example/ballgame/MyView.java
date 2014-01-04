package com.example.ballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyView extends View {
	


	public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		
		// 初始化小球
		ball= new Ball();
		// 初始化挡板
		int maxX= (int)WindowInfo.getWindowWidth();
		int maxY= (int)WindowInfo.getWindowHeight();
		int y1= maxY/9;
		int x1= maxX/3;
		board= new Rect(x1,maxY-y1,x1*2,maxY-y1+10);
		// 初始化砖块
		bricks= new Brick();
		
		// 开始 刷新进程 
		new CreateBricks().start();
	}

	

	private Paint paint= new Paint();	// 初始化画笔
	private Ball ball;					// 小球
	private Rect board;					// 挡板
	private Brick bricks;

	private Rect[][] tt= new Rect[2][2]; 
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		// 画上小球
		paint.setColor(Color.RED);
		canvas.drawCircle(ball.getX(), ball.getY(), ball.getRadius(), paint);
		
		// 画上挡板
		paint.setColor(Color.GRAY);
		paint.setStyle(Paint.Style.FILL);
		canvas.drawRect(board, paint);
		
		// 画上砖块
		bricks.paintOnView(canvas, paint);
		
	}
	
	// 移动小球
	public void move(){
		// 判断是否和边界相撞
		Control.judgeBallBound(ball);
		// 判断是否与挡板相撞
		Control.judgeCircleRect(ball, board);
		// 判断是否与砖块相撞
		bricks.judgeBallBricks(ball);
		// 小球移动
		ball.move();
	}
	
	/**
	 * 刷新砖块
	 * @author nocly_000
	 *
	 */
    class CreateBricks extends Thread{
    	@Override
    	public void run() {
    		// TODO Auto-generated method stub
    		// super.run();
    		while(true){
    			bricks.init_random();
    			try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    }
    
    
    public void moveBoard(float dis){
    	Rect nt= new Rect(board.left+(int)dis, board.top, board.right+(int)dis, board.bottom);
    	if(Control.judgeCircleRect(ball, nt)==0){
    		board= nt;
    	}
    }
    public void moveBoard(float disX, float disY){
    	Rect nt= new Rect(board.left+(int)disX, board.top+(int)disY, board.right+(int)disX, board.bottom+(int)disY);
    	boolean flag= true;
    	// 不能和球发生碰撞
    	if(Control.judgeCircleRect(ball, nt)!=0){	// 发生碰撞
    		flag= false;
    	}
    	// 不能越过边界

    	if(flag)
    		board= nt;
    }
    
}
