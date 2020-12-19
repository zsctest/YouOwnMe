package com.jnu.youownme.dataprocessor;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBankForSuiLi {
    private ArrayList<com.jnu.youownme.dataprocessor.SuiLi> arrayListSuili=new ArrayList<>();
    private Context context;
    private final String RECORD_FILE_NAME="suili.txt";
    public DataBankForSuiLi(Context context)
    {
        this.context=context;
    }
    public ArrayList<com.jnu.youownme.dataprocessor.SuiLi> getSuiliRecords() {
        return arrayListSuili;
    }


    public void Save()
    {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(context.openFileOutput(RECORD_FILE_NAME,Context.MODE_PRIVATE));
            oos.writeObject(arrayListSuili);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //把错误屏蔽掉，下同
    }

    public void Load()
    {
        ObjectInputStream ois = null;
        arrayListSuili=new ArrayList<>();
        try {
            ois = new ObjectInputStream(context.openFileInput(RECORD_FILE_NAME));
            arrayListSuili = (ArrayList<SuiLi>) ois.readObject();
            Log.i("date","size for suili:"+arrayListSuili.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

