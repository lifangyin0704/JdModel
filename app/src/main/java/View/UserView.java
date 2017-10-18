package View;

import okhttp3.Call;

/**
 * Created by 木子 on 2017/10/11.
 */

public interface UserView {

    void LoginSeccuss(String code, String msg);

    void LoginFail(String code, String msg);

    void NetWorkFail(Call call, Exception e);

    void UserName(String tel);

    void UserPass(String pass);


}
