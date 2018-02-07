package pro.mdiakonov.tnews.di;

import javax.inject.Singleton;

import dagger.Component;
import pro.mdiakonov.tnews.presenter.DetailsPresenter;
import pro.mdiakonov.tnews.presenter.NewsListPresenter;
import pro.mdiakonov.tnews.repository.DiskRepository;
import pro.mdiakonov.tnews.repository.NetworkRepository;
import pro.mdiakonov.tnews.view.DetailsActivity;
import pro.mdiakonov.tnews.view.NewsListActivity;

@Singleton
@Component(modules={AppModule.class, NetModule.class, DbModule.class})
public interface ActivityComponent {
    void inject(NewsListActivity activity);
    void inject(DetailsActivity activity);
    //void inject(ObservableUsers cached);
    void inject(DetailsPresenter presenter);
    void inject(NewsListPresenter presenter);
    void inject(NetworkRepository repository);
    void inject(DiskRepository repository);
}