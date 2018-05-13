package com.chat.whu.chattest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private BookFragment bookFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setView();
    }

    private void setView(){
        bookFragment=new BookFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container,bookFragment).commitAllowingStateLoss();

    }
}
