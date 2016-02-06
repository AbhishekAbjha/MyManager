package com.coronate.mymanager;

import android.app.Activity;
import android.os.Bundle;

import android.widget.ListView;
import android.widget.ImageView;

public class PasswordManagerActivity extends Activity {

    private ImageView iv_addaccount;
    private ListView  lv_passwordaccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_manager);

        lv_passwordaccount = (ListView)findViewById(R.id.lv_passwordaccount);
        iv_addaccount = (ImageView)findViewById(R.id.iv_addaccount);

    }
}
