package Mode;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;

import Api.MyApi;
import Bean.NiChengBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 木子 on 2017/10/13.
 */

public class NiCheng_Model {
    private Context context;

    public void NetWork(String uid, String nickname) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder fb = new FormBody.Builder();
        fb.add("uid", uid);
        fb.add("nickname", nickname);
        FormBody body = fb.build();
        Request request = new Request.Builder()
                .post(body)
                .url(MyApi.NAME_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iLogin.NiChengFail(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();

                Gson gson = new Gson();
                NiChengBean userBean = gson.fromJson(string, NiChengBean.class);
                String code = userBean.code;
                if ("0".equals(code)) {
                    iLogin.NiChengSeccuss(code, "成功");
                } else if ("1".equals(code)) {
                    iLogin.NiChengFaile(code);
                }
            }
        });
    }

    private Login iLogin;

    public void setiLogin(Login iLogin) {
        this.iLogin = iLogin;
    }

    public interface Login {
        void NiChengSeccuss(String user, String msg);

        void NiChengFaile(String code);

        void NiChengFail(Call call, Exception e);

    }
}
