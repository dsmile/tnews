package pro.mdiakonov.tnews.view.adapter;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class NetworkState {
    public static final int LOADING = 0;
    public static final int NO_MORE = 1;
    public static final int FAIL = 2;

    @IntDef({LOADING, NO_MORE, FAIL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }
}
