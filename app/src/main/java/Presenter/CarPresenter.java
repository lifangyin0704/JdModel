package Presenter;

import Bean.CarBean;
import Mode.Car_Model;
import View.ShopCarView;
import okhttp3.Call;

/**
 * Created by 木子 on 2017/10/11.
 */

public class CarPresenter implements Car_Model.Login {

    public ShopCarView view;
    public Car_Model model;

    public CarPresenter(ShopCarView view) {
        this.view = view;
        model = new Car_Model();
        model.setiLogin(this);
    }

    public void UserLogin(String uid) {
        model.NetWork(uid);
    }


    @Override
    public void CarSeccuss(CarBean code) {
        view.CarSeccuss(code);
    }

    @Override
    public void CarFaile(String code) {
        view.CarFaile(code);
    }

    @Override
    public void CarFail(Call call, Exception e) {
        view.CarFail(call, e);
    }
}
