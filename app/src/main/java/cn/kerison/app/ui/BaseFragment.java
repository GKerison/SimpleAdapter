package cn.kerison.app.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.kerison.app.R;

/**
 * Created by k on 2016/11/18.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;

    private int index = 0;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), OrientationHelper.VERTICAL));

        configAdapter();


        view.findViewById(R.id.btn_add).setOnClickListener(this);
        view.findViewById(R.id.btn_update).setOnClickListener(this);
        view.findViewById(R.id.btn_remove).setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {

            case R.id.btn_add:
                onAddAction();
                break;

            case R.id.btn_update:
                onUpdateAction();
                break;

            case R.id.btn_remove:
                onRemoveAction();
                break;
        }
    }

    protected int nextId() {
        return index++;
    }

    protected abstract void configAdapter();

    protected abstract void onAddAction();

    protected abstract void onUpdateAction();

    protected abstract void onRemoveAction();
}
