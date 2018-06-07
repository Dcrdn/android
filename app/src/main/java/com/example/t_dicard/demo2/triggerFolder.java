package com.example.t_dicard.demo2;

import android.os.FileObserver;
import android.util.Log;

public class triggerFolder extends FileObserver{

    private String path= "";

    public triggerFolder(String path) {
        super(path,FileObserver.CREATE);
        this.path = path;
    }

    @Override
    public void onEvent(int event, String path) {
        if(path != null){
            Log.i("FileObserver: ","File Created");
        }
    }
}
