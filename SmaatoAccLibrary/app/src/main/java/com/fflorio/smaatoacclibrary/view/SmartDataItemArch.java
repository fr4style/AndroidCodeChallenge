package com.fflorio.smaatoacclibrary.view;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.fflorio.smaatoacclibrary.models.DataItem;

/**
 * Created by francesco on 2017-08-17.
 */

interface SmartDataItemArch {

    interface View{
        void showLoader();
        void hideLoader();
        void showData(@NonNull final DataItem dataItem);
        void showError(@StringRes final int messageResId);
    }

    interface Presenter{
        void loadData();
        void release();
    }
}
