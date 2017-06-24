package com.example.guoxin.applicationprojet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Guoxin on 02/11/2016.
 */

public class ActivityMesure extends AppCompatActivity implements TextWatcher, Button.OnClickListener//implements View.OnClickListener
{

    private ImageView btn_back;

    private TextView tv_vider;

    private TextView tv_tests_normaux;
    private TextView tv_tests_normaux_detail;
    private TextView tv_nb_proposition;

    private CheckBox cb_protamine;
    private CheckBox cb_tp;
    private CheckBox cb_fibrinogene;
    private CheckBox cb_plasma;
    private CheckBox cb_ccp;
    private CheckBox cb_acide;

    private EditText et_ct_hep;
    private EditText et_ct_int;
    private EditText et_ct_ext;
    private EditText et_ct_fib;
    private EditText et_a10_hep;
    private EditText et_a10_ext;
    private EditText et_a10_fib;
    private EditText et_ml_ext;
    private EditText et_ml_fib;

    private int ct_hep;
    private int ct_in;
    private int ct_ext;
    private int a10_hep;
    private int a10_ext;
    private int a10_fib;
    private int nb_proposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesure);

        MyApplication.getInstance().addActivity(this);

        //initialisation views
        btn_back = (ImageView) findViewById(R.id.btn_back);

        tv_vider = (TextView) findViewById(R.id.tv_vider);
        tv_tests_normaux = (TextView) findViewById(R.id.tv_tests_normaux);
        tv_tests_normaux_detail = (TextView) findViewById(R.id.tv_tests_normaux_detail);
        tv_nb_proposition = (TextView) findViewById(R.id.tv_nb_proposition);

        cb_protamine = (CheckBox) findViewById(R.id.tv_protamine);
        cb_tp = (CheckBox) findViewById(R.id.tv_tp);
        cb_fibrinogene = (CheckBox) findViewById(R.id.tv_fibrinogene);
        cb_plasma = (CheckBox) findViewById(R.id.tv_plasma);
        cb_ccp = (CheckBox) findViewById(R.id.tv_ccp);
        cb_acide = (CheckBox) findViewById(R.id.tv_acide);

        tv_tests_normaux.setVisibility(View.VISIBLE);
        tv_tests_normaux_detail.setVisibility(View.VISIBLE);
        tv_nb_proposition.setVisibility(View.VISIBLE);

        cb_protamine.setVisibility(View.GONE);
        cb_tp.setVisibility(View.GONE);
        cb_fibrinogene.setVisibility(View.GONE);
        cb_plasma.setVisibility(View.GONE);
        cb_ccp.setVisibility(View.GONE);
        cb_acide.setVisibility(View.GONE);

        et_ct_hep = (EditText) findViewById(R.id.et_ct_hep);
        et_ct_int = (EditText) findViewById(R.id.et_ct_int);
        et_ct_ext = (EditText) findViewById(R.id.et_ct_ext);
        et_ct_fib = (EditText) findViewById(R.id.et_ct_fib);
        et_a10_hep = (EditText) findViewById(R.id.et_a10_hep);
        et_a10_ext = (EditText) findViewById(R.id.et_a10_ext);
        et_a10_fib = (EditText) findViewById(R.id.et_a10_fib);
        et_ml_ext = (EditText) findViewById(R.id.et_ml_ext);
        et_ml_fib = (EditText) findViewById(R.id.et_ml_fib);

        //initialisation events
        btn_back.setOnClickListener(this);
        tv_vider.setOnClickListener(this);

        et_ct_hep.addTextChangedListener(this);
        et_ct_int.addTextChangedListener(this);
        et_ct_ext.addTextChangedListener(this);
        et_ct_fib.addTextChangedListener(this);

        et_a10_hep.addTextChangedListener(this);
        et_a10_fib.addTextChangedListener(this);
        et_a10_ext.addTextChangedListener(this);

        et_ml_ext.addTextChangedListener(this);
        et_ml_fib.addTextChangedListener(this);

        nb_proposition = 0;
        tv_nb_proposition.setText(Integer.toString(nb_proposition));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                Intent it = new Intent(ActivityMesure.this, ActivityRappel.class);
                startActivity(it);
                break;
            case R.id.tv_vider:
                et_ct_ext.setText("");
                et_a10_ext.setText("");
                et_ml_ext.setText("");
                et_ct_int.setText("");
                et_ct_fib.setText("");
                et_a10_fib.setText("");
                et_ml_fib.setText("");
                et_ct_hep.setText("");
                et_a10_hep.setText("");
                break;
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        nb_proposition = 0;
        //Protamine
        if ((et_ct_hep.getText().length() > 0) && (et_ct_int.getText().length() > 0)) {
            ct_hep = Integer.valueOf(et_ct_hep.getText().toString());
            ct_in = Integer.valueOf(et_ct_int.getText().toString());
            if (ct_hep <= ct_in * 0.8) {
                cb_protamine.setVisibility(View.VISIBLE);
                nb_proposition++;
            } else {
                cb_protamine.setChecked(false);
                cb_protamine.setVisibility(View.GONE);
            }
        } else {
            cb_protamine.setChecked(false);
            cb_protamine.setVisibility(View.GONE);
        }

        //Transfusion plaquettaire et Fibrinogène
        if ((et_a10_hep.getText().length() > 0) && (et_a10_ext.getText().length() > 0) && (et_a10_fib.getText().length() > 0)) {
            a10_hep = Integer.valueOf(et_a10_hep.getText().toString());
            a10_ext = Integer.valueOf(et_a10_ext.getText().toString());
            a10_fib = Integer.valueOf(et_a10_fib.getText().toString());
            if (a10_ext <= 40 && a10_hep <= 40) {
                if (a10_fib > 10) {
                    cb_tp.setVisibility(View.VISIBLE);
                    cb_fibrinogene.setChecked(false);
                    cb_fibrinogene.setVisibility(View.GONE);
                } else {
                    cb_fibrinogene.setVisibility(View.VISIBLE);
                    cb_tp.setChecked(false);
                    cb_tp.setVisibility(View.GONE);
                }
                nb_proposition++;
            } else {
                cb_tp.setChecked(false);
                cb_fibrinogene.setChecked(false);
                cb_fibrinogene.setVisibility(View.GONE);
                cb_tp.setVisibility(View.GONE);
            }
        } else {
            cb_tp.setChecked(false);
            cb_fibrinogene.setChecked(false);
            cb_fibrinogene.setVisibility(View.GONE);
            cb_tp.setVisibility(View.GONE);
        }

        //Plasma et CCP
        if ((et_a10_fib.getText().length() > 0) && (et_ct_hep.getText().length() > 0) && (et_ct_ext.getText().length() > 0)) {
            a10_fib = Integer.valueOf(et_a10_fib.getText().toString());
            ct_hep = Integer.valueOf(et_ct_hep.getText().toString());
            ct_ext = Integer.valueOf(et_ct_ext.getText().toString());
            if (a10_fib >= 10 && ct_hep >= 240) {
                if (ct_ext < 80) {
                    cb_plasma.setVisibility(View.VISIBLE);
                    cb_ccp.setChecked(false);
                    cb_ccp.setVisibility(View.GONE);
                } else {
                    cb_ccp.setVisibility(View.VISIBLE);
                    cb_plasma.setChecked(false);
                    cb_plasma.setVisibility(View.GONE);
                }
                nb_proposition++;
            } else {
                cb_plasma.setChecked(false);
                cb_ccp.setChecked(false);
                cb_plasma.setVisibility(View.GONE);
                cb_ccp.setVisibility(View.GONE);
            }
        } else {
            cb_plasma.setChecked(false);
            cb_ccp.setChecked(false);
            cb_plasma.setVisibility(View.GONE);
            cb_ccp.setVisibility(View.GONE);
        }

        //Acide Tranexamique
        if (((et_a10_ext.getText().length() > 0) && Integer.valueOf(et_a10_ext.getText().toString()) <= 35) ||
                ((et_ct_fib.getText().length() > 0) && Integer.valueOf(et_ct_fib.getText().toString()) >= 600) ||
                ((et_ml_ext.getText().length() > 0) && Integer.valueOf(et_ml_ext.getText().toString()) >= 15) ||
                ((et_ml_fib.getText().length() > 0) && Integer.valueOf(et_ml_fib.getText().toString()) >= 10)) {
            cb_acide.setVisibility(View.VISIBLE);
            nb_proposition++;
        } else {
            cb_acide.setChecked(false);
            cb_acide.setVisibility(View.GONE);
        }

        //Tests normaux
        if (nb_proposition == 0){
            tv_tests_normaux.setVisibility(View.VISIBLE);
            tv_tests_normaux_detail.setVisibility(View.VISIBLE);
        }


        else {
            tv_tests_normaux.setVisibility(View.GONE);
            tv_tests_normaux_detail.setVisibility(View.GONE);
        }

        tv_nb_proposition.setText(Integer.toString(nb_proposition));
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
            Intent it = new Intent(ActivityMesure.this, ActivityAlgo.class);
            startActivity(it);
            return true;
        }
        if (id == R.id.action_a_propos) {
            Intent it = new Intent(ActivityMesure.this, ActivityVersion.class);
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

