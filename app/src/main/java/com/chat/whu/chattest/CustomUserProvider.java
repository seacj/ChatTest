package com.chat.whu.chattest;

import java.util.ArrayList;
import java.util.List;

import cn.leancloud.chatkit.*;

/**
 * 用户体系的接口，开发者需要实现此接口来接入 LCChatKit
 */

public class CustomUserProvider implements LCChatProfileProvider {
    private static CustomUserProvider customUserProvider;

    public synchronized static CustomUserProvider getInstance() {
        if (null == customUserProvider) {
            customUserProvider = new CustomUserProvider();
        }
        return customUserProvider;
    }

    CustomUserProvider() {
    }

    private static List<LCChatKitUser> partUsers = new ArrayList<LCChatKitUser>();

    // 此数据均为模拟数据，仅供参考
    static {
        partUsers.add(new LCChatKitUser("Tom", "Tom", "http://www.avatarsdb.com/avatars/tom_and_jerry2.jpg"));
        partUsers.add(new LCChatKitUser("Jerry", "Jerry", "http://www.avatarsdb.com/avatars/jerry.jpg"));
        partUsers.add(new LCChatKitUser("Harry", "Harry", "http://www.avatarsdb.com/avatars/young_harry.jpg"));
        partUsers.add(new LCChatKitUser("William", "William", "http://www.avatarsdb.com/avatars/william_shakespeare.jpg"));
        partUsers.add(new LCChatKitUser("a", "a", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526224480026&di=a5c8ec8006578e6eac1b290ae9971ff3&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D340a01cbbe8f8c54f7decd6c52404780%2F95eef01f3a292df57ba56b30b6315c6034a873b5.jpg"));
        partUsers.add(new LCChatKitUser("Bob", "Bob", "http://www.avatarsdb.com/avatars/bath_bob.jpg"));

        partUsers.add(new LCChatKitUser("Bob", "Bob", "http://www.avatarsdb.com/avatars/bath_bob.jpg"));
        partUsers.add(new LCChatKitUser("Bob", "Bob", "http://www.avatarsdb.com/avatars/bath_bob.jpg"));
        partUsers.add(new LCChatKitUser("Bob", "Bob", "http://www.avatarsdb.com/avatars/bath_bob.jpg"));
        partUsers.add(new LCChatKitUser("Bob", "Bob", "http://www.avatarsdb.com/avatars/bath_bob.jpg"));
        partUsers.add(new LCChatKitUser("Bob", "Bob", "http://www.avatarsdb.com/avatars/bath_bob.jpg"));
        partUsers.add(new LCChatKitUser("Bob", "Bob", "http://www.avatarsdb.com/avatars/bath_bob.jpg"));
        partUsers.add(new LCChatKitUser("Bob", "Bob", "http://www.avatarsdb.com/avatars/bath_bob.jpg"));
        partUsers.add(new LCChatKitUser("Bob", "Bob", "http://www.avatarsdb.com/avatars/bath_bob.jpg"));
        partUsers.add(new LCChatKitUser("Bob", "Bob", "http://www.avatarsdb.com/avatars/bath_bob.jpg"));

    }

    @Override
    public void fetchProfiles(List<String> list, LCChatProfilesCallBack callBack) {
        List<LCChatKitUser> userList = new ArrayList<LCChatKitUser>();
        for (String userId : list) {
            for (LCChatKitUser user : partUsers) {
                if (user.getUserId().equals(userId)) {
                    userList.add(user);
                    break;
                }
            }
        }
        callBack.done(userList, null);
    }

    public List<LCChatKitUser> getAllUsers() {
        return partUsers;
    }
}
