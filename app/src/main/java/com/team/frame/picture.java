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

public class picture extends AppCompatActivity {
    ArrayAdapter<String> liststr;
    private ListView lst;
    int length;
    String[]p2={"台灣起訴法律比例","法院審判案件數","法官前十審案量","十大最常被引用的法律條目","律師前十參與量","台灣前十位案件參與者","台灣檢察官前十案件參與量"};
    String[]p3={"suitpercent","judgenum","judgen","usuallaw","lawyervisit","visit","police"};
    String[]url={"http:///*<enter your ip>*//statistics/stype","http://140.136.148.163/statistics/courts","http://140.136.148.163/statistics/judge","http://140.136.148.163/statistics/law","http://140.136.148.163/statistics/lawyer","http://140.136.148.163/statistics/party","http://140.136.148.163/statistics/prosecutor"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("圖表列表");
        setSupportActionBar(toolbar);
        lst = (ListView) findViewById(R.id.lst);
        length=p2.length;
        liststr = new ArrayAdapter<String>(picture.this, android.R.layout.simple_list_item_1, p2);
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
                    intent.setClass(picture.this,Webview.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("0",url[i]);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        }

    };
}
