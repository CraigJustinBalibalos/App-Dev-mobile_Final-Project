package com.example.finalprojectdjcgrocery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>{

    //onBindViewHolder method incomplete

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
//        String date = catList.get(position);
//        holder.dateTxt.setText(category);
//        holder.deliveryTxt.setText(category);
//        holder.priceTxt.setText(category);

    }


    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public static class OrdersViewHolder extends RecyclerView.ViewHolder {
        TextView dateTxt, deliveryTxt, priceTxt;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTxt = (TextView) itemView.findViewById(R.id.user_view_order_date);
            deliveryTxt = (TextView) itemView.findViewById(R.id.user_view_order_delivery);
            priceTxt = (TextView) itemView.findViewById(R.id.user_view_order_price);

        }
    }
}
