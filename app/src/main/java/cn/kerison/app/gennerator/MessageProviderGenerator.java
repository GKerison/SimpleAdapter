package cn.kerison.app.gennerator;

import cn.kerison.adapter.BindProvider;
import cn.kerison.adapter.MultiProviderGenerator;
import cn.kerison.app.model.DataItem;
import cn.kerison.app.provider.HeaderProvider;
import cn.kerison.app.provider.ImageProvider;
import cn.kerison.app.provider.OtherProvider;
import cn.kerison.app.provider.TextProvider;

/**
 * Created by k on 2016/11/18.
 */

public class MessageProviderGenerator implements MultiProviderGenerator<DataItem> {

    @Override
    public int genIdentifier(final DataItem data, final int position) {
        if (position == 0) {
            return DataItem.TYPE_HEADER;
        } else {
            return data.type;
        }
    }

    //如果分类之间差别不大，不需要划分特别清楚，可以直接使用SingleAdapter或者通过构造传参到Provider中，在Provider中解析即可。
    @Override
    public BindProvider registerBindProvider(final int identifier) {
        BindProvider provider;
        switch (identifier) {
            case DataItem.TYPE_HEADER:
                provider = new HeaderProvider();
                break;

            case DataItem.TYPE_TXT:
                provider = new TextProvider();

                break;
            case DataItem.TYPE_IMAGE:
                provider = new ImageProvider();

                break;

            case DataItem.TYPE_OTHER:
                provider = new OtherProvider();
                break;

            default:
                throw new IllegalArgumentException("Can't provider for identifier: " + identifier);
        }

        return provider;
    }
}
