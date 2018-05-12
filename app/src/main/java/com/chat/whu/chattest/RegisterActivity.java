package com.chat.whu.chattest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;

import java.util.List;
/*
注册
 */

public class RegisterActivity extends Activity {
    EditText nameView;
    EditText passwordView;
    EditText confirmView;
    EditText chatNameView;
    Button registerButton;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameView = (EditText) findViewById(R.id.username);
        passwordView = (EditText) findViewById(R.id.password);
        confirmView = (EditText) findViewById(R.id.confirmpassword);
        chatNameView = (EditText) findViewById(R.id.chatname);
        registerButton=(Button)findViewById(R.id.register_btn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegisterClick();
            }
        });

    }
    @Override
    protected void onStart() {//显示返回按钮
        super.onStart();
        ActionBar actionBar = this.getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
       switch (item.getItemId()){//左上返回按钮实现
           case android.R.id.home:
//               NavUtils.navigateUpFromSameTask(this);
//
//               Intent myIntent = new Intent();
//               myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
//               startActivity(myIntent);
               this.finish();
               return true;
       }
       return super.onOptionsItemSelected(item);
    }
    public void onRegisterClick(){
        final String clientId = nameView.getText().toString();
        final String password = passwordView.getText().toString();
        final String chatName = chatNameView.getText().toString();
        String confirm=confirmView.getText().toString();
        //判断输入是否为空,为空时提示。
        if (TextUtils.isEmpty(chatName.trim())) {
            Toast.makeText(this, "请输入昵称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(clientId.trim())) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password.trim())) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(confirm.trim())) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!confirm.equals(password)){
            Toast.makeText(this, "两次密码输入不相同，请重新输入", Toast.LENGTH_SHORT).show();
            return;
        }
        //然后用leancloud来查询是否该用户已存在，已存在时提示。不为空时用leancloud添加。因为查询添加都是访问leancloud即访问网络。需要新建子线程。
        //在查询中，如果该用户已存在我们通过定义一个标志flag（初始化为false），令flag为true。判断如果flag为true则return。
        new Thread(new Runnable() {
            boolean flag=false;
            @Override
            public void run() {
                AVQuery<AVObject> avQuery = new AVQuery<>("UserID");
                avQuery.findInBackground(new FindCallback<AVObject>() {
                    @Override
                    public void done(List<AVObject> list, AVException e) {

                        for (AVObject obj : list) {
                            String query_account = obj.getString("userId");
                            if (query_account.equals(clientId)) {
                                Toast.makeText(getApplicationContext(), "该用户名已存在", Toast.LENGTH_SHORT).show();
                                flag = true;
                                break;
                            }
                        }
                        if (flag) {
                            flag = false;
                            return;
                        }
                        addAccount(clientId,password,chatName);
                    }
                });
            }
        }).start();
    }
    public void addAccount(String account,String password,String chatName){
        AVObject avObject = new AVObject("UserID");
        avObject.put("userId",account);
        avObject.put("password", password);
        avObject.put("chatName", chatName);
        avObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "注册失败" + e, Toast.LENGTH_SHORT).show();
            }
        });
        finish();
    }

}
