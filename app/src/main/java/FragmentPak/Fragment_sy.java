package FragmentPak;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.stx.xhb.xbanner.XBanner;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Api.MyApi;
import Bean.MiSaBean;
import Bean.ShapBean;
import Bean.TuiJianBean;
import MyAdapter.MS_Adapter;
import MyAdapter.RecylerAdapter;
import MyAdapter.TJ_Adapter;
import Presenter.TuiJianPresenter;
import View.TuiJianView;
import bwie.com.jdmodel.R;
import bwie.com.jdmodel.ScanActivity;
import bwie.com.jdmodel.Web_vpActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 木子 on 2017/9/29.
 */

public class Fragment_sy extends Fragment implements TuiJianView, View.OnClickListener {


    private View view;
    private SuperSwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private int mDistanceY;
    private Toolbar head_search_rr;
    private XBanner vp_gg;
    private View xbanner;
    private List<ShapBean> list;
    private ViewPager vp;
    private RecyclerView rv;
    private List<MiSaBean> listms;

    private RecyclerView rv_tj;

    private String s1;
    private ImageView iv_erweima;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_sy, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initMsData();
        initTjData();
    }

    private void initTjData() {
        TuiJianPresenter pre = new TuiJianPresenter(this);
        pre.Login();
    }

    private void initMsData() {
        listms = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(MyApi.BANNER_URL)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                try {
                    JSONObject json = new JSONObject(string);
                    JSONObject miaosha = json.getJSONObject("miaosha");
                    JSONArray data = miaosha.getJSONArray("list");
                    if (data != null && data.length() > 0) {
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject jo = (JSONObject) data.get(i);
                            MiSaBean sb = new MiSaBean();
                            sb.detailUrl = jo.optString("detailUrl");
                            sb.newPrice = jo.optString("price");
                            sb.oldPrice = jo.optString("bargainPrice");
                            sb.picUrl = jo.optString("images");
                            listms.add(sb);
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setDadaMs();
                            }
                        });

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    private void setDadaMs() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        MS_Adapter MsAdapter = new MS_Adapter(listms, getActivity());
        rv.setAdapter(MsAdapter);

    }

    private void initData() {
        list = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(MyApi.BANNER_URL)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                try {
                    JSONObject json = new JSONObject(string);
                    JSONArray data = json.getJSONArray("data");
                    if (data != null && data.length() > 0) {
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject jo = (JSONObject) data.get(i);
                            ShapBean sb = new ShapBean();
                            sb.icon = jo.optString("icon");
                            sb.url = jo.optString("url");
                            sb.type = jo.optString("type");
                            list.add(sb);
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setDada();
                            }
                        });

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setDada() {
        vp.setAdapter(new MyAdapterFL(getActivity().getSupportFragmentManager()));
        vp_gg.setData(list, null);
        vp_gg.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getActivity()).load(list.get(position).icon).into((ImageView) view);
            }
        });
        vp_gg.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                Intent in = new Intent(getActivity(), Web_vpActivity.class);
                in.putExtra("url", list.get(position).url);
                startActivity(in);
            }
        });
        List<String> listRV = new ArrayList<>();
        listRV.add("");
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        RecylerAdapter mAdapter = new RecylerAdapter(getActivity(), R.layout.item_list, listRV);
        HeaderAndFooterWrapper mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);
        mHeaderAndFooterWrapper.addHeaderView(xbanner);
        recyclerView.setAdapter(mHeaderAndFooterWrapper);
        mHeaderAndFooterWrapper.notifyDataSetChanged();
    }

    @Override
    public void TJSeccuss(final TuiJianBean bean) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<TuiJianBean.TuijianBean.ListBean> list = bean.getTuijian().getList();
                GridLayoutManager llm = new GridLayoutManager(getActivity(), 2);
                rv_tj.setLayoutManager(llm);
                TJ_Adapter MsAdapter = new TJ_Adapter(list, getActivity());
                rv_tj.setAdapter(MsAdapter);
            }
        });

    }

    @Override
    public void TJFaile(String code) {

    }

    @Override
    public void TJNewNo(Call call, Exception e) {

    }

    @Override
    public void onClick(View view) {
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
// 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setCaptureActivity(ScanActivity.class);
        integrator.setPrompt("请扫描"); //底部的提示文字，设为""可以置空
        integrator.setCameraId(0); //前置或者后置摄像头
        integrator.setBeepEnabled(false); //扫描成功的「哔哔」声，默认开启
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    class MyAdapterFL extends FragmentPagerAdapter {

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //  super.destroyItem(container, position, object);
        }

        public MyAdapterFL(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fg = null;
            switch (position) {
                case 0:
                    fg = new Fragment1();
                    break;
                case 1:
                    fg = new Fragment2();
                    break;
            }
            return fg;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    private void initView() {
        View vv = View.inflate(getActivity(), R.layout.head_search, null);
        xbanner = View.inflate(getActivity(), R.layout.xbanner, null);
        rv_tj = xbanner.findViewById(R.id.recycler_view_tj);
        rv = xbanner.findViewById(R.id.recycler_view_ms);
        vp = xbanner.findViewById(R.id.vp_fl);
        vp_gg = xbanner.findViewById(R.id.vp_gg);
        iv_erweima = vv.findViewById(R.id.iv_erweima);
        iv_erweima.setOnClickListener(this);
        head_search_rr = view.findViewById(R.id.head_search_rr);
        refreshLayout = view.findViewById(R.id.swipe_container);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //滑动的距离
                mDistanceY += dy;
                //toolbar的高度
                int toolbarHeight = head_search_rr.getBottom();

                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistanceY <= toolbarHeight) {
                    float scale = (float) mDistanceY / toolbarHeight;
                    float alpha = scale * 255;
                    head_search_rr.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                } else {
                    //将标题栏的颜色设置为完全不透明状态
                    head_search_rr.setBackgroundResource(R.color.white);
                }
            }
        });

        refreshLayout.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {

            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 500);
            }

            @Override
            public void onPullDistance(int distance) {
                if (distance > 0) {
                    head_search_rr.setVisibility(View.GONE);
                } else {
                    head_search_rr.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPullEnable(boolean enable) {
            }
        });
    }
}
