package io.innofang.mvvmdemo.sign_in;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.innofang.mvvmdemo.BR;
import io.innofang.mvvmdemo.R;
import io.innofang.mvvmdemo.bean.UserBean;
import io.innofang.mvvmdemo.databinding.FragmentSignInBinding;

/**
 * Author: Inno Fang
 * Time: 2017/9/7 17:18
 * Description:
 */


public class SignInFragment extends Fragment {

    private SignInViewModel mViewModel;
    private FragmentSignInBinding mBinding;

    public static SignInFragment newInstance() {
       return Holder.sInstance;
    }

    private static class Holder {
        private static final SignInFragment sInstance = new SignInFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new SignInViewModel(getContext(), new UserBean("hello", "hello"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        mBinding = DataBindingUtil.bind(view);
        mBinding.setVariable(BR.vm, mViewModel);
        return view;
    }
}
