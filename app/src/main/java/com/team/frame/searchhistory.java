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

public class searchhistory extends AppCompatActivity {
    private ListView lst;
    public int length;
    String[] p2;
    ArrayAdapter<String> liststr;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchhistory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt=(TextView)findViewById(R.id.txt);
        lst = (ListView) findViewById(R.id.search_lv_tips);
        toolbar.setTitle("搜尋歷史紀錄");
        setSupportActionBar(toolbar);
        SharedPreferences get=getSharedPreferences("array", Context.MODE_PRIVATE);
        String s;
        s=get.getString("fast","");
        String[] p2=s.split(",");
        length=p2.length;
        SharedPreferences share1=getSharedPreferences("up", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=share1.edit();
        editor.putString("up1",s);//history
        editor.apply();

        liststr = new ArrayAdapter<String>(searchhistory.this, android.R.layout.simple_list_item_1,p2);
        lst.setAdapter(liststr);
        lst.setOnItemClickListener(lstListener);
    }
    private ListView.OnItemClickListener lstListener=new ListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int postion, long id){
            int i;
            for(i=0;i<length;i++){
                if(postion==i){
                    Intent intent=new Intent();
                    intent.setClass(searchhistory.this,search.class);

                    startActivity(intent);
                }
            }
        }

    };
}
