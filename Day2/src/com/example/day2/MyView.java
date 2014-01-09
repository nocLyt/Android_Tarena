package com.example.day2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		// 绘制背景
		canvas.drawColor(Color.RED);
		// 定义画笔
		Paint paint= new Paint();
		paint.setColor(Color.RED);
		canvas.drawRect(10, 5, 110, 105, paint);
		canvas.drawText("大家好", 10, 130, paint);
		
		RectF rectf= new RectF(10, 130, 110, 230);
		canvas.drawArc(rectf, 0, 60, true, paint);
		canvas.drawLine(150, 10, 250, 110, paint);
		canvas.drawOval(new RectF(150,130,250,130), paint);
		
		
	}
	

}
