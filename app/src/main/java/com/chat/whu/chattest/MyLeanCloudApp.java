package com.chat.whu.chattest;

import android.app.Application;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.PushService;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.im.v2.AVIMClient;

import cn.leancloud.chatkit.LCChatKit;

public class MyLeanCloudApp extends Application {
    private final String APP_ID="A1s7nf4vseuLSfYzNesTia0w-gzGzoHsz";
    private final String APP_KEY="E2YFoDd5Avs4PCYJemSQhQHJ";
    @Override
    public void onCreate() {
        super.onCreate();
        //CustomUserProvider
        LCChatKit.getInstance().setProfileProvider(CustomUserProvider.getInstance());
        AVOSCloud.setDebugLogEnabled(true);
        LCChatKit.getInstance().init(getApplicationContext(), APP_ID, APP_KEY);
        AVIMClient.setAutoOpen(true);
        PushService.setDefaultPushCallback(this, MainActivity.class);
        PushService.setAutoWakeUp(true);
        PushService.setDefaultChannelId(this, "default");

        AVInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            public void done(AVException e) {
                if (e == null) {
                    // 保存成功
                    String installationId = AVInstallation.getCurrentInstallation().getInstallationId();
                    System.out.println("---  " + installationId);
                } else {
                    // 保存失败，输出错误信息
                    System.out.println("failed to save installation.");
                }
            }
        });



//        // 初始化参数依次为 this, AppId, AppKey
//        AVOSCloud.initialize(this,APP_ID,APP_KEY);
//
//        // 放在 SDK 初始化语句 AVOSCloud.initialize() 后面，只需要调用一次即可
//        AVOSCloud.setDebugLogEnabled(true);
//
//
//        AVObject todo = new AVObject("Todo");
//        todo.put("title", "工程师周会");
//        todo.put("content", "每周工程师会议，周一下午2点");
//        todo.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(AVException e) {
//                if (e == null) {
//                    // 存储成功
//                } else {
//                    // 失败的话，请检查网络环境以及 SDK 配置是否正确
//                }
//            }
//        });
    }


}
