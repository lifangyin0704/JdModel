package Mode;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;

import Api.MyApi;
import Bean.CarBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 木子 on 2017/10/13.
 */

public class Car_Model {
    private Context context;

    public void NetWork(String uid) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder fb = new FormBody.Builder();
        fb.add("uid", uid);
        FormBody body = fb.build();
        Request request = new Request.Builder()
                .post(body)
                .url(MyApi.CAR_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iLogin.CarFail(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();

                Gson gson = new Gson();
                CarBean userBean = gson.fromJson(string, CarBean.class);
                String code = userBean.getCode();
                if ("0".equals(code)) {
                    iLogin.CarSeccuss(userBean);
                } else if ("1".equals(code)) {
                    iLogin.CarFaile(code);
                }
            }
        });
    }

    private Login iLogin;

    public void setiLogin(Login iLogin) {
        this.iLogin = iLogin;
    }

    public interface Login {
        void CarSeccuss(CarBean code);

        void CarFaile(String code);

        void CarFail(Call call, Exception e);

    }
}
