package pro.mdiakonov.tnews.view;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import javax.inject.Inject;

import pro.mdiakonov.tnews.Constants;
import pro.mdiakonov.tnews.DiApplication;
import pro.mdiakonov.tnews.R;
import pro.mdiakonov.tnews.api.NewsApi;
import pro.mdiakonov.tnews.api.pojo.Title;
import pro.mdiakonov.tnews.presenter.NewsListPresenter;

public class NewsListActivity extends BaseActivity implements NewsListView {

    private static final String NEWS_ID_EXTRA = "NEWS_ID_EXTRA";

    @Inject
    NewsApi newsApi;

    @InjectPresenter
    NewsListPresenter mNewsListPresenter;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView.LayoutManager layoutManager;

    // todo
    private boolean mEndOfList;

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
            int totalItemCount = recyclerView.getLayoutManager().getItemCount();
            int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

            if (!mSwipeRefreshLayout.isRefreshing() && !mEndOfList) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - Constants.PAGINATION_MARGIN
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= Constants.PAGE_SIZE) {
                    mNewsListPresenter.onLoadNextPage();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DiApplication.getComponent().inject(this);
        setContentView(R.layout.activity_list);

        mEndOfList = false;

        mRecyclerView = findViewById(R.id.list);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh);

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);
        mRecyclerView.setHasFixedSize(true);

        mSwipeRefreshLayout.setOnRefreshListener(() -> mNewsListPresenter.onRefresh());
    }

    @Override
    public void showError(int messageResId) {
        Toast.makeText(this, getString(messageResId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDetails(long newsId) {
        final Intent intent = new Intent(this, DetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(NEWS_ID_EXTRA, newsId);

        startActivity(intent);
    }

    @Override
    public void setRefreshing(boolean isRefreshing) {
        // todo
    }

    @Override
    public void setEndOfList(boolean isEndOfList) {

    }

    @Override
    public void refreshPageItems(List<Title> items) {

    }

    @Override
    public void stopPagination() {

    }
}
