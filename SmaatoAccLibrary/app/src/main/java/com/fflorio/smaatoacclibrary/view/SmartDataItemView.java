package com.fflorio.smaatoacclibrary.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.fflorio.smaatoacclibrary.R;
import com.fflorio.smaatoacclibrary.models.DataItem;

/**
 * Created by francesco on 2017-08-17.
 */

public class SmartDataItemView extends ScrollView implements SmartDataItemArch.View{

    private SmartDataItemArch.Presenter presenter;
    private DataItemView dataItemView;
    private ProgressBar progressBar;

    public SmartDataItemView(@NonNull Context context) {
        super(context);
        init();
    }

    public SmartDataItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SmartDataItemView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setFillViewport(true);
        LayoutInflater.from(getContext()).inflate(R.layout.view__smart_data_item, this, true);
        dataItemView = findViewById(R.id.dataItemView);
        progressBar = findViewById(R.id.progressBar);
        presenter = new SmartDataItemPresenter(this);
    }

    public void loadData(){
        presenter.loadData();
    }

    @Override
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
        dataItemView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.GONE);
        dataItemView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData(@NonNull DataItem dataItem) {
        dataItemView.updateWith(dataItem);
    }

    @Override
    public void showError(@StringRes int messageResId) {
        Toast.makeText(getContext(), messageResId, Toast.LENGTH_SHORT).show();
    }
}
