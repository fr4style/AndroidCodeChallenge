package com.fflorio.smaatoacclibrary.dataloader;

import com.fflorio.smaatoacclibrary.models.ImageObject;
import com.fflorio.smaatoacclibrary.models.ObjectType;
import com.fflorio.smaatoacclibrary.models.TextObject;
import com.fflorio.smaatoacclibrary.models.User;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by francesco on 2017-08-17.
 */
public class DataValidatorTest {
    DataValidator dataValidator;

    @Before
    public void init(){
        dataValidator = new DataValidator();
    }

    @Test
    public void isValidCreated() throws Exception {
        assertTrue(dataValidator.isValid(-100));
        assertFalse(dataValidator.isValid(0));
        assertFalse(dataValidator.isValid(100));
    }

    @Test
    public void isValidObjectType() throws Exception {
        ObjectType nullObjectType = null;
        assertFalse(dataValidator.isValid(nullObjectType));
        assertTrue(dataValidator.isValid(ObjectType.IMAGE));
        assertTrue(dataValidator.isValid(ObjectType.TEXT));
    }

    @Test
    public void isValidUser() throws Exception {
        User validUser = new User("Francesco", "Italy");
        assertTrue(dataValidator.isValid(validUser));

        User nullUser = null;
        assertFalse(dataValidator.isValid(nullUser));

        User emptyUser = new User("", "");
        assertFalse(dataValidator.isValid(emptyUser));

        User missingValuesUser = new User(null, null);
        assertFalse(dataValidator.isValid(missingValuesUser));
    }

    @Test
    public void isValidDataObject() throws Exception {
        ImageObject validImageObject = new ImageObject("http://www.google.it");
        assertTrue(dataValidator.isValid(validImageObject));
        ImageObject validUrlUppercase = new ImageObject("HTTpS://www.google.it");
        assertTrue(dataValidator.isValid(validUrlUppercase));
        TextObject validTextObject = new TextObject("Test Text Object");
        assertTrue(dataValidator.isValid(validTextObject));

        TextObject textObjectNull = null;
        assertFalse(dataValidator.isValid(textObjectNull));
        ImageObject imageObjecttNull = null;
        assertFalse(dataValidator.isValid(imageObjecttNull));
        ImageObject invalidUrl = new ImageObject("dklkjfsdkljdsklfj");
        assertFalse(dataValidator.isValid(invalidUrl));
    }

}