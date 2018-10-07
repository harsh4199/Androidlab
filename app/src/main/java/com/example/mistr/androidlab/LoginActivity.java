package com.example.mistr.androidlab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.time.Instant;

public class LoginActivity extends Activity {

    String ACTIVITY_NAME="LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText loginName = (EditText) findViewById(R.id.textEdit1);

        SharedPreferences userloginName = getSharedPreferences("SaveData", Context.MODE_PRIVATE);

        loginName.setText(userloginName.getString("LoginName","email@domain.com"));

        Button loginbtn= findViewById(R.id.button2);

        loginbtn.setOnClickListener(loginfunc);



    }
    private View.OnClickListener loginfunc = new View.OnClickListener() {
        public void onClick(View v) {
            Intent myIntent = new Intent(LoginActivity.this, StartActivity.class);
            EditText loginName = (EditText) findViewById(R.id.textEdit1);
            String name=loginName.getText().toString();
            SharedPreferences userloginName = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
            userloginName.edit().putString("LoginName",name).commit();
            myIntent.putExtra("Username",name);
            LoginActivity.this.startActivity(myIntent);


        }
    };
    @Override
    protected void onStart()
    {
        super.onStart();
        Log.i(ACTIVITY_NAME,"In onStart()");
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i(ACTIVITY_NAME,"In onPause()");
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        Log.i(ACTIVITY_NAME,"In onStop()");
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.i(ACTIVITY_NAME,"In onDestory()");
    }
}
