package pro.mdiakonov.tnews.model.tables;

import android.support.annotation.NonNull;

public class TitleTable {

    @NonNull
    public static final String TABLE = "titles";

    @NonNull
    public static final String COLUMN_ID = "_id";

    @NonNull
    public static final String COLUMN_NEWS_ID = "news_id";

    @NonNull
    public static final String COLUMN_NAME = "name";

    @NonNull
    public static final String COLUMN_TEXT = "title_text";

    @NonNull
    public static final String COLUMN_PUBLICATION_DATE = "publication_date";

    public static final String COLUMN_ID_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_ID;
    public static final String COLUMN_NEWS_ID_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_NEWS_ID;
    public static final String COLUMN_NAME_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_NAME;
    public static final String COLUMN_TEXT_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_TEXT;
    public static final String COLUMN_PUBLICATION_DATE_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_PUBLICATION_DATE;

    // This is just class with Meta Data, we don't need instances
    private TitleTable() {
        throw new IllegalStateException("No instances please");
    }

    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUMN_NEWS_ID + " TEXT NOT NULL, "
                + COLUMN_NAME + " TEXT NOT NULL, "
                + COLUMN_TEXT + " TEXT NOT NULL, "
                + COLUMN_PUBLICATION_DATE + " INTEGER NOT NULL"
                + ");";
    }
}