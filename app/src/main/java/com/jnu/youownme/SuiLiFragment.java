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

import com.jnu.youownme.dataprocessor.DataBankForSuiLi;
import com.jnu.youownme.dataprocessor.SuiLi;

import static android.app.Activity.RESULT_OK;

/**
 * 用于存放随礼列表，具有上下文菜单
 */
public class SuiLiFragment extends Fragment {

    public static final int REQUEST_CODE_ADD_NEW = 100;
    public static final int REQUEST_CODE_RENAME_SUILI = 101;
    private DataBankForSuiLi dataBankForSuiLi;
    private SuiLiAdapter adapter;



    public SuiLiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sui_li, container, false);
        initData();
        initView(view);
        return view;
    }

    private void initView(View view) {
        adapter = new SuiLiAdapter(this.getContext(), R.layout.item_suili, dataBankForSuiLi.getSuiliRecords());
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                SimpleListMainActivity.this, android.R.layout.simple_list_item_1, data);  //创建一个适配器对象（ArrayAdapter类型），携带数据data
        ListView listview = ((ListView) view.findViewById(R.id.listview_suili));
        listview.setAdapter(adapter);    //为listview设置适配器
        this.registerForContextMenu(listview);
    }

    private void initData() {
        dataBankForSuiLi=new DataBankForSuiLi(this.getContext());
        dataBankForSuiLi.Load();
        if(0==dataBankForSuiLi.getSuiliRecords().size())
        {
            dataBankForSuiLi.getSuiliRecords().add(
                    new SuiLi("赵某某","结婚大喜","300","2020/11/26"));
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
        final SuiLiFragment that = this;
        switch(item.getItemId())
        {
            case R.id.context_menu_item1:
                //新建
                //Log.i("result","新建中");
                intent = new Intent(this.getContext(),EditSuiLiActivity.class);    //intent的传输方向
                intent.putExtra("position", position);//获取当前的索引值
                intent.putExtra("suiliname",dataBankForSuiLi.getSuiliRecords().get(position).getName());
                intent.putExtra("suiliabout",dataBankForSuiLi.getSuiliRecords().get(position).getAbout());
                intent.putExtra("suilimoney",dataBankForSuiLi.getSuiliRecords().get(position).getMoney());
                intent.putExtra("suilidate",dataBankForSuiLi.getSuiliRecords().get(position).getDate());
                startActivityForResult(intent, REQUEST_CODE_ADD_NEW);                            //向EditBookActivity发送数据请求，requestCode唯一标识该活动
                break;
            case R.id.context_menu_item2:
                //删除
                AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
                builder.setTitle(R.string.query);
                builder.setMessage(getResources().getString(R.string.queryContent)+' '+dataBankForSuiLi.getSuiliRecords().get(position).getName()+"?");
                //getResources().getString(R.string.queryContent)返回字符串资源id对应的字符串
                builder.setCancelable(true);      //可以点击对话框外使对话框消失从而取消操作
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //监听函数，在点击确定按钮后执行的操作
                        dataBankForSuiLi.getSuiliRecords().remove(position);
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
                intent = new Intent(this.getContext(),EditSuiLiActivity.class);    //intent的传输方向
                intent.putExtra("position", position);//获取当前的索引值
                intent.putExtra("suiliname",dataBankForSuiLi.getSuiliRecords().get(position).getName());
                intent.putExtra("suiliabout",dataBankForSuiLi.getSuiliRecords().get(position).getAbout());
                intent.putExtra("suilimoney",dataBankForSuiLi.getSuiliRecords().get(position).getMoney());
                intent.putExtra("suilidate",dataBankForSuiLi.getSuiliRecords().get(position).getDate());
                startActivityForResult(intent, REQUEST_CODE_RENAME_SUILI);
                Toast.makeText(that.getActivity(),R.string.renameOk,Toast.LENGTH_SHORT).show();
                break;
            case R.id.context_menu_item4:
                //关于
                //TODO:跳转到详情页
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
                    String name = data.getStringExtra("newsuiliname");
                    String about = data.getStringExtra("newsuiliabout");
                    String money = data.getStringExtra("newsuilimoney");
                    String date = data.getStringExtra("newsuilidate");
                    //调用data的而不是getIntent().getIntExtra()

                    dataBankForSuiLi.getSuiliRecords().add(position,new SuiLi(name,about,money,date));   //新增默认记录,以book_no_name为封面
                    dataBankForSuiLi.Save();
                    adapter.notifyDataSetChanged();    //更新
                    Toast.makeText(this.getContext(),R.string.newOk,Toast.LENGTH_SHORT).show();
                }
                break;

            case REQUEST_CODE_RENAME_SUILI:
                //修改随礼记录
                if(resultCode == RESULT_OK){
                    assert data != null;
                    String name = data.getStringExtra("newsuiliname");
                    String about = data.getStringExtra("newsuiliabout");
                    String money = data.getStringExtra("newsuilimoney");
                    String date = data.getStringExtra("newsuilidate");
                    int position = data.getIntExtra("position",0);
                    dataBankForSuiLi.getSuiliRecords().get(position).setName(name);
                    dataBankForSuiLi.getSuiliRecords().get(position).setAbout(about);
                    dataBankForSuiLi.getSuiliRecords().get(position).setMoney(money);
                    dataBankForSuiLi.getSuiliRecords().get(position).setDate(date);
                    dataBankForSuiLi.Save();
                    adapter.notifyDataSetChanged();    //更新
                }
                break;

            default:
                Log.i("default","switch default");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}

//TODO:添加悬浮的新建菜单
//TODO:修改随礼记录时，数据应为原有数据，新建则应为默认数据，现在是相反的,建议新建时直接调取另一个