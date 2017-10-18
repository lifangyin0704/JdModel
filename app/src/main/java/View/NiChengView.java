package View;

import okhttp3.Call;

/**
 * Created by 木子 on 2017/10/13.
 */

public interface NiChengView {
    void NiChengSeccuss(String user, String msg);

    void NiChengFaile(String code);

    void NiChengFail(Call call, Exception e);
}
