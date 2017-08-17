package com.fflorio.smaatoacclibrary.models;

import java.util.Locale;

/**
 * Created by francesco on 2017-08-17.
 */

public class User {

    public final String name;
    public final String country;

    public User(String name, String country) {
        this.name = name;
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%s - %s", name, country);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!name.equals(user.name)) return false;
        return country.equals(user.country);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }
}
