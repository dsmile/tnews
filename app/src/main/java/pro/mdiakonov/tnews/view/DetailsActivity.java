package pro.mdiakonov.tnews.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;

import pro.mdiakonov.tnews.R;
import pro.mdiakonov.tnews.presenter.DetailsPresenter;
import pro.mdiakonov.tnews.presenter.NewsListPresenter;

public class DetailsActivity extends BaseActivity implements DetailsView {

    @InjectPresenter
    DetailsPresenter mDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    @Override
    public void showError(int messageResId) {

    }
}
