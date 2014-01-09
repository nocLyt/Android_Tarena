package com.example.whereru;

import java.io.IOException;
import java.net.URI;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.telephony.SmsManager;
/**
 * ������
 * 
 * �ṩ����绰�� ���Ͷ��ţ�����ȹ���
 * 
 * @author nocly_000
 *
 */
public class ActionUtils {
	
	/**
	 * ���Ͷ���  
	 * ��Ҫ�����Ȩ�� android.permission.SEND_SMS
	 * @param phone
	 * @param text
	 */
	public static void sendSMS(String phone, String text){
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(phone, null, text, null, null);
	}
	/**
	 * ��绰
	 * ���Ȩ�� android.permission.CALL_PHONE
	 * ����ʾ��ͼ�� Intent.ACTION_CALL�� Ҫ�ѵ绰�ŷ�װ��Uri
	 * @param phone
	 * @param context
	 */
	public static void call(String phone, Context context){
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		// �ѵ绰�ŷ�װ�� Uri
		Uri uri = Uri.parse("tel:"+phone);
		intent.setData(uri);
		// ����һ���µ�Activity
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		context.startActivity(intent);
	}
	
	/**
	 * ����  == ��������
	 * SoundPool �ʺϲ��Ŷ����� 
	 */
	
	public static void ring(Context context){
		MediaPlayer music = MediaPlayer.create(context, R.raw.ring);
		if (music !=null){
			music.stop();
		}
		music.start();
	}
	
	
}
