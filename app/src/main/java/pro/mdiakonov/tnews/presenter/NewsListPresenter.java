package pro.mdiakonov.tnews.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.security.Policy;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pro.mdiakonov.tnews.Constants;
import pro.mdiakonov.tnews.DiApplication;
import pro.mdiakonov.tnews.api.NewsApi;
import pro.mdiakonov.tnews.view.NewsListView;

@InjectViewState
public class NewsListPresenter extends BasePresenter<NewsListView> {
    @Inject
    NewsApi mNewsApi;

    private int mCurrentPage;
    private Policy mNewsListRepository;

    public NewsListPresenter() {
        DiApplication.getComponent().inject(this);
    }

    public void onLoadNextPage() {
        mCurrentPage++;
        getViewState().setRefreshing(true);
        getPage();
    }

    public void onRefresh() {
        mCurrentPage = 0;
        getViewState().setRefreshing(true);
        mNewsListRepository.refresh();
        getPage();
    }

    private void getPage() {


/*        unsubscribeOnDestroy(mNewsApi.getTitles(first, last)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(products -> {
                    getViewState().setRefreshing(false);
                        getViewState().refreshPageItems(products.getProducts());
                        if (products.getNumberPages() <= products.getCurrentPage()) {
                            getViewState().stopPagination();
                        }
                        mCurrentPage = products.getCurrentPage();
                    }, e -> {
                        getViewState().setRefreshing(false);
                        getViewState().showError(R.string.error);
                        if (mCurrentPage > 0) {
                            mCurrentPage--;
                        }
                    }
                ));*/

        mNewsApi.getContent(10024)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    Log.d("HEH", data.content);
                });

        mNewsApi.getTitles(0, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    Log.d("HEH", data.get(0).name);
                });
    }

    public void onErrorCancel() {
        //getViewState().hideError();
    }
}
