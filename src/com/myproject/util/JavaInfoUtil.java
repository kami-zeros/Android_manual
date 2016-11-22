package com.myproject.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.ard_myproject.R;
import com.myproject.entity.JavaContent;
import com.myproject.entity.JavaInfo;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class JavaInfoUtil {
	private static SQLiteDatabase database;
	public static final String DATABASE_FILENAME = "javamanual.db";// �����db�ļ�����
	public static final String PACKAGE_NAME = "com.example.ard_myproject";
	public static final String DATABASE_PATH = "/data"+Environment.getDataDirectory().getAbsolutePath()
			+ "/" + PACKAGE_NAME;//���ֻ��������ݿ��λ��
	
	public static SQLiteDatabase openDatabase(Context context) {
		try {
			String databaseFilename =DATABASE_PATH +"/" +DATABASE_FILENAME;
			File dir=new File(DATABASE_PATH);
			if (!dir.exists()) {
				dir.mkdir();//???
			}
			if (!(new File(databaseFilename)).exists()) {
				InputStream is = context.getResources().openRawResource(R.raw.javamanual);
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
	public static List<JavaInfo> getJavaInfos(Context context) { 
		List<JavaInfo> infoList=new ArrayList<JavaInfo>();
		
		SQLiteDatabase db=openDatabase(context);//�����ݿ���õ�����
		
//---�ڶ���---		
  		//String java_info_title = "select title from java_info";//�ӱ�java_info�в�ѯ
		Cursor cursor = null;
		cursor = db.query("java_info", null, "id", null, null, null, null);//desc����asc����
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			int id=cursor.getInt(cursor.getColumnIndex("id"));
			String title=cursor.getString(cursor.getColumnIndex("title"));
			int importance=cursor.getInt(cursor.getColumnIndex("importance"));
//			Log.i("TAG", "���У�"+ title);
			JavaInfo info=new JavaInfo();
			info.setId(id);
			info.setTitle(title);
			info.setImportance(importance);
			infoList.add(info);
			cursor.moveToNext();
		}
//		cursor.close();
		Log.i("TAG", "���⣺"+ infoList);		
		
		return infoList;
	}
	
	
	
	/***--�˴�sql��ֵ���ݿ���ĸ��ű�--*/
	public static List<JavaContent> getJavaContent(String sql, Context context) {
		List<JavaContent> contentList=new ArrayList<JavaContent>();
		
		SQLiteDatabase db=openDatabase(context);
		
//		String javacontent_title="select title from java_basic"; 
//		Cursor cursor=db.rawQuery(javacontent_title, null);
		
		Cursor cursor=db.query(sql, null, "id", null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			int id=cursor.getInt(cursor.getColumnIndex("id"));//�˴��ַ���ֵ��Ҫ�����ݿ��ֵһ��
			String content=cursor.getString(cursor.getColumnIndex("content"));
			int importance=cursor.getInt(cursor.getColumnIndex("importance"));
			String title=cursor.getString(cursor.getColumnIndex("title"));
			int showFlag = cursor.getInt(cursor.getColumnIndex("showFlag"));
//			Log.d("TAG", "��ţ�"+ title);
			
			JavaContent javaContent=new JavaContent();
			javaContent.setId(id);
			
			if (importance==9) {
				javaContent.setTitle(title);
			
			} else {
				javaContent.setTitle2(title);
			}
			
			javaContent.setContent(content);
			javaContent.setImportance(importance);
			javaContent.setShowFlag(showFlag);
			
			contentList.add(javaContent);
			
			cursor.moveToNext();
		}
		cursor.close();
		
		return contentList;
	}
	
}
