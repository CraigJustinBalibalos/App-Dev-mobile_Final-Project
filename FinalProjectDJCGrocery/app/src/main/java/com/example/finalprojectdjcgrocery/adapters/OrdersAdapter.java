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

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>{

    Context context;
    ArrayList<String> dateList;
    ArrayList<String> deliveryList;
    ArrayList<String> priceList;

    public OrdersAdapter(Context context, ArrayList<String> dateList, ArrayList<String> deliveryList, ArrayList<String> priceList) {
        this.context = context;
        this.dateList = dateList;
        this.deliveryList = deliveryList;
        this.priceList = priceList;
    }

    @NonNull
    @Override
    public OrdersAdapter.OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_child_user_view_order, parent, false);
        return new OrdersAdapter.OrdersViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        holder.dateTxt.setText(dateList.get(position));
        holder.deliveryTxt.setText(deliveryList.get(position));
        holder.priceTxt.setText(priceList.get(position));
    }


    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public static class OrdersViewHolder extends RecyclerView.ViewHolder {
        TextView dateTxt, deliveryTxt, priceTxt;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTxt = (TextView) itemView.findViewById(R.id.admin_view_order_date);
            deliveryTxt = (TextView) itemView.findViewById(R.id.admin_view_order_delivery);
            priceTxt = (TextView) itemView.findViewById(R.id.admin_view_order_price);

        }
    }
}
