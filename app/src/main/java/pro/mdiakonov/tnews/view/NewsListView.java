package pro.mdiakonov.tnews.view;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import pro.mdiakonov.tnews.api.pojo.Title;

public interface NewsListView extends MvpView {
    void showError(@StringRes int messageResId);
    void showDetails(long id);
    void setRefreshing(boolean isRefreshing);
    void setEndOfList(boolean isEndOfList);
    void refreshPageItems(List<Title> items);
    void stopPagination();
}
