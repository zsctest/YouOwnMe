package com.jnu.youownme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jnu.youownme.dataprocessor.HomeDisplay;
import com.jnu.youownme.dataprocessor.ShouLi;

import java.util.ArrayList;
import java.util.List;

public class HomeDisplayAdapter extends ArrayAdapter<HomeDisplay> {
    private int resourceId;

    public HomeDisplayAdapter(@NonNull Context context, int resource, @NonNull ArrayList<HomeDisplay> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HomeDisplay homedisplayrecord = getItem(position);
        View view;
        if(null == convertView)
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        else
            view = convertView;       //convertView是否缓存
        assert homedisplayrecord != null;
        ((ImageView) view.findViewById(R.id.image_view_home_icon)).setImageResource(homedisplayrecord.getImageResourceId());
        ((TextView) view.findViewById(R.id.text_view_home_name)).setText(homedisplayrecord.getName());
        ((TextView) view.findViewById(R.id.text_view_home_about)).setText(homedisplayrecord.getAbout());
        ((TextView) view.findViewById(R.id.text_view_home_money)).setText(homedisplayrecord.getMoney());
        ((TextView) view.findViewById(R.id.text_view_home_date)).setText(homedisplayrecord.getDate());
        return view;
    }
}
