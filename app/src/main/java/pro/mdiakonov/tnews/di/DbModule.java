package pro.mdiakonov.tnews.di;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.impl.DefaultStorIOSQLite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pro.mdiakonov.tnews.repository.DbOpenHelper;
import pro.mdiakonov.tnews.repository.entities.ContentEntry;
import pro.mdiakonov.tnews.repository.entities.ContentEntrySQLiteTypeMapping;
import pro.mdiakonov.tnews.repository.entities.TitleEntry;
import pro.mdiakonov.tnews.repository.entities.TitleEntrySQLiteTypeMapping;

@Module
public class DbModule {
    @Provides
    @NonNull
    @Singleton
    public StorIOSQLite providesStorIOSQLiteCache(@NonNull SQLiteOpenHelper sqLiteOpenHelper) {
        return DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(sqLiteOpenHelper)
                .addTypeMapping(TitleEntry.class, new TitleEntrySQLiteTypeMapping())
                .addTypeMapping(ContentEntry.class, new ContentEntrySQLiteTypeMapping())
                .build();
    }

    @Provides
    @NonNull
    @Singleton
    public SQLiteOpenHelper provideSQLiteOpenHelper(@NonNull Context context) {
        return new DbOpenHelper(context);
    }
}