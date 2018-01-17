package com.team.frame;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class love extends AppCompatActivity {
    TextView txt;
    ArrayAdapter<String> liststr;
    private ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("我的最愛");
        txt=(TextView)findViewById(R.id.txt);
        SharedPreferences get=getSharedPreferences("my love", Context.MODE_PRIVATE);
        String s;
        s=get.getString("love","");
        String[] s1=s.split(",");
        SharedPreferences share=getSharedPreferences("my love1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=share.edit();
        editor.putString("love1",s);
        editor.apply();
        int i,j,count=1,q,p,k;
        for(i=0;i<s1.length;i++){
            for(j=i+1;j<s1.length;j++){
                if(s1[i].equals(s1[j])){
                    count++;
                }
            }
            if(count%2==0){
                for(p=0;p<s1.length;p++){
                        if(s1[p].equals(s1[i])&&p!=i){
                            s1[p]="";
                        }
                }
                s1[i]="";
            }
            if(count>1&&count%2!=0){
                for(p=i+1;p<s1.length;p++){
                    if(s1[p].equals(s1[i])){
                        s1[p]="";
                    }
                }
            }
            count=1;
        }
        count=0;
        for(i=0;i<s1.length;i++){
            if(s1[i].equals("")==false){
                count++;
            }
        }
        String[] s2=new String[count];
        count=0;
        for(i=0;i<s1.length;i++){
            if(s1[i].equals("")==false){
                s2[count]=s1[i];
                count++;
            }
        }
        lst = (ListView) findViewById(R.id.lst);
        liststr = new ArrayAdapter<String>(love.this, android.R.layout.simple_list_item_1, s2);
        lst.setAdapter(liststr);
    }

}
