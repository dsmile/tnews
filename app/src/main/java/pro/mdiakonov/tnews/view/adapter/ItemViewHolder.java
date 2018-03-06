package pro.mdiakonov.tnews.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pro.mdiakonov.tnews.R;
import pro.mdiakonov.tnews.api.pojo.Title;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private Title content;

    ItemViewHolder(View itemView, ShowDetailsCallback showDetailsCallback) {
        super(itemView);
        itemView.setOnClickListener(view -> showDetailsCallback.onShowDetails(content.getId()));
    }

    static ItemViewHolder create(ViewGroup parent, ShowDetailsCallback showDetailsCallback) {
        return new ItemViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title, parent, false),
                showDetailsCallback);
    }

    void bind(Title content) {
        this.content = content;
        if (content.getText() != null) {
            final Spanned result;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                result = Html.fromHtml("#" + content.getId() + "-" + content.getText(), Html.FROM_HTML_MODE_COMPACT);
            } else {
                result = Html.fromHtml(content.getText());
            }
            ((TextView) itemView).setText(result);
        }
    }
}