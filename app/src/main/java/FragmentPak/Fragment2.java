package FragmentPak;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import MyAdapter.VP_Adapter;
import Api.MyApi;
import Bean.ShapBean;
import bwie.com.jdmodel.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 木子 on 2017/10/8.
 */

public class Fragment2 extends Fragment {


    private List<ShapBean> list = new ArrayList<>();
    private List<ShapBean> listvp1 = new ArrayList<>();

    private View view;
    private RecyclerView rv;
    private String vpicon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.vpfl_item, null);
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
                            sb.vpicon = jo.optString("icon");
                            sb.name = jo.optString("name");
                            list.add(sb);
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setData();
                            }
                        });

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setData() {

        for (int i = 11; i < list.size(); i++) {
            vpicon = list.get(i).vpicon;
            String name = list.get(i).name;

            listvp1.add(new ShapBean(name, vpicon));
        }
        

        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 5);
        rv.setLayoutManager(mLayoutManager);
        VP_Adapter mAdapter = new VP_Adapter(listvp1, getActivity());
        rv.setAdapter(mAdapter);

    }

    private void initView() {
        rv = view.findViewById(R.id.rv_vp_fl);
    }
}
