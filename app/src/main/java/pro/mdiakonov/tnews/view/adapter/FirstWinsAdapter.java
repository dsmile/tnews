package pro.mdiakonov.tnews.view.adapter;

import android.os.Handler;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pro.mdiakonov.tnews.api.pojo.Title;

abstract class FirstWinsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<Title> items = new ArrayList<>();
    boolean operationPending;

    // This method does the heavy lifting of
    // pushing the work to the background thread
    void updateItemsInternal(final List<Title> newItems) {
        final List<Title> oldItems = new ArrayList<>(this.items);

        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final DiffUtil.DiffResult diffResult =
                        DiffUtil.calculateDiff(new TitlesDiffCallback(oldItems, newItems));
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        applyDiffResult(newItems, diffResult);
                    }
                });

            }
        }).start();
    }

    protected void dispatchUpdates(List<Title> newItems,
                                   DiffUtil.DiffResult diffResult) {
        diffResult.dispatchUpdatesTo(this);
        items.clear();
        items.addAll(newItems);
    }

    public void setItems(final List<Title> newItems) {
        if (operationPending) {
            return;
        }
        operationPending = true;
        updateItemsInternal(newItems);
    }

    protected void applyDiffResult(List<Title> newItems,
                                   DiffUtil.DiffResult diffResult) {
        dispatchUpdates(newItems, diffResult);
        operationPending = false;
    }

}