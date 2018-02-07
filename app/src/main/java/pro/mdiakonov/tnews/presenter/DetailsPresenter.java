package pro.mdiakonov.tnews.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import pro.mdiakonov.tnews.DiApplication;
import pro.mdiakonov.tnews.api.NewsApi;
import pro.mdiakonov.tnews.view.DetailsView;
import pro.mdiakonov.tnews.view.NewsListView;

@InjectViewState
public class DetailsPresenter extends BasePresenter<DetailsView> {
    @Inject
    NewsApi mNewsApi;

    public DetailsPresenter() {
        DiApplication.getComponent().inject(this);
    }
}
