package cn.kerison.app.provider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.kerison.adapter.BindProvider;
import cn.kerison.adapter.MultiAdapter;
import cn.kerison.adapter.utils.ViewHolderHelper;
import cn.kerison.app.R;
import cn.kerison.app.model.DataItem;

/**
 * Created by k on 2016/11/18.
 */

public class TextProvider implements BindProvider<DataItem> {

    @Override
    public View createView(final LayoutInflater layoutInflater, final ViewGroup parent) {
        return layoutInflater.inflate(R.layout.item_text, parent, false);
    }

    @Override
    public void bind(final MultiAdapter multiAdapter, final ViewHolderHelper helper, final DataItem data, final int position) {
        helper.bindText(data.content, R.id.text_view);
    }
}
