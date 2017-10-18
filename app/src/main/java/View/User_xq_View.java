package View;

import Bean.User_xq_Bean;
import okhttp3.Call;

/**
 * Created by 木子 on 2017/10/13.
 */

public interface User_xq_View {

    void UserSeccuss(User_xq_Bean user, String msg);
    void UserFaile(String code);
    void NetFail(Call call,Exception e);
}
