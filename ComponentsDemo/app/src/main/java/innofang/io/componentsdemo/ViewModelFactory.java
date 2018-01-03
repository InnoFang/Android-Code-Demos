package innofang.io.componentsdemo;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.VisibleForTesting;

import innofang.io.componentsdemo.user.LoginViewModel;

/**
 * Author: Inno Fang
 * Time: 2018/1/1 20:39
 * Description:
 */


public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @SuppressWarnings("StaticFieldLeak")
    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;

    public static ViewModelFactory getInstance(Application application) {
        if (null == INSTANCE) {
            synchronized (ViewModelFactory.class) {
                if (null == INSTANCE) {
                    INSTANCE = new ViewModelFactory(application);
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private ViewModelFactory(Application application) {
        mApplication = application;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
