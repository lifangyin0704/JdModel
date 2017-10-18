package View;

import Bean.CarBean;
import okhttp3.Call;

/**
 * Created by 木子 on 2017/10/13.
 */

public interface ShopCarView {
    void CarSeccuss(CarBean code);

    void CarFaile(String code);

    void CarFail(Call call, Exception e);
}
