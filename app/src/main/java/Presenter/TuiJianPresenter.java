package Presenter;

import Bean.TuiJianBean;
import Mode.TuiJianModel;
import View.TuiJianView;
import okhttp3.Call;

/**
 * Created by 木子 on 2017/10/14.
 */

public class TuiJianPresenter implements TuiJianModel.Login {
    public TuiJianModel modle;
    public TuiJianView view;

    public TuiJianPresenter(TuiJianView view) {
        this.view = view;
        modle = new TuiJianModel();
        modle.setiLogin(this);
    }

    public void Login() {

        modle.NetWork();

    }


    @Override
    public void TJSeccuss(TuiJianBean bean) {
        view.TJSeccuss(bean);
    }

    @Override
    public void TJFaile(String code) {
        view.TJFaile(code);
    }

    @Override
    public void TJNewNo(Call call, Exception e) {
        view.TJNewNo(call, e);
    }
}
