package com.darwindeveloper.horizontalscrollmenu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Darwin Morocho on 10/3/2017.
 */

public class MenuItemFragment extends Fragment {

    public static final String ITEM_TEXT = "texto_del_item";


    private Context context;
    private View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = LayoutInflater.from(context).inflate(R.layout.fragment_menu_item, container, false);

        TextView textView =(TextView) rootView.findViewById(R.id.textView);

        String txt = getArguments().getString(ITEM_TEXT);
        textView.setText(txt);


        return rootView;
    }
}
