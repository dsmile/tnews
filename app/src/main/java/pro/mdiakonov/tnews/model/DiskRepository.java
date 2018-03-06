package pro.mdiakonov.tnews.model;

import android.util.Log;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.queries.DeleteQuery;
import com.pushtorefresh.storio3.sqlite.queries.Query;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import pro.mdiakonov.tnews.Constants;
import pro.mdiakonov.tnews.api.pojo.Title;
import pro.mdiakonov.tnews.model.entities.TitleEntry;
import pro.mdiakonov.tnews.model.tables.TitleTable;

public class DiskRepository {
    private StorIOSQLite mStorIOSQLite;

    public DiskRepository(StorIOSQLite mStorIOSQLite) {
        this.mStorIOSQLite = mStorIOSQLite;
    }

    public Observable<List<Title>> getTitles(int page) {
        if (page > 0) {
            return mStorIOSQLite
                    .get()
                    .listOfObjects(TitleEntry.class)
                    .withQuery(Query.builder()
                            .table(TitleTable.TABLE)
                            .orderBy(TitleTable.COLUMN_PUBLICATION_DATE + " DESC")
                            .limit(((page) * Constants.PAGE_SIZE))
                            .build())
                    .prepare()
                    .asRxSingle()
                    .toObservable()
                    .flatMapIterable(list -> list)
                    .map(Title::new)
                    .toList()
                    .toObservable();
        } else {
            return Observable.empty();
        }
    }

    public void clearCache() {
        // todo Get rid of blocking call
        mStorIOSQLite
                .delete()
                .byQuery(DeleteQuery.builder()
                        .table(TitleTable.TABLE)
                        .build())
                .prepare()
                .executeAsBlocking();
    }

    public void addItems(List<Title> items) {
        List<TitleEntry> entries = new ArrayList<>();
        for (Title item : items) {
            entries.add(new TitleEntry(item));
        }
        mStorIOSQLite
                .put()
                .objects(entries)
                .prepare()
                .executeAsBlocking();
    }
}
