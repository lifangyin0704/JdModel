package View;

import Bean.TuiJianBean;
import okhttp3.Call;

/**
 * Created by 木子 on 2017/10/16.
 */

public interface TuiJianView {
    void TJSeccuss(TuiJianBean bean);

    void TJFaile(String code);

    void TJNewNo(Call call, Exception e);
}
