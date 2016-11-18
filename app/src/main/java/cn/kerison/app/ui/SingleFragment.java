package cn.kerison.app.ui;

import cn.kerison.adapter.SingleAdapter;
import cn.kerison.app.adapter.SingleItemAdapter;

/**
 * Created by k on 2016/11/18.
 */

public class SingleFragment extends BaseFragment {

    private SingleAdapter mSingleAdapter;

    @Override
    protected void configAdapter() {
        mSingleAdapter = new SingleItemAdapter(getActivity());
        mRecyclerView.setAdapter(mSingleAdapter);
    }

    @Override
    protected void onAddAction() {
        mSingleAdapter.insertItem(0, "item" + nextId());
        mSingleAdapter.insertItems("1", "2", "3");
    }

    @Override
    protected void onUpdateAction() {
        if (mSingleAdapter.getItemCount() > 1) {
            mSingleAdapter.updateItem(1, "WTF");
        }
    }

    @Override
    protected void onRemoveAction() {
        if (mSingleAdapter.getItemCount() > 1) {
            mSingleAdapter.removeItem(1);
        }
    }
}
