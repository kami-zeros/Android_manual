package com.myproject.util;

import android.R.color;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;

public class TextCanvasUtil {
	
	public static Bitmap textBitmap() {
		Bitmap bitmap=Bitmap.createBitmap(100, 100, Config.ARGB_8888);
		Canvas canvas=new Canvas(bitmap);
		Paint paint=new Paint();
		
		paint.setAntiAlias(true);//设置画笔没有毛边
		paint.setColor(Color.parseColor("#FF69B4"));
		RectF rectF=new RectF(5, 5, 100, 85);
		canvas.drawOval(rectF, paint);
		
		paint.setColor(Color.BLACK);
		paint.setTextSize(rectF.height()/3);
		canvas.drawText("获取", rectF.width()/4, 40, paint);
		canvas.drawText("正版",  rectF.width()/4, 68, paint);
		
		return bitmap;
	}
	
	
	
	
	
	
	
	
	
	
}
