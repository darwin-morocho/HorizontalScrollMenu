package com.darwindeveloper.horizontalscrollmenulibrary.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
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

    private int itemSeletected = 0;


    //attrs
    private int icon_width = 60;
    private int icon_height = 60;
    private int backgroundMenuColor = Color.parseColor("#FFFFFF");
    private int backgroundNotifications = Color.parseColor("#FF0000");
    private int item_textColor = Color.parseColor("#000000");
    private int item_backgroundColor = Color.parseColor("#FFFFFF");
    private int item_marginTop = 0;
    private int item_marginBottom = 0;
    private int item_marginLeft = 0;
    private int item_marginRight = 0;
    private int item_colorSelected = Color.parseColor("#0099cc");
    private int item_textSize = 12;

    public HorizontalScrollMenuView(Context context) {
        super(context);
        this.context = (AppCompatActivity) context;
        init();
    }

    public HorizontalScrollMenuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = (AppCompatActivity) context;


        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HorizontalScrollMenuView, 0, 0);
        icon_height = a.getDimensionPixelSize(R.styleable.HorizontalScrollMenuView_icon_height, 60);
        icon_width = a.getDimensionPixelSize(R.styleable.HorizontalScrollMenuView_icon_width, 60);
        backgroundMenuColor = a.getColor(R.styleable.HorizontalScrollMenuView_backgroundMenuColor, backgroundMenuColor);
        backgroundNotifications = a.getColor(R.styleable.HorizontalScrollMenuView_backgroundNotifications, backgroundNotifications);
        item_textColor = a.getColor(R.styleable.HorizontalScrollMenuView_item_textColor, item_textColor);
        item_backgroundColor = a.getColor(R.styleable.HorizontalScrollMenuView_item_backgroundColor, item_backgroundColor);
        item_colorSelected = a.getColor(R.styleable.HorizontalScrollMenuView_item_colorSelected, item_colorSelected);
        item_marginTop = a.getDimensionPixelSize(R.styleable.HorizontalScrollMenuView_item_marginTop, 0);
        item_marginBottom = a.getDimensionPixelSize(R.styleable.HorizontalScrollMenuView_item_marginBottom, 0);
        item_marginLeft = a.getDimensionPixelSize(R.styleable.HorizontalScrollMenuView_item_marginLeft, 0);
        item_marginRight = a.getDimensionPixelSize(R.styleable.HorizontalScrollMenuView_item_marginRight, 0);
        item_textSize = a.getDimensionPixelSize(R.styleable.HorizontalScrollMenuView_item_textSize, 12);

        a.recycle();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.hscroll_menu, this, true);

        init();

    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        itemAdapter = new ItemAdapter(context, menuItems, icon_width, icon_height, item_textColor,
                item_backgroundColor, item_marginTop, item_marginBottom, item_marginLeft,
                item_marginRight, item_colorSelected, item_textSize, backgroundNotifications);

        itemAdapter.setOnHSItemClickListener(new ItemAdapter.OnHSItemClickListener() {
            @Override
            public void onHSClick(MenuItem menuItem, int position) {
                onHSMenuClickListener.onHSMClick(menuItem, position);
            }
        });
        recyclerView.setAdapter(itemAdapter);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        recyclerView.setBackgroundColor(backgroundMenuColor);
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
     * agrega un nuevo item al menu, pero este se mostrara como seleccionado
     *
     * @param text    texto del item
     * @param icon    icono para el item
     * @param seleted
     */
    public void addItem(String text, int icon, boolean seleted, int numNotifications) {
        menuItems.add(new MenuItem(icon, text, seleted));
        menuItems.get(menuItems.size() - 1).setNumNotifications(numNotifications);
        menuItems.get(menuItems.size() - 1).setNotifications(true);
    }


    /**
     * agrega un nuevo item al menu, pero este se mostrara como seleccionado
     *
     * @param text texto del item
     */
    public void addItem(String text, int icon, int numNotifications) {
        menuItems.get(menuItems.size() - 1).setNumNotifications(numNotifications);
        menuItems.get(menuItems.size() - 1).setNotifications(true);
    }

    /**
     * @return retorna  la posicion del item seleccionado en el menu
     */
    public int getItemSeletected() {
        return itemSeletected;
    }


    /**
     * edita un item del menu
     * @param position          posicion del item en el menu
     * @param text              nuevo texto para el item
     * @param icon              nuevo icono para el item
     * @param showNotifications true o false para decidir si se muestra el badge de notificaciones
     * @param numNotifications  numero de notificaciones en el badge
     */
    public void editItem(int position, String text, int icon, boolean showNotifications, int numNotifications) {
        menuItems.get(position).setNumNotifications(numNotifications);
        menuItems.get(position).setNotifications(showNotifications);
        menuItems.get(position).setIcon(icon);
        menuItems.get(position).setText(text);

        itemAdapter.notifyItemChanged(position);
        itemAdapter.notifyDataSetChanged();
    }


    /**
     * muestra todos los items del menu
     */
    public void showItems() {
        itemAdapter.notifyItemRangeInserted(0, menuItems.size() - 1);
        itemAdapter.notifyDataSetChanged();
    }


    /**
     * cambia el estado de un item a seleccionado
     *
     * @param position posicion del item en el menu
     */
    public void setItemSelected(int position) {
        if (menuItems.size() > 0) {
            for (int i = 0; i < menuItems.size(); i++) {
                if (i == position)
                    menuItems.get(i).setSelected(true);
                else
                    menuItems.get(i).setSelected(false);
            }
            itemAdapter.notifyItemRangeChanged(0, menuItems.size() - 1);
            itemAdapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(position);
            itemSeletected = position;
        }
    }


    /**
     * @return retorna el numero de items que tiene el menu
     */
    public int numItems() {
        return menuItems.size();
    }


    /**
     * retorna un item del menu
     *
     * @param position posicion del item en el menu (desde 0)
     * @return
     */
    public MenuItem getItem(int position) {
        return menuItems.get(position);
    }


    public interface OnHSMenuClickListener {
        /**
         * para capturar los eventos cuando se da click sobre un item del menu
         *
         * @param menuItem item seleccionado
         * @param position posicion del item en el menu (iniciando desde 0)
         */
        void onHSMClick(MenuItem menuItem, int position);
    }


    private OnHSMenuClickListener onHSMenuClickListener;

    public void setOnHSMenuClickListener(OnHSMenuClickListener onHSMenuClickListener) {
        this.onHSMenuClickListener = onHSMenuClickListener;
    }
}
