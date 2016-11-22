package com;

import java.util.Stack;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

/**
 * 
 * TODO:Activity�Ĺ�����
 * 
 * @author huangshang 2015-11-15 ����1:37:23
 * @Modified_By:
 */
public class AppManager {
	private static AppManager instance = new AppManager();
	private Stack<Activity> allActivity;// ���ڱ���򿪵�Activity

	/**
	 * ˽�еĹ��췽��
	 */
	private AppManager() {
		allActivity = new Stack<Activity>();
	}

	/**
	 * ����
	 * 
	 * @return instance�����Ψһ��������
	 */
	public static AppManager getInstance() {
		return instance;
	}

	/**
	 * ����򿪵�Activity
	 * 
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		allActivity.add(activity);
	}

	/**
	 * �����˳�
	 */

	@SuppressWarnings("deprecation")
	public void appExit(Context context) {
		// 1.�ر�����Activity
		for (Activity activity : allActivity) {
			if (activity != null) {
				activity.finish();
			}
		}
		// ��ռ���
		allActivity.clear();
		// 2.������
		ActivityManager manager = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
		manager.restartPackage(context.getPackageName());
		// 2.ϵͳ�˳�
		System.exit(0);
	}
}
