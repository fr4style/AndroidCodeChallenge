package com.fflorio.smaatoacclibrary.dataloader;

/**
 * Created by francesco on 2017-08-17.
 */

public class DataLoaderFactory {

    public static DataLoader getDataLoader(){
        return new DefaultDataLoader(new DefaultDataLoaderConfig.Builder().build());
    }
}
