package com.chat.whu.chattest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class BottomNavActivity extends FragmentActivity implements View.OnClickListener {

    // 三个tab布局
    private RelativeLayout chatLayout, bookLayout, setLayout;

    // 底部标签切换的Fragment
    private Fragment homeFragment, findFragment, meFragment,
            currentFragment;
    // 底部标签图片
    private ImageView homeImg, findImg, meImg;
    // 底部标签的文本
    private TextView homeTv, findTv, meTv;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        initUI();
        initTab();

    }


    /**
     * 初始化UI
     */
    private void initUI() {
        chatLayout = (RelativeLayout) findViewById(R.id.rl_home);
        bookLayout = (RelativeLayout) findViewById(R.id.rl_find);
        setLayout = (RelativeLayout) findViewById(R.id.rl_me);
        chatLayout.setOnClickListener(this);
        bookLayout.setOnClickListener(this);
        setLayout.setOnClickListener(this);

        homeImg = (ImageView) findViewById(R.id.iv_home);
        findImg = (ImageView) findViewById(R.id.iv_find);
        meImg = (ImageView) findViewById(R.id.iv_me);
        homeTv = (TextView) findViewById(R.id.tv_home);
        findTv = (TextView) findViewById(R.id.tv_find);
        meTv = (TextView) findViewById(R.id.tv_me);

    }

    /**
     * 初始化底部标签
     */
    private void initTab() {
        if (homeFragment == null) {
            homeFragment = new ChatFragment();
        }

        if (!homeFragment.isAdded()) {
            // 提交事务
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_layout, homeFragment).commit();

            // 记录当前Fragment
            currentFragment = homeFragment;
            // 设置图片文本的变化
//            homeImg.setImageResource(R.drawable.index_hover);
//            homeTv.setTextColor(getResources()
//                    .getColor(R.color.bottomtab_press));
//            findImg.setImageResource(R.drawable.nav_find);
//            findTv.setTextColor(getResources().getColor(
//                    R.color.bottomtab_normal));
//            meImg.setImageResource(R.drawable.nav_my);
//            meTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));

        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_home: // 知道
                clickTab1Layout();
                break;
            case R.id.rl_find: // 我想知道
                clickTab2Layout();
                break;
            case R.id.rl_me: // 我的
                clickTab3Layout();
                break;
            default:
                break;
        }
    }

    /**
     * 点击第一个tab
     */
    private void clickTab1Layout() {
        if (homeFragment == null) {
            homeFragment = new ChatFragment();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), homeFragment);

        // 设置底部tab变化
//        homeImg.setImageResource(R.drawable.index_hover);
//        homeTv.setTextColor(getResources().getColor(R.color.bottomtab_press));
//        findImg.setImageResource(R.drawable.nav_find);
//        findTv.setTextColor(getResources().getColor(
//                R.color.bottomtab_normal));
//        meImg.setImageResource(R.drawable.nav_my);
//        meTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
    }

    /**
     * 点击第二个tab
     */
    private void clickTab2Layout() {
        if (findFragment == null) {
            findFragment = new BookFragment();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), findFragment);

//        homeImg.setImageResource(R.drawable.index);
//        homeTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
//        findImg.setImageResource(R.drawable.nav_find_hover);
//        findTv.setTextColor(getResources().getColor(
//                R.color.bottomtab_press));
//        meImg.setImageResource(R.drawable.nav_my);
//        meTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));

    }

    /**
     * 点击第三个tab
     */
    private void clickTab3Layout() {
        if (meFragment == null) {
            meFragment = new SetFragment();
        }

        addOrShowFragment(getSupportFragmentManager().beginTransaction(), meFragment);

//        homeImg.setImageResource(R.drawable.index);
//        homeTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
//        findImg.setImageResource(R.drawable.nav_find);
//        findTv.setTextColor(getResources().getColor(
//                R.color.bottomtab_normal));
//        meImg.setImageResource(R.drawable.nav_my_hover);
//        meTv.setTextColor(getResources().getColor(R.color.bottomtab_press));

    }

    /**
     * 添加或者显示碎片
     *
     * @param transaction
     * @param fragment
     */
    private void addOrShowFragment(android.support.v4.app.FragmentTransaction transaction,
                                   Fragment fragment) {
        if (currentFragment == fragment)
            return;

        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment)
                    .add(R.id.content_layout, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }

        currentFragment = fragment;
    }
}
