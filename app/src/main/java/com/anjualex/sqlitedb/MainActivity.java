package com.anjualex.sqlitedb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("isregistered", false)) {

        }


        setContentView(R.layout.activity_main);
    }

    private void opennextactivity() {
        startActivity(new Intent(this, Main2Activity.class));
        this.finish();
   }


    public void register(View view) {
        EditText nametxt = (EditText) findViewById(R.id.nametext);
        String name = nametxt.getText().toString();
        EditText emailtext = (EditText) findViewById(R.id.emailtext);
        String email = emailtext.getText().toString();
        EditText passwordtext = (EditText) findViewById(R.id.passwordtext);
        String password = passwordtext.getText().toString();
        if (name.isEmpty() || email.isEmpty() || password.isEmpty())
            Toast.makeText(MainActivity.this, "Reqired field missing", Toast.LENGTH_SHORT).show();

        else {
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("UsersDB", MODE_PRIVATE, null);
            String create = "CREATE TABLE IF NOT EXISTS USERS(ID INTEGER PRIMARY KEY AUTOINCREMENT , Name VARCHAR , Email VARCHAR , Password VARCHAR);";
            sqLiteDatabase.execSQL(create);

            String insert = "INSERT INTO USERS(Name, Email, Password) values('" + name + "','" + email + "','" + password + "');";
            sqLiteDatabase.execSQL(insert);

            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            sharedPreferences.edit().putBoolean("isRegistered", true).apply();
          opennextactivity();
        }
    }
}