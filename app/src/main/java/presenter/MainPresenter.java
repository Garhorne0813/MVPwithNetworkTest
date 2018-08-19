package presenter;

import net.Connect;

import Callback.MyCallback;
import view.UserView;

public class MainPresenter implements Presenter {

    UserView userView;

    public MainPresenter(UserView userView) {
        this.userView = userView;
    }

    @Override
    public void clear() {
        userView.clearText();
    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        userView.onSetProgressBarVisiblity(visiblity);
    }

    @Override
    public void getConnect(String method,String url, MyCallback myCallback) {
        new Connect(method,url,myCallback);
    }


}
