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
	public static final String DATABASE_FILENAME = "javamanual.db";// 这个是db文件名字
	public static final String PACKAGE_NAME = "com.example.ard_myproject";
	public static final String DATABASE_PATH = "/data"+Environment.getDataDirectory().getAbsolutePath()
			+ "/" + PACKAGE_NAME;//在手机里存放数据库的位置
	
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
			throw new RuntimeException("有误");
		} 
	}
	
	/**
	 * 从数据库里得到数据
	 */
	public static List<JavaInfo> getJavaInfos(Context context) { 
		List<JavaInfo> infoList=new ArrayList<JavaInfo>();
		
		SQLiteDatabase db=openDatabase(context);//从数据库里得到数据
		
//---第二种---		
  		//String java_info_title = "select title from java_info";//从表java_info中查询
		Cursor cursor = null;
		cursor = db.query("java_info", null, "id", null, null, null, null);//desc降序，asc升序
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			int id=cursor.getInt(cursor.getColumnIndex("id"));
			String title=cursor.getString(cursor.getColumnIndex("title"));
			int importance=cursor.getInt(cursor.getColumnIndex("importance"));
//			Log.i("TAG", "所有："+ title);
			JavaInfo info=new JavaInfo();
			info.setId(id);
			info.setTitle(title);
			info.setImportance(importance);
			infoList.add(info);
			cursor.moveToNext();
		}
//		cursor.close();
		Log.i("TAG", "标题："+ infoList);		
		
		return infoList;
	}
	
	
	
	/***--此处sql是值数据库里的各张表--*/
	public static List<JavaContent> getJavaContent(String sql, Context context) {
		List<JavaContent> contentList=new ArrayList<JavaContent>();
		
		SQLiteDatabase db=openDatabase(context);
		
//		String javacontent_title="select title from java_basic"; 
//		Cursor cursor=db.rawQuery(javacontent_title, null);
		
		Cursor cursor=db.query(sql, null, "id", null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			int id=cursor.getInt(cursor.getColumnIndex("id"));//此处字符串值都要与数据库键值一样
			String content=cursor.getString(cursor.getColumnIndex("content"));
			int importance=cursor.getInt(cursor.getColumnIndex("importance"));
			String title=cursor.getString(cursor.getColumnIndex("title"));
			int showFlag = cursor.getInt(cursor.getColumnIndex("showFlag"));
//			Log.d("TAG", "编号："+ title);
			
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
