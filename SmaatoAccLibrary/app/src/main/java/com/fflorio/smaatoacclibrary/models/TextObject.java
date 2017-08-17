package com.fflorio.smaatoacclibrary.models;

/**
 * Created by francesco on 2017-08-17.
 */

public class TextObject implements DataObject {
    public String text;
    public final String type = "TEXT";

    public TextObject(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TextObject{" +
                "text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextObject that = (TextObject) o;

        return text != null ? text.equals(that.text) : that.text == null;

    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }
}
