package com.example.yana.testapp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String NAME_VALID = "^[A-Z][a-zA-Z]+$";
    public static final String EMAIL_VALID = "^[a-zA-Z0-9+_.-]+@[a-zA-Z]+\\.[A-Za-z]{2,4}$";
    public static final String PHONE_VALID = "^\\+?[0-9]{10,16}$";
    public static final String PASSWORD_VALID = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    protected TextView result;
    protected String text;
    protected EditText first_name_input;
    protected EditText last_name_input;
    protected EditText email_input;
    protected EditText phone_input;
    protected EditText password_input;
    protected EditText verify_password_input;
    protected Button submit_button_input;
    protected Boolean validation;


    @Override
    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        first_name_input = findViewById(R.id.first_name_input);
        last_name_input = findViewById(R.id.last_name_input);
        email_input = findViewById(R.id.email_input);
        phone_input = findViewById(R.id.phone_input);
        password_input = findViewById(R.id.password_input);
        verify_password_input = findViewById(R.id.verify_password_input);
        submit_button_input = findViewById(R.id.submit_button);

        ButtonOnClick();
    }

    public void ButtonOnClick() {

        submit_button_input.setOnClickListener(new View.OnClickListener() {

            @Override
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                result.setText("");
                validation = true;
                StringChecker(first_name_input, NAME_VALID);
                StringChecker(last_name_input, NAME_VALID);
                StringChecker(email_input, EMAIL_VALID);
                StringChecker(phone_input, PHONE_VALID);
                StringChecker(password_input, PASSWORD_VALID);
                PasswordChecker();
                if (validation) {
                    result.setText("Correct!");
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void StringChecker(EditText row_id, String term) {
        String value = String.valueOf(row_id.getText());
        String existing_value = String.valueOf(result.getText());
        if (value.equals("")) {
            validation = false;
            result.setText(existing_value + "Can't be empty\n");
            row_id.setError("Can't be empty\n");
        } else if (!(value.matches(term))) {
            validation = false;
            result.setText(existing_value + "Incorrect field");
            row_id.setError("Incorrect field");
        }
    }

    @SuppressLint("SetTextI18n")
    public void PasswordChecker() {
        String password_value = String.valueOf(password_input.getText());
        String valid_password_value = String.valueOf(verify_password_input.getText());
        if (valid_password_value.equals("") && (password_value.equals(""))) {
            validation = false;
            verify_password_input.setError("Can't be empty");
            String existing_value = String.valueOf(result.getText());
            result.setText("Can't be empty\n" + existing_value);
        }
        if (!(password_value.equals(valid_password_value))) {
            validation = false;
            verify_password_input.setError("Passwords aren't similar");
            String existing_value = String.valueOf(result.getText());
            result.setText("Passwords aren't similar\n" + existing_value);
        }
    }
}
