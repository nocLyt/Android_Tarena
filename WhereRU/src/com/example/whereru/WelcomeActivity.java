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
		// 获得动画
		animation = AnimationUtils.loadAnimation(this, R.anim.welcome);
		// 启动动画
		findViewById(R.id.iv_welcome).startAnimation(animation);
		// 设置动画监听器
		animation.setAnimationListener(this);
		
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		// 动画结束，跳转到 activity_main
		// 		声明意图对象
		Intent intent = new Intent();
		// 		为意图设置操作
		intent.setClass(this, MainActivity.class);
		//		跳转
		startActivity(intent);
		// 		关闭当前Acitivy
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
