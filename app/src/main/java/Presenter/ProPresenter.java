package Presenter;

import Bean.ProBean;
import Mode.ProModel;
import View.ProView;
import okhttp3.Call;

/**
 * Created by 木子 on 2017/10/11.
 */

public class ProPresenter implements ProModel.Login {

    public ProView view;
    public ProModel model;

    public ProPresenter(ProView view) {
        this.view = view;
        model = new ProModel();
        model.setiLogin(this);
    }

    public void UserLogin(String pscid, String page, String sort) {
        model.NetWork(pscid, page, sort);
    }


    @Override
    public void ProSeccuss(ProBean bean) {
        view.ProSeccuss(bean);
    }

    @Override
    public void ProFaile(String code) {
        view.ProFaile(code);
    }

    @Override
    public void ProNewNo(Call call, Exception e) {
        view.ProNewNo(call, e);
    }
}
