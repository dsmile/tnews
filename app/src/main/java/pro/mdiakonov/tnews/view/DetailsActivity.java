package pro.mdiakonov.tnews.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import org.w3c.dom.Text;

import pro.mdiakonov.tnews.R;
import pro.mdiakonov.tnews.api.pojo.Content;
import pro.mdiakonov.tnews.presenter.DetailsPresenter;

public class DetailsActivity extends BaseActivity implements DetailsView {

    public static final String NEWS_ID_EXTRA = "NEWS_ID_EXTRA";

    @InjectPresenter
    DetailsPresenter mDetailsPresenter;
    private View progressView;
    private TextView contentText;
    private TextView errorText;

    public static void launch(String newsId, Context context) {
        final Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(NEWS_ID_EXTRA, newsId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressView = findViewById(R.id.progress);
        contentText = findViewById(R.id.content_text);
        contentText.setMovementMethod(LinkMovementMethod.getInstance());
        errorText = findViewById(R.id.error_text);

        String newsId = getIntent().getExtras().getString(NEWS_ID_EXTRA, "");
        if (newsId.isEmpty()) {
            throw new RuntimeException("newsId is expected");
        }

        mDetailsPresenter.onLoadDetails(newsId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showError(int messageResId) {
        errorText.setText(messageResId);
        setRefreshing(false);
        errorText.setVisibility(View.VISIBLE);
    }

    private void hideError() {
        errorText.setVisibility(View.GONE);
    }

    @Override
    public void refresh(Content content) {
        errorText.setVisibility(View.GONE);
        final Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(content.getContent(), Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(content.getContent());
        }
        contentText.setText(result);
        setRefreshing(false);
        hideError();
    }

    @Override
    public void setRefreshing(boolean isRefreshing) {
        progressView.setVisibility(isRefreshing ? View.VISIBLE : View.GONE);
    }
}
