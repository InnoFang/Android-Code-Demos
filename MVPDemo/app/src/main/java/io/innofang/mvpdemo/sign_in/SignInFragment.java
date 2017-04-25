package io.innofang.mvpdemo.sign_in;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.innofang.mvpdemo.R;
import io.innofang.mvpdemo.model.ResponseImpl;
import io.innofang.mvpdemo.model.local.LocalResponse;
import io.innofang.mvpdemo.model.remote.RemoteResponse;
import io.innofang.mvpdemo.util.Action;


public class SignInFragment extends Fragment implements SignInContract.View {
    private static String TAG = "SignInFragment";

    private SignInContract.Presenter mPresenter;

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mSignInButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SignInPresenter(this,
                new ResponseImpl(new LocalResponse(), new RemoteResponse()));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        mPasswordEditText = (EditText) view.findViewById(R.id.et_username);
        mUsernameEditText = (EditText) view.findViewById(R.id.et_passwd);
        mSignInButton = (Button) view.findViewById(R.id.btn_sign_in);

        initEvent();
        return view;
    }

    private void initEvent() {
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.signIn(getUsername(), getPassword(), new Action<Integer>() {
                    @Override
                    public void onAction(Integer integer) {
                        if (integer == 0) {
                            showToast(getString(R.string.sign_in_success));
                        } else {
                            showToast(getString(R.string.sign_in_fail));
                        }
                    }
                });
            }
        });
    }

    @Override
    public void setPresenter(SignInContract.Presenter presenter) {
        mPresenter = presenter;
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
    public String getUsername() {
        return mUsernameEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPasswordEditText.getText().toString();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static Fragment newInstance() {
        return new SignInFragment();
    }
}
