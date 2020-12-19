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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jnu.youownme.calendar_new.MonthDateView;
import com.jnu.youownme.dataprocessor.DataBankForDispaly;
import com.jnu.youownme.dataprocessor.HomeDisplay;

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
//    private String date = "2020/11/26";
    private String date;
    private HomeDisplayAdapter adapter;
    private DataBankForDispaly dataBankForDispaly;
    private View recordView;

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
        recordView = inflater.inflate(R.layout.fragment_home, container, false);

//        initData();
//        initView(view);
//        Log.i("test","more");
        return recordView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final View v = view;
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

        monthDateView.setTextView(tv_date, tv_week);
        monthDateView.setDaysHasThingList(list);
        monthDateView.setDateClick(new MonthDateView.DateClick() {

            @Override
            public void onClickOnDate() {
                Toast.makeText(that.getContext(), "点击了：" + monthDateView.getmSelDay(), Toast.LENGTH_SHORT).show();
                date = monthDateView.getmSelYear()+"/"+(monthDateView.getmSelMonth()+1)+"/"+monthDateView.getmSelDay();
                Log.i("date","touch"+monthDateView.getmSelDay()+(monthDateView.getmSelMonth()+1)+monthDateView.getmSelYear()+" "+date);

                dataBankForDispaly = new DataBankForDispaly(that.getContext());
                dataBankForDispaly.Load(date);
                Log.i("date","size:"+dataBankForDispaly.getArrayListDisplay().size());
                adapter = new HomeDisplayAdapter(that.getContext(),R.layout.items_home_display,dataBankForDispaly.getArrayListDisplay());
                ListView listview = ((ListView) recordView.findViewById(R.id.list_view_home_display));
                listview.setAdapter(adapter);

            }
        });
//        initData(date);
//        initView();
        setOnlistener();
//        if(dataBankForDispaly.getArrayListDisplay().size()>0){
//            //TODO:显示list view数据
//            adapter.notifyDataSetChanged();
//        }
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

    private void initView(){
        //初始化listview
        adapter = new HomeDisplayAdapter(that.getContext(),R.layout.items_home_display,dataBankForDispaly.getArrayListDisplay());
        ListView listview = ((ListView) recordView.findViewById(R.id.list_view_home_display));
        listview.setAdapter(adapter);
        this.registerForContextMenu(listview);
    }


    private void initData(String date){
        //初始化数据，应该放在监听函数里
        dataBankForDispaly = new DataBankForDispaly(that.getContext());
        dataBankForDispaly.Load(date);
//        dataBankForDispaly.getArrayListDisplay().add(
//                    new HomeDisplay("姓名","关于","金额","时间")
//            );
//        dataBankForDispaly.Save();
//        Log.i("date",dataBankForDispaly.getArrayListDisplay().get(0).getDate());
    }
}