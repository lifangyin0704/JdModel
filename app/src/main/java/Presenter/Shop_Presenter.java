package Presenter;

import Mode.Shop_Model;
import View.GouWuView;
import okhttp3.Call;

/**
 * Created by 木子 on 2017/10/13.
 */

public class Shop_Presenter implements Shop_Model.Login {

    public GouWuView view;
    public Shop_Model model;

    public Shop_Presenter(GouWuView view) {
        this.view = view;
        model = new Shop_Model();
        model.setiLogin(this);
    }

    public void UserLogin(String uid, String pid) {
        model.NetWork(uid, pid);
    }

    @Override
    public void GwSeccuss(String code) {
        view.equals(code);

    }

    @Override
    public void GwFaile(String code) {
        view.GwFaile(code);
    }

    @Override
    public void GwFail(Call call, Exception e) {
        view.GwFail(call, e);
    }
}
