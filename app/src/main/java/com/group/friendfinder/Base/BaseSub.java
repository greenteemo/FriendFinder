package com.group.friendfinder.Base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.group.friendfinder.R;
import com.group.friendfinder.View.MainActivity;

public class BaseSub extends Activity {
    Button bnsub;  //
    EditText etaccount, etpwd, etrepwd, etemail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        etaccount = (EditText) findViewById(R.id.user);
        etpwd = (EditText) findViewById(R.id.pwd);
        etrepwd = (EditText) findViewById(R.id.repwd);
        etemail = (EditText) findViewById(R.id.email);
        bnsub = (Button) findViewById(R.id.sub_btn_sure);
        bnsub.setOnClickListener(new View.OnClickListener() {//按下注册键后，对信息进行提取，并转换到主界面
            @Override
            public void onClick(View v) {
                final String username = etaccount.getText().toString();
                String pwd = etpwd.getText().toString();
                String repwd = etrepwd.getText().toString();
                String email = etemail.getText().toString();
                if(pwd.equals(repwd)){

                    Toast.makeText(BaseSub.this, "registration success",Toast.LENGTH_LONG ).show();
                    Intent intent = new Intent(BaseSub.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                else{
                    Toast.makeText(BaseSub.this, "registration failed",Toast.LENGTH_LONG ).show();
                }



            }
            });
    }
}
