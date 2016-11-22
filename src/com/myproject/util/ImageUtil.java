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
 * ʵ��Բ��ͷ��Ĵ���
 */
	public static Bitmap bitmapFormat(Bitmap bitmap){
		int width=bitmap.getWidth();//��ȡ��������ͼƬ�ĸ߿�
		int height=bitmap.getHeight();
		int r=width>=height?height:width;
//		Log.i("TAG", "r="+r);
		
		//����һ��bitmap--��������
		Bitmap background=Bitmap.createBitmap(width, height, Config.ARGB_8888);
		
		//�½�����
		Canvas canvas=new Canvas(background);
		Paint paint=new Paint();//��������
		
		paint.setAntiAlias(true);//���ñ�Ե�⻬��ȥ�����
//		paint.setColor(Color.GRAY);//?????��ôû�л�ɫ
		RectF rectF=new RectF(0,0,r,r);//�������(���Ͻ�->���½�)����������--Ҫ�ڴ����ϻ�Բ
		
		//ͨ���ƶ���rect��һ��Բ�Ǿ��Ρ���Բ��x�᷽��İ뾶����y�᷽��İ뾶ʱ���Ҷ�����r/2ʱ����������Բ�Ǿ��ξ���Բ��
//		canvas.drawColor(Color.GRAY);//������������������Ϊʲô���û�������ɫ�Ͳ��ܻ���Բ����
		canvas.drawRoundRect(rectF, r/2, r/2, paint);//�˻���һ�����ʵ�ĵ�Բ����ֱ�����û��ʵ���ɫ����
		
		//���õ�����ͼ���ཻʱ��ģʽ��SRC_INΪȡ�ӣң�ͼ���ཻ�Ĳ��֣������ȥ��
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		
		//��ͷ��
		canvas.drawBitmap(bitmap, null, rectF,paint);
		
		return background;//�����Ѿ����õ�background
	}
}