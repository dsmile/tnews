package pro.mdiakonov.tnews.repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import pro.mdiakonov.tnews.api.NewsApi;
import pro.mdiakonov.tnews.api.pojo.Title;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class TitleRepository {
    @Inject
    NewsApi newsApi;

    public Observable<List<Title>> getTitles(int page) {
        return Observable.concatArrayEager(
                getTitlesFromDb(),
                newsApi.getTitles(0, 10)
                        .materialize()
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(notification -> {
                            if (notification.isOnError()) {
                                // todo handle Error
                            }
                            return notification;
                        })
                        .filter(notification -> !notification.isOnError())
                        .dematerialize()
                        .debounce(400, MILLISECONDS)
             )
    }
}
