package com.example.homepc.restauranteatitapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpPage extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText signupName, signupPassword;
    Button addAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        myDB = new DatabaseHelper(this);
        signupName = findViewById(R.id.signup_id);
        signupPassword = findViewById(R.id.signup_password);
        addAccount = findViewById(R.id.add_account);

        Add_User();

    }


    public void Add_User() {


        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //帳號密碼為空
                if ((signupName.getText().toString().equals("")) && (signupPassword.getText().toString().equals("")))
                {
                    Toast.makeText(SignUpPage.this, "Username and Password fields can't left blank", Toast.LENGTH_SHORT).show();
                }
                //帳號欄為空
                else if ((signupName.getText().toString().equals("")))
                {
                    Toast.makeText(SignUpPage.this, "Username field can't left blank", Toast.LENGTH_SHORT).show();
                }
                //密碼欄為空
                else if(signupPassword.getText().toString().equals(""))
                {
                    Toast.makeText(SignUpPage.this, "Password field can't left blank", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //將帳號密碼導入資料庫
                    boolean isinserted = myDB.Add_Account(signupName.getText().toString(), signupPassword.getText().toString());
                    //導入成功回傳true
                    if (isinserted)
                    {
                        Toast.makeText(SignUpPage.this, "User Added Successfully !", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),SignInPage.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                        Toast.makeText(SignUpPage.this, "Please, Try again", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }
    }

