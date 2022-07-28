
package com.lilthecurly.weatherapp1_1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherClass {

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("current")
    @Expose
    private Current current;

    public Location getLocation() {
        return location;
    }

    public Current getCurrent() {
        return current;
    }

}
