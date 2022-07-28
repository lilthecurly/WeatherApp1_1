package com.lilthecurly.weatherapp1_1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("v1/current.json?")
    Call<WeatherClass> getCurrentWeatherData(@Query("key") String key,
                                                @Query("q") String q,
                                                @Query("aqi") String aqi);
}
