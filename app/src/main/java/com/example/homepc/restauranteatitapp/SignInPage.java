package com.example.homepc.restauranteatitapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInPage extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText signinName, signinPassword;
    Button signinbtn;
    String usernameSaver, passwordSaver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);
        myDB = new DatabaseHelper(this);
        signinName = findViewById(R.id.signin_id);
        signinPassword = findViewById(R.id.signin_password);
        signinbtn = findViewById(R.id.signinBtn);
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_holder = "";
                String password_holder = "";
                //帳密為空白
                if(signinName.getText().toString().equalsIgnoreCase("")||(signinPassword.getText().toString().equalsIgnoreCase("")))
                {
                    Toast.makeText(getApplicationContext(),"Username and Password fields can't left blank",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //從資料庫尋找帳戶
                    Cursor c = myDB.CheckAccount(signinName.getText().toString());
                    //把帳戶名一個一個抓出來
                    if(c != null)
                        try {
                            name_holder = c.getString(1);
                            password_holder = c.getString(2);

                        }catch (Exception e){

                        }
                    //驗證密碼(成功)
                    if(name_holder.equalsIgnoreCase(signinName.getText().toString()) &&(password_holder.equals(signinPassword.getText().toString())))
                    {
                        usernameSaver = name_holder;
                        passwordSaver = password_holder;
                        Toast.makeText(getApplicationContext(),"Successfully Logged In !",Toast.LENGTH_SHORT).show();
                        //將畫面導至mainActivity
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        //傳送帳戶密碼資料
                        intent.putExtra("Name_marker",usernameSaver);
                        intent.putExtra("pass_marker",passwordSaver);
                        startActivity(intent);
                        finish();
                    }
                    //已有帳戶，密碼錯誤
                    else if(name_holder.equalsIgnoreCase(signinName.getText().toString()))
                    {
                        Toast.makeText(getApplicationContext(),"Invalid Password, Try Again !",Toast.LENGTH_SHORT).show();
                    }
                    //未查詢到帳戶
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Please Register yourself first !",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }
}
