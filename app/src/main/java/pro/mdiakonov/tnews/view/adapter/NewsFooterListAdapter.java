package pro.mdiakonov.tnews.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import pro.mdiakonov.tnews.api.pojo.Title;

public class NewsFooterListAdapter extends FirstWinsAdapter {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int NUMBER_OF_FOOTERS = 1;
    private final ShowDetailsCallback showDetailsCallback;
    private final RetryCallback retryCallback;

    @NetworkState.State
    private int networkState = NetworkState.LOADING;

    public NewsFooterListAdapter(ShowDetailsCallback showDetailsCallback, RetryCallback retryCallback) {
        this.showDetailsCallback = showDetailsCallback;
        this.retryCallback = retryCallback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return FooterViewHolder.create(parent, retryCallback);
        } else {
            return ItemViewHolder.create(parent, showDetailsCallback);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == (getItemCount() - 1)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void setNetworkState(@NetworkState.State int newNetworkState) {
        if (this.networkState != newNetworkState) {
            this.networkState = newNetworkState;
            notifyItemChanged(getItemCount() - 1);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ITEM) {
            ((ItemViewHolder) holder).bind(items.get(position));
        } else {
            ((FooterViewHolder) holder).bind(networkState);
        }
    }

    @Override
    public int getItemCount() {
        return (items.size() + NUMBER_OF_FOOTERS);
    }

    @Override
    public void setItems(final List<Title> newItems) {
        if (items.isEmpty()) {
            // trick to prevent scroll on initial load
            items.addAll(newItems);
            notifyDataSetChanged();
        } else {
            super.setItems(newItems);
        }
    }
}
