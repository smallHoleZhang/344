package com.bignerdranch.android.beom.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.beom.CustomDialog;
import com.bignerdranch.android.beom.MainActivity;
import com.bignerdranch.android.beom.MyUser;
import com.bignerdranch.android.beom.R;
import com.bignerdranch.android.beom.Uilt.L;
import com.bignerdranch.android.beom.Uilt.ShareUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by hasee on 2017/3/11.
 */

public class LoginActivity extends BaseActivity  implements View.OnClickListener{



    private Button btn_login;
    private Button btn_registered;
    private EditText et_name;
    private EditText et_password;
    private Button btnLogin;
    private CheckBox keep_password;
    private TextView tv_forget;
    private CustomDialog mCustomDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())

        {
            case R.id.btn_registered:
                startActivity(new Intent(this, RegisteredActivity.class));
                break;
            case R.id.btn_Login:
                String name = et_name.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(password)) {
                    mCustomDialog.show();
                    //登录
                    final MyUser user = new MyUser();
                    user.setUsername(name);
                    user.setPassword(password);
                    user.login(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            //判断结果
                            if (e == null) {
                                mCustomDialog.dismiss();
                                //判断邮箱是否验证
                                if (user.getEmailVerified()) {
                                    //跳转
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "请前往邮箱验证", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "登录失败：" + e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_forget:
                startActivity(new Intent(this,Forgetpassword.class));
                break;
        }
    }
    private  void  initView()
    {
        btn_registered = (Button) findViewById(R.id.btn_registered);
        btn_login = (Button) findViewById(R.id.btn_Login);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login.setOnClickListener(this);
        btn_registered.setOnClickListener(this);
        keep_password = (CheckBox) findViewById(R.id.keep_password);
        tv_forget = (TextView) findViewById(R.id.tv_forget);
        tv_forget.setOnClickListener(this);
        mCustomDialog = new CustomDialog(this,100,100,R.layout.dialog_loding,R.style.Theme_dialog, Gravity.CENTER,R.style.pop_anim_style);
        mCustomDialog.setCancelable(false);
        boolean isKeep = ShareUtils.getBoonlean(this,"keeppass",false);
        keep_password.setChecked(isKeep);
        if(isKeep)

        {
            L.d("进入了修改密码");
            String name  = ShareUtils.getString(this,"name","");
            String password = ShareUtils.getString(this,"password","");
            et_name.setText(name);
            et_password.setText(password);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //保存状态
        ShareUtils.putBoolean(this,"keeppass",keep_password.isChecked());

        //是否记住密码
        if(keep_password.isChecked())
        {
            L.d("是");
            ShareUtils.putString(this, "name", et_name.getText().toString().trim());
            ShareUtils.putString(this, "password", et_password.getText().toString().trim());
        }else {
            L.d("否123");
            ShareUtils.deleShare(this, "name");
            ShareUtils.deleShare(this, "password");
        }
    }
}
