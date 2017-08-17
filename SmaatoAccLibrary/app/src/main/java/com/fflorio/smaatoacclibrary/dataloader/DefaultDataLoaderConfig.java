package com.fflorio.smaatoacclibrary.dataloader;

/**
 * Created by francesco on 2017-08-17.
 */

public class DefaultDataLoaderConfig {
    public final String dataUrl;
    public final int connectionTimeoutInMs;
    public final int readTimeoutInMs;

    private DefaultDataLoaderConfig(Builder builder) {
        dataUrl = builder.dataUrl;
        connectionTimeoutInMs = builder.connectionTimeoutInMs;
        readTimeoutInMs = builder.readTimeoutInMs;
    }


    public static final class Builder {
        private String dataUrl = "http://private-d847e-demoresponse.apiary-mock.com/questions";;
        private int connectionTimeoutInMs = 10_000;
        private int readTimeoutInMs  = 5_000;

        public Builder() {
        }

        public Builder dataUrl(String val) {
            dataUrl = val;
            return this;
        }

        public Builder connectionTimeoutInMs(int val) {
            connectionTimeoutInMs = val;
            return this;
        }

        public Builder readTimeoutInMs(int val) {
            readTimeoutInMs = val;
            return this;
        }

        public DefaultDataLoaderConfig build() {
            return new DefaultDataLoaderConfig(this);
        }
    }
}
