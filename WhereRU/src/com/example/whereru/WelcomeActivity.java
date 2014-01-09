package com.example.whereru;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

public class WelcomeActivity extends Activity implements AnimationListener {
	
	private Animation animation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		// ��ö���
		animation = AnimationUtils.loadAnimation(this, R.anim.welcome);
		// ��������
		findViewById(R.id.iv_welcome).startAnimation(animation);
		// ���ö���������
		animation.setAnimationListener(this);
		
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		// ������������ת�� activity_main
		// 		������ͼ����
		Intent intent = new Intent();
		// 		Ϊ��ͼ���ò���
		intent.setClass(this, MainActivity.class);
		//		��ת
		startActivity(intent);
		// 		�رյ�ǰAcitivy
		finish();
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}
}
