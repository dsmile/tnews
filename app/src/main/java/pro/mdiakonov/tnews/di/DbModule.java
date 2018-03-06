package pro.mdiakonov.tnews.di;

import android.app.Application;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.impl.DefaultStorIOSQLite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pro.mdiakonov.tnews.model.DbOpenHelper;
import pro.mdiakonov.tnews.model.DiskRepository;
import pro.mdiakonov.tnews.model.entities.ContentEntry;
import pro.mdiakonov.tnews.model.entities.ContentEntrySQLiteTypeMapping;
import pro.mdiakonov.tnews.model.entities.TitleEntry;
import pro.mdiakonov.tnews.model.entities.TitleEntrySQLiteTypeMapping;


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
    public SQLiteOpenHelper provideSQLiteOpenHelper(@NonNull Application application) {
        return new DbOpenHelper(application);
    }

    @Provides
    @NonNull
    @Singleton
    public DiskRepository provideDiskRepository(@NonNull StorIOSQLite storIOSQLite) {
        return new DiskRepository(storIOSQLite);
    }
}