package com.sawapps.baymaxhealthcare.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sawapps.baymaxhealthcare.DoctorProfileActivity;
import com.sawapps.baymaxhealthcare.DoctorsList;
import com.sawapps.baymaxhealthcare.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    public static  final String KEY_NAME = "name";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_ADDR = "address";

    private List<DoctorsList> doctorsLists;
    private  Context context;

    public DoctorAdapter(List<DoctorsList> doctorslist, Context applicationContext) {
        this.doctorsLists = doctorslist;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.doctors_list, viewGroup, false);

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final DoctorsList doctorsList = doctorsLists.get(i);
        viewHolder.doc_name.setText(doctorsList.get_doc_name());
        viewHolder.doc_address.setText(doctorsList.get_doc_addr());

        Picasso.get().load(doctorsList.get_doc_img()).into(viewHolder.doc_image);

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoctorsList doctorsList1 = doctorsLists.get(i);
                Intent skipIntent = new Intent(v.getContext(), DoctorProfileActivity.class);
                skipIntent.putExtra(KEY_NAME, doctorsList1.get_doc_name());
                skipIntent.putExtra(KEY_ADDR, doctorsList1.get_doc_addr());
                skipIntent.putExtra(KEY_IMAGE, doctorsList1.get_doc_img());
                v.getContext().startActivity(skipIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorsLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView doc_name;
        public ImageView doc_image;
        public  TextView doc_address;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            doc_name = (TextView) itemView.findViewById(R.id.doctor_name);
            doc_image = (ImageView) itemView.findViewById(R.id.doctor_img);
            doc_address = (TextView) itemView.findViewById(R.id.doctor_addr);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
