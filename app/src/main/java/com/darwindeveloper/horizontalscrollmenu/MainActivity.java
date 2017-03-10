package com.darwindeveloper.horizontalscrollmenu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.darwindeveloper.horizontalscrollmenulibrary.custom_views.HorizontalScrollMenuView;
import com.darwindeveloper.horizontalscrollmenulibrary.extras.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    HorizontalScrollMenuView horizontal_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        horizontal_menu = (HorizontalScrollMenuView) findViewById(R.id.horizontal_menu);
        viewPager = (ViewPager) findViewById(R.id.pager);


        //agregamos los items al menu
        horizontal_menu.addItem("Home", R.drawable.home_icon, true);
        horizontal_menu.addItem("Account", R.drawable.round_account);
        horizontal_menu.addItem("Message", R.drawable.close_envelope);
        horizontal_menu.addItem("Downloads", R.drawable.download);
        horizontal_menu.addItem("Calendar", R.drawable.calendarw);
        horizontal_menu.addItem("Events", R.drawable.notifications,2);

        horizontal_menu.showItems();//hacemos visible los items del menu


        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //cambiamos el elemto seleccionado del menu a la posicion actual del viewpager
                horizontal_menu.setItemSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //implementamos la interface para caputar los clic en el menu
        horizontal_menu.setOnHSMenuClickListener(new HorizontalScrollMenuView.OnHSMenuClickListener() {
            @Override
            public void onHSMClick(MenuItem menuItem, int position) {
                Toast.makeText(MainActivity.this, "item " + position, Toast.LENGTH_SHORT).show();
                //cambiamos el viewpager a la posicion elegida en el menu
                viewPager.setCurrentItem(position);
            }
        });


    }


    private class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = new MenuItemFragment();
            Bundle bundle = new Bundle();
            MenuItem menuItem = horizontal_menu.getItem(position);
            bundle.putString(MenuItemFragment.ITEM_TEXT, menuItem.getText());
            fragment.setArguments(bundle);

            return fragment;
        }

        @Override
        public int getCount() {
            return horizontal_menu.numItems();
        }
    }
}
