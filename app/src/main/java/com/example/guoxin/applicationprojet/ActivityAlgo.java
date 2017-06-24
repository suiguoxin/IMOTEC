package com.example.guoxin.applicationprojet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ActivityAlgo extends AppCompatActivity implements Button.OnClickListener {

    private ImageView btn_algo_rentrer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_algorithme);

        MyApplication.getInstance().addActivity(this);

        btn_algo_rentrer = (ImageView) findViewById(R.id.btn_algorithme_rentrer);
        btn_algo_rentrer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_algorithme_rentrer:
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
            return true;
        }
        if (id == R.id.action_a_propos) {
            Intent it = new Intent(ActivityAlgo.this, ActivityVersion.class);
            startActivity(it);
            return true;
        }
        if (id == R.id.action_quitter) {
            MyApplication.getInstance().exitApp();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
