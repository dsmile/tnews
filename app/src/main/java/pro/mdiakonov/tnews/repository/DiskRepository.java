package pro.mdiakonov.tnews.repository;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.queries.DeleteQuery;
import com.pushtorefresh.storio3.sqlite.queries.Query;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.Single;
import pro.mdiakonov.tnews.Constants;
import pro.mdiakonov.tnews.DiApplication;
import pro.mdiakonov.tnews.api.NewsApi;
import pro.mdiakonov.tnews.api.pojo.Title;
import pro.mdiakonov.tnews.repository.entities.TitleEntry;
import pro.mdiakonov.tnews.repository.tables.TitleTable;

public class DiskRepository {
    @Inject
    StorIOSQLite mStorIOSQLite;

    public DiskRepository() {
        DiApplication.getComponent().inject(this);
    }

    public Observable<List<Title>> getTitlesPage(int page) {

    }

/*
    public Observable<List<Title>> getObservable() {
        Observable<List<Title>> initObservable =
                Observable.fromCallable(this::getAllUsersFromDb);
        return initObservable;
    }
*/

    private Single<List<Title>> getAll() {
        return mStorIOSQLite
                .get()
                .listOfObjects(TitleEntry.class)
                .withQuery(Query.builder()
                        .table(TitleTable.TABLE)
                        .build())
                .prepare()
                .asRxFlowable(BackpressureStrategy.BUFFER)
                .flatMapIterable(list -> list)
                .map(item -> new Title(item))
                .toList();
    }

    public void insertUserList(List<Title> items) {
        mStorIOSQLite
                .delete()
                .byQuery(DeleteQuery.builder()
                        .table(TitleTable.TABLE)
                        .build())
                .prepare()
                .executeAsBlocking();
        mStorIOSQLite
                .put()
                .objects(items)
                .prepare()
                .executeAsBlocking();
    }
}
