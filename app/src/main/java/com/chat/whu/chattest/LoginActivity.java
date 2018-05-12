package com.chat.whu.chattest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;

import java.util.List;

import cn.leancloud.chatkit.LCChatKit;

/**
 * 登陆
 */
public class LoginActivity extends Activity {
    protected EditText nameView;
    protected EditText passwordView;
    protected Button loginButton;
    protected Button regsterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nameView = (EditText) findViewById(R.id.username);
        passwordView = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClick();
            }
        });
        regsterButton=(Button) findViewById(R.id.register_btn);
        regsterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onRegisterClick();
            }
        });
    }
    //登陆
    public void onLoginClick() {
        final String userId = nameView.getText().toString();
        final String password = passwordView.getText().toString();
        if (TextUtils.isEmpty(userId.trim())) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password.trim())) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(new Runnable() {
            boolean flag;
            @Override
            public void run() {
                AVQuery<AVObject> avQuery = new AVQuery<>("exam");
                avQuery.findInBackground(new FindCallback<AVObject>() {
                    @Override
                    public void done(List<AVObject> list, AVException e) {
                        for (AVObject obj : list){
                            if (obj.getString("userId").equals(userId) && obj.getString("password").equals(password)){
                                flag = true;
                                break;
                            }
                        }
                        if (flag){
                            Toast.makeText(getApplicationContext(),"登陆成功！",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(),"用户名或密码错误！",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();


    }
    //注册
    public void onRegisterClick() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
