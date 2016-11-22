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
		Bmob.initialize(this, BMOB_KEY);//  ��ʼ��BmobSDK
		setContentView(R.layout.activity_login);
		
		ButterKnife.bind(this);
		login_title.setText("�˺ŵ�¼");
		
		//ImageLoader��ȫ���Գ�ʼ������Ĭ�Ϸ�ʽ��
		ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
	}
	
	@OnClick(R.id.rest_actionbar_left)
	public void back(View v){
		finish();
	}
	
	/****-ȡ����ť-*/
	@OnClick(R.id.quxiao)
	public void quxiao(View v){
		etUsername.setText("");
		etPassword.setText("");
	}
	
	/***-��¼��ť--���õ�������¼��Ҫ����BmobSDK_V3.4.5_1111.jar*/
	@OnClick(R.id.login)
	public void login(View v){
		BmobQuery<MyUser> query = new BmobQuery<MyUser>();
		query.addWhereEqualTo("name", etUsername.getText().toString());
		
		query.findObjects(this, new FindListener<MyUser>() {//Bmob����ֵ
			@Override
			public void onSuccess(List<MyUser> arg0) {
				if (arg0.size()>0) {
					MyUser myuser=arg0.get(0);
					
					String password =etPassword.getText().toString();
					String md5pwd=new String(Hex.encodeHex(DigestUtils.sha(password))).toUpperCase();
					
					if (myuser.getPassword().equals(md5pwd)) {
						//TODO ��¼�ɹ�
						Intent data=new Intent();
						data.putExtra("name", myuser.getName());
						data.putExtra("avatar", myuser.getAvatar());
						setResult(RESULT_OK, data);
						Log.d("TAG", "��¼�ɹ���"+myuser.getName());
						finish();
					
					} else {
						Toast.makeText(LoginActivity.this, "�������", Toast.LENGTH_SHORT).show();//�������
					}
				} else {
					//������������username
					Toast.makeText(LoginActivity.this, "�û��������ڻ��������", 0).show();//�û���������
				}
			}
			@Override
			public void onError(int arg0, String arg1) {
				Toast.makeText(LoginActivity.this, "��������æ���Ժ�����", 0).show();
			}
		});
	}
	
	
	/**-ע�ᰴť-д����ʽ��Ҫ����butterknife-7.0.1.jar*/
	@OnClick(R.id.regist)
	public void regist(View v){
		Intent intent=new Intent(this, RegistActivity.class);
		startActivity(intent);
	}
	
}
