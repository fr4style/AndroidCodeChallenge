package com.fflorio.smaatoacclibrary.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by francesco on 2017-08-17.
 */

public class DataItem<T extends DataObject> {

    public long created;
    public T data;
    public User user;

    public DataItem(long created, T data, User user) {
        this.created = created;
        this.data = data;
        this.user = user;
    }

    public String getDate(){
        final long itemDate = System.currentTimeMillis() + created;
        final Date date=new Date(itemDate);
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
        return df2.format(date);
    }

    @Override
    public String toString() {
        return "DataItem{" +
                "created=" + created +
                ", data=" + data +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataItem<?> dataItem = (DataItem<?>) o;

        if (created != dataItem.created) return false;
        if (!data.equals(dataItem.data)) return false;
        return user.equals(dataItem.user);

    }

    @Override
    public int hashCode() {
        int result = (int) (created ^ (created >>> 32));
        result = 31 * result + data.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }
}
