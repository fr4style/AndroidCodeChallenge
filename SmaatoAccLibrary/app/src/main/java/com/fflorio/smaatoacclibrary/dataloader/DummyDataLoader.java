package com.fflorio.smaatoacclibrary.dataloader;

import com.fflorio.smaatoacclibrary.models.DataItem;
import com.fflorio.smaatoacclibrary.models.TextObject;
import com.fflorio.smaatoacclibrary.models.User;

import java.util.ArrayList;

/**
 * Created by francesco on 2017-08-17.
 */

public class DummyDataLoader extends DataLoader {
    private final long TIME_TO_WAIT_IN_MS = 5000;

    @Override
    protected int performLoadTask() {
        try {
            Thread.sleep(TIME_TO_WAIT_IN_MS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dataItems = new ArrayList<>();
        final User user = new User("Francesco Florio", "Italy");
        final TextObject textObject = new TextObject("Dummy Item");
        dataItems.add(new DataItem<>(-1, textObject, user));
        return 0;
    }
}
