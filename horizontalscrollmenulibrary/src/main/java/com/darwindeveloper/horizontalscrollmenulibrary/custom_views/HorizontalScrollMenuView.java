package com.darwindeveloper.horizontalscrollmenulibrary.custom_views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.darwindeveloper.horizontalscrollmenulibrary.R;
import com.darwindeveloper.horizontalscrollmenulibrary.extras.ItemAdapter;
import com.darwindeveloper.horizontalscrollmenulibrary.extras.MenuItem;

import java.util.ArrayList;

/**
 * Created by Darwin Morocho on 9/3/2017.
 */

public class HorizontalScrollMenuView extends LinearLayout {

    private AppCompatActivity context;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private ArrayList<MenuItem> menuItems = new ArrayList<>();

    public HorizontalScrollMenuView(Context context) {
        super(context);
        this.context = (AppCompatActivity) context;
        init();
    }

    public HorizontalScrollMenuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = (AppCompatActivity) context;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.hscroll_menu, this, true);

        init();

    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        itemAdapter = new ItemAdapter(context, menuItems, 60, 60);
        recyclerView.setAdapter(itemAdapter);


    }


    /**
     * agrega un nuevo item al menu
     *
     * @param text texto del item
     * @param icon icono para el item
     */
    public void addItem(String text, int icon) {
        menuItems.add(new MenuItem(icon, text));
    }

    /**
     * agrega un nuevo item al menu, pero este se mostrara como seleccionado
     *
     * @param text    texto del item
     * @param icon    icono para el item
     * @param seleted
     */
    public void addItem(String text, int icon, boolean seleted) {
        menuItems.add(new MenuItem(icon, text, seleted));
    }


    /**
     * muestra todos los items del menu
     */
    public void showItems() {
        itemAdapter.notifyItemRangeInserted(0, menuItems.size() - 1);
        itemAdapter.notifyDataSetChanged();
    }


}
