package cn.kerison.adapter;

/**
 * Created by k on 2016/11/18.
 */

public interface MultiProviderGenerator<T> {

    /**
     * 根据数据或者行号生成唯一标识符
     * @param data
     * @param position
     * @return
     */
    int genIdentifier(T data,int position);

    /**
     * 针对每种标识符注册不同的绑定者
     * @param identifier
     * @return
     */
    BindProvider registerBindProvider(int identifier);
}
