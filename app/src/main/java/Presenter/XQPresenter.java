package Presenter;

import Bean.XBean;
import Mode.XQModel;
import View.XQView;
import okhttp3.Call;

/**
 * Created by 木子 on 2017/10/11.
 */

public class XQPresenter implements XQModel.Login {

    public XQView view;
    public XQModel model;

    public XQPresenter(XQView view) {
        this.view = view;
        model = new XQModel();
        model.setiLogin(this);
    }

    public void UserLogin(String pid) {
        model.NetWork(pid);
    }


    @Override
    public void XqSeccuss(XBean bean) {
        view.XqSeccuss(bean);
    }

    @Override
    public void XqFaile(String code) {
        view.XqFaile(code);
    }

    @Override
    public void XqNewNo(Call call, Exception e) {
        view.XqNewNo(call, e);
    }
}
