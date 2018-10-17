package com.example.yana.testapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    public ArrayList<User> userArrayList;
    public static final String NAME_VALID = "^[A-Z][a-zA-Z]+$";
    public static final String PHONE_VALID = "^\\+?[0-9]{10,16}$";
    public static final String EMAIL_VALID = "^[a-zA-Z0-9+_.-]+@[a-zA-Z]+\\.[A-Za-z]{2,5}$";
    public static final String PASSWORD_VALID = "^[0-9]+$";
    protected TextView result;
    protected String text;
    protected EditText firstNameInput;
    protected EditText lastNameInput;
    protected EditText emailInput;
    protected EditText phoneInput;
    protected EditText passwordInput;
    protected EditText verifyPasswordInput;
    protected Button submitButtonInput;
    protected Boolean validation;
    protected Button list_users_button;

    @Override
    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userArrayList = new ArrayList<>();

        result = findViewById(R.id.result);
        firstNameInput = findViewById(R.id.first_name_input);
        lastNameInput = findViewById(R.id.last_name_input);
        emailInput = findViewById(R.id.email_input);
        phoneInput = findViewById(R.id.phone_input);
        passwordInput = findViewById(R.id.password_input);
        verifyPasswordInput = findViewById(R.id.verify_password_input);
        submitButtonInput = findViewById(R.id.submit_button);
        list_users_button = findViewById(R.id.list_view_button);

        buttonOnClick();
        showUserList();
    }

    public void buttonOnClick() {

        submitButtonInput.setOnClickListener(new View.OnClickListener() {

            @Override
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                validation = true;
                stringChecker(firstNameInput, NAME_VALID, "first name");
                stringChecker(lastNameInput, NAME_VALID, "last name");
                stringChecker(emailInput, EMAIL_VALID, "email");
                stringChecker(phoneInput, PHONE_VALID, "phone");
                stringChecker(passwordInput, PASSWORD_VALID, "password");
                passwordChecker();
                if (validation) {
                    result.setText("Correct!");
                    saveUser();
                }
            }
        });
    }

    public void showUserList() {
        list_users_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent user_list = new Intent(getBaseContext(), UserActivity.class);
                startActivity(user_list);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void stringChecker(EditText row_id, String term, String row_name) {
        String value = String.valueOf(row_id.getText());
        String existing_value = String.valueOf(result.getText());
        if (value.equals("")) {
            validation = false;
            result.setText(existing_value + "\nEmpty " + row_name);
            row_id.setError("Empty " + row_name);
        } else if (!(value.matches(term))) {
            validation = false;
            result.setText(existing_value + "\nIncorrect " + row_name);
            row_id.setError("Incorrect " + row_name);
        }
    }

    @SuppressLint("SetTextI18n")
    public void passwordChecker() {
        String password_value = String.valueOf(passwordInput.getText());
        String valid_password_value = String.valueOf(verifyPasswordInput.getText());
        if (valid_password_value.equals("") && (password_value.equals(""))) {
            validation = false;
            verifyPasswordInput.setError("Any field can't be empty");
            String existing_value = String.valueOf(result.getText());
            result.setText("Any field can't be empty\n" + existing_value);
        }
        if (!(password_value.equals(valid_password_value))) {
            validation = false;
            verifyPasswordInput.setError("Passwords aren't similar");
            String existing_value = String.valueOf(result.getText());
            result.setText("Passwords aren't similar\n" + existing_value);
        }
    }

    public void saveUser(){
        String first_name_value = String.valueOf(firstNameInput.getText());
        String last_name_value = String.valueOf(lastNameInput.getText());
        String email_value = String.valueOf(emailInput.getText());
        String phone_value = String.valueOf(phoneInput.getText());
        String password_value = String.valueOf(passwordInput.getText());

        User user = new User(first_name_value,
                last_name_value, email_value, phone_value, password_value);

        userArrayList.add(user);

        Gson gson = new Gson();
        String json = gson.toJson(userArrayList);
        Log.i("users", json);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(
                "user_list",
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_list",json);
        editor.apply();
    }
}
