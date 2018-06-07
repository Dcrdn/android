package com.example.t_dicard.demo2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity{

    private static final Integer FILES = 0x1;
    private Context context;
    private triggerFolder trigger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context=this;
    }

    public void askForPermission(String permission, Integer requestCode) {

        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            //asking for permissions
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
        } else {
            //now I have permissions
            Toast.makeText(this, "" + permission + " is granted. ", Toast.LENGTH_SHORT).show();
            File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            String path = directory.getAbsolutePath();
            Log.i("Path: ", path);
            File[] listFiles= directory.listFiles();
            Log.i("Size", listFiles.length+"");

            for(int i=0;i<listFiles.length;i++){
                Log.i("Name: ", listFiles[i].getName());
            }
            this.trigger=new triggerFolder(path);
            this.trigger.startWatching();
        }
    }

    public void ask(View v){

        switch (v.getId()){
            case R.id.files:
                Log.i("gets in","gets in");

                askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, this.FILES);
        }
    }
}
