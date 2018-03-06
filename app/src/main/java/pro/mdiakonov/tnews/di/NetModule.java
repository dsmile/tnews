package pro.mdiakonov.tnews.di;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pro.mdiakonov.tnews.BuildConfig;
import pro.mdiakonov.tnews.api.NewsApi;
import pro.mdiakonov.tnews.api.UnwrapConverterFactory;
import pro.mdiakonov.tnews.model.NetworkRepository;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static pro.mdiakonov.tnews.Constants.BASE_URL;
import static pro.mdiakonov.tnews.Constants.CONNECT_TIMEOUT_SEC;
import static pro.mdiakonov.tnews.Constants.READ_WRITE_TIMEOUT_SEC;

@Module
public class NetModule {
    @Provides
    @NonNull
    @Singleton
    public HttpLoggingInterceptor providesLoggingInterceptor() {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("API", message);
            }
        });
        logger.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return logger;
    }

    @Provides
    @NonNull
    @Singleton
    public OkHttpClient providesOkHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
                .readTimeout(READ_WRITE_TIMEOUT_SEC, TimeUnit.SECONDS)
                .writeTimeout(READ_WRITE_TIMEOUT_SEC, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        if (BuildConfig.DEBUG) builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }

    @Provides
    @NonNull
    @Singleton
    public NewsApi providesNewsApi(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(new UnwrapConverterFactory(GsonConverterFactory.create()))
                .build();
        return retrofit.create(NewsApi.class);
    }

    @Provides
    @NonNull
    @Singleton
    public NetworkRepository providesNetworkRepository(NewsApi newsApi) {
        return new NetworkRepository(newsApi);
    }
}