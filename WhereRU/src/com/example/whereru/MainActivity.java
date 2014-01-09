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
		// ����smsphoneButton
		if(AppContext.isSMSPHONE()){	
			// ��� ��ɫͼƬ
			smsphoneButton.setBackgroundColor(Color.GREEN);
			smsphoneButton.setText("�ѿ������ź͵绰���");
		}
		else {			// �ر� ����SMS��PHONE����
			// ��ɺ�ɫͼƬ
			smsphoneButton.setBackgroundColor(Color.RED);
			smsphoneButton.setText("�������ź͵绰���");
		}
		// ���� callbackButton
		if(AppContext.isCALLBACK()){	//�� ����SMS��PHONE����
			// ��� ��ɫͼƬ
			callbackButton.setBackgroundColor(Color.GREEN);
			callbackButton.setText("�ѿ����ز�");
		}
		else {			// �ر� ����SMS��PHONE����
			// ��ɺ�ɫͼƬ
			callbackButton.setBackgroundColor(Color.RED);
			callbackButton.setText("�����ز�");
		}
		// ���� alarmButton
		if(AppContext.isALARM()){	//�� ����SMS��PHONE����
			// ��� ��ɫͼƬ
			alarmButton.setBackgroundColor(Color.GREEN);
			alarmButton.setText("�ѿ�������");
		}
		else {			// �ر� ����SMS��PHONE����
			// ��ɺ�ɫͼƬ
			alarmButton.setBackgroundColor(Color.RED);
			alarmButton.setText("��������");
		}
		// ���� locateButton
		if(AppContext.isLOCATE()){	//�� ����SMS��PHONE����
			// ��� ��ɫͼƬ
			locateButton.setBackgroundColor(Color.GREEN);
			locateButton.setText("�ѿ�����λ");
		}
		else {			// �ر� ����SMS��PHONE����
			// ��ɺ�ɫͼƬ
			locateButton.setBackgroundColor(Color.RED);
			locateButton.setText("������λ");
		}
		// ���� openCloseButton
		if(AppContext.isOPENCLOSE()){	//�� ����SMS��PHONE����
			// ��� ��ɫͼƬ
			openCloseButton.setBackgroundColor(Color.GREEN);
			openCloseButton.setText("�رռ��");
			startService(intent);
		}
		else {			// �ر� ����SMS��PHONE����
			// ��ɺ�ɫͼƬ
			openCloseButton.setBackgroundColor(Color.RED);
			openCloseButton.setText("�������");
			stopService(intent);
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ��ÿؼ�
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
//		// ����Ѿ����������Ҳ��ǰ��رռ�ذ�ť�� ��ʲô������
		if(AppContext.isOPENCLOSE() && ID!=R.id.openCloseButton )
			return; 
		// �жϡ�
		switch (ID){
		case R.id.smsphoneButton:
			// ��ذ�ť�Ƿ񱻴�
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
			// ��������ֻ��ĵ绰��
			String phoneNo= phoneNumEditText.getText().toString();
			// �ж��ֻ����ַ���ȷ
			if(phoneNo.equals("")){	// �ж��Ƿ�Ϊ��
				phoneNumEditText.setError("��������ȷ�ֻ���");
				return;
			}
			// ���������ֻ�����
			AppContext.setPHONENUMBER(phoneNo);
			// set
			AppContext.setOPENCLOSE();
			
			// ����SMSService���� �ŵ��˵�����upDate()��
			
			break;
		}
		upDateButton();
	}


}
