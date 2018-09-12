package com.example.yana.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_fragment);
        getGreeting();
    }

    protected void getGreeting() {
        final EditText editText = (EditText) findViewById(R.id.edit_text);
        final Button clearButton = (Button) findViewById(R.id.clear_button);
        final Button showSayHiButton = (Button) findViewById(R.id.name_button);
        final TextView showText = (TextView) findViewById(R.id.show_text);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
            }
        });

        showSayHiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                showText.setText("Hi " + name + "!");
            }
        });
    }
}
