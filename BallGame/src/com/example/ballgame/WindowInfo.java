package com.example.ballgame;

/**
 * 封装 窗口的信息
 * @author nocly_000
 *
 */
public class WindowInfo {
	private static float windowHeight, windowWidth;	// 窗口最大宽高

	public static float getWindowHeight() {
		return windowHeight;
	}

	public static void setWindowHeight(float windowheight) {
		WindowInfo.windowHeight = windowheight;
	}

	public static float getWindowWidth() {
		return windowWidth;
	}

	public static void setWindowWidth(float windowwidth) {
		WindowInfo.windowWidth = windowwidth;
	}


}
