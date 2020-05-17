package com.example.myorder;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {
    public String total;
    public String orderedItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        total = getIntent().getStringExtra("total");
        orderedItems = getIntent().getStringExtra("orderedItems");

        Intent emailSend = new Intent(Intent.ACTION_SEND);
        emailSend.setType("message/rfc822");
        emailSend.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
        emailSend.putExtra(Intent.EXTRA_SUBJECT, "Your Order");
        emailSend.putExtra(Intent.EXTRA_TEXT   , "Total : "+total+ "\n\n Ordered Items : \n"+orderedItems );
        try {
            startActivity(Intent.createChooser(emailSend, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(OrderActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
