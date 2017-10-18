package Presenter;

import Bean.User_xq_Bean;
import Mode.User_xq_Model;
import View.User_xq_View;
import okhttp3.Call;

/**
 * Created by 木子 on 2017/10/13.
 */

public class User_xq_Presenter implements User_xq_Model.Login {

    public User_xq_View view;
    public User_xq_Model model;

    public User_xq_Presenter(User_xq_View view) {
        this.view = view;
        model = new User_xq_Model();
        model.setiLogin(this);
    }



    public void UserLogin(String uid) {
        model.NetWork(uid);
    }

    @Override
    public void UserSeccuss(User_xq_Bean user, String msg) {
        view.UserSeccuss(user, msg);
    }

    @Override
    public void UserFaile(String code) {
        view.UserFaile(code);
    }

    @Override
    public void NetFail(Call call, Exception e) {
        view.NetFail(call, e);
    }
}
