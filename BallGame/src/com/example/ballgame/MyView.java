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
		
		
		// ��ʼ��С��
		ball= new Ball();
		// ��ʼ������
		int maxX= (int)WindowInfo.getWindowWidth();
		int maxY= (int)WindowInfo.getWindowHeight();
		int y1= maxY/9;
		int x1= maxX/3;
		board= new Rect(x1,maxY-y1,x1*2,maxY-y1+10);
		// ��ʼ��ש��
		bricks= new Brick();
		
		// ��ʼ ˢ�½��� 
		new CreateBricks().start();
	}

	

	private Paint paint= new Paint();	// ��ʼ������
	private Ball ball;					// С��
	private Rect board;					// ����
	private Brick bricks;

	private Rect[][] tt= new Rect[2][2]; 
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		// ����С��
		paint.setColor(Color.RED);
		canvas.drawCircle(ball.getX(), ball.getY(), ball.getRadius(), paint);
		
		// ���ϵ���
		paint.setColor(Color.GRAY);
		paint.setStyle(Paint.Style.FILL);
		canvas.drawRect(board, paint);
		
		// ����ש��
		bricks.paintOnView(canvas, paint);
		
	}
	
	// �ƶ�С��
	public void move(){
		// �ж��Ƿ�ͱ߽���ײ
		Control.judgeBallBound(ball);
		// �ж��Ƿ��뵲����ײ
		Control.judgeCircleRect(ball, board);
		// �ж��Ƿ���ש����ײ
		bricks.judgeBallBricks(ball);
		// С���ƶ�
		ball.move();
	}
	
	/**
	 * ˢ��ש��
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
    	// ���ܺ�������ײ
    	if(Control.judgeCircleRect(ball, nt)!=0){	// ������ײ
    		flag= false;
    	}
    	// ����Խ���߽�

    	if(flag)
    		board= nt;
    }
    
}
