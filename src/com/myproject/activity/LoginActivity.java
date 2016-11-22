package com.myproject.activity;

import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.example.ard_myproject.R;
import com.myproject.entity.MyUser;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class LoginActivity extends Activity {

	@Bind(R.id.username)
	EditText etUsername;
	@Bind(R.id.password)
	EditText etPassword;
	
	@Bind(R.id.rest_actionbar_title)
	TextView login_title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String BMOB_KEY = "390b00a72553ea521069697255cc32c3";
		Bmob.initialize(this, BMOB_KEY);//  初始化BmobSDK
		setContentView(R.layout.activity_login);
		
		ButterKnife.bind(this);
		login_title.setText("账号登录");
		
		//ImageLoader的全局性初始化（以默认方式）
		ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
	}
	
	@OnClick(R.id.rest_actionbar_left)
	public void back(View v){
		finish();
	}
	
	/****-取消按钮-*/
	@OnClick(R.id.quxiao)
	public void quxiao(View v){
		etUsername.setText("");
		etPassword.setText("");
	}
	
	/***-登录按钮--利用第三方登录就要导入BmobSDK_V3.4.5_1111.jar*/
	@OnClick(R.id.login)
	public void login(View v){
		BmobQuery<MyUser> query = new BmobQuery<MyUser>();
		query.addWhereEqualTo("name", etUsername.getText().toString());
		
		query.findObjects(this, new FindListener<MyUser>() {//Bmob返回值
			@Override
			public void onSuccess(List<MyUser> arg0) {
				if (arg0.size()>0) {
					MyUser myuser=arg0.get(0);
					
					String password =etPassword.getText().toString();
					String md5pwd=new String(Hex.encodeHex(DigestUtils.sha(password))).toUpperCase();
					
					if (myuser.getPassword().equals(md5pwd)) {
						//TODO 登录成功
						Intent data=new Intent();
						data.putExtra("name", myuser.getName());
						data.putExtra("avatar", myuser.getAvatar());
						setResult(RESULT_OK, data);
						Log.d("TAG", "登录成功："+myuser.getName());
						finish();
					
					} else {
						Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();//密码错误
					}
				} else {
					//服务器不存在username
					Toast.makeText(LoginActivity.this, "用户名不存在或密码错误", 0).show();//用户名不存在
				}
			}
			@Override
			public void onError(int arg0, String arg1) {
				Toast.makeText(LoginActivity.this, "服务器繁忙，稍后重试", 0).show();
			}
		});
	}
	
	
	/**-注册按钮-写此形式就要导入butterknife-7.0.1.jar*/
	@OnClick(R.id.regist)
	public void regist(View v){
		Intent intent=new Intent(this, RegistActivity.class);
		startActivity(intent);
	}
	
}
