package View;

import okhttp3.Call;

/**
 * Created by 木子 on 2017/10/13.
 */

public interface GouWuView {
    void GwSeccuss(String code);

    void GwFaile(String code);

    void GwFail(Call call, Exception e);
}
