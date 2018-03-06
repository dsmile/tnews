package pro.mdiakonov.tnews.model;

import java.util.List;

import io.reactivex.Observable;
import pro.mdiakonov.tnews.Constants;
import pro.mdiakonov.tnews.api.NewsApi;
import pro.mdiakonov.tnews.api.pojo.Title;

public class NetworkRepository {
    private NewsApi newsApi;

    public NetworkRepository(NewsApi newsApi) {
        this.newsApi = newsApi;
    }

    public Observable<List<Title>> getTitles(int page) {
        long from = (page) * Constants.PAGE_SIZE; // first page is from 0 to 10 (if page size is 10),
        long to = ((page + 1) * Constants.PAGE_SIZE); // second 10 to 20 and so on
        return newsApi.getTitles(from, to);
    }
}
