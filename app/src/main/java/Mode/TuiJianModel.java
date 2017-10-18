package Mode;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;

import Api.MyApi;
import Bean.TuiJianBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 木子 on 2017/10/11.
 */

public class TuiJianModel {

    private Context context;

    public void NetWork() {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder fb = new FormBody.Builder();
        FormBody body = fb.build();
        Request request = new Request.Builder()
                .post(body)
                .url(MyApi.BANNER_URL)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iLogin.TJNewNo(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();

                Gson gson = new Gson();
                TuiJianBean userBean = gson.fromJson(string, TuiJianBean.class);
                String code = userBean.getCode();
                if ("0".equals(code)) {
                    iLogin.TJSeccuss(userBean);
                } else if ("1".equals(code)) {
                    iLogin.TJFaile(code);
                }
            }
        });
    }

    private Login iLogin;

    public void setiLogin(Login iLogin) {
        this.iLogin = iLogin;
    }

    public interface Login {
        void TJSeccuss(TuiJianBean bean);

        void TJFaile(String code);

        void TJNewNo(Call call, Exception e);

    }
}
