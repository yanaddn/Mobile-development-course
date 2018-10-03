package com.example.yana.testapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_fragment);

        Gson gson = new Gson();
        List<User> listOfUsers;
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                "user_list", Context.MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString("user_list", "");
        Log.i("users", jsonPreferences);

        if(!jsonPreferences.equals("")){
            Type type = new TypeToken<List<User>>() {}.getType();
            listOfUsers = gson.fromJson(jsonPreferences, type);
            Log.i("users", listOfUsers.toString());

            ListView userListView = findViewById(R.id.list_view_users);
            ArrayAdapter<User> arrayAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    listOfUsers);

            userListView.setAdapter(arrayAdapter);
        }
    }
}
