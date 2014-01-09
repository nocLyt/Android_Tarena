package com.example.day2;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private Button okBtn;
	private EditText userNameEt, pd1Et, pd2Et, nickEt; 
	
	private  Handler handler= new Handler(){
		public void handleMessage(android.os.Message msg){
			if(msg.what == 0){
				for (int i=0;i<ids.length;i++){
					views[i].setBackgroundColor(Color.parseColor(colors[(i+index)%ids.length]));
				}
				index++;
			}
		}
	};
	
	private int index= 0;
	private String[] colors= new String[]{"#ff0000","#dd0000","#00ff00"};
	private int[] ids= new int[]{R.id.textView1,R.id.textView2,R.id.textView3};
	private TextView[] views= new TextView[ids.length];
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afternoon);
        
        initAfternoon();
    }

    void initAfternoon(){
    	for (int i=0;i<ids.length;i++){
    		views[i]= (TextView)findViewById(ids[i]);
    	}
    	new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(0);
			}
    	}, 0, 200);
    	
    }
    

    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    // 下面是 上午所的东西
    

    private void addListener(){
        okBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// 获取输入的用户名
				String userName= userNameEt.getText().toString();
				// 获取昵称
				String nickName= nickEt.getText().toString();
				// 获取第一次密码
				String pd1= pd1Et.getText().toString();
				// 获取第二次密码
				String pd2= pd2Et.getText().toString();
				
				// 1. 用户名不能为空  2.昵称不能为空 3. 两次密码必须一样

			}
		});
        
    }
    
}
