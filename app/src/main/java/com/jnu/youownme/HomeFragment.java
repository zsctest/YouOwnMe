package com.jnu.youownme;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jnu.youownme.calendar_new.MonthDateView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HomeFragment extends Fragment {
    private ImageView iv_left;
    private ImageView iv_right;
    private TextView tv_date;
    private TextView tv_week;
    private TextView tv_today;
    private MonthDateView monthDateView;
    private HomeFragment that =this;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        List<Integer> list = new ArrayList<Integer>();
//        list.add(10);
//        list.add(12);
//        list.add(15);
//        list.add(16);
//        iv_left = (ImageView) that.getActivity().findViewById(R.id.iv_left);
//        iv_right = (ImageView) that.getActivity().findViewById(R.id.iv_right);
//        monthDateView = (MonthDateView) Objects.requireNonNull(that.getActivity()).findViewById(R.id.monthDateView);
//        tv_date = (TextView) that.getActivity().findViewById(R.id.date_text);
//        tv_week = (TextView) that.getActivity().findViewById(R.id.week_text);
//        tv_today = (TextView) that.getActivity().findViewById(R.id.tv_today);
//        if(monthDateView == null && iv_left == null && iv_right == null && tv_date == null && tv_today ==null && tv_week == null)
//            Log.i("get","nothing");
//        monthDateView.setTextView(tv_date, tv_week);
        //TODO:bug
//        monthDateView.setDaysHasThingList(list);
//        monthDateView.setDateClick(new MonthDateView.DateClick() {
//
//            @Override
//            public void onClickOnDate() {
//                Toast.makeText(that.getContext(), "点击了：" + monthDateView.getmSelDay(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        setOnlistener();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Integer> list = new ArrayList<Integer>();
        list.add(10);
        list.add(12);
        list.add(15);
        list.add(16);
        iv_left = (ImageView) that.getActivity().findViewById(R.id.iv_left);
        iv_right = (ImageView) that.getActivity().findViewById(R.id.iv_right);
        monthDateView = (MonthDateView) Objects.requireNonNull(that.getActivity()).findViewById(R.id.monthDateView);
        tv_date = (TextView) that.getActivity().findViewById(R.id.date_text);
        tv_week = (TextView) that.getActivity().findViewById(R.id.week_text);
        tv_today = (TextView) that.getActivity().findViewById(R.id.tv_today);
        if(monthDateView == null && iv_left == null && iv_right == null && tv_date == null && tv_today ==null && tv_week == null)
            Log.i("get","nothing");
        else
            Log.i("get","something");

        monthDateView.setTextView(tv_date, tv_week);
        //TODO:bug
        monthDateView.setDaysHasThingList(list);
        monthDateView.setDateClick(new MonthDateView.DateClick() {

            @Override
            public void onClickOnDate() {
                Toast.makeText(that.getContext(), "点击了：" + monthDateView.getmSelDay(), Toast.LENGTH_SHORT).show();
            }
        });
        setOnlistener();
    }

    private void setOnlistener(){
        iv_left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                monthDateView.onLeftClick();
            }
        });

        iv_right.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                monthDateView.onRightClick();
            }
        });

        tv_today.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                monthDateView.setTodayToView();
            }
        });
    }
}