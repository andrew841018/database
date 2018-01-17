package com.team.frame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.app.Activity;
import android.widget.RelativeLayout;
import com.google.gson.Gson;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.HttpUrl;
import java.io.IOException;
import okhttp3.Request;
import okhttp3.Response;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class judge1 extends AppCompatActivity {
    TextView mytest;
    public String a, b;
    public int lenght;
    private OkHttpClient client;
    public String[] id, reason, year, type;
    private Request request;
    ArrayAdapter<String> liststr;
    Button btn;
    private String url = "http:///*<enter your ip>*//api/v1/searchl?query[]="+a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mytest=(TextView)findViewById(R.id.mytext);
        final Intent intent2=this.getIntent();
        final Bundle bundle = getIntent().getExtras();
        final String p = bundle.getString("19");
        a=p;
        try {
            a = URLEncoder.encode(a, "UTF-8");
            url = "http:///*<enter your ip>*//api/v1/searchl?query[]="+a;
        } catch (UnsupportedEncodingException e) { // Catch the encoding exception
            e.printStackTrace();
        }
        client = new OkHttpClient();
        request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mytest.setText("false");
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String tmp = response.body().string();
                if(tmp.equals("[]")==false) {
                    //mytest.setText(tmp);
                    tmp = tmp.replace("[", "");
                    tmp = tmp.replace("{", "");
                    tmp = tmp.replace("}", "");
                    tmp = tmp.replace("]", "");
                    tmp = tmp.replace("stype", "");
                    tmp = tmp.replace("year", "");
                    tmp = tmp.replace("reason", "");
                    tmp=tmp.replace("number","");
                    tmp=tmp.replace("word","");
                    tmp=tmp.replace("id","");
                    tmp = tmp.replace("\"", "");
                    tmp = tmp.replace(":", "");
                    String s[] = tmp.split(",");
                    String[] number, reason,court, year,id,word,output,type = new String[s.length];
                    String[] arr=new String[1000];
                    int i, count = 0,w,count1=0;
                    id=new String[s.length/9];
                    output = new String[s.length/9];
                    court = new String[s.length/9];
                    word = new String[s.length/9];
                    year = new String[s.length/9];
                    number = new String[s.length/9];
                    reason = new String[s.length/9];
                    for(i=0;i<s.length;i+=9){
                        id[count1]=s[i];
                        count1++;
                    }
                    for (i = 0; i < s.length; i += 9) {
                        if (i + 1 < s.length)
                            type[count] = s[i + 1];
                        if (i + 2 < s.length)
                            year[count] = s[i + 2];
                        if (i + 5 < s.length)
                            reason[count] = s[i + 5];
                        if(i+3<s.length)
                            word[count]=s[i+3];
                        if(i+8<s.length)
                            court[count]=s[i+8];
                        if(i+4<s.length)
                            number[count] = s[i+4];
                        count++;
                    }
                    for (i = 0; i < number.length; i++) {
                        output[i] =type[i] + " "+year[i]+" "+word[i]+" "+number[i];
                    }
                    Bundle bundle=new Bundle();
                    bundle.putStringArray("18",output);
                    bundle.putStringArray("98",id);
                    intent2.putExtras(bundle);
                }
                else{
                    String[] output=new String[1];
                    output[0]="沒有『"+p+"』的相關資訊！";
                    Bundle bundle=new Bundle();
                    bundle.putStringArray("18",output);
                    intent2.putExtras(bundle);
                }
                setResult(RESULT_OK,intent2);
                finish();
            }
        });
    }

}
