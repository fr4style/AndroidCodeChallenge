package com.fflorio.smaatoacclibrary.dataloader;

import android.util.Patterns;
import android.webkit.URLUtil;

import com.fflorio.smaatoacclibrary.models.DataObject;
import com.fflorio.smaatoacclibrary.models.ImageObject;
import com.fflorio.smaatoacclibrary.models.ObjectType;
import com.fflorio.smaatoacclibrary.models.TextObject;
import com.fflorio.smaatoacclibrary.models.User;
import com.fflorio.smaatoacclibrary.utils.Tools;

/**
 * Created by francesco on 2017-08-17.
 */

public class DataValidator {
    DataValidator(){}

    boolean isValid(final long value){
        return value < 0;
    }

    boolean isValid(final ObjectType type){
        return type != null;
    }

    boolean isValid(final User user){
        return user != null && !Tools.isEmpty(user.name) && !Tools.isEmpty(user.country);
    }

    boolean isValid(final DataObject dataObject){
        if(dataObject instanceof ImageObject){
            return isValidImageObject((ImageObject)dataObject);
        }
        if(dataObject instanceof TextObject){
            return isValidTextObject((TextObject) dataObject);
        }
        return false;
    }

    private boolean isValidImageObject(final ImageObject imageObject){
        return imageObject != null && !Tools.isEmpty(imageObject.imageUrl) &&
                (imageObject.imageUrl.toLowerCase().startsWith("http://") ||
                 imageObject.imageUrl.toLowerCase().startsWith("https://") );
    }

    private boolean isValidTextObject(final TextObject textObject){
        return textObject != null && !Tools.isEmpty(textObject.text);
    }
}
