package com.example.finalprojectdjcgrocery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>{

    Context context;
    ArrayList<String> catList;

    public CategoriesAdapter(Context context, ArrayList<String> catList) {
        this.context = context;
        this.catList = catList;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_child_categories, parent, false);
        return new CategoriesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        String category = catList.get(position);
        holder.catName.setText(category);
//        holder.catName.setText(category.getName());
//        holder.catImg.set
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder {
        TextView catName;
//        ImageView catImg;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            catName = itemView.findViewById(R.id.textView2);
//            catImg = itemView.findViewById(R.id.imageView3);

        }
    }
}
