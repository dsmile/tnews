package pro.mdiakonov.tnews.view;

import android.support.v7.app.AppCompatActivity;

import pro.mdiakonov.tnews.DiApplication;

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onDestroy() {
        super.onDestroy();
        DiApplication.getRefWatcher(this).watch(this);
    }
}
