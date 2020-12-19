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

import com.jnu.youownme.dataprocessor.ShouLi;
import com.jnu.youownme.dataprocessor.SuiLi;


import java.util.List;

public class ShouLiAdapter extends ArrayAdapter<ShouLi> {
    private int resourceId;

    public ShouLiAdapter(@NonNull Context context, int resource, @NonNull List<ShouLi> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ShouLi shoulirecord = getItem(position);
        View view;
        if(null == convertView)
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        else
            view = convertView;       //convertView是否缓存
        assert shoulirecord != null;
        ((ImageView) view.findViewById(R.id.image_view_shouli_icon)).setImageResource(R.drawable.shouli);
        ((TextView) view.findViewById(R.id.text_view_name)).setText(shoulirecord.getName());
        ((TextView) view.findViewById(R.id.text_view_about)).setText(shoulirecord.getAbout());
        ((TextView) view.findViewById(R.id.text_view_money)).setText('￥'+shoulirecord.getMoney());
        ((TextView) view.findViewById(R.id.text_view_date)).setText(shoulirecord.getDate());
        return view;
    }


}
