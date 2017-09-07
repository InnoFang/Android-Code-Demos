package io.innofang.mvvmdemo.sign_in;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Toast;

import io.innofang.mvvmdemo.BR;
import io.innofang.mvvmdemo.R;
import io.innofang.mvvmdemo.bean.UserBean;
import io.innofang.mvvmdemo.event.Action;
import io.innofang.mvvmdemo.source.DataSource;
import io.innofang.mvvmdemo.source.DataSourceImpl;

/**
 * Author: Inno Fang
 * Time: 2017/9/7 17:22
 * Description:
 */


public class SignInViewModel extends BaseObservable {

    private Context mContext;
    private UserBean mUserBean;
    private DataSource mDataSource;

    public SignInViewModel(Context context, UserBean userBean) {
        mContext = context;
        mUserBean = userBean;
        mDataSource = DataSourceImpl.getInstance(context);
    }

    @Bindable
    public String getUsername() {
        return mUserBean == null ? "" : mUserBean.getUsername();
    }

    @Bindable
    public String getPassword() {
        return mUserBean == null ? "" : mUserBean.getPassword();
    }

    public void setUsername(String username) {
        if (null != mUserBean) {
            mUserBean.setUsername(username);
            notifyPropertyChanged(BR.username);
        }
    }

    public void setPassword(String password) {
        if (null != mUserBean) {
            mUserBean.setPassword(password);
            notifyPropertyChanged(BR.password);
        }
    }

    public View.OnClickListener onClickToSignIn() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataSource.signIn(mUserBean, new Action<Integer>() {
                    @Override
                    public void onAction(Integer integer) {
                        if (integer == 1) {
                            toast(R.string.sign_in_succeed);
                        } else {
                            toast(R.string.sign_in_failed);
                        }
                    }
                });
            }
        };
    }

    private void toast(@StringRes int id) {
        Toast.makeText(mContext, id, Toast.LENGTH_SHORT).show();
    }
}
