package bwie.com.jdmodel;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import Bean.ProBean;
import Bean.ProdeBean;
import MyAdapter.Pro_Adapter;
import MyAdapter.Pro_Adapter1;
import MyAdapter.Prode_Adapter;
import Presenter.ProPresenter;
import Presenter.ProdePresenter;
import View.ProView;
import View.ProdeView;
import okhttp3.Call;

public class FlActivity extends AppCompatActivity implements ProView, ProdeView, View.OnClickListener {

    private RecyclerView rv;
    private ImageView iv;
    private SharedPreferences sp;
    private List<ProBean.DataBean> data;
    private Boolean bon = false;
    private String bundle;
    private ProPresenter proPresenter;
    private ProdePresenter prodePresenter;
    int i = 1;
    private List<ProdeBean.DataBean> prode;
    private Boolean bonl = false;
    private Boolean getBon = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fl);
        sp = getSharedPreferences("Pro", MODE_PRIVATE);
        bundle = getIntent().getStringExtra("pcid");
        initView();
        initData();
    }

    private void initData() {
        proPresenter = new ProPresenter(this);
        prodePresenter = new ProdePresenter(this);
        proPresenter.UserLogin(bundle, 1 + "", 0 + "");
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv_fl_rv);
        iv = (ImageView) findViewById(R.id.pro_qh);
        final EditText et = (EditText) findViewById(R.id.tv_sousuo);
        iv.setOnClickListener(this);
        findViewById(R.id.tv_zh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proPresenter.UserLogin(bundle, 1 + "", 0 + "");
            }
        });
        findViewById(R.id.tv_xl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proPresenter.UserLogin(bundle, 1 + "", 1 + "");
            }
        });
        findViewById(R.id.tv_jg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proPresenter.UserLogin(bundle, 1 + "", 2 + "");
            }
        });
        findViewById(R.id.iv_fh_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.textView7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prodePresenter.Login(et.getText().toString());
                bonl = true;

            }
        });

    }

    @Override
    public void ProSeccuss(final ProBean bean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                data = bean.getData();
                LinearLayoutManager llm = new LinearLayoutManager(FlActivity.this);
                rv.setLayoutManager(llm);
                Pro_Adapter adpter = new Pro_Adapter(data, FlActivity.this);
                rv.setAdapter(adpter);
            }
        });


    }

    @Override
    public void ProFaile(String code) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(FlActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void ProNewNo(Call call, Exception e) {

    }

    @Override
    public void onClick(View view) {
        if (bonl == true) {

            if (sp.getBoolean("bon1", getBon) == false) {
                GridLayoutManager llm = new GridLayoutManager(FlActivity.this, 2);
                rv.setLayoutManager(llm);
                Prode_Adapter adpter = new Prode_Adapter(prode, FlActivity.this);
                rv.setAdapter(adpter);
                sp.edit().putBoolean("bon1", true).commit();
            } else if (sp.getBoolean("bon1", getBon) == true) {
                LinearLayoutManager llm = new LinearLayoutManager(FlActivity.this);
                rv.setLayoutManager(llm);
                Prode_Adapter adpter = new Prode_Adapter(prode, FlActivity.this);
                rv.setAdapter(adpter);
                sp.edit().putBoolean("bon1", false).commit();


            }
            Toast.makeText(this, "456", Toast.LENGTH_SHORT).show();
        } else {
            if (sp.getBoolean("bon", bon) == false) {
                GridLayoutManager llm = new GridLayoutManager(FlActivity.this, 2);
                rv.setLayoutManager(llm);
                Pro_Adapter1 adpter = new Pro_Adapter1(data, FlActivity.this);
                rv.setAdapter(adpter);
                sp.edit().putBoolean("bon", true).commit();
            } else if (sp.getBoolean("bon", bon) == true) {
                LinearLayoutManager llm = new LinearLayoutManager(FlActivity.this);
                rv.setLayoutManager(llm);
                Pro_Adapter adpter = new Pro_Adapter(data, FlActivity.this);
                rv.setAdapter(adpter);
                sp.edit().putBoolean("bon", false).commit();


            }
        }
    }


    @Override
    public void ProdeSeccuss(final ProdeBean pro) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                prode = pro.getData();
                LinearLayoutManager llm = new LinearLayoutManager(FlActivity.this);
                rv.setLayoutManager(llm);
                Prode_Adapter adpter = new Prode_Adapter(prode, FlActivity.this);
                rv.setAdapter(adpter);
            }
        });


    }

    @Override
    public void ProdeFaile(String code) {

    }

    @Override
    public void ProdeNet(Call call, Exception e) {

    }
}
