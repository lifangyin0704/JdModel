package Mode;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;

import Api.MyApi;
import Bean.ShapCarBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 木子 on 2017/10/13.
 */

public class Shop_Model {
    private Context context;

    public void NetWork(String uid, String pid) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder fb = new FormBody.Builder();
        fb.add("uid", uid);
        fb.add("pid", pid);
        FormBody body = fb.build();
        Request request = new Request.Builder()
                .post(body)
                .url(MyApi.SHOP_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iLogin.GwFail(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();

                Gson gson = new Gson();
                ShapCarBean userBean = gson.fromJson(string, ShapCarBean.class);
                String code = userBean.code;
                if ("0".equals(code)) {
                    iLogin.GwSeccuss(code);
                } else if ("1".equals(code)) {
                    iLogin.GwFaile(code);
                }
            }
        });
    }

    private Login iLogin;

    public void setiLogin(Login iLogin) {
        this.iLogin = iLogin;
    }

    public interface Login {
        void GwSeccuss(String code);

        void GwFaile(String code);

        void GwFail(Call call, Exception e);

    }
}
