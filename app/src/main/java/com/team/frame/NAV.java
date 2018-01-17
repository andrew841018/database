package com.team.frame;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import java.io.IOException;

public class NAV extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public  String[] target=new String[200];
    private ListView lst;
    public String[] q=new String[200],q1=new String[200];
    public int length;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("法條名目：");
      lst=(ListView)findViewById(R.id.lst);
        length=q.length;
        try {
             q=getResources().getAssets().list("jrf_data");
            int i;
            for(i=0;i<q.length;i++){
                q1[i]=q[i];
                q[i]=q[i].replace(".json","");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> liststr=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,q);
        lst.setAdapter(liststr);
        lst.setOnItemClickListener(lstListener);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private ListView.OnItemClickListener lstListener=new ListView.OnItemClickListener(){
      @Override
        public void onItemClick(AdapterView<?> parent,View view,int postion,long id){
          int i;
          for(i=0;i<length;i++){
              if(postion==i){
                  Intent intent=new Intent();
                  intent.setClass(NAV.this,text.class);
                  Bundle bundle=new Bundle();
                  bundle.putInt("1",i);
                  bundle.putString("0",q1[i]);
                  intent.putExtras(bundle);
                  startActivity(intent);
              }
          }
       }

    };
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         int id = item.getItemId();
  if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            Intent intent=new Intent();
            intent.setClass(this,search.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {//history
            Intent intent=new Intent();
            intent.setClass(this,history.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {//my love
            Intent intent=new Intent();
            intent.setClass(this,love.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {//law
            Intent intent=new Intent();
            intent.setClass(this,law.class);
            startActivity(intent);
        } else if (id == R.id.picture) {
            Intent intent=new Intent();
            intent.setClass(this,picture.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
