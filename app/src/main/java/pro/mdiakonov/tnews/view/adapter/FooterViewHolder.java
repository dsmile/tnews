package pro.mdiakonov.tnews.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import pro.mdiakonov.tnews.R;

import static pro.mdiakonov.tnews.view.adapter.NetworkState.NO_MORE;

public class FooterViewHolder extends RecyclerView.ViewHolder {
    private final Button retryButton;
    private ProgressBar progressBar;
    private View errorBlock;

    FooterViewHolder(View itemView, RetryCallback retryCallback) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progress);
        errorBlock = itemView.findViewById(R.id.error_block);
        retryButton = itemView.findViewById(R.id.button_retry);
        retryButton.setOnClickListener(view -> retryCallback.onRetryLoad());
    }

    static FooterViewHolder create(ViewGroup parent, RetryCallback retryCallback) {
        return new FooterViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer, parent, false),
                retryCallback);
    }

    void bind(@NetworkState.State int state) {
        if (state != NO_MORE) {
            progressBar.setVisibility(state == NetworkState.LOADING ? View.VISIBLE : View.GONE);
            errorBlock.setVisibility(state == NetworkState.FAIL ? View.VISIBLE : View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            errorBlock.setVisibility(View.GONE);
        }
    }
}