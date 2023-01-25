package com.example.stepikcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {

    private String Username;
    private String Drink;
    private String DrinkType;
    private String Additives;

    private TextView TextViewName;
    private TextView TextViewDrink;
    private TextView TextViewDrinkType;
    private TextView textViewAdditives;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        InitVar();
        GetUpVariables();
        SetUp();
    }


    protected static Intent newIntent(
            Context context,
            String Username,
            String Drink,
            String DrinkType,
            String Additives
    ) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra("Username", Username);
        intent.putExtra("Drink", Drink);
        intent.putExtra("DrinkType", DrinkType);
        intent.putExtra("Additives", Additives);
        return intent;
    }

    private void GetUpVariables() {
        Username = getIntent().getStringExtra("Username");
        Drink = getIntent().getStringExtra("Drink");
        DrinkType = getIntent().getStringExtra("DrinkType");
        Additives = getIntent().getStringExtra("Additives");
    }

    private void InitVar() {
        TextViewName = findViewById(R.id.TextViewName);
        TextViewDrink = findViewById(R.id.TextViewDrink);
        TextViewDrinkType = findViewById(R.id.TextViewDrinkType);
        textViewAdditives = findViewById(R.id.textViewAddictives);
    }

    private void SetUp() {
        TextViewName.setText(Username);
        TextViewDrink.setText(Drink);
        TextViewDrinkType.setText(DrinkType);
        textViewAdditives.setText(Additives);
    }


}