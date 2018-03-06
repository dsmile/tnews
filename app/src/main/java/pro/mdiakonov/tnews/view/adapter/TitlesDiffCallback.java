package pro.mdiakonov.tnews.view.adapter;

import android.support.v7.util.DiffUtil;

import java.util.List;

import pro.mdiakonov.tnews.api.pojo.Title;

public class TitlesDiffCallback extends DiffUtil.Callback {

    private List<Title> mOldList;
    private List<Title> mNewList;

    public TitlesDiffCallback(List<Title> oldList, List<Title> newList) {
        this.mOldList = oldList;
        this.mNewList = newList;
    }
    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        // add a unique ID property on Title and expose a getId() method
        return mOldList.get(oldItemPosition).getId().equals(mNewList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Title oldTitle = mOldList.get(oldItemPosition);
        Title newTitle = mNewList.get(newItemPosition);
        return oldTitle.getText().equals(newTitle.getText());
    }
}
