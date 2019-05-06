package com.group.friendfinder.Base;

import android.content.Intent;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.group.friendfinder.R;
public class BaseLogin extends Activity{
    Button bnlogin, bnsub;  //
    EditText etaccount, etpwd;
    TextView etsound_help,etsound_facebook;

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to quit the application?")
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                }
                            })
                    .setPositiveButton("Sure",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                    finish();
                                }
                            }).show();

            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etsound_help=(TextView) findViewById(R.id.sound_help);
//获取编辑框
        etaccount = (EditText) findViewById(R.id.accountEdittext);
        etpwd = (EditText) findViewById(R.id.pwdEdittext);
//获取按钮
        bnlogin = (Button) findViewById(R.id.resetpwd_btn_sure);
        bnsub = (Button) findViewById(R.id.sub);
/**
        etsound_help.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(BaseLogin.this, "The business has not been opened yet.",Toast.LENGTH_LONG ).show();
            }
        });

        bnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username=etaccount.getText().toString();
                String pwd=etpwd.getText().toString();
                if(username.length()<1||pwd.length()<1)
                {
                    Toast.makeText(BaseLogin.this, "请输入用户名或密码",Toast.LENGTH_LONG ).show();
                }
                else {
                    /**
                     校验函数{
                    @Override
                    服务器函数{
                    if (e == null) {//密码非空
                    Person p= new Person(etaccount.getText().toString());
                    Bundle data=new Bundle();
                    data.putSerializable("id",p);

                    Toast.makeText(Login.this, "登陆成功，尊敬的用户："+username+"，欢迎进入FriendFinder",Toast.LENGTH_LONG ).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtras(data);
                    startActivity(intent);
                    finish();
                    }
                    else {
                    Toast.makeText(Login.this, "账号或密码错误",Toast.LENGTH_LONG ).show();
                    }

                    }
                     });
                }




            }
        });
        bnsub.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseLogin.this, BaseSub.class);
                startActivity(intent);
                finish();
            }
        });*/




    }
}
