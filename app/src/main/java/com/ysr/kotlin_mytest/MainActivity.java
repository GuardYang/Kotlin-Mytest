package com.ysr.kotlin_mytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements RclViewDialog.onItemClick {

    private TextView subtract;
    private List<String> list = new ArrayList<>();
    private RclViewDialog dialog;
    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        list.add("555");
        list.add("666");
        list.add("777");
        list.add("888");
        list.add("999");
        list.add("dadwa");
        type = "11";
        dialog = new RclViewDialog(MainActivity.this, list,type);
        dialog.setItemClickListener(this);
        subtract = (TextView) findViewById(R.id.subtract);
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }

    @Override
    public void onItemClick(String data,String type) {
        Log.e("eeeee", data);
        Log.e("eeeee", type);
    }
}
