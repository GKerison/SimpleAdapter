package cn.kerison.app.model;

/**
 * Created by k on 2016/11/18.
 */

public class DataItem {


    public static final int TYPE_HEADER = 0x00;
    public static final int TYPE_TXT = 0x01;
    public static final int TYPE_IMAGE = 0x02;
    public static final int TYPE_OTHER = 0x03;

    public int type;
    public String content;
    public int icon;

    public DataItem(){

    }

    public DataItem(final int type) {
        this.type = type;
    }

    public DataItem(final int type, final String content) {
        this.type = type;
        this.content = content;
    }

    public DataItem(final int type, final int icon) {
        this.icon = icon;
        this.type = type;
    }
}
