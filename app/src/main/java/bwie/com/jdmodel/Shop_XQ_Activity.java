package bwie.com.jdmodel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidkun.xtablayout.XTabLayout;

import java.util.ArrayList;
import java.util.List;

import View.GouWuView;
import FragmentPak.Fragment_1;
import FragmentPak.Fragment_2;
import FragmentPak.Fragment_3;
import Presenter.Shop_Presenter;
import okhttp3.Call;

public class Shop_XQ_Activity extends AppCompatActivity implements View.OnClickListener, GouWuView {

    private XTabLayout tablayout;
    private ImageView xq_back;
    private ViewPager vp;
    private List<String> list;
    private Button but;
    private Shop_Presenter shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_xq);
        initView();
        initData();
    }

    private void initData() {
        shop = new Shop_Presenter(this);
        vp.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tablayout.setupWithViewPager(vp);
    }

    private void initView() {
        but = (Button) findViewById(R.id.joinshapcar);
        but.setOnClickListener(this);
        tablayout = (XTabLayout) findViewById(R.id.tablayout);
        xq_back = (ImageView) findViewById(R.id.xq_back);
        vp = (ViewPager) findViewById(R.id.xq_vp);
        xq_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tablayout.addTab(tablayout.newTab().setText("商品"));
        tablayout.addTab(tablayout.newTab().setText("详情"));
        tablayout.addTab(tablayout.newTab().setText("评价"));
        list = new ArrayList<>();
        list.add("商品");
        list.add("详情");
        list.add("评价");
    }

    @Override
    public void onClick(View view) {
        shop.UserLogin(188+"",getSharedPreferences("SHOPPID",MODE_PRIVATE).getString("pid",null));
    }


    @Override
    public void GwSeccuss(String code) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Shop_XQ_Activity.this, "添加成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void GwFaile(String code) {

    }

    @Override
    public void GwFail(Call call, Exception e) {

    }

    class MyAdapter extends FragmentPagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {

            return list.get(position % list.size());
        }

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fg = null;
            switch (position) {
                case 0:
                    fg = new Fragment_1();
                    break;
                case 1:
                    fg = new Fragment_2();
                    break;
                case 2:
                    fg = new Fragment_3();
                    break;
            }
            return fg;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
