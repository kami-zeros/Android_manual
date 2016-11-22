package com.myproject.util;

import com.example.ard_myproject.R;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class ImageUtil {
/**
 * 实现圆形头像的处理
 */
	public static Bitmap bitmapFormat(Bitmap bitmap){
		int width=bitmap.getWidth();//读取传过来的图片的高宽
		int height=bitmap.getHeight();
		int r=width>=height?height:width;
//		Log.i("TAG", "r="+r);
		
		//构建一个bitmap--画布背景
		Bitmap background=Bitmap.createBitmap(width, height, Config.ARGB_8888);
		
		//新建画布
		Canvas canvas=new Canvas(background);
		Paint paint=new Paint();//创建画笔
		
		paint.setAntiAlias(true);//设置边缘光滑，去掉锯齿
//		paint.setColor(Color.GRAY);//?????怎么没有灰色
		RectF rectF=new RectF(0,0,r,r);//宽高相等(左上角->右下角)，即正方形--要在此形上画圆
		
		//通过制定的rect画一个圆角矩形。当圆角x轴方向的半径等于y轴方向的半径时，且都等于r/2时，画出来的圆角矩形就是圆形
//		canvas.drawColor(Color.GRAY);//？？？？？？？？？为什么设置画布的颜色就不能绘制圆形了
		canvas.drawRoundRect(rectF, r/2, r/2, paint);//此画的一般就是实心的圆，故直接设置画笔的颜色即可
		
		//设置当两个图形相交时的模式，SRC_IN为取ＳＲＣ图形相交的部分，多余的去掉
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		
		//画头像
		canvas.drawBitmap(bitmap, null, rectF,paint);
		
		return background;//返回已经画好的background
	}
}
