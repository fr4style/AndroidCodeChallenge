package com.fflorio.smaatoacclibrary.dataloader;

import com.fflorio.smaatoacclibrary.R;
import com.fflorio.smaatoacclibrary.models.DataItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by francesco on 2017-08-17.
 */

public class DefaultDataLoader extends DataLoader {

    private final DefaultDataLoaderConfig config;
    private DataConverter dataConverter = new DataConverter();

    public DefaultDataLoader(DefaultDataLoaderConfig config) {
        this.config = config;
    }

    @Override
    protected int performLoadTask() {
        try {
            URL url = new URL(config.dataUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setConnectTimeout(config.connectionTimeoutInMs);
            connection.setReadTimeout(config.readTimeoutInMs);

            InputStream inputStream = connection.getInputStream();
            final String jsonString = dataConverter.convertToString(inputStream);
            inputStream.close();
            dataItems = parseResponse(jsonString);
        }catch (IOException e){
            return R.string.error_ioe;
        }catch (JSONException jsone){
            return R.string.error_json;
        }
        return 0;
    }

    private List<DataItem> parseResponse(final String rawJsonString) throws JSONException{
        dataItems = new ArrayList<>();
        final JSONArray jsonArray = new JSONArray(rawJsonString);
        JSONObject jsonObject;
        DataItem dataItem;
        for(int i = 0; i<jsonArray.length(); i++){
            jsonObject = jsonArray.optJSONObject(i);
            dataItem = dataConverter.convertToDataItem(jsonObject);
            if(dataItem != null){
                dataItems.add(dataItem);
            }
        }
        return dataItems;
    }

}
