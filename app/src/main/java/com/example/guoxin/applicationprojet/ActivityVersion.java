package com.example.guoxin.applicationprojet;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Guoxin on 04/12/2016.
 */

public class ActivityVersion extends AppCompatActivity implements View.OnClickListener{

    ImageView btn_version_rentrer ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_version);
        MyApplication.getInstance().addActivity(this);

        btn_version_rentrer = (ImageView) findViewById(R.id.btn_version_rentrer);
        btn_version_rentrer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_version_rentrer:
                this.finish();
                break;
        }
    }

    //ToolBar Menu initialisation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //ToolBar Items initialisation
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_algorithme) {
            Intent it = new Intent(ActivityVersion.this, ActivityAlgo.class);
            startActivity(it);
            return true;
        }
        if (id == R.id.action_a_propos) {
            return true;
        }
        if (id == R.id.action_quitter) {
            MyApplication.getInstance().exitApp();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
