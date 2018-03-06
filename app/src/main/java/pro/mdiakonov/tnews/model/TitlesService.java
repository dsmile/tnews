package pro.mdiakonov.tnews.model;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.queries.Query;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Single;
import pro.mdiakonov.tnews.Constants;
import pro.mdiakonov.tnews.api.NewsApi;
import pro.mdiakonov.tnews.api.pojo.Title;
import pro.mdiakonov.tnews.model.entities.TitleEntry;
import pro.mdiakonov.tnews.model.tables.TitleTable;

public class TitlesService {
    @Inject
    NewsApi newsApi;
    @Inject
    StorIOSQLite mStorIOSQLite;

    public Single<List<Title>> getTitles(int page) {
/*        return Flowable.combineLatest(
                newsApi.getTitles(0,10),
                getTitlesCache(page)).*/
return null;
    }


    private Single<List<Title>> getTitlesCache(int page) {
        return mStorIOSQLite
                .get()
                .listOfObjects(TitleEntry.class)
                .withQuery(Query.builder()
                        .table(TitleTable.TABLE)
                        .orderBy(TitleTable.COLUMN_PUBLICATION_DATE)
                        .limit((page * Constants.PAGE_SIZE))
                        .build())
                .prepare()
                .asRxFlowable(BackpressureStrategy.BUFFER)
                .flatMapIterable(list -> list)
                .map(item -> new Title(item))
                .toList();
    }

}
