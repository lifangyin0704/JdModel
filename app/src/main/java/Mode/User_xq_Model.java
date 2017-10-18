package Mode;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;

import Api.MyApi;
import Bean.User_xq_Bean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 木子 on 2017/10/13.
 */

public class User_xq_Model {
    private Context context;

    public void NetWork(String uid) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder fb = new FormBody.Builder();
        fb.add("uid", uid);
        FormBody body = fb.build();
        Request request = new Request.Builder()
                .post(body)
                .url(MyApi.USER_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iLogin.NetFail(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();

                Gson gson = new Gson();
                User_xq_Bean userBean = gson.fromJson(string, User_xq_Bean.class);
                String code = userBean.getCode();
                if ("0".equals(code)) {
                    iLogin.UserSeccuss(userBean, "成功");
                } else if ("1".equals(code)) {
                    iLogin.UserFaile(code);
                }
            }
        });
    }

    private Login iLogin;

    public void setiLogin(Login iLogin) {
        this.iLogin = iLogin;
    }

    public interface Login {
        void UserSeccuss(User_xq_Bean user, String msg);

        void UserFaile(String code);

        void NetFail(Call call, Exception e);

    }
}
