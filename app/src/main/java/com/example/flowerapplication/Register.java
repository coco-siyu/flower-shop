package com.example.flowerapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flowerapplication.DB.DBAccountInfo;

public class Register extends AppCompatActivity {
    private Button signUp;
    private EditText username, password, repassword;
    private TextView signIn;

    DBAccountInfo mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signUp = (Button) findViewById(R.id.btn_login);
        signIn = (TextView) findViewById(R.id.signin_text);

        username = (EditText) findViewById(R.id.username_login);
        password = (EditText) findViewById(R.id.password_login);
        repassword   = (EditText) findViewById(R.id.repassword_login);

        mDatabaseHelper = new DBAccountInfo(this);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(Register.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = mDatabaseHelper.CheckUser(user);
                        if(checkuser==false){
                            Boolean insert = mDatabaseHelper.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Register.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Register.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });
//        signUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String user = username.getText().toString();
//                String pass = password.getText().toString();
//
//                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) ){
//                    Toast.makeText(Register.this, "All fields required", Toast.LENGTH_SHORT).show();
//
//                }
//                else{
//                    AddData(user, pass);
//                    username.setText("");
//                    Intent intent = new Intent(Register.this, ListDataActivity.class);
//                    startActivity(intent);
//                }
//
//
//            }
//        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    public void AddData(String username, String password) {
        boolean insertData = mDatabaseHelper.addData(username, password);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}