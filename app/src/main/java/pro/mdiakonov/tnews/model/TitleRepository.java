package pro.mdiakonov.tnews.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import pro.mdiakonov.tnews.api.pojo.Title;

public class TitleRepository {
    private NetworkRepository networkRepository;
    private DiskRepository diskRepository;

    public TitleRepository(NetworkRepository networkRepository, DiskRepository diskRepository) {
        this.networkRepository = networkRepository;
        this.diskRepository = diskRepository;
    }

    public Single<List<Title>> getTitles(int page, NetworkErrorCallback callback) {
        return diskRepository.getTitles(page).concatWith(
                networkRepository.getTitles(page)
                        .materialize()
                        .map(listNotification -> {
                            if (listNotification.isOnNext()) {
                                diskRepository.addItems(listNotification.getValue());
                            }
                            return listNotification;
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(listNotification -> {
                            if (listNotification.isOnError()) {
                                callback.onNetworkError();
                            }
                            return listNotification;
                        })
                        .filter(listNotification -> !listNotification.isOnError())
                        .dematerialize()
        ).toList().map(arrayOfLists -> {
            // todo Rx way
            List<Title> titles = arrayOfLists.isEmpty() ?
                    new ArrayList<>() :
                    new ArrayList<>(arrayOfLists.get(0));
            if (arrayOfLists.size() > 1) {
                titles.addAll(arrayOfLists.get(1));
            }

            Collections.sort(titles, (title, t1) -> t1.compareTo(title));
            return titles;
        });
    }

    public void clearCache() {
        diskRepository.clearCache();
    }
}
