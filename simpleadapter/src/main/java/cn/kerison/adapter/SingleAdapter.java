package cn.kerison.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.kerison.adapter.utils.ViewHolderHelper;

/**
 * Created by k on 2016/11/17.
 */

public abstract class SingleAdapter<T> extends RecyclerView.Adapter<ViewHolderHelper> {

    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    protected List<T> mDataList;

    public SingleAdapter(Context pContext) {
        mDataList = new ArrayList<>();
        mContext = pContext;
        mLayoutInflater = LayoutInflater.from(pContext);
    }

    public List<T> getDataList() {
        return mDataList;
    }

    @Override
    public ViewHolderHelper onCreateViewHolder(final ViewGroup pParent, final int pViewType) {
        return ViewHolderHelper.create(createView(mLayoutInflater, pParent, pViewType));
    }

    @Override
    public void onBindViewHolder(final ViewHolderHelper pHelper, final int pPosition) {
        bind(pHelper, mDataList.get(pPosition), pPosition);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    //---------------常用操作--------------

    /**
     * 返回某个位置的数据
     */
    public T getItem(int position) {
        if (position < mDataList.size()) {
            return mDataList.get(position);
        }
        return null;
    }

    /**
     * 返回某个数据的位置
     */
    public int getPosition(T data) {
        return mDataList.indexOf(data);
    }

    /**
     * 数据末尾插入一条数据
     */
    public void insertItem(T data) {
        mDataList.add(data);
        notifyItemInserted(mDataList.size() - 1);
    }


    /**
     * 在某个位置插入一条数据
     */
    public void insertItem(int position, T data) {
        mDataList.add(position, data);
        notifyItemInserted(position);
    }

    /**
     * 同时插入多条数据
     *
     * @param items 可变数组
     */
    public void insertItems(T... items) {
        insertItems(Arrays.asList(items));
    }

    /**
     * 同时插入多条数据
     *
     * @param dataList 数据List
     */
    public void insertItems(List<T> dataList) {
        if (checkNoDataList(dataList)) {
            return;
        }
        mDataList.addAll(dataList);
        notifyItemRangeInserted(mDataList.size() - dataList.size(), dataList.size());
    }

    /**
     * 某个位置插入多条数据
     */
    public void insertItems(int position, List<T> dataList) {
        if (checkNoDataList(dataList)) {
            return;
        }
        mDataList.addAll(position, dataList);
        notifyItemRangeInserted(position, dataList.size());
    }


    /**
     * 更新某个位置的数据
     */
    public void updateItem(int position, T data) {
        mDataList.set(position, data);
        notifyItemChanged(position);
    }

    /**
     * 删除某条数据
     */
    public void removeItem(T pData) {
        int p = mDataList.indexOf(pData);
        if (p != -1) {
            removeItem(p);
        }
    }

    /**
     * 根据位置删除数据
     */
    public void removeItem(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
    }


    /**
     * 移动数据位置
     * @param fromPosition
     * @param toPosition
     */
    public void moveItem(int fromPosition,int toPosition){
        if (fromPosition < mDataList.size()) {
            T item = mDataList.remove(fromPosition);
            toPosition = Math.min(mDataList.size(), Math.max(0,toPosition));
            mDataList.add(toPosition, item);
            notifyItemMoved(fromPosition,toPosition);
        }
    }

    /**
     * 移动交换两个位置的数据
     * @param p1
     * @param p2
     */
    public void exchangeItem(int p1, int p2) {
        int start = Math.min(p1, p2);
        int end = Math.max(p1, p2);
        moveItem(end,start);
        moveItem(start+1,end+1);
    }

    /**
     * 替换所有数据
     *
     * @param items
     */
    public void replaceItems(T ...items) {
        replaceItems(Arrays.asList(items));
    }

    /**
     * 替换所有数据 <=> 清空再添加 用于重新刷新等操作
     * @param dataList
     */
    public void replaceItems(List<T> dataList) {
        mDataList.clear();
        if (dataList != null) {
            mDataList.addAll(dataList);
        }
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    private boolean checkNoDataList(List dataList) {
        return dataList == null || dataList.size() == 0;
    }

    //--------------其他操作通过获取DataList配合Adapter完成----------

    /**
     * 创建View
     */
    protected abstract View createView(final LayoutInflater pLayoutInflater, final ViewGroup pParent, final int pViewType);

    /**
     * 绑定数据
     */
    protected abstract void bind(final ViewHolderHelper pHelper, final T pData, final int pPosition);
}
