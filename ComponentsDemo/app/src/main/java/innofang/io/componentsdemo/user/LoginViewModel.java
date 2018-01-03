package innofang.io.componentsdemo.user;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableField;
import android.text.TextUtils;

import innofang.io.componentsdemo.R;
import innofang.io.componentsdemo.SingleLiveEvent;
import innofang.io.componentsdemo.SnackbarMessage;

/**
 * Author: Inno Fang
 * Time: 2018/1/1 20:45
 * Description:
 */


public class LoginViewModel extends AndroidViewModel {

    private static final String TAG = "LoginViewModel";

    private final SingleLiveEvent<String> mOpenUserList = new SingleLiveEvent<>();
    private final SnackbarMessage mSnackbarText = new SnackbarMessage();
    private final Context mContext;

    public final ObservableField<String> loginName = new ObservableField<>();
    public final ObservableField<String> loginPass = new ObservableField<>();

    public LoginViewModel(Application context) {
        super(context);
        mContext = context.getApplicationContext();
    }

    public SingleLiveEvent<String> getOpenUserList() {
        return mOpenUserList;
    }

    SnackbarMessage getSnackbarMessage() {
        return mSnackbarText;
    }

    private void login(String loginName, String loginPass) {

        if (TextUtils.isEmpty(loginName)) {
            mSnackbarText.setValue(mContext.getString(R.string.login_name_not_input));
            return;
        }
        if (TextUtils.isEmpty(loginPass)) {
            mSnackbarText.setValue(mContext.getString(R.string.login_pass_not_input));
            return;
        }

        mOpenUserList.setValue("123456");
    }

    public void login() {
        login(loginName.get(), loginPass.get());
    }
}
