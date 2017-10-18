package FragmentPak;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Api.MyApi;
import Bean.ShapBean;
import MyAdapter.FL_Adapter;
import bwie.com.jdmodel.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 木子 on 2017/9/29.
 */

public class Fragment_fl extends Fragment {

    private View view;

    private List<ShapBean> list = new ArrayList<>();
    private RecyclerView rv;
    private FL_Adapter mAdapter;
    private FrameLayout fl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment2, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();

    }


    private void initData() {
        OkHttpClient okhttp = new OkHttpClient();
        Request request = new Request.Builder()
                .url(MyApi.FL_URL)
                .build();
        okhttp.newCall(request).enqueue(new Callback() {
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
                            sb.cid = jo.optString("cid");
                            sb.ishome = jo.optString("ishome");
                            sb.name = jo.optString("name");
                            list.add(sb);
                        }
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setData();
                                }
                            });
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setData() {


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(mLayoutManager);
        mAdapter = new FL_Adapter(list, getActivity());
        rv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new FL_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Fragment_fl_item ffi = new Fragment_fl_item();
                ffi.setCid(list.get(position).cid);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_fl_fg, ffi).commit();
            }
        });
    }

    private void initView() {
        Fragment_fl_item ffi = new Fragment_fl_item();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_fl_fg, ffi).commit();
        rv = view.findViewById(R.id.rv);


    }

}
