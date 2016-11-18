package cn.kerison.app.ui;

import cn.kerison.adapter.MultiAdapter;
import cn.kerison.app.R;
import cn.kerison.app.gennerator.MessageProviderGenerator;
import cn.kerison.app.model.DataItem;

/**
 * Created by k on 2016/11/18.
 */

public class MultiFragment extends BaseFragment {

    private MultiAdapter<DataItem> mMultiAdapter;

    @Override
    protected void configAdapter() {
        mMultiAdapter = new MultiAdapter<>(getActivity());
        mMultiAdapter.setMultiProviderGenerator(new MessageProviderGenerator());
        mRecyclerView.setAdapter(mMultiAdapter);
    }

    @Override
    protected void onAddAction() {
        DataItem item1 = new DataItem(DataItem.TYPE_HEADER);
        mMultiAdapter.insertItem(0, item1);

        DataItem item2 = new DataItem(DataItem.TYPE_TXT,"Hi,Boy !");
        DataItem item3 = new DataItem(DataItem.TYPE_IMAGE, R.drawable.friend4);
        DataItem item4 = new DataItem(DataItem.TYPE_OTHER);
        mMultiAdapter.insertItems(item2,item3,item4);
    }

    @Override
    protected void onUpdateAction() {
        if (mMultiAdapter.getItemCount() > 1) {
            mMultiAdapter.updateItem(1, new DataItem(DataItem.TYPE_OTHER));
        }
    }

    @Override
    protected void onRemoveAction() {
        if (mMultiAdapter.getItemCount() > 1) {
            mMultiAdapter.removeItem(1);
        }
    }

    @Override
    protected void onMoveAction() {
        if (mMultiAdapter.getItemCount() > 3) {
//            mMultiAdapter.moveItem(1, 3);
            mMultiAdapter.exchangeItem(1,3);
        }
    }
}
