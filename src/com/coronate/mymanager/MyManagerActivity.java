package com.coronate.mymanager;

import android.app.Activity;

import android.os.Bundle;

import android.widget.ImageView;

import android.view.View;
import android.view.View.OnClickListener;

import android.util.Log;

import android.content.Intent;


public class MyManagerActivity extends Activity{

    private ImageView iv_passwordmanager;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Set main Page.
        setContentView(R.layout.main_page);

        //Get password manager image context in scope and set click event handler.
        iv_passwordmanager = (ImageView)findViewById(R.id.iv_passwordmanager);
        iv_passwordmanager.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                try {
                    Intent pwdManagerIntent = new Intent(getApplicationContext(), PasswordManagerActivity.class);
                    startActivity(pwdManagerIntent);
                } catch (Exception ex) {
                    Log.e(getClass().getSimpleName(), ex.toString());
                }
            }
        });

    }
}
