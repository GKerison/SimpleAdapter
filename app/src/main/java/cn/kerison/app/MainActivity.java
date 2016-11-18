package cn.kerison.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.kerison.adapter.SingleAdapter;
import cn.kerison.app.adapter.SingleItemAdapter;
import cn.kerison.app.ui.MultiFragment;
import cn.kerison.app.ui.SingleFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(final int position) {
                if (position == 0) {
                    return new SingleFragment();
                }else{
                    return new MultiFragment();
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }

}
