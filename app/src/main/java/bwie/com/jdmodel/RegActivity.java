package bwie.com.jdmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import Api.MyApi;
import Presenter.UserPresenter;
import View.UserView;
import okhttp3.Call;

public class RegActivity extends AppCompatActivity implements UserView, View.OnClickListener {

    private Button but;

    private UserPresenter pre;
    private EditText et_tel;
    private EditText et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
        initData();
    }

    private void initData() {
        pre = new UserPresenter(RegActivity.this);
    }

    private void initView() {
        ImageView iv = (ImageView) findViewById(R.id.iv_fh);
        but = (Button) findViewById(R.id.but_zc);
        et_tel = (EditText) findViewById(R.id.et_pass_zc);
        et_pass = (EditText) findViewById(R.id.et_pass_zc);
        but.setOnClickListener(this);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void LoginSeccuss(String code, String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegActivity.this, "注册成功", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void LoginFail(String code, String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegActivity.this, "注册失败", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void NetWorkFail(Call call, Exception e) {

    }

    @Override
    public void UserName(String tel) {

    }

    @Override
    public void UserPass(String pass) {

    }

    @Override
    public void onClick(View view) {
        pre.UserLogin(MyApi.REG_URL,et_tel.getText().toString(),et_pass.getText().toString());
    }
}
