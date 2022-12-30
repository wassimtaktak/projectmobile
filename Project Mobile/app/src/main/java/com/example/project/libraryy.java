package com.example.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class libraryy extends AppCompatActivity {
    SearchView searchView;
    RecyclerView recyclerView;
    private Activity activity;
    FloatingActionButton add_button;
    DatabaseHelper db;
    ArrayList<String> book_id, book_title, book_author, book_isbn;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libraryy);
        recyclerView = findViewById(R.id.recycleView);
//        add_button = findViewById(R.id.addbtn);
        searchView = findViewById(R.id.searchView);
        activity = libraryy.this;
        db = new DatabaseHelper(libraryy.this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_isbn = new ArrayList<>();

        storeDataInArrays();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(libraryy.this, libraryy.class);
                intent.putExtra("Search", String.valueOf(query));
                startActivity(intent);
                finish();


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });


        customAdapter = new CustomAdapter(libraryy.this,this, book_id, book_title, book_author,
                book_isbn);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(libraryy.this));



        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
        void storeDataInArrays(){
            if(getIntent().hasExtra("Search")){

                String word = getIntent().getStringExtra("Search");

                Cursor cursor = db.SearchData(word);
                if(cursor.getCount() == 0){
                    Toast.makeText(libraryy.this, "No match", Toast.LENGTH_SHORT).show();
                }else {
                    while (cursor.moveToNext()) {
                        book_id.add(cursor.getString(0));
                        book_title.add(cursor.getString(1));
                        book_author.add(cursor.getString(2));
                        book_isbn.add(cursor.getString(3));
                    }
                }
            }
            else {
                Cursor cursor = db.readAllData();
                if (cursor.getCount() == 0) {
                    Toast.makeText(libraryy.this, "empty", Toast.LENGTH_SHORT).show();
                } else {
                    while (cursor.moveToNext()) {
                        book_id.add(cursor.getString(0));
                        book_title.add(cursor.getString(1));
                        book_author.add(cursor.getString(2));
                        book_isbn.add(cursor.getString(3));
                    }
                }
            }
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.Add){
            Intent intent = new Intent(libraryy.this , Addbook.class);
            activity.startActivityForResult(intent, 1);
        }
        return super.onOptionsItemSelected(item);
    }
        void Search(String word){
            Cursor cursor = db.SearchData(word);
            if(cursor.getCount() == 0){
                Toast.makeText(libraryy.this, "No match", Toast.LENGTH_SHORT).show();
            }else {
                while (cursor.moveToNext()) {
                    book_id.add(cursor.getString(0));
                    book_title.add(cursor.getString(1));
                    book_author.add(cursor.getString(2));
                    book_isbn.add(cursor.getString(3));
                }
            }

        }
    }
