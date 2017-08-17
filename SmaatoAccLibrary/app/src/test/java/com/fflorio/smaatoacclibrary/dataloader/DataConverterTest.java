package com.fflorio.smaatoacclibrary.dataloader;

import com.fflorio.smaatoacclibrary.models.DataItem;
import com.fflorio.smaatoacclibrary.models.ImageObject;
import com.fflorio.smaatoacclibrary.models.TextObject;
import com.fflorio.smaatoacclibrary.models.User;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by francesco on 2017-08-17.
 */
public class DataConverterTest {
    @Test
    public void convertValidDataItemImage() throws Exception {
        DataConverter dataConverter = new DataConverter();

        String imageInput = "{" +
                "\"created\": -449280, \n" +
                "\"type\": \"IMG\",\n" +
                "\"data\": { \"url\": \"https://pixabay.com/xxxx\" },\n" +
                "\"user\": { \"name\": \"John\", \"country\": \"USA\" }" +
                "}";

        DataItem<ImageObject> expected = new DataItem<>(-449280,
                new ImageObject("https://pixabay.com/xxxx"),
                new User("John", "USA"));

        JSONObject jsonObject = new JSONObject(imageInput);
        assertEquals(expected, dataConverter.convertToDataItem(jsonObject));
    }

    @Test
    public void convertValidDataItemText() throws Exception {
        DataConverter dataConverter = new DataConverter();

        String imageInput = "{" +
                "\"created\": -449280, \n" +
                "\"type\": \"TEXT\",\n" +
                "\"data\": { \"text\": \"Test\" },\n" +
                "\"user\": { \"name\": \"John\", \"country\": \"USA\" }" +
                "}";

        DataItem<TextObject> expected = new DataItem<>(-449280,
                new TextObject("Test"),
                new User("John", "USA"));

        JSONObject jsonObject = new JSONObject(imageInput);
        assertEquals(expected, dataConverter.convertToDataItem(jsonObject));
    }

    @Test
    public void convertInvalidType() throws Exception {
        DataConverter dataConverter = new DataConverter();
        String imageInput = "{" +
                "\"created\": -449280, \n" +
                "\"type\": \"IMG\",\n" +
                "\"data\": { \"text\": \"Test\" },\n" +
                "\"user\": { \"name\": \"John\", \"country\": \"USA\" }" +
                "}";

        JSONObject jsonObject = new JSONObject(imageInput);
        assertNull(dataConverter.convertToDataItem(jsonObject));
    }

    @Test
    public void convertInvalidDataItemWithMissingData() throws Exception {
        DataConverter dataConverter = new DataConverter();

        String imageInput = "{" +
                "\"created\": -449280, \n" +
                "\"type\": \"IMG\",\n" +
                "\"user\": { \"name\": \"John\", \"country\": \"USA\" }" +
                "}";
        JSONObject jsonObject = new JSONObject(imageInput);
        assertNull(dataConverter.convertToDataItem(jsonObject));
    }

    @Test
    public void convertInvalidDataItemWithMissingUser() throws Exception {
        DataConverter dataConverter = new DataConverter();

        String imageInput = "{" +
                "\"created\": -449280, \n" +
                "\"type\": \"IMG\",\n" +
                "\"data\": { \"url\": \"https://pixabay.com/xxxx\" },\n" +
                "}";
        JSONObject jsonObject = new JSONObject(imageInput);
        assertNull(dataConverter.convertToDataItem(jsonObject));
    }

    @Test
    public void convertInvalidDataItemWithPartialUser() throws Exception {
        DataConverter dataConverter = new DataConverter();

        String imageInput = "{" +
                "\"created\": -449280, \n" +
                "\"type\": \"IMG\",\n" +
                "\"data\": { \"url\": \"https://pixabay.com/xxxx\" },\n" +
                "\"user\": { \"country\": \"USA\" }" +
                "}";
        JSONObject jsonObject = new JSONObject(imageInput);
        assertNull(dataConverter.convertToDataItem(jsonObject));
    }

    @Test
    public void convertInvalidDataItemWithCreatedValue0() throws Exception {
        DataConverter dataConverter = new DataConverter();

        String imageInput = "{" +
                "\"created\": 0 \n" +
                "}";
        JSONObject jsonObject = new JSONObject(imageInput);
        assertNull(dataConverter.convertToDataItem(jsonObject));
    }

    @Test
    public void convertInvalidDataItemWithPositiveCreatedValue() throws Exception {
        DataConverter dataConverter = new DataConverter();

        String imageInput = "{" +
                "\"created\": 449280, \n" +
                "\"type\": \"IMG\",\n" +
                "\"data\": { \"url\": \"https://pixabay.com/xxxx\" },\n" +
                "\"user\": { \"name\": \"John\", \"country\": \"USA\" }" +
                "}";
        JSONObject jsonObject = new JSONObject(imageInput);
        assertNull(dataConverter.convertToDataItem(jsonObject));
    }

    @Test
    public void convertInvalidJson() throws Exception {
        DataConverter dataConverter = new DataConverter();

        String imageInput = "{" +
                "}";
        JSONObject jsonObject = new JSONObject(imageInput);
        assertNull(dataConverter.convertToDataItem(jsonObject));
    }
}