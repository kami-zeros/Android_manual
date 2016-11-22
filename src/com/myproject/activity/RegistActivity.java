package com.myproject.activity;

import java.io.File;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

import com.example.ard_myproject.R;
import com.myproject.entity.MyUser;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class RegistActivity extends Activity {

	@Bind(R.id.regist_avator)
	ImageView Avatar;
	@Bind(R.id.regist_username)
	EditText etUsername;
	@Bind(R.id.regist_password)
	EditText etPassword;
	@Bind(R.id.regist_gender)
	RadioGroup radioG_gender;
	
	private String avatarUrl;//头像URL
	private String filePath ;//选图路径
	private String cameraPath;//拍照路径
	
	@Bind(R.id.rest_actionbar_title)
	TextView regist_title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		
		ButterKnife.bind(this);
		regist_title.setText("注册账号");
	}
	
	@OnClick(R.id.rest_actionbar_left)
	public void back(View v){
		finish();
	}
	
	
	//注册按钮
	@OnClick(R.id.regist_regist)
	public void regist(View v){
		MyUser user=new MyUser();
		String username=etUsername.getText().toString();
		String password=etPassword.getText().toString();
		
		boolean gender=true;
		int id=radioG_gender.getCheckedRadioButtonId();
		if (id==R.id.radio1) {
			gender=false;
		}
		
		user.setName(username);
		
		//MD5加密//password--MD5-->pwd
		String pwd=new String(Hex.encodeHex(DigestUtils.sha(password))).toUpperCase();
		user.setPassword(pwd);
		user.setGender(gender);
		user.setAvatar(avatarUrl);
		
		user.save(this, new SaveListener() {
			@Override
			public void onSuccess() {
				Toast.makeText(RegistActivity.this, "注册成功", 0).show();
				//清空
				avatarUrl=null;
				Avatar.setImageResource(R.drawable.icon_user_head);
				etUsername.setText("");
				etPassword.setText("");
				
				finish();
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				Toast.makeText(RegistActivity.this, "失败", 0).show();
			}
		});
	}
	
	//选择头像
	@OnClick(R.id.regist_avator)
	public void setAvatar(View v){
		//开启图库的Intent
		Intent intent1=new Intent(Intent.ACTION_PICK);
		intent1.setDataAndType(Media.EXTERNAL_CONTENT_URI, "image/*");
		
		//开启相机的Intent
		Intent intent2=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		//指定相机拍照后图片保存的位置
		File file=new File(Environment.getExternalStoragePublicDirectory(
				Environment.DIRECTORY_PICTURES), System.currentTimeMillis()+".jpg");
		Uri uri=Uri.fromFile(file);
		intent2.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		
		//利用Intent选择器，让用户选择图片来源（图库，拍照）
		Intent choose=Intent.createChooser(intent1, "选择头像...");
		choose.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{intent2});
		
		startActivityForResult(choose, 102);
	}

	//上述返回值
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode==RESULT_OK && requestCode==102) {
			if (data!=null) {
				//用户从图库选择的头像
				//1）获取头像在SD卡上的路径
				Uri uri=data.getData();
				Cursor cursor=getContentResolver().query(uri, new String[]{Media.DATA}, null, null, null);
				cursor.moveToNext();
				filePath = cursor.getString(0);
				cursor.close();
				
			}else {
				//用户相机拍照的头像
				filePath=cameraPath;
			}
			
			//2）将头像上传到Bmob服务器的文件区域
			final BmobFile bf=new BmobFile(new File(filePath)); 
			
			bf.uploadblock(this, new UploadFileListener() {
				@Override
				public void onSuccess() {
					Toast.makeText(RegistActivity.this, "头像上传成功", Toast.LENGTH_SHORT).show();
					
					//3）获得上传成功后的头像地址（网址）
					avatarUrl = bf.getFileUrl(RegistActivity.this);
					
					//4）在ivAvatar中显示头像（显示的头像是网络中的头像）
					/**--导入universal-image-loader-1.9.2_sources.jar包--*/
					ImageLoader.getInstance().displayImage(avatarUrl, Avatar);
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					Toast.makeText(RegistActivity.this, "头像上传失败，"+arg1, Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
	
}
