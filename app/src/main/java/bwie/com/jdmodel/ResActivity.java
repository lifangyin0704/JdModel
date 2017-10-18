package bwie.com.jdmodel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ResActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv;
    private TextView tv;
    private RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        initView();

    }

    private void initView() {
        rl = (RelativeLayout) findViewById(R.id.rl);
        tv = (TextView) findViewById(R.id.tv_user);
        iv = (ImageView) findViewById(R.id.iv_fh_me);
        iv.setOnClickListener(this);
        tv.setText(getSharedPreferences("NAME", MODE_PRIVATE).getString("name", null));
        rl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_fh_me:
                finish();
                break;
            case R.id.rl:
                Intent in = new Intent(ResActivity.this, UserResActivity.class);
                startActivity(in);
                break;
        }

    }
}
