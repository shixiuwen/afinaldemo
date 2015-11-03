package com.example.shixiuwen.afinaldemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.tsz.afinal.FinalDb;

import java.util.List;

import entities.User;

/**
 * Created by shixiuwen on 15-11-3.
 */
public class Regeist extends Activity {
    private EditText etName;
    private EditText etPsw;
    private EditText etRepsw;
    private Button btnBack;
    private Button btnRegist;
    FinalDb finalDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regeist);

        etName = (EditText) findViewById(R.id.et_name);
        etPsw = (EditText) findViewById(R.id.et_password);
        etRepsw = (EditText) findViewById(R.id.et_repassword);
        btnRegist = (Button) findViewById(R.id.btn_registy);
        btnBack = (Button) findViewById(R.id.btn_back);

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String psw = etPsw.getText().toString();
                String repsw = etRepsw.getText().toString();
                if(!TextUtils.isEmpty(name+psw+repsw)){
                    if(psw.equals(repsw)){
                        //格式均满足，建立数据库存储数据
                        finalDb = FinalDb.create(getApplicationContext(),"user");
                        User user = new User();
                        user.setName(name);
                        user.setPsw(psw);
                        List<User> all = finalDb.findAll(User.class);
                        for(int i = 0;i<all.size();i++){
                            if(all.get(i).getName().equals(name)){
                                Toast.makeText(getApplicationContext(),"该用户已存在",Toast.LENGTH_SHORT).show();
                                etName.setText("");
                                etPsw.setText("");
                                etRepsw.setText("");
                                return;
                            }
                        }
                        finalDb.save(user);
                        Toast.makeText(getApplicationContext(),"恭喜您注册成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"密码不一致,请重新输入",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"信息不完整,请重新输入",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
