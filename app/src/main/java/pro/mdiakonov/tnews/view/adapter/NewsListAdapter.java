package pro.mdiakonov.tnews.view.adapter;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pro.mdiakonov.tnews.api.pojo.Title;

@Deprecated
public class NewsListAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int NUMBER_OF_FOOTERS = 1;
    private final ShowDetailsCallback showDetailsCallback;

    private List<Title> items = new ArrayList<>();
    @NetworkState.State
    private int networkState = NetworkState.LOADING;

    public NewsListAdapter(ShowDetailsCallback showDetailsCallback) {
        this.showDetailsCallback = showDetailsCallback;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ItemViewHolder.create(parent, showDetailsCallback);
    }

    public void setNetworkState(@NetworkState.State int newNetworkState) {
/*        if (this.networkState != newNetworkState) {
            this.networkState = newNetworkState;
            //todo
            notifyItemChanged(getItemCount() - 1);
        }*/
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return (items.size());
    }

    public void setItems(List<Title> items) {
        // compute diffs
        final TitlesDiffCallback diffCallback = new TitlesDiffCallback(this.items, items);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.items.clear();
        this.items.addAll(items);

        diffResult.dispatchUpdatesTo(this); // calls adapter's notify methods after diff is computed
    }

}
