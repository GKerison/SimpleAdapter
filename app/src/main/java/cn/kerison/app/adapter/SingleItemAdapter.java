package cn.kerison.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.kerison.adapter.SingleAdapter;
import cn.kerison.adapter.utils.ViewHolderHelper;
import cn.kerison.app.R;

/**
 * Created by k on 2016/11/18.
 */

public class SingleItemAdapter extends SingleAdapter<String> {

    public SingleItemAdapter(final Context pContext) {
        super(pContext);
    }

    @Override
    protected View createView(final LayoutInflater pLayoutInflater, final ViewGroup pParent, final int pViewType) {
        return pLayoutInflater.inflate(R.layout.item_text, pParent, false);
    }

    @Override
    protected void bind(final ViewHolderHelper pHelper, final String pData, final int pPosition) {
        pHelper.bindText(pData, R.id.text_view);
    }
}
