package com.example.whereru;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	TextView title;
	EditText phoneNumEditText;
	Button smsphoneButton;
	Button callbackButton;
	Button alarmButton;
	Button locateButton;
	Button openCloseButton;
	
	Intent intent;
	
	public void upDateButton(){
		// 更新smsphoneButton
		if(AppContext.isSMSPHONE()){	
			// 设成 绿色图片
			smsphoneButton.setBackgroundColor(Color.GREEN);
			smsphoneButton.setText("已开启短信和电话监控");
		}
		else {			// 关闭 监听SMS和PHONE功能
			// 设成红色图片
			smsphoneButton.setBackgroundColor(Color.RED);
			smsphoneButton.setText("开启短信和电话监控");
		}
		// 更新 callbackButton
		if(AppContext.isCALLBACK()){	//打开 监听SMS和PHONE功能
			// 设成 绿色图片
			callbackButton.setBackgroundColor(Color.GREEN);
			callbackButton.setText("已开启回拨");
		}
		else {			// 关闭 监听SMS和PHONE功能
			// 设成红色图片
			callbackButton.setBackgroundColor(Color.RED);
			callbackButton.setText("开启回拨");
		}
		// 更新 alarmButton
		if(AppContext.isALARM()){	//打开 监听SMS和PHONE功能
			// 设成 绿色图片
			alarmButton.setBackgroundColor(Color.GREEN);
			alarmButton.setText("已开启警报");
		}
		else {			// 关闭 监听SMS和PHONE功能
			// 设成红色图片
			alarmButton.setBackgroundColor(Color.RED);
			alarmButton.setText("开启警报");
		}
		// 更新 locateButton
		if(AppContext.isLOCATE()){	//打开 监听SMS和PHONE功能
			// 设成 绿色图片
			locateButton.setBackgroundColor(Color.GREEN);
			locateButton.setText("已开启定位");
		}
		else {			// 关闭 监听SMS和PHONE功能
			// 设成红色图片
			locateButton.setBackgroundColor(Color.RED);
			locateButton.setText("开启定位");
		}
		// 更新 openCloseButton
		if(AppContext.isOPENCLOSE()){	//打开 监听SMS和PHONE功能
			// 设成 绿色图片
			openCloseButton.setBackgroundColor(Color.GREEN);
			openCloseButton.setText("关闭监控");
			startService(intent);
		}
		else {			// 关闭 监听SMS和PHONE功能
			// 设成红色图片
			openCloseButton.setBackgroundColor(Color.RED);
			openCloseButton.setText("开启监控");
			stopService(intent);
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 获得控件
		title = (TextView) findViewById(R.id.title);
		phoneNumEditText= (EditText) findViewById(R.id.phonenumEditText);
		smsphoneButton = (Button) findViewById(R.id.smsphoneButton);
		callbackButton = (Button) findViewById(R.id.callbackButton);
		alarmButton = (Button) findViewById(R.id.alarmButton);
		locateButton = (Button) findViewById(R.id.locateButton);
		openCloseButton = (Button) findViewById(R.id.openCloseButton);
		
		smsphoneButton.setOnClickListener(this);
		callbackButton.setOnClickListener(this);
		alarmButton.setOnClickListener(this);
		locateButton.setOnClickListener(this);
		openCloseButton.setOnClickListener(this);
		intent = new Intent(this,SMSService.class);
		upDateButton();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int ID = v.getId();
//		// 监控已经开启，并且不是按关闭监控按钮， 就什么都不干
		if(AppContext.isOPENCLOSE() && ID!=R.id.openCloseButton )
			return; 
		// 判断。
		switch (ID){
		case R.id.smsphoneButton:
			// 监控按钮是否被打开
			AppContext.setSMSPHONE();	
			break;
		case R.id.callbackButton :
			AppContext.setCALLBACK();
			break;
		case R.id.alarmButton :
			AppContext.setALARM();
			break;
		case R.id.locateButton :
			AppContext.setLOCATE();
			break;
		case R.id.openCloseButton :
			// 获得主控手机的电话号
			String phoneNo= phoneNumEditText.getText().toString();
			// 判断手机号手否正确
			if(phoneNo.equals("")){	// 判断是否为空
				phoneNumEditText.setError("请输入正确手机号");
				return;
			}
			// 保存主控手机号码
			AppContext.setPHONENUMBER(phoneNo);
			// set
			AppContext.setOPENCLOSE();
			
			// 开启SMSService服务。 放到了到更新upDate()中
			
			break;
		}
		upDateButton();
	}


}
