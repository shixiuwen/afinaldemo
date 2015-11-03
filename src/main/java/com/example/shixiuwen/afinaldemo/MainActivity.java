package com.example.shixiuwen.afinaldemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.tsz.afinal.FinalDb;

import java.util.List;

import entities.User;


public class MainActivity extends ActionBarActivity {

    private EditText etName;
    private EditText etPsw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.et_name);
        etPsw = (EditText) findViewById(R.id.et_password);
        Button btnLogin = (Button) findViewById(R.id.btn_login);
        Button btnRegist = (Button) findViewById(R.id.btn_registy);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etName.getText().toString() + etPsw.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    //按条件查询数据库
                    FinalDb finalDb = FinalDb.create(getApplicationContext(), "user");
                    List<User> allUser = finalDb.findAll(User.class);
                    for (int i = 0; i < allUser.size(); i++) {
                        if (allUser.get(i).getName().equals(etName.getText().toString())) {
                            //用户名匹配，检测密码
                            if (allUser.get(i).getPsw().equals(etPsw.getText().toString())) {
                                Intent intent = new Intent(MainActivity.this, MainPage.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "恭喜您登陆成功", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }

                    }

                    Toast.makeText(getApplicationContext(), "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                    etPsw.setText("");

                }
            }
        });

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Regeist.class);
                startActivity(intent);
            }
        });
    }

}
