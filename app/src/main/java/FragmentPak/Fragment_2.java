package FragmentPak;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import bwie.com.jdmodel.R;

/**
 * Created by 木子 on 2017/10/17.
 */

public class Fragment_2 extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_2, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        WebView wv = view.findViewById(R.id.wv);
        wv.loadUrl(getActivity().getSharedPreferences("URL", getActivity().MODE_PRIVATE).getString("url", null));
    }
}
