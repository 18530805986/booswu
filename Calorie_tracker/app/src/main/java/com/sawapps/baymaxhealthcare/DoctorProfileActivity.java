package com.sawapps.baymaxhealthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sawapps.baymaxhealthcare.Adapters.DoctorAdapter;
import com.squareup.picasso.Picasso;

public class DoctorProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        ImageView doctor_img = (ImageView) findViewById(R.id.doctor_img);
        TextView doctor_name = (TextView) findViewById(R.id.doctor_name);
        TextView doctor_addr = (TextView) findViewById(R.id.doctor_addr);


        Intent intent = getIntent();
        final String userName = intent.getStringExtra(DoctorAdapter.KEY_NAME);
        String image = intent.getStringExtra(DoctorAdapter.KEY_IMAGE);
        final String addr = intent.getStringExtra(DoctorAdapter.KEY_ADDR);


        Picasso.get().load(image).into(doctor_img);

        doctor_name.setText(userName);
        doctor_addr.setText(addr);

    }
}