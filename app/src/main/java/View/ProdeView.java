package View;

import Bean.ProdeBean;
import okhttp3.Call;

/**
 * Created by 木子 on 2017/10/14.
 */

public interface ProdeView {

    void ProdeSeccuss(ProdeBean pro);

    void ProdeFaile(String code);

    void ProdeNet(Call call, Exception e);
}
