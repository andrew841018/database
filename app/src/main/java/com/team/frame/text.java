package com.team.frame;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.res.AssetManager;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.util.Log;

public class text extends AppCompatActivity {
    private Button btn;
    public TextView txt;
    public Toolbar toolbar;
    public String[] context;
    public String s,s1,tmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("起因");
        setSupportActionBar(toolbar);
        btn=(Button)findViewById(R.id.btn);
        txt=(TextView)findViewById(R.id.txt);
        Intent intent=this.getIntent();
        Bundle bundle=getIntent().getExtras();
        int s1=bundle.getInt("1");//Int
        String p=bundle.getString("0");//String
        s=getJson(p);
        context=s.split("content\"");
        context[0]="";
        String[] arr=new String[2000];
        String[] arr1=new String[2000];
            arr = s.split(",");
            arr[4] = arr[4].replace("\"", "");
            arr[4] = arr[4].replace(" ", "");
            arr[0] = arr[4];
            arr[0] = arr[0].replace(":", ":+");
            arr[4] = arr[4].replace("reason:", "");//chinese
            //arr1 = arr[0].split("\\+");
        arr[4]=arr[4].replace("parties_name:[","");
            txt.setText("reason:"+decode(arr[4]));
        context[2]=context[2].replace("\"","");//『"』表達法
        context[2]=context[2].replace(":","");
        context[1]=context[1].replace("\"main_","");
        context[1]=context[1].replace(":","");
   btn.setOnClickListener(new View.OnClickListener() {
            // arrat  to  string     string  to  char   char  to   string    string  to  array
            String[] arr3=decode(context[2]).replace(" ","").replace(" ","").replace("\\","").replace("程序事項：","程序事項：＠")
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
      @Override
            public void onClick(View view) {
                txt.setText("");
                for(int i=0;i<arr3.length;i++){
                    arr3[i]=arr3[i].replace("r","");
                    arr3[i]=arr3[i].replace("n","");
                    txt.append("  "+arr3[i]+"\n");
                }

                btn.setVisibility(View.INVISIBLE);
            }
        });
    }
    public String getJson(String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
           AssetManager assetManager = text.this.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
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

