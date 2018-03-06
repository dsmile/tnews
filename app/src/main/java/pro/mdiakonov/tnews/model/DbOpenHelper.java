package pro.mdiakonov.tnews.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import pro.mdiakonov.tnews.model.tables.ContentTable;
import pro.mdiakonov.tnews.model.tables.TitleTable;

public class DbOpenHelper extends SQLiteOpenHelper {

    public DbOpenHelper(@NonNull Context context) {
        super(context, "cache_db", null, 1);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL(TitleTable.getCreateTableQuery());
        db.execSQL(ContentTable.getCreateTableQuery());
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        // todo
    }
}
