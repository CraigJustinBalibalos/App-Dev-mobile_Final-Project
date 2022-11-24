package com.example.finalprojectdjcgrocery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private DatabaseReference ref;
    private LayoutInflater myInflater;

    public CategoriesAdapter(DatabaseReference ref, LayoutInflater myInflater) {
        this.ref = ref;
        this.myInflater = myInflater;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = myInflater.inflate(R.layout.row_child_categories, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
//        holder.name.setText();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name;
        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imageView2);
            name = itemView.findViewById(R.id.textView2);
        }
    }
}
