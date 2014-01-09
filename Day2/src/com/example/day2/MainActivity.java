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
    
    
    // ������ �������Ķ���
    

    private void addListener(){
        okBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// ��ȡ������û���
				String userName= userNameEt.getText().toString();
				// ��ȡ�ǳ�
				String nickName= nickEt.getText().toString();
				// ��ȡ��һ������
				String pd1= pd1Et.getText().toString();
				// ��ȡ�ڶ�������
				String pd2= pd2Et.getText().toString();
				
				// 1. �û�������Ϊ��  2.�ǳƲ���Ϊ�� 3. �����������һ��

			}
		});
        
    }
    
}
