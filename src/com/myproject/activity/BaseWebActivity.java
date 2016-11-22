package com.myproject.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.AppManager;
import com.myproject.view.MyTitleView;

/**
 * ֱ�Ӽ�����ҳ��Activity�Ļ���
 */
public abstract class BaseWebActivity extends Activity {
	
	private int layoutResId = -1;
	private WebView webView;
	private View empty;
	private String webURL;// ���������ַ
	private MyTitleView title;
	
	public BaseWebActivity(int layoutResId, String webURL) {
		this.layoutResId = layoutResId;
		this.webURL = webURL;
	}

	public void setWebView(int id) {
		this.webView = (WebView) findViewById(id);
	}

	public void setEmpty(int id) {
		this.empty = findViewById(id);
	}

	public void setTitle(MyTitleView title) {
		this.title = title;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
		AppManager.getInstance().addActivity(this);
		if (layoutResId!=-1) {
			setContentView(layoutResId);
		}
		
		initData();
		initView();
		WebViewConfig(webURL);
	}

	public void WebViewConfig(String webURL) {
		webView.loadUrl(webURL);
		WebSettings settings=webView.getSettings();
		settings.setSupportZoom(true); // ֧������
		settings.setBuiltInZoomControls(true); // ������������װ��
		settings.setJavaScriptEnabled(true); // ����JS�ű�
		
		webView.setWebViewClient(new WebViewClient());
		webView.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if (newProgress >70) {
					empty.setVisibility(View.GONE);
				}
			}
			
		});
	}

	/** ��ʼ����ͼ */
	public abstract void initView();
	/** ��ʼ������ */
	public abstract void initData(); 
	
	/**
	 * ������Ӧ����WebView�в鿴��ҳʱ�������ؼ���ʱ�������ʷ�˻�,������������������WebView�����˳�
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Check if the key event was the Back button and if there's history
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			// ���ؼ��˻�
			webView.goBack();
			return true;
		}
		// If it wasn't the Back key or there's no web page history, bubble up
		// to the default
		// system behavior (probably exit the activity)
		return super.onKeyDown(keyCode, event);
	}
}
