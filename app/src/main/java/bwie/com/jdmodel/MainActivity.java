package bwie.com.jdmodel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import FragmentPak.Fragment_fl;
import FragmentPak.Fragment_fx;
import FragmentPak.Fragment_gw;
import FragmentPak.Fragment_sy;
import FragmentPak.Fragment_wd;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView shouye;
    private ImageView wode;
    private ImageView fenlei;
    private ImageView faxian;
    private ImageView gouwuche;
    private Fragment_fl f2;
    private Fragment_fx f3;
    private Fragment_wd f5;
    private Fragment_sy f1;
    private Fragment_gw f4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFragment();
    }

    private void initFragment() {
        f2 = new Fragment_fl();
        f3 = new Fragment_fx();
        f5 = new Fragment_wd();
        f1 = new Fragment_sy();
        f4 = new Fragment_gw();
        getSupportFragmentManager().beginTransaction().add(R.id.fl, f1).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl, f2).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl, f3).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl, f4).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl, f5).commit();
        getSupportFragmentManager().beginTransaction().show(f1).commit();
        getSupportFragmentManager().beginTransaction().hide(f2).commit();
        getSupportFragmentManager().beginTransaction().hide(f3).commit();
        getSupportFragmentManager().beginTransaction().hide(f4).commit();
        getSupportFragmentManager().beginTransaction().hide(f5).commit();
        shouye.setImageResource(R.mipmap.shouyehong);
    }

    private void initView() {

        shouye = (ImageView) findViewById(R.id.iv_sy);
        wode = (ImageView) findViewById(R.id.iv_wd);
        fenlei = (ImageView) findViewById(R.id.iv_fl);
        faxian = (ImageView) findViewById(R.id.iv_fx);
        gouwuche = (ImageView) findViewById(R.id.iv_gw);
        shouye.setOnClickListener(this);
        wode.setOnClickListener(this);
        fenlei.setOnClickListener(this);
        faxian.setOnClickListener(this);
        gouwuche.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_sy:
                fenlei.setImageResource(R.mipmap.fenleibai);
                faxian.setImageResource(R.mipmap.faxian);
                gouwuche.setImageResource(R.mipmap.gouwuchebai);
                wode.setImageResource(R.mipmap.wodebai);
                shouye.setImageResource(R.mipmap.shouyehong);
                getSupportFragmentManager().beginTransaction().show(f1).commit();
                getSupportFragmentManager().beginTransaction().hide(f2).commit();
                getSupportFragmentManager().beginTransaction().hide(f3).commit();
                getSupportFragmentManager().beginTransaction().hide(f4).commit();
                getSupportFragmentManager().beginTransaction().hide(f5).commit();
                break;
            case R.id.iv_fl:
                fenlei.setImageResource(R.mipmap.fenleihong);
                faxian.setImageResource(R.mipmap.faxian);
                gouwuche.setImageResource(R.mipmap.gouwuchebai);
                wode.setImageResource(R.mipmap.wodebai);
                shouye.setImageResource(R.mipmap.shouyebai);
                getSupportFragmentManager().beginTransaction().hide(f1).commit();
                getSupportFragmentManager().beginTransaction().show(f2).commit();
                getSupportFragmentManager().beginTransaction().hide(f3).commit();
                getSupportFragmentManager().beginTransaction().hide(f4).commit();
                getSupportFragmentManager().beginTransaction().hide(f5).commit();
                break;
            case R.id.iv_fx:

                fenlei.setImageResource(R.mipmap.fenleibai);
                faxian.setImageResource(R.mipmap.abz);
                gouwuche.setImageResource(R.mipmap.gouwuchebai);
                wode.setImageResource(R.mipmap.wodebai);
                shouye.setImageResource(R.mipmap.shouyebai);
                getSupportFragmentManager().beginTransaction().hide(f1).commit();
                getSupportFragmentManager().beginTransaction().hide(f2).commit();
                getSupportFragmentManager().beginTransaction().show(f3).commit();
                getSupportFragmentManager().beginTransaction().hide(f4).commit();
                getSupportFragmentManager().beginTransaction().hide(f5).commit();
                break;
            case R.id.iv_gw:
                fenlei.setImageResource(R.mipmap.fenleibai);
                faxian.setImageResource(R.mipmap.faxian);
                gouwuche.setImageResource(R.mipmap.gouwuchehong);
                wode.setImageResource(R.mipmap.wodebai);
                shouye.setImageResource(R.mipmap.shouyebai);
                getSupportFragmentManager().beginTransaction().hide(f1).commit();
                getSupportFragmentManager().beginTransaction().hide(f2).commit();
                getSupportFragmentManager().beginTransaction().hide(f3).commit();
                getSupportFragmentManager().beginTransaction().show(f4).commit();
                getSupportFragmentManager().beginTransaction().hide(f5).commit();
                break;
            case R.id.iv_wd:
                fenlei.setImageResource(R.mipmap.fenleibai);
                faxian.setImageResource(R.mipmap.faxian);
                gouwuche.setImageResource(R.mipmap.gouwuchebai);
                wode.setImageResource(R.mipmap.wodehong);
                shouye.setImageResource(R.mipmap.shouyebai);
                getSupportFragmentManager().beginTransaction().hide(f1).commit();
                getSupportFragmentManager().beginTransaction().hide(f2).commit();
                getSupportFragmentManager().beginTransaction().hide(f3).commit();
                getSupportFragmentManager().beginTransaction().hide(f4).commit();
                getSupportFragmentManager().beginTransaction().show(f5).commit();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "扫码取消！", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "扫描成功，条码值: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
