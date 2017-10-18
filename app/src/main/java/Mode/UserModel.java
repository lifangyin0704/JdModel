package Mode;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;

import Bean.UserBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 木子 on 2017/10/11.
 */

public class UserModel {

    private Context context;

    public void NetWork(String url,String tel, String pass) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder fb = new FormBody.Builder();
        fb.add("mobile", tel);
        fb.add("password", pass);
        FormBody body = fb.build();
        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iLogin.NetWorkFail(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();

                Gson gson = new Gson();
                UserBean userBean = gson.fromJson(string, UserBean.class);
                String code = userBean.code;
                if ("0".equals(code)) {
                    iLogin.LoginSeccuss(code, "成功");
                } else if ("1".equals(code)) {
                    iLogin.LoginFail(code, "失败");
                }
            }
        });
    }

    private Login iLogin;

    public void setiLogin(Login iLogin) {
        this.iLogin = iLogin;
    }

    public interface Login {
        void LoginSeccuss(String code, String msg);

        void LoginFail(String code, String msg);

        void NetWorkFail(Call call, Exception e);

    }
}
