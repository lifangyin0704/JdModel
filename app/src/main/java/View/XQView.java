package View;

import Bean.XBean;
import okhttp3.Call;

/**
 * Created by 木子 on 2017/10/16.
 */

public interface XQView {
    void XqSeccuss(XBean bean);

    void XqFaile(String code);

    void XqNewNo(Call call, Exception e);
}
