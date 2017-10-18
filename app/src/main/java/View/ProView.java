package View;

import Bean.ProBean;
import okhttp3.Call;

/**
 * Created by 木子 on 2017/10/16.
 */

public interface ProView {
    void ProSeccuss(ProBean bean);

    void ProFaile(String code);

    void ProNewNo(Call call, Exception e);
}
