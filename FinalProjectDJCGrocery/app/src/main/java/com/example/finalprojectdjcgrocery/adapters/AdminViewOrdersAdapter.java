package com.example.finalprojectdjcgrocery.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalprojectdjcgrocery.R;

import java.util.ArrayList;

public class AdminViewOrdersAdapter extends RecyclerView.Adapter<AdminViewOrdersAdapter.AdminViewOrdersViewHolder>{

    Context context;
    ArrayList<String> userList;
    ArrayList<String> dateList;
    ArrayList<String> deliveryList;
    ArrayList<String> priceList;

    public AdminViewOrdersAdapter(Context context, ArrayList<String> userList, ArrayList<String> dateList, ArrayList<String> deliveryList, ArrayList<String> priceList) {
        this.context = context;
        this.userList = userList;
        this.dateList = dateList;
        this.deliveryList = deliveryList;
        this.priceList = priceList;
    }

    @NonNull
    @Override
    public AdminViewOrdersAdapter.AdminViewOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_child_admin_view_orders, parent, false);
        return new AdminViewOrdersAdapter.AdminViewOrdersViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewOrdersAdapter.AdminViewOrdersViewHolder holder, int position) {
        holder.userTxt.setText(userList.get(position));
        holder.dateTxt.setText(dateList.get(position));
        holder.deliveryTxt.setText(deliveryList.get(position));
        holder.priceTxt.setText(priceList.get(position));
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class AdminViewOrdersViewHolder extends RecyclerView.ViewHolder {
        TextView userTxt, dateTxt, deliveryTxt, priceTxt;

        public AdminViewOrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            userTxt = (TextView) itemView.findViewById(R.id.admin_view_order_username);
            dateTxt = (TextView) itemView.findViewById(R.id.admin_view_order_date);
            deliveryTxt = (TextView) itemView.findViewById(R.id.admin_view_order_delivery);
            priceTxt = (TextView) itemView.findViewById(R.id.admin_view_order_price);

        }
    }
}

