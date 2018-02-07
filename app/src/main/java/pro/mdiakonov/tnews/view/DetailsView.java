package pro.mdiakonov.tnews.view;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;

public interface DetailsView extends MvpView {
    void showError(@StringRes int messageResId);
}
