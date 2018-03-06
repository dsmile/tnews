package pro.mdiakonov.tnews.model.entities;

import android.support.annotation.Nullable;

import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio3.sqlite.annotations.StorIOSQLiteType;

import pro.mdiakonov.tnews.api.pojo.Title;
import pro.mdiakonov.tnews.model.tables.TitleTable;

@StorIOSQLiteType(table = TitleTable.TABLE)
public class TitleEntry {

    @Nullable
    @StorIOSQLiteColumn(name = TitleTable.COLUMN_ID, key = true)
    Long id;
    @StorIOSQLiteColumn(name = TitleTable.COLUMN_NEWS_ID)
    String newsId;
    @StorIOSQLiteColumn(name = TitleTable.COLUMN_NAME)
    String name;
    @StorIOSQLiteColumn(name = TitleTable.COLUMN_TEXT)
    String text;
    @StorIOSQLiteColumn(name = TitleTable.COLUMN_PUBLICATION_DATE)
    Long publicationDate;

    TitleEntry() {}

    public TitleEntry(Title title) {
        this.newsId = title.getId();
        this.name = title.getName();
        this.text = title.getText();
        this.publicationDate = title.getPublicationDate().getMilliseconds();
    }

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

    public String getText() {
        return text;
    }

    public Long getPublicationDate() {
        return publicationDate;
    }
}
