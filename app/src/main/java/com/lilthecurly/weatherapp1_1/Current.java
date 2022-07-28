
package com.lilthecurly.weatherapp1_1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Current {

    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
    @SerializedName("temp_c")
    @Expose
    private Double tempC;
    @SerializedName("condition")
    @Expose
    private Condition condition;
    @SerializedName("wind_kph")
    @Expose
    private Double windKph;
    @SerializedName("pressure_mb")
    @Expose
    private Double pressureMb;
    @SerializedName("precip_mm")
    @Expose
    private Double precipMm;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("cloud")
    @Expose
    private Integer cloud;
    @SerializedName("feelslike_c")
    @Expose
    private Double feelslikeC;
    @SerializedName("vis_km")
    @Expose
    private Double visKm;
    @SerializedName("uv")
    @Expose
    private Double uv;
    @SerializedName("gust_kph")
    @Expose
    private Double gustKph;

    public String getLastUpdated() {
        return lastUpdated;
    }

    public Double getTempC() {
        return tempC;
    }

    public Condition getCondition() {
        return condition;
    }

    public Double getWindKph() {
        return windKph;
    }

    public Double getPressureMb() {
        return pressureMb;
    }

    public Double getPrecipMm() {
        return precipMm;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Integer getCloud() {
        return cloud;
    }

    public Double getFeelslikeC() {
        return feelslikeC;
    }

    public Double getVisKm() {
        return visKm;
    }

    public Double getUv() {
        return uv;
    }

    public Double getGustKph() {
        return gustKph;
    }

}
