package com.example.stepikcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonSingIn = findViewById(R.id.buttonSingIn);
        buttonSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextName = findViewById(R.id.editTextName);
                String TextName = editTextName.getText().toString().trim();
                EditText editTextPassword = findViewById(R.id.editTextPassword);
                String TextPassword = editTextPassword.getText().toString().trim();

                if(TextName.isEmpty() || TextPassword.isEmpty()) {
                    Toast.makeText(
                            MainActivity.this,
                            R.string.hint,
                            Toast.LENGTH_SHORT
                    ).show();
                }else{
                    Intent intent = MakeOrderActivity.newIntent( MainActivity.this, TextName);
                    startActivity(intent);
                }

            }
        });


    }
}