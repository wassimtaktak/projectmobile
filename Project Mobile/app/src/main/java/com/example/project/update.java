package com.example.project;

import static java.lang.Integer.valueOf;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update extends AppCompatActivity {
    EditText title_input, author_input, isbn_input;
    Button update_button,delete_button;

    String id, title, author, isbn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        title_input= findViewById(R.id.update_title);
        author_input= findViewById(R.id.update_author);
        isbn_input= findViewById(R.id.update_isbn);
        update_button= findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(update.this);
                title = title_input.getText().toString().trim();
                author = author_input.getText().toString().trim();
                isbn = isbn_input.getText().toString().trim();
                db.updatedata(id,title,author,valueOf(isbn));
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(update.this);
                db.deleteOneRow(id);
                finish();
            }
        });
    }
    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("isbn")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            isbn = getIntent().getStringExtra("isbn");

            //Setting Intent Data
            title_input.setText(title);
            author_input.setText(author);
            isbn_input.setText(isbn);

        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}