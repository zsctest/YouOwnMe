package com.jnu.youownme.dataprocessor;

import android.content.Context;
import android.util.Log;


import com.jnu.youownme.R;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class DataBankForDispaly {
    private static final String RECORD_FILE_NAME = "homedisplay.txt";
    private ArrayList<HomeDisplay> arrayListDisplay = new ArrayList<>();
    private Context context;


    private final String RECORD_FILE_SHOULI = "shouli.txt";
    private final String RECORD_FILE_SUILI = "suili.txt";
    public DataBankForDispaly(Context context){
        this.context = context;

    }
    public ArrayList<HomeDisplay> getArrayListDisplay(){
        return arrayListDisplay;
    }

    public void Save(){
        //只读不存
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(context.openFileOutput(RECORD_FILE_NAME,Context.MODE_PRIVATE));
            oos.writeObject(arrayListDisplay);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Load(String date){
        Log.i("date","load date is "+date);
        ObjectInputStream ois_shouli = null;
        ObjectInputStream ois_suili = null;
        ArrayList<ShouLi> tempListShouLi = new ArrayList<>();
        ArrayList<SuiLi> tempListSuiLi = new ArrayList<>();
        try{
            //从收礼记录那获取
            ois_shouli = new ObjectInputStream(context.openFileInput(RECORD_FILE_SHOULI));
            tempListShouLi = (ArrayList<ShouLi>) ois_shouli.readObject();
            if(arrayListDisplay.size()>0 && tempListShouLi.size() == 0) {
                arrayListDisplay.clear();
                Log.i("date","remove1");
            }
            //TODO:无法匹配字符串
            for(int i=0;i<tempListShouLi.size();++i){
                if(getShouLi(date,tempListShouLi,i)!=null)
                    arrayListDisplay.add(getShouLi(date,tempListShouLi,i));
            }
            tempListShouLi.clear();
        }catch(Exception e){
            e.printStackTrace();
        }

        try{
            //从随礼记录那获取
            ois_suili = new ObjectInputStream(context.openFileInput(RECORD_FILE_SUILI));
            tempListSuiLi = (ArrayList<SuiLi>) ois_suili.readObject();
            if(arrayListDisplay.size()>0 && tempListSuiLi.size() == 0) {
                arrayListDisplay.clear();
                Log.i("date","remove1");
            }
            for(int i=0;i<tempListSuiLi.size();++i){
                if(getSuiLi(date,tempListSuiLi,i)!=null)
                    arrayListDisplay.add(getSuiLi(date,tempListSuiLi,i));
            }
            tempListSuiLi.clear();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected HomeDisplay rematch(String str, ArrayList<HomeDisplay> list,int i){
        //把list中与str不匹配的元素去掉
        HomeDisplay temp;
        HomeDisplay goal = null;
        String record;
        if(0 != list.size()) {
            temp = list.get(i);
            record= temp.getDate();           //不能get？
            if (record.equals(str)) {
                goal = list.get(i);
            }
        }
        return goal;
    }

    protected HomeDisplay getShouLi(String str,ArrayList<ShouLi> list,int i){
        String name,date,about,money;
        int image;
        date = list.get(i).getDate();
        if(str.equals(date)) {
            Log.i("date","true");
            name = list.get(i).getName();
            about = list.get(i).getAbout();
            money = "￥"+list.get(i).getMoney();
            image = R.drawable.shouli;
            return new HomeDisplay(image,name, about, money, date);
        }
        return null;
    }

    protected HomeDisplay getSuiLi(String str,ArrayList<SuiLi> list,int i){
        String name,date,about,money;
        int image;
        date = list.get(i).getDate();
        if(str.equals(date)) {
            name = list.get(i).getName();
            about = list.get(i).getAbout();
            money = "￥"+list.get(i).getMoney();
            image = R.drawable.suili;
            return new HomeDisplay(image,name, about, money, date);
        }
        return null;
    }
}
//TODO：合并getShouli和getSuili
//TODO:简化Load代码