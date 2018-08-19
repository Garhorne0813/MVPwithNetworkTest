package com.garhorne.mvpwithnetworktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import net.Constant;

import Callback.MyCallback;
import presenter.MainPresenter;
import view.UserView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, UserView{

    private EditText et_account;
    private EditText et_password;
    private Button btn_register;
    private Button btn_clear;
    private ProgressBar progressBar;

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_account = (EditText)findViewById(R.id.account);
        et_password = (EditText)findViewById(R.id.password);
        btn_register = (Button) findViewById(R.id.register);
        btn_clear = (Button) findViewById(R.id.clear);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btn_register.setOnClickListener(this);
        btn_clear.setOnClickListener(this);

        presenter = new MainPresenter(this);
        presenter.setProgressBarVisiblity(View.INVISIBLE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                presenter.setProgressBarVisiblity(View.VISIBLE);
                presenter.getConnect("GET", Constant.REGISTER_URL + "?account=" + et_account.getText().toString()
                        + "&password=" + et_password.getText().toString(), new MyCallback() {
                    @Override
                    public void Success() {
                        Toast.makeText(MainActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        Log.d("CallBack","注册成功");
                    }

                    @Override
                    public void Fail() {
                        Toast.makeText(MainActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                        Log.d("CallBack","注册失败");
                    }

                    @Override
                    public void Error() {
                        Toast.makeText(MainActivity.this,"错误",Toast.LENGTH_SHORT).show();
                        Log.d("CallBack","错误");
                    }

                    @Override
                    public void Complete() {
                        Toast.makeText(MainActivity.this,"进程完成",Toast.LENGTH_SHORT).show();
                        Log.d("CallBack","进程完成");
                        presenter.setProgressBarVisiblity(View.INVISIBLE);
                    }
                });
                break;
            case R.id.clear:
                presenter.clear();
                break;
            default:
                break;
        }
    }

    @Override
    public void clearText() {
        et_account.setText("");
        et_password.setText("");
    }

    @Override
    public void onSetProgressBarVisiblity(int visiblity) {
        progressBar.setVisibility(visiblity);
    }
}
