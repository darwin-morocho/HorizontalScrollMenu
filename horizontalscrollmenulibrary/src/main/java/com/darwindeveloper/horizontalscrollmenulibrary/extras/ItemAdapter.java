package com.darwindeveloper.horizontalscrollmenulibrary.extras;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    private int icon_width = 60;
    private int icon_height = 60;
    private int item_textColor = Color.parseColor("#000000");
    private int item_backgroundColor = Color.parseColor("#FFFFFF");
    private int item_marginTop = 0;
    private int item_marginBottom = 0;
    private int item_marginLeft = 0;
    private int item_marginRight = 0;
    private int item_colorSelected = Color.parseColor("#0099cc");
    private int item_textSize = 12;
    private int background_notification;

    public ItemAdapter(Context context, ArrayList<MenuItem> menuItems, int icon_width,
                       int icon_height, int item_textColor,
                       int item_backgroundColor, int item_marginTop,
                       int item_marginBottom, int item_marginLeft, int item_marginRight,
                       int item_colorSelected, int item_textSize, int background_notification) {
        this.context = context;
        this.menuItems = menuItems;
        this.icon_width = icon_width;
        this.icon_height = icon_height;
        this.item_textColor = item_textColor;
        this.item_backgroundColor = item_backgroundColor;
        ;
        this.item_marginTop = item_marginTop;
        this.item_marginBottom = item_marginBottom;
        this.item_marginLeft = item_marginLeft;
        this.item_marginRight = item_marginRight;
        this.item_colorSelected = item_colorSelected;
        this.item_textSize = item_textSize;
        this.background_notification = background_notification;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        final MenuItem menuItem = menuItems.get(position);


        holder.itemView.setBackgroundColor(item_backgroundColor);


        holder.textItem.setText(menuItem.getText());
        holder.textItem.setTextColor(item_textColor);
        holder.textItem.setTextSize(item_textSize);

        holder.itemView.setPadding(item_marginLeft, item_marginTop, item_marginRight, item_marginBottom);
        holder.icon.setImageResource(menuItem.getIcon());
        android.view.ViewGroup.LayoutParams layoutParams = holder.icon.getLayoutParams();
        layoutParams.width = icon_width;
        layoutParams.height = icon_height;
        holder.icon.setLayoutParams(layoutParams);
        holder.notification.setSolidColor(background_notification);
        holder.notification.setStrokeColor(background_notification);
        holder.notification.setTextColor(Color.parseColor("#ffffff"));
        holder.notification.setText(menuItem.getNumNotifications() + "");

        if (menuItem.isSelected()) {
            holder.selected.setBackgroundColor(item_colorSelected);
        } else {
            holder.selected.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        }

        if (menuItem.isNotifications()) {
            holder.notification.setVisibility(View.VISIBLE);

        } else {
            holder.notification.setVisibility(View.GONE);
        }


        holder.item_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHSItemClickListener.onHSClick(menuItem, position);
            }
        });


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
        RelativeLayout item_content;


        public ItemViewHolder(View itemView) {
            super(itemView);
            notification = (CircularTextView) itemView.findViewById(R.id.notification);
            textItem = (TextView) itemView.findViewById(R.id.textViewItem);
            icon = (ImageView) itemView.findViewById(R.id.imageViewItem);
            selected = itemView.findViewById(R.id.viewItemSelected);
            item_content = (RelativeLayout) itemView.findViewById(R.id.item_content);
        }
    }


    public interface OnHSItemClickListener {
        void onHSClick(MenuItem menuItem, int position);
    }

    private OnHSItemClickListener onHSItemClickListener;

    public void setOnHSItemClickListener(OnHSItemClickListener onHSItemClickListener) {
        this.onHSItemClickListener = onHSItemClickListener;
    }
}
