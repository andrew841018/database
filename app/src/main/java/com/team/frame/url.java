package com.team.frame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.net.MalformedURLException;
import org.json.JSONArray;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
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
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class url extends AppCompatActivity {



    TextView mytest;
    Button btn;
    public String a2="",tmp="";
    private Request request;
    private String url="";
    String[] b,arr3;
    OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("案件內容");
        btn=(Button)findViewById(R.id.btn);
        setSupportActionBar(toolbar);
        mytest=(TextView)findViewById(R.id.mytext);
        final Intent intent3=this.getIntent();
        final Bundle bundle2 = getIntent().getExtras();
        final String p = bundle2.getString("n");
        //final String p1=bundle2.getString("n1");
        final String p2=bundle2.getString("99");
       // mytest.append(" "+k+" "+p2);
        if(bundle2.containsKey("99")){
            a2=p2;
        }
        else if(bundle2.containsKey("n")){
            a2=p;
        }
       /* else if(bundle2.containsKey("n1")){
            a2=p1;
        }*/

        try {
            a2 = URLEncoder.encode(a2, "UTF-8");
            url = "http:///*<enter your ip>*//api/v1/story?id="+a2;
        } catch (UnsupportedEncodingException e) { // Catch the encoding exception
            e.printStackTrace();
        }
        //mytest.setText(p);
     //   mytest.setText("99999");
        client = new OkHttpClient();
        request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                     //   mytest.setText("false");
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 tmp = response.body().string();
                //mytest.setText(tmp);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                JSONObject obj=new JSONObject(tmp);
                               String a=obj.getString("stype");
                                final String [] b=new String[100];
                                b[0]=obj.getString("year");
                                b[1]=obj.getString("word");
                                b[2]=obj.getString("number");
                                b[4]=obj.getString("adjudged_on");
                                b[5]=obj.getString("pronounced_on");
                                b[6]=obj.getString("court");
                                b[15]=obj.getString("reason");
                                b[7]=obj.getJSONObject("courtFull").getString("name");

                                b[8]="";
                                b[9]="";
                                int i;
                                JSONArray j=obj.getJSONArray("laws");
                                for(i=0;i<j.length();i++){
                                    JSONObject j1=j.getJSONObject(i);
                                    String s="";
                                    s=j1.getString("full_name") + " " + j1.getString("num");
                                    if(i==j.length()-1)
                                        b[9] += s;
                                    else
                                        b[9]+=s+"\n";                                }
                                j=obj.getJSONArray("judges");
                                for(i=0;i<j.length();i++){
                                    String s=j.getString(i);
                                    if(i==j.length()-1)
                                        b[8] += s;
                                    else
                                        b[8]+=s+"\n";
                                }
                                j=obj.getJSONArray("parties");
                                b[11]="";
                                for(i=0;i<j.length();i++){
                                    String s=j.getString(i);
                                    if(i==j.length()-1)
                                        b[11] += s;
                                    else
                                        b[11]+=s+"\n";
                                }

                                b[10]="";
                                j=obj.getJSONArray("lawyers");

                                for(i=0;i<j.length();i++){
                                    String s=j.getString(i);
                                    if(i==j.length()-1)
                                        b[10] += s;
                                    else
                                        b[10]+=s+"\n";
                                }

                                b[12]="";
                                j=obj.getJSONArray("prosecutors");

                                for(i=0;i<j.length();i++){
                                    String s=j.getString(i);
                                    if(i==j.length()-1)
                                        b[12] += s;
                                    else
                                        b[12]+=s+"\n";
                                }
                               // b[13]=obj.getJSONObject("main_content").getString("content");
                                    mytest.append("類別:" + a + "\n");
                                    mytest.append("年:" + b[0] + "\n");
                                    mytest.append("字號:" + b[1] + "\n");
                                    mytest.append("案號:" + b[2] + "\n");
                                    mytest.append("判決日期:" + b[4] + "\n");
                                    mytest.append("宣判日期:" + b[5] + "\n");
                                    mytest.append("法院代碼:" + b[6] + "\n");
                                    mytest.append("涉及人士:" + b[11] + "\n");
                                    mytest.append("法院全名:" + b[7] + "\n");
                                    mytest.append("相關法官:" + b[8] + "\n");
                                    mytest.append("相關律師:" + b[10] + "\n");
                                    mytest.append("相關檢察官:" + b[12] + "\n");
                                    mytest.append("關聯法律:" + b[9] + "\n");
                                    mytest.append("原因:"+b[15]+"\n");
                                // mytest.append(b[13]);
                                if (obj.has("main_content") && obj.getString("main_content") != "{}") {
                                    b[13] = obj.getJSONObject("main_content").getString("content");
                                    // obj.getJSONObject("main_content").getString("url")
                                    b[13]=b[13].replace("\\u2510\\r\\n","\\u2510\\＠"); //  ┐表格換行
                                    b[13]=b[13].replace("\\u250c","\\＠\\u250c"); // ┌
                                    b[13]=b[13].replace("\\u2502\\r\\n","\\u2502\\＠"); //  │
                                    b[13]=b[13].replace("\\u2518\\r\\n","\\u2518\\＠"); //  ┘
                                    arr3=decode(b[13]).replace(" ","").replace(" ","").replace("\\","").replace("程序事項：","程序事項：＠")
                                            .replace("事實概要：","事實概要：＠").replace("原告主張：","原告主張：＠").replace("被告抗辯：","被告抗辯：＠")
                                            .replace("事實及理由","＠＠事實及理由＠").replace("爭點為：","爭點為：＠").replace("則以：","則以：＠")
                                            .replace("略以：","略以：＠").replace("略為：","略為：＠").replace("答辯：","答辯：＠").replace("爭執：","爭執：＠")
                                            .replace("之訴：","之訴：＠").replace("依據：","依據：＠").replace("主張：","主張：＠").replace("院查：","院查：＠")
                                            .replace("理由：","理由：＠").replace("事實概要\ufe30","事實概要\ufe30＠＠").replace("程序事項\ufe30","程序事項\ufe30＠＠")
                                            .replace("\u2500","").replace("\u2514","").replace("\u2518","").replace("\u2502","").replace("\u2524","")//表格
                                            .replace("\u2534","").replace("\u251c","").replace("\u253c","").replace("\u2510","").replace("\u250c","").replace("\u252c","")
                                            .replace("}","")
                                            //.replace("1.","＠1.").replace("2.","＠2.").replace("3.","＠3.").replace("4.","＠4.").replace("5.","＠5.")
                                            //.replace("6.","＠6.").replace("7.","＠7.").replace("8.","＠8.").replace("9.","＠9.").replace("10.","＠10.")
                                            .replace("判斷：","判斷：＠").replace("一\u3001","＠＠一\u3001")
                                            .replace("二\u3001","＠＠二\u3001").replace("三\u3001","＠＠三\u3001").replace("四\u3001","＠＠四\u3001")// 二､
                                            .replace("五\u3001","＠＠五\u3001").replace("六\u3001","＠＠六\u3001").replace("七\u3001","＠＠七\u3001")
                                            .replace("八\u3001","＠＠八\u3001").replace("九\u3001","＠＠九\u3001").replace("十\u3001","＠＠十\u3001")
                                            .replace("一\u3001＠＠二\u3001","一\u3001二\u3001").replace("二\u3001＠＠三\u3001","二\u3001三\u3001").replace("三\u3001＠＠四\u3001","三\u3001四\u3001")
                                            .replace("\uff08\u4e00\uff09\u3001\u3001＠＠\uff08\u4e8c\uff09","\uff08\u4e00\uff09\u3001\uff08\u4e8c\uff09")

                                            .replace("\uff08一\uff09","＠\uff08一\uff09＠").replace("\uff08二\uff09","＠＠\uff08二\uff09＠").replace("\uff08三\uff09","＠＠\uff08三\uff09＠")// (一)
                                            .replace("\uff08四\uff09","＠＠\uff08四\uff09＠").replace("\uff08五\uff09","＠＠\uff08五\uff09＠").replace("\uff08六\uff09","＠＠\uff08六\uff09＠")
                                            .replace("\uff08七\uff09","＠＠\uff08七\uff09＠").replace("\uff08八\uff09","＠＠\uff08八\uff09＠").replace("\uff08九\uff09","＠＠\uff08九\uff09＠")
                                            .replace("(一)","＠(一)＠").replace("(二)","＠＠(二)＠").replace("(三)","＠＠(三)＠").replace("(四)","＠＠(四)＠").replace("(五)","＠＠(五)＠")// (一)
                                            .replace("(六)","＠＠(六)＠").replace("(七)","＠＠(七)＠").replace("(八)","＠＠(八)＠").replace("(九)","＠＠(九)＠").replace("(十)","＠＠(十)＠")

                                            .replace("主文","＠＠主文＠＠").replace("：","：＠").replace("＠＠一\u3001二","一\u3001二").replace("＠＠二\u3001三","二\u3001三")
                                            .replace("＠＠三\u3001四","三\u3001四").replace("＠＠四\u3001五","四\u3001五").replace("＠＠五\u3001六","五\u3001六").replace("＠＠六\u3001七","六\u3001七")
                                            .replace("＠＠七\u3001八","七\u3001八").replace("＠＠九\u3001十","九\u3001十").replace("A：＠","＠A：").replace("B：＠","＠B：").replace("C：＠","＠C：")
                                            .replace("100年：＠","＠100年：").replace("101年：＠","＠101年：").replace("102年：＠","＠102年：")
                                            .replace("7：＠30-11：＠","＠7：30-11：").replace("13：＠30-17：＠","＠13：30-17：").replace("11：＠30-13：＠","＠11：30-13：")
                                            .replace("薪資：＠","薪資：").replace("2.加班費：＠","＠2.加班費：").replace("3.伙食費：＠","＠3.伙食費：").replace("4.交通費","＠4.交通費")
                                            .replace("5.職務","＠5.職務").split("＠");
                                }
                                else{
                                    btn.setVisibility(View.INVISIBLE);
                                }
                            }
                            catch (Exception e){
                               // mytest.append("545"+e.toString());
                                Log.d("4545 ",e.toString());
                            }
                            btn.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                public void onClick(View view) {
                                    for(int i=0;i<arr3.length-1;i++){
                                        arr3[i]=arr3[i].replace("r","");
                                        arr3[i]=arr3[i].replace("n","");
                                            mytest.append("  "+arr3[i]+"\n");
                                    }
                                        btn.setVisibility(View.INVISIBLE);
                                    }
                            });
                        }
                    });

               /*     tmp = tmp.replace("{", "");
                    tmp = tmp.replace("}", "");

                    tmp = tmp.replace("\"", "");
                    String s[] = tmp.split(",");
                int i;
                mytest.setText(tmp);
                for(i=1;i<s.length;i++){
                        mytest.append(s[i]+"\n");
                }*/

            }
        });

    }
    public static String decode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    }
                    catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }

}



