package FragmentPak;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Bean.CarBean;
import MyAdapter.Car_Adapter;
import Presenter.CarPresenter;
import View.ShopCarView;
import bwie.com.jdmodel.R;
import okhttp3.Call;

/**
 * Created by 木子 on 2017/9/29.
 */

public class Fragment_gw extends Fragment implements ShopCarView {

    private View view;
    private CheckBox cb;
    private RecyclerView rv;
    private TextView tv;
    private CarPresenter pre;
    private CheckBox cbb;
    private CheckBox checkBox;
    private Car_Adapter adapter;
    private List<CarBean.DataBean> data;
    private int selected;
    private List<CarBean.DataBean.ListBean> list;
    private int sellerid;
    private int num;
    private int pid;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.shopcar, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {

        pre = new CarPresenter(this);
        pre.UserLogin(188 + "");

    }

    private void initView() {
        View view2 = View.inflate(getActivity(), R.layout.car_item, null);
        cbb = view2.findViewById(R.id.radio);
        View view1 = View.inflate(getActivity(), R.layout.carlayout, null);
        cb = view1.findViewById(R.id.cb_car);
        checkBox = view.findViewById(R.id.radioButton);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    for (int i = 0; i < data.size(); i++) {
                        list = data.get(i).getList();
                        for (int i1 = 0; i1 < list.size(); i1++) {
                            selected = 1;
                            sellerid = list.get(i1).getSellerid();
                            pid = list.get(i1).getPid();
                            num = list.get(i1).getNum();
                            System.out.println(num+"+++++++++++++++++++++");

                        }
                    }


                } else if (b == false) {
                    selected = 0;
                    Toast.makeText(getActivity(), ".....", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rv = view.findViewById(R.id.rv_shopcar);
        tv = view.findViewById(R.id.car_price);
    }


    @Override
    public void onResume() {
        super.onResume();
        pre.UserLogin(188 + "");
    }

    @Override
    public void CarSeccuss(final CarBean code) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                data = code.getData();
                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                rv.setLayoutManager(llm);
                adapter = new Car_Adapter(data, getActivity());
                rv.setAdapter(adapter);

            }
        });

    }

    @Override
    public void CarFaile(String code) {

    }

    @Override
    public void CarFail(Call call, Exception e) {

    }
}
