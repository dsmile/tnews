package pro.mdiakonov.tnews.model.tables;

import android.support.annotation.NonNull;

public class ContentTable {

    @NonNull
    public static final String TABLE = "content";

    @NonNull
    public static final String COLUMN_ID = "_id";

    @NonNull
    public static final String COLUMN_NEWS_ID = "news_id";

    @NonNull
    public static final String COLUMN_CONTENT = "content";


    public static final String COLUMN_ID_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_ID;
    public static final String COLUMN_NEWS_ID_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_NEWS_ID;
    public static final String COLUMN_CONTENT_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_CONTENT;

    // This is just class with Meta Data, we don't need instances
    private ContentTable() {
        throw new IllegalStateException("No instances please");
    }

    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUMN_NEWS_ID + " TEXT NOT NULL, "
                + COLUMN_CONTENT + " TEXT NOT NULL"
                + ");";
    }
}