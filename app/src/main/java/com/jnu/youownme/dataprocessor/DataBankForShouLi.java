package com.jnu.youownme.dataprocessor;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataBankForShouLi {
    private ArrayList<com.jnu.youownme.dataprocessor.ShouLi> arrayListShouli=new ArrayList<>();
    private Context context;
    private final String RECORD_FILE_NAME="shouli.txt";
    public DataBankForShouLi(Context context)
    {
        this.context=context;
    }
    public ArrayList<com.jnu.youownme.dataprocessor.ShouLi> getShouliRecords() {
        return arrayListShouli;
    }


    public void Save()
    {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(context.openFileOutput(RECORD_FILE_NAME,Context.MODE_PRIVATE));
            oos.writeObject(arrayListShouli);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //把错误屏蔽掉，下同
    }

    public void Load()
    {
        ObjectInputStream ois = null;
        arrayListShouli=new ArrayList<>();
        try {
            ois = new ObjectInputStream(context.openFileInput(RECORD_FILE_NAME));
            arrayListShouli = (ArrayList<ShouLi>) ois.readObject();
            Log.i("date","size for shouli:"+arrayListShouli.size()+"-"+arrayListShouli.get(0).getDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
