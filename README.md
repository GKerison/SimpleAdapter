# SimpleAdapter
SimpleAdapter是针对Android RecyclerView提供快速构建的工具包。
- 简单快速构建RecycleView
- 简化ViewHolder
- 针对多中布局的列表提供MultiAdapter
- 可扩展SingleAdapter、MultiAdapter 实现自己的逻辑

[![](https://jitpack.io/v/GKerison/SimpleAdapter.svg)](https://jitpack.io/#GKerison/SimpleAdapter)

## 安装

- 添加仓库
```
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```
- 添加依赖
```
dependencies {
	        compile 'com.github.GKerison:SimpleAdapter:1.0.0'
	}
```

## 数据绑定

- 数据绑定使用ViewHolderHelper
 - getItemView
 - getView
 - bindText
 - bindTextRes
 - bindTextColor
 - bindBackgroundColor
 - bindImageRes
 - bindVisibility
 - 其他操作可通过getView单独处理


- 支持链式调用
```
helper.bindText(text,ids).bindTextColor(color,ids)....
```
- 支持多id同时设置
```
helper.bindVisibility(View.GONE,id1,id2,id3...);
```

## 数据的更删改
提供基本的数据变更
- SingleAdapter常用方法
  - getDataList
  - getItem
  - getPosition
  - insertItem
  - insertItems
  - updateItem
  - removeItem
  - clear
	- replaceItems
	- moveItem
	- exchangeItem
- 更为复杂的获取数据后修改，再Notify。

## 单布局使用
- 继承SingleAdapter就OK了，
```
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
```

## 多布局使用
MultiAdapter是继承SingleAdapter配合Provider实现的
- 创建MultiAdapter
```
mMultiAdapter = new MultiAdapter<>(getActivity());
mMultiAdapter.setMultiProviderGenerator(new MessageProviderGenerator());
mRecyclerView.setAdapter(mMultiAdapter);
```
- 实现Provider
```
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
```

- 配置ProviderGenerator
如果只提供一个Provider也可实现单布局，不过没有直接继承SingleAdapter方便
```
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
```
