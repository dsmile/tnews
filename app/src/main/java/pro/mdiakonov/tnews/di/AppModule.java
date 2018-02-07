package pro.mdiakonov.tnews.di;

import android.app.Application;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio3.sqlite.StorIOSQLite;
import com.pushtorefresh.storio3.sqlite.impl.DefaultStorIOSQLite;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @NonNull
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

/*
    @Provides
    @NonNull
    public ObservableUsersCache provideObservableUsersCache(Application context, StorIOSQLite storIOSQLite) {
        return new ObservableUsersCache(context, storIOSQLite);
    }*/
}