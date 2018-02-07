package pro.mdiakonov.tnews;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import pro.mdiakonov.tnews.di.ActivityComponent;
import pro.mdiakonov.tnews.di.DaggerActivityComponent;
import pro.mdiakonov.tnews.di.DbModule;
import pro.mdiakonov.tnews.di.NetModule;

public class DiApplication extends Application {
    private static ActivityComponent mComponent;

    public static RefWatcher getRefWatcher(Context context) {
        DiApplication application = (DiApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);

        mComponent = DaggerActivityComponent.builder()
        //        .appModule(new AppModule(this))
                .netModule(new NetModule())
                .dbModule(new DbModule())
                .build();
    }

    public static ActivityComponent getComponent() {
        return mComponent;
    }
}


