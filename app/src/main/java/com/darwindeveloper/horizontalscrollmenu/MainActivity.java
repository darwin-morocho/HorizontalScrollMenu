package com.darwindeveloper.horizontalscrollmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.darwindeveloper.horizontalscrollmenulibrary.custom_views.HorizontalScrollMenuView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HorizontalScrollMenuView horizontal_menu = (HorizontalScrollMenuView) findViewById(R.id.horizontal_menu);
        //agregamos los items al menu
        horizontal_menu.addItem("School", R.drawable.school,true);
        horizontal_menu.addItem("Homework", R.drawable.homework);
        horizontal_menu.addItem("Graduate", R.drawable.graduate);
        horizontal_menu.addItem("Notepad", R.drawable.notepad);
        horizontal_menu.addItem("Calendar", R.drawable.calendar);

        horizontal_menu.showItems();//hacemos visible los items del menu



    }
}
