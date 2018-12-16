package com.qalex.m7md.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by m7md on 15/07/18.
 */
public class CustomAdapter extends BaseAdapter{

    Context CustomContext;

    ArrayList list;
    public  CustomAdapter(Context context , ArrayList arrayList){

        this.CustomContext = context;

        this.list=arrayList;

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(CustomContext);
        view = inflater.inflate(R.layout.custom_list,null);

        TextView txt11 = (TextView) view.findViewById(R.id.textView11);
        TextView txt22 = (TextView) view.findViewById(R.id.textView22);

        ImageView img = (ImageView) view.findViewById(R.id.imageView);

        txt11.setText(list.get(i).toString());
        txt22.setText("Desc: vlalsfkjsd");
       // img.setImageResource(CustomImages[i]);



        return view;
    }
}
