package bwie.com.jdmodel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Api.MyApi;
import FragmentPak.Fragment_wd;
import Presenter.UserPresenter;
import View.UserView;
import okhttp3.Call;

public class UserActivity extends AppCompatActivity implements View.OnClickListener, UserView {

    private ImageView iv;

    private Button but;
    private EditText et_tel;
    private EditText et_pass;
    private UserPresenter pre;
    private Fragment_wd fragment2;
    private SharedPreferences sp;
    private ImageView iv1;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        sp = getSharedPreferences("NAME", MODE_PRIVATE);
        initView();
        initData();

    }

    private void initData() {
        pre = new UserPresenter(UserActivity.this);
    }


    private void initView() {
        tv = (TextView) findViewById(R.id.tv_zhuce);
        iv = (ImageView) findViewById(R.id.iv_ch);
        et_tel = (EditText) findViewById(R.id.et_tel);
        et_pass = (EditText) findViewById(R.id.et_pass);
        but = (Button) findViewById(R.id.but_dl);
        but.setOnClickListener(this);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(UserActivity.this, RegActivity.class);
                startActivity(in);
            }
        });


    }

    @Override
    public void onClick(View view) {
        pre.UserLogin(MyApi.LOGIN_URL, et_tel.getText().toString(), et_pass.getText().toString());

    }

    @Override
    public void LoginSeccuss(String code, String msg) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(UserActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

            }
        });
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("name", et_tel.getText().toString());
        edit.commit();

        finish();

    }

    @Override
    public void LoginFail(String code, String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(UserActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
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
}
