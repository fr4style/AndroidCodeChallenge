package com.fflorio.smaatoacclibrary.dataloader;

import com.fflorio.smaatoacclibrary.models.DataItem;
import com.fflorio.smaatoacclibrary.models.DataObject;
import com.fflorio.smaatoacclibrary.models.ImageObject;
import com.fflorio.smaatoacclibrary.models.ObjectType;
import com.fflorio.smaatoacclibrary.models.TextObject;
import com.fflorio.smaatoacclibrary.models.User;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by francesco on 2017-08-17.
 */

public class DataConverter {

    private DataValidator dataValidator = new DataValidator();

    String convertToString(final InputStream inputStream) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        final StringBuilder responseSB = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            responseSB.append(line);
        }
        bufferedReader.close();
        return responseSB.toString();
    }

    DataItem convertToDataItem(final JSONObject jsonObject){
        final long created = jsonObject.optLong("created");
        if(!dataValidator.isValid(created)){
            return null;
        }

        final String rawType = jsonObject.optString("type");
        final ObjectType type = convertToObjectType(rawType);
        if(!dataValidator.isValid(type)){
            return null;
        }

        final DataObject dataObject = convertToDataObject(type, jsonObject.optJSONObject("data"));
        if(!dataValidator.isValid(dataObject)){
            return null;
        }

        final User user = convertToUser(jsonObject.optJSONObject("user"));
        if(!dataValidator.isValid(user)){
            return null;
        }

        return new DataItem(created, dataObject, user);
    }

    private User convertToUser(final JSONObject rawUser){
        if(rawUser == null){
            return null;
        }
        return new User(rawUser.optString("name"), rawUser.optString("country"));
    }

    private DataObject convertToDataObject(final ObjectType objectType, final JSONObject rawData){
        if(rawData == null){
            return null;
        }
        switch (objectType){
            case IMAGE: return convertToImageObject(rawData);
            case TEXT: return convertToTextObject(rawData);
            default: return null;
        }
    }

    private TextObject convertToTextObject(final JSONObject rawData){
        return new TextObject(rawData.optString("text"));
    }

    private ImageObject convertToImageObject(final JSONObject rawData){
        return new ImageObject(rawData.optString("url"));
    }

    private ObjectType convertToObjectType(final String value){
        if("IMG".equalsIgnoreCase(value)){
            return ObjectType.IMAGE;
        }else if("TEXT".equalsIgnoreCase(value)){
            return ObjectType.TEXT;
        }
        return null;
    }


}
