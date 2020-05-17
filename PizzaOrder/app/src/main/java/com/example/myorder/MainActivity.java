package com.example.myorder;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public String orderedItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public float get_total(View view){
        CheckBox chkCheese = findViewById(R.id.chkCheese);
        CheckBox chkHawaiian = findViewById(R.id.chkHawaiian);
        CheckBox chkSupreme = findViewById(R.id.chkSupreme);
        EditText txtCheeseQ= findViewById(R.id.txtCheeseQ);
        EditText txtHawaiianQ= findViewById(R.id.txtHawaiianQ);
        EditText txtSupremeQ= findViewById(R.id.txtSupremeQ);

        Boolean cheeseV =chkCheese.isChecked();
        Boolean hawaiianV =chkHawaiian.isChecked();
        Boolean supremeV =chkSupreme.isChecked();
        Integer cheesePizzaQ = 0;
        Integer hawaiianQ =0;
        Integer supremeQ=0;

        if(!txtCheeseQ.getText().toString().isEmpty()){
            cheesePizzaQ = Integer.parseInt(txtCheeseQ.getText().toString());
        }
        if(!txtHawaiianQ.getText().toString().isEmpty()){
            hawaiianQ =Integer.parseInt(txtHawaiianQ.getText().toString());
        }
        if(!txtSupremeQ.getText().toString().isEmpty()){
            supremeQ =Integer.parseInt(txtSupremeQ.getText().toString());
        }
        float total = calculate(cheeseV,hawaiianV,supremeV,cheesePizzaQ,hawaiianQ,supremeQ);
        return total;

    }
    public void sum(View view){
        float total = get_total(view);

        Intent redirect = new Intent(MainActivity.this, SummaryActivity.class).putExtra("total",total+"").putExtra("orderedItems",orderedItems);
        startActivity(redirect);
    }
    public void add(View view) {
        float total = get_total(view);

        Intent redirect = new Intent(MainActivity.this, OrderActivity.class).putExtra("total",total+"").putExtra("orderedItems",orderedItems);
        startActivity(redirect);
    }

    public float calculate(Boolean cheeseV,Boolean hawaiianV,Boolean supremeV,Integer cheesePizzaQ,Integer hawaiianQ,Integer supremeQ){
        orderedItems="";
        float total =0;
        if(cheeseV){
            total = total + (15 * cheesePizzaQ);
            orderedItems = orderedItems+" Cheese Pizza Quantity:"+cheesePizzaQ ;
        }
        if(hawaiianV){
            total = total + (10 * hawaiianQ);
            orderedItems = orderedItems+"\n Hawaiian Pizza Quantity:"+hawaiianQ;
        }
        if(supremeV){
            total = total + (20 * supremeQ);
            orderedItems = orderedItems+"\n Supreme Pizza Quantity:"+supremeQ;
        }
        return total;
    }
}
