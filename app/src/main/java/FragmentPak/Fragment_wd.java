package FragmentPak;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import bwie.com.jdmodel.R;
import bwie.com.jdmodel.ResActivity;
import bwie.com.jdmodel.UserActivity;

/**
 * Created by 木子 on 2017/9/29.
 */

public class Fragment_wd extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView iv;

    private TextView tv;
    private SharedPreferences sp;
    private ImageView iv_me;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.my_layout, null);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sp = getActivity().getSharedPreferences("NAME", getActivity().MODE_PRIVATE);
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        String name = sp.getString("name", null);
        if (name != null) {
            tv.setText(name);
        }

    }

    private void initView() {
        iv = view.findViewById(R.id.img_denglu);
        tv = view.findViewById(R.id.tv_user_1);
        iv_me = view.findViewById(R.id.iv_me);
        iv.setOnClickListener(this);
        iv_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), ResActivity.class);
                startActivity(in);
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (sp.getString("name", null) == null) {
            Intent in = new Intent(getActivity(), UserActivity.class);
            startActivity(in);
        } else {
            Intent in = new Intent(getActivity(), ResActivity.class);
            startActivity(in);
        }
    }
}
