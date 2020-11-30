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

import com.jnu.youownme.dataprocessor.SuiLi;

import java.util.List;

public class SuiLiAdapter extends ArrayAdapter<SuiLi> {
    private int resourceId;

    public SuiLiAdapter(@NonNull Context context, int resource, @NonNull List<SuiLi> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SuiLi suilirecord = getItem(position);
        View view;
        if(null == convertView)
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        else
            view = convertView;       //convertView是否缓存
        assert suilirecord != null;
        ((ImageView) view.findViewById(R.id.image_view_suili_icon)).setImageResource(SuiLi.getImageResourceId());
        ((TextView) view.findViewById(R.id.text_view_name)).setText(suilirecord.getName());
        ((TextView) view.findViewById(R.id.text_view_about)).setText(suilirecord.getAbout());
        ((TextView) view.findViewById(R.id.text_view_money)).setText('￥'+suilirecord.getMoney());
        ((TextView) view.findViewById(R.id.text_view_date)).setText(suilirecord.getDate());
        return view;
    }


}
