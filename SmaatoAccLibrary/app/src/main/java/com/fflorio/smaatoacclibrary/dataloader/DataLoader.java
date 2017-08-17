package com.fflorio.smaatoacclibrary.dataloader;

import android.os.AsyncTask;
import android.support.annotation.StringRes;

import com.fflorio.smaatoacclibrary.models.DataItem;

import java.util.List;

/**
 * Created by francesco on 2017-08-17.
 */

public abstract class DataLoader {
    protected List<DataItem> dataItems = null;

    public DataItem getRandomDataItem(){
        if(dataItems == null){
            throw new RuntimeException("The loadAll() is required before getRandomDataItem()");
        }

        if(!dataItems.isEmpty()){
            final int randomIndex = (int)(Math.random() * dataItems.size());
            return dataItems.get(randomIndex);
        }

        return null;
    }
    public void loadAll(final Callback callback) {
        new AsyncTask<Void, Void, Integer>(){
            @Override
            protected @StringRes Integer doInBackground(Void... voids) {
                if(dataItems == null) {
                    return performLoadTask();
                }
                return 0;
            }

            @Override
            protected void onPostExecute(@StringRes Integer errorMessageResId) {
                callback.onTaskEnd(dataItems, errorMessageResId);
            }
        }.execute();
    }

    protected abstract @StringRes int performLoadTask();

    public interface Callback{
        /**
         *
         * @param dataItemList the list of dataItems returned by the web service, or null if there was a problem.
         *                     If the value is null, the errorMessageResId should contain the message resId to show.
         * @param errorMessageResId the error message resId to show, or 0 if there is no error to show
         */
        void onTaskEnd(List<DataItem> dataItemList, @StringRes int errorMessageResId);
    }
}
