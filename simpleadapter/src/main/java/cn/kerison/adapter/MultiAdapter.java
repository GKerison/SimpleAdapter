package cn.kerison.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.kerison.adapter.utils.ViewHolderHelper;

/**
 * Created by k on 2016/11/18.
 */

public class MultiAdapter<T> extends SingleAdapter<T> {

    private SparseArray<BindProvider> mTypeProvider = new SparseArray<>();
    private MultiProviderGenerator<T> mMultiProviderGenerator;

    public MultiAdapter(final Context context) {
        super(context);
    }

    public MultiAdapter(final Context context,MultiProviderGenerator<T> multiProviderGenerator) {
        super(context);
        mMultiProviderGenerator = multiProviderGenerator;
    }

    public void setMultiProviderGenerator(final MultiProviderGenerator<T> multiProviderGenerator) {
        mMultiProviderGenerator = multiProviderGenerator;
    }

    @Override
    protected View createView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final int viewType) {
        return mTypeProvider.get(viewType).createView(layoutInflater, viewGroup);
    }

    @Override
    protected void bind(final ViewHolderHelper helper, final T data, final int position) {
        int identifier = mMultiProviderGenerator.genIdentifier(data, position);
        mTypeProvider.get(identifier).bind(this,helper,data,position);
    }

    @Override
    public int getItemViewType(final int position) {
        if (mMultiProviderGenerator == null) {
            throw new IllegalArgumentException(String.format("There must provider a MultiProviderGenerator when use MultiAdapter !!! "));
        }

        int identifier = mMultiProviderGenerator.genIdentifier(mDataList.get(position), position);
        if (mTypeProvider.indexOfKey(identifier) < 0) {

            BindProvider provider = mMultiProviderGenerator.registerBindProvider(identifier);
            if (provider == null) {
                throw new IllegalArgumentException(String.format("MultiProviderGenerator's registerBindProvider(identifier) must handler all the identifier generated in  genIdentifier(data,position)!!! "));
            }
            mTypeProvider.put(identifier, provider);
        }

        return identifier;
    }
}
