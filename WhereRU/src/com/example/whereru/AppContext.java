package com.example.whereru;

import android.graphics.Color;
import android.widget.Button;

public class AppContext {

	// �����ֻ�����
	private static String PHONENUMBER;
	// ���ţ��绰��ť(true ����ing  fase�ر�ing)
	private static boolean SMSPHONE;
	private static boolean CALLBACK;
	private static boolean ALARM;
	private static boolean LOCATE;
	
	private static boolean OPENCLOSE;

	public static String getPHONENUMBER() {
		return PHONENUMBER;
	}

	public static void setPHONENUMBER(String pHONENUMBER) {
		PHONENUMBER = pHONENUMBER;
	}

	public static boolean isSMSPHONE() {
		return SMSPHONE;
	}

	public static void setSMSPHONE() {
		SMSPHONE = !SMSPHONE;
		return;
	}

	public static boolean isCALLBACK() {
		return CALLBACK;
	}

	public static void setCALLBACK() {
		CALLBACK = !CALLBACK;
		return;
	}

	public static boolean isALARM() {
		return ALARM;
	}

	public static void setALARM() {
		ALARM = !ALARM;
	}

	public static boolean isLOCATE() {
		return LOCATE;
	}

	public static void setLOCATE() {
		LOCATE = !LOCATE;
	}

	public static boolean isOPENCLOSE() {
		return OPENCLOSE;
	}

	public static void setOPENCLOSE() {
		OPENCLOSE = !OPENCLOSE;
		
	}
	
	
	
}
