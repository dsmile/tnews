package pro.mdiakonov.tnews.presenter;

import android.content.Context;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import pro.mdiakonov.tnews.DiApplication;
import pro.mdiakonov.tnews.R;
import pro.mdiakonov.tnews.api.NewsApi;
import pro.mdiakonov.tnews.api.pojo.Content;
import pro.mdiakonov.tnews.api.pojo.Title;
import pro.mdiakonov.tnews.view.DetailsView;
import pro.mdiakonov.tnews.view.NewsListView;

@InjectViewState
public class DetailsPresenter extends BasePresenter<DetailsView> {
    @Inject
    NewsApi mNewsApi;
    private String mNewsId;

    public DetailsPresenter() {
        DiApplication.getComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().setRefreshing(true);
    }

    public void onLoadDetails(String newsId) {
        mNewsId = newsId;
        Disposable disposable = mNewsApi.getContent(mNewsId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<Content>() {
                    @Override
                    public void onSuccess(Content content) {
                        getViewState().refresh(content);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showError(R.string.connection_error);
                    }
                });
        unsubscribeOnDestroy(disposable);
    }
}
