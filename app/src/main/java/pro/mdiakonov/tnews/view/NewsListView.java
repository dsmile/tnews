package pro.mdiakonov.tnews.view;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import pro.mdiakonov.tnews.api.pojo.Title;

public interface NewsListView extends MvpView {
    void showError(@StringRes int messageResId);
    @StateStrategyType(value = AddToEndSingleStrategy.class, tag = "set_refreshing")
    void setRefreshing(boolean isRefreshing);
    @StateStrategyType(value = AddToEndSingleStrategy.class, tag = "refresh")
    void refreshPageItems(List<Title> items);
    void stopPagination();
    @StateStrategyType(value = SkipStrategy.class, tag = "details")
    void showDetails(String id);
}
