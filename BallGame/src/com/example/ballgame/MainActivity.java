package com.example.ballgame;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	MyView mv;
	LinearLayout main;
	
	
	private float lastX, lastY;
	private float nowX, nowY;	
	private float disX, disY;
	
	private Handler handler= new Handler(){
    	public void handleMessage(android.os.Message msg) {
    		mv.move();
    		mv.invalidate();
    	};
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏 
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏 

        // 获取分辨率范围。 设置窗口最大宽高
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        WindowInfo.setWindowWidth(mDisplayMetrics.widthPixels);
        WindowInfo.setWindowHeight(mDisplayMetrics.heightPixels);

        // BEGIN
        init();
        setContentView(mv);
        
    }
    void init(){
    	// 初始化小球控件
        mv= new MyView(this);
        // 打开线程
        new MyTread().start();
    }

    class MyTread extends Thread{
    	@Override
    	public void run() {
    		// TODO Auto-generated method stub
    		// super.run();
    		while(true){
    			handler.sendEmptyMessage(0);
    			try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    }
    

    

    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	// TODO Auto-generated method stub
    	
    	switch (event.getAction()) {
    	case MotionEvent.ACTION_DOWN :
    		nowX= event.getX();
    		lastX= nowX;
    		nowY= event.getY();
    		lastY= nowY;
    		break;
    	case MotionEvent.ACTION_MOVE:
    		nowX= event.getX();
    		nowY= event.getY();
    		disX= nowX-lastX;
    		disY= nowY-lastY;
    		mv.moveBoard(disX,disY);
    		mv.invalidate();
    		lastX= nowX;
    		lastY= nowY;
    		break;
    	default:
    		break;
    	}
    	return super.onTouchEvent(event);
    }

    
}
