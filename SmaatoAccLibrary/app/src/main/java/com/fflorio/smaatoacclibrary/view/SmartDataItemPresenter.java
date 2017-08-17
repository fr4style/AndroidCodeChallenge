package com.fflorio.smaatoacclibrary.view;

import android.support.annotation.StringRes;

import com.fflorio.smaatoacclibrary.R;
import com.fflorio.smaatoacclibrary.dataloader.DataLoader;
import com.fflorio.smaatoacclibrary.dataloader.DataLoaderFactory;
import com.fflorio.smaatoacclibrary.models.DataItem;

import java.util.List;

/**
 * Created by francesco on 2017-08-17.
 */

class SmartDataItemPresenter implements SmartDataItemArch.Presenter {
    private SmartDataItemArch.View view;
    private DataLoader dataLoader;

    SmartDataItemPresenter(final SmartDataItemArch.View view){
        this.view = view;
    }

    @Override
    public void loadData() {
        if(view != null){
            view.showLoader();
        }

        dataLoader = DataLoaderFactory.getDataLoader();
        dataLoader.loadAll(new DataLoader.Callback() {
            @Override
            public void onTaskEnd(List<DataItem> list, @StringRes int errorMessageResId) {
                manageDataLoaded(list, errorMessageResId);
            }
        });
    }

    @Override
    public void release(){
        view = null;
    }

    private void manageDataLoaded(List<DataItem> list, @StringRes int errorMessageResId){
        if(view != null) {
            view.hideLoader();
            if (list == null) {
                view.showError(errorMessageResId > 0 ? errorMessageResId : R.string.error_generic);
            }else if(list.isEmpty()){
                view.showError(R.string.error_no_data);
            } else {
                view.showData(dataLoader.getRandomDataItem());
            }
        }
    }
}
