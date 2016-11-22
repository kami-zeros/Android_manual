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
	public static final String DATABASE_ANDROID_FILENAME = "android_manual.db";// 这个是db文件名字
	public static final String PACKAGE_NAME = "com.example.ard_myproject";//
	public static final String DATABASE_PATH = "/data"+Environment.getDataDirectory().getAbsolutePath()
			+ "/" + PACKAGE_NAME; //在手机里存放数据库的位置
	
	public static SQLiteDatabase openDatabase(Context context) {
		try {
			String databaseFilename =DATABASE_PATH +"/" +DATABASE_ANDROID_FILENAME;
			File dir=new File(DATABASE_PATH);
			if (!dir.exists()) {
				dir.mkdir();//???
			}
			if (!(new File(databaseFilename)).exists()) {//判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
				InputStream is = context.getResources().openRawResource(R.raw.android_manual);//欲导入的数据库
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
			throw new RuntimeException("有误");
		} 
	}
	
	/**
	 * 从数据库里得到数据
	 */
	public static List<JavaContent> getAndroidContent(String sql, Context context) {
		List<JavaContent> contentList=new ArrayList<JavaContent>();
		
		SQLiteDatabase db=openDatabase(context);//调用上面方法
		
		String javacontent_title="select title from android_info"; 
		Cursor cursor=db.rawQuery(javacontent_title, null);
		
//		Cursor cursor=db.query(sql, null, "id", null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
//			int id=cursor.getInt(cursor.getColumnIndex("id"));//此处字符串值都要与数据库键值一样
//			
//			String content=cursor.getString(cursor.getColumnIndex("content"));
//			int importance=cursor.getInt(cursor.getColumnIndex("importance"));
			String title=cursor.getString(cursor.getColumnIndex("title"));
			Log.d("TAG", "cursor:"+title);
//			int showFlag = cursor.getInt(cursor.getColumnIndex("showFlag"));
////			Log.d("TAG", "编号："+ title);
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
