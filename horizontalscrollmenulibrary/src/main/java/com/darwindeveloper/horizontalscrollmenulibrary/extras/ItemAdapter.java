package com.darwindeveloper.horizontalscrollmenulibrary.extras;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.darwindeveloper.horizontalscrollmenulibrary.R;
import com.darwindeveloper.horizontalscrollmenulibrary.custom_views.CircularTextView;

import java.util.ArrayList;

/**
 * Created by Darwin Morocho on 9/3/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {


    private Context context;
    private ArrayList<MenuItem> menuItems;
    private int icon_width, icon_height;

    public ItemAdapter(Context context, ArrayList<MenuItem> menuItems, int icon_width, int icon_height) {
        this.context = context;
        this.menuItems = menuItems;
        this.icon_width = icon_width;
        this.icon_height = icon_height;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        MenuItem menuItem = menuItems.get(position);
        holder.textItem.setText(menuItem.getText());
        holder.icon.setImageResource(menuItem.getIcon());

        android.view.ViewGroup.LayoutParams layoutParams = holder.icon.getLayoutParams();
        layoutParams.width = icon_width;
        layoutParams.height = icon_height;
        holder.icon.setLayoutParams(layoutParams);

        if (menuItem.isSelected()) {
            holder.selected.setBackgroundColor(Color.parseColor("#0099cc"));
        } else {
            holder.selected.setBackgroundColor(Color.parseColor("#ffffff"));
        }

    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        CircularTextView notification;
        TextView textItem;
        ImageView icon;
        View selected;


        public ItemViewHolder(View itemView) {
            super(itemView);
            notification = (CircularTextView) itemView.findViewById(R.id.notification);
            textItem = (TextView) itemView.findViewById(R.id.textViewItem);
            icon = (ImageView) itemView.findViewById(R.id.imageViewItem);
            selected = itemView.findViewById(R.id.viewItemSelected);

        }


    }
}
