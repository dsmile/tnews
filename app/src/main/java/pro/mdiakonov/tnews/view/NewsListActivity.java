package pro.mdiakonov.tnews.view;

import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import pro.mdiakonov.tnews.Constants;
import pro.mdiakonov.tnews.R;
import pro.mdiakonov.tnews.api.pojo.Title;
import pro.mdiakonov.tnews.presenter.NewsListPresenter;
import pro.mdiakonov.tnews.view.adapter.NetworkState;
import pro.mdiakonov.tnews.view.adapter.NewsFooterListAdapter;
import pro.mdiakonov.tnews.view.adapter.RetryCallback;
import pro.mdiakonov.tnews.view.adapter.ShowDetailsCallback;

public class NewsListActivity extends BaseActivity implements NewsListView, ShowDetailsCallback, RetryCallback {

    @InjectPresenter
    NewsListPresenter mNewsListPresenter;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView.LayoutManager layoutManager;
    private NewsFooterListAdapter newsListAdapter;

    // todo Determine end of the list
    private boolean mEndOfList;
    private boolean isRefreshing;

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
            int totalItemCount = recyclerView.getLayoutManager().getItemCount();
            int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

            if (!isRefreshing && !mEndOfList) {
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
        setContentView(R.layout.activity_list);

        mEndOfList = false;
        isRefreshing = false;

        mRecyclerView = findViewById(R.id.list);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh);

        layoutManager = new LinearLayoutManager(this);
        newsListAdapter = new NewsFooterListAdapter(this, this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(newsListAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);
        mRecyclerView.setHasFixedSize(true);

        mSwipeRefreshLayout.setOnRefreshListener(() -> mNewsListPresenter.onRefresh());
    }

    @Override
    public void onShowDetails(String newsId) {
        mNewsListPresenter.onShowDetails(newsId);
    }

    @Override
    public void showError(int messageResId) {
        newsListAdapter.setNetworkState(NetworkState.FAIL);
    }

    @Override
    public void showDetails(String newsId) {
        DetailsActivity.launch(newsId, this);
    }

    @Override
    public void setRefreshing(boolean isRefreshing) {
        if (this.isRefreshing != isRefreshing) {
            if (!isRefreshing) {
                mSwipeRefreshLayout.setRefreshing(false);
            } else {
                newsListAdapter.setNetworkState(NetworkState.LOADING);
            }
            this.isRefreshing = isRefreshing;
        }
    }

    @Override
    public void refreshPageItems(List<Title> items) {
        Parcelable recyclerViewState = mRecyclerView.getLayoutManager().onSaveInstanceState();
        newsListAdapter.setItems(items);
        mRecyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
    }

    @Override
    public void stopPagination() {
        mEndOfList = true;
        newsListAdapter.setNetworkState(NetworkState.NO_MORE);
    }

    @Override
    public void onRetryLoad() {
        mNewsListPresenter.onRetryLoad();
    }
}
