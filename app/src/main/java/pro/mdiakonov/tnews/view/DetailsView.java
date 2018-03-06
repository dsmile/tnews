package pro.mdiakonov.tnews.view;

import android.content.Context;
import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import pro.mdiakonov.tnews.api.pojo.Content;

public interface DetailsView extends MvpView {
    void showError(@StringRes int messageResId);
    @StateStrategyType(value = AddToEndSingleStrategy.class, tag = "set_refreshing")
    void setRefreshing(boolean isRefreshing);
    @StateStrategyType(value = AddToEndSingleStrategy.class, tag = "refresh")
    void refresh(Content content);
}
