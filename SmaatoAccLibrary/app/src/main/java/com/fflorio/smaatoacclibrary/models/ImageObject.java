package com.fflorio.smaatoacclibrary.models;

/**
 * Created by francesco on 2017-08-17.
 */

public class ImageObject implements DataObject {
    public final String imageUrl;
    public final String type = "IMG";

    public ImageObject(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ImageObject{" +
                "imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageObject that = (ImageObject) o;

        return imageUrl.equals(that.imageUrl);

    }

    @Override
    public int hashCode() {
        return imageUrl.hashCode();
    }
}
