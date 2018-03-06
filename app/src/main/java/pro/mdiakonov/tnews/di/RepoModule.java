package pro.mdiakonov.tnews.di;

import android.support.annotation.NonNull;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pro.mdiakonov.tnews.model.DiskRepository;
import pro.mdiakonov.tnews.model.NetworkRepository;
import pro.mdiakonov.tnews.model.TitleRepository;


@Module
public class RepoModule {
    @Provides
    @NonNull
    @Singleton
    public TitleRepository provideRepoModule(@NonNull NetworkRepository networkRepository,
                                             @NonNull DiskRepository diskRepository) {
        return new TitleRepository(networkRepository, diskRepository);
    }
}