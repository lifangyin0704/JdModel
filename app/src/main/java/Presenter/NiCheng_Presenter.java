package Presenter;

import Mode.NiCheng_Model;
import View.NiChengView;
import okhttp3.Call;

/**
 * Created by 木子 on 2017/10/13.
 */

public class NiCheng_Presenter implements NiCheng_Model.Login {

    public NiChengView view;
    public NiCheng_Model model;

    public NiCheng_Presenter(NiChengView view) {
        this.view = view;
        model = new NiCheng_Model();
        model.setiLogin(this);
    }


    public void UserLogin(String uid, String name) {
        model.NetWork(uid, name);
    }


    @Override
    public void NiChengSeccuss(String user, String msg) {
        view.NiChengSeccuss(user, msg);
    }

    @Override
    public void NiChengFaile(String code) {
        view.NiChengFaile(code);
    }

    @Override
    public void NiChengFail(Call call, Exception e) {
        view.NiChengFail(call, e);

    }
}
