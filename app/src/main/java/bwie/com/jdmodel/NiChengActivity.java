package bwie.com.jdmodel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Presenter.NiCheng_Presenter;
import View.NiChengView;
import okhttp3.Call;

public class NiChengActivity extends AppCompatActivity implements NiChengView, View.OnClickListener {

    private EditText ed_nc;
    private TextView tv;
    private ImageView iv;
    private NiCheng_Presenter pre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ni_cheng);
        initView();
        initData();
    }

    private void initData() {
        pre = new NiCheng_Presenter(NiChengActivity.this);
    }

    private void initView() {
        ed_nc = (EditText) findViewById(R.id.et_nc);
        tv = (TextView) findViewById(R.id.tv_qd);
        tv.setOnClickListener(this);
        iv = (ImageView) findViewById(R.id.iv_fh_nc);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void NiChengSeccuss(String user, String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(NiChengActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                getSharedPreferences("USERNAME", MODE_PRIVATE).edit().putString("name", ed_nc.getText().toString()).commit();
            }
        });

    }

    @Override
    public void NiChengFaile(String code) {

    }

    @Override
    public void NiChengFail(Call call, Exception e) {

    }

    @Override
    public void onClick(View view) {
        pre.UserLogin(getSharedPreferences("UID", MODE_PRIVATE).getString("uid", null), ed_nc.getText().toString());
        finish();
    }
}
