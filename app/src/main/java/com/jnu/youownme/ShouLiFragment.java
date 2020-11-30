package com.jnu.youownme;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jnu.youownme.dataprocessor.DataBankForShouLi;
import com.jnu.youownme.dataprocessor.ShouLi;


import static android.app.Activity.RESULT_OK;

/**
 * 用于存放随礼列表，具有上下文菜单
 */
public class ShouLiFragment extends Fragment {

    public static final int REQUEST_CODE_ADD_NEW = 100;
    public static final int REQUEST_CODE_RENAME_SHOULI = 101;
    private DataBankForShouLi dataBankForShouLi;
    private ShouLiAdapter adapter;



    public ShouLiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shou_li, container, false);
        initData();
        initView(view);
        return view;
    }

    private void initView(View view) {
        adapter = new ShouLiAdapter(this.getContext(), R.layout.items_shouli, dataBankForShouLi.getShouliRecords());
        ListView listview = ((ListView) view.findViewById(R.id.listview_shouli));
        listview.setAdapter(adapter);    //为listview设置适配器
        this.registerForContextMenu(listview);
    }

    private void initData() {
        dataBankForShouLi=new DataBankForShouLi(this.getContext());
        dataBankForShouLi.Load();
        if(0==dataBankForShouLi.getShouliRecords().size())
        {
            dataBankForShouLi.getShouliRecords().add(
                    new ShouLi("赵某某","结婚大喜","300","2020/11/26"));
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuInfo){
        menu.setHeaderTitle("上下文菜单");
        MenuInflater inflater = this.getActivity().getMenuInflater();       //getActivity()
        inflater.inflate(R.menu.main,menu);
        super.onCreateContextMenu(menu,v,menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int position = menuInfo.position;
        Intent intent;
        final ShouLiFragment that = this;
        switch(item.getItemId())
        {
            case R.id.context_menu_item1:
                //新建
                //Log.i("result","新建中");
                intent = new Intent(this.getContext(),EditShouLiActivity.class);    //intent的传输方向
                intent.putExtra("position", position);//获取当前的索引值
                intent.putExtra("shouliname",dataBankForShouLi.getShouliRecords().get(position).getName());
                intent.putExtra("shouliabout",dataBankForShouLi.getShouliRecords().get(position).getAbout());
                intent.putExtra("shoulimoney",dataBankForShouLi.getShouliRecords().get(position).getMoney());
                intent.putExtra("shoulidate",dataBankForShouLi.getShouliRecords().get(position).getDate());
                startActivityForResult(intent, REQUEST_CODE_ADD_NEW);                            //向EditBookActivity发送数据请求，requestCode唯一标识该活动
                break;
            case R.id.context_menu_item2:
                //删除
                AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
                builder.setTitle(R.string.query);
                builder.setMessage(getResources().getString(R.string.queryContent)+' '+dataBankForShouLi.getShouliRecords().get(position).getName()+"?");
                //getResources().getString(R.string.queryContent)返回字符串资源id对应的字符串
                builder.setCancelable(true);      //可以点击对话框外使对话框消失从而取消操作
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //监听函数，在点击确定按钮后执行的操作
                        dataBankForShouLi.getShouliRecords().remove(position);
                        adapter.notifyDataSetChanged();
//                        Toast.makeText(that.getActivity(),R.string.deleteOk,Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //啥都不干
                    }
                });
                builder.create().show();
                Toast.makeText(that.getActivity(),R.string.deleteOk,Toast.LENGTH_SHORT).show();
                break;
            case R.id.context_menu_item3:
                //重命名（修改）
                Log.i("result","修改中");
                intent = new Intent(this.getContext(),EditShouLiActivity.class);    //intent的传输方向
                intent.putExtra("position", position);//获取当前的索引值
                intent.putExtra("suiliname",dataBankForShouLi.getShouliRecords().get(position).getName());
                intent.putExtra("suiliabout",dataBankForShouLi.getShouliRecords().get(position).getAbout());
                intent.putExtra("suilimoney",dataBankForShouLi.getShouliRecords().get(position).getMoney());
                intent.putExtra("suilidate",dataBankForShouLi.getShouliRecords().get(position).getDate());
                startActivityForResult(intent, REQUEST_CODE_RENAME_SHOULI);
                Toast.makeText(that.getActivity(),R.string.renameOk,Toast.LENGTH_SHORT).show();
                break;
            case R.id.context_menu_item4:
                //关于

                Toast.makeText(that.getActivity(),R.string.about,Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //响应返回的intent
        switch(requestCode){
            case REQUEST_CODE_ADD_NEW:
                //新添随礼记录
                Log.i("result","REQUEST_CODE_ADD_NEW");
                if(resultCode == RESULT_OK){
                    //接收返回的intent
                    assert data != null;
                    int position = data.getIntExtra("position",0);
                    String name = data.getStringExtra("newshouliname");
                    String about = data.getStringExtra("newshouliabout");
                    String money = data.getStringExtra("newshoulimoney");
                    String date = data.getStringExtra("newshoulidate");
                    //调用data的而不是getIntent().getIntExtra()

                    dataBankForShouLi.getShouliRecords().add(position,new ShouLi(name,about,money,date));   //新增默认记录,以book_no_name为封面
                    dataBankForShouLi.Save();
                    adapter.notifyDataSetChanged();    //更新
                    Toast.makeText(this.getContext(),R.string.newOk,Toast.LENGTH_SHORT).show();
                }
                break;

            case REQUEST_CODE_RENAME_SHOULI:
                //修改随礼记录
                if(resultCode == RESULT_OK){
                    assert data != null;
                    String name = data.getStringExtra("newshouliname");
                    String about = data.getStringExtra("newshouliabout");
                    String money = data.getStringExtra("newshoulimoney");
                    String date = data.getStringExtra("newshoulidate");
                    int position = data.getIntExtra("position",0);
                    dataBankForShouLi.getShouliRecords().get(position).setName(name);
                    dataBankForShouLi.getShouliRecords().get(position).setAbout(about);
                    dataBankForShouLi.getShouliRecords().get(position).setMoney(money);
                    dataBankForShouLi.getShouliRecords().get(position).setDate(date);
                    dataBankForShouLi.Save();
                    adapter.notifyDataSetChanged();    //更新
                }
                break;

            default:
                Log.i("default","switch default");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}

