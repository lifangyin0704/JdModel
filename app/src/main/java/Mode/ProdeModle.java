package Mode;

import com.google.gson.Gson;

import java.io.IOException;

import Api.MyApi;
import Bean.ProdeBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 木子 on 2017/10/14.
 */

public class ProdeModle {

    public void NetWork(String name) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder fb = new FormBody.Builder();
        fb.add("keywords", name);
        FormBody body = fb.build();
        Request request = new Request.Builder()
                .url(MyApi.PRODUCTS_URL)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iLogin.ProNet(call, e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();

                Gson gson = new Gson();
                ProdeBean proBean = gson.fromJson(string, ProdeBean.class);
                String code = proBean.getCode();
                if ("0".equals(code)) {
                    iLogin.ProSeccuss(proBean);
                } else if ("1".equals(code)) {
                    iLogin.ProFaile(code);
                }

            }
        });
    }

    public Login iLogin;

    public void ILogin(Login iLogin) {
        this.iLogin = iLogin;
    }


    public interface Login {
        void ProSeccuss(ProdeBean pro);

        void ProFaile(String code);

        void ProNet(Call call, Exception e);

    }
}
