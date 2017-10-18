package FragmentPak;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import Bean.FeileiBean;
import MyAdapter.FL_Xq_Adapter;
import bwie.com.jdmodel.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 木子 on 2017/10/10.
 */

public class Fragment_fl_item extends Fragment {

    private List<FeileiBean.DataBean> list;
    private View view;
    private RecyclerView rv;
    private String cid = "1";
    private FL_Xq_Adapter mAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fl_fragment, null);
        return view;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();


    }


    private void initData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://120.27.23.105/product/getProductCatagory?cid=" + cid)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                FeileiBean feileiBean = gson.fromJson(string, FeileiBean.class);
                list = feileiBean.getData();

                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setData();
                        }
                    });
                }
            }
        });

    }

    private void setData() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        mAdapter = new FL_Xq_Adapter(list, getActivity());
        rv.setAdapter(mAdapter);

    }


    private void initView() {
        View view1 = View.inflate(getActivity(), R.layout.recy_item, null);

        rv = view.findViewById(R.id.recycler_view_1);


    }
}
