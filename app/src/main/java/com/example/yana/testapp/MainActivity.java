package com.example.yana.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView showName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        showName = findViewById(R.id.show_text);
        initNameButton();
        initClearButton();

    }

    public void initNameButton() {
        findViewById(R.id.name_button).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showGreeting();
                    }
                }
        );
    }

    private void initClearButton() {
        findViewById(R.id.clear_button).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editText.setText("");
                    }
                }
        );
    }
    private void showGreeting() {
        String name = editText.getText().toString();

        showName.setText("Hi " + name + "!");
    }
}