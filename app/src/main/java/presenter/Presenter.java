package presenter;

import Callback.MyCallback;

public interface Presenter {

    void clear();
    void setProgressBarVisiblity(int visiblity);
    void getConnect(String method,String url, MyCallback myCallback);

}
