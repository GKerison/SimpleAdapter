package cn.kerison.adapter.utils;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by k on 2016/11/17.
 */

public class ViewHolderHelper extends RecyclerView.ViewHolder {


    private SparseArray<View> mViewCaches = new SparseArray<>();
    private View mItemView;

    public static ViewHolderHelper create(final View itemView){
        return new ViewHolderHelper(itemView);
    }

    public ViewHolderHelper(final View itemView) {
        super(itemView);
        if (itemView == null) {
            throw new IllegalArgumentException("itemView may not be null");
        }
        mItemView = itemView;
    }

    /**
     * 获取ItemView
     * @return
     */
    public View getItemView(){
        return mItemView;
    }


    /**
     * 获取View
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViewCaches.get(viewId);
        if (view == null) {
            view = mItemView.findViewById(viewId);
            mViewCaches.put(viewId, view);
        }
        return (T) view;
    }


    // -----------------  Common Action  ----------------

    /**
     * 设置文本
     * @param text
     * @param viewIds
     * @return
     */
    public ViewHolderHelper bindText(CharSequence text,int ...viewIds) {
        for (int viewId : viewIds){
            ((TextView)getView(viewId)).setText(text);
        }
        return this;
    }

    /**
     * 设置文本资源
     * @param textRes 资源ID
     * @param viewIds
     * @return
     */
    public ViewHolderHelper bindTextRes(int textRes,int ...viewIds) {
        for (int viewId : viewIds){
            ((TextView)getView(viewId)).setText(textRes);
        }
        return this;
    }


    /**
     * 设置文本颜色
     * @param color
     * @param viewIds
     * @return
     */
    public ViewHolderHelper bindTextColor(int color, int... viewIds) {
        for (int viewId : viewIds){
            ((TextView)getView(viewId)).setTextColor(color);
        }
        return this;
    }

    /**
     * 设置背景颜色
     * @param color
     * @param viewIds
     * @return
     */
    public ViewHolderHelper bindBackgroundColor(int color, int... viewIds) {
        for (int viewId : viewIds){
            getView(viewId).setBackgroundColor(color);
        }
        return this;
    }

    /**
     * 设置图片资源
     * @param imageRes 资源ID
     * @param viewIds
     * @return
     */
    public ViewHolderHelper bindImageRes(int imageRes, int... viewIds) {
        for (int viewId : viewIds){
            ((ImageView)getView(viewId)).setImageResource(imageRes);
        }
        return this;
    }

    /**
     * 设置可见性
     * @param visibility
     * @param viewIds
     * @return
     */
    public ViewHolderHelper bindVisibility(int visibility, int... viewIds) {
        for (int viewId : viewIds){
            getView(viewId).setVisibility(visibility);
        }
        return this;
    }




}
