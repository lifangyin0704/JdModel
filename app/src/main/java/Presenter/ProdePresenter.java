package Presenter;

import Bean.ProdeBean;
import Mode.ProdeModle;
import View.ProdeView;
import okhttp3.Call;

/**
 * Created by 木子 on 2017/10/14.
 */

public class ProdePresenter implements ProdeModle.Login {
    public ProdeModle modle;
    public ProdeView view;

    public ProdePresenter(ProdeView view) {
        this.view = view;
        modle = new ProdeModle();
        modle.ILogin(this);
    }

    public void Login(String name) {

        modle.NetWork(name);

    }


    @Override
    public void ProSeccuss(ProdeBean pro) {
        view.ProdeSeccuss(pro);
    }

    @Override
    public void ProFaile(String code) {
        view.ProdeFaile(code);
    }

    @Override
    public void ProNet(Call call, Exception e) {
        view.ProdeNet(call, e);
    }
}
