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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class law extends AppCompatActivity {
    EditText edit;
    Button btn;
    String[] p,q;
    private ListView lst;
    ArrayAdapter<String> liststr;
    int length;
    int REQUEST_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edit=(EditText)findViewById(R.id.edit);
        lst = (ListView) findViewById(R.id.lst);
        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String target=edit.getText().toString();
                Intent intent=new Intent();
                intent.setClass(law.this,judge1.class);
                Bundle bundle=new Bundle();
                bundle.putString("19",target);
                intent.putExtras(bundle);
                startActivity(intent);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE&&resultCode==RESULT_OK) {
            Bundle bundle1=data.getExtras();
            p=bundle1.getStringArray("18");
            q=bundle1.getStringArray("98");
            length=p.length;
            liststr = new ArrayAdapter<String>(law.this, android.R.layout.simple_list_item_1, p);
            lst.setAdapter(liststr);
            lst.setOnItemClickListener(lstListener);
        }
    }
    private ListView.OnItemClickListener lstListener=new ListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int postion, long id){
            int i;
                for (i = 0; i < length; i++) {
                    if (postion == i) {
                        Intent intent2=new Intent();
                        intent2.setClass(law.this,url.class);
                        Bundle bundle2=new Bundle();
                        bundle2.putString("99",q[i]);
                        intent2.putExtras(bundle2);
                        startActivity(intent2);
                    }
                }
        }

    };
}
