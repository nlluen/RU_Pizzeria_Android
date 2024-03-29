package com.example.ru_pizzeria_android;


import static com.example.ru_pizzeria_android.MainActivity.orderNum;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * This is an Adapter class to be used to instantiate an adapter for the RecyclerView.
 * @author Ahnaf Rashid and Nick Lluen
 */
class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsHolder>{
    private Context context; //need the context to inflate the layout
    private ArrayList<Item> items; //need the data binding to each row of RecyclerView

    public ItemsAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    /**
     * This method will inflate the row layout for the items in the RecyclerView
     */
    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the row layout for the items
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);

        return new ItemsHolder(view);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        //assign values for each row
        holder.tv_name.setText(items.get(position).getItemName());
        holder.im_item.setImageResource(items.get(position).getImage());
    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return items.size(); //number of MenuItem in the array list.
    }

    /**
     * Get the views from the row layout file, similar to the onCreate() method.
     */
    public static class ItemsHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private ImageView im_item;
        private ConstraintLayout parentLayout; //this is the row layout

        public ItemsHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_flavor);
            im_item = itemView.findViewById(R.id.im_item);
            parentLayout = itemView.findViewById(R.id.rowLayout);

            parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), ChicagoActivity.class);
                    String input = tv_name.getText().toString();
                    if(input.equalsIgnoreCase("Chicago BBQ Chicken")) {
                        intent = new Intent(itemView.getContext(), ChicagoActivity.class);
                        intent.putExtra("ITEM", "bbq");
                    }else if(input.equalsIgnoreCase("Chicago Deluxe")){
                        intent = new Intent(itemView.getContext(), ChicagoActivity.class);
                        intent.putExtra("ITEM", "deluxe");
                    }else if(input.equalsIgnoreCase("Chicago Meatzza")){
                        intent = new Intent(itemView.getContext(), ChicagoActivity.class);
                        intent.putExtra("ITEM", "meatzza");
                    }else if(input.equalsIgnoreCase("Chicago Build Your Own")){
                        intent = new Intent(itemView.getContext(), ChicagoActivity.class);
                        intent.putExtra("ITEM", "byo");
                    }else if(input.equalsIgnoreCase("New York BBQ Chicken")){
                        intent = new Intent(itemView.getContext(), NYActivity.class);
                        intent.putExtra("ITEM", "bbq");
                    }else if(input.equalsIgnoreCase("New York Deluxe")){
                        intent = new Intent(itemView.getContext(), NYActivity.class);
                        intent.putExtra("ITEM", "deluxe");
                    }else if(input.equalsIgnoreCase("New York Meatzza")){
                        intent = new Intent(itemView.getContext(), NYActivity.class);
                        intent.putExtra("ITEM", "meatzza");
                    }else if(input.equalsIgnoreCase("New York Build Your Own")){
                        intent = new Intent(itemView.getContext(), NYActivity.class);
                        intent.putExtra("ITEM", "byo");
                    }else if(input.equalsIgnoreCase("Current Order")){
                        intent = new Intent(itemView.getContext(), CurrentOrderActivity.class);
                        intent.putExtra("OrderNUM", orderNum);
                    }else if(input.equalsIgnoreCase("Store Orders")){
                        intent = new Intent(itemView.getContext(), StoreOrderActivity.class);
                    }
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
