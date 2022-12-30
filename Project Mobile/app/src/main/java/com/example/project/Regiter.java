package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class Regiter extends AppCompatActivity {

    EditText username,password,confirmpass;
    TextView registerbtn;
    MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiter);
        username = (EditText) findViewById(R.id.Register_username);
        password = (EditText) findViewById(R.id.Register_password);
        confirmpass = (EditText) findViewById(R.id.Register_confirm);
        registerbtn = (TextView) findViewById(R.id.Register_Registerbtn);
//        signin = (TextView) findViewById(R.id.btnsignin);
        db = new MyDatabaseHelper(this);
        registerbtn.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               String user = username.getText().toString();
                                               String pass = password.getText().toString();
                                               String repass = confirmpass.getText().toString();
                                               if (user.equals("") || pass.equals("") || repass.equals(""))
                                                   Toast.makeText(Regiter.this, "Please enter all the fields",Toast.LENGTH_SHORT).show();
                                               else {
                                                   if(pass.equals(repass)){
                                                       Boolean checkuser = db.checkusername(user);
                                                       if (checkuser==false){
                                                           Boolean insert = db.insertData(user, pass);
                                                           if (insert==true){
                                                               Toast.makeText(Regiter.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                                                               Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                                               startActivity(intent);
                                                           }else {
                                                               Toast.makeText(Regiter.this,"Registration failed",Toast.LENGTH_SHORT).show();
                                                           }
                                                       }else {

                                                           Toast.makeText(Regiter.this,"User already exists ! please sign in ",Toast.LENGTH_SHORT).show();
                                                       }
                                                   }else{
                                                       Toast.makeText(Regiter.this,"Passwords not matching",Toast.LENGTH_SHORT).show();

                                                   }
                                               }
                                           }
                                       }
        );
    }
}
