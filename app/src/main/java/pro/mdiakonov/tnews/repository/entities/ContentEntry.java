package pro.mdiakonov.tnews.repository.entities;

import android.support.annotation.Nullable;

import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteType;

import pro.mdiakonov.tnews.repository.tables.ContentTable;

@StorIOSQLiteType(table = ContentTable.TABLE)
public class ContentEntry {

    @Nullable
    @StorIOSQLiteColumn(name = ContentTable.COLUMN_ID, key = true)
    Long id;
    @StorIOSQLiteColumn(name = ContentTable.COLUMN_NEWS_ID)
    String newsId;
    @StorIOSQLiteColumn(name = ContentTable.COLUMN_CONTENT)
    String content;

    ContentEntry() {}
}
