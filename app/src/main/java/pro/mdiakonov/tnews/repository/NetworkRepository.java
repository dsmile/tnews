package pro.mdiakonov.tnews.repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import pro.mdiakonov.tnews.Constants;
import pro.mdiakonov.tnews.DiApplication;
import pro.mdiakonov.tnews.api.NewsApi;
import pro.mdiakonov.tnews.api.pojo.Title;

public class NetworkRepository {
    @Inject
    NewsApi newsApi;

    public NetworkRepository() {
        DiApplication.getComponent().inject(this);
    }

    public Observable<List<Title>> getTitlesPage(int page) {
        long first = (page) * Constants.PAGE_SIZE;
        long last = (page + 1) * Constants.PAGE_SIZE;
        return newsApi.getTitles(first, last);
    }
}
