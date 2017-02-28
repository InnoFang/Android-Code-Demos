package com.juhezi.mvptest.sign_in;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.juhezi.mvptest.R;
import com.juhezi.mvptest.model.ResponseImpl;
import com.juhezi.mvptest.model.local.LocalResponse;
import com.juhezi.mvptest.model.remote.RemoteResponse;

/**
 * Created by qiao1 on 2017/1/7.
 */
public class SignInFragment extends Fragment implements SignInContract.View {
    private static String TAG = "SignInFragment";

    private SignInContract.Presenter mPresenter;

    private View rootView;
    private EditText mEtUsername;
    private EditText mEtPasswd;
    private Button mBtnSignIn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SignInPresenter(this,
                new ResponseImpl(new LocalResponse(), new RemoteResponse()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sign_in_frag, container, false);
        mEtPasswd = (EditText) rootView.findViewById(R.id.et_username);
        mEtUsername = (EditText) rootView.findViewById(R.id.et_passwd);
        mBtnSignIn = (Button) rootView.findViewById(R.id.btn_sign_in);

        initEvent();
        return rootView;
    }

    private void initEvent() {
        mBtnSignIn.setOnClickListener(v ->
                mPresenter.signIn(getUsername(), getPasswd(),
                        result_code -> {
            if (result_code == 0) {
                showToast(getString(R.string.sign_in_success));
            } else {
                showToast(getString(R.string.sign_in_fail));
            }
        }));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.start();
        }
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void setPresenter(SignInContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public String getUsername() {
        return mEtUsername.getText().toString();
    }

    @Override
    public String getPasswd() {
        return mEtPasswd.getText().toString();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @return 返回一个指定的Fragment实例
     */
    public static Fragment newInstance() {
        return new SignInFragment();
    }
}
