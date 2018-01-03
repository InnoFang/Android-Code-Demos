package innofang.io.componentsdemo;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Author: Inno Fang
 * Time: 2018/1/1 21:30
 * Description:
 */


public class SnackbarMessage extends SingleLiveEvent<String>{

    public void observe(LifecycleOwner owner, final SnackbarObserver observer) {
        super.observe(owner, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String message) {
                if (TextUtils.isEmpty(message)) return;

                observer.onNewMessage(message);
            }
        });
    }

    public interface SnackbarObserver {
        /**
         * Called when there is a new message to be shown.
         *
         * @param message The new message, non-null.
         */
        void onNewMessage(String message);
    }

}
