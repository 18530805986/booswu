package com.example.myorder;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {
    public String total;
    public String orderedItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        total = getIntent().getStringExtra("total");
        orderedItems = getIntent().getStringExtra("orderedItems");
        TextView lblOrderSummary =findViewById(R.id.lblOrderSummary);
        lblOrderSummary .setText("Total : "+total+ "\n\n Ordered Items : \n"+orderedItems );
    }

    public void add(View view) {
        Intent redirect = new Intent(SummaryActivity.this, OrderActivity.class).putExtra("total",total+"").putExtra("orderedItems",orderedItems);
        startActivity(redirect);
    }
}
