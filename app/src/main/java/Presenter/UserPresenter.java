package Presenter;

import android.text.TextUtils;

import Mode.UserModel;
import View.UserView;
import okhttp3.Call;

/**
 * Created by 木子 on 2017/10/11.
 */

public class UserPresenter implements UserModel.Login {

    public UserView view;
    public UserModel model;

    public UserPresenter(UserView view) {
        this.view = view;
        model = new UserModel();
        model.setiLogin(this);
    }

    public void UserLogin(String url,String tel, String pass) {
        if (TextUtils.isEmpty(tel)) {
            view.UserName("用户名不能为空");
        }
        if (TextUtils.isEmpty(pass)) {
            view.UserPass("用户密码不能为空");
        }
        model.NetWork(url,tel, pass);
    }

    @Override
    public void LoginSeccuss(String code, String msg) {
        view.LoginSeccuss(code,msg);
    }

    @Override
    public void LoginFail(String code, String msg) {
        view.LoginFail(code,msg);
    }

    @Override
    public void NetWorkFail(Call call, Exception e) {
        view.NetWorkFail(call,e);
    }
}
