package FragmentPak;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import Bean.XBean;
import Presenter.XQPresenter;
import View.XQView;
import bwie.com.jdmodel.R;
import okhttp3.Call;

/**
 * Created by 木子 on 2017/10/17.
 */

public class Fragment_1 extends Fragment implements XQView {

    private View view;
    private XBanner xb;
    List<String> list = new ArrayList<>();
    private TextView tv;
    private TextView tv1;
    private TextView tv_price;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_1, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();

    }

    private void initData() {
        XQPresenter pre = new XQPresenter(this);

        pre.UserLogin(getActivity().getSharedPreferences("PID", getActivity().MODE_PRIVATE).getString("pid", null));
    }

    private void initView() {
        xb = view.findViewById(R.id.vp_gg_xq);
        tv1 = view.findViewById(R.id.tv_xq_title);
        tv_price = view.findViewById(R.id.tv_xq_price);


    }

    @Override
    public void XqSeccuss(final XBean bean) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                XBean.DataBean data = bean.getData();
                getActivity().getSharedPreferences("SHOPPID", getActivity().MODE_PRIVATE).edit().putString("pid", data.getPid()+"").commit();
                getActivity().getSharedPreferences("URL", getActivity().MODE_PRIVATE).edit().putString("url", data.getDetailUrl()).commit();
                tv1.setText(data.getTitle());
                tv_price.setText(" ¥  " + data.getPrice());
                String images = data.getImages();
                String[] split = images.split("\\|");
                for (String s : split) {
                    list.add(s);
                }

                xb.setData(list, null);
                xb.setmAdapter(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, View view, int position) {
                        Glide.with(getActivity()).load(list.get(position)).into((ImageView) view);
                    }
                });
            }

        });

    }

    @Override
    public void XqFaile(String code) {

    }

    @Override
    public void XqNewNo(Call call, Exception e) {

    }
}
