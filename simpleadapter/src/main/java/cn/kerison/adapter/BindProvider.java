package cn.kerison.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.kerison.adapter.utils.ViewHolderHelper;

/**
 * Created by k on 2016/11/18.
 */

public interface BindProvider<T> {

    /**
     * 创建View
     * @param layoutInflater
     * @param parent
     * @return
     */
    View createView(final LayoutInflater layoutInflater, final ViewGroup parent);


    /**
     * 绑定数据
     * @param multiAdapter
     * @param helper
     * @param data
     * @param position
     */
    void bind(final MultiAdapter multiAdapter,final ViewHolderHelper helper, final T data, final int position);
}
