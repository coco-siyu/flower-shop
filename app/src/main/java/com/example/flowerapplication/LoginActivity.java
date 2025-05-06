package com.example.flowerapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.example.flowerapplication.DB.DBAccountInfo;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button signin, register;
    DBAccountInfo DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        DB = new DBAccountInfo(this);

        username = findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);
        signin = findViewById(R.id.btn_login);
        register = findViewById(R.id.btnsignin_signup);

        signin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) )
                    Toast.makeText(LoginActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.CheckPass(user, pass);
                    if (checkuserpass && user.equals("admin") && pass.equals("0000")) {
                        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        Intent AdIntent = new Intent(LoginActivity.this, AdminActivity.class);
                        startActivity(AdIntent);
                    }
                    else if (checkuserpass){

                        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        } );

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
    }

}