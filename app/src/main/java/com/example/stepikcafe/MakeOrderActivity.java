package com.example.stepikcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MakeOrderActivity extends AppCompatActivity {

    private static final String EXTRA_USER_NAME = "TextName";

    private TextView GreetingUser;
    private TextView textViewAddictives;

    private RadioGroup radioGroupDrinks;
    private RadioButton radioButtonTea;
    private RadioButton radioButtonCoffee;

    private CheckBox checkbox_sugar;
    private CheckBox checkbox_milk;
    private CheckBox checkbox_lemon;

    private Spinner SpinnerTea;
    private Spinner SpinnerCoffee;

    private Button ButtonMakeOrder;

    private String TextName;
    private String drink;
    private String drinkType;
    private ArrayList<String> additives;


    public static Intent newIntent(Context context, String TextName) {
        Intent intent = new Intent(context, MakeOrderActivity.class);
        intent.putExtra(EXTRA_USER_NAME, TextName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);

        initViews();
        SetUpUserName();

        radioGroupDrinks.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) { //id это id выбранного радиобатона
                if (id == radioButtonTea.getId()) {
                    onUserChoseTea ();
                } else if(id == radioButtonCoffee.getId()) {
                    onUserChoseCoffee ();
                }
            }
        });
        radioButtonTea.setChecked(true); // Выбираем чай по-умолчанию(чтобы не было %s)
        onUserMadeOrder();

        ButtonMakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = OrderDetailActivity.newIntent(
                        MakeOrderActivity.this,
                        TextName,
                        drink,
                        drinkType,
                        additives.toString()
                );
                startActivity(intent);
            }
        });


    }

    private void onUserMadeOrder() {
        additives = new ArrayList<>();
        if(checkbox_lemon.isChecked()) {
            additives.add(checkbox_lemon.getText().toString());
        }
        if(checkbox_milk.isChecked()) {
            additives.add(checkbox_milk.getText().toString());
        }
        if(radioButtonTea.isChecked() && checkbox_sugar.isChecked()) {
            additives.add(checkbox_sugar.getText().toString());
        }

        drinkType = "";
        if(radioButtonTea.isChecked()) {
            drinkType = SpinnerTea.getSelectedItem().toString();
        } else if (radioButtonCoffee.isChecked()) {
            drinkType = SpinnerCoffee.getSelectedItem().toString();
        }
    }


    private void initViews() {
         GreetingUser = findViewById(R.id.GreetingUser);
         textViewAddictives = findViewById(R.id.textViewAddictives);

         radioGroupDrinks = findViewById(R.id.radioGroupDrinks);
         radioButtonTea = findViewById(R.id.radioButtonTea);
         radioButtonCoffee = findViewById(R.id.radioButtonCoffee);

         checkbox_sugar = findViewById(R.id.checkbox_sugar);
         checkbox_milk = findViewById(R.id.checkbox_milk);
         checkbox_lemon = findViewById(R.id.checkbox_lemon);

         SpinnerTea = findViewById(R.id.SpinnerTea);
         SpinnerCoffee = findViewById(R.id.SpinnerCoffee);

         ButtonMakeOrder = findViewById(R.id.ButtonMakeOrder);
    }

    private void SetUpUserName() {
        TextName = getIntent().getStringExtra(EXTRA_USER_NAME);
        String greetings = getString(R.string.greeting_user, TextName);
        GreetingUser.setText(greetings);
    }

    private void onUserChoseTea () {
        drink = getString(R.string.tea);
        String Addictives = getString(R.string.AddictionsQuestion, drink);
        textViewAddictives.setText(Addictives);
        checkbox_lemon.setVisibility(View.VISIBLE);
        SpinnerTea.setVisibility(View.VISIBLE);
        SpinnerCoffee.setVisibility(View.INVISIBLE);
    }

    private void onUserChoseCoffee () {
        drink = getString(R.string.coffee);
        String Addictives = getString(R.string.AddictionsQuestion, drink);
        textViewAddictives.setText(Addictives);
        checkbox_lemon.setVisibility(View.INVISIBLE);
        SpinnerTea.setVisibility(View.INVISIBLE);
        SpinnerCoffee.setVisibility(View.VISIBLE);
    }
}

