package com.team.frame;

import java.net.URLEncoder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.util.Log;
import android.widget.Toast;

import java.net.URLEncoder;

public class search extends AppCompatActivity {
    public EditText edit,edit2;
    public Button btn,btn1,btn3;
    private ListView lst;
    private String s1;
    private String[] array=new String[1000];
    private String[] array2,p,id1;
    private StringBuilder sb=new StringBuilder();
    private StringBuilder sb2=new StringBuilder();
    private int REQUEST_CODE=1,i=-1,j=0,length,k=1,ID;
    ArrayAdapter<String> liststr;
    StringBuilder sb1=new StringBuilder();
    public static final int CHOICE_MODE_MULTIPLE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        edit=(EditText)findViewById(R.id.edit);
        edit2=(EditText)findViewById(R.id.edit1);
        btn=(Button)findViewById(R.id.btn);
        btn1=(Button)findViewById(R.id.btn1);
        btn3=(Button)findViewById(R.id.click);
        btn3.setVisibility(View.INVISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                btn3.setVisibility(View.VISIBLE);
                SharedPreferences get=getSharedPreferences("up", Context.MODE_PRIVATE);
                String s=get.getString("up1","");
                String target=edit.getText().toString();
                String target1=edit2.getText().toString();
                sb1.append(target+" "+target1).append(",");
                s+=sb1.toString();
                sb2.append(s);
                if(sb2.length()>100){
                    sb2.setLength(0);
                }
                Intent intent=new Intent();
                intent.setClass(search.this,judge.class);
                Bundle bundle=new Bundle();
                SharedPreferences share = getSharedPreferences("array", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = share.edit();
                editor.putString("fast", sb2.toString());
                editor.apply();
                bundle.putString("2",target);
                bundle.putString("3",target1);
                intent.putExtras(bundle);
                startActivity(intent);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                 Intent intent=new Intent();
                intent.setClass(search.this,searchhistory.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                k*=-1;
                fram();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE&&resultCode==RESULT_OK) {
            Bundle bundle1=data.getExtras();
            p=bundle1.getStringArray("1");
            array2=bundle1.getStringArray("4");
            i=bundle1.getInt("7");
            id1=bundle1.getStringArray("21");
            lst = (ListView) findViewById(R.id.lst);
            fram();
        }
        length=p.length;
    }
    void fram(){
        if(k==-1){
            liststr = new ArrayAdapter<String>(search.this, android.R.layout.simple_list_item_checked, p);
            lst.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            lst.setAdapter(liststr);
            lst.setOnItemClickListener(lstListener);
            SharedPreferences get=getSharedPreferences("my love1", Context.MODE_PRIVATE);
            s1=get.getString("love1","");
            sb.append(s1);
            if(sb.length()>100){
                sb.setLength(0);
            }
        }
        else{
            liststr = new ArrayAdapter<String>(search.this, android.R.layout.simple_list_item_1, p);
            lst.setAdapter(liststr);
            lst.setOnItemClickListener(lstListener);
        }
    }
    private ListView.OnItemClickListener lstListener=new ListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int postion, long id){
            int i;
            if(k==-1) {
                String s = (String) parent.getItemAtPosition(postion);
                SharedPreferences share = getSharedPreferences("my love", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = share.edit();
                sb.append(s).append(",");
                for (i = 0; i < length; i++) {
                    if (postion == i) {
                    }
                }
                editor.putString("love", sb.toString());
                editor.apply();
            }
            else{
                for (i = 0; i < length; i++) {
                    if (postion == i) {
                        Intent intent3=new Intent();
                        intent3.setClass(search.this,url.class);
                        Bundle bundle3=new Bundle();
                        bundle3.putString("n",id1[i]);
                        intent3.putExtras(bundle3);
                        startActivity(intent3);
                    }
                }
            }
        }

    };
}
