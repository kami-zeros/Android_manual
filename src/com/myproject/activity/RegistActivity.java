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
	
	private String avatarUrl;//ͷ��URL
	private String filePath ;//ѡͼ·��
	private String cameraPath;//����·��
	
	@Bind(R.id.rest_actionbar_title)
	TextView regist_title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		
		ButterKnife.bind(this);
		regist_title.setText("ע���˺�");
	}
	
	@OnClick(R.id.rest_actionbar_left)
	public void back(View v){
		finish();
	}
	
	
	//ע�ᰴť
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
		
		//MD5����//password--MD5-->pwd
		String pwd=new String(Hex.encodeHex(DigestUtils.sha(password))).toUpperCase();
		user.setPassword(pwd);
		user.setGender(gender);
		user.setAvatar(avatarUrl);
		
		user.save(this, new SaveListener() {
			@Override
			public void onSuccess() {
				Toast.makeText(RegistActivity.this, "ע��ɹ�", 0).show();
				//���
				avatarUrl=null;
				Avatar.setImageResource(R.drawable.icon_user_head);
				etUsername.setText("");
				etPassword.setText("");
				
				finish();
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				Toast.makeText(RegistActivity.this, "ʧ��", 0).show();
			}
		});
	}
	
	//ѡ��ͷ��
	@OnClick(R.id.regist_avator)
	public void setAvatar(View v){
		//����ͼ���Intent
		Intent intent1=new Intent(Intent.ACTION_PICK);
		intent1.setDataAndType(Media.EXTERNAL_CONTENT_URI, "image/*");
		
		//���������Intent
		Intent intent2=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		//ָ��������պ�ͼƬ�����λ��
		File file=new File(Environment.getExternalStoragePublicDirectory(
				Environment.DIRECTORY_PICTURES), System.currentTimeMillis()+".jpg");
		Uri uri=Uri.fromFile(file);
		intent2.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		
		//����Intentѡ���������û�ѡ��ͼƬ��Դ��ͼ�⣬���գ�
		Intent choose=Intent.createChooser(intent1, "ѡ��ͷ��...");
		choose.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{intent2});
		
		startActivityForResult(choose, 102);
	}

	//��������ֵ
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode==RESULT_OK && requestCode==102) {
			if (data!=null) {
				//�û���ͼ��ѡ���ͷ��
				//1����ȡͷ����SD���ϵ�·��
				Uri uri=data.getData();
				Cursor cursor=getContentResolver().query(uri, new String[]{Media.DATA}, null, null, null);
				cursor.moveToNext();
				filePath = cursor.getString(0);
				cursor.close();
				
			}else {
				//�û�������յ�ͷ��
				filePath=cameraPath;
			}
			
			//2����ͷ���ϴ���Bmob���������ļ�����
			final BmobFile bf=new BmobFile(new File(filePath)); 
			
			bf.uploadblock(this, new UploadFileListener() {
				@Override
				public void onSuccess() {
					Toast.makeText(RegistActivity.this, "ͷ���ϴ��ɹ�", Toast.LENGTH_SHORT).show();
					
					//3������ϴ��ɹ����ͷ���ַ����ַ��
					avatarUrl = bf.getFileUrl(RegistActivity.this);
					
					//4����ivAvatar����ʾͷ����ʾ��ͷ���������е�ͷ��
					/**--����universal-image-loader-1.9.2_sources.jar��--*/
					ImageLoader.getInstance().displayImage(avatarUrl, Avatar);
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					Toast.makeText(RegistActivity.this, "ͷ���ϴ�ʧ�ܣ�"+arg1, Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
	
}
