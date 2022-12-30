package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {


        EditText username,password;
        TextView Loginbtn,Registerbtn;
        MyDatabaseHelper db;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id. password);
        Loginbtn = (TextView) findViewById(R.id.Loginbtn);
        Registerbtn = (TextView) findViewById(R.id. Registerbtn);
        db = new MyDatabaseHelper(this);
        Loginbtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            String user = username.getText().toString();
                                            String pass = password.getText().toString();
                                            if (user.equals("") || pass.equals(""))
                                                Toast.makeText(login.this, "please enter all fields", Toast.LENGTH_SHORT).show();
                                            else {
                                                Boolean checkuserpass = db.checkusernamepassword(user, pass);
                                                if (checkuserpass == true) {
                                                    Toast.makeText(login.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(getApplicationContext(), home.class);
                                                    startActivity(intent);

                                                } else {
                                                    Toast.makeText(login.this, "invalid Credentials", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    }

        );
        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Regiter.class);
                startActivity(intent);
            }
        });
    }
}
