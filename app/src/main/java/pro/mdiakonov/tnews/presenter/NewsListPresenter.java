package pro.mdiakonov.tnews.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import pro.mdiakonov.tnews.DiApplication;
import pro.mdiakonov.tnews.R;
import pro.mdiakonov.tnews.api.pojo.Title;
import pro.mdiakonov.tnews.model.NetworkErrorCallback;
import pro.mdiakonov.tnews.model.TitleRepository;
import pro.mdiakonov.tnews.view.NewsListView;

@InjectViewState
public class NewsListPresenter extends BasePresenter<NewsListView> implements NetworkErrorCallback {
    private static final String LOG_TAG = NewsListPresenter.class.getSimpleName();

    @Inject
    TitleRepository mTitlesRepository;

    private int mCurrentPage = -1;

    public NewsListPresenter() {
        DiApplication.getComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        onRefresh();
    }

    public void onLoadNextPage() {
        Log.d(LOG_TAG, "Next page: " + mCurrentPage);
        mCurrentPage++;
        getViewState().setRefreshing(true);
        getPage();
    }

    public void onRefresh() {
        Log.d(LOG_TAG, "Refresh");
        mCurrentPage = 0;
        getViewState().setRefreshing(true);
        mTitlesRepository.clearCache();
        getPage();
    }

    private void getPage() {
        Log.d(LOG_TAG, "Page start: " + mCurrentPage);
        Disposable disposable = mTitlesRepository.getTitles(mCurrentPage, this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<List<Title>>() {
                    @Override
                    public void onSuccess(List<Title> titles) {
                        getViewState().setRefreshing(false);
                        Log.d(LOG_TAG, "Titles: " + titles.size());
                        getViewState().refreshPageItems(titles);

/*                      todo Determine end of the list
                        if (products.getNumberPages() <= products.getCurrentPage()) {
                        getViewState().stopPagination();
                    }*/
                        Log.d(LOG_TAG, "Page end: " + mCurrentPage);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // todo parse error
                        Log.e(LOG_TAG, "error parsing", e);
                    }
                });

        unsubscribeOnDestroy(disposable);
    }

    public void onShowDetails(String newsId) {
        getViewState().showDetails(newsId);
    }

    @Override
    public void onNetworkError() {
        Log.d(LOG_TAG, "onNetworkError");
        getViewState().setRefreshing(false);
        getViewState().showError(R.string.error);
        if (mCurrentPage > -1) {
            mCurrentPage--;
        }
    }

    public void onRetryLoad() {
        if (mCurrentPage > -1) {
            onLoadNextPage();
        } else {
            onRefresh();
        }
    }
}
