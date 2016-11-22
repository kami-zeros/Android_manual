package com.myproject.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.example.ard_myproject.R;
import com.myproject.entity.JavaContent;
import com.myproject.entity.JavaInfo;

public class AndroidInfoUtil {

	private static SQLiteDatabase database;
	public static final String DATABASE_ANDROID_FILENAME = "android_manual.db";// �����db�ļ�����
	public static final String PACKAGE_NAME = "com.example.ard_myproject";//
	public static final String DATABASE_PATH = "/data"+Environment.getDataDirectory().getAbsolutePath()
			+ "/" + PACKAGE_NAME; //���ֻ��������ݿ��λ��
	
	public static SQLiteDatabase openDatabase(Context context) {
		try {
			String databaseFilename =DATABASE_PATH +"/" +DATABASE_ANDROID_FILENAME;
			File dir=new File(DATABASE_PATH);
			if (!dir.exists()) {
				dir.mkdir();//???
			}
			if (!(new File(databaseFilename)).exists()) {//�ж����ݿ��ļ��Ƿ���ڣ�����������ִ�е��룬����ֱ�Ӵ����ݿ�
				InputStream is = context.getResources().openRawResource(R.raw.android_manual);//����������ݿ�
				FileOutputStream fos = new FileOutputStream(databaseFilename);
				byte[] buffer = new byte[1024];
				int count=0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				
				fos.close();
				is.close();
			}
			database = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);
			return database;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("����");
		} 
	}
	
	/**
	 * �����ݿ���õ�����
	 */
	public static List<JavaContent> getAndroidContent(String sql, Context context) {
		List<JavaContent> contentList=new ArrayList<JavaContent>();
		
		SQLiteDatabase db=openDatabase(context);//�������淽��
		
		String javacontent_title="select title from android_info"; 
		Cursor cursor=db.rawQuery(javacontent_title, null);
		
//		Cursor cursor=db.query(sql, null, "id", null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
//			int id=cursor.getInt(cursor.getColumnIndex("id"));//�˴��ַ���ֵ��Ҫ�����ݿ��ֵһ��
//			
//			String content=cursor.getString(cursor.getColumnIndex("content"));
//			int importance=cursor.getInt(cursor.getColumnIndex("importance"));
			String title=cursor.getString(cursor.getColumnIndex("title"));
			Log.d("TAG", "cursor:"+title);
//			int showFlag = cursor.getInt(cursor.getColumnIndex("showFlag"));
////			Log.d("TAG", "��ţ�"+ title);
//			
//			JavaContent javaContent=new JavaContent();
//			javaContent.setId(id);
//			
//			if (importance==9) {
//				javaContent.setTitle(title);
//			
//			} else {
//				javaContent.setTitle2(title);
//			}
//			
//			javaContent.setContent(content);
//			javaContent.setImportance(importance);
//			javaContent.setShowFlag(showFlag);
//			
//			contentList.add(javaContent);
			
			cursor.moveToNext();
		}
		cursor.close();
		
		return contentList;
	}
}
