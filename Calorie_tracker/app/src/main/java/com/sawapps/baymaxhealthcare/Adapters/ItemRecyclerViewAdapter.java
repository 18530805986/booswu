package com.sawapps.baymaxhealthcare.Adapters;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.sawapps.baymaxhealthcare.Network.Responses.Item;
import com.sawapps.baymaxhealthcare.Network.Responses.ValueObject;
import com.sawapps.baymaxhealthcare.R;

import java.util.List;


public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.MealsViewHolder> {

    List<Item> data;

    public ItemRecyclerViewAdapter(List<Item> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MealsViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MealsViewHolder holder, int position) {
        try {
            Item current = data.get(position);
            if (current != null) {


                String builder = "Day " + current.day + "\t\t";

                switch (current.slot) {

                    case 1:

                        builder += "Breakfast";
                        break;

                    case 2:

                        builder += "Lunch";
                        break;

                    case 3:

                        builder += "Dinner";
                        break;
                }

                holder.header.setText(builder);

                ValueObject valueObject = new Gson().fromJson(current.value, ValueObject.class);

                holder.title.setText(valueObject.title);
                holder.readyInMinutes.setText(valueObject.readyInMinutes);

                String imageUrl = "https://spoonacular.com/recipeImages/" + valueObject.id + "-312x231." + valueObject.imageType;

                Log.v("imageurl", imageUrl);

                holder.imageView.setVisibility(View.GONE);
                holder.imageView.setImageDrawable(null);
                Glide.with(holder.itemView.getContext())
                        .load(imageUrl)
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                if (resource != null) {
                                    holder.imageView.setVisibility(View.VISIBLE);
                                    holder.imageView.setImageDrawable(resource);
                                } else {
                                    holder.imageView.setVisibility(View.GONE);
                                    holder.imageView.setImageDrawable(null);
                                }
                            }

                            @Override
                            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                                super.onLoadFailed(errorDrawable);
                                holder.imageView.setVisibility(View.GONE);
                                holder.imageView.setImageDrawable(null);
                            }
                        });

            }

        } catch (Exception e) {
            throw e;
        }
    }


    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class MealsViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView header;
        TextView readyInMinutes;
        ImageView imageView;

        public MealsViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            header = itemView.findViewById(R.id.header);
            readyInMinutes = itemView.findViewById(R.id.readyInMinutes);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
