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
 * 工具类
 * 
 * 提供拨打电话， 发送短信，响铃等功能
 * 
 * @author nocly_000
 *
 */
public class ActionUtils {
	
	/**
	 * 发送短信  
	 * 需要先添加权限 android.permission.SEND_SMS
	 * @param phone
	 * @param text
	 */
	public static void sendSMS(String phone, String text){
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(phone, null, text, null, null);
	}
	/**
	 * 打电话
	 * 添加权限 android.permission.CALL_PHONE
	 * 用显示意图： Intent.ACTION_CALL。 要把电话号封装成Uri
	 * @param phone
	 * @param context
	 */
	public static void call(String phone, Context context){
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		// 把电话号封装成 Uri
		Uri uri = Uri.parse("tel:"+phone);
		intent.setData(uri);
		// 启动一个新的Activity
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		context.startActivity(intent);
	}
	
	/**
	 * 报警  == 播放音乐
	 * SoundPool 适合播放短音乐 
	 */
	
	public static void ring(Context context){
		MediaPlayer music = MediaPlayer.create(context, R.raw.ring);
		if (music !=null){
			music.stop();
		}
		music.start();
	}
	
	
}
