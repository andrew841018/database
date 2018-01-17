package com.team.frame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class history extends AppCompatActivity {
    TextView txt;
    ArrayAdapter<String> liststr;
    private ListView lst;
    int length;
    String[] id2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txt=(TextView)findViewById(R.id.txt);
        SharedPreferences get=getSharedPreferences("my love", Context.MODE_PRIVATE);
        String s;
        s=get.getString("love","");
        String[] s1=s.split(",");

        length=s1.length;
        SharedPreferences share=getSharedPreferences("my love1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=share.edit();
        editor.putString("love1",s);
        editor.apply();
       // Bundle bundle4=new Bundle();
      //  id2=bundle4.getStringArray("21");
        lst = (ListView) findViewById(R.id.lst);
        liststr = new ArrayAdapter<String>(history.this, android.R.layout.simple_list_item_1, s1);
        lst.setAdapter(liststr);

    }
}
