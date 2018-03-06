package pro.mdiakonov.tnews.view;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import pro.mdiakonov.tnews.DiApplication;
import pro.mdiakonov.tnews.common.ConnectivityReceiver;

public class BaseActivity extends MvpAppCompatActivity implements ConnectivityReceiver.ConnectivityListener{
    private BroadcastReceiver mConnectivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mConnectivityReceiver = new ConnectivityReceiver(this, getApplicationContext());
    }

    @Override
    public void onConnectionAvailable() {
        // please override by child
    }

    @Override
    public void onConnectionUnavailable() {
        // please override by child
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(mConnectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mConnectivityReceiver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DiApplication.getRefWatcher(this).watch(this);
    }
}
