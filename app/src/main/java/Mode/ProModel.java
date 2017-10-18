package Mode;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;

import Api.MyApi;
import Bean.ProBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 木子 on 2017/10/11.
 */

public class ProModel {

    private Context context;

    public void NetWork(String pscid, String page, final String sort) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder fb = new FormBody.Builder();
        fb.add("pscid", pscid);
        fb.add("page", page);
        fb.add("sort", sort);
        FormBody body = fb.build();
        Request request = new Request.Builder()
                .post(body)
                .url(MyApi.PRO_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iLogin.ProNewNo(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();

                Gson gson = new Gson();
                ProBean userBean = gson.fromJson(string, ProBean.class);
                String code = userBean.getCode();
                if ("0".equals(code)) {
                    iLogin.ProSeccuss(userBean);
                } else if ("1".equals(code)) {
                    iLogin.ProFaile(code);
                }
            }
        });
    }

    private Login iLogin;

    public void setiLogin(Login iLogin) {
        this.iLogin = iLogin;
    }

    public interface Login {
        void ProSeccuss(ProBean bean);

        void ProFaile(String code);

        void ProNewNo(Call call, Exception e);

    }
}
