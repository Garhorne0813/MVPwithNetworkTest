package net;

import android.os.Handler;
import android.os.Looper;
import java.net.URL;

import Callback.MyCallback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Connect {


    private OkHttpClient client;

    private Handler handler;

    public Connect(String method, String url, MyCallback myCallback) {
        handler = new Handler(Looper.getMainLooper());
        if (method.equals("GET")){
            doGet(url,myCallback);
        }else if (method.equals("POST")){

        } else {

        }
    }

    private void doGet(final String url1, final MyCallback myCallback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int result = 0;
                    URL url = new URL(url1);
                    client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()){
                        String str = response.body().string();
                        if (str.equals("loginsuccess")){
                            result = 100;
                        }else if (str.equals("loginfailed")){
                            result = 200;
                        }
                    } else {
                        result = 300;
                    }
                    final int result1 = result;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            switch (result1){
                                case 100:
                                    myCallback.Success();
                                    break;
                                case 200:
                                    myCallback.Fail();
                                    break;
                                case 300:
                                    myCallback.Error();
                                    break;
                                    default:
                                        break;
                            }
                            myCallback.Complete();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
