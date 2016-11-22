package com.myproject.photowall;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.bmob.v3.util.i;

import android.os.AsyncTask;
import android.util.Log;

public class Images {	
	public static List<String> getImgUrl() throws Exception {//Context context, final List<String> list
		final List<String> list = new ArrayList<String>();
		int i=1;
		do {
			String html="http://sj.zol.com.cn/bizhi/fengjing/240x320/"+i+".html";
			
//			Document content = Jsoup.parse(new URL(html), 5000);
			Document content = Jsoup.connect(html).get();
			//Log.d("TAG", "Document:"+content);
			
			Elements allimgs=content.select("div[class=wrapper top-main clearfix] " +
					"div[class=main] ul[class=pic-list2  " +
					"martop clearfix] li[class=photo-list-padding] a[class=pic]");//li[class=photo-list-padding]
//			Log.d("TAG", "Elements:"+allimgs);
			
			for (Element img : allimgs) {
				Elements imgs=img.select("img");
				String imgurl=imgs.get(0).attr("src");
				
//				Log.d("TAG", "getImgUrl:"+imgurl);
				list.add(imgurl);
			}
			i++;
		} while (i<15);
					
		return list;
	}
}




//public class Images {
	
//	public static List<String> imgUrllist = new ArrayList<String>();
	
	/*static int i=11;
	public static List<String> getImgUrl(List<String> list) {
		do {
			String html = "http://sj.zol.com.cn/bizhi/showpic/480x800_886"+i+"_1.html";
			Document doc = Jsoup.parse(html, "gbk");
			Elements imges=doc.getElementsByTag("img");//属性元素
			Log.d("TAG", "Elements:"+imges.toString());
//			Element img=imges.get(0);
			String url =imges.attr("src");//属性值
			Log.d("TAG", "Images:"+url);
			list.add(url);
			i++;
		} while (i<100);
		Log.d("TAG", "Images***"+list);
		return list;
	}*/
	
	/*public final static String[] imageUrls = new String[] {
			"http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037234_3539.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037194_2965.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037193_1687.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037193_1286.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037192_8379.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037178_9374.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037177_1254.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037177_6203.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037152_6352.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037151_9565.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037151_7904.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037148_7104.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037129_8825.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037128_5291.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037128_3531.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037127_1085.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037095_7515.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037094_8001.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037093_7168.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037091_4950.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949643_6410.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949642_6939.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949630_4505.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949630_4593.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949629_7309.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949629_8247.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949615_1986.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949614_8482.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949614_3743.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949614_4199.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949599_3416.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949599_5269.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949598_7858.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949598_9982.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949578_2770.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949578_8744.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949577_5210.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949577_1998.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949482_8813.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949481_6577.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949480_4490.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949455_6792.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949455_6345.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949442_4553.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949441_8987.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949441_5454.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949454_6367.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949442_4562.jpg",
			http://b.zol-img.com.cn/sjbizhi/images/9/480x800/14682083 2525.jpg,
			http://b.zol-img.com.cn/sjbizhi/images/9/480x800/14682083 2969.jpg,
			http://b.zol-img.com.cn/sjbizhi/images/9/480x800/14682083 32613.jpg,
			http://b.zol-img.com.cn/sjbizhi/images/9/480x800/14682083 35326.jpg	,
			http://b.zol-img.com.cn/sjbizhi/images/9/480x800/14682083 38878.jpg,
			http://b.zol-img.com.cn/sjbizhi/images/9/480x800/14682083 41728.jpg,
			"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3700630188,1430779433&fm=21&gp=0.jpg"
	};*/
//}
