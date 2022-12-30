package com.example.project;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Addbook extends AppCompatActivity {
    EditText title_input,author_input,isbn_input;
    Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);
        title_input = findViewById(R.id.title_input2);
        author_input = findViewById(R.id.author_input2);
        isbn_input = findViewById(R.id.isbn_input2);
        add_button = findViewById(R.id.update_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db  = new DatabaseHelper(Addbook.this);
                db.addBook(title_input.getText().toString().trim(),
                        author_input.getText().toString().trim(),
                        Integer.valueOf(isbn_input.getText().toString().trim()));
            }
        });
    }
}