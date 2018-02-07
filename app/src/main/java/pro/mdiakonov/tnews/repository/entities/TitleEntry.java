package pro.mdiakonov.tnews.repository.entities;

import android.support.annotation.Nullable;

import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteType;

import pro.mdiakonov.tnews.repository.tables.TitleTable;

@StorIOSQLiteType(table = TitleTable.TABLE)
public class TitleEntry {

    @Nullable
    @StorIOSQLiteColumn(name = TitleTable.COLUMN_ID, key = true)
    Long id;
    @StorIOSQLiteColumn(name = TitleTable.COLUMN_NEWS_ID)
    String newsId;
    @StorIOSQLiteColumn(name = TitleTable.COLUMN_NAME)
    String name;
    @StorIOSQLiteColumn(name = TitleTable.COLUMN_PUBLICATION_DATE)
    Long publicationDate;

    TitleEntry() {}

    @Nullable
    public Long getId() {
        return id;
    }

    public String getNewsId() {
        return newsId;
    }

    public String getName() {
        return name;
    }

    public Long getPublicationDate() {
        return publicationDate;
    }
}
